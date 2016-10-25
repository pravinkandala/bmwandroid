package com.pk.bmwandroid.network;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.pk.bmwandroid.model.Location;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pravin on 10/25/16.
 * Project: bmwandroid
 */

public class LocalSearchManager {
    public static void getLocalSearchResults(final Context context, String url, final ServerCallback callback) {

        JsonArrayRequest mJsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(final JSONArray response) {
                List<Location> locations = new ArrayList<>();

                for (int i = 0; i < response.length(); i++) {

                    try {
                        locations.add(
                                Location.fromJsonObject(response.getJSONObject(i))
                        );
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                callback.onSuccess(locations);
            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("", "Error: " + error.getMessage());
                Toast.makeText(context,
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                // hide the progress dialog
            }
        });


        // Adding request to request queue
        VolleyRequestQueue.getInstance(context).addToRequestQueue(mJsonArrayRequest);
    }
}
