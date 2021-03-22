package com.gbcom.gwifi.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import com.gbcom.gwifi.util.Log;

public class ClipZoomImageView extends ImageView implements ScaleGestureDetector.OnScaleGestureListener, View.OnTouchListener, ViewTreeObserver.OnGlobalLayoutListener {

    /* renamed from: a */
    public static float f13575a = 4.0f;

    /* renamed from: b */
    private static final String f13576b = "ClipZoomImageView";

    /* renamed from: c */
    private static float f13577c = 2.0f;

    /* renamed from: d */
    private float f13578d;

    /* renamed from: e */
    private boolean f13579e;

    /* renamed from: f */
    private final float[] f13580f;

    /* renamed from: g */
    private ScaleGestureDetector f13581g;

    /* renamed from: h */
    private final Matrix f13582h;

    /* renamed from: i */
    private GestureDetector f13583i;

    /* renamed from: j */
    private boolean f13584j;

    /* renamed from: k */
    private int f13585k;

    /* renamed from: l */
    private float f13586l;

    /* renamed from: m */
    private float f13587m;

    /* renamed from: n */
    private boolean f13588n;

    /* renamed from: o */
    private int f13589o;

    /* renamed from: p */
    private int f13590p;

    public ClipZoomImageView(Context context) {
        this(context, null);
    }

    public ClipZoomImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f13578d = 1.0f;
        this.f13579e = true;
        this.f13580f = new float[9];
        this.f13581g = null;
        this.f13582h = new Matrix();
        setScaleType(ImageView.ScaleType.MATRIX);
        this.f13583i = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            /* class com.gbcom.gwifi.widget.ClipZoomImageView.C34741 */

