package com.pk.bmwandroid.network;

import com.pk.bmwandroid.model.Location;

import java.util.List;

/**
 * Created by Pravin on 10/24/16.
 * Project: bmwandroid
 */

public interface ServerCallback {
    void onSuccess(List<Location> locations);
}
