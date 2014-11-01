package com.doodeec.filmster.ApplicationState;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;

import com.doodeec.filmster.Helper;

/**
 * Created by Dusan Doodeec Bartos on 26.10.2014.
 *
 * Keeps track of application state
 */
public class AppState extends Application {
    private static Context mContext;
    private static Boolean mOnline;
    private static ConnectionStateChange mCurrentActivity;

    private static final BroadcastReceiver connectionChange = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (mCurrentActivity != null) {
                if (Helper.isOnline()) {
                    mOnline = true;
                    mCurrentActivity.onConnectionGained();
                } else {
                    mOnline = false;
                    mCurrentActivity.onConnectionLost();
                }
            }
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        mOnline = Helper.isOnline();

        // register network state change listener
        IntentFilter networkIntentFilter = new IntentFilter();
        networkIntentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(connectionChange, networkIntentFilter);
    }

    public static void setCurrentActivity(ConnectionStateChange activity) {
        mCurrentActivity = activity;
    }

    public static Context getContext() {
        return mContext;
    }

    public static boolean getIsApplicationOnline() {
        return mOnline;
    }
}
