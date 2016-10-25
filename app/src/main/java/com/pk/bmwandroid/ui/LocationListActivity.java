package com.pk.bmwandroid.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.dexafree.materialList.card.Card;
import com.dexafree.materialList.card.CardProvider;
import com.dexafree.materialList.listeners.RecyclerItemClickListener;
import com.dexafree.materialList.view.MaterialListView;
import com.pk.bmwandroid.R;
import com.pk.bmwandroid.data.repository.LocationRepository;
import com.pk.bmwandroid.model.Location;
import com.pk.bmwandroid.model.factory.LocationComparatorFactory.SortingCriteria;
import com.pk.bmwandroid.network.LocalSearchManager;
import com.pk.bmwandroid.network.ServerCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.recyclerview.animators.SlideInDownAnimator;


public class LocationListActivity extends AppCompatActivity {

    @BindView(R.id.my_recycler_view)
    MaterialListView mMaterialListView;
    @BindView(R.id.activity_main)
    SwipeRefreshLayout mSwipeRefreshLayout;

    Context mContext;

    private LocationRepository mLocationRepository;
    private SortingCriteria mSortingCriteria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mMaterialListView.setItemAnimator(new SlideInDownAnimator());
        mMaterialListView.getItemAnimator().setAddDuration(300);
        mMaterialListView.getItemAnimator().setRemoveDuration(300);

        ButterKnife.bind(this);
        mContext = this;


        //Initializing Repository
        this.mLocationRepository = new LocationRepository();
        this.mSortingCriteria = SortingCriteria.NAME;


        // Get Initial Data - on Notified - fillCards
        initList();

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(true);
                initList();
            }
        });


    }

    public void initList() {
        LocalSearchManager.getLocalSearchResults(mContext, getString(R.string.json_url), new ServerCallback() {
            @Override
            public void onSuccess(final List<Location> locations) {
                mLocationRepository.addAll(locations);
                fillCards(mLocationRepository.getAll(mSortingCriteria, mContext));
            }
        });
    }

    public void fillCards(final List<Location> locations) {
        List<Card> cards = new ArrayList<>();
        mMaterialListView.getAdapter().clearAll();
        for (int i = 0; i < locations.size(); i++) {
            cards.add(new Card.Builder(mContext)
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
                Intent intent = new Intent(mContext, LocationDescriptionActivity.class);
                intent.putExtra("location", locations.get(position));
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(Card card, int position) {
                Log.d("LONG_CLICK", card.getTag().toString());
            }
        });

        mMaterialListView.getAdapter().addAll(cards);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_sort, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_refresh:
                Log.d("menu", "refresh");
                return true;

            case R.id.menu_sort_by_name:
                fillCards(this.mLocationRepository.getAll(SortingCriteria.NAME, mContext));
                return true;

            case R.id.menu_sort_by_time:
                fillCards(this.mLocationRepository.getAll(SortingCriteria.DISTANCE_FROM_CURRENT_LOCATION, mContext));

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}