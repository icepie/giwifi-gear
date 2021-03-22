package com.gbcom.gwifi.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.gbcom.gwifi.C2425R;

public class CircleImageView extends ImageView {

    /* renamed from: a */
    private static final ImageView.ScaleType f13496a = ImageView.ScaleType.CENTER_CROP;

    /* renamed from: b */
    private static final Bitmap.Config f13497b = Bitmap.Config.ARGB_8888;

    /* renamed from: c */
    private static final int f13498c = 2;

    /* renamed from: d */
    private static final int f13499d = 0;

    /* renamed from: e */
    private static final int f13500e = -16777216;

    /* renamed from: f */
    private static final int f13501f = 0;

    /* renamed from: g */
    private static final boolean f13502g = false;

    /* renamed from: A */
    private boolean f13503A;

    /* renamed from: h */
    private final RectF f13504h;

    /* renamed from: i */
    private final RectF f13505i;

    /* renamed from: j */
    private final Matrix f13506j;

    /* renamed from: k */
    private final Paint f13507k;

    /* renamed from: l */
    private final Paint f13508l;

    /* renamed from: m */
    private final Paint f13509m;

    /* renamed from: n */
    private int f13510n;

    /* renamed from: o */
    private int f13511o;

    /* renamed from: p */
    private int f13512p;

    /* renamed from: q */
    private Bitmap f13513q;

    /* renamed from: r */
    private BitmapShader f13514r;

    /* renamed from: s */
    private int f13515s;

    /* renamed from: t */
    private int f13516t;

    /* renamed from: u */
    private float f13517u;

    /* renamed from: v */
    private float f13518v;

    /* renamed from: w */
    private ColorFilter f13519w;

    /* renamed from: x */
    private boolean f13520x;

    /* renamed from: y */
    private boolean f13521y;

    /* renamed from: z */
    private boolean f13522z;

    public CircleImageView(Context context) {
        super(context);
        this.f13504h = new RectF();
        this.f13505i = new RectF();
        this.f13506j = new Matrix();
        this.f13507k = new Paint();
        this.f13508l = new Paint();
        this.f13509m = new Paint();
        this.f13510n = -16777216;
        this.f13511o = 0;
        this.f13512p = 0;
        m14570f();
    }

