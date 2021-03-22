package com.gbcom.gwifi.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.gbcom.gwifi.C2425R;
import org.apache.xpath.XPath;

public class PullToRefreshView extends LinearLayout {

    /* renamed from: a */
    private static final String f13741a = "PullToRefreshView";

    /* renamed from: b */
    private static final int f13742b = 2;

    /* renamed from: c */
    private static final int f13743c = 3;

    /* renamed from: d */
    private static final int f13744d = 4;

    /* renamed from: e */
    private static final int f13745e = 0;

    /* renamed from: f */
    private static final int f13746f = 1;

    /* renamed from: A */
    private int f13747A;

    /* renamed from: B */
    private RotateAnimation f13748B;

    /* renamed from: C */
    private RotateAnimation f13749C;

    /* renamed from: D */
    private boolean f13750D;

    /* renamed from: E */
    private boolean f13751E;

    /* renamed from: F */
    private boolean f13752F;

    /* renamed from: G */
    private boolean f13753G;

    /* renamed from: H */
    private AnimationDrawable f13754H;

    /* renamed from: I */
    private AnimationDrawable f13755I;

    /* renamed from: J */
    private AbstractC3493a f13756J;

    /* renamed from: K */
    private AbstractC3494b f13757K;

    /* renamed from: g */
    private int f13758g;

    /* renamed from: h */
    private int f13759h;

    /* renamed from: i */
    private View f13760i;

    /* renamed from: j */
    private View f13761j;

    /* renamed from: k */
    private AdapterView<?> f13762k;

    /* renamed from: l */
    private ScrollView f13763l;

    /* renamed from: m */
    private int f13764m;

    /* renamed from: n */
    private int f13765n;

    /* renamed from: o */
    private ImageView f13766o;

    /* renamed from: p */
    private ImageView f13767p;

    /* renamed from: q */
    private TextView f13768q;

    /* renamed from: r */
    private TextView f13769r;

    /* renamed from: s */
    private RelativeLayout f13770s;

    /* renamed from: t */
    private TextView f13771t;

    /* renamed from: u */
    private CircularProgressBar f13772u;

    /* renamed from: v */
    private CircularProgressBar f13773v;

    /* renamed from: w */
    private LayoutInflater f13774w;

    /* renamed from: x */
    private int f13775x;

    /* renamed from: y */
    private int f13776y;

    /* renamed from: z */
    private int f13777z = 0;

    /* renamed from: com.gbcom.gwifi.widget.PullToRefreshView$a */
    public interface AbstractC3493a {
        void onFooterRefresh(PullToRefreshView pullToRefreshView);
    }

    /* renamed from: com.gbcom.gwifi.widget.PullToRefreshView$b */
    public interface AbstractC3494b {
        void onHeaderRefresh(PullToRefreshView pullToRefreshView);
    }

