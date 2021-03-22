package com.gbcom.gwifi.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import com.gbcom.gwifi.C2425R;

public class ProgressWheel extends View {

    /* renamed from: A */
    private RectF f13708A = new RectF();

    /* renamed from: B */
    private RectF f13709B = new RectF();

    /* renamed from: C */
    private RectF f13710C = new RectF();

    /* renamed from: D */
    private int f13711D = 2;

    /* renamed from: E */
    private int f13712E = 0;

    /* renamed from: F */
    private String f13713F = "";

    /* renamed from: G */
    private String[] f13714G = new String[0];

    /* renamed from: a */
    int f13715a = 0;

    /* renamed from: b */
    boolean f13716b = false;

    /* renamed from: c */
    private int f13717c = 0;

    /* renamed from: d */
    private int f13718d = 0;

    /* renamed from: e */
    private int f13719e = 100;

    /* renamed from: f */
    private int f13720f = 80;

    /* renamed from: g */
    private int f13721g = 60;

    /* renamed from: h */
    private int f13722h = 20;

    /* renamed from: i */
    private int f13723i = 20;

    /* renamed from: j */
    private int f13724j = 20;

    /* renamed from: k */
    private float f13725k = 0.0f;

    /* renamed from: l */
    private int f13726l = 5;

    /* renamed from: m */
    private int f13727m = 5;

    /* renamed from: n */
    private int f13728n = 5;

    /* renamed from: o */
    private int f13729o = 5;

    /* renamed from: p */
    private int f13730p = -1442840576;

    /* renamed from: q */
    private int f13731q = -1442840576;

    /* renamed from: r */
    private int f13732r = 0;

    /* renamed from: s */
    private int f13733s = -1428300323;

    /* renamed from: t */
    private int f13734t = -16777216;

    /* renamed from: u */
    private Paint f13735u = new Paint();

    /* renamed from: v */
    private Paint f13736v = new Paint();

    /* renamed from: w */
    private Paint f13737w = new Paint();

    /* renamed from: x */
    private Paint f13738x = new Paint();

    /* renamed from: y */
    private Paint f13739y = new Paint();

    /* renamed from: z */
    private RectF f13740z = new RectF();

    public ProgressWheel(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m14749a(context.obtainStyledAttributes(attributeSet, C2425R.styleable.f8416ak));
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        int paddingLeft = (measuredWidth - getPaddingLeft()) - getPaddingRight();
        int paddingTop = (measuredHeight - getPaddingTop()) - getPaddingBottom();
        if (paddingLeft <= paddingTop) {
            paddingTop = paddingLeft;
        }
        setMeasuredDimension(getPaddingLeft() + paddingTop + getPaddingRight(), paddingTop + getPaddingTop() + getPaddingBottom());
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.f13718d = i;
        this.f13717c = i2;
        m14751u();
        m14750t();
        invalidate();
    }

    /* renamed from: t */
    private void m14750t() {
        this.f13735u.setColor(this.f13730p);
        this.f13735u.setAntiAlias(true);
        this.f13735u.setStyle(Paint.Style.STROKE);
        this.f13735u.setStrokeWidth((float) this.f13722h);
        this.f13737w.setColor(this.f13733s);
        this.f13737w.setAntiAlias(true);
        this.f13737w.setStyle(Paint.Style.STROKE);
        this.f13737w.setStrokeWidth((float) this.f13723i);
        this.f13736v.setColor(this.f13732r);
        this.f13736v.setAntiAlias(true);
        this.f13736v.setStyle(Paint.Style.FILL);
        this.f13738x.setColor(this.f13734t);
        this.f13738x.setStyle(Paint.Style.FILL);
        this.f13738x.setAntiAlias(true);
        this.f13738x.setTextSize((float) this.f13724j);
        this.f13739y.setColor(this.f13731q);
        this.f13739y.setAntiAlias(true);
        this.f13739y.setStyle(Paint.Style.STROKE);
        this.f13739y.setStrokeWidth(this.f13725k);
    }

