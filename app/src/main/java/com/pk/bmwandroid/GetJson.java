package com.pk.bmwandroid;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.pk.bmwandroid.model.Location;
import com.pk.bmwandroid.model.ServerCallback;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pravin on 10/24/16.
 * Project: bmwandroid
 */

public class GetJson {

    public static void getJSON(final Context context, String url, final ServerCallback callback) {

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

//                        cards.add(new Card.Builder(MainActivity.this)
//                                .setTag("HOSPITAL_NEAR_YOU")
//                                .withProvider(new CardProvider())
//                                .setTitle("Name: " + name)
//                                .setTitleColor(Color.BLACK)
//                                .setSubtitle("Location :" + address)
//                                .setSubtitleColor(Color.BLACK)
//                                .setLayout(R.layout.item_location)
//                                .endConfig()
//                                .build());

//                        Log.d("Json response:", "id: " + id + " ,name: " + name);
//                    }

//                    mMaterialListView.addOnItemTouchListener(new RecyclerItemClickListener.OnItemClickListener() {
//
//                        @Override
//                        public void onItemClick(Card card, int position) {
//                            Intent intent = new Intent(MainActivity.this, LocationDescriptionActivity.class);
//                            try {
//                                intent.putExtra("id", response.getJSONObject(position).getString("ID"));
//                                intent.putExtra("name", response.getJSONObject(position).getString("Name"));
//                                intent.putExtra("latitude", response.getJSONObject(position).getString("Latitude"));
//                                intent.putExtra("longitude", response.getJSONObject(position).getString("Longitude"));
//                                intent.putExtra("address", response.getJSONObject(position).getString("Address"));
//                                intent.putExtra("arrival_time", response.getJSONObject(position).getString("ArrivalTime"));
//
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//
//                            startActivity(intent);
//
//                        }
//
//                        @Override
//                        public void onItemLongClick(Card card, int position) {
//                            Log.d("LONG_CLICK", card.getTag().toString());
//                        }
//                    });
//
//                    mMaterialListView.getAdapter().addAll(cards);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }


//            }
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
        AppController.getInstance().addToRequestQueue(mJsonArrayRequest);
    }
}
