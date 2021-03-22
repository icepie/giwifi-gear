package com.gbcom.gwifi.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import com.gbcom.gwifi.C2425R;
import org.apache.xpath.XPath;

public class CircularProgressBar extends View {

    /* renamed from: a */
    private static final String f13530a = CircularProgressBar.class.getSimpleName();

    /* renamed from: b */
    private int f13531b = 28;

    /* renamed from: c */
    private int f13532c = 4;

    /* renamed from: d */
    private int f13533d = 4;

    /* renamed from: e */
    private final int f13534e = 16;

    /* renamed from: f */
    private final int f13535f = 270;

    /* renamed from: g */
    private boolean f13536g = false;

    /* renamed from: h */
    private double f13537h = XPath.MATCH_SCORE_QNAME;

    /* renamed from: i */
    private double f13538i = 460.0d;

    /* renamed from: j */
    private float f13539j = 0.0f;

    /* renamed from: k */
    private boolean f13540k = true;

    /* renamed from: l */
    private long f13541l = 0;

    /* renamed from: m */
    private final long f13542m = 200;

    /* renamed from: n */
    private int f13543n = -1442840576;

    /* renamed from: o */
    private int f13544o = 16777215;

    /* renamed from: p */
    private Paint f13545p = new Paint();

    /* renamed from: q */
    private Paint f13546q = new Paint();

    /* renamed from: r */
    private RectF f13547r = new RectF();

    /* renamed from: s */
    private float f13548s = 230.0f;

    /* renamed from: t */
    private long f13549t = 0;

    /* renamed from: u */
    private boolean f13550u;

    /* renamed from: v */
    private float f13551v = 0.0f;

    /* renamed from: w */
    private float f13552w = 0.0f;

    /* renamed from: x */
    private boolean f13553x = false;

    /* renamed from: y */
    private AbstractC3473a f13554y;

    /* renamed from: com.gbcom.gwifi.widget.CircularProgressBar$a */
    public interface AbstractC3473a {
        /* renamed from: a */
        void mo28235a(float f);
    }

