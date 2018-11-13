package rapid.decoder;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;

import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;
import rapid.decoder.test.R;

import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class BasicUnitTest {
    private Resources res;

    @Before
    public void setUp() {
        res = InstrumentationRegistry.getTargetContext().getResources();
    }

    @Test
    public void testDecoding1() {
        decodingTest(R.drawable.android);
    }

    @Test
    public void testDecoding2() {
        decodingTest(R.drawable.pond);
    }


    private void decodingTest(int id) {
        Bitmap bitmap = BitmapFactory.decodeResource(res, id);
        Bitmap bitmap2;

        BitmapDecoder d;
        int w, h;

        d = BitmapDecoder.from(res, id);
        w = d.width();
        h = d.height();
        bitmap2 = d.decode();
        assertNotNull(bitmap2);
        assertEquals(bitmap.getWidth(), bitmap2.getWidth());
        assertEquals(bitmap.getHeight(), bitmap2.getHeight());
        assertEquals(bitmap2.getWidth(), w);
        assertEquals(bitmap2.getHeight(), h);
        bitmap2.recycle();

        d = BitmapDecoder.from(res, id).useBuiltInDecoder();
        w = d.width();
        h = d.height();
        bitmap2 = d.decode();
        assertNotNull(bitmap2);
        assertEquals(bitmap.getWidth(), bitmap2.getWidth());
        assertEquals(bitmap.getHeight(), bitmap2.getHeight());
        assertEquals(bitmap2.getWidth(), w);
        assertEquals(bitmap2.getHeight(), h);
        bitmap2.recycle();

        d = BitmapDecoder.from(res, id).region(10, 10, 100, 90);
        w = d.width();
        h = d.height();
        bitmap2 = d.decode();
        assertNotNull(bitmap2);
        assertEquals(90, bitmap2.getWidth());
        assertEquals(80, bitmap2.getHeight());
        assertEquals(bitmap2.getWidth(), w);
        assertEquals(bitmap2.getHeight(), h);
        bitmap2.recycle();

        d = BitmapDecoder.from(res, id).region(10, 10, 100, 90).scaleBy(0.7f, 0.8f);
        w = d.width();
        h = d.height();
        bitmap2 = d.decode();
        assertNotNull(bitmap2);
        assertEquals(63, bitmap2.getWidth());
        assertEquals(64, bitmap2.getHeight());
        assertEquals(bitmap2.getWidth(), w);
        assertEquals(bitmap2.getHeight(), h);
        bitmap2.recycle();

        d = BitmapDecoder.from(res, id).region(10, 10, 100, 90).useBuiltInDecoder();
        w = d.width();
        h = d.height();
        bitmap2 = d.decode();
        assertNotNull(bitmap2);
        assertEquals(90, bitmap2.getWidth());
        assertEquals(80, bitmap2.getHeight());
        assertEquals(bitmap2.getWidth(), w);
        assertEquals(bitmap2.getHeight(), h);
        bitmap2.recycle();

        d = BitmapDecoder.from(res, id).region(10, 10, 100, 90).scaleBy(0.7f, 0.8f).useBuiltInDecoder();
        w = d.width();
        h = d.height();
        bitmap2 = d.decode();
        assertNotNull(bitmap2);
        assertEquals(63, bitmap2.getWidth());
        assertEquals(64, bitmap2.getHeight());
        assertEquals(bitmap2.getWidth(), w);
        assertEquals(bitmap2.getHeight(), h);
        bitmap2.recycle();

        d = BitmapDecoder.from(res, id).scale(210, 220);
        w = d.width();
        h = d.height();
        bitmap2 = d.decode();
        assertNotNull(bitmap2);
        assertEquals(210, bitmap2.getWidth());
        assertEquals(220, bitmap2.getHeight());
        assertEquals(bitmap2.getWidth(), w);
        assertEquals(bitmap2.getHeight(), h);
        bitmap2.recycle();

        final float SCALE_FACTOR = 0.5f;

        d = BitmapDecoder.from(res, id).scaleBy(SCALE_FACTOR);
        w = d.width();
        h = d.height();
        bitmap2 = d.decode();
        assertNotNull(bitmap2);
        assertEquals((int) Math.ceil(bitmap.getWidth() * SCALE_FACTOR), bitmap2.getWidth());
        assertEquals((int) Math.ceil(bitmap.getHeight() * SCALE_FACTOR), bitmap2.getHeight());
        assertEquals(bitmap2.getWidth(), w);
        assertEquals(bitmap2.getHeight(), h);
        bitmap2.recycle();

        d = BitmapDecoder.from(res, id).scaleBy(SCALE_FACTOR).useBuiltInDecoder();
        w = d.width();
        h = d.height();
        bitmap2 = d.decode();
        assertNotNull(bitmap2);
        assertEquals((int) Math.ceil(bitmap.getWidth() * SCALE_FACTOR), bitmap2.getWidth());
        assertEquals((int) Math.ceil(bitmap.getHeight() * SCALE_FACTOR), bitmap2.getHeight());
        assertEquals(bitmap2.getWidth(), w);
        assertEquals(bitmap2.getHeight(), h);
        bitmap2.recycle();

        // region after scale

        final float SCALE_FACTOR_AFTER_REGION = 0.7f;

        d = BitmapDecoder.from(res, id).scaleBy(SCALE_FACTOR_AFTER_REGION).region(10, 10, 30, 30).mutable();
        w = d.width();
        h = d.height();
        bitmap2 = d.decode();
        assertNotNull(bitmap2);
        assertEquals(bitmap2.getWidth(), w);
        assertEquals(bitmap2.getHeight(), h);

        bitmap2.recycle();
    }
}
