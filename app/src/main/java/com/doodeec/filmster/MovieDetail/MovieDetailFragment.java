package com.doodeec.filmster.MovieDetail;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.doodeec.filmster.ApplicationState.AppState;
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

    private final View.OnClickListener mLinkBtnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(mMovie.getLink()));
            startActivity(intent);
        }
    };

    private Movie mMovie;
    private Button mLinkBtn;
    private TextView mTitle;
    private TextView mYear;
    private TextView mSynopsis;
    private TextView mAudRating;
    private TextView mCritRating;
    private TextView mCastLabel;
    private ListView mCast;
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
        mAudRating = (TextView) v.findViewById(R.id.audience_rating);
        mCritRating = (TextView) v.findViewById(R.id.critics_rating);
        mCastLabel = (TextView) v.findViewById(R.id.movie_cast_label);
        mCast = (ListView) v.findViewById(R.id.movie_cast);
        mLinkBtn = (Button) v.findViewById(R.id.movie_link);

        if (mPoster == null || mTitle == null || mYear == null || mSynopsis == null || mAudRating == null ||
                mCritRating == null || mCastLabel == null || mCast == null || mLinkBtn == null) {
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

    @Override
    public void onDetach() {
        // free listener reference first
        mLinkBtn.setOnClickListener(null);
        super.onDetach();
    }

    /**
     * Sets data from currently available Movie reference to the view
     */
    public void setData() {
        mTitle.setText(mMovie.getTitle());
        mYear.setText(String.format(YEAR, mMovie.getYear()));
        mSynopsis.setText(mMovie.getSynopsis());

        // set rating numbers
        mAudRating.setText(mMovie.getAudienceRating() != null ? String.valueOf(mMovie.getAudienceRating()) : "");
        mCritRating.setText(mMovie.getCriticsRating() != null ? String.valueOf(mMovie.getCriticsRating()) : "");

        // set cast adapter
        if (mMovie.getCast() != null && mMovie.getCast().length > 0) {
            mCast.setAdapter(new ArrayAdapter<String>(AppState.getContext(), android.R.layout.simple_list_item_1, mMovie.getCast()));
            mCast.setVisibility(View.VISIBLE);
            mCastLabel.setVisibility(View.VISIBLE);
        } else {
            mCast.setVisibility(View.GONE);
            mCastLabel.setVisibility(View.GONE);
        }

        // sets movie poster
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

        // if movie has a link, register click listener, else hide link button
        if (mMovie.getLink() != null) {
            mLinkBtn.setOnClickListener(mLinkBtnClickListener);
            mLinkBtn.setVisibility(View.VISIBLE);
        } else {
            mLinkBtn.setVisibility(View.GONE);
        }
    }
}
