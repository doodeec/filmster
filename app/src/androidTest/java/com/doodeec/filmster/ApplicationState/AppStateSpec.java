package com.doodeec.filmster.ApplicationState;

import android.app.Activity;
import android.test.ApplicationTestCase;

import com.doodeec.filmster.Helper;

/**
 * Created by Dusan Doodeec Bartos on 26.10.2014.
 *
 * @see com.doodeec.filmster.ApplicationState.AppState
 */
public class AppStateSpec extends ApplicationTestCase<AppState> {

    public AppStateSpec() {
        super(AppState.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        createApplication();
    }

    public void testContext() throws Exception {
        assertNotNull(AppState.getContext());
    }

    public void testIsOnline() throws Exception {
        assertEquals(Helper.isOnline(), AppState.getIsApplicationOnline());
    }

    public void testCurrentActivity() throws Exception {
        Activity activity = new Activity();

        assertNotSame(activity, AppState.getCurrentActivity());
        AppState.setCurrentActivity(activity);
        assertEquals(activity, AppState.getCurrentActivity());
    }
}
