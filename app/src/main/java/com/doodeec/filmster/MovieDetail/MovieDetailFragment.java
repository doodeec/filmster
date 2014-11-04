package com.doodeec.filmster.MovieDetail;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.doodeec.filmster.ImageCache;
import com.doodeec.filmster.Model.Movie;
import com.doodeec.filmster.Provider.MovieProvider;
import com.doodeec.filmster.R;
import com.doodeec.filmster.ServerCommunicator.ResourceService;
import com.doodeec.filmster.ServerCommunicator.ResponseListener.BitmapServerResponseListener;
import com.doodeec.filmster.ServerCommunicator.ServerRequest.ErrorResponse;

/**
 * Created by dbartos on 31.10.2014.
 *
 * Movie detail fragment
 */
public class MovieDetailFragment extends Fragment {

    private static final String YEAR = "(%d)";
    private static final String DETAIL_ID_BUNDLE = "Movie_detail";

    private Movie mMovie;
    private TextView mTitle;
    private TextView mYear;
    private TextView mSynopsis;
    private ImageView mPoster;

    public void setMovie(Movie movie) {
        mMovie = movie;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.movie_detail, container, false);
        mPoster = (ImageView) v.findViewById(R.id.movie_poster);
        mTitle = (TextView) v.findViewById(R.id.movie_title);
        mYear = (TextView) v.findViewById(R.id.movie_year);
        mSynopsis = (TextView) v.findViewById(R.id.movie_synopsis);

        if (mPoster == null || mTitle == null || mYear == null || mSynopsis == null) {
            throw new AssertionError("Movie detail has invalid layout defined");
        }

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // if detail was opened before, restore the save movie to the view
        if (savedInstanceState != null) {
            mMovie = MovieProvider.getMovies().get(savedInstanceState.getInt(DETAIL_ID_BUNDLE));
        }

        // when creating detail in separate new fragment, we can automatically set data to view
        if (mMovie != null) {
            setData();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(DETAIL_ID_BUNDLE, MovieProvider.getMovies().indexOf(mMovie));
        super.onSaveInstanceState(outState);
    }

    /**
     * Sets data from currently available Movie reference to the view
     */
    public void setData() {
        mTitle.setText(mMovie.getTitle());
        mYear.setText(String.format(YEAR, mMovie.getYear()));
        mSynopsis.setText(mMovie.getSynopsis());

        if (mMovie.getPoster() == null) {
            // poster link not available
            Log.d("FILMSTER", "Movie does not have any URL link to poster defined");
        } else if (ImageCache.getBitmapFromCache(mMovie.getPoster()) != null) {
            // poster bitmap available in LRUCache
            mPoster.setImageBitmap(ImageCache.getBitmapFromCache(mMovie.getPoster()));
        } else {
            // poster bitmap not available, try to download it
            ResourceService.loadImage(mMovie.getPoster(), new BitmapServerResponseListener() {
                @Override
                public void onSuccess(Bitmap loadedImage) {
                    mPoster.setImageBitmap(loadedImage);
                }

                @Override
                public void onError(ErrorResponse error) {
                }

                @Override
                public void onProgress(Integer progress) {
                }

                @Override
                public void onCancelled() {
                }
            });
        }
    }
}
