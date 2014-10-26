package com.doodeec.filmster.ServerCommunicator.ResponseListener;

import android.graphics.Bitmap;

/**
 * Created by Dusan Doodeec Bartos on 26.10.2014.
 *
 * Bitmap server response listener
 * Extends Generic interface
 * A way to use two generic interfaces in the object constructors
 *
 * @see com.doodeec.filmster.ServerCommunicator.ServerRequest
 */
public interface BitmapServerResponseListener extends ServerResponseListener<Bitmap> {
}