    public PullToRefreshView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m14792a(context.obtainStyledAttributes(attributeSet, C2425R.styleable.f8417al));
        m14799e();
    }

    public PullToRefreshView(Context context) {
        super(context);
        m14799e();
    }

    /* renamed from: a */
    private void m14792a(TypedArray typedArray) {
        this.f13777z = typedArray.getInt(0, this.f13777z);
        typedArray.recycle();
    }

    /* renamed from: e */
    private void m14799e() {
        this.f13748B = new RotateAnimation(0.0f, -180.0f, 1, 0.5f, 1, 0.5f);
        this.f13748B.setInterpolator(new LinearInterpolator());
        this.f13748B.setDuration(250);
        this.f13748B.setFillAfter(true);
        this.f13749C = new RotateAnimation(-180.0f, 0.0f, 1, 0.5f, 1, 0.5f);
        this.f13749C.setInterpolator(new LinearInterpolator());
        this.f13749C.setDuration(250);
        this.f13749C.setFillAfter(true);
        this.f13774w = LayoutInflater.from(getContext());
        m14800f();
    }

    /* renamed from: f */
    private void m14800f() {
        this.f13760i = this.f13774w.inflate(C2425R.layout.common_refresh_header, (ViewGroup) this, false);
        this.f13766o = (ImageView) this.f13760i.findViewById(C2425R.C2427id.pull_to_refresh_image);
        this.f13768q = (TextView) this.f13760i.findViewById(C2425R.C2427id.pull_to_refresh_text);
        this.f13771t = (TextView) this.f13760i.findViewById(C2425R.C2427id.pull_to_refresh_updated_at);
        this.f13772u = (CircularProgressBar) this.f13760i.findViewById(C2425R.C2427id.circular_progress);
        m14793a(this.f13760i);
        this.f13764m = this.f13760i.getMeasuredHeight();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, this.f13764m);
        layoutParams.topMargin = -this.f13764m;
        addView(this.f13760i, layoutParams);
    }

    /* renamed from: g */
    private void m14802g() {
        this.f13761j = this.f13774w.inflate(C2425R.layout.common_refresh_footer, (ViewGroup) this, false);
        this.f13770s = (RelativeLayout) this.f13774w.inflate(C2425R.layout.common_failed_footer, (ViewGroup) this, false);
        this.f13767p = (ImageView) this.f13761j.findViewById(C2425R.C2427id.pull_to_load_image);
        this.f13769r = (TextView) this.f13761j.findViewById(C2425R.C2427id.pull_to_load_text);
        this.f13773v = (CircularProgressBar) this.f13761j.findViewById(C2425R.C2427id.smooth_progress);
        m14793a(this.f13761j);
        this.f13765n = this.f13761j.getMeasuredHeight();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, this.f13765n);
        addView(this.f13770s, layoutParams);
        addView(this.f13761j, layoutParams);
    }

    /* renamed from: a */
    public void mo28395a(Drawable drawable) {
        if (this.f13760i != null) {
            this.f13760i.setBackground(drawable);
        }
    }

    /* renamed from: a */
    public void mo28399a(boolean z, int i) {
        this.f13750D = !z;
        if (z && i != 0 && this.f13766o != null) {
            this.f13766o.setImageDrawable(null);
            this.f13766o.setImageResource(i);
            this.f13751E = true;
            this.f13754H = (AnimationDrawable) this.f13766o.getDrawable();
            this.f13754H.setOneShot(false);
        }
    }

    /* renamed from: b */
    public void mo28402b(boolean z, int i) {
        this.f13752F = !z;
        if (z && i != 0 && this.f13767p != null) {
            this.f13767p.setImageDrawable(null);
            this.f13767p.setImageResource(i);
            this.f13753G = true;
            this.f13755I = (AnimationDrawable) this.f13767p.getDrawable();
            this.f13755I.setOneShot(false);
        }
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        m14802g();
        m14803h();
    }

    /* renamed from: h */
    private void m14803h() {
        int childCount = getChildCount();
        if (childCount < 3) {
            throw new IllegalArgumentException("this layout must contain 3 child views,and AdapterView or ScrollView must in the second position!");
        }
        for (int i = 0; i < childCount - 1; i++) {
            View childAt = getChildAt(i);
            if (childAt instanceof AdapterView) {
                this.f13762k = (AdapterView) childAt;
            }
            if (childAt instanceof ScrollView) {
                this.f13763l = (ScrollView) childAt;
            }
        }
        if (this.f13762k == null && this.f13763l == null) {
            throw new IllegalArgumentException("must contain a AdapterView or ScrollView in this layout!");
        }
    }

    /* renamed from: a */
    private void m14793a(View view) {
        int makeMeasureSpec;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new ViewGroup.LayoutParams(-1, -2);
        }
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(0, 0, layoutParams.width);
        int i = layoutParams.height;
        if (i > 0) {
            makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i, 1073741824);
        } else {
            makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        }
        view.measure(childMeasureSpec, makeMeasureSpec);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int rawY = (int) motionEvent.getRawY();
        int rawX = (int) motionEvent.getRawX();
        switch (motionEvent.getAction()) {
            case 0:
                this.f13758g = rawY;
                this.f13759h = rawX;
                return false;
            case 1:
            default:
                return false;
            case 2:
                int i = rawY - this.f13758g;
                int i2 = rawY - this.f13759h;
                if (i < 10 && i > 0) {
                    return false;
                }
                if ((i <= -10 || i >= 0) && m14794a(i)) {
                    return true;
                }
                return false;
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int rawY = (int) motionEvent.getRawY();
        switch (motionEvent.getAction()) {
            case 1:
            case 3:
                int j = m14805j();
                if (this.f13747A != 1) {
                    if (this.f13747A == 0) {
                        if (Math.abs(j) < this.f13764m + this.f13765n) {
                            m14801f(-this.f13764m);
                            break;
                        } else {
                            m14804i();
                            break;
                        }
                    }
                } else if (j < 0) {
                    m14801f(-this.f13764m);
                    break;
                } else {
                    mo28394a();
                    break;
                }
                break;
            case 2:
                int i = rawY - this.f13758g;
                if (this.f13747A == 1) {
                    Log.i(f13741a, " pull down!parent view move!");
                    if (this.f13777z == 0 || this.f13777z == 1) {
                        m14795b(i);
                    }
                } else if (this.f13747A == 0) {
                    Log.i(f13741a, "pull up!parent view move!");
                    if (this.f13777z == 0 || this.f13777z == 2) {
                        m14796c(i);
                    }
                }
                this.f13758g = rawY;
                break;
        }
        return super.onTouchEvent(motionEvent);
    }

    /* renamed from: a */
    private boolean m14794a(int i) {
        if (this.f13775x == 4 || this.f13776y == 4) {
            return false;
        }
        if (this.f13762k != null) {
            if (i > 0) {
                View childAt = this.f13762k.getChildAt(0);
                if (childAt == null) {
                    return false;
                }
                if (this.f13762k.getFirstVisiblePosition() == 0 && childAt.getTop() == 0) {
                    this.f13747A = 1;
                    return true;
                }
                int top = childAt.getTop();
                int paddingTop = this.f13762k.getPaddingTop();
                if (this.f13762k.getFirstVisiblePosition() == 0 && Math.abs(top - paddingTop) <= 8) {
                    this.f13747A = 1;
                    return true;
                }
            } else if (i < 0) {
                View childAt2 = this.f13762k.getChildAt(this.f13762k.getChildCount() - 1);
                if (childAt2 == null) {
                    return false;
                }
                if (childAt2.getBottom() <= getHeight() && this.f13762k.getLastVisiblePosition() == this.f13762k.getCount() - 1) {
                    this.f13747A = 0;
                    return true;
                }
            }
        }
        if (this.f13763l == null) {
            return false;
        }
        View childAt3 = this.f13763l.getChildAt(0);
        if (i > 0 && this.f13763l.getScrollY() == 0) {
            this.f13747A = 1;
            return true;
        } else if (i >= 0 || childAt3.getMeasuredHeight() > getHeight() + this.f13763l.getScrollY()) {
            return false;
        } else {
            this.f13747A = 0;
            return true;
        }
    }

    /* renamed from: b */
    private void m14795b(int i) {
        if (!this.f13750D) {
            int d = m14797d(i);
            if (d >= 0 && this.f13775x != 3) {
                this.f13768q.setText(C2425R.C2429string.pull_to_refresh_release_label);
                this.f13771t.setVisibility(0);
                if (!this.f13751E) {
                    this.f13766o.clearAnimation();
                    this.f13766o.startAnimation(this.f13748B);
                } else if (this.f13754H != null) {
                    if (this.f13754H.isRunning()) {
                        this.f13754H.stop();
                    }
                    this.f13754H.start();
                }
                this.f13775x = 3;
            } else if (d < 0 && d > (-this.f13764m) + 20) {
                if (!this.f13751E) {
                    this.f13766o.clearAnimation();
                    this.f13766o.startAnimation(this.f13748B);
                } else if (this.f13754H != null) {
                    if (this.f13754H.isRunning()) {
                        this.f13754H.stop();
                    }
                    this.f13754H.start();
                }
                this.f13768q.setText(C2425R.C2429string.pull_to_refresh_pull_label);
                this.f13775x = 2;
            }
        }
    }

    /* renamed from: c */
    private void m14796c(int i) {
        if (!this.f13752F) {
            this.f13770s.setVisibility(8);
            int e = m14798e(i);
            Log.d("newTopMargin", e + "");
            if (Math.abs(e) >= this.f13764m + this.f13765n && this.f13776y != 3) {
                this.f13769r.setText(C2425R.C2429string.pull_to_refresh_footer_release_label);
                if (!this.f13753G) {
                    this.f13767p.clearAnimation();
                    this.f13767p.startAnimation(this.f13748B);
                } else if (this.f13755I != null) {
                    if (this.f13755I.isRunning()) {
                        this.f13755I.stop();
                    }
                    this.f13755I.start();
                }
                this.f13776y = 3;
            } else if (Math.abs(e) < this.f13764m + this.f13765n + 20) {
                if (!this.f13753G) {
                    this.f13767p.clearAnimation();
                    this.f13767p.startAnimation(this.f13748B);
                } else if (this.f13755I != null) {
                    if (this.f13755I.isRunning()) {
                        this.f13755I.stop();
                    }
                    this.f13755I.start();
                }
                this.f13769r.setText(C2425R.C2429string.pull_to_refresh_footer_pull_label);
                this.f13776y = 2;
            }
        }
    }

    /* renamed from: d */
    private int m14797d(int i) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.f13760i.getLayoutParams();
        float f = ((float) layoutParams.topMargin) + (((float) i) * 0.45f);
        if (i > 0 && this.f13747A == 0 && Math.abs(layoutParams.topMargin) <= this.f13764m) {
            return layoutParams.topMargin;
        }
        if (i < 0 && this.f13747A == 1 && Math.abs(layoutParams.topMargin) >= this.f13764m) {
            return layoutParams.topMargin;
        }
        layoutParams.topMargin = (int) f;
        this.f13760i.setLayoutParams(layoutParams);
        invalidate();
        return layoutParams.topMargin;
    }

    /* renamed from: e */
    private int m14798e(int i) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.f13760i.getLayoutParams();
        float f = ((float) layoutParams.topMargin) + (((float) i) * 1.5f);
        if (i > 0 && this.f13747A == 0 && Math.abs(layoutParams.topMargin) <= this.f13764m) {
            return layoutParams.topMargin;
        }
        if (i < 0 && this.f13747A == 1 && Math.abs(layoutParams.topMargin) >= this.f13764m) {
            return layoutParams.topMargin;
        }
        if (((double) Math.abs(f)) >= ((double) this.f13764m) * 2.5d) {
            if (f < 0.0f) {
                f = (float) (XPath.MATCH_SCORE_QNAME - (((double) this.f13764m) * 2.5d));
            } else {
                f = (float) (((double) this.f13764m) * 2.5d);
            }
        }
        layoutParams.topMargin = (int) f;
        this.f13760i.setLayoutParams(layoutParams);
        invalidate();
        return layoutParams.topMargin;
    }

    /* renamed from: a */
    public void mo28394a() {
        if (!this.f13750D) {
            this.f13775x = 4;
            m14801f(0);
            this.f13766o.setVisibility(8);
            if (!this.f13751E) {
                this.f13766o.clearAnimation();
                this.f13766o.setImageDrawable(null);
            } else if (this.f13754H != null && this.f13754H.isRunning()) {
                this.f13754H.stop();
            }
            this.f13772u.setVisibility(0);
            this.f13768q.setText(C2425R.C2429string.pull_to_refresh_refreshing_label);
            if (this.f13757K != null) {
                this.f13757K.onHeaderRefresh(this);
            }
        }
    }

    /* renamed from: i */
    private void m14804i() {
        if (!this.f13752F) {
            this.f13776y = 4;
            m14801f(-(this.f13764m + this.f13765n));
            this.f13767p.setVisibility(8);
            if (!this.f13753G) {
                this.f13767p.clearAnimation();
                this.f13767p.setImageDrawable(null);
            } else if (this.f13755I != null && this.f13755I.isRunning()) {
                this.f13755I.stop();
            }
            this.f13773v.setVisibility(0);
            this.f13769r.setText("");
            if (this.f13756J != null) {
                this.f13756J.onFooterRefresh(this);
            }
        }
    }

    /* renamed from: f */
    private void m14801f(int i) {
        if (!this.f13750D) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.f13760i.getLayoutParams();
            layoutParams.topMargin = i;
            this.f13760i.setLayoutParams(layoutParams);
            invalidate();
        }
    }

    /* renamed from: b */
    public void mo28400b() {
        m14801f(-this.f13764m);
        this.f13766o.setVisibility(0);
        if (!this.f13751E) {
            this.f13766o.setImageResource(C2425R.C2426drawable.ic_pulltorefresh_arrow);
        }
        this.f13768q.setText(C2425R.C2429string.pull_to_refresh_pull_label);
        this.f13772u.setVisibility(8);
        this.f13775x = 2;
        if (this.f13763l != null) {
            this.f13763l.smoothScrollTo(0, 0);
        }
    }

    /* renamed from: a */
    public void mo28398a(CharSequence charSequence) {
        mo28401b(charSequence);
        mo28400b();
    }

    /* renamed from: c */
    public void mo28403c() {
        m14801f(-this.f13764m);
        this.f13767p.setVisibility(0);
        if (!this.f13753G) {
            this.f13767p.setImageResource(C2425R.C2426drawable.ic_pulltorefresh_arrow_up);
        }
        this.f13769r.setText(C2425R.C2429string.pull_to_refresh_footer_pull_label);
        this.f13773v.setVisibility(8);
        this.f13776y = 2;
    }

    /* renamed from: d */
    public void mo28404d() {
        this.f13767p.setVisibility(0);
        this.f13770s.setVisibility(0);
        if (!this.f13753G) {
            this.f13767p.setImageResource(C2425R.C2426drawable.ic_pulltorefresh_arrow_up);
        }
        this.f13769r.setText(C2425R.C2429string.pull_to_refresh_footer_pull_label);
        this.f13773v.setVisibility(8);
        this.f13776y = 2;
    }

    /* renamed from: b */
    public void mo28401b(CharSequence charSequence) {
        if (charSequence != null) {
            this.f13771t.setVisibility(0);
            this.f13771t.setText(charSequence);
            return;
        }
        this.f13771t.setVisibility(4);
    }

    /* renamed from: j */
    private int m14805j() {
        return ((LinearLayout.LayoutParams) this.f13760i.getLayoutParams()).topMargin;
    }

    /* renamed from: a */
    public void mo28397a(AbstractC3494b bVar) {
        this.f13757K = bVar;
    }

    /* renamed from: a */
    public void mo28396a(AbstractC3493a aVar) {
        this.f13756J = aVar;
    }
}
