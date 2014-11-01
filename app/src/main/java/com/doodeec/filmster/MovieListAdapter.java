package com.doodeec.filmster;

import android.graphics.Bitmap;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;

import com.doodeec.filmster.LazyList.LazyListAdapter;
import com.doodeec.filmster.LazyList.MovieItemHolder;
import com.doodeec.filmster.Model.Movie;
import com.doodeec.filmster.ServerCommunicator.ResourceService;
import com.doodeec.filmster.ServerCommunicator.ResponseListener.BitmapServerResponseListener;
import com.doodeec.filmster.ServerCommunicator.ServerRequest.ErrorResponse;

import java.util.List;

/**
 * Created by Dusan Doodeec Bartos on 1.11.2014.
 *
 * Implementation of lazy list adapter for movies
 *
 * @see com.doodeec.filmster.LazyList.LazyListAdapter
 */
public class MovieListAdapter extends LazyListAdapter<Movie> {

    public MovieListAdapter(List<Movie> movies, MovieListFragment list) {
        super(movies, list);
    }

    @SuppressWarnings("InflateParams")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        MovieItemHolder holder;

        // init holder pattern
        if (convertView == null) {
            v = mInflater.inflate(R.layout.movie_list_item, null);
            holder = new MovieItemHolder(v);
            v.setTag(holder);
        }

        holder = (MovieItemHolder) v.getTag();

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
                } else if (ImageCache.getBitmapFromCache(getItem(position).getThumbnail()) != null) {
                    ((MovieListFragment) mLazyList.get()).movieImageLoaded(position, ImageCache.getBitmapFromCache(getItem(position).getThumbnail()));
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
