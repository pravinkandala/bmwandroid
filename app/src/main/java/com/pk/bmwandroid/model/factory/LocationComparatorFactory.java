package com.pk.bmwandroid.model.factory;

import com.pk.bmwandroid.model.Location;

import java.util.Comparator;

/**
 * Created by Pravin on 10/24/16.
 * Project: bmwandroid
 */

public class LocationComparatorFactory {

    public enum SortingCriteria { NAME, DISTANCE_FROM_CURRENT_LOCATION }

    public static Comparator<Location> getCompartorFactory(SortingCriteria criteria) {
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

                    android.location.Location loc2 = new android.location.Location("Location 1");
                    loc2.setLatitude(Double.parseDouble(l1.getLongitude()));
                    loc2.setLongitude(Double.parseDouble(l1.getLongitude()));

                    float distance = loc1.distanceTo(loc2);




                    //final double d1 = getDistanceBetween(lat, lng, l1.getLatitude(), l1.getLongitude());
                    //final double d2 = getDistanceBetween(lat, lng, l2.getLatitude(), l2.getLongitude());



                    // getTimeDifferenceFromNow(Date date) : (date - now)
                    //return Double.compare(d1,d2);
                    return 0;
                }
            };

            default: throw new RuntimeException();
        }
    }
}
