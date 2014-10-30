package com.doodeec.filmster.ServerCommunicator;

import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;

import com.doodeec.filmster.ImageCache;
import com.doodeec.filmster.Model.Movie;
import com.doodeec.filmster.Model.MovieDefinitionKeys;
import com.doodeec.filmster.ServerCommunicator.ResponseListener.BitmapServerResponseListener;
import com.doodeec.filmster.ServerCommunicator.ResponseListener.JSONServerResponseListener;
import com.doodeec.filmster.ServerCommunicator.ResponseListener.ServerResponseListener;
import com.doodeec.filmster.ServerCommunicator.ServerRequest.ErrorResponse;
import com.doodeec.filmster.ServerCommunicator.ServerRequest.ServerRequest;
import com.doodeec.filmster.ServerCommunicator.ServerRequest.ServerRequestInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Dusan Doodeec Bartos on 20.10.2014.
 *
 * Service for handling REST calls and Bitmap (poster) downloads
 */
public class ResourceService {

    //    http://api.rottentomatoes.com/api/public/v1.0/lists/movies/upcoming.json?page_limit=5&page=1&country=us&apikey=38fnext9p73hh8m3hacx5s6c
    private static final String SCHEME = "http";
    private static final String AUTHORITY = "api.rottentomatoes.com";
    private static final String PATH_PRIMARY = "api";
    private static final String PATH_SECONDARY = "public";
    private static final String PATH_VERSION = "v1.0";
    private static final String PATH_TYPE = "lists";
    private static final String PATH_KEY = "movies";
    private static final String PATH_FILTER = "upcoming.json";
    private static final String PAGE_LIMIT_PARAM_KEY = "page_limit";
    private static final String API_PARAM_KEY = "apikey";
    private static final String API_KEY = "38fnext9p73hh8m3hacx5s6c";
    private static final String PAGE_KEY = "page";
    private static final int PAGE_LIMIT = 5;
    private static final String MOCK_AUTHORITY = "api.doodeec.com";

    private static HashMap<String, BitmapServerResponseListener> mImageRequestMap = new HashMap<String, BitmapServerResponseListener>();

    /**
     * Builds API url for specific page of movie list
     *
     * @param page page to load
     * @return API url
     */
    private static String buildPathForPage(int page) {
        Uri.Builder builder = new Uri.Builder();

/*        builder.scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(PATH_PRIMARY)
                .appendPath(PATH_SECONDARY)
                .appendPath(PATH_VERSION)
                .appendPath(PATH_TYPE)
                .appendPath(PATH_KEY)
                .appendPath(PATH_FILTER)
                .appendQueryParameter(PAGE_LIMIT_PARAM_KEY, String.valueOf(PAGE_LIMIT))
                .appendQueryParameter(API_PARAM_KEY, API_KEY)
                .appendQueryParameter(PAGE_KEY, String.valueOf(page));*/

        //mock server
        builder.scheme(SCHEME)
                .authority(MOCK_AUTHORITY)
                .appendPath(PATH_KEY)
                .appendQueryParameter(PAGE_KEY, String.valueOf(page));

        return builder.build().toString();
    }

    /**
     * Loads specific page of movie list
     *
     * @param page             page to load
     * @param responseListener response listener
     * @return request interface
     */
    public static ServerRequestInterface loadMovies(final int page, final ServerResponseListener<Movie[]> responseListener) {
        String url = buildPathForPage(page);
        ServerRequest request = new ServerRequest(url, ServerRequest.RequestType.GET, new JSONServerResponseListener() {
            @Override
            public void onSuccess(JSONObject movieListDefinition) {
                Log.d("FILMSTER", "Movies loaded for page " + page);
                try {
                    //TODO handle total count
                    Integer totalCount = movieListDefinition.getInt(MovieDefinitionKeys.TOTAL_KEY);
                    JSONArray listDefinition = movieListDefinition.getJSONArray(MovieDefinitionKeys.MOVIES_KEY);
                    Movie[] movieList = new Movie[listDefinition.length()];

                    for (int i = 0; i < listDefinition.length(); i++) {
                        movieList[i] = new Movie(listDefinition.getJSONObject(i));
                    }

                    responseListener.onSuccess(movieList);
                } catch (JSONException e) {
                    Log.e("FILMSTER", "Movie list has invalid structure: " + e.getMessage());
                    responseListener.onError(new ErrorResponse(e.getMessage()));
                }
            }

            @Override
            public void onError(ErrorResponse error) {
                Log.e("FILMSTER", "Movies can not be loaded: " + error.getMessage());
                responseListener.onError(error);
            }

            @Override
            public void onProgress(Integer progress) {
                Log.d("FILMSTER", "Movies loading: " + progress + "%");
                responseListener.onProgress(progress);
            }

            @Override
            public void onCancelled() {
                Log.d("FILMSTER", "Movies loading cancelled");
                responseListener.onCancelled();
            }
        });
        request.execute();
        return request;
    }

    /**
     * Loads image from the given url as Bitmap
     * Returns cached bitmap if available
     *
     * @param url              image url
     * @param responseListener response listener
     * @return request interface
     */
    public static ServerRequestInterface loadImage(final String url, final BitmapServerResponseListener responseListener) {
        Bitmap cachedImage = ImageCache.getBitmapFromCache(url);

        if (cachedImage != null) {
            // do not load image which was cached before from server, return cached one
            responseListener.onSuccess(cachedImage);
            return null;
        } else if (mImageRequestMap.containsKey(url)) {
            //TODO multiple response listeners chaining support?
            return null;
        } else {
            // if image is not cached, load it from server and cache if there is valid response (Bitmap is not null)
            ServerRequest request = new ServerRequest(url, ServerRequest.RequestType.GET, new BitmapServerResponseListener() {
                @Override
                public void onSuccess(Bitmap loadedImage) {
                    //intercept success response to cache image to LRU cache
                    Log.d("FILMSTER", "Image loaded: " + url);
                    ImageCache.addBitmapToCache(url, loadedImage);
                    mImageRequestMap.remove(url);
                    responseListener.onSuccess(loadedImage);
                }

                @Override
                public void onError(ErrorResponse error) {
                    Log.e("FILMSTER", "Image can not be loaded: " + error.getMessage());
                    mImageRequestMap.remove(url);
                    responseListener.onError(error);
                }

                @Override
                public void onProgress(Integer progress) {
                    Log.d("FILMSTER", "Image loading: " + progress + "% (" + url + ")");
                    mImageRequestMap.remove(url);
                    responseListener.onProgress(progress);
                }

                @Override
                public void onCancelled() {
                    Log.d("FILMSTER", "Image loading cancelled");
                    mImageRequestMap.remove(url);
                    responseListener.onCancelled();
                }
            });

            mImageRequestMap.put(url, responseListener);

            request.execute();
            return request;
        }
    }
}
