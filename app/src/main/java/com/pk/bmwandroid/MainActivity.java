package com.pk.bmwandroid;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.dexafree.materialList.card.Card;
import com.dexafree.materialList.card.CardProvider;
import com.dexafree.materialList.listeners.RecyclerItemClickListener;
import com.dexafree.materialList.view.MaterialListView;
import com.pk.bmwandroid.Model.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.animators.SlideInDownAnimator;

public class MainActivity extends AppCompatActivity {

    MaterialListView mMaterialListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMaterialListView = ((MaterialListView) findViewById(R.id.my_recycler_view));
        mMaterialListView.setItemAnimator(new SlideInDownAnimator());
        mMaterialListView.getItemAnimator().setAddDuration(300);
        mMaterialListView.getItemAnimator().setRemoveDuration(300);

        getJson(getString(R.string.json_url));

    }

    public void getJson(String url) {

        JsonArrayRequest mJsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(final JSONArray response) {
                try {
                    List<Card> cards = new ArrayList<>();

                    for (int i = 0; i < response.length(); i++) {

                        JSONObject jsonObject = response.getJSONObject(i);
                        String id = jsonObject.getString("ID");
                        String name = jsonObject.getString("Name");
                        String latitude = jsonObject.getString("Latitude");
                        String longitude = jsonObject.getString("Longitude");
                        String address = jsonObject.getString("Address");
                        String arrivalTime = jsonObject.getString("ArrivalTime");

                        cards.add(new Card.Builder(MainActivity.this)
                                .setTag("HOSPITAL_NEAR_YOU")
                                .withProvider(new CardProvider())
                                .setTitle("Name: " + name)
                                .setTitleColor(Color.BLACK)
                                .setSubtitle("Location :" + address)
                                .setSubtitleColor(Color.BLACK)
                                .setLayout(R.layout.item_location)
                                .endConfig()
                                .build());

                        Log.d("Json response:", "id: " + id + " ,name: " + name);
                    }

                    mMaterialListView.addOnItemTouchListener(new RecyclerItemClickListener.OnItemClickListener() {

                        @Override
                        public void onItemClick(Card card, int position) {
                            Intent intent = new Intent(MainActivity.this, LocationDescription.class);
                            try {
                                intent.putExtra("id", response.getJSONObject(position).getString("ID"));
                                intent.putExtra("name", response.getJSONObject(position).getString("Name"));
                                intent.putExtra("latitude", response.getJSONObject(position).getString("Latitude"));
                                intent.putExtra("longitude", response.getJSONObject(position).getString("Longitude"));
                                intent.putExtra("address", response.getJSONObject(position).getString("Address"));
                                intent.putExtra("arrival_time", response.getJSONObject(position).getString("ArrivalTime"));

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            startActivity(intent);

                        }

                        @Override
                        public void onItemLongClick(Card card, int position) {
                            Log.d("LONG_CLICK", card.getTag().toString());
                        }
                    });
                    mMaterialListView.getAdapter().addAll(cards);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("", "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                // hide the progress dialog
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(mJsonArrayRequest);
    }
}
