package com.petsource.petSalon;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.petsource.R;
import com.petsource.model.Pet;
import com.petsource.network.API;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PetSalonActivity extends AppCompatActivity {

    public static final int REQ_MAPS = 100;
    public static Pet ChosePet;

    public static Activity petSalonActivity;

    private Button btnSalon;
    private TextView lblSalonTitle, textView8, textPetName;
    private CheckBox checkBox, checkBox2, checkBox3;

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;

    public PetSalonActivity() {
        petSalonActivity = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_salon);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/FRADMCN.TTF");

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        btnSalon = (Button) findViewById(R.id.btnSalon);
        btnSalon.setTypeface(typeface);

        lblSalonTitle = (TextView) findViewById(R.id.lblSalonTitle);
        lblSalonTitle.setTypeface(typeface);

        textView8 = (TextView) findViewById(R.id.textView8);
        textView8.setTypeface(typeface);

        checkBox = (CheckBox) findViewById(R.id.checkBox);
        checkBox.setTypeface(typeface);

        checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
        checkBox2.setTypeface(typeface);

        checkBox3 = (CheckBox) findViewById(R.id.checkBox3);
        checkBox3.setTypeface(typeface);

        textPetName = (TextView) findViewById(R.id.textPetName);
        textPetName.setTypeface(typeface);
        textPetName.setText(ChosePet.getName());
    }


    public void gotoListSalon(View view) {

        if(checkBox.isChecked() || checkBox2.isChecked() || checkBox3.isChecked()){
            if (checkBox.isChecked()) {
                ListSalonActivity.isWashing = 1;
            } else {
                ListSalonActivity.isWashing = 0;
            }
            if (checkBox2.isChecked()) {
                ListSalonActivity.isClipping = 1;
            } else {
                ListSalonActivity.isClipping = 0;
            }
            if (checkBox3.isChecked()) {
                ListSalonActivity.isTrimming = 1;
            } else {
                ListSalonActivity.isTrimming = 0;
            }
            ListSalonActivity.ChosePet = this.ChosePet;
            Intent intent = new Intent(this, ListSalonActivity.class);
            startActivity(intent);

        }else{
            Toast toast = Toast.makeText(PetSalonActivity.this, "Field can not be blank!" , Toast.LENGTH_SHORT);
            TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
            v.setTextColor(Color.parseColor("#FDBD15"));
            toast.setGravity(Gravity.LEFT| Gravity.TOP, 280, 1470);
            toast.show();
        }


    }

}
