package org.thoughtcrime.securesms.tor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.thoughtcrime.securesms.database.Address;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static org.thoughtcrime.securesms.isrlmods.TrustNetwork.getRecentFriendIds;
import static org.thoughtcrime.securesms.tor.CommunicateTorService.rand;

public class TimerReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Alarm Triggered", Toast.LENGTH_SHORT).show();


        Log.i("ISRL_TAG", "Starting Tor service after Alarm trigger");
        new TorConnection("runTorService", "");
        Log.i("ISRL_TAG", "Finished Tor service");


        new Timer().schedule(new TimerTask() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void run() {

                String[] trustedFriendIds = getRecentFriendIds(context);
                List<String> list = new ArrayList<String>(Arrays.asList(trustedFriendIds));

                Log.i("ISRL_TAG", "Sending request to Tor service ");
                String jsonString = CommunicateTorService.read(context, CommunicateTorService.torServicesListFileName);

                JSONObject jsonObj = null;
                String hostname = "";
                try {
                    jsonObj = new JSONObject(jsonString);
                    hostname = jsonObj.getString(list.get(0));
                    Log.i("ISRL_TAG", "hostname is  " + hostname);
                    new TorConnection("accessing_key_through_friends_tor_service", hostname);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, rand.nextInt(9000) + 40000);

    }
}