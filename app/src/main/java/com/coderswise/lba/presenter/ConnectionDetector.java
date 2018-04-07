package com.coderswise.lba.presenter;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Jakir Hossain
 * Date: 3/4/2017.
 */
public class ConnectionDetector {
    private Context context;

    public ConnectionDetector(Context context){
        this.context = context;
    }

    /**
     * Checking for all possible internet providers
     * **/
    public boolean isConnectingToInternet(){
        boolean isConnected=false;
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null)
        {
            NetworkInfo activeNetwork = connectivity.getActiveNetworkInfo();
             isConnected= activeNetwork != null &&
                    activeNetwork.isConnectedOrConnecting();

        }
        return isConnected;
    }
}
