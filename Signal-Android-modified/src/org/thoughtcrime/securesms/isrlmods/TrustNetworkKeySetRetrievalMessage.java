package org.thoughtcrime.securesms.isrlmods;

import android.os.Build;
import android.support.annotation.RequiresApi;

import org.json.JSONException;
import org.json.JSONObject;

public class TrustNetworkKeySetRetrievalMessage extends TrustNetworkMessage {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public TrustNetworkKeySetRetrievalMessage(int lastRetrievedTimestamp) {
        super();


        try {
            this.headers.put("type", "KEYSETRETRIEVE");
            this.headers.put("last_retrieved_timestamp", lastRetrievedTimestamp);
        } catch (JSONException e) {

        }
    }


}
