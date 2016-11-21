package com.petsource.petCare;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.petsource.HomeActivity;
import com.petsource.Maps2Activity;
import com.petsource.R;

public class FinalCareActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_care);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView txtName, txtCity, txtAddress, txtJob, txtPrice, lblDealing;
        Button btnDeal;

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/FRADMCN.TTF");

        btnDeal = (Button) findViewById(R.id.btnDeal);
        btnDeal.setTypeface(typeface);

        lblDealing = (TextView) findViewById(R.id.lblDealing);
        lblDealing.setTypeface(typeface);

        txtPrice = (TextView) findViewById(R.id.txtPrice);
        txtPrice.setTypeface(typeface);

        txtName = (TextView) findViewById(R.id.txtName);
        txtCity = (TextView) findViewById(R.id.txtCity);
        txtAddress = (TextView) findViewById(R.id.txtAddress);
        txtJob = (TextView) findViewById(R.id.txtJob);
        txtPrice = (TextView) findViewById(R.id.txtPrice);

        txtName.setText(Maps2Activity.nameStaff);
        txtCity.setText(Maps2Activity.cityStaff);
        txtAddress.setText(Maps2Activity.addressStaff);
        txtJob.setText(Maps2Activity.jobStaff);
        txtPrice.setText(Maps2Activity.priceStaff);


    }

    public void gotoHome(View view) {
        PetCareActivity.petCareActivity.finish();
        ListCareActivity.listCareActivity.finish();
        Maps2Activity.maps2Activity.finish();
        finish();
    }


}