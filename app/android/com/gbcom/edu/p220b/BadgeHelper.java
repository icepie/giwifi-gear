package com.gbcom.edu.p220b;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;

/* renamed from: com.gbcom.edu.b.a */
public class BadgeHelper extends View {

    /* renamed from: a */
    private static final String f8038a = "BadgeHelper";

    /* renamed from: b */
    private float f8039b;

    /* renamed from: c */
    private Paint f8040c;

    /* renamed from: d */
    private Paint f8041d;

    /* renamed from: e */
    private String f8042e = "0";

    /* renamed from: f */
    private int f8043f = 0;

    /* renamed from: g */
    private boolean f8044g;

    /* renamed from: h */
    private final RectF f8045h = new RectF();

    /* renamed from: i */
    private int f8046i = -2936293;

    /* renamed from: j */
    private int f8047j = -1;

    /* renamed from: k */
    private float f8048k;

    /* renamed from: l */
    private int f8049l;

    /* renamed from: m */
    private int f8050m;

    /* renamed from: n */
    private boolean f8051n;

    /* renamed from: o */
    private boolean f8052o;

    /* renamed from: p */
    private boolean f8053p;

    /* renamed from: q */
    private int f8054q;

    /* renamed from: r */
    private int f8055r;

    /* renamed from: s */
    private int f8056s;

    /* renamed from: t */
    private int f8057t;

    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: com.gbcom.edu.b.a$a */
    /* compiled from: BadgeHelper */
    public @interface AbstractC2421a {

        /* renamed from: a */
        public static final int f8058a = 0;

        /* renamed from: b */
        public static final int f8059b = 1;
    }

    public BadgeHelper(Context context) {
        super(context);
    }

