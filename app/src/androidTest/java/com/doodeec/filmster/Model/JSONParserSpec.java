package com.doodeec.filmster.Model;

import android.test.InstrumentationTestCase;

import com.doodeec.filmster.Mock;

import org.json.JSONObject;

/**
 * Created by Dusan Doodeec Bartos on 20.10.2014.
 *
 * @see com.doodeec.filmster.Model.JSONParser
 */
public class JSONParserSpec extends InstrumentationTestCase {

    private JSONObject mJson;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        mJson = Mock.REST_MOVIE();
        assertNotNull(mJson);
    }

    public void testGetInt() throws Exception {
        assertNotNull(JSONParser.getIntForKey(mJson, "year"));
        assertEquals(mJson.get("year"), JSONParser.getIntForKey(mJson, "year"));
    }

    public void testGetString() throws Exception {
        assertNotNull(JSONParser.getStringForKey(mJson, "id"));
        assertEquals(mJson.get("id"), JSONParser.getStringForKey(mJson, "id"));
    }

    public void testGetObject() throws Exception {
        assertNotNull(JSONParser.getObjectForKey(mJson, "ratings"));
//        assertEquals(mJson.get("ratings"), JSONParser.getObjectForKey(mJson, "ratings"));
    }

    public void testGetArray() throws Exception {
        assertNotNull(JSONParser.getArrayForKey(mJson, "abridged_cast"));
        assertEquals(5, JSONParser.getArrayForKey(mJson, "abridged_cast").length());
    }
}
