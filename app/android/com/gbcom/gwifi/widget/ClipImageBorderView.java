package com.gbcom.gwifi.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

public class ClipImageBorderView extends View {

    /* renamed from: a */
    private int f13566a;

    /* renamed from: b */
    private int f13567b;

    /* renamed from: c */
    private int f13568c;

    /* renamed from: d */
    private int f13569d;

    /* renamed from: e */
    private int f13570e;

    /* renamed from: f */
    private Paint f13571f;

    public ClipImageBorderView(Context context) {
        this(context, null);
    }

    public ClipImageBorderView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ClipImageBorderView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f13569d = Color.parseColor("#FFFFFF");
        this.f13570e = 1;
        this.f13570e = (int) TypedValue.applyDimension(1, (float) this.f13570e, getResources().getDisplayMetrics());
        this.f13571f = new Paint();
        this.f13571f.setAntiAlias(true);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.f13568c = getWidth() - (this.f13566a * 2);
        this.f13567b = (getHeight() - this.f13568c) / 2;
        this.f13571f.setColor(Color.parseColor("#aa000000"));
        this.f13571f.setStyle(Paint.Style.FILL);
        canvas.drawRect(0.0f, 0.0f, (float) this.f13566a, (float) getHeight(), this.f13571f);
        canvas.drawRect((float) (getWidth() - this.f13566a), 0.0f, (float) getWidth(), (float) getHeight(), this.f13571f);
        canvas.drawRect((float) this.f13566a, 0.0f, (float) (getWidth() - this.f13566a), (float) this.f13567b, this.f13571f);
        canvas.drawRect((float) this.f13566a, (float) (getHeight() - this.f13567b), (float) (getWidth() - this.f13566a), (float) getHeight(), this.f13571f);
        this.f13571f.setColor(this.f13569d);
        this.f13571f.setStrokeWidth((float) this.f13570e);
        this.f13571f.setStyle(Paint.Style.STROKE);
        canvas.drawRect((float) this.f13566a, (float) this.f13567b, (float) (getWidth() - this.f13566a), (float) (getHeight() - this.f13567b), this.f13571f);
    }

    /* renamed from: a */
    public void mo28236a(int i) {
        this.f13566a = i;
    }
}
