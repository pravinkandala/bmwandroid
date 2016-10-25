package com.pk.bmwandroid.network;

import android.content.Context;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Pravin on 10/25/16.
 * Project: bmwandroid
 */

public class VolleyRequestQueue {
    public static final String DEFAULT_TAG = "VolleyRequestQueue";
    private static VolleyRequestQueue mInstance;
    private RequestQueue mRequestQueue;
    private Context mContext;

    private VolleyRequestQueue(Context context) {
        mContext = context;
        mRequestQueue = getRequestQueue();
    }

    public static synchronized VolleyRequestQueue getInstance(Context context) {
        if(mInstance == null)
            mInstance = new VolleyRequestQueue(context);
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null)
            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? DEFAULT_TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(DEFAULT_TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        getRequestQueue().cancelAll(tag);
    }
}
