package com.doodeec.filmster.LazyList;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.test.ActivityInstrumentationTestCase2;

import com.doodeec.filmster.MainActivity;
import com.robotium.solo.Solo;

import java.util.Arrays;

/**
 * Created by Dusan Doodeec Bartos on 1.11.2014.
 *
 * @see com.doodeec.filmster.LazyList.LazyList
 */
public class LazyListSpec extends ActivityInstrumentationTestCase2<MainActivity> {

    private Solo solo;
    private MainActivity mActivity;
    private LazyList<String> mFragment;

    public LazyListSpec() {
        super(MainActivity.class);
    }

    private Fragment startFragment(Fragment fragment) throws Exception {
        FragmentTransaction transaction = mActivity.getSupportFragmentManager().beginTransaction();
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
        mFragment = new LazyList<String>();
        startFragment(mFragment);
    }

    public void testInitAdapter() throws Exception {
        assertNotNull(mFragment.mAdapter);
    }

    public void testMaxDataLength() throws Exception {
        mFragment.setMaxDataLength(null);
        mFragment.mData.clear();
        mFragment.mData.addAll(Arrays.asList(new String[5]));
        assertFalse(mFragment.mBlockLazyLoad);

        mFragment.setMaxDataLength(5);
        assertTrue(mFragment.mBlockLazyLoad);

        mFragment.setMaxDataLength(null);
        assertFalse(mFragment.mBlockLazyLoad);
    }

    @Override
    protected void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();
    }
}
