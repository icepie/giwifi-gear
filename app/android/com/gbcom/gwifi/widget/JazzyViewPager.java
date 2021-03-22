package com.gbcom.gwifi.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.p009v4.view.PagerAdapter;
import android.support.p009v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import com.gbcom.gwifi.C2425R;
import com.nineoldandroids.view.ViewHelper;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class JazzyViewPager extends ViewPager {

    /* renamed from: a */
    public static final String f13622a = "JazzyViewPager";

    /* renamed from: b */
    public static int f13623b = -1;

    /* renamed from: c */
    public static final int f13624c = 1;

    /* renamed from: d */
    public static final int f13625d = 2;

    /* renamed from: e */
    public static final int f13626e = 3;

    /* renamed from: f */
    public static final int f13627f = 4;

    /* renamed from: l */
    private static final float f13628l = 0.5f;

    /* renamed from: m */
    private static final float f13629m = 0.5f;

    /* renamed from: n */
    private static final float f13630n = 15.0f;

    /* renamed from: o */
    private static final boolean f13631o = (Build.VERSION.SDK_INT >= 11);

    /* renamed from: g */
    private boolean f13632g;

    /* renamed from: h */
    private boolean f13633h;

    /* renamed from: i */
    private boolean f13634i;

    /* renamed from: j */
    private EnumC3486b f13635j;

    /* renamed from: k */
    private HashMap<Integer, Object> f13636k;

    /* renamed from: p */
    private EnumC3485a f13637p;

    /* renamed from: q */
    private int f13638q;

    /* renamed from: r */
    private View f13639r;

    /* renamed from: s */
    private View f13640s;

    /* renamed from: t */
    private float f13641t;

    /* renamed from: u */
    private float f13642u;

    /* renamed from: v */
    private float f13643v;

    /* renamed from: w */
    private Matrix f13644w;

    /* renamed from: x */
    private Camera f13645x;

    /* renamed from: y */
    private float[] f13646y;

    /* renamed from: z */
    private Handler f13647z;

    /* access modifiers changed from: private */
    /* renamed from: com.gbcom.gwifi.widget.JazzyViewPager$a */
    public enum EnumC3485a {
        IDLE,
        GOING_LEFT,
        GOING_RIGHT
    }

    /* renamed from: com.gbcom.gwifi.widget.JazzyViewPager$b */
    public enum EnumC3486b {
        Standard,
        Tablet,
        CubeIn,
        CubeOut,
        FlipVertical,
        FlipHorizontal,
        Stack,
        ZoomIn,
        ZoomOut,
        RotateUp,
        RotateDown,
        Accordion
    }

    public JazzyViewPager(Context context) {
        this(context, null);
    }

    public JazzyViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f13632g = true;
        this.f13633h = false;
        this.f13634i = false;
        this.f13635j = EnumC3486b.Standard;
        this.f13636k = new LinkedHashMap();
        this.f13644w = new Matrix();
        this.f13645x = new Camera();
        this.f13646y = new float[2];
        this.f13647z = new Handler() {
            /* class com.gbcom.gwifi.widget.JazzyViewPager.HandlerC34832 */

            /* renamed from: a */
            public static final int f13649a = 5000;

            /* renamed from: c */
            private boolean f13651c = true;

            /* renamed from: d */
            private boolean f13652d = false;

            public void handleMessage(Message message) {
                super.handleMessage(message);
                switch (message.what) {
                    case 1:
                        if (!this.f13652d) {
                            if (this.f13651c) {
                                JazzyViewPager.this.setCurrentItem(JazzyViewPager.this.getCurrentItem() + 1, true);
                            }
                            m14698a(5000);
                            return;
                        }
                        return;
                    case 2:
                        this.f13651c = true;
                        return;
                    case 3:
                        this.f13651c = false;
                        return;
                    case 4:
                        this.f13652d = true;
                        return;
                    default:
                        return;
                }
            }

            /* renamed from: a */
            private void m14698a(long j) {
                removeMessages(1);
                sendEmptyMessageDelayed(1, j);
            }
        };
        setClipChildren(false);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C2425R.styleable.f8389W);
        mo28279a(EnumC3486b.valueOf(getResources().getStringArray(C2425R.array.jazzy_effects)[obtainStyledAttributes.getInt(1, 0)]));
        mo28289b(obtainStyledAttributes.getBoolean(0, false));
        mo28292c(obtainStyledAttributes.getBoolean(2, false));
        mo28272a(obtainStyledAttributes.getColor(3, -1));
        switch (this.f13635j) {
            case Stack:
            case ZoomOut:
                mo28289b(true);
                break;
        }
        obtainStyledAttributes.recycle();
        setOnTouchListener(new View.OnTouchListener() {
            /* class com.gbcom.gwifi.widget.JazzyViewPager.View$OnTouchListenerC34821 */

            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case 0:
                        JazzyViewPager.this.mo28293d();
                        return false;
                    case 1:
                        JazzyViewPager.this.mo28290c();
                        return false;
                    default:
                        return false;
                }
            }
        });
    }

    /* renamed from: a */
    public void mo28279a(EnumC3486b bVar) {
        this.f13635j = bVar;
    }

    /* renamed from: a */
    public void mo28281a(boolean z) {
        this.f13632g = z;
    }

    /* renamed from: b */
    public void mo28289b(boolean z) {
        this.f13633h = z;
    }

    /* renamed from: a */
    public boolean mo28282a() {
        return this.f13633h;
    }

    /* renamed from: c */
    public void mo28292c(boolean z) {
        this.f13634i = z;
        m14675f();
    }

    /* renamed from: a */
    public void mo28272a(int i) {
        f13623b = i;
    }

    /* renamed from: f */
    private void m14675f() {
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (!(childAt instanceof OutlineContainer)) {
                removeView(childAt);
                super.addView(m14665a(childAt), i);
            }
        }
    }

    /* renamed from: a */
    private View m14665a(View view) {
        if (!this.f13634i || (view instanceof OutlineContainer)) {
            return view;
        }
        OutlineContainer outlineContainer = new OutlineContainer(getContext());
        outlineContainer.setLayoutParams(generateDefaultLayoutParams());
        view.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        outlineContainer.addView(view);
        return outlineContainer;
    }

    public void addView(View view) {
        super.addView(m14665a(view));
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i) {
        super.addView(m14665a(view), i);
    }

    /* renamed from: a */
    public void mo28275a(View view, ViewPager.LayoutParams layoutParams) {
        super.addView(m14665a(view), layoutParams);
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i, int i2) {
        super.addView(m14665a(view), i, i2);
    }

    /* renamed from: a */
    public void mo28274a(View view, int i, ViewPager.LayoutParams layoutParams) {
        super.addView(m14665a(view), i, layoutParams);
    }

    @Override // android.support.p009v4.view.ViewPager
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.f13632g) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        return false;
    }

    /* renamed from: a */
    private void m14667a(View view, String str) {
        Log.v(f13622a, str + ": ROT (" + ViewHelper.getRotation(view) + ", " + ViewHelper.getRotationX(view) + ", " + ViewHelper.getRotationY(view) + "), TRANS (" + ViewHelper.getTranslationX(view) + ", " + ViewHelper.getTranslationY(view) + "), SCALE (" + ViewHelper.getScaleX(view) + ", " + ViewHelper.getScaleY(view) + "), ALPHA " + ViewHelper.getAlpha(view));
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo28273a(int i, float f) {
        if (this.f13637p != EnumC3485a.IDLE) {
            this.f13641t = (((float) (1.0d - Math.cos(6.283185307179586d * ((double) f)))) / 2.0f) * 30.0f;
            ViewHelper.setRotationY(this, this.f13637p == EnumC3485a.GOING_RIGHT ? this.f13641t : -this.f13641t);
            ViewHelper.setPivotX(this, ((float) getMeasuredWidth()) * 0.5f);
            ViewHelper.setPivotY(this, ((float) getMeasuredHeight()) * 0.5f);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo28277a(View view, View view2, float f) {
        if (this.f13637p != EnumC3485a.IDLE) {
            if (view != null) {
                m14668a(view, true);
                this.f13641t = 30.0f * f;
                this.f13642u = mo28271a(this.f13641t, view.getMeasuredWidth(), view.getMeasuredHeight());
                ViewHelper.setPivotX(view, (float) (view.getMeasuredWidth() / 2));
                ViewHelper.setPivotY(view, (float) (view.getMeasuredHeight() / 2));
                ViewHelper.setTranslationX(view, this.f13642u);
                ViewHelper.setRotationY(view, this.f13641t);
                m14667a(view, "Left");
            }
            if (view2 != null) {
                m14668a(view2, true);
                this.f13641t = -30.0f * (1.0f - f);
                this.f13642u = mo28271a(this.f13641t, view2.getMeasuredWidth(), view2.getMeasuredHeight());
                ViewHelper.setPivotX(view2, ((float) view2.getMeasuredWidth()) * 0.5f);
                ViewHelper.setPivotY(view2, ((float) view2.getMeasuredHeight()) * 0.5f);
                ViewHelper.setTranslationX(view2, this.f13642u);
                ViewHelper.setRotationY(view2, this.f13641t);
                m14667a(view2, "Right");
            }
        }
    }

    /* renamed from: a */
    private void m14666a(View view, View view2, float f, boolean z) {
        float f2 = 90.0f;
        if (this.f13637p != EnumC3485a.IDLE) {
            if (view != null) {
                m14668a(view, true);
                this.f13641t = (z ? 90.0f : -90.0f) * f;
                ViewHelper.setPivotX(view, (float) view.getMeasuredWidth());
                ViewHelper.setPivotY(view, ((float) view.getMeasuredHeight()) * 0.5f);
                ViewHelper.setRotationY(view, this.f13641t);
            }
            if (view2 != null) {
                m14668a(view2, true);
                if (!z) {
                    f2 = -90.0f;
                }
                this.f13641t = (-f2) * (1.0f - f);
                ViewHelper.setPivotX(view2, 0.0f);
                ViewHelper.setPivotY(view2, ((float) view2.getMeasuredHeight()) * 0.5f);
                ViewHelper.setRotationY(view2, this.f13641t);
            }
        }
    }

    /* renamed from: c */
    private void m14672c(View view, View view2, float f) {
        if (this.f13637p != EnumC3485a.IDLE) {
            if (view != null) {
                m14668a(view, true);
                ViewHelper.setPivotX(view, (float) view.getMeasuredWidth());
                ViewHelper.setPivotY(view, 0.0f);
                ViewHelper.setScaleX(view, 1.0f - f);
            }
            if (view2 != null) {
                m14668a(view2, true);
                ViewHelper.setPivotX(view2, 0.0f);
                ViewHelper.setPivotY(view2, 0.0f);
                ViewHelper.setScaleX(view2, f);
            }
        }
    }

    /* renamed from: b */
    private void m14671b(View view, View view2, float f, boolean z) {
        if (this.f13637p != EnumC3485a.IDLE) {
            if (view != null) {
                m14668a(view, true);
                this.f13643v = z ? ((1.0f - f) * 0.5f) + 0.5f : 1.5f - ((1.0f - f) * 0.5f);
                ViewHelper.setPivotX(view, ((float) view.getMeasuredWidth()) * 0.5f);
                ViewHelper.setPivotY(view, ((float) view.getMeasuredHeight()) * 0.5f);
                ViewHelper.setScaleX(view, this.f13643v);
                ViewHelper.setScaleY(view, this.f13643v);
            }
            if (view2 != null) {
                m14668a(view2, true);
                this.f13643v = z ? (0.5f * f) + 0.5f : 1.5f - (0.5f * f);
                ViewHelper.setPivotX(view2, ((float) view2.getMeasuredWidth()) * 0.5f);
                ViewHelper.setPivotY(view2, ((float) view2.getMeasuredHeight()) * 0.5f);
                ViewHelper.setScaleX(view2, this.f13643v);
                ViewHelper.setScaleY(view2, this.f13643v);
            }
        }
    }

    /* renamed from: c */
    private void m14674c(View view, View view2, float f, boolean z) {
        if (this.f13637p != EnumC3485a.IDLE) {
            if (view != null) {
                m14668a(view, true);
                this.f13641t = ((float) (z ? 1 : -1)) * f13630n * f;
                this.f13642u = ((float) (z ? -1 : 1)) * ((float) (((double) getMeasuredHeight()) - (((double) getMeasuredHeight()) * Math.cos((((double) this.f13641t) * 3.141592653589793d) / 180.0d))));
                ViewHelper.setPivotX(view, ((float) view.getMeasuredWidth()) * 0.5f);
                ViewHelper.setPivotY(view, z ? 0.0f : (float) view.getMeasuredHeight());
                ViewHelper.setTranslationY(view, this.f13642u);
                ViewHelper.setRotation(view, this.f13641t);
            }
            if (view2 != null) {
                m14668a(view2, true);
                this.f13641t = ((float) (z ? 1 : -1)) * (-15.0f + (f13630n * f));
                this.f13642u = ((float) (z ? -1 : 1)) * ((float) (((double) getMeasuredHeight()) - (((double) getMeasuredHeight()) * Math.cos((((double) this.f13641t) * 3.141592653589793d) / 180.0d))));
                ViewHelper.setPivotX(view2, ((float) view2.getMeasuredWidth()) * 0.5f);
                ViewHelper.setPivotY(view2, z ? 0.0f : (float) view2.getMeasuredHeight());
                ViewHelper.setTranslationY(view2, this.f13642u);
                ViewHelper.setRotation(view2, this.f13641t);
            }
        }
    }

    /* renamed from: b */
    private void m14670b(View view, View view2, float f, int i) {
        if (this.f13637p != EnumC3485a.IDLE) {
            if (view != null) {
                m14668a(view, true);
                this.f13641t = 180.0f * f;
                if (this.f13641t > 90.0f) {
                    view.setVisibility(4);
                } else {
                    if (view.getVisibility() == 4) {
                        view.setVisibility(0);
                    }
                    this.f13642u = (float) i;
                    ViewHelper.setPivotX(view, ((float) view.getMeasuredWidth()) * 0.5f);
                    ViewHelper.setPivotY(view, ((float) view.getMeasuredHeight()) * 0.5f);
                    ViewHelper.setTranslationX(view, this.f13642u);
                    ViewHelper.setRotationY(view, this.f13641t);
                }
            }
            if (view2 != null) {
                m14668a(view2, true);
                this.f13641t = -180.0f * (1.0f - f);
                if (this.f13641t < -90.0f) {
                    view2.setVisibility(4);
                    return;
                }
                if (view2.getVisibility() == 4) {
                    view2.setVisibility(0);
                }
                this.f13642u = (float) (((-getWidth()) - getPageMargin()) + i);
                ViewHelper.setPivotX(view2, ((float) view2.getMeasuredWidth()) * 0.5f);
                ViewHelper.setPivotY(view2, ((float) view2.getMeasuredHeight()) * 0.5f);
                ViewHelper.setTranslationX(view2, this.f13642u);
                ViewHelper.setRotationY(view2, this.f13641t);
            }
        }
    }

    /* renamed from: c */
    private void m14673c(View view, View view2, float f, int i) {
        if (this.f13637p != EnumC3485a.IDLE) {
            if (view != null) {
                m14668a(view, true);
                this.f13641t = 180.0f * f;
                if (this.f13641t > 90.0f) {
                    view.setVisibility(4);
                } else {
                    if (view.getVisibility() == 4) {
                        view.setVisibility(0);
                    }
                    this.f13642u = (float) i;
                    ViewHelper.setPivotX(view, ((float) view.getMeasuredWidth()) * 0.5f);
                    ViewHelper.setPivotY(view, ((float) view.getMeasuredHeight()) * 0.5f);
                    ViewHelper.setTranslationX(view, this.f13642u);
                    ViewHelper.setRotationX(view, this.f13641t);
                }
            }
            if (view2 != null) {
                m14668a(view2, true);
                this.f13641t = -180.0f * (1.0f - f);
                if (this.f13641t < -90.0f) {
                    view2.setVisibility(4);
                    return;
                }
                if (view2.getVisibility() == 4) {
                    view2.setVisibility(0);
                }
                this.f13642u = (float) (((-getWidth()) - getPageMargin()) + i);
                ViewHelper.setPivotX(view2, ((float) view2.getMeasuredWidth()) * 0.5f);
                ViewHelper.setPivotY(view2, ((float) view2.getMeasuredHeight()) * 0.5f);
                ViewHelper.setTranslationX(view2, this.f13642u);
                ViewHelper.setRotationX(view2, this.f13641t);
            }
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo28278a(View view, View view2, float f, int i) {
        if (this.f13637p != EnumC3485a.IDLE) {
            if (view2 != null) {
                m14668a(view2, true);
                this.f13643v = (0.5f * f) + 0.5f;
                this.f13642u = (float) (((-getWidth()) - getPageMargin()) + i);
                ViewHelper.setScaleX(view2, this.f13643v);
                ViewHelper.setScaleY(view2, this.f13643v);
                ViewHelper.setTranslationX(view2, this.f13642u);
            }
            if (view != null) {
                view.bringToFront();
            }
        }
    }

    /* renamed from: a */
    private void m14668a(View view, boolean z) {
        if (!f13631o) {
        }
    }

    /* renamed from: g */
    private void m14676g() {
        if (!f13631o) {
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public float mo28271a(float f, int i, int i2) {
        this.f13644w.reset();
        this.f13645x.save();
        this.f13645x.rotateY(Math.abs(f));
        this.f13645x.getMatrix(this.f13644w);
        this.f13645x.restore();
        this.f13644w.preTranslate(((float) (-i)) * 0.5f, ((float) (-i2)) * 0.5f);
        this.f13644w.postTranslate(((float) i) * 0.5f, ((float) i2) * 0.5f);
        this.f13646y[0] = (float) i;
        this.f13646y[1] = (float) i2;
        this.f13644w.mapPoints(this.f13646y);
        return (f > 0.0f ? 1.0f : -1.0f) * (((float) i) - this.f13646y[0]);
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void mo28288b(View view, View view2, float f) {
        if (view != null) {
            ViewHelper.setAlpha(view, 1.0f - f);
        }
        if (view2 != null) {
            ViewHelper.setAlpha(view2, f);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo28276a(View view, View view2) {
        if (view instanceof OutlineContainer) {
            if (this.f13637p != EnumC3485a.IDLE) {
                if (view != null) {
                    m14668a(view, true);
                    ((OutlineContainer) view).mo28342a(1.0f);
                }
                if (view2 != null) {
                    m14668a(view2, true);
                    ((OutlineContainer) view2).mo28342a(1.0f);
                    return;
                }
                return;
            }
            if (view != null) {
                ((OutlineContainer) view).start();
            }
            if (view2 != null) {
                ((OutlineContainer) view2).start();
            }
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // android.support.p009v4.view.ViewPager
    public void onPageScrolled(int i, float f, int i2) {
        boolean z;
        float f2;
        if (this.f13637p == EnumC3485a.IDLE && f > 0.0f) {
            this.f13638q = getCurrentItem();
            this.f13637p = i == this.f13638q ? EnumC3485a.GOING_RIGHT : EnumC3485a.GOING_LEFT;
        }
        if (i == this.f13638q) {
            z = true;
        } else {
            z = false;
        }
        if (this.f13637p == EnumC3485a.GOING_RIGHT && !z) {
            this.f13637p = EnumC3485a.GOING_LEFT;
        } else if (this.f13637p == EnumC3485a.GOING_LEFT && z) {
            this.f13637p = EnumC3485a.GOING_RIGHT;
        }
        if (m14669a(f)) {
            f2 = 0.0f;
        } else {
            f2 = f;
        }
        this.f13639r = mo28286b(i);
        this.f13640s = mo28286b(i + 1);
        if (this.f13633h) {
            mo28288b(this.f13639r, this.f13640s, f2);
        }
        if (this.f13634i) {
            mo28276a(this.f13639r, this.f13640s);
        }
        switch (this.f13635j) {
            case Stack:
                mo28278a(this.f13639r, this.f13640s, f2, i2);
                break;
            case ZoomOut:
                m14671b(this.f13639r, this.f13640s, f2, false);
                break;
            case Tablet:
                mo28277a(this.f13639r, this.f13640s, f2);
                break;
            case CubeIn:
                m14666a(this.f13639r, this.f13640s, f2, true);
                break;
            case CubeOut:
                m14666a(this.f13639r, this.f13640s, f2, false);
                break;
            case FlipVertical:
                m14673c(this.f13639r, this.f13640s, f, i2);
                break;
            case FlipHorizontal:
                m14670b(this.f13639r, this.f13640s, f2, i2);
                mo28278a(this.f13639r, this.f13640s, f2, i2);
                break;
            case ZoomIn:
                m14671b(this.f13639r, this.f13640s, f2, true);
                break;
            case RotateUp:
                m14674c(this.f13639r, this.f13640s, f2, true);
                break;
            case RotateDown:
                m14674c(this.f13639r, this.f13640s, f2, false);
                break;
            case Accordion:
                m14672c(this.f13639r, this.f13640s, f2);
                break;
        }
        super.onPageScrolled(i, f, i2);
        if (f2 == 0.0f) {
            m14676g();
            this.f13637p = EnumC3485a.IDLE;
        }
    }

    /* renamed from: a */
    private boolean m14669a(float f) {
        return ((double) Math.abs(f)) < 1.0E-4d;
    }

    /* renamed from: a */
    public void mo28280a(Object obj, int i) {
        this.f13636k.put(Integer.valueOf(i), obj);
    }

    /* renamed from: b */
    public View mo28286b(int i) {
        Object obj = this.f13636k.get(Integer.valueOf(i));
        if (obj == null) {
            return null;
        }
        PagerAdapter adapter = getAdapter();
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            View childAt = getChildAt(i2);
            if (adapter.isViewFromObject(childAt, obj)) {
                return childAt;
            }
        }
        return null;
    }

    /* renamed from: c */
    public void mo28291c(int i) {
        if (this.f13636k.size() <= i || this.f13636k.remove(Integer.valueOf(i)) == null) {
        }
    }

    /* renamed from: b */
    public void mo28287b() {
        this.f13647z.sendEmptyMessage(1);
    }

    /* renamed from: c */
    public void mo28290c() {
        this.f13647z.sendEmptyMessage(2);
    }

    /* renamed from: d */
    public void mo28293d() {
        this.f13647z.sendEmptyMessage(3);
    }

    /* renamed from: e */
    public void mo28294e() {
        this.f13647z.sendEmptyMessage(4);
    }
}
