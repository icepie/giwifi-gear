package com.gbcom.gwifi.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.RelativeLayout;

public class ClipImageLayout extends RelativeLayout {

    /* renamed from: a */
    private ClipZoomImageView f13572a;

    /* renamed from: b */
    private ClipImageBorderView f13573b;

    /* renamed from: c */
    private int f13574c = 0;

    public ClipImageLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f13572a = new ClipZoomImageView(context);
        this.f13573b = new ClipImageBorderView(context);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        addView(this.f13572a, layoutParams);
        addView(this.f13573b, layoutParams);
        this.f13574c = (int) TypedValue.applyDimension(1, (float) this.f13574c, getResources().getDisplayMetrics());
        this.f13572a.mo28244a(this.f13574c);
        this.f13573b.mo28236a(this.f13574c);
    }

    /* renamed from: a */
    public void mo28241a(Drawable drawable) {
        this.f13572a.setImageDrawable(drawable);
    }

    /* renamed from: a */
    public void mo28240a(Bitmap bitmap) {
        this.f13572a.setImageBitmap(bitmap);
    }

    /* renamed from: a */
    public void mo28239a(int i) {
        this.f13574c = i;
    }

    /* renamed from: a */
    public Bitmap mo28238a() {
        return this.f13572a.mo28245b();
    }
}
