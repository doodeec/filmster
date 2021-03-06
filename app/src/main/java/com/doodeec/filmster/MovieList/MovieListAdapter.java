package com.doodeec.filmster.MovieList;

import android.graphics.Bitmap;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.doodeec.filmster.ImageCache;
import com.doodeec.filmster.Model.Movie;
import com.doodeec.filmster.R;
import com.doodeec.filmster.ServerCommunicator.ResourceService;
import com.doodeec.filmster.ServerCommunicator.ResponseListener.BitmapServerResponseListener;
import com.doodeec.filmster.ServerCommunicator.ServerRequest.ErrorResponse;
import com.doodeec.lazylist.LazyAdapter;

import java.util.List;

/**
 * Created by Dusan Doodeec Bartos on 1.11.2014.
 *
 * Implementation of lazy list adapter for movies
 *
 * @see com.doodeec.filmster.LazyList.LazyListAdapter
 */
public class MovieListAdapter extends LazyAdapter<Movie> {

    public MovieListAdapter(List<Movie> movies, MovieListFragment list) {
        super(movies, list);
    }

    @SuppressWarnings("InflateParams")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        MovieListItemHolder holder;

        // init holder pattern
        if (convertView == null) {
            v = mInflater.inflate(R.layout.movie_list_item, null);
            holder = new MovieListItemHolder(v);
            v.setTag(holder);
        }

        holder = (MovieListItemHolder) v.getTag();

        Movie movie = getItem(position);

        // set basic movie information
        holder.setTitle(movie.getTitle());
        holder.setYear(movie.getYear());
        holder.setRating(movie.getAudienceRating());

        // older APIs aren't good with handling images (even cached) in listView, so don't display them at all
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.HONEYCOMB) {
            holder.setThumbnail(null);
        } else {
            checkImage(position);
        }

        return v;
    }

    private void checkImage(final int position) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // load image - either from cache, or load from service
                if (getItem(position).getThumbnail() == null) {
                    // poster link not available
                    Log.d("FILMSTER", "Movie thumbnail not defined: " + position);
                } else if (ImageCache.getBitmapFromCache(getItem(position).getThumbnail()) != null) {
                    // list can be garbage collected already
                    if (mLazyList.get() != null) {
                        ((MovieListFragment) mLazyList.get()).movieImageLoaded(position, ImageCache.getBitmapFromCache(getItem(position).getThumbnail()));
                    }
                } else {
                    ResourceService.loadImage(getItem(position).getThumbnail(), new BitmapServerResponseListener() {
                        @Override
                        public void onSuccess(Bitmap loadedImage) {
                            if (mLazyList.get() != null) {
                                ((MovieListFragment) mLazyList.get()).movieImageLoaded(position, loadedImage);
                            }
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
        }).start();
    }
}
