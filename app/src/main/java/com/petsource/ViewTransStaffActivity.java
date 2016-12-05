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
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.petsource.model.Info;
import com.petsource.model.Pet;
import com.petsource.model.Transaction;
import com.petsource.network.API;
import com.petsource.upHomeFragments.UpHistoryFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewTransStaffActivity extends AppCompatActivity {

    private TextView txtCustName, txtType, txtPetName, txtTransDate, txtPrice, txtStatus, lblTransTitle;
    private Button btnACC, btnDECLINE;
    private FirebaseAuth mFirebaseAuth;
    public static Transaction tr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_trans_staff);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/FRADMCN.TTF");

        mFirebaseAuth = FirebaseAuth.getInstance();


        btnACC = (Button) findViewById(R.id.btnACC);
        btnACC.setTypeface(typeface);

        btnDECLINE = (Button) findViewById(R.id.btnDECLINE);
        btnDECLINE.setTypeface(typeface);

        lblTransTitle = (TextView) findViewById(R.id.lblTransStaffTitle);
        lblTransTitle.setTypeface(typeface);

        txtCustName = (TextView) findViewById(R.id.txtCustName);
        txtType = (TextView) findViewById(R.id.txtType);
        txtPetName = (TextView) findViewById(R.id.txtPetName);
        txtTransDate = (TextView) findViewById(R.id.txtTransDate);
        txtPrice = (TextView) findViewById(R.id.txtPrice);
        txtStatus = (TextView) findViewById(R.id.txtStatus);

        prepareData();

    }

    public void prepareData() {
        txtTransDate.setText(tr.getDate());
        txtPrice.setText(tr.getPrice());
        if(tr.getType()==0){
            txtType.setText("Salon Service");
        }else{
            txtType.setText("Care Service");
        }
        txtStatus.setText(tr.getStatus());

        if (tr.getStatus().equals("Pending")) {
            btnACC.setVisibility(View.VISIBLE);
            btnACC.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Call<Transaction> getTrans = API.Factory.getInstance().getATrans(tr.getId());
                    getTrans.enqueue(new Callback<Transaction>() {
                        @Override
                        public void onResponse(Call<Transaction> call, Response<Transaction> response) {
                            if (response.body() != null) {
                                Call<Transaction> up = API.Factory.getInstance().updateTrans(tr.getId(), "Accepted");
                                up.enqueue(new Callback<Transaction>() {
                                    @Override
                                    public void onResponse(Call<Transaction> call, Response<Transaction> response) {
                                        finish();
                                        Toast.makeText(getBaseContext(), "This transaction has been accepted", Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onFailure(Call<Transaction> call, Throwable t) {

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
            btnDECLINE.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Call<Transaction> getTrans = API.Factory.getInstance().getATrans(tr.getId());
                    getTrans.enqueue(new Callback<Transaction>() {
                        @Override
                        public void onResponse(Call<Transaction> call, Response<Transaction> response) {
                            if (response.body() != null) {
                                Call<Transaction> up = API.Factory.getInstance().updateTrans(tr.getId(), "Declined");
                                up.enqueue(new Callback<Transaction>() {
                                    @Override
                                    public void onResponse(Call<Transaction> call, Response<Transaction> response) {
                                        finish();
                                        Toast.makeText(getBaseContext(), "This transaction has been declined", Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onFailure(Call<Transaction> call, Throwable t) {

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
            btnACC.setVisibility(View.GONE);
            btnDECLINE.setVisibility(View.GONE);
        }

        Call<List<Info>> i = API.Factory.getInstance().checkAccount(tr.getIduser());
        i.enqueue(new Callback<List<Info>>() {
            @Override
            public void onResponse(Call<List<Info>> call, Response<List<Info>> response) {
                txtCustName.setText(response.body().get(0).getName());
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
