package com.blogspot.android_by_example.nwfind;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Jeffrey on 2018-01-13.
 */

public class HostPending extends AppCompatActivity implements View.OnClickListener {
    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            String message = intent.getStringExtra("PlayerLogin");

        }
    };
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hostpending);

        final String url = "https://2018nwhacks.azurewebsites.net/games";



        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("PlayerLogin"));

        TextView eventId = (TextView) findViewById(R.id.eventID);
        Button mSubmitButton = (Button) findViewById(R.id.start);

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent HostPending = new Intent(HostPending.this, Leaderboard.class);
                startActivity(HostPending);
            }
        });
    }
    public void onClick(View view) {

        Intent PlayerLogin = new Intent(this, Leaderboard.class);
        startActivity(PlayerLogin);

    }
}
