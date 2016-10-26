package com.pk.bmwandroid.data.source;

import com.pk.bmwandroid.model.Location;

import java.util.ArrayList;
import java.util.List;

public class InMemoryDataSource implements DataSource {
    private static List<Location> mLocations = new ArrayList<>();
    public List<Location> getLocations() { return mLocations; }
}
