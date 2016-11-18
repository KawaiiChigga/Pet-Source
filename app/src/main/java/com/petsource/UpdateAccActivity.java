package com.petsource;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.petsource.R;
import com.petsource.model.User;
import com.petsource.network.API;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateAccActivity extends AppCompatActivity {
    private EditText txtCity;
    private EditText txtBirthdate;
    private EditText txtJob;
    private EditText txtStreet;
    private Button btnOK;

    private Button btnUpAccountGo;
    private TextView lblUpdateTitle;

    public static SharedPreferences shared;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_acc);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/FRADMCN.TTF");

        btnUpAccountGo = (Button) findViewById(R.id.btnUpAccountGo);
        btnUpAccountGo.setTypeface(typeface);

        lblUpdateTitle = (TextView) findViewById(R.id.lblUpdateTitle);
        lblUpdateTitle.setTypeface(typeface);

        txtCity = (EditText) findViewById(R.id.txtUpAccountCity);
        txtBirthdate = (EditText) findViewById(R.id.txtUpAccountBirthdate);
        txtJob = (EditText) findViewById(R.id.txtUpAccountJob);
        txtStreet = (EditText) findViewById(R.id.txtUpAccountStreet);

        shared = getSharedPreferences("MySession", Context.MODE_PRIVATE);

        btnOK = (Button) findViewById(R.id.btnUpAccountGo);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<User> u = API.Factory.getInstance().updateAccount(
                        shared.getString("idKEY", null),
                        1,
                        0,
                        "HARI INI",
                        txtStreet.getText().toString(),
                        txtCity.getText().toString(),
                        txtBirthdate.getText().toString(),
                        txtJob.getText().toString()
                );
                u.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        finish();
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(UpdateAccActivity.this, "Please check your network connection and internet permission", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

}
