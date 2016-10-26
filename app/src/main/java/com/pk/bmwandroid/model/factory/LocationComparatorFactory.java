package com.pk.bmwandroid.model.factory;

import android.content.Context;

import com.pk.bmwandroid.model.Location;

import org.joda.time.Seconds;

import java.util.Comparator;

import static com.pk.bmwandroid.util.DateUtil.getDurationInJoda;
import static com.pk.bmwandroid.util.DateUtil.toDateTime;

public class LocationComparatorFactory {

    public static Comparator<Location> getComparatorFactory(SortingCriteria criteria, final Context context) {
        switch (criteria) {
            case NAME:
                return new Comparator<Location>() {
                    @Override
                    public int compare(Location l1, Location l2) {
                        return l1.getName().compareTo(l2.getName());
                    }
                };

            case TIME:
                return new Comparator<Location>() {
                    @Override
                    public int compare(Location l1, Location l2) {
                        Seconds time1 = getDurationInJoda(toDateTime(l1.getArrivalTime()));
                        Seconds time2 = getDurationInJoda(toDateTime(l2.getArrivalTime()));
                        return time1.compareTo(time2);

                    }
                };

            default:
                throw new RuntimeException();
        }
    }

    public enum SortingCriteria {NAME, TIME}
}
