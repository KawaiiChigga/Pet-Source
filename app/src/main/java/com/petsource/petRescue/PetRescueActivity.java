package com.petsource.petRescue;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.petsource.AddPetActivity;
import com.petsource.R;
import com.petsource.SplashActivity;
import com.petsource.adapter.PetListAdapter;
import com.petsource.adapter.RescueListAdapter;
import com.petsource.model.Pet;
import com.petsource.network.API;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PetRescueActivity extends AppCompatActivity{

    private List<Pet> data;
    private RecyclerView petRV;

    public RescueListAdapter adapter;
    public static SharedPreferences shared;
    public SwipeRefreshLayout swipeRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_rescue);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        toolbar.setTitle("Pet Rescue");
        setSupportActionBar(toolbar);

        shared = getSharedPreferences("MySession", Context.MODE_PRIVATE);
        petRV = (RecyclerView) findViewById(R.id.rvpetrescue);
        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.refreshpetlist);

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
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PetRescueActivity.this, AddPetActivity.class);
                startActivity(intent);
            }
        });


    }

    public void prepareData() {
        Call<List<Pet>> p = API.Factory.getInstance().getPets(SplashActivity.mFirebaseAuth.getCurrentUser().getUid());
        p.enqueue(new Callback<List<Pet>>() {
            @Override
            public void onResponse(Call<List<Pet>> call, Response<List<Pet>> response) {
                swipeRefresh.setRefreshing(false);
                data.clear();
                for (Pet p : response.body() ) {
                    data.add(p);
                }
                adapter = new RescueListAdapter(data);
                LinearLayoutManager manager = new LinearLayoutManager(getBaseContext());
                petRV.setHasFixedSize(true);
                petRV.setLayoutManager(manager);
                petRV.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Pet>> call, Throwable t) {
                swipeRefresh.setRefreshing(false);
                Toast.makeText(PetRescueActivity.this, "Please check your network connection and internet permission", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
