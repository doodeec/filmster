package com.doodeec.filmster.Provider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.doodeec.filmster.Model.Movie;

import org.json.JSONArray;

/**
 * Created by Dusan Doodeec Bartos on 25.10.2014.
 *
 * Helper for creating DB tables
 */
public class DbHelper extends SQLiteOpenHelper {

    public static final String TYPE_TEXT = " TEXT";
    public static final String TYPE_INT = " INTEGER";

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "filmster.db";

    DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MovieEntry.DICTIONARY_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply discard the data and create new data
        db.execSQL(MovieEntry.DICTIONARY_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    /**
     * Saves movie to database
     * @param db database reference
     * @param movie movie to save
     */
    public void saveMovie(SQLiteDatabase db, Movie movie) {
        ContentValues values = new ContentValues();
        values.put(MovieEntry.ID_KEY, movie.getId());
        values.put(MovieEntry.TITLE_KEY, movie.getTitle());
        values.put(MovieEntry.SYNOPSIS_KEY, movie.getSynopsis());
        values.put(MovieEntry.POSTER_KEY, movie.getPoster());
        values.put(MovieEntry.THUMBNAIL_KEY, movie.getThumbnail());
        values.put(MovieEntry.LINK_KEY, movie.getLink());
        values.put(MovieEntry.YEAR_KEY, movie.getYear());
        values.put(MovieEntry.RATING_AUDIENCE_KEY, movie.getAudienceRating());
        values.put(MovieEntry.RATING_CRITICS_KEY, movie.getCriticsRating());

        JSONArray cast = new JSONArray();
        for (String actor: movie.getCast()) {
            cast.put(actor);
        }
        values.put(MovieEntry.CAST_KEY, cast.toString());

        String[] stlpec = { MovieEntry.ID_KEY } ;
        String[] args = { movie.getId() } ;

        Cursor c = db.query(MovieEntry.DICTIONARY_TABLE_NAME, stlpec , "id = ?", args, null, null, null);

        if(c.moveToFirst()) {
            db.update(MovieEntry.DICTIONARY_TABLE_NAME, values, "id = ?", args);
            Log.d("FILMSTER", "Updated movie: " + movie.getId());
        } else {
            db.insert(MovieEntry.DICTIONARY_TABLE_NAME, null, values);
            Log.d("FILMSTER", "Inserted movie: " + movie.getId());
        }

        c.close();
    }
}
