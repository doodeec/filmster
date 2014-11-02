package com.doodeec.filmster.MovieList;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.doodeec.filmster.ApplicationState.AppState;
import com.doodeec.filmster.LazyList.LazyList;
import com.doodeec.filmster.Model.Movie;
import com.doodeec.filmster.Provider.MovieProvider;
import com.doodeec.filmster.R;
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
            Toast.makeText(getActivity(), R.string.not_connected, Toast.LENGTH_SHORT).show();
            onDataLoadingFailed(REASON_CONNECTION_LOST, null);

            // block lazy load scrolling
            setMaxDataLength(0);

            // read from DB
            mData.addAll(MovieProvider.getSavedMovies());

            // show toast if database had some data entries
            if (mData.size() > 0) {
                Toast.makeText(getActivity(), R.string.data_loaded_db, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), R.string.db_data_empty, Toast.LENGTH_SHORT).show();
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

                    // save data to DB (handles create/update)
                    MovieProvider.saveMoviesToDb(mData);
                } else {
                    onDataLoadingFailed(REASON_LIST_END, null);
                }
            }

            @Override
            public void onError(ErrorResponse error) {
                onDataLoadingFailed(LazyList.REASON_SERVER_ERROR, page);
            }

            @Override
            public void onProgress(Integer progress) {
                //TODO show progress?
            }

            @Override
            public void onCancelled() {
                onDataLoadingFailed(LazyList.REASON_REQUEST_CANCELLED, page);
            }
        });
    }

    @Override
    public void reloadData() {
        // clear database data first
        MovieProvider.clearMoviesDb();
        super.reloadData();
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
