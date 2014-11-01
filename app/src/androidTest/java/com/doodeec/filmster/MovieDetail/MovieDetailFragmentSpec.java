package com.doodeec.filmster.MovieDetail;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.test.ActivityInstrumentationTestCase2;

import com.doodeec.filmster.LazyList.LazyList;
import com.doodeec.filmster.MainActivity;
import com.doodeec.filmster.Model.Movie;
import com.robotium.solo.Solo;

/**
 * Created by Dusan Doodeec Bartos on 1.11.2014.
 *
 * @see com.doodeec.filmster.MovieDetail.MovieDetailFragment
 */
public class MovieDetailFragmentSpec extends ActivityInstrumentationTestCase2<MainActivity> {

    private Solo solo;
    private MainActivity mActivity;
    private LazyList<Movie> mFragment;

    public MovieDetailFragmentSpec() {
        super(MainActivity.class);
    }

    private Fragment startFragment(Fragment fragment) throws Exception {
        FragmentTransaction transaction = mActivity.getSupportFragmentManager().beginTransaction();
        //TODO
        transaction.add(android.R.id.content, fragment, "lazy_list_fragment");
        transaction.commit();
        getInstrumentation().waitForIdleSync();
        return mActivity.getSupportFragmentManager().findFragmentByTag("lazy_list_fragment");
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        solo = new Solo(getInstrumentation(), getActivity());
        setActivityInitialTouchMode(false);

        mActivity = getActivity();
        mFragment = new LazyList<Movie>();
        startFragment(mFragment);
    }

    @Override
    protected void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();
    }
}
