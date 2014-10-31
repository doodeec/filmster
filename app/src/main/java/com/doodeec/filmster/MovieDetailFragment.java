package com.doodeec.filmster;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.doodeec.filmster.Model.Movie;
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
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mTitle.setText(mMovie.getTitle());
        mYear.setText(String.format(YEAR, mMovie.getYear()));
        mSynopsis.setText(mMovie.getSynopsis());

        if (mMovie.getPoster() == null) {
            // poster link not available
        } else if (ImageCache.getBitmapFromCache(mMovie.getPoster()) != null) {
            mPoster.setImageBitmap(ImageCache.getBitmapFromCache(mMovie.getPoster()));
        } else {
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
