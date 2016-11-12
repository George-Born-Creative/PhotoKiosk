package com.kisoktmrocha.photo.photokiosk;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.kisoktmrocha.photo.photokiosk.Services.ImagesService;
import com.kisoktmrocha.photo.photokiosk.Services.TimeService;


public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new TimeService(findViewById(R.id.timeLayout));
        new ImagesService(this, findViewById(R.id.mainLayout));
    }



    @Override
    protected void onPause() {
        super.onPause();
        Log.e("onPause", "prevent this action");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("onStop", "prevent this action");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.e("onBackPressed", "prevent this action");
    }
}
