package com.pk.bmwandroid.data.repository;

import android.content.Context;

import com.pk.bmwandroid.data.source.DataSource;
import com.pk.bmwandroid.data.source.InMemoryDataSource;
import com.pk.bmwandroid.model.Location;
import com.pk.bmwandroid.model.factory.LocationComparatorFactory;
import com.pk.bmwandroid.model.factory.LocationComparatorFactory.SortingCriteria;

import java.util.Collections;
import java.util.List;

/**
 * Created by Pravin on 10/25/16.
 * Project: bmwandroid
 */

public class LocationRepository {

    private DataSource dataSource;
    public LocationRepository() { dataSource = new InMemoryDataSource(); }

    public void add(Location location) {
        dataSource.getLocations().add(location);
    }
    public void addAll(List<Location> locations) {
        dataSource.getLocations().clear();
        dataSource.getLocations().addAll(locations);
    }
    public List<Location> getAll() {
        return dataSource.getLocations();
    }
    public List<Location> getAll(SortingCriteria criteria, Context context) {
        Collections.sort(dataSource.getLocations(), LocationComparatorFactory.getCompartorFactory(criteria, context));
        return dataSource.getLocations();
    }

}
