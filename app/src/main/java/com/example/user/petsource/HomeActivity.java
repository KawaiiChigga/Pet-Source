    package com.example.user.petsource;

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
import android.widget.Toast;

import com.example.user.petsource.homeFragments.AccountFragment;
import com.example.user.petsource.homeFragments.HistoryFragment;
import com.example.user.petsource.homeFragments.HomeFragment;
import com.example.user.petsource.upHomeFragments.UpAccountFragment;
import com.example.user.petsource.upHomeFragments.UpHistoryFragment;
import com.example.user.petsource.upHomeFragments.UpHomeFragment;

    public class HomeActivity extends AppCompatActivity {

    private CustomerSectionsPagerAdapter mSectionsPagerAdapter;
    private StaffSectionsPagerAdapter mCSectionsPagerAdapter;
    private ViewPager mViewPager;
    private TextView lblName;

    public static SharedPreferences shared;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        shared = getSharedPreferences("MySession", Context.MODE_PRIVATE);

        if (shared.getInt("isStaffKEY", 0) == 1 && shared.getInt("isApproveKEY", 0) == 1) {
            mCSectionsPagerAdapter = new StaffSectionsPagerAdapter(getSupportFragmentManager());
            mViewPager = (ViewPager) findViewById(R.id.container);
            mViewPager.setAdapter(mCSectionsPagerAdapter);
        } else {
            mSectionsPagerAdapter = new CustomerSectionsPagerAdapter(getSupportFragmentManager());
            mViewPager = (ViewPager) findViewById(R.id.container);
            mViewPager.setAdapter(mSectionsPagerAdapter);
        }

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }


        public void gotoPetSalon(View view) {
            Intent intent = new Intent(this, MapsActivity.class);
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

    public void signOut(View view) {
        SharedPreferences.Editor editor = shared.edit();
        editor.clear();
        editor.commit();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
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
