package com.doodeec.filmster.LazyList;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.doodeec.filmster.AppState;
import com.doodeec.filmster.ImageCache;
import com.doodeec.filmster.Model.Movie;
import com.doodeec.filmster.R;
import com.doodeec.filmster.ServerCommunicator.ResourceService;
import com.doodeec.filmster.ServerCommunicator.ResponseListener.BitmapServerResponseListener;
import com.doodeec.filmster.ServerCommunicator.ServerRequest.ErrorResponse;

import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Dusan Doodeec Bartos on 27.10.2014.
 *
 * Lazy loading list adapter
 */
public class LazyListAdapter extends BaseAdapter {

    private List<Movie> mMovies;
    private LayoutInflater mInflater;
    private WeakReference<LazyList> mLazyList;

    public LazyListAdapter(Movie[] movies, LazyList list) {
        mMovies = Arrays.asList(movies);
        mLazyList = new WeakReference<LazyList>(list);

        mInflater = (LayoutInflater) AppState.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

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

        // load image - either from cache, or load from service
        if (movie.getPoster() == null) {
            // poster link not available
        } else if (ImageCache.getBitmapFromCache(movie.getPoster()) != null) {
            holder.setPoster(ImageCache.getBitmapFromCache(movie.getPoster()));
        } else {
            ResourceService.loadImage(movie.getPoster(), new BitmapServerResponseListener() {
                @Override
                public void onSuccess(Bitmap loadedImage) {
                    if (mLazyList.get() != null) {
                        mLazyList.get().movieImageLoaded(position, loadedImage);
                    }
                }

                @Override
                public void onError(ErrorResponse error) {}

                @Override
                public void onProgress(Integer progress) {}

                @Override
                public void onCancelled() {}
            });
        }

        return v;
    }

    @Override
    public int getCount() {
        return mMovies.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Movie getItem(int position) {
        return mMovies.get(position);
    }
}
