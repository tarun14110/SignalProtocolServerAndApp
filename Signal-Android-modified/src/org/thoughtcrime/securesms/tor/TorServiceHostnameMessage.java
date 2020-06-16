package org.thoughtcrime.securesms.tor;

import android.os.Build;
import android.support.annotation.RequiresApi;

import org.json.JSONException;
import org.json.JSONObject;

public class TorServiceHostnameMessage extends TorNetworkMessage {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public TorServiceHostnameMessage(String userId, String hostname) {
        super();


        try {
            this.headers.put("type", "TORSERVICEHOSTNAME");
            this.headers.put("user_id", userId);
            this.headers.put("hostname", hostname);
        } catch (JSONException e) {

        }
    }

    public TorServiceHostnameMessage(TorNetworkMessage message) {
        super();
        try {
            this.headers = new JSONObject(message.headers.toString());
        } catch (JSONException e) {

        }
    }

    public static TorServiceHostnameMessage asVerificationMessage (TorNetworkMessage msg) {
        if (msg.getMessageType().equals("TORSERVICEHOSTNAME")) {
            return new TorServiceHostnameMessage(msg);
        } else {
            return null;
        }
    }

    public String getHostname() {
        try {
            return this.headers.getString("hostname");
        } catch (JSONException e) {
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
