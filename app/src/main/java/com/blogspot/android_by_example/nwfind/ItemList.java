package com.blogspot.android_by_example.nwfind;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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


        }

        public void onClick(View view) {

        }


    }

