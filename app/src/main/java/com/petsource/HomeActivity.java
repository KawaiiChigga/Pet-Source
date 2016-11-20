    package com.petsource;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.petsource.homeFragments.AccountFragment;
import com.petsource.homeFragments.HistoryFragment;
import com.petsource.homeFragments.HomeFragment;
import com.petsource.model.Info;
import com.petsource.model.User;
import com.petsource.network.API;
import com.petsource.upHomeFragments.UpAccountFragment;
import com.petsource.upHomeFragments.UpHistoryFragment;
import com.petsource.upHomeFragments.UpHomeFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

    public class HomeActivity extends AppCompatActivity {

    private CustomerSectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private TextView lblUpdate;
    private GoogleApiClient mGoogleApiClient;

    public static Activity homeActivity;
    public HomeActivity() {
        homeActivity = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSectionsPagerAdapter = new CustomerSectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }


    public void gotoPetList(View view) {
        Intent intent = new Intent(this, PetListActivity.class);
        startActivity(intent);
    }

    public class CustomerSectionsPagerAdapter extends FragmentPagerAdapter {

        public CustomerSectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        Fragment fragment;
        @Override
        public Fragment getItem(int position) {
            switch(position) {
                case 0 :
                    fragment = new HomeFragment(); break;
                case 1 :
                    fragment = new HistoryFragment(); break;
                case 2 :
                    fragment = new AccountFragment(); break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "HOME";
                case 1:
                    return "HISTORY";
                case 2:
                    return "MY PROFILE";
            }
            return null;
        }
    }
}
