package com.doodeec.filmster.MovieList;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.doodeec.filmster.ApplicationState.AppState;
import com.doodeec.filmster.LazyList.LazyList;
import com.doodeec.filmster.Model.Movie;
import com.doodeec.filmster.Provider.MovieProvider;
import com.doodeec.filmster.ServerCommunicator.ResourceService;
import com.doodeec.filmster.ServerCommunicator.ResponseListener.ServerResponseListener;
import com.doodeec.filmster.ServerCommunicator.ServerRequest.ErrorResponse;

import java.util.Arrays;

/**
 * Created by dbartos on 31.10.2014.
 *
 * Implementation of LazyList Class
 */
public class MovieListFragment extends LazyList<Movie> {

    @Override
    protected void initAdapter() {
        mAdapter = new MovieListAdapter(mData, this);
        initClickListener();

        if (AppState.getIsApplicationOnline()) {
            loadPage(1);
        } else {
            Toast.makeText(getActivity(), "Internet connection unavailable", Toast.LENGTH_SHORT).show();
            onDataLoadingFailed(REASON_CONNECTION_LOST);

            // block lazy load scrolling
            setMaxDataLength(0);

            // read from DB
            mData.addAll(MovieProvider.getSavedMovies());

            if (mData.size() > 0) {
                Toast.makeText(getActivity(), "Movies loaded from database", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * Fires when movie poster was loaded asynchronously and is ready to be set to the corresponding view
     *
     * @param position   view position
     * @param movieImage loaded image
     */
    public void movieImageLoaded(final int position, final Bitmap movieImage) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                View viewAtPosition = getListView().getChildAt(position - getListView().getFirstVisiblePosition());

                // update only if view is visible
                if (viewAtPosition != null) {
                    MovieListItemHolder holder = (MovieListItemHolder) viewAtPosition.getTag();
                    holder.setThumbnail(movieImage);
                }
            }
        });
    }

    /**
     * Loads data for specific page
     *
     * @param page page to load
     */
    @Override
    protected synchronized void loadPage(final int page) {
        super.loadPage(page);

        ResourceService.loadMovies(page, new ServerResponseListener<Movie[]>() {
            @Override
            public void onSuccess(Movie[] movies) {
                if (movies.length > 0) {
                    mData.addAll(Arrays.asList(movies));
                    onDataLoadingCompleted(page);

                    //save to DB
                    MovieProvider.saveMoviesToDb(mData);
                } else {
                    onDataLoadingFailed(REASON_LIST_END);
                }
            }

            @Override
            public void onError(ErrorResponse error) {
                onDataLoadingFailed(LazyList.REASON_SERVER_ERROR);
            }

            @Override
            public void onProgress(Integer progress) {
                //TODO progressbar?
            }

            @Override
            public void onCancelled() {
                onDataLoadingFailed(LazyList.REASON_REQUEST_CANCELLED);
            }
        });
    }

    /**
     * Initializes item click listener
     */
    private void initClickListener() {
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((MovieListActivityInterface) getActivity()).onDisplayDetail((Movie) mAdapter.getItem(position));
            }
        });
    }
}
