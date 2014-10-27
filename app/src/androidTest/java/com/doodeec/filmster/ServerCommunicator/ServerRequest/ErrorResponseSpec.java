package com.doodeec.filmster.ServerCommunicator.ServerRequest;

import android.test.InstrumentationTestCase;

/**
 * Created by Dusan Doodeec Bartos on 27.10.2014.
 *
 * @see com.doodeec.filmster.ServerCommunicator.ServerRequest.ErrorResponse
 */
public class ErrorResponseSpec extends InstrumentationTestCase {

    public void testErrorResponseConstructor() throws Exception {
        ErrorResponse error = new ErrorResponse("Test");

        assertNotNull(error);
    }

    public void testErrorResponseGetters() throws Exception {
        ErrorResponse error = new ErrorResponse("Test");

        assertEquals("Test", error.getMessage());
    }
}
