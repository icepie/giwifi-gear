package com.gbcom.gwifi.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;

public class CustomListView extends ListView {
    public CustomListView(Context context) {
        this(context, null);
    }

    public CustomListView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CustomListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(536870911, Integer.MIN_VALUE));
    }
}
