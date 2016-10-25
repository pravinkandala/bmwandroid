package com.pk.bmwandroid;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.dexafree.materialList.card.Card;
import com.dexafree.materialList.card.CardProvider;
import com.dexafree.materialList.listeners.RecyclerItemClickListener;
import com.dexafree.materialList.view.MaterialListView;
import com.pk.bmwandroid.model.Location;
import com.pk.bmwandroid.model.ServerCallback;
import com.pk.bmwandroid.model.factory.LocationComparatorFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jp.wasabeef.recyclerview.animators.SlideInDownAnimator;

import static com.pk.bmwandroid.model.factory.LocationComparatorFactory.getCompartorFactory;


public class MainActivity extends AppCompatActivity {

    MaterialListView mMaterialListView;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        mMaterialListView = ((MaterialListView) findViewById(R.id.my_recycler_view));
        mMaterialListView.setItemAnimator(new SlideInDownAnimator());
        mMaterialListView.getItemAnimator().setAddDuration(300);
        mMaterialListView.getItemAnimator().setRemoveDuration(300);

        // Get the criteria on which you want to sort
        final LocationComparatorFactory.SortingCriteria yourInput = LocationComparatorFactory.SortingCriteria.NAME;



        GetJson.getJSON(context, getString(R.string.json_url), new ServerCallback() {
            @Override
            public void onSuccess(final List<Location> locations) {
                // At any point of time you will get whole list of locations here.
                // Sort it using comparator.
                Collections.sort(locations, getCompartorFactory(yourInput));
                List<Card> cards = new ArrayList<>();
                for (int i = 0; i < locations.size(); i++) {
                    cards.add(new Card.Builder(MainActivity.this)
                            .withProvider(new CardProvider())
                            .setTitle(locations.get(i).getName())
                            .setTitleColor(Color.BLACK)
                            .setSubtitle(locations.get(i).getAddress())
                            .setSubtitleColor(Color.BLACK)
                            .setLayout(R.layout.item_location)
                            .endConfig()
                            .build());
                }
                mMaterialListView.addOnItemTouchListener(new RecyclerItemClickListener.OnItemClickListener() {

                    @Override
                    public void onItemClick(Card card, int position) {

                        Intent intent = new Intent(MainActivity.this, LocationDescriptionActivity.class);

                        intent.putExtra("id", locations.get(position).getId());
                        intent.putExtra("name", locations.get(position).getName());
                        intent.putExtra("latitude", locations.get(position).getLatitude());
                        intent.putExtra("longitude", locations.get(position).getLongitude());
                        intent.putExtra("address", locations.get(position).getAddress());
                        intent.putExtra("arrival_time", locations.get(position).getArrivalTime());

                        startActivity(intent);

                    }

                    @Override
                    public void onItemLongClick(Card card, int position) {
                        Log.d("LONG_CLICK", card.getTag().toString());
                    }
                });

                mMaterialListView.getAdapter().addAll(cards);

            }
        });

    }



}
