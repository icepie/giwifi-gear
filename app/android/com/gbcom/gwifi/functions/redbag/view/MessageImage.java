package com.gbcom.gwifi.functions.redbag.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.View;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.util.p257b.DensityUtil;

public class MessageImage extends View {

    /* renamed from: a */
    private Drawable f11376a;

    /* renamed from: b */
    private Paint f11377b;

    /* renamed from: c */
    private CharSequence f11378c;

    /* renamed from: d */
    private int f11379d;

    /* renamed from: e */
    private int f11380e;

    /* renamed from: f */
    private Rect f11381f;

    public MessageImage(Context context) {
        this(context, null);
    }

    public MessageImage(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MessageImage(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f11378c = "恭喜185xxxx5986抽中幸运数字188,获得1442旺豆";
        m12597a(context);
    }

    /* renamed from: a */
    private void m12597a(Context context) {
        this.f11376a = context.getResources().getDrawable(C2425R.C2426drawable.redbag_messege);
        this.f11377b = new Paint();
        this.f11377b.setColor(-1);
        this.f11377b.setTextAlign(Paint.Align.CENTER);
        this.f11377b.setAntiAlias(true);
        this.f11377b.setFlags(1);
        this.f11377b.setFakeBoldText(true);
        this.f11377b.setTextSize((float) DensityUtil.m14128a(context, 12.0f));
        this.f11377b.getTextBounds(((Object) this.f11378c) + "", 0, (((Object) this.f11378c) + "").length(), new Rect());
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        this.f11379d = m12599b(i);
        this.f11380e = m12596a(i2);
        setMeasuredDimension(this.f11379d, this.f11380e);
        this.f11381f = new Rect((this.f11379d / 2) - (this.f11376a.getIntrinsicWidth() / 2), (this.f11380e / 2) - (this.f11376a.getIntrinsicHeight() / 2), (this.f11379d / 2) + (this.f11376a.getIntrinsicWidth() / 2), (this.f11380e / 2) + (this.f11376a.getIntrinsicHeight() / 2));
        this.f11376a.setBounds(this.f11381f);
    }

    /* renamed from: a */
    private int m12596a(int i) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode == 1073741824) {
            return size;
        }
        int intrinsicHeight = this.f11376a.getIntrinsicHeight();
        if (mode == Integer.MIN_VALUE) {
            return Math.min(intrinsicHeight, size);
        }
        return intrinsicHeight;
    }

    /* renamed from: b */
    private int m12599b(int i) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode == 1073741824) {
            return size;
        }
        int intrinsicWidth = this.f11376a.getIntrinsicWidth();
        if (mode == Integer.MIN_VALUE) {
            return Math.min(intrinsicWidth, size);
        }
        return intrinsicWidth;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        m12598a(canvas);
    }

    /* renamed from: a */
    private void m12598a(Canvas canvas) {
        this.f11376a.draw(canvas);
        Paint.FontMetricsInt fontMetricsInt = this.f11377b.getFontMetricsInt();
        int measuredHeight = (((getMeasuredHeight() - fontMetricsInt.bottom) + fontMetricsInt.top) / 2) - fontMetricsInt.top;
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(this.f11378c);
        new ForegroundColorSpan(getResources().getColor(C2425R.color.redbagBottomText));
        canvas.drawText(spannableStringBuilder, 0, spannableStringBuilder.length(), (float) this.f11381f.centerX(), (float) this.f11381f.centerY(), this.f11377b);
    }

    /* renamed from: a */
    public void mo26744a(CharSequence charSequence) {
        this.f11378c = charSequence;
        postInvalidate();
    }

    /* renamed from: a */
    public CharSequence mo26743a() {
        return this.f11378c;
    }
}
