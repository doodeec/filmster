package com.doodeec.filmster.LazyList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.doodeec.filmster.AppState;
import com.doodeec.filmster.Model.Movie;
import com.doodeec.filmster.R;

import java.util.List;

/**
 * Created by Dusan Doodeec Bartos on 27.10.2014.
 */
public class LazyListAdapter extends BaseAdapter {

    private List<Movie> mMovies;
    private LayoutInflater mInflater;

    public LazyListAdapter(Movie[] movies) {
        mInflater = (LayoutInflater) AppState.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        MovieItemHolder holder;

        if (convertView == null) {
            v = mInflater.inflate(R.layout.movie_list_item, null);

            holder = new MovieItemHolder(v);
            v.setTag(holder);
        }

        holder = (MovieItemHolder) v.getTag();

        Movie movie = getItem(position);

        holder.setTitle(movie.getTitle());
        holder.setYear(movie.getYear());
        holder.setRating(movie.getAudienceRating());
        //TODO
//        holder.setPoster();

        return null;
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
