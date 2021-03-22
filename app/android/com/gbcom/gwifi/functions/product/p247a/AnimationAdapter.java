package com.gbcom.gwifi.functions.product.p247a;

import android.annotation.SuppressLint;
import android.os.Build;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import com.gbcom.gwifi.functions.listview.BaseAdapterDecorator;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;

/* renamed from: com.gbcom.gwifi.functions.product.a.a */
public abstract class AnimationAdapter extends BaseAdapterDecorator {

    /* renamed from: b */
    protected static final long f10709b = 100;

    /* renamed from: c */
    protected static final long f10710c = 300;

    /* renamed from: d */
    private static final long f10711d = 150;

    /* renamed from: e */
    private SparseArray<C2931a> f10712e = new SparseArray<>();

    /* renamed from: f */
    private long f10713f = -1;

    /* renamed from: g */
    private int f10714g;

    /* renamed from: h */
    private int f10715h = -1;

    /* renamed from: i */
    private boolean f10716i;

    /* renamed from: j */
    private boolean f10717j = true;

    /* renamed from: a */
    public abstract Animator[] mo25527a(ViewGroup viewGroup, View view);

    /* access modifiers changed from: protected */
    /* renamed from: h */
    public abstract long mo25534h();

    /* access modifiers changed from: protected */
    /* renamed from: i */
    public abstract long mo25535i();

    public AnimationAdapter(BaseAdapter baseAdapter) {
        super(baseAdapter);
        if (baseAdapter instanceof AnimationAdapter) {
            ((AnimationAdapter) baseAdapter).mo25530c(true);
        }
    }

    /* renamed from: e */
    public void mo25531e() {
        this.f10712e.clear();
        this.f10714g = 0;
        this.f10715h = -1;
        this.f10713f = -1;
        this.f10717j = true;
        if (mo24871b() instanceof AnimationAdapter) {
            ((AnimationAdapter) mo24871b()).mo25531e();
        }
    }

    /* renamed from: b */
    public void mo25529b(boolean z) {
        this.f10717j = z;
    }

    /* renamed from: b */
    public void mo25528b(int i) {
        this.f10717j = true;
        this.f10714g = i - 1;
        this.f10715h = i - 1;
    }

    /* renamed from: f */
    public void mo25532f() {
        if (mo24865a() == null) {
            throw new IllegalStateException("Call setListView() on this AnimationAdapter before setShouldAnimateNotVisible()!");
        }
        this.f10717j = true;
        this.f10714g = mo24865a().getLastVisiblePosition();
        this.f10715h = mo24865a().getLastVisiblePosition();
    }

    @Override // com.gbcom.gwifi.functions.listview.BaseAdapterDecorator
    public final View getView(int i, View view, ViewGroup viewGroup) {
        boolean z = false;
        if (!this.f10716i) {
            if (mo24865a() == null) {
                throw new IllegalStateException("Call setListView() on this AnimationAdapter before setAdapter()!");
            } else if (view != null) {
                z = m12118a(i, view);
            }
        }
        View view2 = super.getView(i, view, viewGroup);
        if (!this.f10716i && !z) {
            m12115a(i, view2, viewGroup);
        }
        return view2;
    }

    /* renamed from: a */
    private boolean m12118a(int i, View view) {
        int hashCode = view.hashCode();
        C2931a aVar = this.f10712e.get(hashCode);
        if (aVar == null) {
            return false;
        }
        if (aVar.f10718a == i) {
            return true;
        }
        aVar.f10719b.end();
        this.f10712e.remove(hashCode);
        return false;
    }

    /* renamed from: a */
    private void m12115a(int i, View view, ViewGroup viewGroup) {
        if (i > this.f10715h && this.f10717j) {
            m12116a(i, viewGroup, view, false);
            this.f10715h = i;
        }
    }

    /* renamed from: a */
    private void m12116a(int i, ViewGroup viewGroup, View view, boolean z) {
        Animator[] animatorArr;
        if (this.f10713f == -1) {
            this.f10713f = System.currentTimeMillis();
        }
        m12117a(view);
        if (this.f9411a instanceof AnimationAdapter) {
            animatorArr = ((AnimationAdapter) this.f9411a).mo25527a(viewGroup, view);
        } else {
            animatorArr = new Animator[0];
        }
        Animator[] a = mo25527a(viewGroup, view);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "alpha", 0.0f, 1.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(m12119a(animatorArr, a, ofFloat));
        animatorSet.setStartDelay(m12120d(z));
        animatorSet.setDuration(mo25535i());
        animatorSet.start();
        this.f10712e.put(view.hashCode(), new C2931a(i, animatorSet));
    }

    /* renamed from: a */
    private void m12117a(View view) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "alpha", 0.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(ofFloat);
        animatorSet.setDuration(0L);
        animatorSet.start();
    }

    /* renamed from: a */
    private Animator[] m12119a(Animator[] animatorArr, Animator[] animatorArr2, Animator animator) {
        Animator[] animatorArr3 = new Animator[(animatorArr.length + animatorArr2.length + 1)];
        int i = 0;
        while (i < animatorArr2.length) {
            animatorArr3[i] = animatorArr2[i];
            i++;
        }
        for (Animator animator2 : animatorArr) {
            animatorArr3[i] = animator2;
            i++;
        }
        animatorArr3[animatorArr3.length - 1] = animator;
        return animatorArr3;
    }

    @SuppressLint({"NewApi"})
    /* renamed from: d */
    private long m12120d(boolean z) {
        long h;
        if ((mo24865a().getLastVisiblePosition() - mo24865a().getFirstVisiblePosition()) + 1 < this.f10715h) {
            long h2 = mo25534h();
            h = (!(mo24865a() instanceof GridView) || Build.VERSION.SDK_INT < 11) ? h2 : (((long) ((this.f10715h + 1) % ((GridView) mo24865a()).getNumColumns())) * mo25534h()) + h2;
        } else {
            h = (((((long) ((this.f10715h - this.f10714g) + 1)) * mo25534h()) + (this.f10713f + mo25533g())) - System.currentTimeMillis()) - ((!z || this.f10715h <= 0) ? 0 : mo25534h());
        }
        return Math.max(0L, h);
    }

    /* renamed from: c */
    public void mo25530c(boolean z) {
        this.f10716i = z;
    }

    /* access modifiers changed from: protected */
    /* renamed from: g */
    public long mo25533g() {
        return f10711d;
    }

    /* access modifiers changed from: private */
    /* renamed from: com.gbcom.gwifi.functions.product.a.a$a */
    /* compiled from: AnimationAdapter */
    public class C2931a {

        /* renamed from: a */
        public int f10718a;

        /* renamed from: b */
        public Animator f10719b;

        public C2931a(int i, Animator animator) {
            this.f10718a = i;
            this.f10719b = animator;
        }
    }
}
