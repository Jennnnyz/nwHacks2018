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
import android.widget.EditText;
import android.util.Log;

/**
 * Created by Jeffrey on 2018-01-13.
 */

public class PlayerLogin extends AppCompatActivity implements View.OnClickListener{
    final EditText UserPassKey = (EditText) findViewById(R.id.PassKeyText);
    //intialize broadcast receiver
    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            String message = intent.getStringExtra("PlayerLogin");

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playerlogin);



        Button mSubmitButton = (Button) findViewById(R.id.SubmitKey_button);
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setContentView(R.layout.activity_host);
                //Intent HostBegin = new Intent(StartActivity.this, HostBegin.class);
               //startActivity(HostBegin);
            }
       });


        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("PlayerLogin"));
    }

    public void onClick(View view)
    {
        String toSend = UserPassKey.getText().toString();
        sendMessage("PlayerLogin", toSend );



    }

    //this message sends a message to the service saying its from energy requesting an update
    private void sendMessage(String id, String val) {
        Intent intent = new Intent("update");
        intent.putExtra(id, val);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        System.out.println("Sent success");
    }
}
