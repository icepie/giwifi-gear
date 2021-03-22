package com.gbcom.gwifi.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridView;

public class MyGridView extends GridView {

    /* renamed from: a */
    public boolean f13695a;

    public MyGridView(Context context) {
        this(context, null);
    }

    public MyGridView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MyGridView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        this.f13695a = true;
        super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(536870911, Integer.MIN_VALUE));
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        this.f13695a = false;
        super.onLayout(z, i, i2, i3, i4);
    }
}
