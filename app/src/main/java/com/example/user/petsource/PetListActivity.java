package com.example.user.petsource;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.user.petsource.adapter.PetListAdapter;
import com.example.user.petsource.model.Pet;
import com.example.user.petsource.network.API;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PetListActivity extends AppCompatActivity {
    private List<Pet> data;
    private RecyclerView petRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Pet List");
        setSupportActionBar(toolbar);

        petRV = (RecyclerView) findViewById(R.id.rvpetlist);
        prepareData();

        PetListAdapter adapter = new PetListAdapter(data);

        petRV.setHasFixedSize(true);
        petRV.setLayoutManager(new LinearLayoutManager(this));
        petRV.setAdapter(adapter);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PetListActivity.this, AddPetActivity.class);
                startActivity(intent);
            }
        });
    }

    public void prepareData() {
        data = new ArrayList<>();

        Call<List<Pet>> p = API.Factory.getInstance().getPets(HomeActivity.shared.getString("idKEY", null).toString());
        p.enqueue(new Callback<List<Pet>>() {
            @Override
            public void onResponse(Call<List<Pet>> call, Response<List<Pet>> response) {
                for (Pet p : response.body() ) {
                    data.add(p);
                }
            }

            @Override
            public void onFailure(Call<List<Pet>> call, Throwable t) {
                Toast.makeText(PetListActivity.this, "Please check your network connection and internet permission", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
