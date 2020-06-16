package org.thoughtcrime.securesms.isrlmods;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class TrustNetworkKeyVerificationMessage extends TrustNetworkMessage {

    public TrustNetworkKeyVerificationMessage (String userId, boolean queryServer) {
        super();

        String userId_hash = TrustNetwork.computeHashString(userId);

        try {
            this.headers.put("type", "KEYVERIFY");
            this.headers.put("user_hash", userId_hash);
            this.headers.put("user_id", userId);
            this.headers.put("query_server", queryServer);
        } catch (JSONException e) {

        }
    }

    public TrustNetworkKeyVerificationMessage (TrustNetworkMessage message) {
        super();
        try {
            this.headers = new JSONObject(message.headers.toString());
        } catch (JSONException e) {

        }
    }

    public static TrustNetworkKeyVerificationMessage asVerificationMessage (TrustNetworkMessage msg) {
        if (msg.getMessageType().equals("KEYVERIFY") && msg.getHeader("user_hash") != null) {
            return new TrustNetworkKeyVerificationMessage(msg);
        } else {
            return null;
        }
    }

    public boolean shouldQueryServer () {
        try {
            return this.headers.getBoolean("query_server");
        } catch (JSONException e) {
            return false;
        }
    }

    public String getUserHash () {
        try {
            return this.headers.getString("user_hash");
        } catch (JSONException e) {
            return null;
        }
    }

    public String getUserID () {
        try {
            return this.headers.getString("user_id");
        } catch (JSONException e) {
            return null;
        }
    }
}
