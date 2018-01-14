package com.blogspot.android_by_example.nwfind;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Jeffrey on 2018-01-13.
 */

public class Leaderboard extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        Button mEndGameButton = (Button) findViewById(R.id.endGame);

        mEndGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Leaderboard = new Intent(Leaderboard.this, StartActivity.class);
                startActivity(Leaderboard);
            }
        });
        }

    public void onClick(View view)
    {
    }
}
