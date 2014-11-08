package com.doodeec.filmster.ApplicationState;

import android.app.Activity;
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
    private static Activity mCurrentActivity;

    private static final BroadcastReceiver connectionChange = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (Helper.isOnline()) {
                mOnline = true;
                if (mCurrentActivity != null && mCurrentActivity instanceof ConnectionStateChange) {
                    ((ConnectionStateChange) mCurrentActivity).onConnectionGained();
                }
            } else {
                mOnline = false;
                if (mCurrentActivity != null && mCurrentActivity instanceof ConnectionStateChange) {
                    ((ConnectionStateChange) mCurrentActivity).onConnectionLost();
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

    /**
     * Tracks currently opened activity
     *
     * @param activity activity
     */
    public static void setCurrentActivity(Activity activity) {
        mCurrentActivity = activity;
    }

    /**
     * @return currently opened activity
     */
    public static Activity getCurrentActivity() {
        return mCurrentActivity;
    }

    /**
     * @return application context
     */
    public static Context getContext() {
        return mContext;
    }

    /**
     * @return true if device has access to the network
     */
    public static boolean getIsApplicationOnline() {
        return mOnline;
    }
}
