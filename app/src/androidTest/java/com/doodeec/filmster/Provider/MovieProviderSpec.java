package com.doodeec.filmster.Provider;

import android.test.AndroidTestCase;

import com.doodeec.filmster.Mock;
import com.doodeec.filmster.Model.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dusan Doodeec Bartos on 20.10.2014.
 *
 * @see com.doodeec.filmster.Provider.MovieProvider
 */
public class MovieProviderSpec extends AndroidTestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        MovieProvider.clearMoviesDb();

        List<Movie> movies = new ArrayList<Movie>();
        movies.add(new Movie(Mock.REST_MOVIE()));

        MovieProvider.saveMoviesToDb(movies);
    }

    public void testGetMovies() throws Exception {
        assertNotNull(MovieProvider.getSavedMovies());
        assertEquals(1, MovieProvider.getSavedMovies().size());
    }

    public void testClearMovies() throws Exception {
        MovieProvider.clearMoviesDb();

        assertNotNull(MovieProvider.getSavedMovies());
        assertEquals(0, MovieProvider.getSavedMovies().size());
    }
}
