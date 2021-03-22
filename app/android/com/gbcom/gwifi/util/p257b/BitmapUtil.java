package com.gbcom.gwifi.util.p257b;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;
import com.gbcom.gwifi.util.Log;
import java.net.HttpURLConnection;
import java.net.URL;
import java.p456io.ByteArrayOutputStream;
import java.p456io.IOException;
import java.p456io.InputStream;
import org.apache.http.util.ByteArrayBuffer;

/* renamed from: com.gbcom.gwifi.util.b.a */
public class BitmapUtil {

    /* renamed from: a */
    private static final String f13138a = "BitmapUtil";

    /* renamed from: a */
    public static Bitmap m14119a(Context context, int i) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inPurgeable = true;
        options.inInputShareable = true;
        return BitmapFactory.decodeStream(context.getResources().openRawResource(i), null, options);
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0030 A[SYNTHETIC, Splitter:B:17:0x0030] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x003e A[SYNTHETIC, Splitter:B:24:0x003e] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.Bitmap m14120a(android.content.Context r4, java.lang.String r5) {
        /*
            r3 = 1
            r0 = 0
            android.graphics.BitmapFactory$Options r1 = new android.graphics.BitmapFactory$Options
            r1.<init>()
            android.graphics.Bitmap$Config r2 = android.graphics.Bitmap.Config.RGB_565
            r1.inPreferredConfig = r2
            r1.inPurgeable = r3
            r1.inInputShareable = r3
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ FileNotFoundException -> 0x0029, all -> 0x0039 }
            java.io.File r3 = new java.io.File     // Catch:{ FileNotFoundException -> 0x0029, all -> 0x0039 }
            r3.<init>(r5)     // Catch:{ FileNotFoundException -> 0x0029, all -> 0x0039 }
            r2.<init>(r3)     // Catch:{ FileNotFoundException -> 0x0029, all -> 0x0039 }
            r3 = 0
            android.graphics.Bitmap r0 = android.graphics.BitmapFactory.decodeStream(r2, r3, r1)     // Catch:{ FileNotFoundException -> 0x0049 }
            if (r2 == 0) goto L_0x0023
            r2.close()     // Catch:{ IOException -> 0x0024 }
        L_0x0023:
            return r0
        L_0x0024:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0023
        L_0x0029:
            r1 = move-exception
            r2 = r0
        L_0x002b:
            r1.printStackTrace()     // Catch:{ all -> 0x0047 }
            if (r2 == 0) goto L_0x0023
            r2.close()     // Catch:{ IOException -> 0x0034 }
            goto L_0x0023
        L_0x0034:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0023
        L_0x0039:
            r1 = move-exception
            r2 = r0
            r0 = r1
        L_0x003c:
            if (r2 == 0) goto L_0x0041
            r2.close()     // Catch:{ IOException -> 0x0042 }
        L_0x0041:
            throw r0
        L_0x0042:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0041
        L_0x0047:
            r0 = move-exception
            goto L_0x003c
        L_0x0049:
            r1 = move-exception
            goto L_0x002b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gbcom.gwifi.util.p257b.BitmapUtil.m14120a(android.content.Context, java.lang.String):android.graphics.Bitmap");
    }

    /* renamed from: a */
    public static Bitmap m14121a(Context context, byte[] bArr) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inPurgeable = true;
        options.inInputShareable = true;
        return BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
    }

    /* renamed from: b */
    public static byte[] m14125b(Context context, int i) {
        byte[] bArr = null;
        InputStream openRawResource = context.getResources().openRawResource(i);
        byte[] bArr2 = new byte[1024];
        ByteArrayBuffer byteArrayBuffer = new ByteArrayBuffer(1048576);
        while (true) {
            try {
                int read = openRawResource.read(bArr2);
                if (read == -1) {
                    break;
                }
                byteArrayBuffer.append(bArr2, 0, read);
            } catch (IOException e) {
                e.printStackTrace();
                try {
                    openRawResource.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            } catch (Throwable th) {
                try {
                    openRawResource.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
                throw th;
            }
        }
        bArr = byteArrayBuffer.toByteArray();
        try {
            openRawResource.close();
        } catch (IOException e4) {
            e4.printStackTrace();
        }
        return bArr;
    }

    /* renamed from: a */
    public static void m14123a(Bitmap bitmap) {
        if (bitmap != null && !bitmap.isRecycled()) {
            bitmap.recycle();
            System.m38963gc();
        }
    }

    /* renamed from: a */
    public static void m14124a(ImageView imageView) {
        if (!(imageView == null || imageView.getDrawable() == null)) {
            Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
            imageView.setImageDrawable(null);
            if (bitmap != null) {
                bitmap.recycle();
            }
        }
        System.m38963gc();
    }

    /* renamed from: b */
    public static byte[] m14126b(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    /* renamed from: a */
    public static Bitmap m14122a(String str) {
        Exception e;
        Bitmap bitmap;
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setConnectTimeout(6000);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setUseCaches(false);
            InputStream inputStream = httpURLConnection.getInputStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
            try {
                inputStream.close();
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                Log.m14403e(f13138a, "offlineFaceDetect:" + e.getMessage());
                return bitmap;
            }
        } catch (Exception e3) {
            bitmap = null;
            e = e3;
            e.printStackTrace();
            Log.m14403e(f13138a, "offlineFaceDetect:" + e.getMessage());
            return bitmap;
        }
        return bitmap;
    }
}
