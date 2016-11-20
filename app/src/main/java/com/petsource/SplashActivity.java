package com.petsource;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.petsource.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 2000;

    public static SharedPreferences shared;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        shared = getSharedPreferences("MySession", Context.MODE_PRIVATE);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                if (mFirebaseUser == null) {
                    // Not signed in, launch the Sign In activity
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    finish();
                } else {
                    startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                    finish();
                }
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

}
