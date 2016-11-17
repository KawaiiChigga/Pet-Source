package com.petsource;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.user.petsource.R;

public class SplashActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 2000;

    public static SharedPreferences shared;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        shared = getSharedPreferences("MySession", Context.MODE_PRIVATE);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent start;
                String s = shared.getAll().toString();
                if (s.contains("idKEY")) {
                    start = new Intent(SplashActivity.this, HomeActivity.class);
                } else {
                    start = new Intent(SplashActivity.this, LoginActivity.class);
                }
                startActivity(start);
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

}