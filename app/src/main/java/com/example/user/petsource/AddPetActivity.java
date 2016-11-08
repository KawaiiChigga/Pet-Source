package com.example.user.petsource;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.example.user.petsource.model.Pet;
import com.example.user.petsource.network.API;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPetActivity extends AppCompatActivity {
    private EditText txtName;
    private EditText txtBirthdate;
    private EditText txtRace;
    private RadioGroup rbtGender;
    private RadioGroup rbtSpecies;
    private Switch isCertified;

    private int isMale;
    private int isDog;
    private int certified;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pet);

        txtName = (EditText) findViewById(R.id.txtPetName);
        txtBirthdate = (EditText) findViewById(R.id.txtPetBirthdate);
        txtRace = (EditText) findViewById(R.id.txtPetRace);
        rbtGender = (RadioGroup) findViewById(R.id.radioSex);
        rbtSpecies = (RadioGroup) findViewById(R.id.radioPet);
        isCertified = (Switch) findViewById(R.id.switchCertified);
    }

    public void addpet(View view) {
        rbtGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioMale : isMale = 1; break;
                    case R.id.radioFemale : isMale = 0; break;
                }
            }
        });
        rbtSpecies.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioDog : isDog = 1; break;
                    case R.id.radioCat : isDog = 0; break;
                }
            }
        });
        isCertified.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    certified = 1;
                } else {
                    certified = 0;
                }
            }
        });
        Call<Pet> addPet = API.Factory.getInstance().registerPet(txtName.getText().toString(), txtBirthdate.getText().toString(),
                txtRace.getText().toString(), HomeActivity.shared.getString("idKEY", null).toString(), isMale, isDog, certified);
        addPet.enqueue(new Callback<Pet>() {
            @Override
            public void onResponse(Call<Pet> call, Response<Pet> response) {
                //startactivitfor result aja, trus buat refresh()
                finish();
            }

            @Override
            public void onFailure(Call<Pet> call, Throwable t) {
                Toast.makeText(AddPetActivity.this, "Please check your network connection and internet permission", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
