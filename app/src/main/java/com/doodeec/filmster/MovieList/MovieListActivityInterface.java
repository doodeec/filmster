package com.doodeec.filmster.MovieList;

import com.doodeec.filmster.Model.Movie;

/**
 * Created by Dusan Doodeec Bartos on 1.11.2014.
 *
 * Movie List interface used to propagate Detail opening event to Activity
 */
public interface MovieListActivityInterface {

    /**
     * Fired when Movie List asks for Movie detail fragment
     * @param movie movie to display in detail
     */
    public void onDisplayDetail(Movie movie);
}
