package com.blogspot.android_by_example.nwfind;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

/**
 * Created by Jeffrey on 2018-01-13.
 */

public class PlayerStart extends AppCompatActivity implements View.OnClickListener{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
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
