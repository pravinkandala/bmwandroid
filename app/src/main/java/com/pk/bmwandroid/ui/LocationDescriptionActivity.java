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

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.pk.bmwandroid.util.DateUtil.getDuration;

public class LocationDescriptionActivity extends AppCompatActivity {

    @BindView(R.id.name) TextView mNameTV;
    @BindView(R.id.address) TextView mAddressTV;
    @BindView(R.id.arrival_time) TextView mArrivalTimeTV;
    @BindView(R.id.latitude) TextView mLatitudeTV;
    @BindView(R.id.longitude) TextView mLongitudeTV;

    private GoogleMap mGoogleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_description);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = this.getIntent();
        final Location location = (Location) intent.getSerializableExtra("location");
        getSupportActionBar().setTitle(location.getName());

//        String id = location.getId();
        String name = location.getName();
        String latitude = location.getLatitude();
        String longitude = location.getLongitude();
        String address = location.getAddress();
        String arrival_time = location.getArrivalTime();

        //find diff of duration from now
        DateTime arrival_time_in_string = new DateTime(arrival_time);
        String time = getDuration(arrival_time_in_string);

        mNameTV.setText("Name: "+name);
        mArrivalTimeTV.setText("Arrival Time: "+time);
        mLatitudeTV.setText("Lat:"+latitude);
        mLongitudeTV.setText("Lng:"+longitude);
        mAddressTV.setText("Address: "+address);


        try{
            //map settings
            mGoogleMap = ((MapFragment)getFragmentManager().findFragmentById(R.id.google_map_fragment)).getMap();
            MarkerOptions k = new MarkerOptions()
                    .position(new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude)))
                    .title(name);
            mGoogleMap.addMarker(k);
            mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            mGoogleMap.getUiSettings().setZoomControlsEnabled(false);
            mGoogleMap.getUiSettings().setAllGesturesEnabled(false);
            mGoogleMap.getUiSettings().setCompassEnabled(true);
            mGoogleMap.getUiSettings().setMyLocationButtonEnabled(true);
            mGoogleMap.getUiSettings().setRotateGesturesEnabled(true);
            mGoogleMap.getUiSettings().setTiltGesturesEnabled(true);
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude))).zoom(16).build();
            mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem)
    {
        finish();
        return true;
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
