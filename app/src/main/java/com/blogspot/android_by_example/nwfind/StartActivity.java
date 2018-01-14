package com.blogspot.android_by_example.nwfind;

import android.app.LoaderManager;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.util.Log;

/**
 * Created by Jeffrey on 2018-01-13.
 */

public class StartActivity extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


        Button mHostButton = (Button) findViewById(R.id.host_button);
        Button mPlayerButton = (Button) findViewById(R.id.player_button);

        mHostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent HostBegin = new Intent(StartActivity.this, HostBegin.class);
                startActivity(HostBegin);
            }
        });
        mPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent PlayerLogin = new Intent(StartActivity.this, PlayerLogin.class);
                startActivity(PlayerLogin);
            }
        });
    }

    public void onClick(View view)
    {
        switch(view.getId()) {
            case R.id.player_button:
                Intent PlayerLogin = new Intent(StartActivity.this, PlayerLogin.class);
                startActivity(PlayerLogin);

            case R.id.host_button:
//                Intent HostBegin = new Intent(this, HostBegin.class);
//                startActivity(HostBegin);
        }

    }
}
