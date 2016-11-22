package com.petsource.petRescue;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.petsource.MapsActivity;
import com.petsource.MapsRescueActivity;
import com.petsource.R;
import com.petsource.SplashActivity;
import com.petsource.model.Pet;
import com.petsource.model.Rescue;
import com.petsource.network.API;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RescueInfoActivity extends AppCompatActivity {

    public static String idRescue;
    public static TextView name, gender, race, iscertified, birthdate, description;
    public static double lat,lng;

    private Rescue rescue;
    List<String> mypet;
    public static Activity rescueInfoActivity;

    public RescueInfoActivity() {
        rescueInfoActivity=this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rescue_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/FRADMCN.TTF");
        name = (TextView) findViewById(R.id.textName);
        gender = (TextView) findViewById(R.id.textisMale);
        race = (TextView) findViewById(R.id.textRace);
        birthdate = (TextView) findViewById(R.id.textBirthdate);
        description = (TextView) findViewById(R.id.textdescription);
        iscertified = (TextView) findViewById(R.id.textisCertified);

        Call<Rescue> getRescueUser = API.Factory.getInstance().getRescueUser(idRescue);
        getRescueUser.enqueue(new Callback<Rescue>() {
            @Override
            public void onResponse(Call<Rescue> call, Response<Rescue> response) {
                rescue = response.body();
                description.setText(rescue.getDescription());
                MapsRescueActivity.lat=rescue.getLatitude();
                MapsRescueActivity.lng=rescue.getLongitude();
                Call <Pet> p = API.Factory.getInstance().getPet(rescue.getPetid());
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

            @Override
            public void onFailure(Call<Rescue> call, Throwable t) {

            }
        });

    }

    public void gotoMaps(View view) {
        Intent intent = new Intent(this, MapsRescueActivity.class);
        startActivity(intent);
    }

}


