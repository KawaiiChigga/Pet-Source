package com.petsource.mycare;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.petsource.R;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;

public class MyCareActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private TextView txtSetDate;
    private TextView txtStartTime;
    private TextView txtEndTime;
    private EditText txtSetPrice;

    private Button btnOpenCare;
    private TextView lblOpenCare;

    public SharedPreferences shared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_care);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/FRADMCN.TTF");

        btnOpenCare = (Button) findViewById(R.id.btnOpenCare);
        btnOpenCare.setTypeface(typeface);

        lblOpenCare = (TextView) findViewById(R.id.lblOpenCare);
        lblOpenCare.setTypeface(typeface);

        shared = getSharedPreferences("MySession", Context.MODE_PRIVATE);

        txtStartTime = (TextView) findViewById(R.id.txtStartTime);
        txtEndTime = (TextView) findViewById(R.id.txtEndTime);
        txtSetPrice = (EditText) findViewById(R.id.txtSetPrice);
        txtSetDate = (TextView) findViewById(R.id.txtSetDate);

        txtSetDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        MyCareActivity.this,
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
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = dayOfMonth+"/"+(monthOfYear+1)+"/"+year;
        txtSetDate.setText(date);
    }

}
