package com.doodeec.filmster.Model;

import android.test.InstrumentationTestCase;

import com.doodeec.filmster.Mock;

import org.json.JSONObject;

/**
 * Created by Dusan Doodeec Bartos on 20.10.2014.
 *
 * @see com.doodeec.filmster.Model.Movie
 */
public class MovieSpec extends InstrumentationTestCase {

    private JSONObject movieMock;
    private Movie movie;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        movieMock = Mock.REST_MOVIE();
        movie = new Movie(movieMock);
    }

    public void testMovieJSONConstructor() throws Exception {
        Movie movie = new Movie(new JSONObject());

        assertNotNull(movie);
    }

    public void testMovieCursorConstructor() throws Exception {
        //TODO test cursor constructor
    }

    public void testMovieGetters() throws Exception {
        assertEquals(movieMock.getString(MovieDefinitionKeys.KEY_ID), movie.getId());
        assertEquals(movieMock.getString(MovieDefinitionKeys.KEY_TITLE), movie.getTitle());
        assertEquals((Integer) movieMock.getInt(MovieDefinitionKeys.KEY_YEAR), movie.getYear());
        assertEquals((Integer) movieMock.getJSONObject(MovieDefinitionKeys.KEY_RATING).getInt(MovieDefinitionKeys.KEY_RATING_AUDIENCE), movie.getAudienceRating());
        assertEquals((Integer) movieMock.getJSONObject(MovieDefinitionKeys.KEY_RATING).getInt(MovieDefinitionKeys.KEY_RATING_CRITICS), movie.getCriticsRating());
        assertEquals(movieMock.getString(MovieDefinitionKeys.KEY_SYNOPSIS), movie.getSynopsis());
        assertEquals(movieMock.getJSONObject(MovieDefinitionKeys.KEY_POSTERS).getString(MovieDefinitionKeys.KEY_POSTER_THUMBNAIL), movie.getPoster());
        assertEquals(movieMock.getJSONObject(MovieDefinitionKeys.KEY_LINKS).getString(MovieDefinitionKeys.KEY_LINK_ALTERNATE), movie.getLink());
    }
}