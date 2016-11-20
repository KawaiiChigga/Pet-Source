package com.petsource.mysalon;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.petsource.R;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;


public class MySalonActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private TextView txtSetDate;
    private EditText txtStartTime;
    private EditText txtEndTime;
    private CheckBox cbWashing;
    private CheckBox cbNail;
    private CheckBox cbTrimming;
    private EditText txtSetPrice;

    private Button btnOpenSalon;
    private TextView lblOpenSalon;

    public SharedPreferences shared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_salon);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/FRADMCN.TTF");

        btnOpenSalon = (Button) findViewById(R.id.btnOpenSalon);
        btnOpenSalon.setTypeface(typeface);

        lblOpenSalon = (TextView) findViewById(R.id.lblOpenSalon);
        lblOpenSalon.setTypeface(typeface);

        shared = getSharedPreferences("MySession", Context.MODE_PRIVATE);

        txtStartTime = (EditText) findViewById(R.id.txtStartTime);
        txtEndTime = (EditText) findViewById(R.id.txtEndTime);
        cbWashing = (CheckBox) findViewById(R.id.cbWashing);
        cbNail = (CheckBox) findViewById(R.id.cbNail);
        cbTrimming = (CheckBox) findViewById(R.id.cbTrimming);
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
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = dayOfMonth+"/"+(monthOfYear+1)+"/"+year;
        txtSetDate.setText(date);
    }
}