package com.petsource.petCare;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.petsource.R;
import com.petsource.adapter.ChosePetCareAdapter;
import com.petsource.model.Pet;
import com.petsource.network.API;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChosePetCareActivity extends AppCompatActivity {

    private List<Pet> data;
    private RecyclerView petRV;
    private TextView lblChosePetCareTitle;
    private TextView isEmpty;

    public static Activity chosePetCareActivity;

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;

    public ChosePetCareAdapter adapter;
    public static SharedPreferences shared;
    public SwipeRefreshLayout swipeRefresh;

    public ChosePetCareActivity() {
        chosePetCareActivity = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chose_pet_care);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/FRADMCN.TTF");

        lblChosePetCareTitle = (TextView) findViewById(R.id.lblChosePetCareTitle);
        lblChosePetCareTitle.setTypeface(typeface);

        petRV = (RecyclerView) findViewById(R.id.rvChosePetCare);
        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.refreshChosePetCare);

        isEmpty = (TextView) findViewById(R.id.lblEmpty);
        isEmpty.setVisibility(View.GONE);
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        data = new ArrayList<>();
        prepareData();

        adapter = new ChosePetCareAdapter(data);
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
        Call<List<Pet>> p = API.Factory.getInstance().getPets(mFirebaseAuth.getCurrentUser().getUid());
        p.enqueue(new Callback<List<Pet>>() {
            @Override
            public void onResponse(Call<List<Pet>> call, Response<List<Pet>> response) {
                swipeRefresh.setRefreshing(false);
                data.clear();
                for (Pet p : response.body()) {
                    data.add(p);
                }
                if (data.isEmpty()) {
                    isEmpty.setVisibility(View.VISIBLE);
                } else {
                    isEmpty.setVisibility(View.GONE);
                }
                adapter = new ChosePetCareAdapter(data);
                LinearLayoutManager manager = new LinearLayoutManager(getBaseContext());
                petRV.setHasFixedSize(true);
                petRV.setLayoutManager(manager);
                petRV.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Pet>> call, Throwable t) {
                swipeRefresh.setRefreshing(false);
                Toast.makeText(ChosePetCareActivity.this, "Please check your network connection and internet permission", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
