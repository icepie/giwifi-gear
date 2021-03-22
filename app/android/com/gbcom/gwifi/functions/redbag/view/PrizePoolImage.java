package com.gbcom.gwifi.functions.redbag.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.util.p257b.DensityUtil;

public class PrizePoolImage extends View {

    /* renamed from: a */
    private Drawable f11382a;

    /* renamed from: b */
    private Paint f11383b;

    /* renamed from: c */
    private String f11384c;

    /* renamed from: d */
    private Rect f11385d;

    /* renamed from: e */
    private int f11386e;

    /* renamed from: f */
    private int f11387f;

    public PrizePoolImage(Context context) {
        this(context, null);
    }

    public PrizePoolImage(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PrizePoolImage(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f11384c = "";
        m12603a(context);
    }

    /* renamed from: a */
    private void m12603a(Context context) {
        this.f11382a = context.getResources().getDrawable(C2425R.C2426drawable.prizepool);
        this.f11383b = new Paint();
        this.f11383b.setColor(-16777216);
        this.f11383b.setTextAlign(Paint.Align.CENTER);
        this.f11383b.setAntiAlias(true);
        this.f11383b.setFlags(1);
        this.f11383b.setFakeBoldText(true);
        this.f11383b.setTextSize((float) DensityUtil.m14128a(context, 12.0f));
        this.f11383b.getTextBounds(this.f11384c, 0, this.f11384c.length(), new Rect());
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        this.f11386e = m12605b(i);
        this.f11387f = m12602a(i2);
        setMeasuredDimension(this.f11386e, this.f11387f);
        this.f11385d = new Rect((this.f11386e / 2) - (this.f11382a.getIntrinsicWidth() / 2), (this.f11387f / 2) - (this.f11382a.getIntrinsicHeight() / 2), (this.f11386e / 2) + (this.f11382a.getIntrinsicWidth() / 2), (this.f11387f / 2) + (this.f11382a.getIntrinsicHeight() / 2));
        this.f11382a.setBounds(this.f11385d);
    }

    /* renamed from: a */
    private int m12602a(int i) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode == 1073741824) {
            return size;
        }
        int intrinsicHeight = this.f11382a.getIntrinsicHeight();
        if (mode == Integer.MIN_VALUE) {
            return Math.min(intrinsicHeight, size);
        }
        return intrinsicHeight;
    }

    /* renamed from: b */
    private int m12605b(int i) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode == 1073741824) {
            return size;
        }
        int intrinsicWidth = this.f11382a.getIntrinsicWidth();
        if (mode == Integer.MIN_VALUE) {
            return Math.min(intrinsicWidth, size);
        }
        return intrinsicWidth;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        m12604a(canvas);
    }

    /* renamed from: a */
    private void m12604a(Canvas canvas) {
        this.f11382a.draw(canvas);
        canvas.drawText(this.f11384c, (float) (this.f11385d.centerX() + (this.f11382a.getIntrinsicWidth() / 5)), (float) (this.f11385d.centerY() + ((this.f11382a.getIntrinsicWidth() * 24) / 109)), this.f11383b);
    }

    /* renamed from: a */
    public void mo26747a(String str) {
        this.f11384c = str;
        postInvalidate();
    }
}
