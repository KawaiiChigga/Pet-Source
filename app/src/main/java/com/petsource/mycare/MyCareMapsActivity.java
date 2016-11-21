package com.petsource.mycare;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.petsource.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.petsource.petSalon.ListSalonActivity;
import com.petsource.petSalon.PetSalonActivity;

public class MyCareMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    GoogleMapOptions options = new GoogleMapOptions();
    public static double longitude;
    public static double latitude;

    private Button btnNext;
    private TextView lblLocation;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/FRADMCN.TTF");

        btnNext = (Button) findViewById(R.id.btnNext);
        btnNext.setTypeface(typeface);

        lblLocation = (TextView) findViewById(R.id.lblLocation);
        lblLocation.setTypeface(typeface);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        options.mapType(GoogleMap.MAP_TYPE_SATELLITE)
                .compassEnabled(true)
                .rotateGesturesEnabled(true)
                .tiltGesturesEnabled(true)
                .mapToolbarEnabled(true)
        ;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }


        mMap = googleMap;
        mMap.setMyLocationEnabled(true);
    }
    public void testToast(View view) {
        try {
            Location findme = mMap.getMyLocation();
            latitude = findme.getLatitude();
            longitude = findme.getLongitude();
            setResult(RESULT_OK);
            finish();
        } catch (Exception e) {
            Toast.makeText(MyCareMapsActivity.this, "Couldn't find your location", Toast.LENGTH_SHORT).show();
        }


    }

}
