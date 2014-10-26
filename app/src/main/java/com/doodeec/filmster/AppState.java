package com.doodeec.filmster;

import android.app.Application;
import android.content.Context;

/**
 * Created by Dusan Doodeec Bartos on 26.10.2014.
 *
 * Keeps track of application state
 */
public class AppState extends Application {
    private static Context mContext;
    private static Boolean mOnline;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getContext() {
        return mContext;
    }

    public static void setIsApplicationOnline(boolean isOnline) {
        mOnline = isOnline;
    }

    public static boolean getIsApplicationOnline() {
        return mOnline;
    }
}
