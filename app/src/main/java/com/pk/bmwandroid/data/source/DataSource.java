package com.pk.bmwandroid.data.source;

import com.pk.bmwandroid.model.Location;

import java.util.List;

/**
 * Created by Pravin on 10/25/16.
 * Project: bmwandroid
 */

public interface DataSource {
    List<Location> getLocations();
}
