package com.petsource.petCare;

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

import com.petsource.model.Shop;
import com.petsource.network.API;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListCareActivity extends AppCompatActivity {

    public static String idStaff;
    private List<Shop> data;
    private RecyclerView petRV;
    private TextView lblPetCareTitle;

    public ListCareAdapter adapter;
    public SwipeRefreshLayout swipeRefresh;
    public static Activity listCareActivity;

    public static String cusDateStart;
    public static String cusDateEnd;

    public ListCareActivity() {
        listCareActivity = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_care);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/FRADMCN.TTF");

        lblPetCareTitle = (TextView) findViewById(R.id.lblCareListTitle);
        lblPetCareTitle.setTypeface(typeface);

        petRV = (RecyclerView) findViewById(R.id.rvListCare);
        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.refreshpetlist);

        data = new ArrayList<>();
        prepareData();

        adapter = new ListCareAdapter(data);
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
        Call<List<Shop>> p = API.Factory.getInstance().getCare(1);
        p.enqueue(new Callback<List<Shop>>() {
            @Override
            public void onResponse(Call<List<Shop>> call, Response<List<Shop>> response) {
                for (Shop s : response.body()) {
                    data.clear();
                    for (Shop i : response.body()) {
                        data.add(i);
                    }
                    adapter = new ListCareAdapter(data);
                    LinearLayoutManager manager = new LinearLayoutManager(getBaseContext());
                    petRV.setHasFixedSize(true);
                    petRV.setLayoutManager(manager);
                    petRV.setAdapter(adapter);
//                    boolean match = true;
//                    String a[];
//                    int start[], end[], cstart[], cend[];
//
//                    a = s.getEnddate().split("/");
//                    end = new int[a.length];
//                    for (int i = 0; i < a.length; i++) {
//                        end[i] = Integer.parseInt(a[i]);
//                    }
//
//                    a = s.getStartdate().split("/");
//                    start = new int[a.length];
//                    for (int i = 0; i < a.length; i++) {
//                        start[i] = Integer.parseInt(a[i]);
//                    }
//
//                    a = cusDateStart.split("/");
//                    cstart = new int[a.length];
//                    for (int i = 0; i < a.length; i++) {
//                        cstart[i] = Integer.parseInt(a[i]);
//                    }
//
//                    a = cusDateEnd.split("/");
//                    cend = new int[a.length];
//                    for (int i = 0; i < a.length; i++) {
//                        cend[i] = Integer.parseInt(a[i]);
//                    }

//                    if ()

//                    if (match) {
//                        Call<List<Info>> itemCall = API.Factory.getInstance().checkAccount(s.getIduser());
//                        itemCall.enqueue(new Callback<List<Info>>() {
//                            @Override
//                            public void onResponse(Call<List<Info>> call, Response<List<Info>> response) {
//                                for (Info i : response.body()) {
//                                    data.add(i);
//                                }
//                                adapter = new ListCareAdapter(data);
//                                LinearLayoutManager manager = new LinearLayoutManager(getBaseContext());
//                                petRV.setHasFixedSize(true);
//                                petRV.setLayoutManager(manager);
//                                petRV.setAdapter(adapter);
//                            }
//
//                            @Override
//                            public void onFailure(Call<List<Info>> call, Throwable t) {
//                                Toast.makeText(ListCareActivity.this, "Please check your network connection and internet permission", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                    }
                }

            }
            @Override
            public void onFailure(Call<List<Shop>> call, Throwable t) {
                swipeRefresh.setRefreshing(false);
                Toast.makeText(ListCareActivity.this, "Please check your network connection and internet permission", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
