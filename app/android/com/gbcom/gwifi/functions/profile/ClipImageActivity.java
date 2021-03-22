package com.gbcom.gwifi.functions.profile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.util.GetPathFromUri4kitkat;
import com.gbcom.gwifi.util.Log;
import com.gbcom.gwifi.util.p257b.ImageTools;
import com.gbcom.gwifi.widget.ClipImageLayout;
import java.p456io.IOException;

public class ClipImageActivity extends Activity implements View.OnClickListener {
    public static final String RESULT_PATH = "crop_image";

    /* renamed from: a */
    private static final String f10815a = "ClipImageActivity";

    /* renamed from: b */
    private static final String f10816b = "path";

    /* renamed from: c */
    private ClipImageLayout f10817c = null;

    /* renamed from: d */
    private String f10818d;

    public static void startActivity(Activity activity, Uri uri, int i) {
        Intent intent = new Intent(activity, ClipImageActivity.class);
        intent.putExtra(f10816b, uri);
        activity.startActivityForResult(intent, i);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        Log.m14400c(f10815a, "ClipImageActivity  onCreate");
        super.onCreate(bundle);
        setContentView(C2425R.layout.common_crop_image_layout);
        this.f10817c = (ClipImageLayout) findViewById(C2425R.C2427id.clipImageLayout);
        Uri uri = (Uri) getIntent().getParcelableExtra(f10816b);
        Log.m14398b(f10815a, "ClipImageActivity path=" + ((Object) uri));
        this.f10818d = GetPathFromUri4kitkat.m14236a(GBApplication.instance(), uri);
        if (this.f10818d == null) {
            Log.m14403e(f10815a, "ClipImageActivity getPath fail " + ((Object) uri));
            return;
        }
        int b = m12228b(this.f10818d);
        Bitmap a = m12226a(this.f10818d);
        if (a == null) {
            Log.m14403e(f10815a, "ClipImageActivity createBitmap fail " + ((Object) uri));
            finish();
        } else if (b == 0) {
            this.f10817c.mo28240a(a);
        } else {
            this.f10817c.mo28240a(m12224a(b, a));
        }
        findViewById(C2425R.C2427id.okBtn).setOnClickListener(this);
        findViewById(C2425R.C2427id.cancleBtn).setOnClickListener(this);
    }

    public void onClick(View view) {
        if (view.getId() == 2131755289) {
            Bitmap a = this.f10817c.mo28238a();
            String storagePath = MyProfileActivity.getStoragePath();
            m12227a(m12225a(a), storagePath);
            Intent intent = new Intent();
            intent.putExtra(RESULT_PATH, storagePath);
            setResult(-1, intent);
        }
        finish();
    }

    /* renamed from: a */
    private Bitmap m12225a(Bitmap bitmap) {
        double e = (double) (ImageTools.m14167e(bitmap) / 1000);
        if (e <= 50.0d) {
            return bitmap;
        }
        double d = e / 50.0d;
        return zoomImage(bitmap, ((double) bitmap.getWidth()) / Math.sqrt(d), ((double) bitmap.getHeight()) / Math.sqrt(d));
    }

