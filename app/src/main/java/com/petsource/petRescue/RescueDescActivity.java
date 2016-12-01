package com.petsource.petRescue;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.petsource.R;
import com.petsource.model.Pet;
import com.petsource.model.Rescue;
import com.petsource.network.API;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RescueDescActivity extends AppCompatActivity {

    public static String idPet;
    public static TextView name, gender, race, iscertified, birthdate, description;
    private TextView lblRescueDescTitle;
    public static double lat,lng;
    public static Activity rescueDescActivity;
    private Rescue rescue;
    List<String> mypet;

    public RescueDescActivity() {
        rescueDescActivity=this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rescue_desc);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/FRADMCN.TTF");

        lblRescueDescTitle = (TextView) findViewById(R.id.lblRescueDescTitle);
        lblRescueDescTitle.setTypeface(typeface);

        name = (TextView) findViewById(R.id.textName);
        gender = (TextView) findViewById(R.id.textisMale);
        race = (TextView) findViewById(R.id.textRace);
        birthdate = (TextView) findViewById(R.id.textBirthdate);
        description = (TextView) findViewById(R.id.textdescription);
        iscertified = (TextView) findViewById(R.id.textisCertified);

        Call <Pet> p = API.Factory.getInstance().getPet(idPet);
                p.enqueue(new Callback<Pet>() {
                    @Override
                    public void onResponse(Call<Pet> call, Response<Pet> response) {
                        name.setText(response.body().getName());
                        MapsRescueActivity.namaPet=response.body().getName();
                        if (response.body().isMale() == 1) {
                            gender.setText("Male");
                        } else {
                            gender.setText("Female");
                        }
                        if (response.body().isCertified() == 1) {
                            iscertified.setText("Certified");
                        } else {
                            iscertified.setText("Uncertified");
                        }
                        race.setText(response.body().getRace());
                        birthdate.setText(response.body().getBirthdate());
                    }

                    @Override
                    public void onFailure(Call<Pet> call, Throwable t) {

                    }
                });
            }


    public void gotoMaps(View view) {
        Intent intent = new Intent(this, MapsAddRescueActivity.class);
        startActivity(intent);
    }

}


