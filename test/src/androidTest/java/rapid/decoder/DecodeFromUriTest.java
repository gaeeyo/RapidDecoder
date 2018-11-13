package rapid.decoder;

import android.content.Context;
import android.graphics.Bitmap;

import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(AndroidJUnit4.class)
public class DecodeFromUriTest {

    private Context getContext() {
        return InstrumentationRegistry.getTargetContext();
    }

    @Test
    public void testInvalidUri() {
        Bitmap bitmap = BitmapDecoder.from(getContext(), "android.resource://a.b.c/12345").decode();
        assertNull(bitmap);

        bitmap = BitmapDecoder.from(getContext(), "://a.b.c/12345").decode();
        assertNull(bitmap);
    }

    @Test
    public void testValidUri() {
        Bitmap bitmap = BitmapDecoder.from(getContext(), "android.resource://rapid.decoder" +
                ".test/drawable/android").decode();
        assertNotNull(bitmap);

        bitmap = BitmapDecoder.from(getContext(), "android.resource://rapid.decoder" +
                ".test/" + R.drawable.pond).decode();
        assertNotNull(bitmap);
    }
}
