package com.doodeec.filmster.LazyList;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.doodeec.filmster.Model.Movie;
import com.doodeec.filmster.R;
import com.doodeec.filmster.ServerCommunicator.ResourceService;
import com.doodeec.filmster.ServerCommunicator.ResponseListener.ServerResponseListener;
import com.doodeec.filmster.ServerCommunicator.ServerRequest.ErrorResponse;

/**
 * Created by Dusan Doodeec Bartos on 26.10.2014.
 *
 * Lazy loading list fragment
 * Extends {@link android.support.v4.app.ListFragment}
 * Features loading indicator at the bottom of the list to enable lazy loading of data
 */
public class LazyList extends android.support.v4.app.ListFragment {

    private LazyListAdapter mAdapter;
    private ProgressBar mProgress;

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

        //init resources
        ResourceService.loadMovies(1, new ServerResponseListener<Movie[]>() {
            @Override
            public void onSuccess(Movie[] movies) {
                Toast.makeText(getActivity(), "Movies loaded", Toast.LENGTH_SHORT).show();

                mAdapter = new LazyListAdapter(movies, LazyList.this);
                setListAdapter(mAdapter);

                mProgress.setVisibility(View.GONE);
            }

            @Override
            public void onError(ErrorResponse error) {
                //TODO toast
                Toast.makeText(getActivity(), "Load movies error", Toast.LENGTH_SHORT).show();
                mProgress.setVisibility(View.GONE);
            }

            @Override
            public void onProgress(Integer progress) {
                //TODO progressbar?
                mProgress.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled() {
                //TODO toast?
                Toast.makeText(getActivity(), "Load movies cancelled", Toast.LENGTH_SHORT).show();
                mProgress.setVisibility(View.GONE);
            }
        });
    }

    protected void movieImageLoaded(int position, Bitmap movieImage) {
        View viewAtPosition = getListView().getChildAt(position - getListView().getFirstVisiblePosition());

        // update only if view is visible
        if (viewAtPosition != null) {
            MovieItemHolder holder = (MovieItemHolder) viewAtPosition.getTag();
            holder.setPoster(movieImage);
        }
    }
}
