package com.petsource;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.petsource.R;
import com.petsource.model.User;
import com.petsource.network.API;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private Button btnRegGo;
    private TextView lblRegTitle;
    private EditText txtEmail;
    private EditText txtName;
    private EditText txtPassword;

    SharedPreferences shared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/FRADMCN.TTF");

        btnRegGo = (Button) findViewById(R.id.btnRegGO);
        btnRegGo.setTypeface(typeface);

        lblRegTitle = (TextView) findViewById(R.id.lblRegTitle);
        lblRegTitle.setTypeface(typeface);

        txtEmail = (EditText) findViewById(R.id.txtRegEmail);
        txtName = (EditText) findViewById(R.id.txtRegName);
        txtPassword = (EditText) findViewById(R.id.txtRegPassword);

        shared = getSharedPreferences("MySession", Context.MODE_PRIVATE);
    }


    public void gotoHome(View view) {
        Call<User> register = API.Factory.getInstance().register(txtEmail.getText().toString(),
                txtPassword.getText().toString(), txtName.getText().toString(), "");
        register.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                SharedPreferences.Editor editor = shared.edit();
                editor.putString("idKEY", response.body().getId());
                editor.putString("emailKEY", response.body().getUsername());
                editor.putString("nameKEY", response.body().getName());
                editor.putString("phoneKEY", response.body().getPhonenum());
                editor.commit();

                Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                startActivity(intent);
                LoginActivity.firstActivity.finish();
                finish();

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Please check your network connection and internet permission", Toast.LENGTH_SHORT).show();

            }
        });

    }

    //    public void gotoHome(View view) {
//        Intent intent = new Intent(this, HomeActivity.class);
//        startActivity(intent);
//    }

}
