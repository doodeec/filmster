package com.doodeec.filmster.LazyList;

import android.graphics.Bitmap;
import android.view.View;

/**
 * Created by Dusan Doodeec Bartos on 26.10.2014.
 *
 * Lazy loading list fragment
 * Extends {@link android.support.v4.app.ListFragment}
 * Features loading indicator at the bottom of the list to enable lazy loading of data
 */
public class LazyList extends android.support.v4.app.ListFragment {

    protected void movieImageLoaded(int position, Bitmap movieImage) {
        View viewAtPosition = getListView().getChildAt(position - getListView().getFirstVisiblePosition());

        // update only if view is visible
        if (viewAtPosition != null) {
            MovieItemHolder holder = (MovieItemHolder) viewAtPosition.getTag();
            holder.setPoster(movieImage);
        }
    }
}
