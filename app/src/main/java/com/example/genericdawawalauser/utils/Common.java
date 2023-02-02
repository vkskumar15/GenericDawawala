package com.example.genericdawawalauser.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Common {
    public static boolean isConnectedToInternet(Context context) {
        NetworkInfo[] infos;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null || (infos = connectivityManager.getAllNetworkInfo()) == null) {
            return false;
        }
        for (NetworkInfo state : infos) {
            if (state.getState() == NetworkInfo.State.CONNECTED) {
                return true;
            }
        }
        return false;
    }
}