            public boolean onDoubleTap(MotionEvent motionEvent) {
                if (!ClipZoomImageView.this.f13584j) {
                    float x = motionEvent.getX();
                    float y = motionEvent.getY();
                    if (ClipZoomImageView.this.mo28242a() < ClipZoomImageView.f13577c) {
                        ClipZoomImageView.this.postDelayed(new RunnableC3475a(ClipZoomImageView.f13577c, x, y), 16);
                        ClipZoomImageView.this.f13584j = true;
                    } else {
                        ClipZoomImageView.this.postDelayed(new RunnableC3475a(ClipZoomImageView.this.f13578d, x, y), 16);
                        ClipZoomImageView.this.f13584j = true;
                    }
                }
                return true;
            }
        });
        this.f13581g = new ScaleGestureDetector(context, this);
        setOnTouchListener(this);
    }

    /* renamed from: com.gbcom.gwifi.widget.ClipZoomImageView$a */
    private class RunnableC3475a implements Runnable {

        /* renamed from: a */
        static final float f13592a = 1.07f;

        /* renamed from: b */
        static final float f13593b = 0.93f;

        /* renamed from: d */
        private float f13595d;

        /* renamed from: e */
        private float f13596e;

        /* renamed from: f */
        private float f13597f;

        /* renamed from: g */
        private float f13598g;

        public RunnableC3475a(float f, float f2, float f3) {
            this.f13595d = f;
            this.f13597f = f2;
            this.f13598g = f3;
            if (ClipZoomImageView.this.mo28242a() < this.f13595d) {
                this.f13596e = f13592a;
            } else {
                this.f13596e = f13593b;
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            ClipZoomImageView.this.f13582h.postScale(this.f13596e, this.f13596e, this.f13597f, this.f13598g);
            ClipZoomImageView.this.m14633e();
            ClipZoomImageView.this.setImageMatrix(ClipZoomImageView.this.f13582h);
            float a = ClipZoomImageView.this.mo28242a();
            if ((this.f13596e <= 1.0f || a >= this.f13595d) && (this.f13596e >= 1.0f || this.f13595d >= a)) {
                float f = this.f13595d / a;
                ClipZoomImageView.this.f13582h.postScale(f, f, this.f13597f, this.f13598g);
                ClipZoomImageView.this.m14633e();
                ClipZoomImageView.this.setImageMatrix(ClipZoomImageView.this.f13582h);
                ClipZoomImageView.this.f13584j = false;
                return;
            }
            ClipZoomImageView.this.postDelayed(this, 16);
        }
    }

    public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
        float a = mo28242a();
        float scaleFactor = scaleGestureDetector.getScaleFactor();
        if (getDrawable() != null && ((a < f13575a && scaleFactor > 1.0f) || (a > this.f13578d && scaleFactor < 1.0f))) {
            if (scaleFactor * a < this.f13578d) {
                scaleFactor = this.f13578d / a;
            }
            if (scaleFactor * a > f13575a) {
                scaleFactor = f13575a / a;
            }
            this.f13582h.postScale(scaleFactor, scaleFactor, scaleGestureDetector.getFocusX(), scaleGestureDetector.getFocusY());
            m14633e();
            setImageMatrix(this.f13582h);
        }
        return true;
    }

    /* renamed from: d */
    private RectF m14631d() {
        Matrix matrix = this.f13582h;
        RectF rectF = new RectF();
        Drawable drawable = getDrawable();
        if (drawable != null) {
            rectF.set(0.0f, 0.0f, (float) drawable.getIntrinsicWidth(), (float) drawable.getIntrinsicHeight());
            matrix.mapRect(rectF);
        }
        return rectF;
    }

    public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
        return true;
    }

    public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        float f = 0.0f;
        if (!this.f13583i.onTouchEvent(motionEvent)) {
            this.f13581g.onTouchEvent(motionEvent);
            int pointerCount = motionEvent.getPointerCount();
            float f2 = 0.0f;
            float f3 = 0.0f;
            for (int i = 0; i < pointerCount; i++) {
                f3 += motionEvent.getX(i);
                f2 += motionEvent.getY(i);
            }
            float f4 = f3 / ((float) pointerCount);
            float f5 = f2 / ((float) pointerCount);
            if (pointerCount != this.f13589o) {
                this.f13588n = false;
                this.f13586l = f4;
                this.f13587m = f5;
            }
            this.f13589o = pointerCount;
            switch (motionEvent.getAction()) {
                case 1:
                case 3:
                    this.f13589o = 0;
                    break;
                case 2:
                    float f6 = f4 - this.f13586l;
                    float f7 = f5 - this.f13587m;
                    if (!this.f13588n) {
                        this.f13588n = m14625a(f6, f7);
                    }
                    if (this.f13588n && getDrawable() != null) {
                        RectF d = m14631d();
                        if (d.width() <= ((float) (getWidth() - (this.f13590p * 2)))) {
                            f6 = 0.0f;
                        }
                        if (d.height() > ((float) (getHeight() - (m14634f() * 2)))) {
                            f = f7;
                        }
                        this.f13582h.postTranslate(f6, f);
                        m14633e();
                        setImageMatrix(this.f13582h);
                    }
                    this.f13586l = f4;
                    this.f13587m = f5;
                    break;
            }
        }
        return true;
    }

    /* renamed from: a */
    public final float mo28242a() {
        this.f13582h.getValues(this.f13580f);
        return this.f13580f[0];
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getViewTreeObserver().removeGlobalOnLayoutListener(this);
    }

    public void onGlobalLayout() {
        Drawable drawable;
        float f;
        if (this.f13579e && (drawable = getDrawable()) != null) {
            int width = getWidth();
            int height = getHeight();
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            int width2 = getWidth() - (this.f13590p * 2);
            if (intrinsicWidth > width2 && intrinsicHeight < width2) {
                f = (((float) width2) * 1.0f) / ((float) intrinsicHeight);
            } else if (intrinsicHeight > width2 && intrinsicWidth < width2) {
                f = (((float) width2) * 1.0f) / ((float) intrinsicWidth);
            } else if (intrinsicWidth <= width2 || intrinsicHeight <= width2) {
                f = 1.0f;
            } else {
                f = Math.max((((float) width2) * 1.0f) / ((float) intrinsicWidth), (((float) width2) * 1.0f) / ((float) intrinsicHeight));
            }
            if (intrinsicWidth < width2 && intrinsicHeight > width2) {
                f = (((float) width2) * 1.0f) / ((float) intrinsicWidth);
            } else if (intrinsicHeight < width2 && intrinsicWidth > width2) {
                f = (((float) width2) * 1.0f) / ((float) intrinsicHeight);
            } else if (intrinsicWidth < width2 && intrinsicHeight < width2) {
                f = Math.max((((float) width2) * 1.0f) / ((float) intrinsicWidth), (1.0f * ((float) width2)) / ((float) intrinsicHeight));
            }
            this.f13578d = f;
            f13577c = this.f13578d * 2.0f;
            f13575a = this.f13578d * 4.0f;
            this.f13582h.postTranslate((float) ((width - intrinsicWidth) / 2), (float) ((height - intrinsicHeight) / 2));
            this.f13582h.postScale(f, f, (float) (getWidth() / 2), (float) (getHeight() / 2));
            setImageMatrix(this.f13582h);
            this.f13579e = false;
        }
    }

    /* renamed from: b */
    public Bitmap mo28245b() {
        Bitmap createBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_4444);
        draw(new Canvas(createBitmap));
        if ((m14634f() + getWidth()) - (this.f13590p * 2) <= createBitmap.getHeight()) {
            return mo28243a(Bitmap.createBitmap(createBitmap, this.f13590p, m14634f(), getWidth() - (this.f13590p * 2), getWidth() - (this.f13590p * 2)));
        }
        return mo28243a(Bitmap.createBitmap(createBitmap, this.f13590p, 0, getWidth() - (this.f13590p * 2), createBitmap.getHeight()));
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: e */
    private void m14633e() {
        float f;
        float f2 = 0.0f;
        RectF d = m14631d();
        int width = getWidth();
        int height = getHeight();
        if (((double) d.width()) + 0.01d >= ((double) (width - (this.f13590p * 2)))) {
            if (d.left > ((float) this.f13590p)) {
                f = (-d.left) + ((float) this.f13590p);
            } else {
                f = 0.0f;
            }
            if (d.right < ((float) (width - this.f13590p))) {
                f = ((float) (width - this.f13590p)) - d.right;
            }
        } else {
            f = 0.0f;
        }
        if (((double) d.height()) + 0.01d >= ((double) (height - (m14634f() * 2)))) {
            if (d.top > ((float) m14634f())) {
                f2 = (-d.top) + ((float) m14634f());
            }
            if (d.bottom < ((float) (height - m14634f()))) {
                f2 = ((float) (height - m14634f())) - d.bottom;
            }
        }
        this.f13582h.postTranslate(f, f2);
    }

    /* renamed from: a */
    private boolean m14625a(float f, float f2) {
        return Math.sqrt((double) ((f * f) + (f2 * f2))) >= ((double) this.f13585k);
    }

    /* renamed from: a */
    public void mo28244a(int i) {
        this.f13590p = i;
    }

    /* renamed from: f */
    private int m14634f() {
        int height = (getHeight() - (getWidth() - (this.f13590p * 2))) / 2;
        if (height > 0) {
            return height;
        }
        return 0;
    }

    /* renamed from: a */
    public Bitmap mo28243a(Bitmap bitmap) {
        float f;
        float f2;
        float f3;
        float f4;
        float f5;
        float f6;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width <= height) {
            f4 = (float) width;
            f3 = (float) width;
            f5 = (float) width;
            f6 = (float) width;
            f = (float) (width / 2);
            height = width;
            f2 = 0.0f;
        } else {
            f = (float) (height / 2);
            f2 = (float) ((width - height) / 2);
            f3 = ((float) width) - f2;
            f4 = (float) height;
            f5 = (float) height;
            f6 = (float) height;
            width = height;
        }
        Bitmap createBitmap = Bitmap.createBitmap(height, width, Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        Rect rect = new Rect((int) f2, (int) 0.0f, (int) f3, (int) f4);
        Rect rect2 = new Rect((int) 0.0f, (int) 0.0f, (int) f5, (int) f6);
        RectF rectF = new RectF(rect2);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(-12434878);
        canvas.drawRoundRect(rectF, f, f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        if (bitmap.isRecycled()) {
            Log.m14403e(f13576b, "ClipZoomImageView : bitmap is Recycled : " + ((Object) bitmap));
        }
        canvas.drawBitmap(bitmap, rect, rect2, paint);
        return createBitmap;
    }
}
