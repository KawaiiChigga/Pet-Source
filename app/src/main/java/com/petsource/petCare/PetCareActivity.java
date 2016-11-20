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
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.user.petsource.R;
import com.petsource.HomeActivity;
import com.petsource.MapsActivity;
import com.petsource.model.Pet;
import com.petsource.network.API;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PetCareActivity extends AppCompatActivity {

    private Button btnCare;
    private TextView lblCareTitle;
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

        Call<List<Pet>> getpet = API.Factory.getInstance().getPets(HomeActivity.shared.getString("idKEY", null));
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

    public void gotoMaps(View view) {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

}


