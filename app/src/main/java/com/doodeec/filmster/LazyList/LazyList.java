package com.doodeec.filmster.LazyList;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.doodeec.filmster.R;

/**
 * Created by Dusan Doodeec Bartos on 26.10.2014.
 *
 * Lazy loading list fragment
 * Extends {@link android.support.v4.app.ListFragment}
 * Features loading indicator at the bottom of the list to enable lazy loading of data
 */
public class LazyList extends android.support.v4.app.ListFragment {

    protected LazyListAdapter mAdapter;
    protected ProgressBar mProgress;

    public LazyList() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.lazy_list_fragment, container, false);
        mProgress = (ProgressBar) v.findViewById(R.id.loading_progress);

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

    protected void movieImageLoaded(int position, Bitmap movieImage) {
        View viewAtPosition = getListView().getChildAt(position - getListView().getFirstVisiblePosition());

        // update only if view is visible
        if (viewAtPosition != null) {
            MovieItemHolder holder = (MovieItemHolder) viewAtPosition.getTag();
            holder.setThumbnail(movieImage);
        }
    }

    /**
     * Subclasses should override this method to load data at this point
     */
    protected void initData() {
    }
}
