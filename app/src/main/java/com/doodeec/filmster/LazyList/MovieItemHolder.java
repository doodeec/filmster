package com.doodeec.filmster.LazyList;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.doodeec.filmster.Helper;
import com.doodeec.filmster.R;

/**
 * Created by Dusan Doodeec Bartos on 27.10.2014.
 *
 * Movie item - holder pattern
 */
public class MovieItemHolder {

    private TextView mTitleView;
    private TextView mYearView;
    private TextView mRatingView;
    private ImageView mPosterView;

    public MovieItemHolder(View view) {
        mTitleView = (TextView) view.findViewById(R.id.movie_title);
        mYearView = (TextView) view.findViewById(R.id.movie_year);
        mRatingView = (TextView) view.findViewById(R.id.movie_rating);
        mPosterView = (ImageView) view.findViewById(R.id.movie_poster);

        if (mTitleView == null || mYearView == null || mRatingView == null || mPosterView == null) {
            throw new AssertionError("Movie holder does not have required structure");
        }
    }

    public void setTitle(String title) {
        mTitleView.setText(title);
    }

    public void setYear(Integer year) {
        if (year != null) {
            mYearView.setText(String.valueOf(year));
        }
    }

    public void setRating(Integer rating) {
        if (rating != null) {
            mRatingView.setText(String.format(Helper.getString(R.string.movie_rating), rating));
            mRatingView.setTextColor(getRatingColor(rating));
        }
    }

    public void setPoster(Bitmap poster) {
        if (poster != null) {
            mPosterView.setImageBitmap(poster);
            mPosterView.setVisibility(View.VISIBLE);
        } else {
            mPosterView.setVisibility(View.GONE);
        }
    }

    /**
     * Color of rating number adapts to its value, to be visually marked in the list
     * 0  - 19 %   - red (bullshit)
     * 20 - 39 %   - orange (wtf)
     * 40 - 79 %   - blue (not bad)
     * 80 - 100 %  - green (blockbuster)
     *
     * @param rating rating in percent
     * @return color resource id
     */
    private int getRatingColor(int rating) {
        switch ((int) Math.floor(rating / 20)) {
            case 0:
                return R.color.ruby_deep;
            case 1:
                return R.color.amber_deep;
            case 4:
                return R.color.emerald_deep;
            case 5:
                return R.color.emerald;
            default:
                return R.color.azure_deep;
        }
    }
}
