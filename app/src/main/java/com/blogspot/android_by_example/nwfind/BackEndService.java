package com.blogspot.android_by_example.nwfind;

/**
 * Created by Jeffrey on 2018-01-13.
 */

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;


import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import android.util.JsonReader;

import org.json.JSONObject;

/**
 * Created by Jeffrey on 2018-01-13.
 */

public class BackEndService extends Service {

    private Handler handler;
    private int msgtype = 0;
    public static Runnable runnable = null;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    //Creates the service
    @Override
    public void onCreate() {


    }

    BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            System.out.println("Received Message");
            //condition variable which decides on the type of intent to send back based on the message received from the activity
            try {
                String message = "alarm-1 notify 0";
                if (intent.hasExtra("HostBegin")) {
                    message = intent.getStringExtra("HostBegin");
                    msgtype = 3;
                } else {
                    if (intent.hasExtra("HostPending")) {
                        message = intent.getStringExtra("HostPending");
                        msgtype = 0;
                    } else {
                        if (intent.hasExtra("Leaderboard")) {
                            message = intent.getStringExtra("Leaderboard");
                            msgtype = 2;
                        } else {
                            if (intent.hasExtra("PlayerLogin")) {
                                message = intent.getStringExtra("PlayerLogin");
                                msgtype = 4;
                            } else {
                                if (intent.hasExtra("WaitingForPlayers")) {
                                    message = intent.getStringExtra("WaitingForPlayers");
                                    msgtype = 5;
                                } else {
                                    if (intent.hasExtra("update")) {
                                        message = intent.getStringExtra("update");
                                        msgtype = 0;
                                    }
                                }
                            }
                        }
                    }
                }
                System.out.println("Section" + message);
                new LongOperation().execute();

            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    };


    //Destroys service
    @Override
    public void onDestroy() {
        handler.removeCallbacks(runnable);
        Toast.makeText(this, "Service stopped", Toast.LENGTH_LONG).show();
    }
    //Executed on begining of service, gets the user login id for login
    @Override
    public void onStart(Intent intent, int startid) {
        Toast.makeText(this, "Service started by user.", Toast.LENGTH_LONG).show();

    }

    private class LongOperation extends AsyncTask<String, Void, String> {


        private static final String USER_AGENT = "Mozilla/5.0";

        private static final String GET_URL = "https://2018nwhacks.azurewebsites.net/games/0/users";

        private static final String POST_URL = "https://2018nwhacks.azurewebsites.net/games";

        private static final String POST_PARAMS = "userName=";

        @Override
        protected String doInBackground(String... params) {
            String msg = "";
            try {

                switch (msgtype)
                {
                    case 0:
                        break;
                    case 1:
                        msg = sendGET(msgtype);
                        break;
                    case 2:
                        msg = sendGET(msgtype);
                        break;
                    case 3:
                        msg = sendGET(msgtype);
                        break;
                    case 4:
                        msg = sendPOST(msgtype);
                        break;
                    case 5:
                        msg = sendPOST(msgtype);
                        break;
                    case 6:
                        msg = sendPOST(msgtype);
                        break;
                    default:
                        break;
                }


            } catch (Exception e2) {
                e2.printStackTrace();
            }
            return "Executed";
        }

        private String sendGET(int msg) throws IOException {
            URL obj = new URL(GET_URL);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", USER_AGENT);
            int responseCode = con.getResponseCode();
            System.out.println("GET Response Code :: " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // print result
                return response.toString();
            } else {
                return "GET request not worked";
            }

        }

        private String sendPOST(int msg) throws IOException {
            URL obj = new URL(POST_URL);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", USER_AGENT);

            // For POST only - START
            con.setDoOutput(true);
            OutputStream os = con.getOutputStream();
            os.write(POST_PARAMS.getBytes());
            os.flush();
            os.close();
            // For POST only - END

            int responseCode = con.getResponseCode();
            System.out.println("POST Response Code :: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) { //success
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // print result
                return response.toString();
            } else {
                return "POST request not worked";
            }
        }

    }

    //sends message to weather
    private void sendMessageListUsers(String id, String val) {
        Log.d("sender", "Broadcasting message");
        Intent intent = new Intent("HostPending");
        intent.putExtra(id, val);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }
    //sends message to weather temperature
    private void sendMessageItemList(String id, String val) {
        Log.d("sender", "Broadcasting message");
        Intent intent = new Intent("ItemList");
        intent.putExtra(id, val);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }
    //sends message to weather humidity
    private void sendMessageHumidListFound(String id, String val) {
        Log.d("sender", "Broadcasting message");
        Intent intent = new Intent("ItemDone");
        intent.putExtra(id, val);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }
}
