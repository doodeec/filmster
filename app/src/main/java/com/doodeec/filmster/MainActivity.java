package com.doodeec.filmster;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.doodeec.filmster.ApplicationState.AppState;
import com.doodeec.filmster.ApplicationState.ConnectionStateChange;


public class MainActivity extends ActionBarActivity implements ConnectionStateChange {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        AppState.setCurrentActivity(this);
    }

    @Override
    protected void onPause() {
        AppState.setCurrentActivity(null);
        super.onPause();
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
            Toast.makeText(this, "Refresh clicked", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConnectionGained() {
        //TODO handle connection gained
    }

    @Override
    public void onConnectionLost() {
        //TODO handle connection lost
    }
}
