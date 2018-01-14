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

/**
 * Created by Jeffrey on 2018-01-14.
 */

public class AuthenticateUser extends AppCompatActivity {

    private int valid = 0;
    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            String message = intent.getStringExtra("Authentication");
            if(message == "OK")
            {
                valid = 1;
                Intent WaitingPlayers = new Intent(AuthenticateUser.this, WaitingForPlayers.class);
                startActivity(WaitingPlayers);
            }
            else
            {
                Intent WaitingPlayers = new Intent(AuthenticateUser.this, PlayerLogin.class);
                startActivity(WaitingPlayers);
            }

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playerlogin);



        Button mSubmitButton = (Button) findViewById(R.id.SubmitKey_button);
//        mSubmitButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //setContentView(R.layout.activity_host);
//                //Intent HostBegin = new Intent(StartActivity.this, HostBegin.class);
//                //startActivity(HostBegin);
//            }
//        });


        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("Authentication"));
    }


}
