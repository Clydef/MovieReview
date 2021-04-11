package com.unasat.sr.moviereview;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Service extends AppCompatActivity implements View.OnClickListener {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm");
    String currentDateandTime = sdf.format(new Date());

    Handler handler = new Handler();
    Runnable runnable;
    int delay = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void onClick(View v) {

    }


    public void startToast() {
        handler.postDelayed(runnable = new Runnable() {
            public void run() {
                handler.postDelayed(runnable, delay);
                Toast toast = Toast.makeText(Service.this, currentDateandTime, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP | Gravity.CENTER, 0, 150);
                toast.show();
            }
        }, delay);
    }


    public void endToast() {
        handler.removeCallbacks(runnable);
    }
}
