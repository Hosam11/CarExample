package com.example.carexample;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class HelperClass {
    public static final String TAG = "example_app";
//    public static final String API_URL = "https://api.github.com/users/square/";
    public static final String API_URL = "http://demo1585915.mockable.io/api/v1/";

    public static final String OPEN_INTERNET = "Please open internet ";
    public static final String ERROR_HAPPENED = "error happened ";


    /**
     * @param context: context that called the method
     * @return true if there is a internet connection otherwise false
     */
    public static boolean checkInternetState(Context context) {
        ConnectivityManager cm = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            return netInfo != null && netInfo.isConnectedOrConnecting();
        } else {
            // if device on airplane mode
            return false;
        }
    }

}
