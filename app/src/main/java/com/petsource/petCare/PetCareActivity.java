package com.petsource.petCare;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.petsource.R;
import com.petsource.SplashActivity;
import com.petsource.model.Pet;
import com.petsource.network.API;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PetCareActivity extends AppCompatActivity {

    public static Pet ChosePet;
    private Button btnCare;
    private TextView lblCareTitle, textView11, textView, textView2, textPetName;
    private TextView txtFrom, txtTo;

    private FirebaseAuth mFirebaseAuth;

    public static Activity petCareActivity;

    public PetCareActivity() {
        petCareActivity = this;
    }

    public static final int REQ_MAPS2 = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_care);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/FRADMCN.TTF");

        mFirebaseAuth = FirebaseAuth.getInstance();

        btnCare = (Button) findViewById(R.id.btnCare);
        btnCare.setTypeface(typeface);

        lblCareTitle = (TextView) findViewById(R.id.lblCareTitle);
        lblCareTitle.setTypeface(typeface);

        textView11 = (TextView) findViewById(R.id.textView11);
        textView11.setTypeface(typeface);

        textView = (TextView) findViewById(R.id.textView);
        textView.setTypeface(typeface);

        textView2 = (TextView) findViewById(R.id.textView2);
        textView2.setTypeface(typeface);

        textPetName = (TextView) findViewById(R.id.textPetName);
        textPetName.setTypeface(typeface);
        textPetName.setText(ChosePet.getName());

        txtFrom = (TextView) findViewById(R.id.txtFrom);
        txtTo = (TextView) findViewById(R.id.txtTo);

        txtFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                                String date = dayOfMonth+"/"+(monthOfYear+1)+"/"+year;
                                txtFrom.setText(date);
                            }
                        },
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.vibrate(false);
                dpd.show(getFragmentManager(), "Datepickerdialog");
            }
        });

        txtTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                                String date = dayOfMonth+"/"+(monthOfYear+1)+"/"+year;
                                txtTo.setText(date);
                            }
                        },
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.vibrate(false);
                dpd.show(getFragmentManager(), "Datepickerdialog");
            }
        });
    }

    public void gotoListCare(View view) {
        ListCareActivity.ChosePet = this.ChosePet;
        if (txtFrom.getText().toString().equalsIgnoreCase("")) {
            txtFrom.setError("This field can not be blank");
        }else if (txtTo.getText().toString().trim().equalsIgnoreCase("")) {
            txtTo.setError("This field can not be blank");
        }else{
            ListCareActivity.cusDateStart = txtFrom.getText().toString();
            ListCareActivity.cusDateEnd = txtTo.getText().toString();
            Intent intent = new Intent(this, ListCareActivity.class);
            startActivity(intent);
        }

    }

}


