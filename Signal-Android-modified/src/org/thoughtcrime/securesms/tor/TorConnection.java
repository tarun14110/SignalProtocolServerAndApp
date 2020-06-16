package org.thoughtcrime.securesms.tor;

import android.annotation.TargetApi;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.msopentech.thali.toronionproxy.OnionProxyManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;

import socks.Socks5Proxy;
import socks.SocksSocket;

import static java.lang.Boolean.TRUE;
import static org.thoughtcrime.securesms.tor.CommunicateTorService.rand;
import static org.webrtc.ContextUtils.getApplicationContext;


public class TorConnection {

    public TorConnection(String job, String recipient) {


        Log.d("ISRL_TAG", "in Tor connection");

        if (job == "runTorService") {
            String res= "";
            System.out.println("Torrr starting");
            new TorStartTorService().execute();
        } else if (job == "accessing_key_through_keyServer" || job == "accessing_key_through_friends_tor_service")  {
            Log.d("ISRL_TAG", "in Tor connection2");

            new TorRetrieveKeys().execute(job, recipient);
        }

        /*
        final long startTime = System.currentTimeMillis();

        String res= "";
        try {
            res = new TorRetrieveKeys().execute(recipient).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        final long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime - startTime));
        //       res =  "{" + res.split("[\\{\\}]")[1] + "}";
        //       System.out.println("result is:" + res.split("[\\{\\}]")[1]);*/


    }




