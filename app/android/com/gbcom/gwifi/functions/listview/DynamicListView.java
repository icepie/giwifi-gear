package com.gbcom.gwifi.functions.listview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.p009v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorListenerAdapter;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.TypeEvaluator;
import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.view.ViewHelper;

public class DynamicListView extends ListView {

    /* renamed from: D */
    private static final TypeEvaluator<Rect> f9365D = new TypeEvaluator<Rect>() {
        /* class com.gbcom.gwifi.functions.listview.DynamicListView.C26425 */

        /* renamed from: a */
        public Rect evaluate(float f, Rect rect, Rect rect2) {
            return new Rect(mo24855a(rect.left, rect2.left, f), mo24855a(rect.top, rect2.top, f), mo24855a(rect.right, rect2.right, f), mo24855a(rect.bottom, rect2.bottom, f));
        }

        /* renamed from: a */
        public int mo24855a(int i, int i2, float f) {
            return (int) (((float) i) + (((float) (i2 - i)) * f));
        }
    };

    /* renamed from: A */
    private boolean f9366A;

    /* renamed from: B */
    private AbstractC2644a f9367B;

    /* renamed from: C */
    private AdapterView.OnItemLongClickListener f9368C = new AdapterView.OnItemLongClickListener() {
        /* class com.gbcom.gwifi.functions.listview.DynamicListView.C26381 */

        @Override // android.widget.AdapterView.OnItemLongClickListener
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
            if (DynamicListView.this.f9393x != 0) {
                return false;
            }
            DynamicListView.this.f9394y = true;
            DynamicListView.this.m11045b();
            return true;
        }
    };

    /* renamed from: E */
    private AbsListView.OnScrollListener f9369E = new AbsListView.OnScrollListener() {
        /* class com.gbcom.gwifi.functions.listview.DynamicListView.C26436 */

        /* renamed from: b */
        private int f9406b = -1;

        /* renamed from: c */
        private int f9407c = -1;

        /* renamed from: d */
        private int f9408d;

        /* renamed from: e */
        private int f9409e;

        /* renamed from: f */
        private int f9410f;

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            this.f9408d = i;
            this.f9409e = i2;
            this.f9406b = this.f9406b == -1 ? this.f9408d : this.f9406b;
            this.f9407c = this.f9407c == -1 ? this.f9409e : this.f9407c;
            mo24858a();
            mo24859b();
            this.f9406b = this.f9408d;
            this.f9407c = this.f9409e;
        }

        public void onScrollStateChanged(AbsListView absListView, int i) {
            this.f9410f = i;
            DynamicListView.this.f9390u = i;
            m11072c();
        }

        /* renamed from: c */
        private void m11072c() {
            if (this.f9409e > 0 && this.f9410f == 0) {
                if (DynamicListView.this.f9377h && DynamicListView.this.f9378i) {
                    DynamicListView.this.m11056f();
                } else if (DynamicListView.this.f9389t) {
                    DynamicListView.this.m11051d();
                }
            }
        }

        /* renamed from: a */
        public void mo24858a() {
            if (this.f9408d != this.f9406b && DynamicListView.this.f9377h && DynamicListView.this.f9382m != -1) {
                DynamicListView.this.m11050c((DynamicListView) DynamicListView.this.f9382m);
                DynamicListView.this.m11049c();
            }
        }

        /* renamed from: b */
        public void mo24859b() {
            if (this.f9408d + this.f9409e != this.f9406b + this.f9407c && DynamicListView.this.f9377h && DynamicListView.this.f9382m != -1) {
                DynamicListView.this.m11050c((DynamicListView) DynamicListView.this.f9382m);
                DynamicListView.this.m11049c();
            }
        }
    };

    /* renamed from: a */
    private final int f9370a = 15;

    /* renamed from: b */
    private final int f9371b = 150;

    /* renamed from: c */
    private int f9372c = -1;

    /* renamed from: d */
    private int f9373d = -1;

    /* renamed from: e */
    private int f9374e = -1;

    /* renamed from: f */
    private int f9375f = -1;

    /* renamed from: g */
    private int f9376g = 0;

    /* renamed from: h */
    private boolean f9377h = false;

    /* renamed from: i */
    private boolean f9378i = false;

    /* renamed from: j */
    private int f9379j = 0;

    /* renamed from: k */
    private final int f9380k = -1;

    /* renamed from: l */
    private long f9381l = -1;

    /* renamed from: m */
    private long f9382m = -1;

    /* renamed from: n */
    private long f9383n = -1;

    /* renamed from: o */
    private Drawable f9384o;

    /* renamed from: p */
    private Rect f9385p;

    /* renamed from: q */
    private Rect f9386q;

    /* renamed from: r */
    private final int f9387r = -1;

    /* renamed from: s */
    private int f9388s = -1;

    /* renamed from: t */
    private boolean f9389t = false;

    /* renamed from: u */
    private int f9390u = 0;

    /* renamed from: v */
    private View.OnTouchListener f9391v;

    /* renamed from: w */
    private boolean f9392w;

    /* renamed from: x */
    private int f9393x;

    /* renamed from: y */
    private boolean f9394y;

    /* renamed from: z */
    private int f9395z;

    /* renamed from: com.gbcom.gwifi.functions.listview.DynamicListView$a */
    public interface AbstractC2644a {
        /* renamed from: a */
        Drawable mo24862a(Drawable drawable);
    }

    /* renamed from: com.gbcom.gwifi.functions.listview.DynamicListView$b */
    public interface AbstractC2645b {
        /* renamed from: a */
        void mo24863a(int i, int i2);
    }

    /* renamed from: com.gbcom.gwifi.functions.listview.DynamicListView$c */
    public interface AbstractView$OnTouchListenerC2646c extends View.OnTouchListener {
        /* renamed from: a */
        boolean mo24864a();
    }

    public DynamicListView(Context context) {
        super(context);
        mo24841a(context);
    }

    public DynamicListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        mo24841a(context);
    }

    public DynamicListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        mo24841a(context);
    }

    /* renamed from: a */
    public void mo24841a(Context context) {
        setOnItemLongClickListener(this.f9368C);
        setOnScrollListener(this.f9369E);
        this.f9379j = (int) (15.0f / context.getResources().getDisplayMetrics().density);
        this.f9395z = ViewConfiguration.get(getContext()).getScaledTouchSlop();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: b */
    private void m11045b() {
        int pointToPosition = pointToPosition(this.f9375f, this.f9374e);
        View childAt = getChildAt(pointToPosition - getFirstVisiblePosition());
        if (childAt != null) {
            this.f9376g = 0;
            this.f9382m = getAdapter().getItemId(pointToPosition);
            this.f9384o = m11038a(childAt);
            if (this.f9367B != null) {
                this.f9384o = this.f9367B.mo24862a(this.f9384o);
            }
            childAt.setVisibility(4);
            this.f9377h = true;
            getParent().requestDisallowInterceptTouchEvent(true);
            m11050c(this.f9382m);
        }
    }

    /* renamed from: a */
    private BitmapDrawable m11038a(View view) {
        int width = view.getWidth();
        int height = view.getHeight();
        int top = view.getTop();
        int left = view.getLeft();
        BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), m11044b(view));
        this.f9386q = new Rect(left, top, width + left, height + top);
        this.f9385p = new Rect(this.f9386q);
        bitmapDrawable.setBounds(this.f9385p);
        return bitmapDrawable;
    }

    /* renamed from: b */
    private Bitmap m11044b(View view) {
        Bitmap createBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        view.draw(new Canvas(createBitmap));
        return createBitmap;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: c */
    private void m11050c(long j) {
        long j2;
        long j3 = Long.MIN_VALUE;
        int b = mo24846b(j);
        BaseAdapter baseAdapter = (BaseAdapter) getAdapter();
        if (!baseAdapter.hasStableIds()) {
            throw new IllegalStateException("Adapter doesn't have stable ids! Make sure your adapter has stable ids, and override hasStableIds() to return true.");
        }
        if (b - 1 >= 0) {
            j2 = baseAdapter.getItemId(b - 1);
        } else {
            j2 = Long.MIN_VALUE;
        }
        this.f9381l = j2;
        if (b + 1 < baseAdapter.getCount()) {
            j3 = baseAdapter.getItemId(b + 1);
        }
        this.f9383n = j3;
    }

    /* renamed from: a */
    public View mo24839a(long j) {
        int firstVisiblePosition = getFirstVisiblePosition();
        BaseAdapter baseAdapter = (BaseAdapter) getAdapter();
        if (!baseAdapter.hasStableIds()) {
            throw new IllegalStateException("Adapter doesn't have stable ids! Make sure your adapter has stable ids, and override hasStableIds() to return true.");
        }
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (baseAdapter.getItemId(firstVisiblePosition + i) == j) {
                return childAt;
            }
        }
        return null;
    }

    /* renamed from: b */
    public int mo24846b(long j) {
        View a = mo24839a(j);
        if (a == null) {
            return -1;
        }
        return getPositionForView(a);
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (this.f9384o != null) {
            this.f9384o.draw(canvas);
        }
    }

    public void setOnTouchListener(View.OnTouchListener onTouchListener) {
        this.f9391v = onTouchListener;
    }

    /* renamed from: a */
    public void mo24842a(AbstractC2644a aVar) {
        this.f9367B = aVar;
    }

    /* renamed from: a */
    private Rect m11037a(View view, View view2) {
        Rect rect = new Rect(view2.getLeft(), view2.getTop(), view2.getRight(), view2.getBottom());
        if (view == view2) {
            return rect;
        }
        while (true) {
            ViewGroup viewGroup = (ViewGroup) view2.getParent();
            if (viewGroup == view) {
                return rect;
            }
            rect.offset(viewGroup.getLeft(), viewGroup.getTop());
            view2 = viewGroup;
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        View view;
        View view2;
        if (this.f9366A) {
            return super.onTouchEvent(motionEvent);
        }
        if (!(this.f9391v instanceof AbstractView$OnTouchListenerC2646c) || !((AbstractView$OnTouchListenerC2646c) this.f9391v).mo24864a()) {
            switch (motionEvent.getAction() & 255) {
                case 0:
                    this.f9375f = (int) motionEvent.getX();
                    this.f9374e = (int) motionEvent.getY();
                    this.f9388s = motionEvent.getPointerId(0);
                    this.f9394y = false;
                    if (this.f9393x != 0) {
                        this.f9392w = false;
                        int pointToPosition = pointToPosition(this.f9375f, this.f9374e);
                        int firstVisiblePosition = pointToPosition != -1 ? pointToPosition - getFirstVisiblePosition() : -1;
                        if (firstVisiblePosition >= 0) {
                            view = getChildAt(firstVisiblePosition);
                        } else {
                            view = null;
                        }
                        if (view != null) {
                            view2 = view.findViewById(this.f9393x);
                        } else {
                            view2 = null;
                        }
                        if (view2 != null && m11037a(this, view2).contains(this.f9375f, this.f9374e)) {
                            this.f9394y = true;
                            getParent().requestDisallowInterceptTouchEvent(true);
                        }
                    }
                    if (this.f9392w) {
                        getParent().requestDisallowInterceptTouchEvent(true);
                        break;
                    }
                    break;
                case 1:
                    this.f9394y = false;
                    m11051d();
                    break;
                case 2:
                    if (this.f9388s != -1) {
                        int findPointerIndex = motionEvent.findPointerIndex(this.f9388s);
                        this.f9372c = (int) motionEvent.getY(findPointerIndex);
                        this.f9373d = (int) motionEvent.getX(findPointerIndex);
                        int i = this.f9372c - this.f9374e;
                        int i2 = this.f9373d - this.f9375f;
                        if (!this.f9377h && this.f9394y && Math.abs(i) > this.f9395z && Math.abs(i) > Math.abs(i2)) {
                            m11045b();
                            MotionEvent obtain = MotionEvent.obtain(motionEvent);
                            obtain.setAction((motionEvent.getActionIndex() << 8) | 3);
                            super.onTouchEvent(obtain);
                            obtain.recycle();
                        }
                        if (this.f9377h) {
                            this.f9385p.offsetTo(this.f9386q.left, i + this.f9386q.top + this.f9376g);
                            this.f9384o.setBounds(this.f9385p);
                            invalidate();
                            m11049c();
                            this.f9378i = false;
                            m11056f();
                            break;
                        }
                    }
                    break;
                case 3:
                    this.f9394y = false;
                    m11054e();
                    break;
                case 6:
                    if (motionEvent.getPointerId((motionEvent.getAction() & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8) == this.f9388s) {
                        this.f9394y = false;
                        m11051d();
                        break;
                    }
                    break;
            }
            if (this.f9377h) {
                return false;
            }
            if (this.f9391v != null) {
                this.f9366A = true;
                boolean onTouch = this.f9391v.onTouch(this, motionEvent);
                this.f9366A = false;
                if (onTouch) {
                    return true;
                }
            }
            return super.onTouchEvent(motionEvent);
        }
        this.f9366A = true;
        boolean onTouch2 = this.f9391v.onTouch(this, motionEvent);
        this.f9366A = false;
        if (onTouch2) {
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: c */
    private void m11049c() {
        boolean z = true;
        final int i = this.f9372c - this.f9374e;
        int i2 = this.f9386q.top + this.f9376g + i;
        View a = mo24839a(this.f9383n);
        View a2 = mo24839a(this.f9382m);
        View a3 = mo24839a(this.f9381l);
        boolean z2 = a != null && i2 > a.getTop();
        if (a3 == null || i2 >= a3.getTop()) {
            z = false;
        }
        if (z2 || z) {
            final long j = z2 ? this.f9383n : this.f9381l;
            if (z2) {
                a3 = a;
            }
            int positionForView = getPositionForView(a2);
            if (a3 == null) {
                m11050c(this.f9382m);
                return;
            }
            m11040a(positionForView, getPositionForView(a3));
            ((BaseAdapter) getAdapter()).notifyDataSetChanged();
            this.f9374e = this.f9372c;
            this.f9375f = this.f9373d;
            final int top = a3.getTop();
            a2.setVisibility(0);
            a3.setVisibility(4);
            m11050c(this.f9382m);
            final ViewTreeObserver viewTreeObserver = getViewTreeObserver();
            viewTreeObserver.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                /* class com.gbcom.gwifi.functions.listview.DynamicListView.ViewTreeObserver$OnPreDrawListenerC26392 */

                public boolean onPreDraw() {
                    viewTreeObserver.removeOnPreDrawListener(this);
                    View a = DynamicListView.this.mo24839a(j);
                    DynamicListView.this.f9376g += i;
                    ViewHelper.setTranslationY(a, (float) (top - a.getTop()));
                    ObjectAnimator ofFloat = ObjectAnimator.ofFloat(a, "translationY", 0.0f);
                    ofFloat.setDuration(150L);
                    ofFloat.start();
                    return true;
                }
            });
        }
    }

    /* renamed from: a */
    private void m11040a(int i, int i2) {
        ListAdapter adapter = getAdapter();
        if (adapter instanceof AbstractC2645b) {
            ((AbstractC2645b) adapter).mo24863a(i, i2);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: d */
    private void m11051d() {
        final View a = mo24839a(this.f9382m);
        if (this.f9377h || this.f9389t) {
            this.f9377h = false;
            this.f9389t = false;
            this.f9378i = false;
            this.f9388s = -1;
            if (this.f9390u != 0) {
                this.f9389t = true;
                return;
            }
            this.f9385p.offsetTo(this.f9386q.left, a.getTop());
            ObjectAnimator ofObject = ObjectAnimator.ofObject(this.f9384o, "bounds", f9365D, this.f9385p);
            ofObject.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                /* class com.gbcom.gwifi.functions.listview.DynamicListView.C26403 */

                @Override // com.nineoldandroids.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    DynamicListView.this.invalidate();
                }
            });
            ofObject.addListener(new AnimatorListenerAdapter() {
                /* class com.gbcom.gwifi.functions.listview.DynamicListView.C26414 */

                @Override // com.nineoldandroids.animation.AnimatorListenerAdapter, com.nineoldandroids.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animator) {
                    DynamicListView.this.setEnabled(false);
                }

                @Override // com.nineoldandroids.animation.AnimatorListenerAdapter, com.nineoldandroids.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    DynamicListView.this.f9381l = -1;
                    DynamicListView.this.f9382m = -1;
                    DynamicListView.this.f9383n = -1;
                    a.setVisibility(0);
                    DynamicListView.this.f9384o = null;
                    DynamicListView.this.setEnabled(true);
                    DynamicListView.this.invalidate();
                }
            });
            ofObject.start();
            return;
        }
        m11054e();
    }

    /* renamed from: e */
    private void m11054e() {
        View a = mo24839a(this.f9382m);
        if (this.f9377h) {
            this.f9381l = -1;
            this.f9382m = -1;
            this.f9383n = -1;
            a.setVisibility(0);
            this.f9384o = null;
            invalidate();
        }
        this.f9377h = false;
        this.f9378i = false;
        this.f9388s = -1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: f */
    private void m11056f() {
        this.f9378i = mo24845a(this.f9385p);
    }

    /* renamed from: a */
    public boolean mo24845a(Rect rect) {
        int computeVerticalScrollOffset = computeVerticalScrollOffset();
        int height = getHeight();
        int computeVerticalScrollExtent = computeVerticalScrollExtent();
        int computeVerticalScrollRange = computeVerticalScrollRange();
        int i = rect.top;
        int height2 = rect.height();
        if (i <= 0 && computeVerticalScrollOffset > 0) {
            smoothScrollBy(-this.f9379j, 0);
            return true;
        } else if (i + height2 < height || computeVerticalScrollOffset + computeVerticalScrollExtent >= computeVerticalScrollRange) {
            return false;
        } else {
            smoothScrollBy(this.f9379j, 0);
            return true;
        }
    }

    /* renamed from: a */
    public void mo24843a(boolean z) {
        if (this.f9393x != 0) {
            z = false;
        }
        this.f9392w = z;
    }

    /* renamed from: a */
    public boolean mo24844a() {
        return this.f9392w;
    }

    /* renamed from: a */
    public void mo24840a(int i) {
        this.f9393x = i;
        if (i != 0) {
            mo24843a(false);
        }
    }
}
