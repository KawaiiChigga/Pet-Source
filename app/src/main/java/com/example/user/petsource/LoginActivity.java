package com.example.user.petsource;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class LoginActivity extends AppCompatActivity {

    public static Activity firstActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        firstActivity = this;
    }

    public void gotoSignin(View view) {
        Intent intent = new Intent(this, SigninActivity.class);
        startActivity(intent);
    }

    public void gotoRegister(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}
