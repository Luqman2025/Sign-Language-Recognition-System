package com.example.imagepro;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class CustomInternetDetector {

    private Context context;

    public CustomInternetDetector(Context context) {
        this.context = context;
    }

    public boolean isInternetAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
