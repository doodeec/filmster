package com.doodeec.filmster.MovieList;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.test.AndroidTestCase;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.doodeec.filmster.R;

/**
 * Created by Dusan Doodeec Bartos on 1.11.2014.
 *
 * @see com.doodeec.filmster.MovieList.MovieListItemHolder
 */
public class MovieListItemHolderSpec extends AndroidTestCase {

    private View view;
    private MovieListItemHolder holder;

    @SuppressWarnings("InflateParams")
    @Override
    protected void setUp() throws Exception {
        super.setUp();

        view = LayoutInflater.from(getContext()).inflate(R.layout.movie_list_item, null);
        holder = new MovieListItemHolder(view);
    }

    public void testSetTitle() throws Exception {
        holder.setTitle("firstMovieTitle");
        assertEquals("firstMovieTitle", ((TextView) view.findViewById(R.id.movie_title)).getText());

        holder.setTitle("secondMovieTitle");
        assertEquals("secondMovieTitle", ((TextView) view.findViewById(R.id.movie_title)).getText());
    }

    public void testSetYear() throws Exception {
        holder.setYear(2014);
        assertEquals("2014", ((TextView) view.findViewById(R.id.movie_year)).getText());

        holder.setYear(1990);
        assertEquals("1990", ((TextView) view.findViewById(R.id.movie_year)).getText());

        holder.setYear(null);
        assertEquals("", ((TextView) view.findViewById(R.id.movie_year)).getText());
    }

    public void testSetRating() throws Exception {
        holder.setRating(80);
        assertEquals("80 %", ((TextView) view.findViewById(R.id.movie_rating)).getText());

        holder.setRating(100);
        assertEquals("100 %", ((TextView) view.findViewById(R.id.movie_rating)).getText());

        holder.setRating(null);
        assertEquals("", ((TextView) view.findViewById(R.id.movie_rating)).getText());
    }

    public void testRatingColor() throws Exception {
        holder.setRating(0);
        assertEquals(R.color.ruby_deep,
                ((TextView) view.findViewById(R.id.movie_rating)).getCurrentTextColor());

        holder.setRating(19);
        assertEquals(R.color.amber_deep,
                ((TextView) view.findViewById(R.id.movie_rating)).getCurrentTextColor());

        holder.setRating(60);
        assertEquals(R.color.azure_deep,
                ((TextView) view.findViewById(R.id.movie_rating)).getCurrentTextColor());

        holder.setRating(80);
        assertEquals(R.color.emerald_deep,
                ((TextView) view.findViewById(R.id.movie_rating)).getCurrentTextColor());

        holder.setRating(100);
        assertEquals(R.color.emerald,
                ((TextView) view.findViewById(R.id.movie_rating)).getCurrentTextColor());
    }

    public void testSetThumbnail() throws Exception {
        Bitmap bitmap = Bitmap.createBitmap(50,100, Bitmap.Config.ARGB_8888);
        Drawable oldDrawable = ((ImageView) view.findViewById(R.id.movie_poster)).getDrawable();


        holder.setThumbnail(bitmap);
        assertNotSame(oldDrawable.getConstantState(),
                ((ImageView) view.findViewById(R.id.movie_poster)).getDrawable().getConstantState());
    }
}
