package com.pk.bmwandroid.network;

import com.pk.bmwandroid.model.Location;

import java.util.List;

public interface ServerCallback {
    void onSuccess(List<Location> locations);
}
