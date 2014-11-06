package com.doodeec.filmster.MovieList;

import android.test.AndroidTestCase;
import android.view.View;
import android.widget.TextView;

import com.doodeec.filmster.Helper;
import com.doodeec.filmster.Mock;
import com.doodeec.filmster.Model.Movie;
import com.doodeec.filmster.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dusan Doodeec Bartos on 1.11.2014.
 *
 * @see com.doodeec.filmster.MovieList.MovieListAdapter
 */
public class MovieListAdapterSpec extends AndroidTestCase {
    private MovieListAdapter mAdapter;

    private Movie firstMovie;
    private Movie secondMovie;
    private Movie thirdMovie;

    public MovieListAdapterSpec() {
        super();
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        JSONObject listDefinition = Mock.REST_MOVIE_LIST();
        JSONArray list = listDefinition.getJSONArray("movies");

        List<Movie> movies = new ArrayList<Movie>();
        firstMovie = new Movie(list.getJSONObject(0));
        secondMovie = new Movie(list.getJSONObject(1));
        thirdMovie = new Movie(list.getJSONObject(2));

        movies.add(firstMovie);
        movies.add(secondMovie);
        movies.add(thirdMovie);

        mAdapter = new MovieListAdapter(movies, null);
    }

    public void testGetView() throws Exception {
        View firstView = mAdapter.getView(0, null, null);

        assertNotNull(firstView);
        assertNotNull(firstView.getTag());
        assertTrue(firstView.getTag() instanceof MovieListItemHolder);

        assertEquals(firstMovie.getTitle(), ((TextView) firstView.findViewById(R.id.movie_title)).getText());
        assertEquals(String.valueOf(firstMovie.getYear()),
                ((TextView) firstView.findViewById(R.id.movie_year)).getText());
        assertEquals(String.format(Helper.getString(R.string.movie_rating), firstMovie.getAudienceRating()),
                ((TextView) firstView.findViewById(R.id.movie_rating)).getText());

        View secondView = mAdapter.getView(1, null, null);

        assertNotNull(secondView);

        assertEquals(secondMovie.getTitle(), ((TextView) secondView.findViewById(R.id.movie_title)).getText());
        assertEquals(String.valueOf(secondMovie.getYear()),
                ((TextView) secondView.findViewById(R.id.movie_year)).getText());
        assertEquals(String.format(Helper.getString(R.string.movie_rating), secondMovie.getAudienceRating()),
                ((TextView) secondView.findViewById(R.id.movie_rating)).getText());

        View thirdView = mAdapter.getView(2, null, null);

        assertNotNull(thirdView);

        assertEquals(thirdMovie.getTitle(), ((TextView) thirdView.findViewById(R.id.movie_title)).getText());
        assertEquals(String.valueOf(thirdMovie.getYear()),
                ((TextView) thirdView.findViewById(R.id.movie_year)).getText());
        assertEquals(String.format(Helper.getString(R.string.movie_rating), thirdMovie.getAudienceRating()),
                ((TextView) thirdView.findViewById(R.id.movie_rating)).getText());
    }

    public void testRecycleView() throws Exception {
        View firstView = mAdapter.getView(0, null, null);
        View secondView = mAdapter.getView(1, firstView, null);

        assertNotNull(secondView);

        assertEquals(secondMovie.getTitle(), ((TextView) secondView.findViewById(R.id.movie_title)).getText());
        assertEquals(String.valueOf(secondMovie.getYear()),
                ((TextView) secondView.findViewById(R.id.movie_year)).getText());
        assertEquals(String.format(Helper.getString(R.string.movie_rating), secondMovie.getAudienceRating()),
                ((TextView) secondView.findViewById(R.id.movie_rating)).getText());
    }

    public void testGetCount() throws Exception {
        assertEquals(3, mAdapter.getCount());
    }

    public void testGetItemId() throws Exception {
        assertEquals(2, mAdapter.getItemId(2));
    }
}
