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
import com.petsource.model.Info;
import com.petsource.network.API;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 2000;

    public static SharedPreferences shared;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;

    private int isStaff;
    private int isApprove;
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
                    Call<List<Info>> getType = API.Factory.getInstance().checkAccount(mFirebaseUser.getUid());
                    getType.enqueue(new Callback<List<Info>>() {
                        @Override
                        public void onResponse(Call<List<Info>> call, Response<List<Info>> response) {
                            isApprove = response.body().get(0).getIsApprove();
                            isStaff = response.body().get(0).getIsStaff();
                            Intent intent;
                            if (isApprove == 1 && isStaff == 1) {
                                intent = new Intent(SplashActivity.this, UpdateHomeActivity.class);
                            } else {
                                intent = new Intent(SplashActivity.this, HomeActivity.class);
                            }
                            startActivity(intent);
                            finish();
                        }

                        @Override
                        public void onFailure(Call<List<Info>> call, Throwable t) {

                        }
                    });
                }
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

}
