package com.petsource.mysalon;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.petsource.R;
import com.petsource.SplashActivity;
import com.petsource.model.Shop;
import com.petsource.network.API;
import com.petsource.petSalon.ListSalonActivity;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MySalonActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    public static final int REQ_MAPS = 100;

    private TextView txtSetDate;
    private TextView txtStartTime;
    private TextView txtEndTime;
    private EditText txtSetPrice;

    private Button btnOpenSalon;
    private TextView lblOpenSalon;

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_salon);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/FRADMCN.TTF");

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        btnOpenSalon = (Button) findViewById(R.id.btnOpenSalon);
        btnOpenSalon.setTypeface(typeface);

        lblOpenSalon = (TextView) findViewById(R.id.lblOpenSalon);
        lblOpenSalon.setTypeface(typeface);

        txtStartTime = (TextView) findViewById(R.id.txtStartTime);
        txtEndTime = (TextView) findViewById(R.id.txtEndTime);
        txtSetPrice = (EditText) findViewById(R.id.txtSetPrice);
        txtSetDate = (TextView) findViewById(R.id.txtSetDate);

        txtSetDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        MySalonActivity.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.vibrate(false);
                dpd.show(getFragmentManager(), "Datepickerdialog");
            }
        });

        txtStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                TimePickerDialog tpd = TimePickerDialog.newInstance(
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute, int second) {
                                String print = "";
                                if (hourOfDay < 10) {
                                    print += "0";
                                }
                                print += hourOfDay + ":";
                                if (minute < 10) {
                                    print += 0;
                                }
                                print += minute + "";
                                txtStartTime.setText(print);
                            }
                        },
                        now.get(Calendar.HOUR),
                        now.get(Calendar.MINUTE),
                        true
                );
                tpd.vibrate(false);
                tpd.show(getFragmentManager(), "StartTime");
            }
        });

        txtEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                TimePickerDialog tpd = TimePickerDialog.newInstance(
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute, int second) {
                                String print = "";
                                if (hourOfDay < 10) {
                                    print += "0";
                                }
                                print += hourOfDay + ":";
                                if (minute < 10) {
                                    print += 0;
                                }
                                print += minute + "";
                                txtEndTime.setText(print);
                            }
                        },
                        now.get(Calendar.HOUR),
                        now.get(Calendar.MINUTE),
                        true
                );
                tpd.vibrate(false);
                tpd.show(getFragmentManager(), "EndTime");
            }
        });

        btnOpenSalon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                finish();
                Intent intent = new Intent(MySalonActivity.this, MySalonMapsActivity.class);
//                startActivity(intent);
                startActivityForResult(intent, REQ_MAPS);

            }
        });

        Call<List<Shop>> getStaff = API.Factory.getInstance().getStaff(mFirebaseAuth.getCurrentUser().getUid(), 0);
        getStaff.enqueue(new Callback<List<Shop>>() {
            @Override
            public void onResponse(Call<List<Shop>> call, Response<List<Shop>> response) {
                if (!response.body().isEmpty()) {
                    txtStartTime.setText(response.body().get(0).getStarttime());
                    txtEndTime.setText(response.body().get(0).getEndtime());
                    txtSetPrice.setText(response.body().get(0).getPrice());
                    txtSetDate.setText(response.body().get(0).getEnddate());
                    btnOpenSalon.setText("Update Salon");
                }
            }

            @Override
            public void onFailure(Call<List<Shop>> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQ_MAPS){
            if(resultCode == RESULT_OK){
                Call<List<Shop>> getStaff = API.Factory.getInstance().getStaff(mFirebaseAuth.getCurrentUser().getUid(), 0);
                getStaff.enqueue(new Callback<List<Shop>>() {
                    @Override
                    public void onResponse(Call<List<Shop>> call, Response<List<Shop>> response) {
                        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                        String sysdate = df.format(Calendar.getInstance().getTime());
                        if (response.body().isEmpty()) {
                            Call<Shop> addshop = API.Factory.getInstance().addShop(
                                    mFirebaseAuth.getCurrentUser().getUid(),
                                    sysdate,
                                    txtStartTime.getText().toString(),
                                    txtSetDate.getText().toString(),
                                    txtEndTime.getText().toString(),
                                    MySalonMapsActivity.latitude,
                                    MySalonMapsActivity.longitude,
                                    0,
                                    txtSetPrice.getText().toString()
                            );
                            addshop.enqueue(new Callback<Shop>() {
                                @Override
                                public void onResponse(Call<Shop> call, Response<Shop> response) {
                                    Toast.makeText(MySalonActivity.this, "My Salon has been created!", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onFailure(Call<Shop> call, Throwable t) {

                                }
                            });
                        } else {
                            Call<Shop> updateshop = API.Factory.getInstance().updateShop(
                                    response.body().get(0).getId(),
                                    sysdate,
                                    txtStartTime.getText().toString(),
                                    txtSetDate.getText().toString(),
                                    txtEndTime.getText().toString(),
                                    MySalonMapsActivity.latitude,
                                    MySalonMapsActivity.longitude,
                                    txtSetPrice.getText().toString()
                            );
                            updateshop.enqueue(new Callback<Shop>() {
                                @Override
                                public void onResponse(Call<Shop> call, Response<Shop> response) {
                                    Toast.makeText(MySalonActivity.this, "My Salon has been updated!", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onFailure(Call<Shop> call, Throwable t) {

                                }
                            });
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Shop>> call, Throwable t) {

                    }
                });
                finish();

            }else if(resultCode == RESULT_CANCELED);
        }
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = dayOfMonth+"/"+(monthOfYear+1)+"/"+year;
        txtSetDate.setText(date);
    }
}
