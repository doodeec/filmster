package com.doodeec.filmster;

import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.doodeec.filmster.LazyList.LazyList;
import com.doodeec.filmster.LazyList.LazyListAdapter;
import com.doodeec.filmster.Model.Movie;
import com.doodeec.filmster.ServerCommunicator.ResourceService;
import com.doodeec.filmster.ServerCommunicator.ResponseListener.ServerResponseListener;
import com.doodeec.filmster.ServerCommunicator.ServerRequest.ErrorResponse;

/**
 * Created by dbartos on 31.10.2014.
 *
 * Implementation of LazyList Class
 */
public class MovieListFragment extends LazyList {

    private static final String DETAIL_TAG = "detail";

    @Override
    protected void initData() {
        //init resources
        ResourceService.loadMovies(1, new ServerResponseListener<Movie[]>() {
            @Override
            public void onSuccess(Movie[] movies) {
                Toast.makeText(getActivity(), "Movies loaded", Toast.LENGTH_SHORT).show();

                mAdapter = new LazyListAdapter(movies, MovieListFragment.this);
                setListAdapter(mAdapter);

                mProgress.setVisibility(View.GONE);
                dataLoaded();
            }

            @Override
            public void onError(ErrorResponse error) {
                //TODO toast
                Toast.makeText(getActivity(), "Load movies error", Toast.LENGTH_SHORT).show();
                mProgress.setVisibility(View.GONE);
            }

            @Override
            public void onProgress(Integer progress) {
                //TODO progressbar?
                mProgress.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled() {
                //TODO toast?
                Toast.makeText(getActivity(), "Load movies cancelled", Toast.LENGTH_SHORT).show();
                mProgress.setVisibility(View.GONE);
            }
        });
    }

    private void dataLoaded() {
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MovieDetailFragment fragment = new MovieDetailFragment();
                fragment.setMovie(mAdapter.getItem(position));

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.slide_from_right, R.anim.slide_to_right, R.anim.slide_from_right, R.anim.slide_to_right);
                transaction.add(android.R.id.content, fragment, DETAIL_TAG);
                transaction.addToBackStack(DETAIL_TAG);
                transaction.commit();
            }
        });
    }
}