    /* renamed from: a */
    private void m10087a(int i, boolean z) {
        this.f8043f = i;
        this.f8044g = z;
        this.f8039b = getResources().getDisplayMetrics().density;
        switch (i) {
            case 0:
                this.f8041d = new Paint();
                this.f8041d.setStyle(Paint.Style.FILL);
                this.f8041d.setFlags(1);
                this.f8041d.setColor(this.f8046i);
                int round = Math.round(this.f8039b * 7.0f);
                this.f8050m = round;
                this.f8049l = round;
                return;
            case 1:
                this.f8041d = new Paint();
                this.f8041d.setStyle(Paint.Style.FILL);
                this.f8041d.setFlags(1);
                this.f8041d.setColor(this.f8046i);
                this.f8040c = new Paint();
                this.f8040c.setStyle(Paint.Style.FILL);
                this.f8040c.setFlags(1);
                this.f8040c.setColor(this.f8047j);
                if (this.f8048k == 0.0f) {
                    this.f8040c.setTextSize(this.f8039b * 10.0f);
                } else {
                    this.f8040c.setTextSize(this.f8048k);
                }
                int round2 = Math.round(m10086a("99", this.f8040c) * 1.4f);
                this.f8050m = round2;
                this.f8049l = round2;
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    public BadgeHelper mo23957a(int i, int i2, int i3, int i4) {
        this.f8054q = i;
        this.f8055r = i2;
        this.f8056s = i3;
        this.f8057t = i4;
        return this;
    }

    /* renamed from: a */
    public BadgeHelper mo23954a() {
        this.f8053p = true;
        return this;
    }

    /* renamed from: a */
    public BadgeHelper mo23955a(int i) {
        this.f8043f = i;
        return this;
    }

    /* renamed from: b */
    public BadgeHelper mo23962b(int i) {
        Resources resources;
        Context context = getContext();
        if (context == null) {
            resources = Resources.getSystem();
        } else {
            resources = context.getResources();
        }
        this.f8048k = TypedValue.applyDimension(2, (float) i, resources.getDisplayMetrics());
        return this;
    }

    /* renamed from: c */
    public BadgeHelper mo23963c(int i) {
        this.f8047j = i;
        return this;
    }

    /* renamed from: a */
    public BadgeHelper mo23958a(boolean z) {
        return mo23959a(z, false);
    }

    /* renamed from: a */
    public BadgeHelper mo23959a(boolean z, boolean z2) {
        this.f8044g = z;
        this.f8052o = z2;
        if (!z && z2) {
            Log.w(f8038a, "警告:只有重叠模式isOverlap=true 设置mIgnoreTargetPadding才有意义");
        }
        return this;
    }

    /* renamed from: d */
    public BadgeHelper mo23964d(int i) {
        this.f8046i = i;
        return this;
    }

    /* renamed from: a */
    public BadgeHelper mo23956a(int i, int i2) {
        this.f8049l = i;
        this.f8050m = i2;
        return this;
    }

    /* renamed from: e */
    public void mo23965e(int i) {
        this.f8042e = String.valueOf(i);
        if (this.f8051n) {
            invalidate();
        }
    }

    /* renamed from: a */
    public void mo23960a(TabLayout tabLayout, int i) {
        View view;
        View view2;
        View view3 = null;
        TabLayout.Tab tabAt = tabLayout.getTabAt(i);
        try {
            Field declaredField = TabLayout.Tab.class.getDeclaredField("mView");
            declaredField.setAccessible(true);
            view = (View) declaredField.get(tabAt);
            view3 = view;
        } catch (Exception e) {
            e.printStackTrace();
            view = null;
        }
        if (view != null) {
            try {
                Field declaredField2 = view.getClass().getDeclaredField("mTextView");
                declaredField2.setAccessible(true);
                view2 = (View) declaredField2.get(view);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } else {
            view2 = view3;
        }
        view3 = view2;
        if (view3 != null) {
            mo23961a(view3);
        }
    }

    /* renamed from: a */
    public void mo23961a(View view) {
        boolean z;
        m10087a(this.f8043f, this.f8044g);
        if (getParent() != null) {
            ((ViewGroup) getParent()).removeView(this);
        }
        if (view == null) {
            return;
        }
        if (view.getParent() instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            int indexOfChild = viewGroup.indexOfChild(view);
            viewGroup.removeView(view);
            if (this.f8044g) {
                FrameLayout frameLayout = new FrameLayout(getContext());
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                frameLayout.setLayoutParams(layoutParams);
                view.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
                viewGroup.addView(frameLayout, indexOfChild, layoutParams);
                frameLayout.addView(view);
                frameLayout.addView(this);
                FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) getLayoutParams();
                if (this.f8053p) {
                    layoutParams2.gravity = 16;
                } else {
                    layoutParams2.gravity = 8388661;
                }
                if (this.f8052o) {
                    layoutParams2.rightMargin = view.getPaddingRight() - this.f8049l;
                    layoutParams2.topMargin = view.getPaddingTop() - (this.f8050m / 2);
                }
                setLayoutParams(layoutParams2);
            } else {
                LinearLayout linearLayout = new LinearLayout(getContext());
                linearLayout.setOrientation(0);
                ViewGroup.LayoutParams layoutParams3 = view.getLayoutParams();
                linearLayout.setLayoutParams(layoutParams3);
                view.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
                viewGroup.addView(linearLayout, indexOfChild, layoutParams3);
                linearLayout.addView(view);
                linearLayout.addView(this);
                if (this.f8053p) {
                    linearLayout.setGravity(16);
                }
            }
            if (this.f8054q > 0 || this.f8055r > 0 || this.f8056s > 0 || this.f8057t > 0) {
                z = true;
            } else {
                z = false;
            }
            if (z && (getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
                marginLayoutParams.setMargins(this.f8054q, this.f8055r, this.f8056s, this.f8057t);
                setLayoutParams(marginLayoutParams);
            }
            this.f8051n = true;
        } else if (view.getParent() == null) {
            throw new IllegalStateException("目标View不能没有父布局!");
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        if (this.f8049l <= 0 || this.f8050m <= 0) {
            throw new IllegalStateException("如果你自定义了小红点的宽高,就不能设置其宽高小于0 ,否则请不要设置!");
        }
        setMeasuredDimension(this.f8049l, this.f8050m);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.f8045h.left = 0.0f;
        this.f8045h.top = 0.0f;
        this.f8045h.right = (float) getWidth();
        this.f8045h.bottom = (float) getHeight();
        canvas.drawRoundRect(this.f8045h, (float) (getWidth() / 2), (float) (getWidth() / 2), this.f8041d);
        if (this.f8043f == 1) {
            canvas.drawText(this.f8042e, ((float) (getWidth() / 2)) - (m10086a(this.f8042e, this.f8040c) / 2.0f), (m10088b(this.f8042e, this.f8040c) / 2.0f) + ((float) (getHeight() / 2)), this.f8040c);
        }
    }

    /* renamed from: a */
    private float m10086a(String str, Paint paint) {
        return paint.measureText(str, 0, str.length());
    }

    /* renamed from: b */
    private float m10088b(String str, Paint paint) {
        Rect rect = new Rect();
        paint.getTextBounds(str, 0, str.length(), rect);
        return (float) rect.height();
    }
}
