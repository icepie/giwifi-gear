package com.gbcom.gwifi.widget;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.ViewConfiguration;
import android.view.animation.AnimationUtils;
import android.widget.OverScroller;
import android.widget.ScrollView;
import android.widget.Toast;

public class GbScrollView extends ScrollView {

    /* renamed from: a */
    private long f13611a;

    /* renamed from: b */
    private OverScroller f13612b;

    /* renamed from: c */
    private int f13613c;

    /* renamed from: d */
    private int f13614d;

    /* renamed from: e */
    private int f13615e;

    /* renamed from: f */
    private int f13616f;

    /* renamed from: g */
    private int f13617g;

    /* renamed from: h */
    private int f13618h;

    /* renamed from: i */
    private boolean f13619i = true;

    /* renamed from: j */
    private boolean f13620j = false;

    /* renamed from: k */
    private AbstractC3481a f13621k;

    /* renamed from: com.gbcom.gwifi.widget.GbScrollView$a */
    public interface AbstractC3481a {
        /* renamed from: a */
        void mo27271a();

        /* renamed from: b */
        void mo27272b();
    }

    public GbScrollView(Context context) {
        super(context);
        m14656c();
    }

    public GbScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m14656c();
    }

    public GbScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m14656c();
    }

    /* renamed from: c */
    private void m14656c() {
        this.f13612b = new OverScroller(getContext());
        setFocusable(true);
        setDescendantFocusability(262144);
        setWillNotDraw(false);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        this.f13613c = viewConfiguration.getScaledTouchSlop();
        this.f13614d = viewConfiguration.getScaledMinimumFlingVelocity();
        this.f13615e = viewConfiguration.getScaledMaximumFlingVelocity();
        this.f13616f = viewConfiguration.getScaledOverscrollDistance();
        this.f13617g = viewConfiguration.getScaledOverflingDistance();
    }

    /* renamed from: a */
    public void mo28264a(int i, int i2, int i3) {
        m14655b(i - getScrollX(), i2 - getScrollY(), i3);
    }

    /* renamed from: b */
    private void m14655b(int i, int i2, int i3) {
        if (getChildCount() != 0) {
            if (AnimationUtils.currentAnimationTimeMillis() - this.f13611a > 250) {
                int max = Math.max(0, getChildAt(0).getHeight() - ((getHeight() - getPaddingBottom()) - getPaddingTop()));
                int scrollY = getScrollY();
                this.f13612b.startScroll(getScrollX(), scrollY, 0, Math.max(0, Math.min(scrollY + i2, max)) - scrollY, i3);
                if (Build.VERSION.SDK_INT < 16) {
                    postInvalidate();
                } else {
                    postInvalidateOnAnimation();
                }
            } else {
                if (!this.f13612b.isFinished()) {
                    this.f13612b.abortAnimation();
                }
                scrollBy(i, i2);
            }
            this.f13611a = AnimationUtils.currentAnimationTimeMillis();
        }
    }

    public void computeScroll() {
        super.computeScroll();
        if (this.f13612b.computeScrollOffset()) {
            int scrollX = getScrollX();
            int scrollY = getScrollY();
            int currX = this.f13612b.getCurrX();
            int currY = this.f13612b.getCurrY();
            if (!(scrollX == currX && scrollY == currY)) {
                int d = m14657d();
                int overScrollMode = getOverScrollMode();
                boolean z = overScrollMode == 0 || (overScrollMode == 1 && d > 0);
                overScrollBy(currX - scrollX, currY - scrollY, scrollX, scrollY, 0, d, 0, this.f13617g, false);
                onScrollChanged(getScrollX(), getScrollY(), scrollX, scrollY);
                if (!z || ((currY < 0 && scrollY >= 0) || currY <= d || scrollY > d)) {
                }
            }
            if (awakenScrollBars()) {
                return;
            }
            if (Build.VERSION.SDK_INT < 16) {
                postInvalidate();
            } else {
                postInvalidateOnAnimation();
            }
        }
    }

    /* renamed from: d */
    private int m14657d() {
        if (getChildCount() > 0) {
            return Math.max(0, getChildAt(0).getHeight() - ((getHeight() - getPaddingBottom()) - getPaddingTop()));
        }
        return 0;
    }

    /* renamed from: a */
    public void mo28265a(AbstractC3481a aVar) {
        this.f13621k = aVar;
    }

    /* access modifiers changed from: protected */
    public void onOverScrolled(int i, int i2, boolean z, boolean z2) {
        super.onOverScrolled(i, i2, z, z2);
        if (i2 != 0 && getChildAt(0).getMeasuredHeight() == getChildAt(0).getHeight() + getScrollY()) {
            Toast.makeText(getContext(), "到底了", 0).show();
        }
    }

    /* access modifiers changed from: protected */
    public void onScrollChanged(int i, int i2, int i3, int i4) {
        int bottom = getChildAt(getChildCount() - 1).getBottom() - (getHeight() + getScrollY());
        if (bottom != 0 || bottom == this.f13618h) {
            super.onScrollChanged(i, i2, i3, i4);
        } else if (this.f13621k != null) {
            this.f13621k.mo27271a();
        }
        this.f13618h = bottom;
    }

    /* renamed from: e */
    private void m14658e() {
        if (this.f13619i) {
            if (this.f13621k != null) {
                this.f13621k.mo27272b();
                Toast.makeText(getContext(), "到顶了", 0).show();
            }
        } else if (this.f13620j && this.f13621k != null) {
            this.f13621k.mo27271a();
            Toast.makeText(getContext(), "到底了", 0).show();
        }
    }

    /* renamed from: a */
    public boolean mo28266a() {
        return this.f13619i;
    }

    /* renamed from: b */
    public boolean mo28267b() {
        return this.f13620j;
    }
}
