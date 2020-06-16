package org.thoughtcrime.securesms.isrlmods;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class TrustNetworkMessage {

    protected JSONObject headers;

    public TrustNetworkMessage () {
        this.headers = new JSONObject();
    }

    public TrustNetworkMessage (String message) {
        message = message.replace("isrl_trust_network://", "");
        try {
            this.headers = new JSONObject(message);
        } catch (JSONException e) {
            Log.i("ISRL_TAG", "Failure to read JSON");
        }
    }

    public String getHeader (String key) {
        try { return this.headers.getString(key); }
        catch (JSONException e) { return null; }
    }

    public String deriveTextMessage () {
        return "isrl_trust_network://" + headers.toString();
    }

    public String getMessageType () {
        try {
            return this.headers.getString("type");
        } catch (JSONException e) {
            return null;
        }
    }
}
