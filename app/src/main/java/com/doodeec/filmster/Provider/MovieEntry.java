package com.doodeec.filmster.Provider;

/**
 * Created by Dusan Doodeec Bartos on 25.10.2014.
 *
 * Movie DB entry
 */
public class MovieEntry {
    public static final String ID_KEY = "id";
    public static final String TITLE_KEY = "title";
    public static final String SYNOPSIS_KEY = "synopsis";
    public static final String THUMBNAIL_KEY = "thumbnail";
    public static final String POSTER_KEY = "poster";
    public static final String LINK_KEY = "link";
    public static final String YEAR_KEY = "year";
    public static final String RATING_AUDIENCE_KEY = "audRat";
    public static final String RATING_CRITICS_KEY = "critRat";
    public static final String CAST_KEY = "cast";

    public static final String DICTIONARY_TABLE_NAME = "filmDictionary";
    public static final String DICTIONARY_TABLE_CREATE =
            "CREATE TABLE " + DICTIONARY_TABLE_NAME + " (" +
                    ID_KEY + DbHelper.TYPE_TEXT + " PRIMARY KEY, " +
                    TITLE_KEY + DbHelper.TYPE_TEXT + ", " +
                    SYNOPSIS_KEY + DbHelper.TYPE_TEXT + ", " +
                    THUMBNAIL_KEY + DbHelper.TYPE_TEXT + ", " +
                    POSTER_KEY + DbHelper.TYPE_TEXT + ", " +
                    LINK_KEY + DbHelper.TYPE_TEXT + ", " +
                    YEAR_KEY + DbHelper.TYPE_INT + ", " +
                    RATING_AUDIENCE_KEY + DbHelper.TYPE_INT + ", " +
                    RATING_CRITICS_KEY + DbHelper.TYPE_INT + ", " +
                    CAST_KEY + DbHelper.TYPE_TEXT + ");";

    public static final String DICTIONARY_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DICTIONARY_TABLE_NAME;
}
