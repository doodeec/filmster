package com.doodeec.filmster;

import android.graphics.Bitmap;
import android.os.Build;
import android.test.InstrumentationTestCase;

/**
 * Created by dbartos on 30.10.2014.
 *
 * @see com.doodeec.filmster.ImageCache
 */
public class ImageCacheSpec extends InstrumentationTestCase {

    private Bitmap mImage;
    private String mImageKey = "myNewImage";

    @Override
    protected void setUp() throws Exception {
        mImage = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
        assertNotNull(mImage);
    }

    /**
     * Should throw an exception if something goes wrong during adding bitmap to cache
     * @throws Exception
     */
    public void testAddToCacheMethod() throws Exception {
        ImageCache.addBitmapToCache(mImageKey, mImage);
    }

    public void testGetFromCacheMethod() throws Exception {
        ImageCache.addBitmapToCache(mImageKey, mImage);

        assertNotNull(ImageCache.getBitmapFromCache(mImageKey));
        assertEquals(mImage, ImageCache.getBitmapFromCache(mImageKey));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
            assertTrue(mImage.sameAs(ImageCache.getBitmapFromCache(mImageKey)));
        }
    }
}
