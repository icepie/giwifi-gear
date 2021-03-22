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

public class BonusImage extends View {

    /* renamed from: a */
    private Drawable f11368a;

    /* renamed from: b */
    private Paint f11369b;

    /* renamed from: c */
    private Paint f11370c;

    /* renamed from: d */
    private String f11371d;

    /* renamed from: e */
    private String f11372e;

    /* renamed from: f */
    private Rect f11373f;

    /* renamed from: g */
    private int f11374g;

    /* renamed from: h */
    private int f11375h;

    public BonusImage(Context context) {
        this(context, null);
    }

    public BonusImage(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BonusImage(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f11371d = "1000";
        this.f11372e = "恭喜您抽取辛运数字198,获得旺豆1000";
        m12592a(context);
    }

    /* renamed from: a */
    private void m12592a(Context context) {
        this.f11368a = context.getResources().getDrawable(C2425R.C2426drawable.bonus);
        this.f11369b = new Paint();
        this.f11369b.setColor(-65536);
        this.f11369b.setTextAlign(Paint.Align.CENTER);
        this.f11369b.setAntiAlias(true);
        this.f11369b.setFlags(1);
        this.f11369b.setFakeBoldText(true);
        this.f11369b.setTextSize((float) DensityUtil.m14128a(context, 24.0f));
        this.f11369b.getTextBounds(this.f11371d, 0, this.f11371d.length(), new Rect());
        this.f11370c = new Paint();
        this.f11370c.setColor(-65536);
        this.f11370c.setTextAlign(Paint.Align.CENTER);
        this.f11370c.setAntiAlias(true);
        this.f11370c.setFlags(1);
        this.f11370c.setFakeBoldText(true);
        this.f11370c.setTextSize((float) DensityUtil.m14128a(context, 12.0f));
        this.f11369b.getTextBounds(this.f11372e, 0, this.f11372e.length(), new Rect());
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        this.f11374g = m12594b(i);
        this.f11375h = m12591a(i2);
        setMeasuredDimension(this.f11374g, this.f11375h);
        this.f11373f = new Rect(0, 0, this.f11374g, this.f11375h);
        this.f11368a.setBounds(this.f11373f);
    }

    /* renamed from: a */
    private int m12591a(int i) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode == 1073741824) {
            return size;
        }
        int intrinsicHeight = this.f11368a.getIntrinsicHeight();
        if (mode == Integer.MIN_VALUE) {
            return Math.min(intrinsicHeight, size);
        }
        return intrinsicHeight;
    }

    /* renamed from: b */
    private int m12594b(int i) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode == 1073741824) {
            return size;
        }
        int intrinsicWidth = this.f11368a.getIntrinsicWidth();
        if (mode == Integer.MIN_VALUE) {
            return Math.min(intrinsicWidth, size);
        }
        return intrinsicWidth;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        m12593a(canvas);
    }

    /* renamed from: a */
    private void m12593a(Canvas canvas) {
        this.f11368a.draw(canvas);
        canvas.drawText(this.f11371d, (float) this.f11373f.centerX(), (float) (this.f11373f.centerY() - (this.f11368a.getIntrinsicWidth() / 20)), this.f11369b);
        canvas.drawText(this.f11372e, (float) this.f11373f.centerX(), (float) (this.f11373f.centerY() + (this.f11368a.getIntrinsicWidth() / 4)), this.f11370c);
    }

    /* renamed from: a */
    public void mo26740a(String str, String str2) {
        this.f11371d = str;
        this.f11372e = str2;
        postInvalidate();
    }
}