    public CircularProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m14592a(context.obtainStyledAttributes(attributeSet, C2425R.styleable.f8453w));
    }

    public CircularProgressBar(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int paddingLeft = this.f13531b + getPaddingLeft() + getPaddingRight();
        int paddingTop = this.f13531b + getPaddingTop() + getPaddingBottom();
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size2 = View.MeasureSpec.getSize(i2);
        if (mode != 1073741824) {
            size = mode == Integer.MIN_VALUE ? Math.min(paddingLeft, size) : paddingLeft;
        }
        if (mode2 == 1073741824 || mode == 1073741824) {
            paddingTop = size2;
        } else if (mode2 == Integer.MIN_VALUE) {
            paddingTop = Math.min(paddingTop, size2);
        }
        setMeasuredDimension(size, paddingTop);
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        m14590a(i, i2);
        m14594l();
        invalidate();
    }

    /* renamed from: l */
    private void m14594l() {
        this.f13545p.setColor(this.f13543n);
        this.f13545p.setAntiAlias(true);
        this.f13545p.setStyle(Paint.Style.STROKE);
        this.f13545p.setStrokeWidth((float) this.f13532c);
        this.f13546q.setColor(this.f13544o);
        this.f13546q.setAntiAlias(true);
        this.f13546q.setStyle(Paint.Style.STROKE);
        this.f13546q.setStrokeWidth((float) this.f13533d);
    }

    /* renamed from: a */
    private void m14590a(int i, int i2) {
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        if (!this.f13536g) {
            int min = Math.min(Math.min((i - paddingLeft) - paddingRight, (i2 - paddingBottom) - paddingTop), (this.f13531b * 2) - (this.f13532c * 2));
            int i3 = paddingLeft + ((((i - paddingLeft) - paddingRight) - min) / 2);
            int i4 = paddingTop + ((((i2 - paddingTop) - paddingBottom) - min) / 2);
            this.f13547r = new RectF((float) (this.f13532c + i3), (float) (this.f13532c + i4), (float) ((i3 + min) - this.f13532c), (float) ((i4 + min) - this.f13532c));
            return;
        }
        this.f13547r = new RectF((float) (paddingLeft + this.f13532c), (float) (paddingTop + this.f13532c), (float) ((i - paddingRight) - this.f13532c), (float) ((i2 - paddingBottom) - this.f13532c));
    }

    /* renamed from: a */
    private void m14592a(TypedArray typedArray) {
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        this.f13532c = (int) TypedValue.applyDimension(1, (float) this.f13532c, displayMetrics);
        this.f13533d = (int) TypedValue.applyDimension(1, (float) this.f13533d, displayMetrics);
        this.f13531b = (int) TypedValue.applyDimension(1, (float) this.f13531b, displayMetrics);
        this.f13531b = (int) typedArray.getDimension(6, (float) this.f13531b);
        this.f13536g = typedArray.getBoolean(7, false);
        this.f13532c = (int) typedArray.getDimension(8, (float) this.f13532c);
        this.f13533d = (int) typedArray.getDimension(3, (float) this.f13533d);
        this.f13548s = typedArray.getFloat(4, this.f13548s / 360.0f) * 360.0f;
        this.f13538i = (double) typedArray.getInt(5, (int) this.f13538i);
        this.f13543n = typedArray.getColor(1, this.f13543n);
        this.f13544o = typedArray.getColor(2, this.f13544o);
        this.f13550u = typedArray.getBoolean(9, false);
        if (typedArray.getBoolean(0, false)) {
            mo28214d();
        }
        typedArray.recycle();
    }

    /* renamed from: a */
    public void mo28205a(AbstractC3473a aVar) {
        this.f13554y = aVar;
        if (!this.f13553x) {
            m14595m();
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        boolean z;
        float f;
        float f2;
        super.onDraw(canvas);
        canvas.drawArc(this.f13547r, 360.0f, 360.0f, false, this.f13546q);
        boolean z2 = false;
        if (this.f13553x) {
            z = true;
            long uptimeMillis = SystemClock.uptimeMillis() - this.f13549t;
            m14591a(uptimeMillis);
            this.f13551v += (((float) uptimeMillis) * this.f13548s) / 1000.0f;
            if (this.f13551v > 360.0f) {
                this.f13551v -= 360.0f;
                m14593d(-1.0f);
            }
            this.f13549t = SystemClock.uptimeMillis();
            float f3 = this.f13551v - 90.0f;
            float f4 = 16.0f + this.f13539j;
            if (isInEditMode()) {
                f3 = 0.0f;
                f4 = 135.0f;
            }
            canvas.drawArc(this.f13547r, f3, f4, false, this.f13545p);
        } else {
            float f5 = this.f13551v;
            if (this.f13551v != this.f13552w) {
                z2 = true;
                this.f13551v = Math.min(((((float) (SystemClock.uptimeMillis() - this.f13549t)) / 1000.0f) * this.f13548s) + this.f13551v, this.f13552w);
                this.f13549t = SystemClock.uptimeMillis();
            }
            z = z2;
            if (f5 != this.f13551v) {
                m14595m();
            }
            float f6 = this.f13551v;
            if (!this.f13550u) {
                f6 = ((float) (1.0d - Math.pow((double) (1.0f - (this.f13551v / 360.0f)), (double) 2.0f))) * 360.0f;
                f = ((float) (1.0d - Math.pow((double) (1.0f - (this.f13551v / 360.0f)), (double) (2.0f * 2.0f)))) * 360.0f;
            } else {
                f = 0.0f;
            }
            if (isInEditMode()) {
                f2 = 360.0f;
            } else {
                f2 = f6;
            }
            canvas.drawArc(this.f13547r, f - 90.0f, f2, false, this.f13545p);
        }
        if (z) {
            invalidate();
        }
    }

    /* access modifiers changed from: protected */
    public void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        if (i == 0) {
            this.f13549t = SystemClock.uptimeMillis();
        }
    }

    /* renamed from: a */
    private void m14591a(long j) {
        if (this.f13541l >= 200) {
            this.f13537h += (double) j;
            if (this.f13537h > this.f13538i) {
                this.f13537h -= this.f13538i;
                this.f13541l = 0;
                this.f13540k = !this.f13540k;
            }
            float cos = (((float) Math.cos(((this.f13537h / this.f13538i) + 1.0d) * 3.141592653589793d)) / 2.0f) + 0.5f;
            if (this.f13540k) {
                this.f13539j = cos * 254.0f;
                return;
            }
            float f = (1.0f - cos) * 254.0f;
            this.f13551v += this.f13539j - f;
            this.f13539j = f;
            return;
        }
        this.f13541l += j;
    }

    /* renamed from: a */
    public boolean mo28207a() {
        return this.f13553x;
    }

    /* renamed from: b */
    public void mo28208b() {
        this.f13551v = 0.0f;
        this.f13552w = 0.0f;
        invalidate();
    }

    /* renamed from: c */
    public void mo28211c() {
        this.f13553x = false;
        this.f13551v = 0.0f;
        this.f13552w = 0.0f;
        invalidate();
    }

    /* renamed from: d */
    public void mo28214d() {
        this.f13549t = SystemClock.uptimeMillis();
        this.f13553x = true;
        invalidate();
    }

    /* renamed from: d */
    private void m14593d(float f) {
        if (this.f13554y != null) {
            this.f13554y.mo28235a(f);
        }
    }

    /* renamed from: m */
    private void m14595m() {
        if (this.f13554y != null) {
            this.f13554y.mo28235a(((float) Math.round((this.f13551v * 100.0f) / 360.0f)) / 100.0f);
        }
    }

    /* renamed from: a */
    public void mo28203a(float f) {
        if (this.f13553x) {
            this.f13551v = 0.0f;
            this.f13553x = false;
            m14595m();
        }
        if (f > 1.0f) {
            f -= 1.0f;
        } else if (f < 0.0f) {
            f = 0.0f;
        }
        if (f != this.f13552w) {
            if (this.f13551v == this.f13552w) {
                this.f13549t = SystemClock.uptimeMillis();
            }
            this.f13552w = Math.min(f * 360.0f, 360.0f);
            invalidate();
        }
    }

    /* renamed from: b */
    public void mo28209b(float f) {
        if (this.f13553x) {
            this.f13551v = 0.0f;
            this.f13553x = false;
        }
        if (f > 1.0f) {
            f -= 1.0f;
        } else if (f < 0.0f) {
            f = 0.0f;
        }
        if (f != this.f13552w) {
            this.f13552w = Math.min(f * 360.0f, 360.0f);
            this.f13551v = this.f13552w;
            this.f13549t = SystemClock.uptimeMillis();
            invalidate();
        }
    }

    public Parcelable onSaveInstanceState() {
        WheelSavedState wheelSavedState = new WheelSavedState(super.onSaveInstanceState());
        wheelSavedState.f13555a = this.f13551v;
        wheelSavedState.f13556b = this.f13552w;
        wheelSavedState.f13557c = this.f13553x;
        wheelSavedState.f13558d = this.f13548s;
        wheelSavedState.f13559e = this.f13532c;
        wheelSavedState.f13560f = this.f13543n;
        wheelSavedState.f13561g = this.f13533d;
        wheelSavedState.f13562h = this.f13544o;
        wheelSavedState.f13563i = this.f13531b;
        wheelSavedState.f13564j = this.f13550u;
        wheelSavedState.f13565k = this.f13536g;
        return wheelSavedState;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof WheelSavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        WheelSavedState wheelSavedState = (WheelSavedState) parcelable;
        super.onRestoreInstanceState(wheelSavedState.getSuperState());
        this.f13551v = wheelSavedState.f13555a;
        this.f13552w = wheelSavedState.f13556b;
        this.f13553x = wheelSavedState.f13557c;
        this.f13548s = wheelSavedState.f13558d;
        this.f13532c = wheelSavedState.f13559e;
        this.f13543n = wheelSavedState.f13560f;
        this.f13533d = wheelSavedState.f13561g;
        this.f13544o = wheelSavedState.f13562h;
        this.f13531b = wheelSavedState.f13563i;
        this.f13550u = wheelSavedState.f13564j;
        this.f13536g = wheelSavedState.f13565k;
        this.f13549t = SystemClock.uptimeMillis();
    }

    /* renamed from: e */
    public float mo28216e() {
        if (this.f13553x) {
            return -1.0f;
        }
        return this.f13551v / 360.0f;
    }

    /* renamed from: a */
    public void mo28206a(boolean z) {
        this.f13550u = z;
        if (!this.f13553x) {
            invalidate();
        }
    }

    /* renamed from: f */
    public int mo28218f() {
        return this.f13531b;
    }

    /* renamed from: a */
    public void mo28204a(int i) {
        this.f13531b = i;
        if (!this.f13553x) {
            invalidate();
        }
    }

    /* renamed from: g */
    public int mo28219g() {
        return this.f13532c;
    }

    /* renamed from: b */
    public void mo28210b(int i) {
        this.f13532c = i;
        if (!this.f13553x) {
            invalidate();
        }
    }

    /* renamed from: h */
    public int mo28220h() {
        return this.f13543n;
    }

    /* renamed from: c */
    public void mo28213c(int i) {
        this.f13543n = i;
        m14594l();
        if (!this.f13553x) {
            invalidate();
        }
    }

    /* renamed from: i */
    public int mo28221i() {
        return this.f13544o;
    }

    /* renamed from: d */
    public void mo28215d(int i) {
        this.f13544o = i;
        m14594l();
        if (!this.f13553x) {
            invalidate();
        }
    }

    /* renamed from: j */
    public float mo28222j() {
        return this.f13548s / 360.0f;
    }

    /* renamed from: c */
    public void mo28212c(float f) {
        this.f13548s = 360.0f * f;
    }

    /* renamed from: k */
    public int mo28223k() {
        return this.f13533d;
    }

    /* renamed from: e */
    public void mo28217e(int i) {
        this.f13533d = i;
        if (!this.f13553x) {
            invalidate();
        }
    }

    /* access modifiers changed from: package-private */
    public static class WheelSavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<WheelSavedState> CREATOR = new Parcelable.Creator<WheelSavedState>() {
            /* class com.gbcom.gwifi.widget.CircularProgressBar.WheelSavedState.C34721 */

            /* renamed from: a */
            public WheelSavedState createFromParcel(Parcel parcel) {
                return new WheelSavedState(parcel);
            }

            /* renamed from: a */
            public WheelSavedState[] newArray(int i) {
                return new WheelSavedState[i];
            }
        };

        /* renamed from: a */
        float f13555a;

        /* renamed from: b */
        float f13556b;

        /* renamed from: c */
        boolean f13557c;

        /* renamed from: d */
        float f13558d;

        /* renamed from: e */
        int f13559e;

        /* renamed from: f */
        int f13560f;

        /* renamed from: g */
        int f13561g;

        /* renamed from: h */
        int f13562h;

        /* renamed from: i */
        int f13563i;

        /* renamed from: j */
        boolean f13564j;

        /* renamed from: k */
        boolean f13565k;

        WheelSavedState(Parcelable parcelable) {
            super(parcelable);
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        private WheelSavedState(Parcel parcel) {
            super(parcel);
            boolean z;
            boolean z2 = true;
            this.f13555a = parcel.readFloat();
            this.f13556b = parcel.readFloat();
            this.f13557c = parcel.readByte() != 0;
            this.f13558d = parcel.readFloat();
            this.f13559e = parcel.readInt();
            this.f13560f = parcel.readInt();
            this.f13561g = parcel.readInt();
            this.f13562h = parcel.readInt();
            this.f13563i = parcel.readInt();
            if (parcel.readByte() != 0) {
                z = true;
            } else {
                z = false;
            }
            this.f13564j = z;
            this.f13565k = parcel.readByte() == 0 ? false : z2;
        }

        public void writeToParcel(Parcel parcel, int i) {
            int i2;
            int i3 = 1;
            super.writeToParcel(parcel, i);
            parcel.writeFloat(this.f13555a);
            parcel.writeFloat(this.f13556b);
            parcel.writeByte((byte) (this.f13557c ? 1 : 0));
            parcel.writeFloat(this.f13558d);
            parcel.writeInt(this.f13559e);
            parcel.writeInt(this.f13560f);
            parcel.writeInt(this.f13561g);
            parcel.writeInt(this.f13562h);
            parcel.writeInt(this.f13563i);
            if (this.f13564j) {
                i2 = 1;
            } else {
                i2 = 0;
            }
            parcel.writeByte((byte) i2);
            if (!this.f13565k) {
                i3 = 0;
            }
            parcel.writeByte((byte) i3);
        }
    }
}
