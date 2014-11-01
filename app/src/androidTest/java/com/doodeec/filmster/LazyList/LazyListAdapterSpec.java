package com.doodeec.filmster.LazyList;

import android.test.AndroidTestCase;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dusan Doodeec Bartos on 1.11.2014.
 *
 * @see com.doodeec.filmster.LazyList.LazyListAdapter
 */
public class LazyListAdapterSpec extends AndroidTestCase {
    private LazyListAdapter<String> mAdapter;

    private String firstItem;
    private String secondItem;

    public LazyListAdapterSpec() {
        super();
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        List<String> items = new ArrayList<String>();
        firstItem = "First Item";
        secondItem = "Second Item";

        items.add(firstItem);
        items.add(secondItem);

        mAdapter = new LazyListAdapter<String>(items, null);
    }

    public void testGetView() throws Exception {
        View firstView = mAdapter.getView(0, null, null);

        assertNotNull(firstView);
        assertNotNull(firstView.getTag());
        assertTrue(firstView.getTag() instanceof LazyListHolder);
        assertEquals(firstItem, ((TextView) firstView.findViewById(android.R.id.text1)).getText());

        View secondView = mAdapter.getView(1, null, null);

        assertNotNull(secondView);
        assertEquals(secondItem, ((TextView) secondView.findViewById(android.R.id.text1)).getText());
    }

    public void testRecycleView() throws Exception {
        View firstView = mAdapter.getView(0, null, null);
        View secondView = mAdapter.getView(1, firstView, null);

        assertNotNull(secondView);
        assertEquals(secondItem, ((TextView) secondView.findViewById(android.R.id.text1)).getText());
    }

    public void testGetCount() throws Exception {
        assertEquals(2, mAdapter.getCount());
    }

    public void testGetItemId() throws Exception {
        assertEquals(2, mAdapter.getItemId(2));
    }
}
