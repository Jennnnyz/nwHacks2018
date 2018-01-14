package com.blogspot.android_by_example.nwfind;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONObject;

import static android.R.id.message;

/**
 * Created by Jeffrey on 2018-01-14.
 */

public class ItemList extends AppCompatActivity implements View.OnClickListener {

    private String msg;
    //set up broadcast receiver, get updates on the temperature and humidity values first
    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.hasExtra("ItemList")) {
                msg = intent.getStringExtra("ItemList");
            } else {
                if (intent.hasExtra("ItemDone")) {
                    msg = intent.getStringExtra("ItemDone");
                }


                int valid = 0;
                try {
                    JSONObject obj = new JSONObject(msg);


                } catch (Exception e1) {
                }

                Log.d("receiver", "Got message: " + message);
                System.out.println("WORKS - WEATHER");
            }
        }


    };
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_itemlist);

            LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                    new IntentFilter("ItemList"));
            LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                    new IntentFilter("ItemDone"));

            Button mCameraButton = (Button) findViewById(R.id.Camera);
            mCameraButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //setContentView(R.layout.activity_host);
                    Intent HostBegin = new Intent(ItemList.this, UseCamera.class);
                    startActivity(HostBegin);
                }
            });

            Button mLeaderButton = (Button) findViewById(R.id.Leaderboard);
            mLeaderButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setContentView(R.layout.activity_leaderboard);
                    Intent HostBegin = new Intent(ItemList.this, Leaderboard.class);
                    startActivity(HostBegin);
                }
            });



        }

        public void onClick(View view) {

            switch(view.getId()) {
                case R.id.player_button:
                    Intent PlayerLogin = new Intent(ItemList.this, UseCamera.class);
                    startActivity(PlayerLogin);

                case R.id.host_button:
                Intent HostBegin = new Intent(this, Leaderboard.class);
                startActivity(HostBegin);
            }
        }


    }

