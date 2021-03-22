package com.gbcom.gwifi.functions.test;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.widget.ScrollView;
import java.p456io.ByteArrayInputStream;
import java.p456io.ByteArrayOutputStream;

/* renamed from: com.gbcom.gwifi.functions.test.a */
public class DeviceTest2Util {
    /* renamed from: a */
    public static Bitmap m13711a(ScrollView scrollView) {
        int i = 0;
        for (int i2 = 0; i2 < scrollView.getChildCount(); i2++) {
            i += scrollView.getChildAt(i2).getHeight();
            scrollView.getChildAt(i2).setBackgroundColor(Color.parseColor("#ffffff"));
        }
        Bitmap createBitmap = Bitmap.createBitmap(scrollView.getWidth(), i, Bitmap.Config.RGB_565);
        scrollView.draw(new Canvas(createBitmap));
        return createBitmap;
    }

    /* renamed from: a */
    public static Bitmap m13710a(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        int i = 100;
        while (byteArrayOutputStream.toByteArray().length / 1024 > 100) {
            byteArrayOutputStream.reset();
            bitmap.compress(Bitmap.CompressFormat.JPEG, i, byteArrayOutputStream);
            i -= 10;
        }
        return BitmapFactory.decodeStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()), null, null);
    }
}
