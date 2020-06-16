package org.thoughtcrime.securesms.tor;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.thoughtcrime.securesms.isrlmods.TrustNetworkRecord;
import org.thoughtcrime.securesms.recipients.Recipient;
import org.thoughtcrime.securesms.sms.MessageSender;
import org.thoughtcrime.securesms.sms.OutgoingTextMessage;
import org.thoughtcrime.securesms.util.Base64;

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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import static java.lang.Boolean.FALSE;
import static org.webrtc.ContextUtils.getApplicationContext;

public class CommunicateTorService {
    public static boolean USE_TOR = true;
    private static int NUM_RECENT_FRIENDS = 3;
    private static Set<String> suspiciousIds = new HashSet<>();
    private static Map<String, TrustNetworkRecord> trustRecords = new HashMap<>();
    private static final String TAG = CommunicateTorService.class.getSimpleName();
    static Random rand = new Random();
    private static boolean keyreceived = false;
    public static String torServicesListFileName = "torServicesList.json";
    public static boolean ISSENTHOSTSERVICENAME = FALSE;


    /*
    - compare timestamps and see
    - take note of number of mismatches and matches (create separate class)

     -
     */

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static void sendTorServiceHostname(Context context, Recipient recipient) {

        SharedPreferences pref = getApplicationContext().getSharedPreferences("Tor", 0); // 0 - for private mode
        String hostname = pref.getString("hostname", null);
        Log.i("ISRL_TAG", "Sending Tor service to .." + recipient.getAddress().toPhoneString());
        TorServiceHostnameMessage msg = new TorServiceHostnameMessage("", hostname);
        //Recipient friend = Recipient.from(context, Address.fromExternal(context, recipient), false);
        OutgoingTextMessage trustMessage = new OutgoingTextMessage(recipient,  msg.deriveTextMessage(), 0, -1);
        MessageSender.send(context, trustMessage, -2, false, () -> {});
        Log.i("ISRL_TAG", "Sent Tor service");

    }



    @TargetApi(Build.VERSION_CODES.N)
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static void handleKeyHandshake (Context context, Recipient recipient, String rawMessage) {
        if (!USE_TOR) { return; }
        Log.i("ISRL_TAG", "Received Tor network message: " + rawMessage);
        TorNetworkMessage message = new TorNetworkMessage(rawMessage);

        switch (message.getMessageType()) {
            case "TORSERVICEHOSTNAME": {

                if (!ISSENTHOSTSERVICENAME) {
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            Log.i("ISRL_TAG", "sending Tor service hostname");
                            sendTorServiceHostname(context, recipient);
                        }
                    }, rand.nextInt(9000) + 3000);
                }


                TorServiceHostnameMessage msg = TorServiceHostnameMessage.asVerificationMessage(message);
                Log.i("ISRL_TAG", "Msg received"  + msg.getHostname());
                if(isFilePresent(context, torServicesListFileName)) {
                    String jsonString = read(context, torServicesListFileName);
                    JSONObject jsonObj = null;
                    try {
                        jsonObj = new JSONObject(jsonString);
                        jsonObj.put(recipient.getAddress().toString(), msg.getHostname());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.i("ISRL_TAG", "friends key file after update"  + jsonObj.toString());
                } else {
                    JSONObject jsonObj = new JSONObject();
                    try {
                        jsonObj.put(recipient.getAddress().toString(), msg.getHostname());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    create(context, torServicesListFileName, jsonObj.toString());
                }

                ISSENTHOSTSERVICENAME = FALSE;


                break;
            }

        }
    }


    public static String read(Context context, String fileName) {
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

}
