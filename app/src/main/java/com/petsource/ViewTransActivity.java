package com.petsource;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.petsource.model.Info;
import com.petsource.model.Pet;
import com.petsource.model.Transaction;
import com.petsource.network.API;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewTransActivity extends AppCompatActivity {

    private TextView txtStaffName, txtType, txtPetName, txtTransDate, txtPrice, txtStatus, lblTransTitle;
    private Button btnOKE, btnCANCEL;
    private FirebaseAuth mFirebaseAuth;
    public static Transaction tr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_trans);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/FRADMCN.TTF");

        mFirebaseAuth = FirebaseAuth.getInstance();


        btnCANCEL = (Button) findViewById(R.id.btnCANCEL);
        btnCANCEL.setTypeface(typeface);

        lblTransTitle = (TextView) findViewById(R.id.lblTransTitle);
        lblTransTitle.setTypeface(typeface);

        txtStaffName = (TextView) findViewById(R.id.txtStaffName);
        txtType = (TextView) findViewById(R.id.txtType);
        txtStaffName = (TextView) findViewById(R.id.txtStaffName);
        txtPetName = (TextView) findViewById(R.id.txtPetName);
        txtTransDate = (TextView) findViewById(R.id.txtTransDate);
        txtPrice = (TextView) findViewById(R.id.txtPrice);
        txtStatus = (TextView) findViewById(R.id.txtStatus);

        prepareData();




    }

    public void prepareData(){

        txtTransDate.setText(tr.getDate());
        txtPrice.setText(tr.getPrice());
        if(tr.getType()==0){
            txtType.setText("Salon Service");
        }else{
            txtType.setText("Care Service");
        }
        txtStatus.setText(tr.getStatus());

        Call<List<Info>> i = API.Factory.getInstance().checkAccount(tr.getIdshop());
        i.enqueue(new Callback<List<Info>>() {
            @Override
            public void onResponse(Call<List<Info>> call, Response<List<Info>> response) {
                txtStaffName.setText(response.body().get(0).getName());
            }

            @Override
            public void onFailure(Call<List<Info>> call, Throwable t) {

            }
        });

        Call<Pet> p = API.Factory.getInstance().getPet(tr.getIdpet());
        p.enqueue(new Callback<Pet>() {
            @Override
            public void onResponse(Call<Pet> call, Response<Pet> response) {
                txtPetName.setText(response.body().getName());
            }

            @Override
            public void onFailure(Call<Pet> call, Throwable t) {

            }
        });
    }

}
