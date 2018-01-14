package com.blogspot.android_by_example.nwfind;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.microsoft.projectoxford.vision.VisionServiceClient;
import com.microsoft.projectoxford.vision.VisionServiceRestClient;
import com.microsoft.projectoxford.vision.contract.AnalysisResult;
import com.microsoft.projectoxford.vision.contract.Category;
import com.microsoft.projectoxford.vision.rest.VisionServiceException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;


/**
 * Created by jennnnyz on 2018-01-13.
 */

public class camera extends AppCompatActivity implements View.OnClickListener{

    private VisionServiceClient client;
    // The image selected to detect.
    private Bitmap mBitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        if (client==null){
            client = new VisionServiceRestClient(getString(R.string.subscription_key), getString(R.string.subscription_apiroot));
        }
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

    private AnalysisResult process() throws VisionServiceException, IOException {
        Gson gson = new Gson();
        String[] features = {"Categories"};
        String[] details = {};

        // Put the image into an input stream for detection.
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, output);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(output.toByteArray());

        AnalysisResult v = this.client.analyzeImage(inputStream, features, details);

        return v;
    }

    private String getCategory(){
        try {
            AnalysisResult v = process();
            List<Category> categories = v.categories;
            int size = categories.size();
            if(size > 0){
                Category max = categories.get(0);
                for(Category c : categories){
                    if(c.score > max.score){
                        max = c;
                    }
                }

                return max.name;
            }else{
                return "";
            }
        }catch (Exception e){

        }

        return "";
    }
}