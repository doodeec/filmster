package com.doodeec.filmster.LazyList;

import android.test.AndroidTestCase;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Dusan Doodeec Bartos on 1.11.2014.
 *
 * @see com.doodeec.filmster.LazyList.LazyListHolder
 */
public class LazyListHolderSpec extends AndroidTestCase {

    private View view;
    private LazyListHolder holder;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        view = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, null);
        holder = new LazyListHolder(view);
    }

    public void testLazyListHolder() throws Exception {
        holder.setText("firstText");
        assertEquals("firstText", ((TextView) view.findViewById(android.R.id.text1)).getText());

        holder.setText("secondText");
        assertEquals("secondText", ((TextView) view.findViewById(android.R.id.text1)).getText());
    }
}
