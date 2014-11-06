package com.doodeec.filmster.MovieList;

import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.test.ActivityInstrumentationTestCase2;

import com.doodeec.filmster.MainActivity;
import com.robotium.solo.Solo;

/**
 * Created by Dusan Doodeec Bartos on 1.11.2014.
 *
 * @see com.doodeec.filmster.MovieList.MovieListFragment
 */
public class MovieListFragmentSpec extends ActivityInstrumentationTestCase2<MainActivity> {

    private Solo solo;
    private MainActivity mActivity;
    private MovieListFragment mFragment;

    public MovieListFragmentSpec() {
        super(MainActivity.class);
    }

    private Fragment startFragment(Fragment fragment) throws Exception {
        FragmentTransaction transaction = mActivity.getSupportFragmentManager().beginTransaction();
        transaction.add(android.R.id.content, fragment, "movie_list_fragment");
        transaction.commit();
        getInstrumentation().waitForIdleSync();
        return mActivity.getSupportFragmentManager().findFragmentByTag("movie_list_fragment");
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        solo = new Solo(getInstrumentation(), getActivity());
        setActivityInitialTouchMode(false);

        mActivity = getActivity();
        mFragment = new MovieListFragment();
        startFragment(mFragment);
    }

    public void testImageLoaded() throws Exception {
        Bitmap image = Bitmap.createBitmap(100,200, Bitmap.Config.ARGB_8888);

        mFragment.movieImageLoaded(0, image);
    }

    public void testLoadPage() throws Exception {
        mFragment.loadPage(1);
    }

    //TODO more tests

    @Override
    protected void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();
    }
}
