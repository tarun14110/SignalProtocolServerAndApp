package org.thoughtcrime.securesms.isrlmods;

import android.os.Build;
import android.support.annotation.RequiresApi;

import org.json.JSONException;
import org.json.JSONObject;

public class TrustNetworkKeyRetrievalMessage extends TrustNetworkMessage {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public TrustNetworkKeyRetrievalMessage(String type, String userId, boolean queryServer) {
        super();

        String userId_hash = TrustNetwork.computeHashString(userId);

        try {
            this.headers.put("type", type);
            this.headers.put("user_hash", userId_hash);
            this.headers.put("user_id", userId);
            this.headers.put("query_server", queryServer);
        } catch (JSONException e) {

        }
    }

    public TrustNetworkKeyRetrievalMessage(TrustNetworkMessage message) {
        super();
        try {
            this.headers = new JSONObject(message.headers.toString());
        } catch (JSONException e) {

        }
    }

    public static TrustNetworkKeyRetrievalMessage asKeyRetrievalMessage (TrustNetworkMessage msg) {
        if ((msg.getMessageType().equals("KEYRETRIEVEKEYSERVER") || msg.getMessageType().equals("KEYRETRIEVEDATASET")) && msg.getHeader("user_hash") != null) {
            return new TrustNetworkKeyRetrievalMessage(msg);
        } else {
            return null;
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
