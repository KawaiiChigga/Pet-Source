package com.example.user.petsource;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SigninActivity extends AppCompatActivity {

    Button buttonGO;
    TextView textView5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/FRADMCN.TTF");

        buttonGO = (Button) findViewById(R.id.buttonGO);
        buttonGO.setTypeface(typeface);

        textView5 = (TextView) findViewById(R.id.textView5);
        textView5.setTypeface(typeface);
    }


    public void gotoLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }



//    public void gotoHome(View view) {
//        Intent intent = new Intent(this, HomeActivity.class);
//        startActivity(intent);
//    }
}
