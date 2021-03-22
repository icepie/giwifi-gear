package com.gbcom.gwifi.functions.product.p247a;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.ObjectAnimator;

/* renamed from: com.gbcom.gwifi.functions.product.a.c */
public class SwingLeftInAnimationAdapter extends SingleAnimationAdapter {

    /* renamed from: d */
    private final long f10721d;

    /* renamed from: e */
    private final long f10722e;

    public SwingLeftInAnimationAdapter(BaseAdapter baseAdapter) {
        this(baseAdapter, 100, 300);
    }

    public SwingLeftInAnimationAdapter(BaseAdapter baseAdapter, long j) {
        this(baseAdapter, j, 300);
    }

    public SwingLeftInAnimationAdapter(BaseAdapter baseAdapter, long j, long j2) {
        super(baseAdapter);
        this.f10721d = j;
        this.f10722e = j2;
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.functions.product.p247a.AnimationAdapter
    /* renamed from: h */
    public long mo25534h() {
        return this.f10721d;
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.functions.product.p247a.AnimationAdapter
    /* renamed from: i */
    public long mo25535i() {
        return this.f10722e;
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.functions.product.p247a.SingleAnimationAdapter
    /* renamed from: b */
    public Animator mo25536b(ViewGroup viewGroup, View view) {
        return ObjectAnimator.ofFloat(view, "translationX", (float) (0 - viewGroup.getWidth()), 0.0f);
    }
}
