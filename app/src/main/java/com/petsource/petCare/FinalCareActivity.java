package com.petsource.petCare;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.petsource.R;
import com.petsource.model.Transaction;
import com.petsource.network.API;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import retrofit2.Call;

public class FinalCareActivity extends AppCompatActivity {
    private FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_care);
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

        txtName.setText(Maps2Activity.infoStaff.getName());
        txtCity.setText(Maps2Activity.infoStaff.getCity());
        txtAddress.setText(Maps2Activity.infoStaff.getAddress());
        txtJob.setText(Maps2Activity.infoStaff.getJob());
        txtPrice.setText(Maps2Activity.staff.getPrice());


    }

    public void gotoHome(View view) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String sysdate = df.format(Calendar.getInstance().getTime());
        Call<Transaction> addTrans = API.Factory.getInstance().addTrans(
                mFirebaseAuth.getCurrentUser().getUid(),
                Maps2Activity.ChosePet.getId(),
                sysdate,
                Maps2Activity.staff.getIduser(),
                Maps2Activity.staff.getPrice(),
                1,
                0,
                0,
                0,
                Maps2Activity.cusDateStart,
                Maps2Activity.cusDateEnd,
                "Pending"
        );
        PetCareActivity.petCareActivity.finish();
        ListCareActivity.listCareActivity.finish();
        Maps2Activity.maps2Activity.finish();
        finish();
    }


}
