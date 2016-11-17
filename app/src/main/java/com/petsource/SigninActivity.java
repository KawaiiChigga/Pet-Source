package com.petsource;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.petsource.R;
import com.petsource.model.Login;
import com.petsource.model.User;
import com.petsource.network.API;
//import com.google.android.gms.appindexing.Action;
//import com.google.android.gms.appindexing.AppIndex;
//import com.google.android.gms.appindexing.Thing;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SigninActivity extends AppCompatActivity {

    private Button buttonGO;
    private TextView lblSignTitle;
    
    private EditText txtEmail;
    private EditText txtPassword;

    public SharedPreferences shared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/FRADMCN.TTF");

        buttonGO = (Button) findViewById(R.id.btnSignGo);
        buttonGO.setTypeface(typeface);

        lblSignTitle = (TextView) findViewById(R.id.lblSignTitle);
        lblSignTitle.setTypeface(typeface);
        
        txtEmail = (EditText) findViewById(R.id.txtSignEmail);
        txtPassword = (EditText) findViewById(R.id.txtSignPassword);

        shared = getSharedPreferences("MySession", Context.MODE_PRIVATE);
    }

    public void gotoHome(View view) {
        Call<Login> login = API.Factory.getInstance().logIn(txtEmail.getText().toString(), txtPassword.getText().toString());
        login.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                if (response.body() != null) {
                    Call<User> user = API.Factory.getInstance().getUser(response.body().getUid());
                    user.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            SharedPreferences.Editor editor = shared.edit();

                            editor.putString("idKEY", response.body().getId());
                            editor.putString("emailKEY", response.body().getUsername());
                            editor.putString("nameKEY", response.body().getName());
                            editor.putString("phoneKEY", response.body().getPhonenum());
                            editor.putInt("isStaffKEY", response.body().getIsStaff());
                            editor.putInt("isApproveKEY", response.body().getIsApprove());
                            editor.commit();

                            Intent intent = new Intent(SigninActivity.this, HomeActivity.class);
                            startActivity(intent);
                            LoginActivity.firstActivity.finish();
                            finish();
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {

                        }
                    });
                } else {
                    Toast.makeText(SigninActivity.this, "Please check your username or password", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                Toast.makeText(SigninActivity.this, "Please check your network connection and internet permission", Toast.LENGTH_SHORT).show();
            }
        });
        
        
    }

    public void gotoLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
