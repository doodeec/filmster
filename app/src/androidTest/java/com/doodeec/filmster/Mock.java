package com.doodeec.filmster;

import android.graphics.Bitmap;
import android.util.Log;

import com.doodeec.filmster.ServerCommunicator.ResponseListener.BitmapServerResponseListener;
import com.doodeec.filmster.ServerCommunicator.ResponseListener.JSONServerResponseListener;
import com.doodeec.filmster.ServerCommunicator.ServerRequest.ErrorResponse;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Dusan Doodeec Bartos on 20.10.2014.
 *
 * Mock definitions for testing
 */
public class Mock {

    public static String REST_URL = "http://doodeec.com";

    public static JSONServerResponseListener JSON_LISTENER = new JSONServerResponseListener() {
        @Override
        public void onSuccess(JSONObject responseObject) {}

        @Override
        public void onError(ErrorResponse error) {}

        @Override
        public void onProgress(Integer progress) {}

        @Override
        public void onCancelled() {}
    };

    public static BitmapServerResponseListener BITMAP_LISTENER = new BitmapServerResponseListener() {
        @Override
        public void onSuccess(Bitmap responseObject) {}

        @Override
        public void onError(ErrorResponse error) {}

        @Override
        public void onProgress(Integer progress) {}

        @Override
        public void onCancelled() {}
    };

    public static JSONObject REST_MOVIE() {
        try {
            return new JSONObject("{\n" +
                    "        \"id\": \"771379104\",\n" +
                    "        \"title\": \"Goodbye to Language 3D\",\n" +
                    "        \"year\": 2014,\n" +
                    "        \"mpaa_rating\": \"Unrated\",\n" +
                    "        \"runtime\": 70,\n" +
                    "        \"release_dates\": {\n" +
                    "            \"theater\": \"2014-10-29\"\n" +
                    "        },\n" +
                    "        \"ratings\": {\n" +
                    "            \"critics_rating\": \"Fresh\",\n" +
                    "            \"critics_score\": 86,\n" +
                    "            \"audience_score\": 77\n" +
                    "        },\n" +
                    "        \"synopsis\": \"The only film to get a round of applause mid-screening at the 2014 Cannes Film Festival, where it won the Prix du Jury), the 3D marvel Goodbye to Language alights on doubt and despair with the greatest freedom and joy. The idea is simple, in its way: a stray dog wanders from town to country, and over the course of some seasons observes a married woman and a single man as they meet, love, argue and fight. Or perhaps it's the audience viewing one couple, or two couples, or an alternate version of the same couple. At 83, Godard works as a truly independent filmmaker, unencumbered by all concerns beyond the immediate: to create a work that embodies his own state of being in relation to time, light, color, and cinema itself. The artist's beloved dog Roxy is the de facto \\\"star\\\" of this film, which is as impossible to summarize as a poem by Wallace Stevens or a Messiaen quartet. This might be a farewell to words - but it's a warm welcome to the legendary director into the world of 3D, in which it must be seen and experienced to fully appreciate Godard's wondrous use of the technology. (C) Kino Lorber\",\n" +
                    "        \"posters\": {\n" +
                    "            \"thumbnail\": \"http://content6.flixster.com/movie/11/18/05/11180564_tmb.jpg\",\n" +
                    "            \"profile\": \"http://content6.flixster.com/movie/11/18/05/11180564_tmb.jpg\",\n" +
                    "            \"detailed\": \"http://content6.flixster.com/movie/11/18/05/11180564_tmb.jpg\",\n" +
                    "            \"original\": \"http://content6.flixster.com/movie/11/18/05/11180564_tmb.jpg\"\n" +
                    "        },\n" +
                    "        \"abridged_cast\": [\n" +
                    "            {\n" +
                    "                \"name\": \"Heloise Godet\",\n" +
                    "                \"id\": \"771491997\"\n" +
                    "            },\n" +
                    "            {\n" +
                    "                \"name\": \"Kamel Abdelli\",\n" +
                    "                \"id\": \"771491998\"\n" +
                    "            },\n" +
                    "            {\n" +
                    "                \"name\": \"Richard Chevallier\",\n" +
                    "                \"id\": \"771491999\"\n" +
                    "            },\n" +
                    "            {\n" +
                    "                \"name\": \"Zoe Bruneau\",\n" +
                    "                \"id\": \"771492000\"\n" +
                    "            },\n" +
                    "            {\n" +
                    "                \"name\": \"Christian Gregori\",\n" +
                    "                \"id\": \"771081573\"\n" +
                    "            }\n" +
                    "        ],\n" +
                    "        \"alternate_ids\": {\n" +
                    "            \"imdb\": \"2400275\"\n" +
                    "        },\n" +
                    "        \"links\": {\n" +
                    "            \"self\": \"http://api.rottentomatoes.com/api/public/v1.0/movies/771379104.json\",\n" +
                    "            \"alternate\": \"http://www.rottentomatoes.com/m/goodbye_to_language_3d/\",\n" +
                    "            \"cast\": \"http://api.rottentomatoes.com/api/public/v1.0/movies/771379104/cast.json\",\n" +
                    "            \"reviews\": \"http://api.rottentomatoes.com/api/public/v1.0/movies/771379104/reviews.json\",\n" +
                    "            \"similar\": \"http://api.rottentomatoes.com/api/public/v1.0/movies/771379104/similar.json\"\n" +
                    "        }\n" +
                    "    }");
        } catch (JSONException e) {
            Log.d("MOCK Movie error", e.getMessage());
        }

        return null;
    }

