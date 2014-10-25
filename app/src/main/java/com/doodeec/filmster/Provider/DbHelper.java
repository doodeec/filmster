package com.doodeec.filmster.Provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Dusan Doodeec Bartos on 25.10.2014.
 *
 * Helper for creating DB tables
 */
public class DbHelper extends SQLiteOpenHelper {

    public static final String TYPE_TEXT = " TEXT";
    public static final String TYPE_INT = " INTEGER";

    private static final int DATABASE_VERSION = 1;
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
}
