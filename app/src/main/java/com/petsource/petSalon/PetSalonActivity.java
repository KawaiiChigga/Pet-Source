package com.petsource.petSalon;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.petsource.R;
import com.petsource.model.Pet;
import com.petsource.network.API;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PetSalonActivity extends AppCompatActivity {

    public static final int REQ_MAPS = 100;

    public static Activity petSalonActivity;
    List<Pet> mypet;
    Spinner staticSpinner;
    double latitude, longtitude;

    private Button btnSalon;
    private TextView lblSalonTitle, textView8;
    private CheckBox checkBox, checkBox2, checkBox3;

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;

    public PetSalonActivity() {
        petSalonActivity = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_salon);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/FRADMCN.TTF");

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        btnSalon = (Button) findViewById(R.id.btnSalon);
        btnSalon.setTypeface(typeface);

        lblSalonTitle = (TextView) findViewById(R.id.lblSalonTitle);
        lblSalonTitle.setTypeface(typeface);

        textView8 = (TextView) findViewById(R.id.textView8);
        textView8.setTypeface(typeface);

        checkBox = (CheckBox) findViewById(R.id.checkBox);
        checkBox.setTypeface(typeface);

        checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
        checkBox2.setTypeface(typeface);

        checkBox3 = (CheckBox) findViewById(R.id.checkBox3);
        checkBox3.setTypeface(typeface);

//        staticSpinner = (Spinner) findViewById(R.id.spinnerONE);
//        mypet = new ArrayList<Pet>();

//        Call<List<Pet>> getpet = API.Factory.getInstance().getPets(mFirebaseAuth.getCurrentUser().getUid());
//        getpet.enqueue(new Callback<List<Pet>>() {
//            @Override
//            public void onResponse(Call<List<Pet>> call, Response<List<Pet>> response) {
//                for (Pet p : response.body() ) {
//                    mypet.add(p);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Pet>> call, Throwable t) {
//
//            }
//        });
//
//        ArrayAdapter<Pet> petAdapter = new ArrayAdapter<Pet>(this, R.layout.spinner_layout, mypet);
//
//        petAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        staticSpinner.setAdapter(petAdapter);
//        staticSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(PetSalonActivity.this, parent.getItemAtPosition(position) + "", Toast.LENGTH_SHORT);
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
    }


    public void gotoListSalon(View view) {
        Intent intent = new Intent(this, ListSalonActivity.class);
        startActivity(intent);
    }

}
