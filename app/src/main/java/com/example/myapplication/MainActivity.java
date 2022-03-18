package com.example.myapplication;

import java.time.*;
import java.time.Instant;
import java.time.ZonedDateTime;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static android.widget.Toast.LENGTH_LONG;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback {
    public GoogleMap map;
    LatLng latLng;
    LatLng latLng1;
    Location currentLocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    private static final int REQUEST_CODE= 101;
    public ArrayList<LatLng> points = new ArrayList<LatLng>();
    int index=0;
    float[] minumum= new float[1];
    float[] x= new float[1];
    public Instant instant;
    public String minute;
    public String hour;
   // Polyline currentPolyline;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      fusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(this);
      fetchLastLocation();

       // RequestQueue requestQueue = Volley.newRequestQueue(this);


    }


    private void fetchLastLocation() {
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
         ActivityCompat.requestPermissions(this,new String[]
                 {Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE);
         return;
         }
        Task<Location>task=fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location!= null){
                    currentLocation=location;
                    SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.google_map);
                    supportMapFragment.getMapAsync(MainActivity.this);
                    assign();


                }

                      }
        });
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        latLng=new LatLng(currentLocation.getLatitude(),currentLocation.getLongitude());
        double lng=points.get(index).latitude;
        double ltd=points.get(index).longitude;



        latLng1=new LatLng(lng,ltd);
        int height = 100;
        int width = 100;
        BitmapDrawable bitmapdraw=(BitmapDrawable)getResources().getDrawable(R.drawable.logo_pp);
        Bitmap b=bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
        MarkerOptions markerOptions=new MarkerOptions().position(latLng).title("I am here");
        MarkerOptions markerOptions1=new MarkerOptions().position(latLng1).title(Math.round(minumum[0])+" m").icon(BitmapDescriptorFactory.fromBitmap(smallMarker));



        googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));


        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));

        googleMap.addMarker(markerOptions);
        googleMap.addMarker(markerOptions1);


    }


    public void assign(){
        points.add(new LatLng(47.474856,19.098792));
        points.add(new LatLng(47.483543,19.065962));
        points.add(new LatLng(47.4859617,19.0579744));
        points.add(new LatLng(47.4848047,19.0604441));
        points.add(new LatLng(47.481920,19.068655));
        points.add(new LatLng(47.4740863,19.0604441));
        points.add(new LatLng(47.475256,19.07149));
        points.add(new LatLng(47.4671284,19.075725));
        points.add(new LatLng(47.486211,19.074642));
        points.add(new LatLng(47.4751959,19.072594));
        points.add(new LatLng(47.478773,19.087352));
        points.add(new LatLng(47.479982,19.071813));
        points.add(new LatLng(47.478868,19.089672));
        points.add(new LatLng(47.478100,19.089588));
        points.add(new LatLng(47.476693,19.091690));
        points.add(new LatLng(47.459747,19.107592));
        points.add(new LatLng(47.475556,19.092216));
        points.add(new LatLng(47.4656135,19.0796032));
        points.add(new LatLng(47.4700175,19.0840331));
        points.add(new LatLng(47.475238,19.097320));
        points.add(new LatLng(47.470756,19.084229));
        points.add(new LatLng(47.475683,19.074910));
        points.add(new LatLng(47.459022,19.102733));
        points.add(new LatLng(47.451316,19.089563));
        points.add(new LatLng(47.470487,19.110492));
        points.add(new LatLng(47.4618666,19.1162203));
        points.add(new LatLng(47.462041,19.0796032));
        points.add(new LatLng(47.4656135,19.115323));

        Location.distanceBetween(currentLocation.getLatitude(),currentLocation.getLongitude(),
                points.get(0).latitude,points.get(0).longitude,minumum);
        for(int i=1;i<28;i++){
            Location.distanceBetween(currentLocation.getLatitude(),currentLocation.getLongitude(),
                    points.get(i).latitude,points.get(i).longitude,x);
            if (minumum[0]>x[0]){
                minumum[0]=x[0];
                index=i;

            }
        }
    }

    /*public void findtime(){
        instant = Instant.now();
        ZoneId zoneId = ZoneId.of("Europe/Budapest");
        ZonedDateTime zdt = ZonedDateTime.ofInstant(instant,zoneId);
        String dayeOfWeek = zdt.getDayOfWeek().toString();
        if(Integer.valueOf(zdt.getMinute())<30){
            minute = String.valueOf(30);
            hour = String.valueOf(zdt.getHour());
        }
        else if(Integer.valueOf(zdt.getMinute())>30){
            minute = "00";
            if(Integer.valueOf(zdt.getHour())==23) {
                hour = "00";
            }
            else{
                hour = String.valueOf(Integer.valueOf(zdt.getHour())+1);}
        }
    }*/


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode){
            case REQUEST_CODE:
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    fetchLastLocation();
                }
                break;
        }
    }



}