    /* renamed from: u */
    private void m14751u() {
        int min = Math.min(this.f13718d, this.f13717c);
        int i = this.f13718d - min;
        int i2 = this.f13717c - min;
        this.f13726l = getPaddingTop() + (i2 / 2);
        this.f13727m = (i2 / 2) + getPaddingBottom();
        this.f13728n = getPaddingLeft() + (i / 2);
        this.f13729o = getPaddingRight() + (i / 2);
        int width = getWidth();
        int height = getHeight();
        this.f13740z = new RectF((float) this.f13728n, (float) this.f13726l, (float) (width - this.f13729o), (float) (height - this.f13727m));
        this.f13708A = new RectF((float) (this.f13728n + this.f13722h), (float) (this.f13726l + this.f13722h), (float) ((width - this.f13729o) - this.f13722h), (float) ((height - this.f13727m) - this.f13722h));
        this.f13710C = new RectF(this.f13708A.left + (((float) this.f13723i) / 2.0f) + (this.f13725k / 2.0f), this.f13708A.top + (((float) this.f13723i) / 2.0f) + (this.f13725k / 2.0f), (this.f13708A.right - (((float) this.f13723i) / 2.0f)) - (this.f13725k / 2.0f), (this.f13708A.bottom - (((float) this.f13723i) / 2.0f)) - (this.f13725k / 2.0f));
        this.f13709B = new RectF((this.f13708A.left - (((float) this.f13723i) / 2.0f)) - (this.f13725k / 2.0f), (this.f13708A.top - (((float) this.f13723i) / 2.0f)) - (this.f13725k / 2.0f), this.f13708A.right + (((float) this.f13723i) / 2.0f) + (this.f13725k / 2.0f), this.f13708A.bottom + (((float) this.f13723i) / 2.0f) + (this.f13725k / 2.0f));
        this.f13719e = ((width - this.f13729o) - this.f13722h) / 2;
        this.f13720f = (this.f13719e - this.f13722h) + 1;
    }

    /* renamed from: a */
    private void m14749a(TypedArray typedArray) {
        this.f13722h = (int) typedArray.getDimension(10, (float) this.f13722h);
        this.f13723i = (int) typedArray.getDimension(5, (float) this.f13723i);
        this.f13711D = (int) typedArray.getDimension(6, (float) this.f13711D);
        this.f13712E = typedArray.getInteger(7, this.f13712E);
        if (this.f13712E < 0) {
            this.f13712E = 0;
        }
        this.f13730p = typedArray.getColor(3, this.f13730p);
        this.f13721g = (int) typedArray.getDimension(11, (float) this.f13721g);
        this.f13724j = (int) typedArray.getDimension(2, (float) this.f13724j);
        this.f13734t = typedArray.getColor(1, this.f13734t);
        if (typedArray.hasValue(0)) {
            mo28351a(typedArray.getString(0));
        }
        this.f13733s = typedArray.getColor(4, this.f13733s);
        this.f13732r = typedArray.getColor(8, this.f13732r);
        this.f13731q = typedArray.getColor(12, this.f13731q);
        this.f13725k = typedArray.getDimension(13, this.f13725k);
        setClickable(true);
        typedArray.recycle();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawArc(this.f13708A, 360.0f, 360.0f, false, this.f13736v);
        canvas.drawArc(this.f13708A, 360.0f, 360.0f, false, this.f13737w);
        canvas.drawArc(this.f13709B, 360.0f, 360.0f, false, this.f13739y);
        canvas.drawArc(this.f13710C, 360.0f, 360.0f, false, this.f13739y);
        if (this.f13716b) {
            canvas.drawArc(this.f13708A, (float) (this.f13715a - 90), (float) this.f13721g, false, this.f13735u);
        } else {
            canvas.drawArc(this.f13708A, -90.0f, (float) this.f13715a, false, this.f13735u);
        }
        float descent = ((this.f13738x.descent() - this.f13738x.ascent()) / 2.0f) - this.f13738x.descent();
        String[] strArr = this.f13714G;
        for (String str : strArr) {
            canvas.drawText(str, ((float) (getWidth() / 2)) - (this.f13738x.measureText(str) / 2.0f), ((float) (getHeight() / 2)) + descent, this.f13738x);
        }
        if (this.f13716b) {
            m14752v();
        }
    }

    /* renamed from: v */
    private void m14752v() {
        this.f13715a += this.f13711D;
        if (this.f13715a > 360) {
            this.f13715a = 0;
        }
        postInvalidateDelayed((long) this.f13712E);
    }

    /* renamed from: a */
    public boolean mo28352a() {
        if (this.f13716b) {
            return true;
        }
        return false;
    }

    /* renamed from: b */
    public void mo28353b() {
        this.f13715a = 0;
        mo28351a("0%");
        invalidate();
    }

    /* renamed from: c */
    public void mo28355c() {
        this.f13716b = false;
        this.f13715a = 0;
        postInvalidate();
    }

    /* renamed from: d */
    public void mo28357d() {
        this.f13716b = true;
        postInvalidate();
    }

    /* renamed from: e */
    public void mo28359e() {
        this.f13716b = true;
        this.f13715a++;
        if (this.f13715a > 360) {
            this.f13715a = 0;
        }
        postInvalidate();
    }

