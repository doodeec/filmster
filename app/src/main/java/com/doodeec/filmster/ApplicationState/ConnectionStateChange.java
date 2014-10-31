package com.doodeec.filmster.ApplicationState;

/**
 * Created by Dusan Doodeec Bartos on 31.10.2014.
 *
 * Used in activity to notify about connection state change
 */
public interface ConnectionStateChange {

    /**
     * Fired when network connection was initialized
     */
    public void onConnectionGained();

    /**
     * Fired when network connection was lost
     */
    public void onConnectionLost();
}
