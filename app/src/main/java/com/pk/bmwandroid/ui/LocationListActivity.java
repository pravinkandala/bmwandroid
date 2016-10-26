package com.pk.bmwandroid.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.pk.bmwandroid.LocationAdapter;
import com.pk.bmwandroid.R;
import com.pk.bmwandroid.data.repository.LocationRepository;
import com.pk.bmwandroid.model.Location;
import com.pk.bmwandroid.model.factory.LocationComparatorFactory.SortingCriteria;
import com.pk.bmwandroid.network.LocalSearchManager;
import com.pk.bmwandroid.network.ServerCallback;

import java.util.List;

import butterknife.ButterKnife;
import jp.wasabeef.recyclerview.animators.SlideInRightAnimator;


public class LocationListActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView.Adapter mAdapter;

    Context mContext;
    int count = 0;
    private LocationRepository mLocationRepository;
    private SortingCriteria mSortingCriteria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mContext = this;

        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new SlideInRightAnimator());

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.activity_main);


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
//        List<Card> cards = new ArrayList<>();

        mAdapter = new LocationAdapter(this, locations);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


//        mMaterialListView.addOnItemTouchListener(new RecyclerItemClickListener.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(Card card, int position) {
//                Log.d("cards","button clicked: "+ count);
//                Intent intent = new Intent(mContext, LocationDescriptionActivity.class);
//                intent.putExtra("location", locations.get(position));
//                startActivity(intent);
//            }
//
//            @Override
//            public void onItemLongClick(Card card, int position) {
//                Log.d("LONG_CLICK", card.getTag().toString());
//            }
//        });
//
//        mMaterialListView.getAdapter().addAll(cards);
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
                Log.d("context", "Context: " + mContext);
                return true;

            case R.id.menu_sort_by_time:
                fillCards(this.mLocationRepository.getAll(SortingCriteria.TIME, mContext));

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}