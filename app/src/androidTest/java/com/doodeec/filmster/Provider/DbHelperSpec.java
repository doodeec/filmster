package com.doodeec.filmster.Provider;

import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

import com.doodeec.filmster.Mock;
import com.doodeec.filmster.Model.Movie;

/**
 * Created by Dusan Doodeec Bartos on 26.10.2014.
 *
 * @see com.doodeec.filmster.Provider.DbHelper
 */
public class DbHelperSpec extends AndroidTestCase {
    private DbHelper mHelper;
    private SQLiteDatabase db;

    public void setUp() {
        RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), "test_");
        mHelper = new DbHelper(context);
        db = mHelper.getReadableDatabase();
    }

    public void testAddEntry() {
        Movie movie = new Movie(Mock.REST_MOVIE());
        mHelper.saveMovie(db, movie);
    }

    public void tearDown() throws Exception {
        db.close();
        super.tearDown();
    }
}
