package com.petsource.petSalon;

import android.app.Activity;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.petsource.R;
import com.petsource.HomeActivity;
import com.petsource.MapsActivity;
import com.petsource.SplashActivity;
import com.petsource.model.Pet;
import com.petsource.network.API;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PetSalonActivity extends AppCompatActivity {

    public static final int REQ_MAPS = 100;

    List<Pet> mypet;
    Spinner staticSpinner;
    double latitude, longtitude;

    private Button btnSalon;
    private TextView lblSalonTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_salon);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/FRADMCN.TTF");

        btnSalon = (Button) findViewById(R.id.btnSalon);
        btnSalon.setTypeface(typeface);

        lblSalonTitle = (TextView) findViewById(R.id.lblSalonTitle);
        lblSalonTitle.setTypeface(typeface);

        staticSpinner = (Spinner) findViewById(R.id.spinnerONE);

        mypet = new ArrayList<Pet>();

        Call<List<Pet>> getpet = API.Factory.getInstance().getPets(SplashActivity.mFirebaseAuth.getCurrentUser().getUid());
        getpet.enqueue(new Callback<List<Pet>>() {
            @Override
            public void onResponse(Call<List<Pet>> call, Response<List<Pet>> response) {
                for (Pet p : response.body() ) {
                    Log.d("DATAS", p.getName());
                    mypet.add(p);
                }
            }

            @Override
            public void onFailure(Call<List<Pet>> call, Throwable t) {

            }
        });


        // Create an ArrayAdapter using the string array and a default spinner
//        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
//                .createFromResource(this, R.array.spinner,
//                R.layout.spinner_layout);

        ArrayAdapter<Pet> petAdapter = new ArrayAdapter<Pet>(this, R.layout.spinner_layout, mypet);

        // Specify the layout to use when the list of choices appears
        petAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        staticSpinner.setAdapter(petAdapter);
        staticSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(PetSalonActivity.this, parent.getItemAtPosition(position) + "", Toast.LENGTH_SHORT);
                Log.d("FAK", position +"");

                }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d("FAK", "kosong");
            }
        });
    }


    public void gotoListSalon(View view) {

        Intent intent = new Intent(this, ListSalonActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQ_MAPS){
            if(resultCode == RESULT_OK){
                latitude = data.getDoubleExtra("LA", 0);
                longtitude = data.getDoubleExtra("LO", 0);

                Intent intent2 = new Intent(this, ListSalonActivity.class);
                startActivity(intent2);
                finish();

            }else if(resultCode == RESULT_CANCELED);
        }
    }
}
