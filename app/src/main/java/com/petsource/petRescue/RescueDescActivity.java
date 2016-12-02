package com.petsource.petRescue;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.petsource.R;
import com.petsource.model.Pet;
import com.petsource.model.Rescue;
import com.petsource.network.API;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RescueDescActivity extends AppCompatActivity {

    public static Pet ChosePet;
    private TextView lblRescueDescTitle;
    private EditText txtDescription;
    public static Activity rescueDescActivity;

    public RescueDescActivity() {
        rescueDescActivity=this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rescue_desc);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/FRADMCN.TTF");

        lblRescueDescTitle = (TextView) findViewById(R.id.lblRescueDescTitle);
        lblRescueDescTitle.setTypeface(typeface);

        txtDescription = (EditText) findViewById(R.id.txtDescriptionRescue);

    }


    public void gotoMaps(View view) {
        MapsAddRescueActivity.ChosePet = this.ChosePet;
        MapsAddRescueActivity.desc = txtDescription.getText().toString();
        Intent intent = new Intent(this, MapsAddRescueActivity.class);
        startActivity(intent);
    }

}