    private class TorRetrieveKeys extends android.os.AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... strings) {
            String fileStorageLocation = "torfiles";

            OnionProxyManager onionProxyManager =
                    new com.msopentech.thali.android.toronionproxy.AndroidOnionProxyManager(getApplicationContext(), fileStorageLocation);
            int totalSecondsPerTorStartup = 4 * 60;
            int totalTriesPerTorStartup = 5;
            String response = "";

            try {


                if(!onionProxyManager.isRunning()) {
                    boolean ok = onionProxyManager.startWithRepeat(totalSecondsPerTorStartup, totalTriesPerTorStartup);
                    if (!ok)
                        System.out.println("Couldn't start tor");

                    while (!onionProxyManager.isRunning())
                        Thread.sleep(90);
                    System.out.println("Tor initialized on port " + onionProxyManager.getIPv4LocalHostSocksPort());
                }


/*

                String wdir = getApplicationContext().getApplicationInfo().dataDir + "/app_torfiles";
                File keyServiceFile = new File(wdir, "/keyService/" + "index.html");
                //LOG.info("Creating hidden service");

                if (!keyServiceFile.getParentFile().exists() &&
                        !keyServiceFile.getParentFile().mkdirs()) {
                    throw new RuntimeException("Could not create keyService parent directory");
                }

                if (!keyServiceFile.exists() && !keyServiceFile.createNewFile()) {
                    throw new RuntimeException("Could not create keyService");
                }

                String keyIndexFile = getApplicationContext().getApplicationInfo().dataDir + "/app_torfiles/keyService";
                System.out.println("1111");
                PrintWriter writer = new PrintWriter(keyIndexFile + "/index.html", "UTF-8");
                writer.println("The first line");
                writer.println("The second line");
                writer.close();
                System.out.println("22222");
                TinyWebServer.startServer("localhost",9991,  keyIndexFile);

                System.out.println("HEYYYYY");
                String hiddenServiceHostname = onionProxyManager.publishHiddenService(80, 9991);
                System.out.print("HEYYYYYOOO");

                System.out.print("TORRROOO "+ hiddenServiceHostname);
*/


                Log.d("ISRL_TAG", "string passed" + strings[0]);

               // accessing tor service
                if (strings[0] == "accessing_key_through_friends_tor_service") {
                    int port = onionProxyManager.getIPv4LocalHostSocksPort();
                    String hiddenServiceHostname = strings[1];
                    int proxyPort = port;
                    String proxyHost = "127.0.0.1";
                    String remoteHost = hiddenServiceHostname;//"duskgytldkxiuqc6.onion";//"signal.cs.byu.edu";//"google.com";
                    int remotePort = 80;


                    Log.d("ISRL_TAG", "connecting to " + remoteHost);


                    Thread.sleep(4000);
                    System.out.println("3333333");
                    Socks5Proxy socks5Proxy = new Socks5Proxy(proxyHost, proxyPort);
                    socks5Proxy.resolveAddrLocally(false);

                    Socket socket = new SocksSocket(socks5Proxy, remoteHost, remotePort);
                    BufferedReader dIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintStream dOut = new PrintStream(socket.getOutputStream());
                    //dOut.println("GET /v1/profile/+18017629094 HTTP/1.1\r\nHost: signal.cs.byu.edu:8082\r\nAuthorization: Basic KzE4MDE2MzY0NzEwOjZkVnZjek5LVWhRVldoek9Qa2RJbDlHNA==\r\n\r\n");
                    dOut.println("GET / HTTP/1.1\r\nHost: " + hiddenServiceHostname + ":80\r\n\r\n");


                    Log.d("ISRL_TAG", "connected to Tor service");


                    dOut.println();
                    String str = null;
                    Log.e("in TORRRR", "LOLLL");
                    System.out.println("444444");
                    System.out.print("YOYO");
                    do {
                        str = dIn.readLine();
                        response = response + str;
                        System.out.println("TOTOTOOT" + str);
                    } while (str != null);

                }



                //     accessing keys through tor

                if (strings[0] == "accessing_key_through_keyServer") {
                    int port = onionProxyManager.getIPv4LocalHostSocksPort();

                    int proxyPort = port;
                    String proxyHost = "127.0.0.1";
                    String remoteHost = "signal.cs.byu.edu";//"signal.cs.byu.edu";//"google.com";
                    int remotePort = 8082;

                    Socks5Proxy socks5Proxy = new Socks5Proxy(proxyHost, proxyPort);
                    socks5Proxy.resolveAddrLocally(false);

                    Socket socket = new SocksSocket(socks5Proxy, remoteHost, remotePort);
                    BufferedReader dIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintStream dOut = new PrintStream(socket.getOutputStream());
                    dOut.println("GET /v1/profile/" + strings[0] + " HTTP/1.1\r\nHost: signal.cs.byu.edu:8082\r\nAuthorization: Basic KzEyMjIyMjIyMjIyOlp1ckJ2MWFTT1psRVdDWWJORzhDZ0dxTQ==\r\n\r\n");
                    //dOut.println("GET / HTTP/1.1\r\nHost: google.com\r\n\r\n");

                    dOut.println();
                    String str = null;
                    Log.e("in TORRRR", "LOLLL");
                    System.out.print("YOYO");
                    do {
                        str = dIn.readLine();
                        response = response + str;
                        System.out.println("TOTOTOOT" + str);
                    } while (str != null);
                }

            }
            catch (Exception e) {
                e.printStackTrace();

            }
            return response;
        }


        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onPostExecute(String result) {

        }
    }



    private class TorStartTorService extends android.os.AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... strings) {
            String fileStorageLocation = "torfiles";

            OnionProxyManager onionProxyManager =
                    new com.msopentech.thali.android.toronionproxy.AndroidOnionProxyManager(getApplicationContext(), fileStorageLocation);
            int totalSecondsPerTorStartup = 4 * 60;
            int totalTriesPerTorStartup = 5;
            String response = "";

            try {

                if(!onionProxyManager.isRunning()) {
                    boolean ok = onionProxyManager.startWithRepeat(totalSecondsPerTorStartup, totalTriesPerTorStartup);
                    if (!ok)
                        System.out.println("Couldn't start tor");

                    while (!onionProxyManager.isRunning())
                        Thread.sleep(90);
                    System.out.println("Tor initialized on port " + onionProxyManager.getIPv4LocalHostSocksPort());
                }



                String wdir = getApplicationContext().getApplicationInfo().dataDir + "/app_torfiles";
                File keyServiceFile = new File(wdir, "/keyService/" + "index.html");

                Log.d("ISRL_TAG", "creating hidden service");
                if (!keyServiceFile.getParentFile().exists() &&
                        !keyServiceFile.getParentFile().mkdirs()) {
                    throw new RuntimeException("Could not create keyService parent directory");
                }

                if (!keyServiceFile.exists() && !keyServiceFile.createNewFile()) {
                    throw new RuntimeException("Could not create keyService");
                }

                String keyIndexFile = getApplicationContext().getApplicationInfo().dataDir + "/app_torfiles/keyService";
                PrintWriter writer = new PrintWriter(keyIndexFile + "/index.html", "UTF-8");
                writer.println("The first line");
                writer.println("The second line");
                writer.close();

                TinyWebServer.startServer("localhost",9991,  keyIndexFile);
                String hiddenServiceHostname = onionProxyManager.publishHiddenService(80, 9991);
                Log.d("ISRL_TAG", "hidden service name "+ hiddenServiceHostname);




                System.out.print("TORRROOO "+ hiddenServiceHostname);

                SharedPreferences pref = getApplicationContext().getSharedPreferences("Tor", 0); // 0 - for private mode
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("hostname", hiddenServiceHostname);
                editor.commit();


/*
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        try {
                            onionProxyManager.stop();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }, 300000);*/




            }
            catch (Exception e) {
                e.printStackTrace();

            }
            return response;
        }


        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onPostExecute(String result) {

        }
    }

}
