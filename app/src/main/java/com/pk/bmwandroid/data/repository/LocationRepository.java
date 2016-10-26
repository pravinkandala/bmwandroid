package com.pk.bmwandroid.data.repository;

import android.content.Context;

import com.pk.bmwandroid.data.source.DataSource;
import com.pk.bmwandroid.data.source.InMemoryDataSource;
import com.pk.bmwandroid.model.Location;
import com.pk.bmwandroid.model.factory.LocationComparatorFactory;
import com.pk.bmwandroid.model.factory.LocationComparatorFactory.SortingCriteria;

import java.util.Collections;
import java.util.List;

public class LocationRepository {

    private DataSource mDataSource;
    public LocationRepository() { mDataSource = new InMemoryDataSource(); }


    public void add(Location location) {
        mDataSource.getLocations().add(location);
    }

    /**
     * store list of locations
     */
    public void addAll(List<Location> locations) {
        mDataSource.getLocations().clear();
        mDataSource.getLocations().addAll(locations);
    }
    /**
     * returns stored list
     */
    public List<Location> getAll() {
        return mDataSource.getLocations();
    }

    /**
     * returns stored list using criteria
     */
    public List<Location> getAll(SortingCriteria criteria, Context context) {
        Collections.sort(mDataSource.getLocations(),
                LocationComparatorFactory.getComparatorFactory(criteria, context));
        return mDataSource.getLocations();
    }

}
