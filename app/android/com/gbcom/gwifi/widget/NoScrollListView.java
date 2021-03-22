package com.gbcom.gwifi.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;

public class NoScrollListView extends ListView {

    /* renamed from: a */
    private int f13696a;

    /* renamed from: b */
    private int f13697b;

    public NoScrollListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public NoScrollListView(Context context) {
        super(context);
    }

    public NoScrollListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void onMeasure(int i, int i2) {
        super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(536870911, Integer.MIN_VALUE));
    }
}
