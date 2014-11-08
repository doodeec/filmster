package com.doodeec.filmster;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.doodeec.filmster.ApplicationState.AppState;
import com.doodeec.filmster.ApplicationState.ConnectionStateChange;
import com.doodeec.filmster.Model.Movie;
import com.doodeec.filmster.MovieDetail.MovieDetailFragment;
import com.doodeec.filmster.MovieList.MovieListActivityInterface;
import com.doodeec.filmster.MovieList.MovieListFragment;
import com.doodeec.filmster.Provider.MovieProvider;


public class MainActivity extends ActionBarActivity implements ConnectionStateChange, MovieListActivityInterface {

    private static final String DETAIL_TAG = "detail";
    private static final String CONNECTION_DIALOG_BUNDLE = "Connection_dialog";
    private static final String DETAIL_DISPLAYED_BUNDLE = "Detail_show";
    private static final String DETAIL_ID_DISPLAYED_BUNDLE = "Detail_displayed";

    private MovieListFragment mListFragment;
    private MovieDetailFragment mDetailFragment;
    private AlertDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppState.setCurrentActivity(this);
        setContentView(R.layout.activity_main);

        mListFragment = (MovieListFragment) getSupportFragmentManager().findFragmentById(R.id.movie_list);

        if (findViewById(R.id.movie_detail) != null) {
            mDetailFragment = (MovieDetailFragment) getSupportFragmentManager().findFragmentById(R.id.movie_detail);
            getSupportFragmentManager().beginTransaction().hide(mDetailFragment).commit();
        }

        if (savedInstanceState != null) {
            if (savedInstanceState.getBoolean(CONNECTION_DIALOG_BUNDLE)) {
                showConnectionDialog();
            }
            if (savedInstanceState.getBoolean(DETAIL_DISPLAYED_BUNDLE)) {
                onDisplayDetail(MovieProvider.getMovies().get(savedInstanceState.getInt(DETAIL_ID_DISPLAYED_BUNDLE)));
            }
        }
    }

    @Override
    public void onDisplayDetail(Movie movie) {
        if (mDetailFragment != null) {
            // if detail fragment exists on the screen (tablet), update its content
            mDetailFragment.setMovie(movie);
            mDetailFragment.setData();

            // if detail fragment was not visible yet, show it with animation
            if (!mDetailFragment.isVisible()) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.slide_from_right, R.anim.slide_to_right);
                transaction.show(mDetailFragment);
                transaction.commit();
            }
        } else {
            // small screens does not show contain master/detail style layout, add detail fragment to the content view
            MovieDetailFragment fragment = new MovieDetailFragment();
            fragment.setMovie(movie);

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.slide_from_right, R.anim.slide_to_right, R.anim.slide_from_right, R.anim.slide_to_right);
            transaction.add(android.R.id.content, fragment, DETAIL_TAG);
            transaction.addToBackStack(DETAIL_TAG);
            transaction.commit();
        }
    }

    @Override
    protected void onResume() {
        AppState.setCurrentActivity(this);
        super.onResume();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (mDetailFragment != null && mDetailFragment.isVisible()) {
            int moviePosition = mDetailFragment.getMovieItemId();

            if (moviePosition != -1) {
                outState.putBoolean(DETAIL_DISPLAYED_BUNDLE, true);
                outState.putInt(DETAIL_ID_DISPLAYED_BUNDLE, mDetailFragment.getMovieItemId());
            }
        } else if (getSupportFragmentManager().findFragmentByTag(DETAIL_TAG) != null) {
            int moviePosition = ((MovieDetailFragment) getSupportFragmentManager().findFragmentByTag(DETAIL_TAG)).getMovieItemId();

            if (moviePosition != -1) {
                outState.putBoolean(DETAIL_DISPLAYED_BUNDLE, true);
                outState.putInt(DETAIL_ID_DISPLAYED_BUNDLE, moviePosition);
            }
            // close detail fragment, it will be recreated by saved instance parameters
            getSupportFragmentManager().popBackStackImmediate();

        }

        outState.putBoolean(CONNECTION_DIALOG_BUNDLE, mDialog != null);
        if (mDialog != null) {
            mDialog.dismiss();
        }

        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_refresh) {
            //close detail fragment
            if (mDetailFragment != null && mDetailFragment.isVisible()) {
                getSupportFragmentManager().beginTransaction().hide(mDetailFragment).commit();

                mListFragment.getListView().getLayoutParams().width = findViewById(android.R.id.content).getWidth();
            } else if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                getSupportFragmentManager().popBackStackImmediate();
            }

            mListFragment.reloadData();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConnectionGained() {
        // do not display while loading layout for the first time
        if (mListFragment.isVisible()) {
            showConnectionDialog();
        }
    }

    @Override
    public void onConnectionLost() {
        //TODO handle connection lost

        //block lazy loading
        mListFragment.setMaxDataLength(0);
    }

    /**
     * Shows dialog that connection is available again
     */
    private void showConnectionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.reconnected);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                mListFragment.reloadData();
            }
        });
        builder.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        mDialog = builder.show();
        mDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                mDialog = null;
            }
        });
    }
}
