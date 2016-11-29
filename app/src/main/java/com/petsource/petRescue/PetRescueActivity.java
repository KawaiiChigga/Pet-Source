package com.petsource.petRescue;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.petsource.R;
import com.petsource.adapter.RescueListAdapter;
import com.petsource.model.Pet;
import com.petsource.model.Rescue;
import com.petsource.network.API;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PetRescueActivity extends AppCompatActivity {

    public static String idStaff;
    public static String rescueName;
    public static String rescueRace;
    public static String rescuePetID;
    public static String rescueUserID;
    public static int rescueCertified;
    public static int rescueGender;
    public static int rescueIsDog;
    public static String rescueYear;
    public static String rescueDecript;
    public static double rescueLatitude;
    public static double rescueLongtitude;

    private List<Pet> data;
    private RecyclerView petRV;
    private TextView lblPetRescueTitle;

    public RescueListAdapter adapter;
    public SwipeRefreshLayout swipeRefresh;
    public static Activity petRescueActivity;

    public PetRescueActivity() {
        petRescueActivity=this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_rescue);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/FRADMCN.TTF");

        lblPetRescueTitle = (TextView) findViewById(R.id.lblAddRescueTitle);
        lblPetRescueTitle.setTypeface(typeface);

        petRV = (RecyclerView) findViewById(R.id.rvpetrescue);
        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.refreshpetrescue);

        data = new ArrayList<>();
        prepareData();

        adapter = new RescueListAdapter(data);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        petRV.setHasFixedSize(true);
        petRV.setLayoutManager(manager);
        petRV.setAdapter(adapter);

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                prepareData();
            }
        });
        swipeRefresh.setRefreshing(true);
        FloatingActionButton fabulous = (FloatingActionButton) findViewById(R.id.fabulous);
        fabulous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("FAK", "shit");
                Intent intent = new Intent(PetRescueActivity.this, AddRescueActivity.class);
                startActivity(intent);
            }
        });

    }


    public void prepareData() {
        Call<List<Rescue>> p = API.Factory.getInstance().getRescue();
        p.enqueue(new Callback<List<Rescue>>() {
            @Override
            public void onResponse(Call<List<Rescue>> call, Response<List<Rescue>> response) {
                data.clear();
                for (Rescue s : response.body()) {
                    Call<Pet> itemCall = API.Factory.getInstance().getPet(s.getPetid());
                    itemCall.enqueue(new Callback<Pet>() {
                        @Override
                        public void onResponse(Call<Pet> call, Response<Pet> response) {
                            data.add(response.body());

                            adapter = new RescueListAdapter(data);
                            LinearLayoutManager manager = new LinearLayoutManager(getBaseContext());
                            petRV.setHasFixedSize(true);
                            petRV.setLayoutManager(manager);
                            petRV.setAdapter(adapter);
                        }

                        @Override
                        public void onFailure(Call<Pet> call, Throwable t) {
                            Toast.makeText(PetRescueActivity.this, "Please check your network connection and internet permission", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
            @Override
            public void onFailure(Call<List<Rescue>> call, Throwable t) {
                swipeRefresh.setRefreshing(false);
                Toast.makeText(PetRescueActivity.this, "Please check your network connection and internet permission", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
