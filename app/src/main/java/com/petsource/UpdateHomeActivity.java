    package com.petsource;

    import android.app.Activity;
    import android.content.Intent;
    import android.content.SharedPreferences;
    import android.graphics.Typeface;
    import android.os.Bundle;
    import android.support.design.widget.TabLayout;
    import android.support.v4.app.Fragment;
    import android.support.v4.app.FragmentManager;
    import android.support.v4.app.FragmentPagerAdapter;
    import android.support.v4.view.ViewPager;
    import android.support.v7.app.AppCompatActivity;
    import android.support.v7.widget.Toolbar;
    import android.view.View;
    import android.widget.TextView;

    import com.google.android.gms.common.api.GoogleApiClient;
    import com.petsource.homeFragments.AccountFragment;
    import com.petsource.homeFragments.HistoryFragment;
    import com.petsource.homeFragments.HomeFragment;
    import com.petsource.mycare.MyCareActivity;
    import com.petsource.mysalon.MySalonActivity;
    import com.petsource.petCare.PetCareActivity;
    import com.petsource.petRescue.PetRescueActivity;
    import com.petsource.petSalon.PetSalonActivity;
    import com.petsource.upHomeFragments.UpAccountFragment;
    import com.petsource.upHomeFragments.UpHistoryFragment;
    import com.petsource.upHomeFragments.UpHomeFragment;

    public class UpdateHomeActivity extends AppCompatActivity {

    private StaffSectionsPagerAdapter mCSectionsPagerAdapter;
    private ViewPager mViewPager;
    private TextView lblHomeTitle;

    public static Activity upHomeActivity;

    public UpdateHomeActivity() {
        upHomeActivity = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/FRADMCN.TTF");

        lblHomeTitle = (TextView) findViewById(R.id.lblHomeTitle);
        lblHomeTitle.setTypeface(typeface);

        mCSectionsPagerAdapter = new StaffSectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mCSectionsPagerAdapter);

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

        public void gotoPetRescue(View view) {
            Intent intent = new Intent(this, PetRescueActivity.class);
            startActivity(intent);
        }

        public void gotoMySalon(View view) {
            Intent intent = new Intent(this, MySalonActivity.class);
            startActivity(intent);
        }

        public void gotoMycare(View view) {
            Intent intent = new Intent(this, MyCareActivity.class);
            startActivity(intent);
        }


        public void gotoPetList(View view) {
            Intent intent = new Intent(this, PetListActivity.class);
            startActivity(intent);
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
