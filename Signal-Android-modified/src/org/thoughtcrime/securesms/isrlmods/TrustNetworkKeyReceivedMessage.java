package org.thoughtcrime.securesms.isrlmods;

import org.json.JSONException;
import org.json.JSONObject;
import org.thoughtcrime.securesms.database.IdentityDatabase;
import org.whispersystems.libsignal.IdentityKey;

public class TrustNetworkKeyReceivedMessage extends TrustNetworkMessage {

    public TrustNetworkKeyReceivedMessage(String type, int identityKeyHash, String userId_hash) {
        super();

        try {
            this.headers.put("type", type);


            if (identityKeyHash != 0) {
                this.headers.put("is_key_retrieved", true);
                this.headers.put("user_hash", userId_hash);
                this.headers.put("identity_key_hash", identityKeyHash/*record.getIdentityKey().hashCode()*/);
                //this.headers.put("timestamp", record.getTimestamp());
            } else {
                this.headers.put("is_key_retrieved", false);
                this.headers.put("user_hash", userId_hash);
            }

        } catch (JSONException e) {

        }
    }

    public TrustNetworkKeyReceivedMessage(TrustNetworkMessage message) {
        super();
        try {
            this.headers = new JSONObject(message.headers.toString());
        } catch (JSONException e) {

        }
    }

    public String getUserHash () {
        try {
            return this.headers.getString("user_hash");
        } catch (JSONException e) {
            return null;
        }
    }

    public long getTimestamp () {
        try {
            return this.headers.getLong("timestamp");
        } catch (JSONException e) {
            return -1;
        }
    }

    public int getIdentityKeyHAsh () {
        try {
            return this.headers.getInt("identity_key_hash");
        } catch (JSONException e) {
            return 0;
        }
    }

    public boolean isKeyRetrieved () {
        try {
            return this.headers.getBoolean("is_key_retrieved");
        } catch (JSONException e) {
            return false;
        }
    }

    public static TrustNetworkKeyReceivedMessage asKeyReceivedMessage (TrustNetworkMessage msg) {
        if ((msg.getMessageType().equals("KEYRESPONSEKEYSERVER") || msg.getMessageType().equals("KEYRESPONSEDATASET"))&& msg.getHeader("user_hash") != null) {
            return new TrustNetworkKeyReceivedMessage(msg);
        } else {
            return null;
        }
    }

}
