package com.gbcom.gwifi.util.p257b;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;
import com.gbcom.gwifi.util.C3467s;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import java.p456io.ByteArrayInputStream;
import java.p456io.ByteArrayOutputStream;
import java.p456io.File;
import java.p456io.FileNotFoundException;
import java.p456io.FileOutputStream;
import java.p456io.IOException;
import java.p456io.InputStream;
import org.apache.xpath.compiler.PsuedoNames;

/* renamed from: com.gbcom.gwifi.util.b.c */
public final class ImageTools {
    /* renamed from: a */
    public static Bitmap m14140a(Drawable drawable) {
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        Bitmap createBitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, drawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
        drawable.draw(canvas);
        return createBitmap;
    }

    /* renamed from: a */
    public static Drawable m14144a(Bitmap bitmap) {
        return new BitmapDrawable(bitmap);
    }

    /* renamed from: a */
    public static Bitmap m14141a(InputStream inputStream) throws Exception {
        return BitmapFactory.decodeStream(inputStream);
    }

    /* renamed from: a */
    public static Bitmap m14143a(byte[] bArr) {
        if (bArr.length != 0) {
            return BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
        }
        return null;
    }

    /* renamed from: b */
    public static Drawable m14158b(byte[] bArr) {
        ByteArrayInputStream byteArrayInputStream;
        if (bArr != null) {
            byteArrayInputStream = new ByteArrayInputStream(bArr);
        } else {
            byteArrayInputStream = null;
        }
        return Drawable.createFromStream(byteArrayInputStream, null);
    }

    /* renamed from: b */
    public static byte[] m14160b(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    /* renamed from: b */
    public static byte[] m14161b(Drawable drawable) {
        return m14160b(((BitmapDrawable) drawable).getBitmap());
    }

    /* renamed from: c */
    public static Bitmap m14162c(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.preScale(1.0f, -1.0f);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, height / 2, width, height / 2, matrix, false);
        Bitmap createBitmap2 = Bitmap.createBitmap(width, (height / 2) + height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap2);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
        canvas.drawRect(0.0f, (float) height, (float) width, (float) (height + 4), new Paint());
        canvas.drawBitmap(createBitmap, 0.0f, (float) (height + 4), (Paint) null);
        Paint paint = new Paint();
        paint.setShader(new LinearGradient(0.0f, (float) bitmap.getHeight(), 0.0f, (float) (createBitmap2.getHeight() + 4), 1895825407, 16777215, Shader.TileMode.CLAMP));
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawRect(0.0f, (float) height, (float) width, (float) (createBitmap2.getHeight() + 4), paint);
        return createBitmap2;
    }

