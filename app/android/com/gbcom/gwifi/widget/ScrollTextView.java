package com.gbcom.gwifi.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.TextView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.util.C3467s;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ScrollTextView extends TextView {

    /* renamed from: a */
    private int f13778a;

    /* renamed from: b */
    private int f13779b;

    /* renamed from: c */
    private List<String> f13780c;

    /* renamed from: d */
    private List<String> f13781d;

    /* renamed from: e */
    private List<String> f13782e;

    /* renamed from: f */
    private int f13783f;

    /* renamed from: g */
    private int f13784g;

    /* renamed from: h */
    private Paint f13785h;

    /* renamed from: i */
    private boolean f13786i;

    /* renamed from: j */
    private Timer f13787j;

    /* renamed from: k */
    private TimerTask f13788k;

    /* renamed from: l */
    private boolean f13789l;

    /* renamed from: m */
    private Rect f13790m;

    /* renamed from: n */
    private String f13791n;

    /* renamed from: o */
    private int f13792o;

    /* renamed from: p */
    private Paint f13793p;

    /* renamed from: q */
    private Rect f13794q;

    /* renamed from: r */
    private String f13795r;

    /* renamed from: s */
    private int f13796s;

    /* renamed from: t */
    private String f13797t;

    public ScrollTextView(Context context) {
        this(context, null);
    }

    public ScrollTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f13783f = 0;
        this.f13784g = 0;
        this.f13786i = true;
        this.f13789l = false;
        this.f13792o = 3;
        this.f13796s = 20;
        m14819e();
    }

    /* renamed from: a */
    public boolean mo28410a() {
        return this.f13789l;
    }

    /* renamed from: a */
    public void mo28409a(List<String> list) {
        this.f13780c = list;
    }

    /* renamed from: b */
    public void mo28413b(List<String> list) {
        this.f13781d = list;
    }

    /* renamed from: a */
    public void mo28408a(int i) {
        this.f13779b = i;
    }

    /* renamed from: b */
    public void mo28412b(int i) {
        this.f13778a = i;
    }

    /* renamed from: c */
    public void mo28415c(int i) {
        this.f13785h.setColor(i);
    }

    /* renamed from: e */
    private void m14819e() {
        this.f13778a = 10;
        this.f13779b = 1500;
        this.f13784g = 0;
        this.f13785h = new Paint();
        this.f13785h.setAntiAlias(true);
        this.f13785h.setDither(true);
        this.f13785h.setTextSize((float) mo28417d(14));
        this.f13785h.setColor(getResources().getColor(C2425R.color.black));
        this.f13793p = new Paint();
        this.f13793p.setAntiAlias(true);
        this.f13793p.setDither(true);
        this.f13793p.setTextSize((float) mo28417d(12));
        this.f13793p.setColor(getResources().getColor(C2425R.color.black));
    }

    /* renamed from: b */
    public void mo28411b() {
        this.f13789l = true;
        invalidate();
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (this.f13780c != null && this.f13780c.size() > 0 && this.f13780c.size() == this.f13781d.size()) {
            this.f13791n = this.f13780c.get(this.f13784g);
            this.f13797t = this.f13791n;
            float measureText = getPaint().measureText(this.f13780c.get(this.f13784g));
            if (measureText > ((float) getMeasuredWidth())) {
                this.f13791n = this.f13780c.get(this.f13784g).substring(0, ((int) (((float) getMeasuredWidth()) / (measureText / ((float) this.f13780c.get(this.f13784g).length())))) - 3) + "...";
            }
            this.f13795r = this.f13781d.get(this.f13784g);
            String str = this.f13782e.get(this.f13784g);
            if (!C3467s.m14513e(str)) {
                this.f13785h.setColor(Color.parseColor(str));
            } else {
                this.f13785h.setColor(getResources().getColor(C2425R.color.black));
            }
            this.f13790m = new Rect();
            this.f13794q = new Rect();
            this.f13785h.getTextBounds(this.f13791n, 0, this.f13791n.length(), this.f13790m);
            this.f13793p.getTextBounds(this.f13795r, 0, this.f13795r.length(), this.f13794q);
            if (this.f13783f == m14818e((((0 - this.f13790m.bottom) - this.f13794q.bottom) + this.f13794q.top) - this.f13796s)) {
                this.f13783f = m14818e(getMeasuredHeight() - this.f13790m.top);
                this.f13784g++;
            }
            canvas.drawText(this.f13791n, 0, this.f13791n.length(), 10.0f, (float) this.f13783f, this.f13785h);
            canvas.drawText(this.f13795r, 0, this.f13795r.length(), 10.0f, (float) (((this.f13783f + this.f13790m.bottom) - this.f13794q.top) + this.f13796s), this.f13793p);
            if (this.f13783f == m14818e((getMeasuredHeight() / 2) - (((((this.f13790m.bottom + this.f13790m.top) + this.f13794q.bottom) - this.f13794q.top) + this.f13796s) / 2)) && this.f13789l) {
                this.f13786i = false;
                this.f13787j = new Timer();
                Timer timer = this.f13787j;
                C34951 r1 = new TimerTask() {
                    /* class com.gbcom.gwifi.widget.ScrollTextView.C34951 */

                    @Override // java.util.TimerTask, java.lang.Runnable
                    public void run() {
                        ScrollTextView.this.postInvalidate();
                        ScrollTextView.this.f13786i = true;
                    }
                };
                this.f13788k = r1;
                timer.schedule(r1, (long) this.f13779b);
            }
            if (this.f13789l) {
                this.f13783f -= this.f13792o;
            }
            if (this.f13784g == this.f13780c.size()) {
                this.f13784g = 0;
            }
            if (this.f13786i && this.f13789l) {
                postInvalidateDelayed((long) this.f13778a);
            }
        }
    }

    /* renamed from: c */
    public void mo28414c() {
        this.f13789l = false;
        if (this.f13787j != null) {
            this.f13787j.cancel();
        }
        if (this.f13788k != null) {
            this.f13788k.cancel();
        }
        if (this.f13790m != null) {
            this.f13783f = m14818e((getMeasuredHeight() / 2) - (((((this.f13790m.bottom + this.f13790m.top) + this.f13794q.bottom) - this.f13794q.top) + this.f13796s) / 2));
        }
    }

    /* renamed from: e */
    private int m14818e(int i) {
        return i - (i % this.f13792o);
    }

    /* renamed from: d */
    public int mo28417d(int i) {
        return (int) ((getContext().getResources().getDisplayMetrics().density * ((float) i)) + 0.5f);
    }

    /* renamed from: d */
    public String mo28418d() {
        return this.f13797t;
    }

    /* renamed from: c */
    public void mo28416c(List<String> list) {
        this.f13782e = list;
    }
}
