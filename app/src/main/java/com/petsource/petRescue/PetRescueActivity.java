package com.petsource.petRescue;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.petsource.R;

public class PetRescueActivity extends AppCompatActivity {

    private TextView lblRescueTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_rescue);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/FRADMCN.TTF");

        lblRescueTitle = (TextView) findViewById(R.id.lblRescueTitle);
        lblRescueTitle.setTypeface(typeface);

    }

}