    /* renamed from: a */
    public static Bitmap m14138a(Bitmap bitmap, float f) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, width, height);
        RectF rectF = new RectF(rect);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(-12434878);
        canvas.drawRoundRect(rectF, f, f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return createBitmap;
    }

    /* renamed from: d */
    public static Bitmap m14165d(Bitmap bitmap) {
        if (bitmap == null) {
            return bitmap;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float f = (float) width;
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, width, height);
        Rect rect2 = new Rect(2, 2, width - 2, height - 2);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(f / 2.0f, f / 2.0f, f / 2.0f, paint);
        canvas.drawBitmap(bitmap, rect, rect2, paint);
        return createBitmap;
    }

    /* renamed from: a */
    public static Bitmap m14139a(Bitmap bitmap, int i, int i2) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(((float) i) / ((float) width), ((float) i2) / ((float) height));
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
    }

    /* renamed from: a */
    public static Drawable m14145a(Drawable drawable, int i, int i2) {
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        Bitmap a = m14140a(drawable);
        Matrix matrix = new Matrix();
        matrix.postScale(((float) i) / ((float) intrinsicWidth), ((float) i2) / ((float) intrinsicHeight));
        return new BitmapDrawable(Bitmap.createBitmap(a, 0, 0, intrinsicWidth, intrinsicHeight, matrix, true));
    }

    /* renamed from: a */
    public static Bitmap m14142a(String str, String str2) {
        Bitmap decodeFile = BitmapFactory.decodeFile(str + PsuedoNames.PSEUDONAME_ROOT + str2 + ".png");
        if (decodeFile == null) {
            return null;
        }
        return decodeFile;
    }

    /* renamed from: a */
    public static boolean m14156a() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    /* renamed from: b */
    public static boolean m14159b(String str, String str2) {
        File[] listFiles;
        if (!m14156a()) {
            return false;
        }
        if (!new File(str).exists()) {
            return false;
        }
        boolean z = false;
        for (File file : new File(str).listFiles()) {
            if (file.getName().split("\\.")[0].equals(str2)) {
                z = true;
            }
        }
        return z;
    }

    /* renamed from: a */
    public static String m14146a(Bitmap bitmap, String str, String str2) {
        FileOutputStream fileOutputStream;
        Throwable th;
        IOException e;
        FileNotFoundException e2;
        String str3 = "";
        if (m14156a()) {
            File file = new File(str);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            File file2 = new File(str, str2 + ".png");
            try {
                fileOutputStream = new FileOutputStream(file2);
                if (bitmap != null) {
                    try {
                        if (bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)) {
                            fileOutputStream.flush();
                            str3 = file2.getPath();
                        }
                    } catch (FileNotFoundException e3) {
                        e2 = e3;
                        try {
                            file2.delete();
                            e2.printStackTrace();
                            try {
                                fileOutputStream.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                            return str3;
                        } catch (Throwable th2) {
                            th = th2;
                            try {
                                fileOutputStream.close();
                            } catch (IOException e5) {
                                e5.printStackTrace();
                            }
                            throw th;
                        }
                    } catch (IOException e6) {
                        e = e6;
                        file2.delete();
                        e.printStackTrace();
                        try {
                            fileOutputStream.close();
                        } catch (IOException e7) {
                            e7.printStackTrace();
                        }
                        return str3;
                    }
                }
                try {
                    fileOutputStream.close();
                } catch (IOException e8) {
                    e8.printStackTrace();
                }
            } catch (FileNotFoundException e9) {
                e2 = e9;
                fileOutputStream = null;
                file2.delete();
                e2.printStackTrace();
                fileOutputStream.close();
                return str3;
            } catch (IOException e10) {
                e = e10;
                fileOutputStream = null;
                file2.delete();
                e.printStackTrace();
                fileOutputStream.close();
                return str3;
            } catch (Throwable th3) {
                th = th3;
                fileOutputStream = null;
                fileOutputStream.close();
                throw th;
            }
        }
        return str3;
    }

    /* renamed from: a */
    public static void m14155a(String str, String str2, Bitmap bitmap) {
        FileOutputStream fileOutputStream;
        Throwable th;
        IOException e;
        FileNotFoundException e2;
        if (Environment.getExternalStorageState().equals("mounted")) {
            File file = new File(str);
            if (!file.exists()) {
                file.mkdirs();
            }
            Log.d("ImageTools", str + str2);
            File file2 = new File(str, str2);
            try {
                fileOutputStream = new FileOutputStream(file2);
                if (bitmap != null) {
                    try {
                        if (bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)) {
                            fileOutputStream.flush();
                        }
                    } catch (FileNotFoundException e3) {
                        e2 = e3;
                        try {
                            file2.delete();
                            e2.printStackTrace();
                            try {
                                fileOutputStream.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                                return;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            try {
                                fileOutputStream.close();
                            } catch (IOException e5) {
                                e5.printStackTrace();
                            }
                            throw th;
                        }
                    } catch (IOException e6) {
                        e = e6;
                        file2.delete();
                        e.printStackTrace();
                        try {
                            fileOutputStream.close();
                        } catch (IOException e7) {
                            e7.printStackTrace();
                            return;
                        }
                    }
                }
                try {
                    fileOutputStream.close();
                } catch (IOException e8) {
                    e8.printStackTrace();
                }
            } catch (FileNotFoundException e9) {
                e2 = e9;
                fileOutputStream = null;
                file2.delete();
                e2.printStackTrace();
                fileOutputStream.close();
            } catch (IOException e10) {
                e = e10;
                fileOutputStream = null;
                file2.delete();
                e.printStackTrace();
                fileOutputStream.close();
            } catch (Throwable th3) {
                th = th3;
                fileOutputStream = null;
                fileOutputStream.close();
                throw th;
            }
        }
    }

    /* renamed from: a */
    public static void m14147a(String str) {
        File[] listFiles;
        if (m14156a()) {
            for (File file : new File(str).listFiles()) {
                file.delete();
            }
        }
    }

    /* renamed from: c */
    public static void m14164c(String str, String str2) {
        if (m14156a()) {
            File[] listFiles = new File(str).listFiles();
            for (int i = 0; i < listFiles.length; i++) {
                if (listFiles[i].getName().equals(str2)) {
                    listFiles[i].delete();
                }
            }
        }
    }

    /* renamed from: b */
    public static int m14157b(String str) {
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
    public static Bitmap m14137a(int i, Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.postRotate((float) i);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, false);
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0038 A[SYNTHETIC, Splitter:B:18:0x0038] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0046 A[SYNTHETIC, Splitter:B:25:0x0046] */
    /* renamed from: c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.Bitmap m14163c(java.lang.String r5) {
        /*
            r4 = 0
            r3 = 1
            r0 = 0
            if (r5 != 0) goto L_0x0006
        L_0x0005:
            return r0
        L_0x0006:
            android.graphics.BitmapFactory$Options r1 = new android.graphics.BitmapFactory$Options
            r1.<init>()
            r2 = 2
            r1.inSampleSize = r2
            r1.inJustDecodeBounds = r4
            r1.inPurgeable = r3
            r1.inInputShareable = r3
            r1.inDither = r4
            r1.inPurgeable = r3
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0031, all -> 0x0041 }
            r2.<init>(r5)     // Catch:{ IOException -> 0x0031, all -> 0x0041 }
            java.io.FileDescriptor r3 = r2.getFD()     // Catch:{ IOException -> 0x0051 }
            r4 = 0
            android.graphics.Bitmap r0 = android.graphics.BitmapFactory.decodeFileDescriptor(r3, r4, r1)     // Catch:{ IOException -> 0x0051 }
            if (r2 == 0) goto L_0x0005
            r2.close()     // Catch:{ IOException -> 0x002c }
            goto L_0x0005
        L_0x002c:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0005
        L_0x0031:
            r1 = move-exception
            r2 = r0
        L_0x0033:
            r1.printStackTrace()     // Catch:{ all -> 0x004f }
            if (r2 == 0) goto L_0x0005
            r2.close()     // Catch:{ IOException -> 0x003c }
            goto L_0x0005
        L_0x003c:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0005
        L_0x0041:
            r1 = move-exception
            r2 = r0
            r0 = r1
        L_0x0044:
            if (r2 == 0) goto L_0x0049
            r2.close()     // Catch:{ IOException -> 0x004a }
        L_0x0049:
            throw r0
        L_0x004a:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0049
        L_0x004f:
            r0 = move-exception
            goto L_0x0044
        L_0x0051:
            r1 = move-exception
            goto L_0x0033
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gbcom.gwifi.util.p257b.ImageTools.m14163c(java.lang.String):android.graphics.Bitmap");
    }

    /* renamed from: e */
    public static long m14167e(Bitmap bitmap) {
        if (Build.VERSION.SDK_INT >= 19) {
            return (long) bitmap.getAllocationByteCount();
        }
        if (Build.VERSION.SDK_INT >= 12) {
            return (long) bitmap.getByteCount();
        }
        return (long) (bitmap.getRowBytes() * bitmap.getHeight());
    }

    /* renamed from: d */
    public static Bitmap m14166d(String str) {
        if (str == null || str.equals("null") || C3467s.m14513e(str)) {
            return null;
        }
        return ImageLoader.getInstance().loadImageSync(str);
    }

    /* renamed from: a */
    public static void m14148a(String str, ImageView imageView) {
        if (str != null && !str.equals("null") && !C3467s.m14513e(str)) {
            ImageLoader.getInstance().displayImage(str, imageView);
        }
    }

    /* renamed from: a */
    public static void m14149a(String str, ImageView imageView, DisplayImageOptions displayImageOptions) {
        if (str != null && !str.equals("null") && !C3467s.m14513e(str)) {
            ImageLoader.getInstance().displayImage(str, imageView, displayImageOptions);
        }
    }

    /* renamed from: a */
    public static void m14152a(String str, ImageView imageView, DisplayImageOptions displayImageOptions, Enum<ImageLoader.DEALIMAGE> r4) {
        if (str != null && !str.equals("null") && !C3467s.m14513e(str)) {
            ImageLoader.getInstance().displayImage(str, imageView, displayImageOptions, r4);
        }
    }

    /* renamed from: a */
    public static void m14153a(String str, ImageView imageView, ImageLoadingListener imageLoadingListener) {
        if (str != null && !str.equals("null") && !C3467s.m14513e(str)) {
            ImageLoader.getInstance().displayImage(str, imageView, imageLoadingListener);
        }
    }

    /* renamed from: a */
    public static void m14150a(String str, ImageView imageView, DisplayImageOptions displayImageOptions, ImageLoadingListener imageLoadingListener) {
        if (str != null && !str.equals("null") && !C3467s.m14513e(str)) {
            ImageLoader.getInstance().displayImage(str, imageView, displayImageOptions, imageLoadingListener);
        }
    }

    /* renamed from: a */
    public static void m14151a(String str, ImageView imageView, DisplayImageOptions displayImageOptions, ImageLoadingListener imageLoadingListener, Enum<ImageLoader.DEALIMAGE> r10) {
        if (str != null && !str.equals("null") && !C3467s.m14513e(str)) {
            ImageLoader.getInstance().displayImage(str, imageView, displayImageOptions, imageLoadingListener, r10);
        }
    }

    /* renamed from: a */
    public static void m14154a(String str, ImageLoadingListener imageLoadingListener) {
        if (str != null && !str.equals("null") && !C3467s.m14513e(str)) {
            ImageLoader.getInstance().loadImage(str, imageLoadingListener);
        }
    }
}
