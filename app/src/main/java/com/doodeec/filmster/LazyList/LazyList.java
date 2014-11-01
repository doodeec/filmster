package com.doodeec.filmster.LazyList;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.doodeec.filmster.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dusan Doodeec Bartos on 26.10.2014.
 *
 * Lazy loading list fragment
 * Extends {@link android.support.v4.app.ListFragment}
 * Features loading indicator at the bottom of the list to enable lazy loading of data
 */
public class LazyList<T> extends android.support.v4.app.ListFragment {

    public static final int REASON_LIST_END = 1;
    public static final int REASON_SERVER_ERROR = 2;
    public static final int REASON_CONNECTION_LOST = 4;
    public static final int REASON_REQUEST_CANCELLED = 8;

    private static final float SHOWN_LOADER_POSITION = 0;
    private static final float SHOWN_LOADER_ALPHA = 1f;
    private static final float HIDDEN_LOADER_ALPHA = 0;
    private static final int LOADER_ANIMATION_DURATION = 300;

    protected Integer maxDataLength;
    // last currently loaded page
    protected int mPage;
    protected boolean mLoading = false;
    protected boolean mBlockLazyLoad = false;
    protected final List<T> mData = new ArrayList<T>();
    protected LazyListAdapter mAdapter;
    protected RelativeLayout mProgress;

    private final AbsListView.OnScrollListener mScrollListener = new AbsListView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {}

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            if (!mBlockLazyLoad && !mLoading && visibleItemCount > 0 && firstVisibleItem + visibleItemCount == totalItemCount) {
                loadPage(++mPage);
            }
        }
    };

    public LazyList() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.lazy_list_fragment, container, false);
        mProgress = (RelativeLayout) v.findViewById(R.id.loading_progress);

        if (mProgress == null) {
            throw new AssertionError("Lazy list has invalid layout defined");
        }

        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mProgress.setVisibility(View.VISIBLE);
        initData();
    }

    /**
     * Subclasses should override this method to specify adapter at this point
     */
    protected void initAdapter() {
        mAdapter = new LazyListAdapter<T>(mData, LazyList.this);
    }

    /**
     * Initializes data list, initializes and sets the adapter
     */
    private void initData() {
        mData.clear();
        initAdapter();
        setListAdapter(mAdapter);
        getListView().setOnScrollListener(mScrollListener);
    }

    /**
     * Subclasses should override this method to load specific page
     *
     * @param page page to load
     */
    protected synchronized void loadPage(int page) {
        mLoading = true;
        mProgress.setVisibility(View.VISIBLE);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB) {
            mProgress.animate()
                    .translationY(SHOWN_LOADER_POSITION)
                    .alpha(SHOWN_LOADER_ALPHA)
                    .setDuration(LOADER_ANIMATION_DURATION);
        } else {
            mProgress.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Notifies adapter when page was loaded
     *
     * @param pageNumber page loaded
     */
    protected synchronized void onDataLoadingCompleted(int pageNumber) {
        mLoading = false;
        mPage = pageNumber;

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB) {
                    mProgress.animate()
                            .translationY(mProgress.getHeight())
                            .alpha(HIDDEN_LOADER_ALPHA)
                            .setDuration(LOADER_ANIMATION_DURATION);
                } else {
                    mProgress.setVisibility(View.GONE);
                }

                mAdapter.notifyDataSetChanged();
                checkMaxDataLength();
            }
        });
    }

    /**
     * Loading failed
     *
     * @param reason reason ID
     */
    protected synchronized void onDataLoadingFailed(int reason) {
        mLoading = false;

        //TODO prompt request repeat

        switch (reason) {
            case REASON_LIST_END:
                Toast.makeText(getActivity(), "End of the list", Toast.LENGTH_SHORT).show();
                mBlockLazyLoad = true;
                break;
            case REASON_SERVER_ERROR:
                Toast.makeText(getActivity(), "Server error", Toast.LENGTH_SHORT).show();
                break;
            case REASON_CONNECTION_LOST:
                break;
            default:
                Toast.makeText(getActivity(), "Unknown error", Toast.LENGTH_SHORT).show();
                //TODO throw error?
                break;
        }

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB) {
                    mProgress.animate()
                            .translationY(mProgress.getHeight())
                            .alpha(HIDDEN_LOADER_ALPHA)
                            .setDuration(LOADER_ANIMATION_DURATION);
                } else {
                    mProgress.setVisibility(View.GONE);
                }
            }
        });
    }


    /**
     * Sets maximal data length to be possible to load with lazy loading
     * If data length oversizes max length, lazy loading by scroll will be further disabled
     * @param maxLength max length to set
     */
    protected void setMaxDataLength(Integer maxLength) {
        maxDataLength = maxLength;
        if (maxDataLength != null) {
            mBlockLazyLoad = mData.size() <= maxDataLength;
        }
    }

    /**
     * If max length is defined,
     */
    private void checkMaxDataLength() {
        if (maxDataLength != null && mData.size() >= maxDataLength) {
            mBlockLazyLoad = true;
            Toast.makeText(getActivity(), "End of the list", Toast.LENGTH_SHORT).show();
        }
    }
}
