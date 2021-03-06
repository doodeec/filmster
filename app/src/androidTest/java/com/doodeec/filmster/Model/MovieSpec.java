package com.doodeec.filmster.Model;

import android.test.InstrumentationTestCase;
import android.test.mock.MockCursor;

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

        assertNotNull(movie);
    }

    public void testMovieCursorConstructor() throws Exception {
        MockCursor cursor = new MockCursor() {
            @Override
            public int getColumnIndex(String columnName) {
                return 1;
            }

            @Override
            public String getString(int columnIndex) {
                return "MockCursorString";
            }

            @Override
            public boolean isNull(int columnIndex) {
                return false;
            }

            @Override
            public int getInt(int columnIndex) {
                return 1;
            }
        };
        Movie movie = new Movie(cursor);
        assertNotNull(movie);
    }

    public void testMovieGetters() throws Exception {
        assertEquals(movieMock.getString(MovieDefinitionKeys.KEY_ID), movie.getId());
        assertEquals(movieMock.getString(MovieDefinitionKeys.KEY_TITLE), movie.getTitle());
        assertEquals((Integer) movieMock.getInt(MovieDefinitionKeys.KEY_YEAR), movie.getYear());
        assertEquals((Integer) movieMock.getJSONObject(MovieDefinitionKeys.KEY_RATING).getInt(MovieDefinitionKeys.KEY_RATING_AUDIENCE), movie.getAudienceRating());
        assertEquals((Integer) movieMock.getJSONObject(MovieDefinitionKeys.KEY_RATING).getInt(MovieDefinitionKeys.KEY_RATING_CRITICS), movie.getCriticsRating());
        assertEquals(movieMock.getString(MovieDefinitionKeys.KEY_SYNOPSIS), movie.getSynopsis());
        assertEquals(movieMock.getJSONObject(MovieDefinitionKeys.KEY_POSTERS).getString(MovieDefinitionKeys.KEY_POSTER_THUMBNAIL), movie.getThumbnail());
        assertEquals(movieMock.getJSONObject(MovieDefinitionKeys.KEY_POSTERS).getString(MovieDefinitionKeys.KEY_POSTER_DETAIL), movie.getPoster());
        assertEquals(movieMock.getJSONObject(MovieDefinitionKeys.KEY_LINKS).getString(MovieDefinitionKeys.KEY_LINK_IMDB), movie.getLink());
    }
}