package com.petsource.petCare;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.petsource.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.petsource.model.Info;
import com.petsource.model.Pet;
import com.petsource.model.Shop;

public class Maps2Activity extends FragmentActivity implements OnMapReadyCallback {
    public static Pet ChosePet;
    private GoogleMap mMap;
//    public static String nameStaff;
//    public static double latitudeStaff;
//    public static double longtitudeStaff;
//    public static String addressStaff;
//    public static String cityStaff;
//    public static String jobStaff;
//    public static String priceStaff;
    public static Activity maps2Activity;

    public static Shop staff;
    public static Info infoStaff;
    public static String cusDateStart;
    public static String cusDateEnd;

    public Maps2Activity() {
        maps2Activity = this;
    }

    GoogleMapOptions options = new GoogleMapOptions();

    private Button btnNext;
    private TextView lblLocation;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/FRADMCN.TTF");

        btnNext = (Button) findViewById(R.id.btnNext);
        btnNext.setTypeface(typeface);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Location findme = mMap.getMyLocation();
                    double latitude = findme.getLatitude();
                    double longitude = findme.getLongitude();
                    LatLng latLng = new LatLng(latitude, longitude);
                    Intent intent = new Intent(Maps2Activity.this, FinalCareActivity.class);
                    startActivity(intent);
                }
                catch(Exception e){
                    Toast.makeText(Maps2Activity.this, "Please get your location first.", Toast.LENGTH_SHORT).show();
                }

            }
        });
        lblLocation = (TextView) findViewById(R.id.lblLocation);
        lblLocation.setTypeface(typeface);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        options.mapType(GoogleMap.MAP_TYPE_SATELLITE)
                .compassEnabled(true)
                .rotateGesturesEnabled(true)
                .tiltGesturesEnabled(true)
                .mapToolbarEnabled(true)
        ;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }


        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        mMap.setMyLocationEnabled(true);
        LatLng ithb = new LatLng(staff.getLatitude(), staff.getLongitude());
        //   mMap.addMarker(new MarkerOptions().position(sydney).title("Yay"));
        mMap.addMarker(new MarkerOptions().position(ithb).title(infoStaff.getName()));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ithb, 16.0f));


    }

}
