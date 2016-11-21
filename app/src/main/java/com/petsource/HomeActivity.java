    package com.petsource;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.Location;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.petsource.homeFragments.AccountFragment;
import com.petsource.homeFragments.HistoryFragment;
import com.petsource.homeFragments.HomeFragment;
import com.petsource.petCare.PetCareActivity;
import com.petsource.petSalon.FinalSalonActivity;
import com.petsource.petSalon.PetSalonActivity;
import com.petsource.upHomeFragments.UpAccountFragment;
import com.petsource.upHomeFragments.UpHistoryFragment;
import com.petsource.upHomeFragments.UpHomeFragment;

    public class HomeActivity extends AppCompatActivity {

    private CustomerSectionsPagerAdapter mSectionsPagerAdapter;
    private StaffSectionsPagerAdapter mCSectionsPagerAdapter;
    private ViewPager mViewPager;

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

    public void gotoPetSalon(View view) {
        Intent intent = new Intent(this, PetSalonActivity.class);
        startActivity(intent);
    }

    public void gotoPetCare(View view) {
        Intent intent = new Intent(this, PetCareActivity.class);
        startActivity(intent);
    }

//        public void gotoPetRescue(View view) {
//            Intent intent = new Intent(this, PetRescueActivity.class);
//            startActivity(intent);
//        }
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

    public class StaffSectionsPagerAdapter extends FragmentPagerAdapter {
        public StaffSectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        Fragment fragment;

        @Override
        public Fragment getItem(int position) {
            switch(position) {
                case 0 :
                    fragment = new UpHomeFragment(); break;
                case 1 :
                    fragment = new UpHistoryFragment(); break;
                case 2 :
                    fragment = new UpAccountFragment(); break;
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
