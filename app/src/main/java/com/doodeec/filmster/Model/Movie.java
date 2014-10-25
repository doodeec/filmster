package com.doodeec.filmster.Model;

import android.database.Cursor;
import android.util.Log;

import com.doodeec.filmster.Provider.MovieEntry;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Dusan Doodeec Bartos on 20.10.2014.
 *
 * Movie object
 */
public class Movie extends JSONParser {

    private String mId;
    private String mTitle;
    private String mSynopsis;
    private String mPoster;
    private String mLink;
    private Integer mYear;
    private Integer mAudienceRating;
    private Integer mCriticsRating;

    /**
     * Constructs Movie object from entry in DB
     *
     * @param cursor cursor to movie entry
     */
    public Movie(Cursor cursor) {
        mId = cursor.getString(cursor.getColumnIndex(MovieEntry.ID_KEY));
        mTitle = cursor.getString(cursor.getColumnIndex(MovieEntry.TITLE_KEY));
        mSynopsis = cursor.getString(cursor.getColumnIndex(MovieEntry.SYNOPSIS_KEY));
        mPoster = cursor.getString(cursor.getColumnIndex(MovieEntry.POSTER_KEY));
        mLink = cursor.getString(cursor.getColumnIndex(MovieEntry.LINK_KEY));
        mYear = cursor.getInt(cursor.getColumnIndex(MovieEntry.YEAR_KEY));
        mAudienceRating = cursor.getInt(cursor.getColumnIndex(MovieEntry.RATING_AUDIENCE_KEY));
        mCriticsRating = cursor.getInt(cursor.getColumnIndex(MovieEntry.RATING_CRITICS_KEY));
    }

    /**
     * Constructs Movie object from JSON definition (from API)
     *
     * @param jsonObject movie definition
     */
    public Movie(JSONObject jsonObject) {
        try {
            mId = jsonObject.getString(MovieDefinitionKeys.KEY_ID);
            mTitle = jsonObject.getString(MovieDefinitionKeys.KEY_TITLE);
            mSynopsis = jsonObject.getString(MovieDefinitionKeys.KEY_SYNOPSIS);
            mYear = jsonObject.getInt(MovieDefinitionKeys.KEY_YEAR);
            mAudienceRating = jsonObject.getInt(MovieDefinitionKeys.KEY_RATING_AUDIENCE);
            mCriticsRating = jsonObject.getInt(MovieDefinitionKeys.KEY_RATING_CRITICS);

            JSONObject postersObject = jsonObject.getJSONObject(MovieDefinitionKeys.KEY_POSTERS);
            mPoster = postersObject.getString(MovieDefinitionKeys.KEY_POSTER_THUMBNAIL);

            JSONObject linksObject = jsonObject.getJSONObject(MovieDefinitionKeys.KEY_LINKS);
            mLink = linksObject.getString(MovieDefinitionKeys.KEY_LINK_ALTERNATE);
        } catch (JSONException e) {
            Log.e("FILMSTER", "Movie definition not valid " + e.getMessage());
        }
    }

    public String getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getSynopsis() {
        return mSynopsis;
    }

    public String getPoster() {
        return mPoster;
    }

    public String getLink() {
        return mLink;
    }

    public Integer getYear() {
        return mYear;
    }

    public Integer getAudienceRating() {
        return mAudienceRating;
    }

    public Integer getCriticsRating() {
        return mCriticsRating;
    }
}
