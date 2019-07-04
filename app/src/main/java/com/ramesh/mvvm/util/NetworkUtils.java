package com.ramesh.mvvm.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.ramesh.mvvm.di.ApplicationContext;

import javax.inject.Inject;


public class NetworkUtils {

    private Context mContext;


    @Inject
    public NetworkUtils(@ApplicationContext Context context) {
        mContext = context;
    }

    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        }
        return false;
    }
}
