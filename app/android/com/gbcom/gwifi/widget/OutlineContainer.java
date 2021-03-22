package com.gbcom.gwifi.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.util.AttributeSet;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.util.p257b.DensityUtil;

public class OutlineContainer extends FrameLayout implements Animatable {

    /* renamed from: e */
    private static final long f13698e = 500;

    /* renamed from: f */
    private static final long f13699f = 16;

    /* renamed from: a */
    private Paint f13700a;

    /* renamed from: b */
    private boolean f13701b = false;

    /* renamed from: c */
    private long f13702c;

    /* renamed from: d */
    private float f13703d = 1.0f;

    /* renamed from: g */
    private final Interpolator f13704g = new Interpolator() {
        /* class com.gbcom.gwifi.widget.OutlineContainer.animationInterpolatorC34911 */

        public float getInterpolation(float f) {
            float f2 = f - 1.0f;
            return (f2 * f2 * f2) + 1.0f;
        }
    };

    /* renamed from: h */
    private final Runnable f13705h = new Runnable() {
        /* class com.gbcom.gwifi.widget.OutlineContainer.RunnableC34922 */

        @Override // java.lang.Runnable
        public void run() {
            long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis() - OutlineContainer.this.f13702c;
            if (currentAnimationTimeMillis >= OutlineContainer.f13698e) {
                OutlineContainer.this.f13703d = 0.0f;
                OutlineContainer.this.invalidate();
                OutlineContainer.this.stop();
                return;
            }
            OutlineContainer.this.f13703d = OutlineContainer.this.f13704g.getInterpolation(1.0f - (((float) currentAnimationTimeMillis) / 500.0f));
            OutlineContainer.this.invalidate();
            OutlineContainer.this.postDelayed(OutlineContainer.this.f13705h, 16);
        }
    };

    public OutlineContainer(Context context) {
        super(context);
        m14745a();
    }

    public OutlineContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m14745a();
    }

    public OutlineContainer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m14745a();
    }

    /* renamed from: a */
    private void m14745a() {
        this.f13700a = new Paint();
        this.f13700a.setAntiAlias(true);
        this.f13700a.setStrokeWidth((float) DensityUtil.m14129a(getResources(), 2));
        this.f13700a.setColor(getResources().getColor(C2425R.color.holo_blue));
        this.f13700a.setStyle(Paint.Style.STROKE);
        int a = DensityUtil.m14129a(getResources(), 10);
        setPadding(a, a, a, a);
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        int a = DensityUtil.m14129a(getResources(), 5);
        if (this.f13700a.getColor() != JazzyViewPager.f13623b) {
            this.f13700a.setColor(JazzyViewPager.f13623b);
        }
        this.f13700a.setAlpha((int) (this.f13703d * 255.0f));
        canvas.drawRect(new Rect(a, a, getMeasuredWidth() - a, getMeasuredHeight() - a), this.f13700a);
    }

    /* renamed from: a */
    public void mo28342a(float f) {
        this.f13703d = f;
    }

    public boolean isRunning() {
        return this.f13701b;
    }

    public void start() {
        if (!this.f13701b) {
            this.f13701b = true;
            this.f13702c = AnimationUtils.currentAnimationTimeMillis();
            post(this.f13705h);
        }
    }

    public void stop() {
        if (this.f13701b) {
            this.f13701b = false;
        }
    }
}
