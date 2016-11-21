package com.petsource.petSalon;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.petsource.MapsActivity;
import com.petsource.R;
import com.petsource.SplashActivity;
import com.petsource.adapter.ListSalonAdapter;
import com.petsource.model.Info;
import com.petsource.model.Pet;
import com.petsource.model.Shop;
import com.petsource.model.User;
import com.petsource.network.API;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.petsource.petSalon.PetSalonActivity.REQ_MAPS;

public class ListSalonActivity extends AppCompatActivity {

    public static String idStaff;
    private List<Info> data;
    private RecyclerView petRV;

    public ListSalonAdapter adapter;
    public static SharedPreferences shared;
    public SwipeRefreshLayout swipeRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_salon);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        shared = getSharedPreferences("MySession", Context.MODE_PRIVATE);
        petRV = (RecyclerView) findViewById(R.id.rvListSalon);
        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.refreshpetlist);

        data = new ArrayList<>();
        prepareData();

        adapter = new ListSalonAdapter(data);
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

    }

    public void gotMaps(View view) {

        Intent intent = new Intent(this, MapsActivity.class);
        startActivityForResult(intent, REQ_MAPS);
    }

    public void prepareData() {
        Call<List<Shop>> p = API.Factory.getInstance().getSalon();
        p.enqueue(new Callback<List<Shop>>() {
            @Override
            public void onResponse(Call<List<Shop>> call, Response<List<Shop>> response) {
                for (Shop s : response.body()) {
                    data.clear();
                    Call<List<Info>> itemCall = API.Factory.getInstance().checkAccount(s.getIduser());
                    itemCall.enqueue(new Callback<List<Info>>() {
                        @Override
                        public void onResponse(Call<List<Info>> call, Response<List<Info>> response) {
                            for (Info i : response.body()) {
                                data.add(i);
                            }
                            adapter = new ListSalonAdapter(data);
                            LinearLayoutManager manager = new LinearLayoutManager(getBaseContext());
                            petRV.setHasFixedSize(true);
                            petRV.setLayoutManager(manager);
                            petRV.setAdapter(adapter);
                        }

                        @Override
                        public void onFailure(Call<List<Info>> call, Throwable t) {
                            Toast.makeText(ListSalonActivity.this, "Please check your network connection and internet permission", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
            @Override
            public void onFailure(Call<List<Shop>> call, Throwable t) {
                swipeRefresh.setRefreshing(false);
                Toast.makeText(ListSalonActivity.this, "Please check your network connection and internet permission", Toast.LENGTH_SHORT).show();
            }
        });
    }

    }
