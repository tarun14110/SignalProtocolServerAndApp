package org.thoughtcrime.securesms.isrlmods;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.thoughtcrime.securesms.BuildConfig;
import org.thoughtcrime.securesms.database.Address;
import org.thoughtcrime.securesms.database.DatabaseFactory;
import org.thoughtcrime.securesms.database.IdentityDatabase;
import org.thoughtcrime.securesms.push.SignalServiceNetworkAccess;
import org.thoughtcrime.securesms.recipients.Recipient;
import org.thoughtcrime.securesms.sms.MessageSender;
import org.thoughtcrime.securesms.sms.OutgoingTextMessage;
import org.thoughtcrime.securesms.util.Base64;
import org.thoughtcrime.securesms.util.TextSecurePreferences;
import org.whispersystems.libsignal.state.PreKeyBundle;
import org.whispersystems.signalservice.api.push.SignalServiceAddress;
import org.whispersystems.signalservice.api.util.CredentialsProvider;
import org.whispersystems.signalservice.internal.push.PushServiceSocket;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class TrustNetwork {
    public static boolean USE_TRUST_NETWORK = false;
    private static int NUM_RECENT_FRIENDS = 3;
    private static Set<String> suspiciousIds = new HashSet<>();
    private static Map<String, TrustNetworkRecord> trustRecords = new HashMap<>();
    private static final String TAG = TrustNetwork.class.getSimpleName();
    static Random rand = new Random();
    private static boolean keyreceived = false;
    static String keySetFileName = "friends_keys_set.json";


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
        c.close();
        return recentFriendIds.toArray(new String[0]);
    }

    /*public static String debug (Context context) {
        String path = String.format(PREKEY_DEVICE_PATH, destination.getNumber(), deviceId);

        if (destination.getRelay().isPresent()) {
            path = path + "?relay=" + destination.getRelay().get();
        }

        String             responseText = makeServiceRequest(path, "GET", null);
    }*/


    public static void initiateKeyCheck (Context context, String addressToCheck, boolean forceSms, long expiresIn, int subscriptionId) {
        if (!USE_TRUST_NETWORK) { return; }

        Log.i("ISRL_TAG", "Initiate key check..");

        String[] trustedFriendIds = getRecentFriendIds(context); //{ "+15555215558"};
        for (String friendId : trustedFriendIds) {
            TrustNetworkKeyVerificationMessage message = new TrustNetworkKeyVerificationMessage(addressToCheck, false);
            Recipient friend = Recipient.from(context, Address.fromExternal(context, friendId), false);
            sendTrustNetworkMessage(message, friend, context);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static void initiateTrustNetwork (Context context, String addressToCheck) {
        if (!USE_TRUST_NETWORK) {
            return;
        }

        boolean isMutualFriend = false;

        if(isFilePresent(context, keySetFileName)) {
            String jsonString = read(context, keySetFileName);
            JSONObject jsonObj = null;
            try {
                jsonObj = new JSONObject(jsonString);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.i("ISRL_TAG", "Key data set: "  + jsonObj.toString());

            JSONArray keys = jsonObj.names();
            for (int i = 0; i < keys.length(); ++i) {
                try {
                    String key = keys.getString (i);
                    JSONObject value = jsonObj.getJSONObject(key);
                    String addressToCheckHash = computeHashString(addressToCheck);
                    if (value.has(addressToCheckHash)) {
                        // mutual friend found in keyset
                        isMutualFriend = true;
                        initiateKeyRetrieval(context, addressToCheck, "KEYRETRIEVEDATASET", key);
                        Log.i("ISRL_TAG", "Key found in key dataset and sent the key retrieval from dataset request to"  + key);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        // if no Mutual friend found, get key from key server
        if (!isMutualFriend) {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    Log.i("ISRL_TAG", "sending KEY RETRIEVAL REQUEST ");
                    initiateKeyRetrieval(context, addressToCheck, "KEYRETRIEVEKEYSERVER", "");
                }
            }, rand.nextInt(9000) + 1000);
        }



        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Log.i("ISRL_TAG", "sending SET retrieval Request");
                TrustNetworkKeySetRetrievalMessage message = new TrustNetworkKeySetRetrievalMessage(0);
                Recipient friend = Recipient.from(context, Address.fromExternal(context, addressToCheck), false);
                sendTrustNetworkMessage(message, friend, context);
            }
        }, 7000);


    }


        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static void initiateKeyRetrieval (Context context, String addressToCheck, String type, String friendToRetrieveKeyFrom) {

            Log.i("ISRL_TAG", "IN INITIATE");
        if (type=="KEYRETRIEVEKEYSERVER") {
            String[] trustedFriendIds = getRecentFriendIds(context);
            List<String> list = new ArrayList<String>(Arrays.asList(trustedFriendIds));
            list.remove(addressToCheck);
            trustedFriendIds = list.toArray(new String[0]);
            if (trustedFriendIds.length > 0 ) {
                String friendId = trustedFriendIds[rand.nextInt(trustedFriendIds.length)];
                TrustNetworkKeyRetrievalMessage message = new TrustNetworkKeyRetrievalMessage(type, addressToCheck, false);
                Recipient friend = Recipient.from(context, Address.fromExternal(context, friendId), false);
                sendTrustNetworkMessage(message, friend, context);
            }
        } else {
            Log.i("ISRL_TAG", "IN INITIATE ELSE");
                TrustNetworkKeyRetrievalMessage message = new TrustNetworkKeyRetrievalMessage(type, addressToCheck, false);
                Recipient friend = Recipient.from(context, Address.fromExternal(context, friendToRetrieveKeyFrom), false);
                keyreceived = false;
                sendTrustNetworkMessage(message, friend, context);
                while (!keyreceived) ;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static void sendContactsHashAndKey(Recipient recipient, Context context) {
        Map<String, IdentityDatabase.IdentityRecord> record = getAllKeys(context);
        TrustNetworkKeyValidationMessage out = new TrustNetworkKeyValidationMessage(record);
        sendTrustNetworkMessage(out, recipient, context);
    }

    public static void sendTrustNetworkMessage (TrustNetworkMessage msg, Recipient friend, Context context) {
        OutgoingTextMessage trustMessage = new OutgoingTextMessage(friend,  msg.deriveTextMessage(), 0, -1);
        Log.i("ISRL_TAG MESSSAGEE SIZE", String.valueOf(msg.deriveTextMessage().length()));
        MessageSender.send(context, trustMessage, -2, false, () -> {});
    }

    @TargetApi(Build.VERSION_CODES.N)
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static void handleKeyHandshake (Context context, Recipient recipient, String rawMessage) {
        if (!USE_TRUST_NETWORK) { return; }
        Log.i("ISRL_TAG", "Received Trust network message: " + rawMessage);
        TrustNetworkMessage message = new TrustNetworkMessage(rawMessage);

        switch (message.getMessageType()) {
            case "KEYSETRETRIEVE": {
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Log.i("ISRL_TAG", "sending key set");
                        sendContactsHashAndKey(recipient, context);
                    }
                }, rand.nextInt(7000) + 1000);
                break;
            }
            case "KEYSETRESPONSE": {
                TrustNetworkKeyValidationMessage validation = TrustNetworkKeyValidationMessage.asValidationMessage(message);
                if(isFilePresent(context, keySetFileName)) {
                    String jsonString = read(context, keySetFileName);
                    JSONObject jsonObj = null;
                    try {
                        jsonObj = new JSONObject(jsonString);
                        jsonObj.put(recipient.getAddress().toString(), validation.getdataJSON());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.i("ISRL_TAG", "friends key file after update"  + jsonObj.toString());
                } else {
                    JSONObject jsonObj = new JSONObject();
                    try {
                        jsonObj.put(recipient.getAddress().toString(), validation.getdataJSON());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    create(context, keySetFileName, jsonObj.toString());
                }
                break;
            }
            case "KEYRETRIEVEKEYSERVER": {
                TrustNetworkKeyRetrievalMessage retrievalMsg = TrustNetworkKeyRetrievalMessage.asKeyRetrievalMessage(message);
                SignalServiceAddress dest = new SignalServiceAddress(retrievalMsg.getUserID());
                List<PreKeyBundle> keyList = null;
                try {
                    keyList = new PushServiceSocket( new SignalServiceNetworkAccess(context).getConfiguration(context), new DynamicCredentialsProvider(context), BuildConfig.USER_AGENT).getPreKeys(dest, 1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.i(TAG, "Retrieved keys from key server " + (keyList.get(0).getIdentityKey().hashCode()));
                TrustNetworkKeyReceivedMessage out = new TrustNetworkKeyReceivedMessage("KEYRESPONSEKEYSERVER", keyList.get(0).getIdentityKey().hashCode(), retrievalMsg.getUserHash());

                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Log.i(TAG, "Sending KEYRESPONSEKEYSERVER");
                        sendTrustNetworkMessage(out, recipient, context);
                    }
                }, rand.nextInt(12000) + 1000);


                break;
            }
            case "KEYRESPONSEKEYSERVER": {
                TrustNetworkKeyReceivedMessage receivedKeyMsg = TrustNetworkKeyReceivedMessage.asKeyReceivedMessage(message);
                    IdentityDatabase.IdentityRecord record = getIdFromMatchingHash(context, receivedKeyMsg.getUserHash());
                    int myKeyHash = record.getIdentityKey().hashCode();
                    if (myKeyHash != receivedKeyMsg.getIdentityKeyHAsh()) {
                        Log.i("ISRL_TAG", "Key mismatch, Pair targeted attack detected ");
                    } else {
                        Log.i("ISRL_TAG", "Key matched");
                    }
                break;
            }

            case "KEYRETRIEVEDATASET": {
                TrustNetworkKeyRetrievalMessage retrievalMsg = TrustNetworkKeyRetrievalMessage.asKeyRetrievalMessage(message);
                IdentityDatabase.IdentityRecord record = getIdFromMatchingHash(context, retrievalMsg.getUserHash());
                TrustNetworkKeyReceivedMessage out = new TrustNetworkKeyReceivedMessage("KEYRESPONSEDATASET",record.getIdentityKey().hashCode(), retrievalMsg.getUserHash());
                Log.i(TAG, "Sending KEYRESPONSEDATASET");
                sendTrustNetworkMessage(out, recipient, context);
                break;
            }
            case "KEYRESPONSEDATASET": {
                TrustNetworkKeyReceivedMessage receivedKeyMsg = TrustNetworkKeyReceivedMessage.asKeyReceivedMessage(message);
                    keyreceived = true;
                    Log.i(TAG, "Received Identity key RESPONSE KEY DATASET");
                    IdentityDatabase.IdentityRecord record = null;

                    while (record == null) {
                        try {
                            Log.i("ISRL_TAG", "waiting for the key retrieval from key server");
                            TimeUnit.MILLISECONDS.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        record = getIdFromMatchingHash(context, receivedKeyMsg.getUserHash());
                    }
                    int myKeyHash = record.getIdentityKey().hashCode();
                    if (myKeyHash != receivedKeyMsg.getIdentityKeyHAsh()) {
                        Log.i("ISRL_TAG", "Key mismatch, attack detected");
                    } else {
                        Log.i("ISRL_TAG", "Key match, secure connection");
                    }
                break;
            }
        }
    }


    private static String read(Context context, String fileName) {
        try {
            FileInputStream fis = context.openFileInput(fileName);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (FileNotFoundException fileNotFound) {
            return null;
        } catch (IOException ioException) {
            return null;
        }
    }

    private static boolean create(Context context, String fileName, String jsonString){
        try {
            FileOutputStream fos = context.openFileOutput(fileName,Context.MODE_PRIVATE);
            if (jsonString != null) {
                fos.write(jsonString.getBytes());
            }
            fos.close();
            return true;
        } catch (FileNotFoundException fileNotFound) {
            return false;
        } catch (IOException ioException) {
            return false;
        }

    }

    public static boolean isFilePresent(Context context, String fileName) {
        String path = context.getFilesDir().getAbsolutePath() + "/" + fileName;
        File file = new File(path);
        return file.exists();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private static IdentityDatabase.IdentityRecord getIdFromMatchingHash (Context context, String id_hash) {
        Cursor c = DatabaseFactory.getIdentityDatabase(context).getIdentities();
        IdentityDatabase.IdentityRecord record = null;
        while (!c.isLast() && c.moveToNext()) {
            String c_id = c.getString(c.getColumnIndex("address"));
            String c_hash = computeHashString(c_id);
            Log.i("ISRL_TAG", "\t" + c_id + " -> " + c_hash + " =?= " + id_hash);
            if (c_hash.equals(id_hash)) {
                record = DatabaseFactory.getIdentityDatabase(context).getIdentity(Address.fromExternal(context, c_id)).orNull();
                break;
            }
        }
        return record;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private static Map<String, IdentityDatabase.IdentityRecord> getAllKeys (Context context) {
        Map<String, IdentityDatabase.IdentityRecord> idKeyMap = new HashMap<String, IdentityDatabase.IdentityRecord>();
        Cursor c = DatabaseFactory.getIdentityDatabase(context).getIdentities();
        while (!c.isLast() && c.moveToNext()) {
            String c_id = c.getString(c.getColumnIndex("address"));
            String c_hash = computeHashString(c_id);
            Log.i("ISRL_TAG", "\t" + c_id + " -> " + c_hash + " KEY RETRIEVE FROM DATABASE");
            idKeyMap.put(c_hash, DatabaseFactory.getIdentityDatabase(context).getIdentity(Address.fromExternal(context, c_id)).orNull());
        }
        return idKeyMap;
    }

    public static boolean isIdSuspicious (String id) {
        if (trustRecords.containsKey(id)) {
            return trustRecords.get(id).isSuspicious();
        } else {
            return false;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
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

    private static class DynamicCredentialsProvider implements CredentialsProvider {

        private final Context context;

        private DynamicCredentialsProvider(Context context) {
            this.context = context.getApplicationContext();
        }

        @Override
        public String getUser() {
            return TextSecurePreferences.getLocalNumber(context);
        }

        @Override
        public String getPassword() {
            return TextSecurePreferences.getPushServerPassword(context);
        }

        @Override
        public String getSignalingKey() {
            return TextSecurePreferences.getSignalingKey(context);
        }
    }
}
