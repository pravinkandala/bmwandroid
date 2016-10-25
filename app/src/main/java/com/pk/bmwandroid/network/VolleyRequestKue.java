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

public class VolleyRequestKue {
    public static final String DEFAULT_TAG = "VolleyRequestKue";
    private static VolleyRequestKue mInstance;
    private RequestQueue mRequestQueue;
    private Context mContext;

    private VolleyRequestKue(Context context) {
        mContext = context;
        mRequestQueue = getRequestQueue();
    }

    public static synchronized VolleyRequestKue getInstance(Context context) {
        if(mInstance == null)
            mInstance = new VolleyRequestKue(context);
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
