package com.gbcom.gwifi.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.util.Log;
import com.gbcom.gwifi.util.SystemUtil;

public class CircleProgress extends FrameLayout implements View.OnClickListener {

    /* renamed from: a */
    private static final String f13523a = "CircleProgress";

    /* renamed from: b */
    private Paint f13524b;

    /* renamed from: c */
    private Paint f13525c;

    /* renamed from: d */
    private Paint f13526d;

    /* renamed from: e */
    private ImageView f13527e;

    /* renamed from: f */
    private ImageView f13528f;

    /* renamed from: g */
    private Integer f13529g = 0;

    public CircleProgress(Context context) {
        super(context);
        mo28199a();
    }

    public CircleProgress(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        mo28199a();
    }

    /* renamed from: a */
    public void mo28199a() {
        int parseColor;
        setOnClickListener(this);
        this.f13528f = new ImageView(getContext());
        this.f13528f.setImageResource(C2425R.C2426drawable.one_key_check_btn);
        this.f13528f.setScaleType(ImageView.ScaleType.FIT_CENTER);
        this.f13524b = new Paint(1);
        this.f13524b.setColor(Color.parseColor("#cccccc"));
        this.f13524b.setStyle(Paint.Style.FILL_AND_STROKE);
        this.f13524b.setAntiAlias(true);
        if (SystemUtil.m14531e()) {
            parseColor = getResources().getColor(C2425R.color.yellow_4);
        } else {
            parseColor = Color.parseColor("#00ACEE");
        }
        this.f13525c = new Paint(1);
        this.f13525c.setColor(parseColor);
        this.f13525c.setStyle(Paint.Style.STROKE);
        this.f13525c.setAntiAlias(true);
        this.f13525c.setStrokeWidth(10.0f);
        this.f13526d = new Paint(1);
        this.f13526d.setColor(parseColor);
        this.f13526d.setStyle(Paint.Style.FILL_AND_STROKE);
        this.f13526d.setAntiAlias(true);
        this.f13527e = new ImageView(getContext());
        addView(this.f13527e);
        addView(this.f13528f, new FrameLayout.LayoutParams(-2, -2, 17));
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
    }

    public void onClick(View view) {
    }

    /* renamed from: a */
    public void mo28200a(Integer num) {
        this.f13529g = num;
        Log.m14400c(f13523a, "progress..." + ((Object) num));
        int width = getWidth();
        int height = getHeight();
        Log.m14400c(f13523a, "width..." + ((Object) num));
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(width - 40, height - 40, 17);
        int i = width - 10;
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawArc(new RectF(10.0f, 10.0f, (float) i, (float) i), (float) (((num.intValue() * 360) / 100) - 90), (float) ((num.intValue() * 360) / 100), false, this.f13525c);
        canvas.drawCircle((float) (((double) (width / 2)) + (((double) ((i - 10) / 2)) * Math.sin((3.141592653589793d * ((double) (((num.intValue() * 360) / 100) * 2))) / 180.0d))), (float) (((double) (width / 2)) - (((double) ((i - 10) / 2)) * Math.cos((3.141592653589793d * ((double) (((num.intValue() * 360) / 100) * 2))) / 180.0d))), 10.0f, this.f13526d);
        this.f13527e.setImageBitmap(createBitmap);
        updateViewLayout(this.f13528f, layoutParams);
    }
}
