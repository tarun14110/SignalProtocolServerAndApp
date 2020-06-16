package org.thoughtcrime.securesms.tor;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class TorNetworkMessage {

    protected JSONObject headers;

    public TorNetworkMessage() {
        this.headers = new JSONObject();
    }

    public TorNetworkMessage(String message) {
        message = message.replace("isrl_tor://", "");
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
        return "isrl_tor://" + headers.toString();
    }

    public String getMessageType () {
        try {
            return this.headers.getString("type");
        } catch (JSONException e) {
            return null;
        }
    }
}
