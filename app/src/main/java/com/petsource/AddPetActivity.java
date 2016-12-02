package com.petsource;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.petsource.R;
import com.petsource.model.Pet;
import com.petsource.network.API;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPetActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private EditText txtName;
    private TextView txtBirthdate;
    private EditText txtRace;
    private RadioGroup rbtGender;
    private RadioGroup rbtSpecies;
    private Switch isCertified;

    private int isMale;
    private int isDog;
    private int certified;

    private Button btnAddPet;
    private TextView lblAddTitle;

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pet);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/FRADMCN.TTF");

        btnAddPet = (Button) findViewById(R.id.btnAddPet);
        btnAddPet.setTypeface(typeface);

        lblAddTitle = (TextView) findViewById(R.id.lblAddTitle);
        lblAddTitle.setTypeface(typeface);

        txtName = (EditText) findViewById(R.id.txtPetName);
        txtBirthdate = (TextView) findViewById(R.id.txtPetBirthdate);
        txtRace = (EditText) findViewById(R.id.txtPetRace);
        rbtGender = (RadioGroup) findViewById(R.id.radioSex);
        rbtSpecies = (RadioGroup) findViewById(R.id.radioPet);
        isCertified = (Switch) findViewById(R.id.switchCertified);


        txtBirthdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        AddPetActivity.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.vibrate(false);
                dpd.show(getFragmentManager(), "Datepickerdialog");
            }
        });
    }

    public void addpet(View view) {

        if(txtName.getText().toString().trim().equalsIgnoreCase("") ||
           txtBirthdate.getText().toString().trim().equalsIgnoreCase("") ||
            txtRace.getText().toString().trim().equalsIgnoreCase("") ||
                rbtGender.getCheckedRadioButtonId()==-1 ||
                rbtSpecies.getCheckedRadioButtonId()==-1
                ){
            Toast toast = Toast.makeText(AddPetActivity.this, "Field can not be blank!" , Toast.LENGTH_SHORT);
            TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
            v.setTextColor(Color.parseColor("#FDBD15"));
            toast.setGravity(Gravity.LEFT| Gravity.TOP, 280, 1470);
            toast.show();
        }else{
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
                    mFirebaseAuth.getCurrentUser().getUid(),
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

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = dayOfMonth+"/"+(monthOfYear+1)+"/"+year;
        txtBirthdate.setText(date);
    }
}
