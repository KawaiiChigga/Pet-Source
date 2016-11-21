package com.petsource.petCare;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.petsource.R;
import com.petsource.HomeActivity;
import com.petsource.MapsActivity;
import com.petsource.SplashActivity;
import com.petsource.model.Pet;
import com.petsource.mysalon.MySalonActivity;
import com.petsource.network.API;
import com.petsource.petSalon.ListSalonActivity;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PetCareActivity extends AppCompatActivity {

    private Button btnCare;
    private TextView lblCareTitle;
    private TextView txtFrom, txtTo;
    private Spinner spinner;
    List<String> mypet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_care);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/FRADMCN.TTF");


        mypet = new ArrayList<String>();

        Call<List<Pet>> getpet = API.Factory.getInstance().getPets(SplashActivity.mFirebaseAuth.getCurrentUser().getUid());
        getpet.enqueue(new Callback<List<Pet>>() {
            @Override
            public void onResponse(Call<List<Pet>> call, Response<List<Pet>> response) {
                for (Pet p : response.body() ) {
                    mypet.add(p.getName());
                }
            }

            @Override
            public void onFailure(Call<List<Pet>> call, Throwable t) {

            }
        });


        btnCare = (Button) findViewById(R.id.btnCare);
        btnCare.setTypeface(typeface);

        lblCareTitle = (TextView) findViewById(R.id.lblCareTitle);
        lblCareTitle.setTypeface(typeface);


        txtFrom = (TextView) findViewById(R.id.txtFrom);
        txtTo = (TextView) findViewById(R.id.txtTo);

        txtFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                                String date = dayOfMonth+"/"+(monthOfYear+1)+"/"+year;
                                txtFrom.setText(date);
                            }
                        },
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.vibrate(false);
                dpd.show(getFragmentManager(), "Datepickerdialog");
            }
        });

        txtTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                                String date = dayOfMonth+"/"+(monthOfYear+1)+"/"+year;
                                txtTo.setText(date);
                            }
                        },
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.vibrate(false);
                dpd.show(getFragmentManager(), "Datepickerdialog");
            }
        });


        // Create an ArrayAdapter using the string array and a default spinner layout

//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//                R.array.array, android.R.layout.simple_spinner_item);

        ArrayAdapter<String> petAdapter = new ArrayAdapter<String>(getBaseContext(), R.layout.spinner_layout, mypet);


        // Specify the layout to use when the list of choices appears
        petAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spinner = (Spinner) findViewById(R.id.spinner2);
        spinner.setAdapter(petAdapter);
        spinner.setPrompt("Select pets");
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("PO123", position + " hai ");
                spinner.setOnItemSelectedListener(this);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Apply the adapter to the spinner

    }

    public void gotoListSalon(View view) {
        Intent intent = new Intent(this, ListSalonActivity.class);
        startActivity(intent);
    }

}


