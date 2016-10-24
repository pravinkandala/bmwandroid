package com.pk.bmwandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class LocationDescription extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_description);

        Intent intent = this.getIntent();
        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("name");
        String latitude = intent.getStringExtra("latitude");
        String longitude = intent.getStringExtra("longitude");
        String address = intent.getStringExtra("address");
        String arrival_time = intent.getStringExtra("arrival_time");


    }
}
