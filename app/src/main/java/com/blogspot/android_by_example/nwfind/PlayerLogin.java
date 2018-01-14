package com.blogspot.android_by_example.nwfind;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.util.Log;

/**
 * Created by Jeffrey on 2018-01-13.
 */

public class PlayerLogin extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playerlogin);
        final EditText UserPassKey = (EditText) findViewById(R.id.PassKeyText);


        Button mSubmitButton = (Button) findViewById(R.id.SubmitKey_button);
//        mSubmitButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //setContentView(R.layout.activity_host);
//                //Intent HostBegin = new Intent(StartActivity.this, HostBegin.class);
//                //startActivity(HostBegin);
//            }
//        });
    }

    public void onClick(View view)
    {
        switch(view.getId()) {
            case R.id.player_button:
                Intent PlayerLogin = new Intent(this, PlayerLogin.class);
                startActivity(PlayerLogin);
            case R.id.host_button:
                //Intent HostBegin = new Intent(this, HostBegin.class);
                //startActivity(HostBegin);

        }
    }
}