    /* renamed from: a */
    public void mo28349a(int i) {
        this.f13716b = false;
        this.f13715a = i;
        postInvalidate();
    }

    /* renamed from: a */
    public void mo28351a(String str) {
        this.f13713F = str;
        this.f13714G = this.f13713F.split("\n");
    }

    /* renamed from: f */
    public int mo28361f() {
        return this.f13720f;
    }

    /* renamed from: b */
    public void mo28354b(int i) {
        this.f13720f = i;
    }

    /* renamed from: g */
    public int mo28363g() {
        return this.f13721g;
    }

    /* renamed from: c */
    public void mo28356c(int i) {
        this.f13721g = i;
    }

    /* renamed from: h */
    public int mo28369h() {
        return this.f13722h;
    }

    /* renamed from: d */
    public void mo28358d(int i) {
        this.f13722h = i;
        if (this.f13735u != null) {
            this.f13735u.setStrokeWidth((float) this.f13722h);
        }
    }

    /* renamed from: i */
    public int mo28371i() {
        return this.f13724j;
    }

    /* renamed from: e */
    public void mo28360e(int i) {
        this.f13724j = i;
        if (this.f13738x != null) {
            this.f13738x.setTextSize((float) this.f13724j);
        }
    }

    public int getPaddingTop() {
        return this.f13726l;
    }

    /* renamed from: f */
    public void mo28362f(int i) {
        this.f13726l = i;
    }

    public int getPaddingBottom() {
        return this.f13727m;
    }

    /* renamed from: g */
    public void mo28364g(int i) {
        this.f13727m = i;
    }

    public int getPaddingLeft() {
        return this.f13728n;
    }

    /* renamed from: h */
    public void mo28370h(int i) {
        this.f13728n = i;
    }

    public int getPaddingRight() {
        return this.f13729o;
    }

    /* renamed from: i */
    public void mo28372i(int i) {
        this.f13729o = i;
    }

    /* renamed from: j */
    public int mo28373j() {
        return this.f13730p;
    }

    /* renamed from: j */
    public void mo28374j(int i) {
        this.f13730p = i;
        if (this.f13735u != null) {
            this.f13735u.setColor(this.f13730p);
        }
    }

    /* renamed from: k */
    public int mo28375k() {
        return this.f13732r;
    }

    /* renamed from: k */
    public void mo28376k(int i) {
        this.f13732r = i;
        if (this.f13736v != null) {
            this.f13736v.setColor(this.f13732r);
        }
    }

    /* renamed from: l */
    public int mo28377l() {
        return this.f13733s;
    }

    /* renamed from: l */
    public void mo28378l(int i) {
        this.f13733s = i;
        if (this.f13737w != null) {
            this.f13737w.setColor(this.f13733s);
        }
    }

    /* renamed from: m */
    public Shader mo28379m() {
        return this.f13737w.getShader();
    }

    /* renamed from: a */
    public void mo28350a(Shader shader) {
        this.f13737w.setShader(shader);
    }

    /* renamed from: n */
    public int mo28381n() {
        return this.f13734t;
    }

    /* renamed from: m */
    public void mo28380m(int i) {
        this.f13734t = i;
        if (this.f13738x != null) {
            this.f13738x.setColor(this.f13734t);
        }
    }

    /* renamed from: o */
    public int mo28383o() {
        return this.f13711D;
    }

    /* renamed from: n */
    public void mo28382n(int i) {
        this.f13711D = i;
    }

    /* renamed from: p */
    public int mo28388p() {
        return this.f13723i;
    }

    /* renamed from: o */
    public void mo28384o(int i) {
        this.f13723i = i;
        if (this.f13737w != null) {
            this.f13737w.setStrokeWidth((float) this.f13723i);
        }
    }

    /* renamed from: q */
    public int mo28390q() {
        return this.f13712E;
    }

    /* renamed from: p */
    public void mo28389p(int i) {
        this.f13712E = i;
    }

    /* renamed from: r */
    public int mo28392r() {
        return this.f13731q;
    }

    /* renamed from: q */
    public void mo28391q(int i) {
        this.f13731q = i;
        if (this.f13739y != null) {
            this.f13739y.setColor(this.f13731q);
        }
    }

    /* renamed from: s */
    public float mo28393s() {
        return this.f13725k;
    }

    /* renamed from: a */
    public void mo28348a(float f) {
        this.f13725k = f;
        if (this.f13739y != null) {
            this.f13739y.setStrokeWidth(this.f13725k);
        }
    }
}
