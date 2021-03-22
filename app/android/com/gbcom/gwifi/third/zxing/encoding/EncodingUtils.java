package com.gbcom.gwifi.third.zxing.encoding;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import com.p136b.p145b.BarcodeFormat;
import com.p136b.p145b.EncodeHintType;
import com.p136b.p145b.WriterException;
import com.p136b.p145b.p154c.BitMatrix;
import com.p136b.p145b.p175i.QRCodeWriter;
import com.p136b.p145b.p175i.p176a.ErrorCorrectionLevel;
import java.util.HashMap;

public class EncodingUtils {
    public static Bitmap createQRCode(String str, int i, int i2, Bitmap bitmap) {
        if (str != null) {
            try {
                if (!"".equals(str)) {
                    HashMap hashMap = new HashMap();
                    hashMap.put(EncodeHintType.CHARACTER_SET, "utf-8");
                    hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.f5192d);
                    BitMatrix a = new QRCodeWriter().mo21721a(str, BarcodeFormat.QR_CODE, i, i2, hashMap);
                    int[] iArr = new int[(i * i2)];
                    for (int i3 = 0; i3 < i2; i3++) {
                        for (int i4 = 0; i4 < i; i4++) {
                            if (a.mo21896a(i4, i3)) {
                                iArr[(i3 * i) + i4] = -16777216;
                            } else {
                                iArr[(i3 * i) + i4] = -1;
                            }
                        }
                    }
                    Bitmap createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
                    createBitmap.setPixels(iArr, 0, i, 0, 0, i, i2);
                    if (bitmap != null) {
                        return addLogo(createBitmap, bitmap);
                    }
                    return createBitmap;
                }
            } catch (WriterException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    private static Bitmap addLogo(Bitmap bitmap, Bitmap bitmap2) {
        if (bitmap == null) {
            return null;
        }
        if (bitmap2 == null) {
            return bitmap;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int width2 = bitmap2.getWidth();
        int height2 = bitmap2.getHeight();
        if (width == 0 || height == 0) {
            return null;
        }
        if (width2 == 0 || height2 == 0) {
            return bitmap;
        }
        float f = ((((float) width) * 1.0f) / 5.0f) / ((float) width2);
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        try {
            Canvas canvas = new Canvas(createBitmap);
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
            canvas.scale(f, f, (float) (width / 2), (float) (height / 2));
            canvas.drawBitmap(bitmap2, (float) ((width - width2) / 2), (float) ((height - height2) / 2), (Paint) null);
            canvas.save();
            canvas.restore();
        } catch (Exception e) {
            e.getStackTrace();
            createBitmap = null;
        }
        return createBitmap;
    }
}
