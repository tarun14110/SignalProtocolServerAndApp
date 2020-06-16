package org.thoughtcrime.securesms.isrlmods;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import org.thoughtcrime.securesms.database.IdentityDatabase;

public class TrustNetworkKeyValidationMessage extends TrustNetworkMessage {

    public TrustNetworkKeyValidationMessage(IdentityDatabase.IdentityRecord record, String userId_hash) {
        super();

        try {
            this.headers.put("type", "KEYVALIDATE");


            if (record != null) {
                this.headers.put("knows_hash", true);
                this.headers.put("user_hash", userId_hash);
                this.headers.put("key_hash", record.getIdentityKey().hashCode());
                this.headers.put("timestamp", record.getTimestamp());
            } else {
                this.headers.put("knows_hash", false);
                this.headers.put("user_hash", userId_hash);
            }

        } catch (JSONException e) {

        }
    }

    public TrustNetworkKeyValidationMessage (TrustNetworkMessage message) {
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

    public int getKeyHash () {
        try {
            return this.headers.getInt("key_hash");
        } catch (JSONException e) {
            return -1;
        }
    }

    public boolean knowsContact () {
        try {
            return this.headers.getBoolean("knows_hash");
        } catch (JSONException e) {
            return false;
        }
    }

    public static TrustNetworkKeyValidationMessage asValidationMessage (TrustNetworkMessage msg) {
        if (msg.getMessageType().equals("KEYVALIDATE") && msg.getHeader("user_hash") != null) {
            return new TrustNetworkKeyValidationMessage(msg);
        } else {
            return null;
        }
    }

}
