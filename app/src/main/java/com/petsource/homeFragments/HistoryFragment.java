package com.petsource.homeFragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.petsource.R;
import com.petsource.adapter.TransListAdapter;
import com.petsource.model.Transaction;
import com.petsource.network.API;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryFragment extends Fragment{
    RecyclerView rvhistory;

    List<Transaction> transdata;
    TransListAdapter adapter;

    FirebaseAuth mFirebaseAuth;

    SwipeRefreshLayout swipeRefresh;
    TextView myTrans;

    public HistoryFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvhistory = (RecyclerView) getActivity().findViewById(R.id.rvcustomerhistory);
        swipeRefresh = (SwipeRefreshLayout) getActivity().findViewById(R.id.refreshHistory);
        myTrans = (TextView) getActivity().findViewById(R.id.lblMyHistory);

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                prepareData();
            }
        });
        swipeRefresh.setRefreshing(true);

        mFirebaseAuth = FirebaseAuth.getInstance();

        transdata = new ArrayList<>();
        prepareData();

        adapter = new TransListAdapter(transdata);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        rvhistory.setHasFixedSize(true);
        rvhistory.setLayoutManager(manager);
        rvhistory.setAdapter(adapter);

    }

    public void prepareData() {
        Call<List<Transaction>> gettransdb = API.Factory.getInstance().getTransUser(mFirebaseAuth.getCurrentUser().getUid());
        gettransdb.enqueue(new Callback<List<Transaction>>() {
            @Override
            public void onResponse(Call<List<Transaction>> call, Response<List<Transaction>> response) {
                swipeRefresh.setRefreshing(false);
                transdata.clear();
                if (!response.body().isEmpty()) {
                    myTrans.setVisibility(View.VISIBLE);
                } else {
                    myTrans.setVisibility(View.GONE);
                }
                for (Transaction t : response.body()) {
                    transdata.add(t);
                }

                adapter = new TransListAdapter(transdata);
                LinearLayoutManager manager = new LinearLayoutManager(getContext());
                rvhistory.setHasFixedSize(true);
                rvhistory.setLayoutManager(manager);
                rvhistory.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Transaction>> call, Throwable t) {
                swipeRefresh.setRefreshing(false);
                Toast.makeText(getContext(), "Please check your network connection and internet permission", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }
}