    public CircleImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CircleImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f13504h = new RectF();
        this.f13505i = new RectF();
        this.f13506j = new Matrix();
        this.f13507k = new Paint();
        this.f13508l = new Paint();
        this.f13509m = new Paint();
        this.f13510n = -16777216;
        this.f13511o = 0;
        this.f13512p = 0;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C2425R.styleable.f8451u, i, 0);
        this.f13511o = obtainStyledAttributes.getDimensionPixelSize(0, 0);
        this.f13510n = obtainStyledAttributes.getColor(1, -16777216);
        this.f13522z = obtainStyledAttributes.getBoolean(2, false);
        this.f13512p = obtainStyledAttributes.getColor(3, 0);
        obtainStyledAttributes.recycle();
        m14570f();
    }

    /* renamed from: f */
    private void m14570f() {
        super.setScaleType(f13496a);
        this.f13520x = true;
        if (this.f13521y) {
            m14573i();
            this.f13521y = false;
        }
    }

    public ImageView.ScaleType getScaleType() {
        return f13496a;
    }

    public void setScaleType(ImageView.ScaleType scaleType) {
        if (scaleType != f13496a) {
            throw new IllegalArgumentException(String.format("ScaleType %s not supported.", scaleType));
        }
    }

    public void setAdjustViewBounds(boolean z) {
        if (z) {
            throw new IllegalArgumentException("adjustViewBounds not supported.");
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (this.f13503A) {
            super.onDraw(canvas);
        } else if (this.f13513q != null) {
            if (this.f13512p != 0) {
                canvas.drawCircle(this.f13504h.centerX(), this.f13504h.centerY(), this.f13517u, this.f13509m);
            }
            canvas.drawCircle(this.f13504h.centerX(), this.f13504h.centerY(), this.f13517u, this.f13507k);
            if (this.f13511o > 0) {
                canvas.drawCircle(this.f13505i.centerX(), this.f13505i.centerY(), this.f13518v, this.f13508l);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        m14573i();
    }

    public void setPadding(int i, int i2, int i3, int i4) {
        super.setPadding(i, i2, i3, i4);
        m14573i();
    }

    public void setPaddingRelative(int i, int i2, int i3, int i4) {
        super.setPaddingRelative(i, i2, i3, i4);
        m14573i();
    }

    /* renamed from: a */
    public int mo28174a() {
        return this.f13510n;
    }

    /* renamed from: a */
    public void mo28175a(int i) {
        if (i != this.f13510n) {
            this.f13510n = i;
            this.f13508l.setColor(this.f13510n);
            invalidate();
        }
    }

    @Deprecated
    /* renamed from: b */
    public void mo28178b(@ColorRes int i) {
        mo28175a(getContext().getResources().getColor(i));
    }

    @Deprecated
    /* renamed from: b */
    public int mo28177b() {
        return this.f13512p;
    }

    @Deprecated
    /* renamed from: c */
    public void mo28181c(int i) {
        if (i != this.f13512p) {
            this.f13512p = i;
            this.f13509m.setColor(i);
            invalidate();
        }
    }

    @Deprecated
    /* renamed from: d */
    public void mo28182d(@ColorRes int i) {
        mo28181c(getContext().getResources().getColor(i));
    }

    /* renamed from: c */
    public int mo28180c() {
        return this.f13511o;
    }

    /* renamed from: e */
    public void mo28184e(int i) {
        if (i != this.f13511o) {
            this.f13511o = i;
            m14573i();
        }
    }

    /* renamed from: d */
    public boolean mo28183d() {
        return this.f13522z;
    }

    /* renamed from: a */
    public void mo28176a(boolean z) {
        if (z != this.f13522z) {
            this.f13522z = z;
            m14573i();
        }
    }

    /* renamed from: e */
    public boolean mo28185e() {
        return this.f13503A;
    }

    /* renamed from: b */
    public void mo28179b(boolean z) {
        if (this.f13503A != z) {
            this.f13503A = z;
            m14572h();
        }
    }

    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        m14572h();
    }

    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        m14572h();
    }

    public void setImageResource(@DrawableRes int i) {
        super.setImageResource(i);
        m14572h();
    }

    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        m14572h();
    }

    @Override // android.widget.ImageView
    public void setColorFilter(ColorFilter colorFilter) {
        if (colorFilter != this.f13519w) {
            this.f13519w = colorFilter;
            m14571g();
            invalidate();
        }
    }

    public ColorFilter getColorFilter() {
        return this.f13519w;
    }

    /* renamed from: g */
    private void m14571g() {
        if (this.f13507k != null) {
            this.f13507k.setColorFilter(this.f13519w);
        }
    }

    /* renamed from: a */
    private Bitmap m14569a(Drawable drawable) {
        Bitmap createBitmap;
        if (drawable == null) {
            return null;
        }
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        try {
            if (drawable instanceof ColorDrawable) {
                createBitmap = Bitmap.createBitmap(2, 2, f13497b);
            } else {
                createBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), f13497b);
            }
            Canvas canvas = new Canvas(createBitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return createBitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: h */
    private void m14572h() {
        if (this.f13503A) {
            this.f13513q = null;
        } else {
            this.f13513q = m14569a(getDrawable());
        }
        m14573i();
    }

    /* renamed from: i */
    private void m14573i() {
        if (!this.f13520x) {
            this.f13521y = true;
        } else if (getWidth() != 0 || getHeight() != 0) {
            if (this.f13513q == null) {
                invalidate();
                return;
            }
            this.f13514r = new BitmapShader(this.f13513q, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            this.f13507k.setAntiAlias(true);
            this.f13507k.setShader(this.f13514r);
            this.f13508l.setStyle(Paint.Style.STROKE);
            this.f13508l.setAntiAlias(true);
            this.f13508l.setColor(this.f13510n);
            this.f13508l.setStrokeWidth((float) this.f13511o);
            this.f13509m.setStyle(Paint.Style.FILL);
            this.f13509m.setAntiAlias(true);
            this.f13509m.setColor(this.f13512p);
            this.f13516t = this.f13513q.getHeight();
            this.f13515s = this.f13513q.getWidth();
            this.f13505i.set(m14574j());
            this.f13518v = Math.min((this.f13505i.height() - ((float) this.f13511o)) / 2.0f, (this.f13505i.width() - ((float) this.f13511o)) / 2.0f);
            this.f13504h.set(this.f13505i);
            if (!this.f13522z && this.f13511o > 0) {
                this.f13504h.inset(((float) this.f13511o) - 1.0f, ((float) this.f13511o) - 1.0f);
            }
            this.f13517u = Math.min(this.f13504h.height() / 2.0f, this.f13504h.width() / 2.0f);
            m14571g();
            m14575k();
            invalidate();
        }
    }

    /* renamed from: j */
    private RectF m14574j() {
        int width = (getWidth() - getPaddingLeft()) - getPaddingRight();
        int height = (getHeight() - getPaddingTop()) - getPaddingBottom();
        int min = Math.min(width, height);
        float paddingLeft = (((float) (width - min)) / 2.0f) + ((float) getPaddingLeft());
        float paddingTop = (((float) (height - min)) / 2.0f) + ((float) getPaddingTop());
        return new RectF(paddingLeft, paddingTop, ((float) min) + paddingLeft, ((float) min) + paddingTop);
    }

    /* renamed from: k */
    private void m14575k() {
        float width;
        float f;
        float f2 = 0.0f;
        this.f13506j.set(null);
        if (((float) this.f13515s) * this.f13504h.height() > this.f13504h.width() * ((float) this.f13516t)) {
            width = this.f13504h.height() / ((float) this.f13516t);
            f = (this.f13504h.width() - (((float) this.f13515s) * width)) * 0.5f;
        } else {
            width = this.f13504h.width() / ((float) this.f13515s);
            f = 0.0f;
            f2 = (this.f13504h.height() - (((float) this.f13516t) * width)) * 0.5f;
        }
        this.f13506j.setScale(width, width);
        this.f13506j.postTranslate(((float) ((int) (f + 0.5f))) + this.f13504h.left, ((float) ((int) (f2 + 0.5f))) + this.f13504h.top);
        this.f13514r.setLocalMatrix(this.f13506j);
    }
}
