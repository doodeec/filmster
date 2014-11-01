package com.doodeec.filmster.LazyList;

import android.view.View;
import android.widget.TextView;

/**
 * Created by Dusan Doodeec Bartos on 1.11.2014.
 *
 * Default holder for Lazy ListView
 * @see com.doodeec.filmster.LazyList.LazyList
 * @see com.doodeec.filmster.LazyList.LazyListAdapter
 */
@SuppressWarnings("unused")
public class LazyListHolder {
    private TextView mText;

    protected LazyListHolder(View v) {
        mText = (TextView) v.findViewById(android.R.id.text1);
        if (mText == null) {
            throw new AssertionError("Lazy List holder has incorrect layout");
        }
    }

    public void setText(String text) {
        mText.setText(text);
    }
}
