package com.pk.bmwandroid;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pk.bmwandroid.model.Location;
import com.pk.bmwandroid.ui.LocationDescriptionActivity;

import java.util.List;

/**
 * Created by Pravin on 10/26/16.
 * Project: bmwandroid
 */

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.ViewHolder> {

    private List<Location> mLocations;
    private Context mContext;

    public LocationAdapter(Context context, List<Location> locations) {
        mContext = context;
        mLocations = locations;
    }


    public Context getContext(){
        return mContext;
    }


    //inflating a layout(xml) and returning the holder
    @Override
    public LocationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View locationView = inflater.inflate(R.layout.item_location, parent, false);

        //return a new holder instance
        ViewHolder viewHolder = new ViewHolder(locationView, mLocations);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(LocationAdapter.ViewHolder holder, int position) {

        Location location = mLocations.get(position);

        TextView nameTV = holder.mNameTV;
        nameTV.setText(location.getName());

        TextView addressTV = holder.mAddressTV;
        addressTV.setText(location.getAddress());



    }

    @Override
    public int getItemCount() {
        return mLocations.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView mNameTV;
        public TextView mAddressTV;
        private Context mContext;
        List<Location> mLocations;

        public ViewHolder(View view, List<Location> locations) {
            super(view);
            this.mContext = view.getContext();

            mLocations = locations;
            mNameTV = (TextView) view.findViewById(R.id.title_name);
            mAddressTV = (TextView) view.findViewById(R.id.subtitle_address);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) { // Check if an item was deleted, but the user clicked it before the UI removed it

                Log.d("card","clicked pos:"+position);
                Intent intent = new Intent(mContext, LocationDescriptionActivity.class);
                intent.putExtra("location", mLocations.get(position));
                mContext.startActivity(intent);
            }
        }
    }
}




