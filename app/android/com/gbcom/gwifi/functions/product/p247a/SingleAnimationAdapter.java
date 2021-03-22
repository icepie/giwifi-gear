package com.gbcom.gwifi.functions.product.p247a;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.nineoldandroids.animation.Animator;

/* renamed from: com.gbcom.gwifi.functions.product.a.b */
public abstract class SingleAnimationAdapter extends AnimationAdapter {
    /* access modifiers changed from: protected */
    /* renamed from: b */
    public abstract Animator mo25536b(ViewGroup viewGroup, View view);

    public SingleAnimationAdapter(BaseAdapter baseAdapter) {
        super(baseAdapter);
    }

    @Override // com.gbcom.gwifi.functions.product.p247a.AnimationAdapter
    /* renamed from: a */
    public Animator[] mo25527a(ViewGroup viewGroup, View view) {
        return new Animator[]{mo25536b(viewGroup, view)};
    }
}
