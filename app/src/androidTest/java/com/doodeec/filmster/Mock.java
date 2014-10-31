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
        public void onSuccess(JSONObject responseObject) {}
        public void onError(ErrorResponse error) {}
        public void onProgress(Integer progress) {}
        public void onCancelled() {}
    };

    public static BitmapServerResponseListener BITMAP_LISTENER = new BitmapServerResponseListener() {
        public void onSuccess(Bitmap responseObject) {}
        public void onError(ErrorResponse error) {}
        public void onProgress(Integer progress) {}
        public void onCancelled() {}
    };

    public static JSONObject REST_MOVIE() {
        try {
            return new JSONObject("{" +
                    "        \"id\": \"771379104\"," +
                    "        \"title\": \"Goodbye to Language 3D\"," +
                    "        \"year\": 2014," +
                    "        \"runtime\": 70," +
                    "        \"release_dates\": {" +
                    "            \"theater\": \"2014-10-29\"" +
                    "        }," +
                    "        \"ratings\": {" +
                    "            \"critics_score\": 86," +
                    "            \"audience_score\": 77" +
                    "        }," +
                    "        \"synopsis\": \"The only film to get a round of applause mid-screening at the 2014 Cannes Film Festival...\"," +
                    "        \"posters\": {" +
                    "            \"thumbnail\": \"http://content6.flixster.com/movie/11/18/05/11180564_tmb.jpg\"," +
                    "            \"detailed\": \"http://content6.flixster.com/movie/11/18/05/11180564_tmb.jpg\"" +
                    "        }," +
                    "        \"abridged_cast\": [" +
                    "            {" +
                    "                \"name\": \"Heloise Godet\"" +
                    "            }," +
                    "            {" +
                    "                \"name\": \"Kamel Abdelli\"" +
                    "            }," +
                    "            {" +
                    "                \"name\": \"Richard Chevallier\"" +
                    "            }," +
                    "            {" +
                    "                \"name\": \"Zoe Bruneau\"" +
                    "            }," +
                    "            {" +
                    "                \"name\": \"Christian Gregori\"" +
                    "            }" +
                    "        ]," +
                    "        \"links\": {" +
                    "            \"imdb\": \"http://api.rottentomatoes.com/api/public/v1.0/movies/771379104.json\"" +
                    "        }" +
                    "    }");
        } catch (JSONException e) {
            Log.d("MOCK Movie error", e.getMessage());
        }

        return null;
    }

    public static JSONObject REST_MOVIE_LIST() {
        try {
            return new JSONObject("{" +
                    "  \"total\": 18," +
                    "  \"movies\": [" +
                    "    {" +
                    "      \"id\": \"771351912\"," +
                    "      \"title\": \"Interstellar\"," +
                    "      \"year\": 2014," +
                    "      \"runtime\": 169," +
                    "      \"release_dates\": {\"theater\": \"2014-11-07\"}," +
                    "      \"ratings\": {" +
                    "        \"critics_score\": 78," +
                    "        \"audience_score\": 95" +
                    "      }," +
                    "      \"synopsis\": \"With our time on Earth coming to an end, a team of explorers undertakes the most important mission in human history; traveling beyond this galaxy to discover whether mankind has a future among the stars. (C) Paramount\"," +
                    "      \"posters\": {" +
                    "        \"thumbnail\": \"http://ia.media-imdb.com/images/M/MV5BMjIxNTU4MzY4MF5BMl5BanBnXkFtZTgwMzM4ODI3MjE@._V1_SX140_CR0,0,140,209_AL_.jpg\"," +
                    "        \"detailed\": \"http://ia.media-imdb.com/images/M/MV5BMjIxNTU4MzY4MF5BMl5BanBnXkFtZTgwMzM4ODI3MjE@._V1_SX214_AL_.jpg\"" +
                    "      }," +
                    "      \"abridged_cast\": [" +
                    "        {" +
                    "          \"name\": \"Matthew McConaughey\"" +
                    "        }," +
                    "        {" +
                    "          \"name\": \"Anne Hathaway\"" +
                    "        }," +
                    "        {" +
                    "          \"name\": \"Jessica Chastain\"" +
                    "        }," +
                    "        {" +
                    "          \"name\": \"Casey Affleck\"" +
                    "        }," +
                    "        {" +
                    "          \"name\": \"Wes Bentley\"" +
                    "        }" +
                    "      ]," +
                    "      \"links\": {" +
                    "        \"imdb\": \"http://www.imdb.com/title/tt0816692/\"" +
                    "      }" +
                    "    }," +
                    "    {" +
                    "      \"id\": \"771355766\"," +
                    "      \"title\": \"Big Hero 6\"," +
                    "      \"year\": 2014," +
                    "      \"runtime\": 93," +
                    "      \"release_dates\": {\"theater\": \"2014-11-07\"}," +
                    "      \"ratings\": {" +
                    "        \"critics_score\": 81," +
                    "        \"audience_score\": 77" +
                    "      }," +
                    "      \"synopsis\": \"With all the heart and humor audiences expect from Walt Disney Animation Studios, \\\"Big Hero 6\\\" is an action-packed comedy-adventure about robotics prodigy Hiro Hamada, who learns to harness his genius-thanks to his brilliant brother Tadashi and their like-minded friends: adrenaline junkie Go Go Tamago, neatnik Wasabi, chemistry whiz Honey Lemon and fanboy Fred. When a devastating turn of events catapults them into the midst of a dangerous plot unfolding in the streets of San Fransokyo, Hiro turns to his closest companion-a robot named Baymax-and transforms the group into a band of high-tech heroes determined to solve the mystery. (C) Disney\"," +
                    "      \"posters\": {" +
                    "        \"thumbnail\": \"http://ia.media-imdb.com/images/M/MV5BMjI4MTIzODU2NV5BMl5BanBnXkFtZTgwMjE0NDAwMjE@._V1_SY209_CR0,0,140,209_AL_.jpg\"," +
                    "        \"detailed\": \"http://ia.media-imdb.com/images/M/MV5BMjI4MTIzODU2NV5BMl5BanBnXkFtZTgwMjE0NDAwMjE@._V1_SY317_CR0,0,214,317_AL_.jpg\"" +
                    "      }," +
                    "      \"abridged_cast\": [" +
                    "        {" +
                    "          \"name\": \"Ryan Potter\"" +
                    "        }," +
                    "        {" +
                    "          \"name\": \"Scott Adsit\"" +
                    "        }," +
                    "        {" +
                    "          \"name\": \"T.J. Miller\"" +
                    "        }," +
                    "        {" +
                    "          \"name\": \"Jamie Chung\"" +
                    "        }," +
                    "        {" +
                    "          \"name\": \"Damon Wayans Jr.\"" +
                    "        }" +
                    "      ]," +
                    "      \"links\": {" +
                    "        \"imdb\": \"http://www.imdb.com/title/tt2245084\"" +
                    "      }" +
                    "    }," +
                    "    {" +
                    "      \"id\": \"771325470\"," +
                    "      \"title\": \"The Theory of Everything\"," +
                    "      \"year\": 2014," +
                    "      \"runtime\": 123," +
                    "      \"release_dates\": {\"theater\": \"2015-01-01\"}," +
                    "      \"ratings\": {" +
                    "        \"critics_score\": 68," +
                    "        \"audience_score\": 77" +
                    "      }," +
                    "      \"synopsis\": \"A look at the relationship between the famous physicist Stephen Hawking and his wife.\"," +
                    "      \"posters\": {" +
                    "        \"thumbnail\": \"http://ia.media-imdb.com/images/M/MV5BMTAwMTU4MDA3NDNeQTJeQWpwZ15BbWU4MDk4NTMxNTIx._V1_SY209_CR0,0,140,209_AL_.jpg\"," +
                    "        \"detailed\": \"http://ia.media-imdb.com/images/M/MV5BMTAwMTU4MDA3NDNeQTJeQWpwZ15BbWU4MDk4NTMxNTIx._V1_SX214_AL_.jpg\"" +
                    "      }," +
                    "      \"abridged_cast\": [" +
                    "        {" +
                    "          \"name\": \"Felicity Jones\"" +
                    "        }," +
                    "        {" +
                    "          \"name\": \"Eddie Redmayne\"" +
                    "        }," +
                    "        {" +
                    "          \"name\": \"Charlie Cox\"" +
                    "        }" +
                    "      ]," +
                    "      \"links\": {" +
                    "        \"imdb\": \"http://www.imdb.com/title/tt2980516\"" +
                    "      }" +
                    "    }," +
                    "    {" +
                    "      \"id\": \"771366367\"," +
                    "      \"title\": \"Jessabelle\"," +
                    "      \"year\": 2014," +
                    "      \"runtime\": 90," +
                    "      \"release_dates\": {\"theater\": \"2014-11-06\"}," +
                    "      \"ratings\": {" +
                    "      }," +
                    "      \"synopsis\": \"Returning to her childhood home in Louisiana to recuperate from a horrific car accident, Jessabelle comes face to face with a long-tormented spirit that has been seeking her return -- and has no intention of letting her escape.\"," +
                    "      \"posters\": {" +
                    "        \"thumbnail\": \"http://ia.media-imdb.com/images/M/MV5BMTQ2NTM1MjYzOV5BMl5BanBnXkFtZTgwNjQwMjA1MjE@._V1_SY209_CR0,0,140,209_AL_.jpg\"," +
                    "        \"detailed\": \"http://ia.media-imdb.com/images/M/MV5BMTQ2NTM1MjYzOV5BMl5BanBnXkFtZTgwNjQwMjA1MjE@._V1_SX214_AL_.jpg\"" +
                    "      }," +
                    "      \"abridged_cast\": [" +
                    "        {" +
                    "          \"name\": \"Sarah Snook\"" +
                    "        }," +
                    "        {" +
                    "          \"name\": \"Mark Webber\"" +
                    "        }," +
                    "        {" +
                    "          \"name\": \"Joelle Carter\"" +
                    "        }" +
                    "      ]," +
                    "      \"links\": {" +
                    "        \"imdb\": \"http://www.imdb.com/title/tt2300975\"" +
                    "      }" +
                    "    }," +
                    "    {" +
                    "      \"id\": \"771386517\"," +
                    "      \"title\": \"The Better Angels\"," +
                    "      \"year\": 2014," +
                    "      \"runtime\": 95," +
                    "      \"release_dates\": {\"theater\": \"2014-11-07\"}," +
                    "      \"ratings\": {" +
                    "        \"audience_score\": 66" +
                    "      }," +
                    "      \"synopsis\": \"The story of Abraham Lincoln's childhood in the harsh wilderness of Indiana and the hardships that shaped him, the tragedy that marked him for ever and the two women who guided him to immortality.\"," +
                    "      \"posters\": {" +
                    "        \"thumbnail\": \"http://ia.media-imdb.com/images/M/MV5BMjEyOTI0OTg3N15BMl5BanBnXkFtZTgwNjk5NTExMzE@._V1_SY209_CR0,0,140,209_AL_.jpg\"," +
                    "        \"detailed\": \"http://ia.media-imdb.com/images/M/MV5BMjEyOTI0OTg3N15BMl5BanBnXkFtZTgwNjk5NTExMzE@._V1_SX214_AL_.jpg\"" +
                    "      }," +
                    "      \"abridged_cast\": [" +
                    "        {" +
                    "          \"name\": \"Jason Clarke\"" +
                    "        }," +
                    "        {" +
                    "          \"name\": \"Diane Kruger\"" +
                    "        }," +
                    "        {" +
                    "          \"name\": \"Brit Marling\"" +
                    "        }" +
                    "      ]," +
                    "      \"links\": {" +
                    "        \"imdb\": \"http://www.imdb.com/title/tt2316868\"" +
                    "      }" +
                    "    }" +
                    "  ]" +
                    "}");
        } catch (JSONException e) {
            Log.d("MOCK Movie List", e.getMessage());
        }

        return null;
    }
}