    public static JSONObject REST_MOVIE_LIST() {
        try {
            return new JSONObject("{\"total\": 15, \"movies\": [\n" +
                    "    {\n" +
                    "        \"id\": \"771379104\",\n" +
                    "        \"title\": \"Goodbye to Language 3D\",\n" +
                    "        \"year\": 2014,\n" +
                    "        \"mpaa_rating\": \"Unrated\",\n" +
                    "        \"runtime\": 70,\n" +
                    "        \"release_dates\": {\n" +
                    "            \"theater\": \"2014-10-29\"\n" +
                    "        },\n" +
                    "        \"ratings\": {\n" +
                    "            \"critics_rating\": \"Fresh\",\n" +
                    "            \"critics_score\": 86,\n" +
                    "            \"audience_score\": 77\n" +
                    "        },\n" +
                    "        \"synopsis\": \"The only film to get a round of applause mid-screening at the 2014 Cannes Film Festival, where it won the Prix du Jury), the 3D marvel Goodbye to Language alights on doubt and despair with the greatest freedom and joy. The idea is simple, in its way: a stray dog wanders from town to country, and over the course of some seasons observes a married woman and a single man as they meet, love, argue and fight. Or perhaps it's the audience viewing one couple, or two couples, or an alternate version of the same couple. At 83, Godard works as a truly independent filmmaker, unencumbered by all concerns beyond the immediate: to create a work that embodies his own state of being in relation to time, light, color, and cinema itself. The artist's beloved dog Roxy is the de facto \\\"star\\\" of this film, which is as impossible to summarize as a poem by Wallace Stevens or a Messiaen quartet. This might be a farewell to words - but it's a warm welcome to the legendary director into the world of 3D, in which it must be seen and experienced to fully appreciate Godard's wondrous use of the technology. (C) Kino Lorber\",\n" +
                    "        \"posters\": {\n" +
                    "            \"thumbnail\": \"http://content6.flixster.com/movie/11/18/05/11180564_tmb.jpg\",\n" +
                    "            \"profile\": \"http://content6.flixster.com/movie/11/18/05/11180564_tmb.jpg\",\n" +
                    "            \"detailed\": \"http://content6.flixster.com/movie/11/18/05/11180564_tmb.jpg\",\n" +
                    "            \"original\": \"http://content6.flixster.com/movie/11/18/05/11180564_tmb.jpg\"\n" +
                    "        },\n" +
                    "        \"abridged_cast\": [\n" +
                    "            {\n" +
                    "                \"name\": \"Heloise Godet\",\n" +
                    "                \"id\": \"771491997\"\n" +
                    "            },\n" +
                    "            {\n" +
                    "                \"name\": \"Kamel Abdelli\",\n" +
                    "                \"id\": \"771491998\"\n" +
                    "            },\n" +
                    "            {\n" +
                    "                \"name\": \"Richard Chevallier\",\n" +
                    "                \"id\": \"771491999\"\n" +
                    "            },\n" +
                    "            {\n" +
                    "                \"name\": \"Zoe Bruneau\",\n" +
                    "                \"id\": \"771492000\"\n" +
                    "            },\n" +
                    "            {\n" +
                    "                \"name\": \"Christian Gregori\",\n" +
                    "                \"id\": \"771081573\"\n" +
                    "            }\n" +
                    "        ],\n" +
                    "        \"alternate_ids\": {\n" +
                    "            \"imdb\": \"2400275\"\n" +
                    "        },\n" +
                    "        \"links\": {\n" +
                    "            \"self\": \"http://api.rottentomatoes.com/api/public/v1.0/movies/771379104.json\",\n" +
                    "            \"alternate\": \"http://www.rottentomatoes.com/m/goodbye_to_language_3d/\",\n" +
                    "            \"cast\": \"http://api.rottentomatoes.com/api/public/v1.0/movies/771379104/cast.json\",\n" +
                    "            \"reviews\": \"http://api.rottentomatoes.com/api/public/v1.0/movies/771379104/reviews.json\",\n" +
                    "            \"similar\": \"http://api.rottentomatoes.com/api/public/v1.0/movies/771379104/similar.json\"\n" +
                    "        }\n" +
                    "    },\n" +
                    "    {\n" +
                    "        \"id\": \"16980\",\n" +
                    "        \"title\": \"Saw\",\n" +
                    "        \"year\": 2014,\n" +
                    "        \"mpaa_rating\": \"R\",\n" +
                    "        \"runtime\": 103,\n" +
                    "        \"critics_consensus\": \"\",\n" +
                    "        \"release_dates\": {\n" +
                    "            \"theater\": \"2014-10-31\",\n" +
                    "            \"dvd\": \"2005-02-15\"\n" +
                    "        },\n" +
                    "        \"ratings\": {\n" +
                    "            \"critics_rating\": \"Rotten\",\n" +
                    "            \"critics_score\": 48,\n" +
                    "            \"audience_rating\": \"Upright\",\n" +
                    "            \"audience_score\": 64\n" +
                    "        },\n" +
                    "        \"synopsis\": \"The directorial debut from filmmaker James Wan, this psychological thriller comes from the first screenplay by actor Leigh Whannell, who also stars. Whannell plays Adam, one of two men chained up in a mysterious chamber. The other, Dr. Gordon (Cary Elwes), like Adam, has no idea how either of them got there. Neither of them are led to feel optimistic by the man lying between them dead of a self-inflicted gunshot wound. Together, Adam and Dr. Gordon attempt to piece together what has happened to them and who the sadistic madman behind their imprisonment is. Also starring Danny Glover and Monica Potter, Saw premiered at the 2004 Sundance Film Festival. ~ Matthew Tobey, Rovi\",\n" +
                    "        \"posters\": {\n" +
                    "            \"thumbnail\": \"http://content6.flixster.com/movie/11/17/76/11177636_tmb.jpg\",\n" +
                    "            \"profile\": \"http://content6.flixster.com/movie/11/17/76/11177636_tmb.jpg\",\n" +
                    "            \"detailed\": \"http://content6.flixster.com/movie/11/17/76/11177636_tmb.jpg\",\n" +
                    "            \"original\": \"http://content6.flixster.com/movie/11/17/76/11177636_tmb.jpg\"\n" +
                    "        },\n" +
                    "        \"abridged_cast\": [\n" +
                    "            {\n" +
                    "                \"name\": \"Leigh Whannell\",\n" +
                    "                \"id\": \"364604347\",\n" +
                    "                \"characters\": [\"Adam\"]\n" +
                    "            },\n" +
                    "            {\n" +
                    "                \"name\": \"Cary Elwes\",\n" +
                    "                \"id\": \"162657306\",\n" +
                    "                \"characters\": [\"Dr. Lawrence Gordon\"]\n" +
                    "            },\n" +
                    "            {\n" +
                    "                \"name\": \"Danny Glover\",\n" +
                    "                \"id\": \"162660485\",\n" +
                    "                \"characters\": [\"Detective David Tapp\"]\n" +
                    "            },\n" +
                    "            {\n" +
                    "                \"name\": \"Ken Leung\",\n" +
                    "                \"id\": \"528393137\",\n" +
                    "                \"characters\": [\"Detective Steven Sing\"]\n" +
                    "            },\n" +
                    "            {\n" +
                    "                \"name\": \"Dina Meyer\",\n" +
                    "                \"id\": \"162674020\",\n" +
                    "                \"characters\": [\"Kerry\"]\n" +
                    "            }\n" +
                    "        ],\n" +
                    "        \"alternate_ids\": {\n" +
                    "            \"imdb\": \"0387564\"\n" +
                    "        },\n" +
                    "        \"links\": {\n" +
                    "            \"self\": \"http://api.rottentomatoes.com/api/public/v1.0/movies/16980.json\",\n" +
                    "            \"alternate\": \"http://www.rottentomatoes.com/m/saw/\",\n" +
                    "            \"cast\": \"http://api.rottentomatoes.com/api/public/v1.0/movies/16980/cast.json\",\n" +
                    "            \"reviews\": \"http://api.rottentomatoes.com/api/public/v1.0/movies/16980/reviews.json\",\n" +
                    "            \"similar\": \"http://api.rottentomatoes.com/api/public/v1.0/movies/16980/similar.json\"\n" +
                    "        }\n" +
                    "    }]}");
        } catch (JSONException e) {
            Log.d("MOCK Movie List", e.getMessage());
        }

        return null;
    }
}