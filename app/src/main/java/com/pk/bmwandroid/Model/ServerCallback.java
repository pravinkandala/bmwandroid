package com.pk.bmwandroid.model;

import java.util.List;

/**
 * Created by Pravin on 10/24/16.
 * Project: bmwandroid
 */

public interface ServerCallback {
    void onSuccess(List<Location> locations);
}
