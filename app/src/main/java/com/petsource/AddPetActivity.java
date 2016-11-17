package com.petsource;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.example.user.petsource.R;
import com.petsource.model.Pet;
import com.petsource.network.API;

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

    public SharedPreferences shared;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pet);

        shared = getSharedPreferences("MySession", Context.MODE_PRIVATE);

        txtName = (EditText) findViewById(R.id.txtPetName);
        txtBirthdate = (EditText) findViewById(R.id.txtPetBirthdate);
        txtRace = (EditText) findViewById(R.id.txtPetRace);
        rbtGender = (RadioGroup) findViewById(R.id.radioSex);
        rbtSpecies = (RadioGroup) findViewById(R.id.radioPet);
        isCertified = (Switch) findViewById(R.id.switchCertified);
    }

    public void addpet(View view) {
        switch (rbtGender.getCheckedRadioButtonId()) {
            case R.id.radioMale : isMale = 1; break;
            case R.id.radioFemale : isMale = 0; break;
        }
        switch (rbtSpecies.getCheckedRadioButtonId()) {
            case R.id.radioDog : isDog = 1; break;
            case R.id.radioCat : isDog = 0; break;
        }
        if (isCertified.isChecked()) {
            certified = 1;
        } else {
            certified = 0;
        }
        Call<Pet> add_pet = API.Factory.getInstance().registerPet(
                txtName.getText().toString(),
                txtBirthdate.getText().toString(),
                txtRace.getText().toString(),
                shared.getString("idKEY", null),
                isMale,
                isDog,
                certified
        );
        add_pet.enqueue(new Callback<Pet>() {
            @Override
            public void onResponse(Call<Pet> call, Response<Pet> response) {
                finish();
            }

            @Override
            public void onFailure(Call<Pet> call, Throwable t) {
                Toast.makeText(AddPetActivity.this, "Check your internet connection", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