    public static Bitmap zoomImage(Bitmap bitmap, double d, double d2) {
        float width = (float) bitmap.getWidth();
        float height = (float) bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(((float) d) / width, ((float) d2) / height);
        return Bitmap.createBitmap(bitmap, 0, 0, (int) width, (int) height, matrix, true);
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0033 A[SYNTHETIC, Splitter:B:18:0x0033] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0040 A[SYNTHETIC, Splitter:B:25:0x0040] */
    /* JADX WARNING: Removed duplicated region for block: B:35:? A[RETURN, SYNTHETIC] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m12227a(android.graphics.Bitmap r4, java.lang.String r5) {
        /*
            r3 = this;
            java.io.File r0 = new java.io.File
            r0.<init>(r5)
            boolean r1 = r0.exists()
            if (r1 == 0) goto L_0x000e
            r0.delete()
        L_0x000e:
            r2 = 0
            r0.createNewFile()     // Catch:{ IOException -> 0x002c, all -> 0x003c }
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x002c, all -> 0x003c }
            r1.<init>(r0)     // Catch:{ IOException -> 0x002c, all -> 0x003c }
            android.graphics.Bitmap$CompressFormat r0 = android.graphics.Bitmap.CompressFormat.PNG     // Catch:{ IOException -> 0x004b }
            r2 = 100
            r4.compress(r0, r2, r1)     // Catch:{ IOException -> 0x004b }
            r1.flush()     // Catch:{ IOException -> 0x004b }
            if (r1 == 0) goto L_0x0026
            r1.close()     // Catch:{ IOException -> 0x0027 }
        L_0x0026:
            return
        L_0x0027:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0026
        L_0x002c:
            r0 = move-exception
            r1 = r2
        L_0x002e:
            r0.printStackTrace()     // Catch:{ all -> 0x0049 }
            if (r1 == 0) goto L_0x0026
            r1.close()     // Catch:{ IOException -> 0x0037 }
            goto L_0x0026
        L_0x0037:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0026
        L_0x003c:
            r0 = move-exception
            r1 = r2
        L_0x003e:
            if (r1 == 0) goto L_0x0043
            r1.close()     // Catch:{ IOException -> 0x0044 }
        L_0x0043:
            throw r0
        L_0x0044:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0043
        L_0x0049:
            r0 = move-exception
            goto L_0x003e
        L_0x004b:
            r0 = move-exception
            goto L_0x002e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gbcom.gwifi.functions.profile.ClipImageActivity.m12227a(android.graphics.Bitmap, java.lang.String):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0036 A[SYNTHETIC, Splitter:B:18:0x0036] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0044 A[SYNTHETIC, Splitter:B:25:0x0044] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.graphics.Bitmap m12226a(java.lang.String r6) {
        /*
            r5 = this;
            r4 = 1
            r3 = 0
            r0 = 0
            if (r6 != 0) goto L_0x0006
        L_0x0005:
            return r0
        L_0x0006:
            android.graphics.BitmapFactory$Options r1 = new android.graphics.BitmapFactory$Options
            r1.<init>()
            r2 = 2
            r1.inSampleSize = r2
            r1.inJustDecodeBounds = r3
            r1.inPurgeable = r4
            r1.inInputShareable = r4
            r1.inDither = r3
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ IOException -> 0x002f, all -> 0x003f }
            r2.<init>(r6)     // Catch:{ IOException -> 0x002f, all -> 0x003f }
            java.io.FileDescriptor r3 = r2.getFD()     // Catch:{ IOException -> 0x004f }
            r4 = 0
            android.graphics.Bitmap r0 = android.graphics.BitmapFactory.decodeFileDescriptor(r3, r4, r1)     // Catch:{ IOException -> 0x004f }
            if (r2 == 0) goto L_0x0005
            r2.close()     // Catch:{ IOException -> 0x002a }
            goto L_0x0005
        L_0x002a:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0005
        L_0x002f:
            r1 = move-exception
            r2 = r0
        L_0x0031:
            r1.printStackTrace()     // Catch:{ all -> 0x004d }
            if (r2 == 0) goto L_0x0005
            r2.close()     // Catch:{ IOException -> 0x003a }
            goto L_0x0005
        L_0x003a:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0005
        L_0x003f:
            r1 = move-exception
            r2 = r0
            r0 = r1
        L_0x0042:
            if (r2 == 0) goto L_0x0047
            r2.close()     // Catch:{ IOException -> 0x0048 }
        L_0x0047:
            throw r0
        L_0x0048:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0047
        L_0x004d:
            r0 = move-exception
            goto L_0x0042
        L_0x004f:
            r1 = move-exception
            goto L_0x0031
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gbcom.gwifi.functions.profile.ClipImageActivity.m12226a(java.lang.String):android.graphics.Bitmap");
    }

    /* renamed from: b */
    private int m12228b(String str) {
        try {
            switch (new ExifInterface(str).getAttributeInt("Orientation", 1)) {
                case 3:
                    return 180;
                case 4:
                case 5:
                case 7:
                default:
                    return 0;
                case 6:
                    return 90;
                case 8:
                    return 270;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /* renamed from: a */
    private Bitmap m12224a(int i, Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.postRotate((float) i);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, false);
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, float f, float f2) {
        int i = options.outHeight;
        int i2 = options.outWidth;
        if (((float) i) <= f2 && ((float) i2) <= f) {
            return 1;
        }
        int round = Math.round(((float) i) / f2);
        int round2 = Math.round(((float) i2) / f);
        return round < round2 ? round : round2;
    }

    public static String getRealFilePath(Context context, Uri uri) {
        Cursor query;
        int columnIndex;
        String str = null;
        if (uri == null) {
            return null;
        }
        String scheme = uri.getScheme();
        if (scheme == null) {
            return uri.getPath();
        }
        if ("file".equals(scheme)) {
            return uri.getPath();
        }
        if (!"content".equals(scheme) || (query = context.getContentResolver().query(uri, new String[]{"_data"}, null, null, null)) == null) {
            return null;
        }
        if (query.moveToFirst() && (columnIndex = query.getColumnIndex("_data")) > -1) {
            str = query.getString(columnIndex);
        }
        query.close();
        return str;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        Log.m14400c(f10815a, "ClipImageActivity  onResume");
        super.onResume();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        Log.m14400c(f10815a, "ClipImageActivity  onPause");
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        Log.m14400c(f10815a, "ClipImageActivity  onDestroy");
        super.onDestroy();
    }
}
