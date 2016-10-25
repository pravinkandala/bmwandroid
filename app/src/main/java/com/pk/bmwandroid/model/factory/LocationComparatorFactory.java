package com.pk.bmwandroid.model.factory;

import android.content.Context;

import com.pk.bmwandroid.listener.GPSTracker;
import com.pk.bmwandroid.model.Location;

import java.util.Comparator;

/**
 * Created by Pravin on 10/24/16.
 * Project: bmwandroid
 */

public class LocationComparatorFactory {

    public enum SortingCriteria { NAME, DISTANCE_FROM_CURRENT_LOCATION }

    public static Comparator<Location> getCompartorFactory(SortingCriteria criteria, final Context context) {
        switch (criteria) {
            case NAME: return new Comparator<Location>() {
                @Override
                public int compare(Location l1, Location l2) {
                    return l1.getName().compareTo(l2.getName());
                }
            };

            case DISTANCE_FROM_CURRENT_LOCATION: return new Comparator<Location>() {
                @Override
                public int compare(Location l1, Location l2) {

                    android.location.Location loc1 = new android.location.Location("Location 1");
                    loc1.setLatitude(Double.parseDouble(l1.getLongitude()));
                    loc1.setLongitude(Double.parseDouble(l1.getLongitude()));

                    android.location.Location loc2 = new android.location.Location("Location 2");
                    loc2.setLatitude(Double.parseDouble(l1.getLongitude()));
                    loc2.setLongitude(Double.parseDouble(l1.getLongitude()));

                    android.location.Location myLocation = new android.location.Location("Location 2");
                    GPSTracker gpsTracker = new GPSTracker(context);
                    if(gpsTracker.getIsGPSTrackingEnabled()) {
                        loc2.setLatitude(gpsTracker.getLatitude());
                        loc2.setLongitude(gpsTracker.getLatitude());
                    }else{
                        gpsTracker.showSettingsAlert();
                    }


                    float distance1 = myLocation.distanceTo(loc1);
                    float distance2 = myLocation.distanceTo(loc2);




                    //final double d1 = getDistanceBetween(lat, lng, l1.getLatitude(), l1.getLongitude());
                    //final double d2 = getDistanceBetween(lat, lng, l2.getLatitude(), l2.getLongitude());



                    // getTimeDifferenceFromNow(Date date) : (date - now)
                    return Float.compare(distance1,distance2);

                }
            };

            default: throw new RuntimeException();
        }
    }
}
