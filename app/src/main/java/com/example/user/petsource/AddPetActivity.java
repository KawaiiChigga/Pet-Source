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
    public static final String KET_PETNAME = "PETNAME";
    public static final String KET_BIRTHDATE = "BIRTHDATE";
    public static final String KET_PETRACE = "PETRACE";
    public static final String KET_IDUSER = "IDUSER";
    public static final String KET_ISMALE = "ISMALE";
    public static final String KET_ISDOG = "ISDOG";
    public static final String KET_ISCERTIFIED = "ISCERTIFIED";

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
        Intent intent = new Intent(this, AddPetActivity.class);
        intent.putExtra(KET_PETNAME, txtName.getText().toString());
        intent.putExtra(KET_BIRTHDATE, txtBirthdate.getText().toString());
        intent.putExtra(KET_PETRACE, txtRace.getText().toString());
        intent.putExtra(KET_IDUSER, HomeActivity.shared.getString("idKEY", null));
        intent.putExtra(KET_ISMALE, isMale);
        intent.putExtra(KET_ISDOG, isDog);
        intent.putExtra(KET_ISCERTIFIED, certified);
        setResult(RESULT_OK, intent);
        finish();
    }
}
