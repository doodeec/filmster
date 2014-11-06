package com.doodeec.filmster.MovieDetail;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.test.ActivityInstrumentationTestCase2;

import com.doodeec.filmster.MainActivity;
import com.doodeec.filmster.Mock;
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
    private MovieDetailFragment mFragment;

    public MovieDetailFragmentSpec() {
        super(MainActivity.class);
    }

    private Fragment startFragment(Fragment fragment) throws Exception {
        FragmentTransaction transaction = mActivity.getSupportFragmentManager().beginTransaction();
        //TODO
        transaction.add(android.R.id.content, fragment, "movie_detail_fragment");
        transaction.commit();
        getInstrumentation().waitForIdleSync();
        return mActivity.getSupportFragmentManager().findFragmentByTag("movie_detail_fragment");
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        solo = new Solo(getInstrumentation(), getActivity());
        setActivityInitialTouchMode(false);

        mActivity = getActivity();
        mFragment = new MovieDetailFragment();
        mFragment.setMovie(new Movie(Mock.REST_MOVIE()));
        startFragment(mFragment);
    }

    @Override
    protected void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();
    }
}
