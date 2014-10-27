package com.doodeec.filmster.ServerCommunicator.ServerRequest;

import android.test.InstrumentationTestCase;

import com.doodeec.filmster.Mock;

/**
 * Created by Dusan Doodeec Bartos on 20.10.2014.
 *
 * @see com.doodeec.filmster.ServerCommunicator.ServerRequest.ServerRequest
 */
public class ServerRequestSpec extends InstrumentationTestCase {

    public void testServerRequestJSONListenerConstructor() throws Exception {
        ServerRequest request = new ServerRequest(Mock.REST_URL, ServerRequest.RequestType.GET, Mock.JSON_LISTENER);

        assertNotNull(request);
    }

    public void testServerRequestBitmapListenerConstructor() throws Exception {
        ServerRequest request = new ServerRequest(Mock.REST_URL, ServerRequest.RequestType.GET, Mock.BITMAP_LISTENER);

        assertNotNull(request);
    }
}
