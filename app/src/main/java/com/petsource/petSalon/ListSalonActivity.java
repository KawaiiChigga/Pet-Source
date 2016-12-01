package com.petsource.petSalon;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.petsource.R;
import com.petsource.adapter.ListCareAdapter;
import com.petsource.adapter.ListSalonAdapter;
import com.petsource.model.Info;
import com.petsource.model.Pet;
import com.petsource.model.Shop;
import com.petsource.network.API;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListSalonActivity extends AppCompatActivity {

    public static String idStaff;
    private List<Shop> data;
    private RecyclerView petRV;
    private TextView lblPetSalonTitle;

    public ListSalonAdapter adapter;
    public SwipeRefreshLayout swipeRefresh;
    public static Activity listSalonActivity;
    public static Pet ChosePet;
    public static int isWashing;
    public static int isTrimming;
    public static int isClipping;
    public ListSalonActivity() {
        listSalonActivity = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_salon);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/FRADMCN.TTF");

        lblPetSalonTitle = (TextView) findViewById(R.id.lblSalonListTitle);
        lblPetSalonTitle.setTypeface(typeface);

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

    public void prepareData() {
        Call<List<Shop>> p = API.Factory.getInstance().getSalon(0);
        p.enqueue(new Callback<List<Shop>>() {
            @Override
            public void onResponse(Call<List<Shop>> call, Response<List<Shop>> response) {
                for (Shop s : response.body()) {
                    data.clear();
                    for (Shop i : response.body()) {
                        data.add(i);
                    }
                    adapter = new ListSalonAdapter(data);
                    LinearLayoutManager manager = new LinearLayoutManager(getBaseContext());
                    petRV.setHasFixedSize(true);
                    petRV.setLayoutManager(manager);
                    petRV.setAdapter(adapter);
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
