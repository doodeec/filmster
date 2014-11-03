package com.doodeec.filmster;

import android.content.Context;
import android.net.ConnectivityManager;
import android.test.AndroidTestCase;

/**
 * Created by Dusan Doodeec Bartos on 26.10.2014.
 *
 * @see com.doodeec.filmster.Helper
 */
public class HelperSpec extends AndroidTestCase {

    public void testGetString() throws Exception {
        assertEquals(getContext().getResources().getString(android.R.string.ok),
                Helper.getString(android.R.string.ok));
    }

    public void testGetColor() throws Exception {
        assertEquals(getContext().getResources().getColor(android.R.color.white),
                Helper.getColor(android.R.color.white));
    }

    public void testIsOnline() throws Exception {
        assertEquals(((ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE))
                        .getActiveNetworkInfo().isConnectedOrConnecting(),
                Helper.isOnline());
    }
}
