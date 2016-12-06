package com.petsource.petSalon;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
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
import com.petsource.adapter.ChosePetSalonAdapter;
import com.petsource.model.Pet;
import com.petsource.network.API;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChosePetSalonActivity extends AppCompatActivity {

    private List<Pet> data;
    private RecyclerView petRV;
    private TextView lblChosePetSalonTitle;
    private TextView isEmpty;

    public static Activity chosePetSalonActivity;

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;

    public ChosePetSalonAdapter adapter;
    public SwipeRefreshLayout swipeRefresh;

    public ChosePetSalonActivity() {
        chosePetSalonActivity = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chose_pet_salon);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/FRADMCN.TTF");

        lblChosePetSalonTitle = (TextView) findViewById(R.id.lblChosePetSalonTitle);
        lblChosePetSalonTitle.setTypeface(typeface);

        petRV = (RecyclerView) findViewById(R.id.rvChosePetSalon);
        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.refreshChosePetSalon);

        isEmpty = (TextView) findViewById(R.id.lblEmpty);
        isEmpty.setVisibility(View.GONE);
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        data = new ArrayList<>();
        prepareData();

        adapter = new ChosePetSalonAdapter(data);
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
                adapter = new ChosePetSalonAdapter(data);
                LinearLayoutManager manager = new LinearLayoutManager(getBaseContext());
                petRV.setHasFixedSize(true);
                petRV.setLayoutManager(manager);
                petRV.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Pet>> call, Throwable t) {
                swipeRefresh.setRefreshing(false);
                Toast.makeText(ChosePetSalonActivity.this, "Please check your network connection and internet permission", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
