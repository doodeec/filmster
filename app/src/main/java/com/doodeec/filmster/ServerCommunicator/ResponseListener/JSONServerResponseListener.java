package com.doodeec.filmster.ServerCommunicator.ResponseListener;

import org.json.JSONObject;

/**
 * Created by Dusan Doodeec Bartos on 26.10.2014.
 *
 * JSON server response listener
 * Extends Generic interface
 * A way to use two generic interfaces in the object constructors
 *
 * @see com.doodeec.filmster.ServerCommunicator.ServerRequest
 */
public interface JSONServerResponseListener extends ServerResponseListener<JSONObject> {
}
