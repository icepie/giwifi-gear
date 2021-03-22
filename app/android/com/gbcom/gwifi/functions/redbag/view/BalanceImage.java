package com.gbcom.gwifi.functions.redbag.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.util.p257b.DensityUtil;

public class BalanceImage extends View {

    /* renamed from: a */
    private Drawable f11362a;

    /* renamed from: b */
    private Paint f11363b;

    /* renamed from: c */
    private String f11364c;

    /* renamed from: d */
    private int f11365d;

    /* renamed from: e */
    private int f11366e;

    /* renamed from: f */
    private Rect f11367f;

    public BalanceImage(Context context) {
        this(context, null);
    }

    public BalanceImage(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BalanceImage(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f11364c = "";
        m12586a(context);
    }

    /* renamed from: a */
    private void m12586a(Context context) {
        this.f11362a = context.getResources().getDrawable(C2425R.C2426drawable.redbagbalance);
        this.f11363b = new Paint();
        this.f11363b.setColor(-16777216);
        this.f11363b.setTextAlign(Paint.Align.CENTER);
        this.f11363b.setAntiAlias(true);
        this.f11363b.setFlags(1);
        this.f11363b.setFakeBoldText(true);
        this.f11363b.setTextSize((float) DensityUtil.m14128a(context, 12.0f));
        this.f11363b.getTextBounds(this.f11364c, 0, this.f11364c.length(), new Rect());
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        this.f11365d = m12588b(i);
        this.f11366e = m12585a(i2);
        setMeasuredDimension(this.f11365d, this.f11366e);
        this.f11367f = new Rect((this.f11365d / 2) - (this.f11362a.getIntrinsicWidth() / 2), (this.f11366e / 2) - (this.f11362a.getIntrinsicHeight() / 2), (this.f11365d / 2) + (this.f11362a.getIntrinsicWidth() / 2), (this.f11366e / 2) + (this.f11362a.getIntrinsicHeight() / 2));
        this.f11362a.setBounds(this.f11367f);
    }

    /* renamed from: a */
    private int m12585a(int i) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode == 1073741824) {
            return size;
        }
        int intrinsicHeight = this.f11362a.getIntrinsicHeight();
        if (mode == Integer.MIN_VALUE) {
            return Math.min(intrinsicHeight, size);
        }
        return intrinsicHeight;
    }

    /* renamed from: b */
    private int m12588b(int i) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode == 1073741824) {
            return size;
        }
        int intrinsicWidth = this.f11362a.getIntrinsicWidth();
        if (mode == Integer.MIN_VALUE) {
            return Math.min(intrinsicWidth, size);
        }
        return intrinsicWidth;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        m12587a(canvas);
    }

    /* renamed from: a */
    private void m12587a(Canvas canvas) {
        this.f11362a.draw(canvas);
        Paint.FontMetricsInt fontMetricsInt = this.f11363b.getFontMetricsInt();
        canvas.drawText(this.f11364c, (float) this.f11367f.centerX(), (float) ((((getMeasuredHeight() - fontMetricsInt.bottom) + fontMetricsInt.top) / 2) - fontMetricsInt.top), this.f11363b);
    }

    /* renamed from: a */
    public void mo26737a(String str) {
        this.f11364c = str;
        postInvalidate();
    }

    /* renamed from: a */
    public String mo26736a() {
        return this.f11364c;
    }
}
