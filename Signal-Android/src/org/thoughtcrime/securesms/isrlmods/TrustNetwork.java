package org.thoughtcrime.securesms.isrlmods;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.util.Log;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.thoughtcrime.securesms.database.Address;
import org.thoughtcrime.securesms.database.DatabaseFactory;
import org.thoughtcrime.securesms.database.IdentityDatabase;
import org.thoughtcrime.securesms.recipients.Recipient;
import org.thoughtcrime.securesms.sms.MessageSender;
import org.thoughtcrime.securesms.sms.OutgoingTextMessage;
import org.thoughtcrime.securesms.util.Base64;
import org.whispersystems.libsignal.ecc.ECPublicKey;
import org.whispersystems.libsignal.state.PreKeyBundle;
import org.whispersystems.signalservice.api.push.exceptions.AuthorizationFailedException;
import org.whispersystems.signalservice.api.push.exceptions.ExpectationFailedException;
import org.whispersystems.signalservice.api.push.exceptions.NonSuccessfulResponseCodeException;
import org.whispersystems.signalservice.api.push.exceptions.NotFoundException;
import org.whispersystems.signalservice.api.push.exceptions.PushNetworkException;
import org.whispersystems.signalservice.api.push.exceptions.RateLimitException;
import org.whispersystems.signalservice.api.push.exceptions.UnregisteredUserException;
import org.whispersystems.signalservice.internal.push.DeviceLimit;
import org.whispersystems.signalservice.internal.push.DeviceLimitExceededException;
import org.whispersystems.signalservice.internal.push.LockedException;
import org.whispersystems.signalservice.internal.push.MismatchedDevices;
import org.whispersystems.signalservice.internal.push.PreKeyResponse;
import org.whispersystems.signalservice.internal.push.PreKeyResponseItem;
import org.whispersystems.signalservice.internal.push.StaleDevices;
import org.whispersystems.signalservice.internal.push.exceptions.MismatchedDevicesException;
import org.whispersystems.signalservice.internal.push.exceptions.StaleDevicesException;
import org.whispersystems.signalservice.internal.util.JsonUtil;
import org.whispersystems.signalservice.internal.push.PushServiceSocket;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TrustNetwork {
    private static boolean USE_TRUST_NETWORK = true;
    private static int NUM_RECENT_FRIENDS = 3;
    private static Set<String> suspiciousIds = new HashSet<>();
    private static Map<String, TrustNetworkRecord> trustRecords = new HashMap<>();
    /*
    - compare timestamps and see
    - take note of number of mismatches and matches (create separate class)

     -
     */

    public static String[] getRecentFriendIds (Context context) {
        // [_id, date, message_count, recipient_ids, snippet, snippet_cs, read, unread_count, type, error, snippet_type, snippet_uri, archived, status, delivery_receipt_count, expires_in, last_seen, read_receipt_count, block, notification, call_ringtone, vibrate, call_vibrate, mute_until, color, seen_invite_reminder, default_subscription_id, expire_messages, registered, profile_key, system_display_name, system_contact_photo, system_phone_label, system_contact_uri, signal_profile_name, signal_profile_avatar, profile_sharing_approval, group_id, title, members, avatar, avatar_id, avatar_key, avatar_content_type, avatar_relay, avatar_digest, timestamp, active, mms]
        Cursor c = DatabaseFactory.getThreadDatabase(context).getConversationList();
        ArrayList<String> recentFriendIds = new ArrayList<>();

        int i = 0;
        while (i < NUM_RECENT_FRIENDS && !c.isLast() && c.moveToNext()) {
            String id = c.getString(c.getColumnIndex("recipient_ids"));
            if (!suspiciousIds.contains(id)) {
                recentFriendIds.add(id);
                i++;
            }
        }
        return recentFriendIds.toArray(new String[0]);
    }

/*
    public static String debug (Context context) {
        String path = String.format(PREKEY_DEVICE_PATH, destination.getNumber(), deviceId);

        if (destination.getRelay().isPresent()) {
            path = path + "?relay=" + destination.getRelay().get();
        }

        String             responseText = makeServiceRequest(path, "GET", null);
    }
*/


    public static void initiateKeyCheck (Context context, String addressToCheck, boolean forceSms, long expiresIn, int subscriptionId) {
        if (!USE_TRUST_NETWORK) { return; }

        Log.i("AARON_TAG", "Initiate key check..");

        String[] trustedFriendIds = getRecentFriendIds(context); //{ "+15555215558"};
        for (String friendId : trustedFriendIds) {
            TrustNetworkKeyVerificationMessage message = new TrustNetworkKeyVerificationMessage(addressToCheck, false);
            Recipient friend = Recipient.from(context, Address.fromExternal(context, friendId), false);
            sendTrustNetworkMessage(message, friend, context);
        }
    }

    public static void sendTrustNetworkMessage (TrustNetworkMessage msg, Recipient friend, Context context) {
        OutgoingTextMessage trustMessage = new OutgoingTextMessage(friend,  msg.deriveTextMessage(), 0, -1);
        MessageSender.send(context, trustMessage, -2, false, () -> {});
    }

    public static void handleKeyHandshake (Context context, Recipient recipient, String rawMessage) {
        if (!USE_TRUST_NETWORK) { return; }

        Log.i("AARON_TAG", "Retrieved key handshake message: " + rawMessage);
        TrustNetworkMessage message = new TrustNetworkMessage(rawMessage);

        switch (message.getMessageType()) {
            case "KEYVERIFY": {
                TrustNetworkKeyVerificationMessage verification = TrustNetworkKeyVerificationMessage.asVerificationMessage(message);
                IdentityDatabase.IdentityRecord record = getIdFromMatchingHash(context, verification.getUserHash());

                TrustNetworkKeyValidationMessage out = new TrustNetworkKeyValidationMessage(record, verification.getUserHash());
                sendTrustNetworkMessage(out, recipient, context);
                Log.i("AARON_TAG", "Sending KEYVALIDATE");
                break;
            }
            case "KEYVALIDATE": {
                TrustNetworkKeyValidationMessage validation = TrustNetworkKeyValidationMessage.asValidationMessage(message);
                IdentityDatabase.IdentityRecord record = getIdFromMatchingHash(context, validation.getUserHash());

                Log.i("AARON_TAG", "Retrieved the record from key validation message: " + record);
                long timestamp = validation.getTimestamp();
                String id = record.getAddress().toPhoneString();


                Log.i("AARON_TAG", "Retrieved " + validation.getKeyHash() + ", " + timestamp);

                if (record != null) {
                    int myKeyHash = record.getIdentityKey().hashCode();
                    long myTimestamp = record.getTimestamp();
                    Log.i("AARON_TAG", "In comparison, mine are " + myKeyHash + ", " + myTimestamp);

                    if (!trustRecords.containsKey(id)) {
                        trustRecords.put(id, new TrustNetworkRecord(id));
                        trustRecords.get(id).addRecordFromMessage(new TrustNetworkKeyValidationMessage(record, id));
                    }

                    if (validation.knowsContact()) {
                        trustRecords.get(id).addRecordFromMessage(validation);
                    }
                }

                break;
            }
        }
    }

    private static IdentityDatabase.IdentityRecord getIdFromMatchingHash (Context context, String id_hash) {
        Cursor c = DatabaseFactory.getIdentityDatabase(context).getIdentities();
        IdentityDatabase.IdentityRecord record = null;
        while (!c.isLast() && c.moveToNext()) {
            String c_id = c.getString(c.getColumnIndex("address"));
            String c_hash = computeHashString(c_id);
            Log.i("AARON_TAG", "\t" + c_id + " -> " + c_hash + " =?= " + id_hash);
            if (c_hash.equals(id_hash)) {
                record = DatabaseFactory.getIdentityDatabase(context).getIdentity(Address.fromExternal(context, c_id)).orNull();
                break;
            }
        }
        return record;
    }

    public static boolean isIdSuspicious (String id) {
        if (trustRecords.containsKey(id)) {
            return trustRecords.get(id).isSuspicious();
        } else {
            return false;
        }
    }

    public static String computeHashString (String s) {
        String userId_hash = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(s.getBytes(StandardCharsets.UTF_8));
            userId_hash = Base64.encodeBytes(hash);
        } catch (NoSuchAlgorithmException e) {

        }
        return userId_hash;
    }
}
