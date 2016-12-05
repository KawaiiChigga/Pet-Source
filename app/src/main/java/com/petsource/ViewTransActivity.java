package com.petsource;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.petsource.model.Info;
import com.petsource.model.Pet;
import com.petsource.model.Transaction;
import com.petsource.network.API;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewTransActivity extends AppCompatActivity {

    private TextView txtStaffName, txtType, txtPetName, txtTransDate, txtPrice, txtStatus, lblTransTitle;
    private Button btnCANCEL;
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

        if (tr.getStatus().equals("Pending")) {
            btnCANCEL.setText("Cancel");
            btnCANCEL.setVisibility(View.VISIBLE);
            btnCANCEL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Call<Transaction> getTrans = API.Factory.getInstance().getATrans(tr.getId());
                    getTrans.enqueue(new Callback<Transaction>() {
                        @Override
                        public void onResponse(Call<Transaction> call, Response<Transaction> response) {
                            if (response.body() != null) {
                                Call<ResponseBody> cancelTrans = API.Factory.getInstance().delTrans(response.body().getId());
                                cancelTrans.enqueue(new Callback<ResponseBody>() {
                                    @Override
                                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                        Toast.makeText(getBaseContext(), "Canceled", Toast.LENGTH_SHORT).show();
                                        finish();
                                    }

                                    @Override
                                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                                        Toast.makeText(getBaseContext(), "Failed to cancel the transaction", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }

                        @Override
                        public void onFailure(Call<Transaction> call, Throwable t) {

                        }
                    });

                }
            });
        } else {
            btnCANCEL.setVisibility(View.GONE);
        }

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
