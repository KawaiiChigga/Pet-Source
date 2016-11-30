package com.petsource.petSalon;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.petsource.R;

public class FinalSalonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_salon);
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

        txtName.setText(MapsActivity.infoStaff.getName());
        txtCity.setText(MapsActivity.infoStaff.getCity());
        txtAddress.setText(MapsActivity.infoStaff.getAddress());
        txtJob.setText(MapsActivity.infoStaff.getJob());
        txtPrice.setText(MapsActivity.staff.getPrice());


    }

    public void gotoHome(View view) {
        PetSalonActivity.petSalonActivity.finish();
        ListSalonActivity.listSalonActivity.finish();
        MapsActivity.mapsActivity.finish();
        finish();
    }


}
