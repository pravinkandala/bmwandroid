package com.pk.bmwandroid.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.pk.bmwandroid.R;
import com.pk.bmwandroid.model.Location;

import org.joda.time.DateTime;

import static com.pk.bmwandroid.R.id.address;
import static com.pk.bmwandroid.R.id.latitude;
import static com.pk.bmwandroid.R.id.longitude;
import static com.pk.bmwandroid.util.DateUtil.getDuration;

public class LocationDescriptionActivity extends AppCompatActivity {

    GoogleMap map;
    TextView mNameTV, mAddressTV, mArrivalTimeTV, mLatitudeTV, mLongitudeTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_description);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mNameTV = (TextView) findViewById(R.id.name);
        mArrivalTimeTV = (TextView) findViewById(R.id.arrival_time);
        mLatitudeTV = (TextView) findViewById(latitude);
        mLongitudeTV = (TextView) findViewById(longitude);
        mAddressTV = (TextView) findViewById(address);

        Intent intent = this.getIntent();
        final Location location = (Location) intent.getSerializableExtra("location");
        getSupportActionBar().setTitle(location.getName());

        // TODO:  get from location
        String id = location.getId();
        String name = location.getName();
        String latitude = location.getLatitude();
        String longitude = location.getLongitude();
        String address = location.getAddress();
        String arrival_time = location.getArrivalTime();

        mNameTV.setText("Name: "+name);

        DateTime arrival_time_in_string = new DateTime(arrival_time);
        String time = getDuration(arrival_time_in_string);

        mArrivalTimeTV.setText("Arrival Time: "+time);
        mLatitudeTV.setText("Lat:"+latitude);
        mLongitudeTV.setText("Lng:"+longitude);
        mAddressTV.setText("Address: "+address);


        try{


            //map settings
            map = ((MapFragment)getFragmentManager().findFragmentById(R.id.mapFragment)).getMap();
            MarkerOptions k = new MarkerOptions()
                    .position(new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude)))
                    .title(name);
            map.addMarker(k);
            map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            map.getUiSettings().setZoomControlsEnabled(false);
            map.getUiSettings().setAllGesturesEnabled(false);
            map.getUiSettings().setCompassEnabled(true);
            map.getUiSettings().setMyLocationButtonEnabled(true);
            map.getUiSettings().setRotateGesturesEnabled(true);
            map.getUiSettings().setTiltGesturesEnabled(true);
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude))).zoom(16).build();
            map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


        }catch (Exception e){
            e.printStackTrace();
        }//end of try - catch

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem)
    {
        onBackPressed();
        return true;
    }


}