package com.gbcom.gwifi.base.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import com.gbcom.gwifi.C2425R;

/* renamed from: com.gbcom.gwifi.base.activity.b */
public class LabelIndicatorStrategy {

    /* renamed from: a */
    private Context f8802a;

    /* renamed from: b */
    private int f8803b;

    /* renamed from: c */
    private final CharSequence f8804c;

    public LabelIndicatorStrategy(Context context, CharSequence charSequence) {
        this.f8802a = context;
        this.f8804c = charSequence;
    }

    public LabelIndicatorStrategy(Context context, CharSequence charSequence, int i) {
        this(context, charSequence);
        this.f8803b = i;
    }

    /* renamed from: a */
    public View mo24349a(TabHost tabHost) {
        if (this.f8803b == 0) {
            this.f8803b = C2425R.layout.tab_indicator_holo;
        }
        return ((LayoutInflater) this.f8802a.getSystemService("layout_inflater")).inflate(this.f8803b, (ViewGroup) tabHost.getTabWidget(), false);
    }
}
