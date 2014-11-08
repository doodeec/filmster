package com.doodeec.filmster.Model;

import android.database.Cursor;
import android.util.Log;

import com.doodeec.filmster.Provider.MovieEntry;

import org.json.JSONArray;
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
    private String mThumbnail;
    private String mPoster;
    private String mLink;
    private String[] mCast;
    private Integer mYear = null;
    private Integer mAudienceRating = null;
    private Integer mCriticsRating = null;

    /**
     * Constructs Movie object from entry in DB
     *
     * @param cursor cursor to movie entry
     */
    public Movie(Cursor cursor) {
        mId = cursor.getString(cursor.getColumnIndex(MovieEntry.ID_KEY));
        mTitle = cursor.getString(cursor.getColumnIndex(MovieEntry.TITLE_KEY));
        mSynopsis = cursor.getString(cursor.getColumnIndex(MovieEntry.SYNOPSIS_KEY));
        mThumbnail = cursor.getString(cursor.getColumnIndex(MovieEntry.THUMBNAIL_KEY));
        mPoster = cursor.getString(cursor.getColumnIndex(MovieEntry.POSTER_KEY));
        mLink = cursor.getString(cursor.getColumnIndex(MovieEntry.LINK_KEY));
        if (!cursor.isNull(cursor.getColumnIndex(MovieEntry.YEAR_KEY))) {
            mYear = cursor.getInt(cursor.getColumnIndex(MovieEntry.YEAR_KEY));
        }
        if (!cursor.isNull(cursor.getColumnIndex(MovieEntry.RATING_AUDIENCE_KEY))) {
            mAudienceRating = cursor.getInt(cursor.getColumnIndex(MovieEntry.RATING_AUDIENCE_KEY));
        }
        if (!cursor.isNull(cursor.getColumnIndex(MovieEntry.RATING_CRITICS_KEY))) {
            mCriticsRating = cursor.getInt(cursor.getColumnIndex(MovieEntry.RATING_CRITICS_KEY));
        }

        try {
            JSONArray castArray = new JSONArray(cursor.getString(cursor.getColumnIndex(MovieEntry.CAST_KEY)));
            mCast = new String[castArray.length()];
            for (int i = 0; i < castArray.length(); i++) {
                mCast[i] = castArray.getString(i);
            }
        } catch (JSONException e) {
            Log.e("FILMSTER", "Invalid cast definition: " + e.getMessage());
        }
    }

    /**
     * Constructs Movie object from JSON definition (from API)
     *
     * @param jsonObject movie definition
     */
    public Movie(JSONObject jsonObject) {
        mId = getStringForKey(jsonObject, MovieDefinitionKeys.KEY_ID);
        mTitle = getStringForKey(jsonObject, MovieDefinitionKeys.KEY_TITLE);
        mSynopsis = getStringForKey(jsonObject, MovieDefinitionKeys.KEY_SYNOPSIS);
        mYear = getIntForKey(jsonObject, MovieDefinitionKeys.KEY_YEAR);

        JSONObject ratings = getObjectForKey(jsonObject, MovieDefinitionKeys.KEY_RATING);
        if (ratings.has(MovieDefinitionKeys.KEY_RATING_AUDIENCE)) {
            mAudienceRating = getIntForKey(ratings, MovieDefinitionKeys.KEY_RATING_AUDIENCE);
        }
        if (ratings.has(MovieDefinitionKeys.KEY_RATING_CRITICS)) {
            mCriticsRating = getIntForKey(ratings, MovieDefinitionKeys.KEY_RATING_CRITICS);
        }

        JSONObject postersObject = getObjectForKey(jsonObject, MovieDefinitionKeys.KEY_POSTERS);
        mThumbnail = getStringForKey(postersObject, MovieDefinitionKeys.KEY_POSTER_THUMBNAIL);
        mPoster = getStringForKey(postersObject, MovieDefinitionKeys.KEY_POSTER_DETAIL);

        JSONObject linksObject = getObjectForKey(jsonObject, MovieDefinitionKeys.KEY_LINKS);
        mLink = getStringForKey(linksObject, MovieDefinitionKeys.KEY_LINK_IMDB);

        JSONArray castArray = getArrayForKey(jsonObject, MovieDefinitionKeys.KEY_CAST);
        mCast = new String[castArray.length()];
        try {
            for (int i = 0; i < castArray.length(); i++) {
                mCast[i] = castArray.getJSONObject(i).getString("name");
            }
        } catch (JSONException e) {
            Log.e("FILMSTER", "Invalid cast definition: " + e.getMessage());
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

    public String getThumbnail() {
        return mThumbnail;
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

    public String[] getCast() {
        return mCast;
    }
}
