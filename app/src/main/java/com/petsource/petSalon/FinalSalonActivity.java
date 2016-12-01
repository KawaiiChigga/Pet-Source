package com.petsource.petSalon;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.petsource.R;
import com.petsource.model.Transaction;
import com.petsource.network.API;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FinalSalonActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_salon);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView txtName, txtCity, txtAddress, txtJob, txtPrice, lblDealing;
        Button btnDeal;

        mFirebaseAuth = FirebaseAuth.getInstance();

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
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String sysdate = df.format(Calendar.getInstance().getTime());
        Call<Transaction> addTrans = API.Factory.getInstance().addTrans(
                mFirebaseAuth.getCurrentUser().getUid(),
                MapsActivity.ChosePet.getId(),
                sysdate,
                MapsActivity.staff.getId(),
                MapsActivity.staff.getPrice(),
                0,
                MapsActivity.isWashing,
                MapsActivity.isClipping,
                MapsActivity.isTrimming,
                "Pending"
        );
        addTrans.enqueue(new Callback<Transaction>() {
            @Override
            public void onResponse(Call<Transaction> call, Response<Transaction> response) {
                ChosePetSalonActivity.chosePetSalonActivity.finish();
                PetSalonActivity.petSalonActivity.finish();
                ListSalonActivity.listSalonActivity.finish();
                MapsActivity.mapsActivity.finish();
                finish();
            }

            @Override
            public void onFailure(Call<Transaction> call, Throwable t) {
                Toast.makeText(FinalSalonActivity.this, "Please check your internet connection!", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
