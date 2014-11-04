package com.doodeec.filmster.Provider;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.doodeec.filmster.ApplicationState.AppState;
import com.doodeec.filmster.Model.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dusan Doodeec Bartos on 20.10.2014.
 *
 * Movie Provider
 * Saves and loads movies to/from database
 */
public class MovieProvider {

    private static DbHelper mDbHelper;
    private static SQLiteDatabase db;
    private static List<Movie> mMovieList;

    /**
     * Opens database if not opened
     */
    private static void openDbConnection() {
        if (db == null || !db.isOpen()) {
            mDbHelper = new DbHelper(AppState.getContext());
            db = mDbHelper.getReadableDatabase();
        }
    }

    /**
     * Reads movies - either saved reference, or reads the list from database
     * @return available list of movies
     */
    public static List<Movie> getMovies() {
        if (mMovieList == null || mMovieList.size() == 0) {
            return getSavedMovies();
        } else {
            Log.d("FILMSTER", "Movies loaded from memory: " + mMovieList.size());
            return mMovieList;
        }
    }

    /**
     * Reads movies from database
     * @return movie list
     */
    public static List<Movie> getSavedMovies() {
        openDbConnection();
        List<Movie> movies;

        try {
            Cursor cursor = db.query(MovieEntry.DICTIONARY_TABLE_NAME, null, null, null, null, null, null);
            movies = new ArrayList<Movie>();

            while (cursor.moveToNext()) {
                movies.add(new Movie(cursor));
            }

            Log.d("FILMSTER", "Movies loaded from DB: " + movies.size());
        } finally {
            if (db != null) db.close();
        }
        return movies;
    }

    /**
     * Deletes movie list from database
     */
    public static void clearMoviesDb() {
        openDbConnection();
        mDbHelper.onUpgrade(db, 0, 0);
        Log.d("FILMSTER", "Database data cleared");
        mMovieList.clear();
    }

    /**
     * Saves movie list to database
     * @param movies list to save
     */
    public static void saveMoviesToDb(List<Movie> movies) {
        openDbConnection();

        try {
            db.beginTransaction();

            for (Movie m : movies) {
                m.saveToDb(db);
            }

            db.setTransactionSuccessful();
        } catch (SQLiteException e) {
            Log.e("FILMSTER", "Database error: " + e.getMessage());
        } finally {
            db.endTransaction();
            mMovieList = movies;
        }
    }
}
