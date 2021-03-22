package com.gbcom.gwifi.functions.template.builder;

import android.content.Context;
import android.support.p009v4.view.MotionEventCompat;
import android.support.p009v4.view.NestedScrollingChild;
import android.support.p009v4.view.NestedScrollingChildHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.tencent.smtt.sdk.WebView;

public class NestedScrollX5WebView extends WebView implements NestedScrollingChild {

    /* renamed from: b */
    public static final String f11704b = NestedScrollX5WebView.class.getSimpleName();

    /* renamed from: c */
    private int f11705c;

    /* renamed from: d */
    private final int[] f11706d;

    /* renamed from: e */
    private final int[] f11707e;

    /* renamed from: f */
    private int f11708f;

    /* renamed from: g */
    private NestedScrollingChildHelper f11709g;

    public NestedScrollX5WebView(Context context) {
        super(context);
        this.f11706d = new int[2];
        this.f11707e = new int[2];
        m12835h();
    }

    public NestedScrollX5WebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f11706d = new int[2];
        this.f11707e = new int[2];
        m12835h();
    }

    public NestedScrollX5WebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f11706d = new int[2];
        this.f11707e = new int[2];
        m12835h();
    }

    /* renamed from: h */
    private void m12835h() {
        this.f11709g = new NestedScrollingChildHelper(this);
        setNestedScrollingEnabled(true);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        MotionEvent obtain = MotionEvent.obtain(motionEvent);
        int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        if (actionMasked == 0) {
            this.f11708f = 0;
        }
        int y = (int) motionEvent.getY();
        motionEvent.offsetLocation(0.0f, (float) this.f11708f);
        switch (actionMasked) {
            case 0:
                this.f11705c = y;
                startNestedScroll(3);
                return super.onTouchEvent(motionEvent);
            case 1:
            case 3:
            case 5:
                stopNestedScroll();
                return super.onTouchEvent(motionEvent);
            case 2:
                int i = this.f11705c - y;
                if (dispatchNestedPreScroll(0, i, this.f11707e, this.f11706d)) {
                    i -= this.f11707e[1];
                    obtain.offsetLocation(0.0f, (float) this.f11706d[1]);
                    this.f11708f += this.f11706d[1];
                }
                int scrollY = getScrollY();
                this.f11705c = y - this.f11706d[1];
                int max = Math.max(0, scrollY + i);
                int i2 = i - (max - scrollY);
                if (dispatchNestedScroll(0, max - i2, 0, i2, this.f11706d)) {
                    this.f11705c -= this.f11706d[1];
                    obtain.offsetLocation(0.0f, (float) this.f11706d[1]);
                    this.f11708f += this.f11706d[1];
                }
                if (this.f11707e[1] != 0 || this.f11706d[1] != 0) {
                    return false;
                }
                obtain.recycle();
                return super.onTouchEvent(obtain);
            case 4:
            default:
                return false;
        }
    }

    @Override // android.support.p009v4.view.NestedScrollingChild
    public void setNestedScrollingEnabled(boolean z) {
        this.f11709g.setNestedScrollingEnabled(z);
    }

    @Override // android.support.p009v4.view.NestedScrollingChild
    public boolean isNestedScrollingEnabled() {
        return this.f11709g.isNestedScrollingEnabled();
    }

    @Override // android.support.p009v4.view.NestedScrollingChild
    public boolean startNestedScroll(int i) {
        return this.f11709g.startNestedScroll(i);
    }

    @Override // android.support.p009v4.view.NestedScrollingChild
    public void stopNestedScroll() {
        this.f11709g.stopNestedScroll();
    }

    @Override // android.support.p009v4.view.NestedScrollingChild
    public boolean hasNestedScrollingParent() {
        return this.f11709g.hasNestedScrollingParent();
    }

    @Override // android.support.p009v4.view.NestedScrollingChild
    public boolean dispatchNestedScroll(int i, int i2, int i3, int i4, int[] iArr) {
        return this.f11709g.dispatchNestedScroll(i, i2, i3, i4, iArr);
    }

    @Override // android.support.p009v4.view.NestedScrollingChild
    public boolean dispatchNestedPreScroll(int i, int i2, int[] iArr, int[] iArr2) {
        return this.f11709g.dispatchNestedPreScroll(i, i2, iArr, iArr2);
    }

    @Override // android.support.p009v4.view.NestedScrollingChild
    public boolean dispatchNestedFling(float f, float f2, boolean z) {
        return this.f11709g.dispatchNestedFling(f, f2, z);
    }

    @Override // android.support.p009v4.view.NestedScrollingChild
    public boolean dispatchNestedPreFling(float f, float f2) {
        return this.f11709g.dispatchNestedPreFling(f, f2);
    }
}
