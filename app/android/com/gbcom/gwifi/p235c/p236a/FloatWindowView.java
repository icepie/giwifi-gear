package com.gbcom.gwifi.p235c.p236a;

import android.app.StatsManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.p235c.p239c.SystemUtils;

/* renamed from: com.gbcom.gwifi.c.a.f */
public class FloatWindowView extends FrameLayout implements IFloatView {

    /* renamed from: a */
    private static final String f9090a = FloatWindowView.class.getSimpleName();

    /* renamed from: A */
    private int f9091A = 0;

    /* renamed from: B */
    private int f9092B = 0;

    /* renamed from: C */
    private int f9093C = 0;

    /* renamed from: D */
    private int f9094D = 0;

    /* renamed from: E */
    private final Runnable f9095E = new Runnable() {
        /* class com.gbcom.gwifi.p235c.p236a.FloatWindowView.RunnableC25903 */

        @Override // java.lang.Runnable
        public void run() {
            FloatWindowView.this.m10724c((FloatWindowView) (FloatWindowView.this.f9113n.x + FloatWindowView.this.f9091A), FloatWindowView.this.f9113n.y + FloatWindowView.this.f9092B);
        }
    };

    /* renamed from: F */
    private boolean f9096F = false;

    /* renamed from: G */
    private final View.OnTouchListener f9097G = new View.OnTouchListener() {
        /* class com.gbcom.gwifi.p235c.p236a.FloatWindowView.View$OnTouchListenerC25914 */

        public boolean onTouch(View view, MotionEvent motionEvent) {
            return FloatWindowView.this.mo24491a(motionEvent);
        }
    };

    /* renamed from: H */
    private boolean f9098H = false;

    /* renamed from: I */
    private boolean f9099I = false;

    /* renamed from: J */
    private final Runnable f9100J = new Runnable() {
        /* class com.gbcom.gwifi.p235c.p236a.FloatWindowView.RunnableC25925 */

        @Override // java.lang.Runnable
        public void run() {
            FloatWindowView.this.m10749m();
        }
    };

    /* renamed from: b */
    private float f9101b;

    /* renamed from: c */
    private float f9102c;

    /* renamed from: d */
    private float f9103d;

    /* renamed from: e */
    private float f9104e;

    /* renamed from: f */
    private float f9105f;

    /* renamed from: g */
    private float f9106g;

    /* renamed from: h */
    private Context f9107h;

    /* renamed from: i */
    private TextView f9108i;

    /* renamed from: j */
    private RelativeLayout f9109j;

    /* renamed from: k */
    private RelativeLayout f9110k;

    /* renamed from: l */
    private ImageView f9111l;

    /* renamed from: m */
    private WindowManager f9112m = null;

    /* renamed from: n */
    private WindowManager.LayoutParams f9113n = null;

    /* renamed from: o */
    private FloatViewParams f9114o = null;

    /* renamed from: p */
    private FloatViewListener f9115p;

    /* renamed from: q */
    private int f9116q = 0;

    /* renamed from: r */
    private int f9117r;

    /* renamed from: s */
    private int f9118s;

    /* renamed from: t */
    private int f9119t;

    /* renamed from: u */
    private int f9120u;

    /* renamed from: v */
    private float f9121v = 1.77f;

    /* renamed from: w */
    private int f9122w;

    /* renamed from: x */
    private boolean f9123x = false;

    /* renamed from: y */
    private final View.OnTouchListener f9124y = new View.OnTouchListener() {
        /* class com.gbcom.gwifi.p235c.p236a.FloatWindowView.View$OnTouchListenerC25892 */

        /* renamed from: a */
        float f9127a = 0.0f;

        /* renamed from: b */
        float f9128b = 0.0f;

        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction()) {
                case 0:
                    FloatWindowView.this.f9098H = true;
                    this.f9127a = motionEvent.getRawX();
                    this.f9128b = motionEvent.getRawY();
                    FloatWindowView.this.f9125z = 0;
                    break;
                case 1:
                    if (FloatWindowView.this.f9115p != null) {
                        FloatWindowView.this.f9115p.mo24484d();
                    }
                    FloatWindowView.this.m10750n();
                    if (!FloatWindowView.this.f9123x) {
                        FloatWindowView.this.m10736f();
                    }
                    FloatWindowView.this.f9098H = false;
                    FloatWindowView.this.f9125z = 0;
                    break;
                case 2:
                    FloatWindowView.this.m10747l();
                    m10756a(motionEvent);
                    break;
            }
            return true;
        }

        /* renamed from: a */
        private void m10756a(MotionEvent motionEvent) {
            FloatWindowView.this.f9098H = true;
            float rawX = motionEvent.getRawX();
            float rawY = motionEvent.getRawY();
            float f = rawX - this.f9127a;
            float f2 = rawY - this.f9128b;
            double sqrt = Math.sqrt((double) ((f * f) + (f2 * f2)));
            if (sqrt >= 2.0d) {
                int width = FloatWindowView.this.f9110k.getWidth();
                if (rawY <= this.f9128b || rawX <= this.f9127a) {
                    if (width == FloatWindowView.this.f9120u) {
                        return;
                    }
                } else if (width != FloatWindowView.this.f9119t) {
                    sqrt = -sqrt;
                } else {
                    return;
                }
                int cos = (int) (sqrt * Math.cos(45.0d));
                if (!FloatWindowView.this.f9123x) {
                    if (FloatWindowView.this.f9113n.width != FloatWindowView.this.f9120u) {
                        FloatWindowView.this.m10732e((FloatWindowView) FloatWindowView.this.f9120u);
                    }
                    FloatWindowView.this.m10728d((FloatWindowView) cos);
                } else {
                    FloatWindowView.this.m10718b((FloatWindowView) cos);
                }
            }
            this.f9127a = rawX;
            this.f9128b = rawY;
        }
    };

    /* renamed from: z */
    private int f9125z = 0;

    public FloatWindowView(Context context, FloatViewParams dVar, WindowManager.LayoutParams layoutParams) {
        super(context);
        this.f9114o = dVar;
        this.f9113n = layoutParams;
        m10722c();
    }

    /* renamed from: c */
    private void m10722c() {
        m10731e();
        m10727d();
    }

    /* renamed from: d */
    private void m10727d() {
        View inflate = LayoutInflater.from(getContext()).inflate(C2425R.layout.float_view_inner_layout, (ViewGroup) null);
        this.f9110k = (RelativeLayout) inflate.findViewById(C2425R.C2427id.content_wrap);
        this.f9109j = (RelativeLayout) inflate.findViewById(C2425R.C2427id.videoViewWrap);
        this.f9108i = (TextView) inflate.findViewById(C2425R.C2427id.tv_info);
        this.f9108i.setText(getResources().getString(C2425R.C2429string.title_alert_window));
        this.f9111l = (ImageView) inflate.findViewById(C2425R.C2427id.iv_zoom_btn);
        inflate.findViewById(C2425R.C2427id.iv_close_window).setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.p235c.p236a.FloatWindowView.View$OnClickListenerC25881 */

            public void onClick(View view) {
                if (FloatWindowView.this.f9115p != null) {
                    FloatWindowView.this.f9115p.mo24481a();
                }
            }
        });
        int i = this.f9114o.f9070e;
        m10714a(i, (int) (((float) i) * this.f9121v));
        addView(inflate);
    }

    /* renamed from: e */
    private void m10731e() {
        this.f9107h = getContext();
        this.f9112m = SystemUtils.m10807c(this.f9107h);
        this.f9116q = this.f9114o.f9074i;
        this.f9117r = this.f9114o.f9072g;
        this.f9118s = this.f9114o.f9073h - this.f9116q;
        this.f9122w = this.f9114o.f9078m;
        this.f9120u = this.f9114o.f9076k;
        this.f9119t = this.f9114o.f9075j;
        this.f9121v = this.f9114o.f9077l;
        this.f9093C = this.f9114o.f9068c;
        this.f9094D = this.f9114o.f9069d;
    }

    /* renamed from: a */
    private void m10714a(int i, int i2) {
        if (this.f9110k != null) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.f9110k.getLayoutParams();
            layoutParams.height = i2;
            layoutParams.width = i;
            this.f9110k.setLayoutParams(layoutParams);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: b */
    private void m10718b(int i) {
        int f = m10734f(this.f9113n.width + i);
        int i2 = (int) (((float) f) * this.f9121v);
        m10723c(i);
        m10719b(f, i2);
        m10714a(f, i2);
    }

    /* renamed from: c */
    private void m10723c(int i) {
        this.f9125z += i / 2;
        int i2 = this.f9093C - this.f9125z;
        int i3 = (int) (((float) this.f9094D) - (((float) this.f9125z) * this.f9121v));
        int i4 = this.f9113n.width;
        if (i4 >= this.f9119t && i4 <= this.f9120u) {
            this.f9113n.x = i2;
            this.f9113n.y = i3;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: f */
    private void m10736f() {
        this.f9091A = this.f9110k.getLeft();
        this.f9092B = this.f9110k.getTop();
        m10738g();
        m10732e(this.f9110k.getWidth());
        if (this.f9091A > 0 && this.f9092B > 0) {
            removeCallbacks(this.f9095E);
            postDelayed(this.f9095E, 0);
        }
    }

    /* renamed from: a */
    public int mo24489a() {
        return this.f9110k != null ? this.f9110k.getWidth() : this.f9119t;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: d */
    private void m10728d(int i) {
        int f = m10734f(this.f9110k.getWidth() + i);
        m10714a(f, (int) (((float) f) * this.f9121v));
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: e */
    private void m10732e(int i) {
        int f = m10734f(i);
        m10719b(f, (int) (((float) f) * this.f9121v));
    }

    /* renamed from: b */
    private synchronized void m10719b(int i, int i2) {
        if (this.f9112m != null) {
            this.f9113n.width = i;
            this.f9113n.height = i2;
            this.f9112m.updateViewLayout(this, this.f9113n);
        }
    }

    /* renamed from: f */
    private int m10734f(int i) {
        if (i > this.f9120u) {
            i = this.f9120u;
        }
        if (i < this.f9119t) {
            return this.f9119t;
        }
        return i;
    }

    /* renamed from: g */
    private void m10738g() {
        int width = this.f9110k.getWidth();
        if (this.f9113n.x + width >= this.f9117r) {
            this.f9113n.x = (this.f9117r - width) - 1;
        }
        if (this.f9113n.x <= 0) {
            this.f9113n.x = 0;
        }
        int height = this.f9110k.getHeight();
        if (this.f9113n.y + height >= this.f9118s) {
            this.f9113n.y = (this.f9118s - height) - 1;
        }
        if (this.f9113n.y <= this.f9116q) {
            this.f9113n.y = this.f9116q;
        }
    }

    /* renamed from: a */
    public boolean mo24491a(MotionEvent motionEvent) {
        boolean z = false;
        if (!this.f9098H) {
            switch (motionEvent.getAction()) {
                case 0:
                    this.f9096F = false;
                    this.f9101b = motionEvent.getX();
                    this.f9102c = motionEvent.getY();
                    this.f9105f = motionEvent.getRawX();
                    this.f9106g = motionEvent.getRawY();
                    this.f9103d = this.f9105f;
                    this.f9104e = this.f9106g;
                    break;
                case 1:
                    if (m10740h()) {
                        if (this.f9115p != null) {
                            this.f9115p.mo24482b();
                        }
                    } else if (this.f9115p != null) {
                        this.f9115p.mo24483c();
                    }
                    m10744j();
                    this.f9096F = false;
                    break;
                case 2:
                    m10747l();
                    this.f9103d = motionEvent.getRawX();
                    this.f9104e = motionEvent.getRawY();
                    if (this.f9096F) {
                        m10742i();
                        break;
                    } else {
                        if (!m10740h()) {
                            z = true;
                        }
                        this.f9096F = z;
                        break;
                    }
            }
        }
        return true;
    }

    /* renamed from: h */
    private boolean m10740h() {
        int scaledTouchSlop = ViewConfiguration.get(this.f9107h).getScaledTouchSlop();
        if (Math.abs(this.f9105f - this.f9103d) > ((float) scaledTouchSlop) || Math.abs(this.f9106g - this.f9104e) > ((float) scaledTouchSlop)) {
            return false;
        }
        return true;
    }

    /* renamed from: i */
    private void m10742i() {
        int i = (int) (this.f9103d - this.f9101b);
        int i2 = (int) (this.f9104e - this.f9102c);
        if (i2 < this.f9116q) {
            i2 = this.f9116q;
        }
        this.f9093C = i;
        this.f9094D = i2;
        m10724c(i, i2);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: c */
    private synchronized void m10724c(int i, int i2) {
        if (this.f9112m != null) {
            this.f9113n.x = i;
            this.f9113n.y = i2;
            this.f9112m.updateViewLayout(this, this.f9113n);
        }
    }

    /* renamed from: j */
    private void m10744j() {
        m10746k();
        m10750n();
    }

    /* renamed from: k */
    private void m10746k() {
        boolean z = true;
        boolean z2 = this.f9113n.x <= 0;
        if (this.f9113n.y > this.f9116q) {
            z = false;
        }
        if (z2 || z) {
            m10749m();
            if (z2 && z) {
                m10715a(0, 0, this.f9122w, this.f9122w);
            } else if (z2) {
                m10715a(0, this.f9122w, this.f9122w, 0);
            } else if (z) {
                m10715a(this.f9122w, 0, 0, this.f9122w);
            }
        } else {
            m10747l();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: l */
    private void m10747l() {
        if (!this.f9099I) {
            m10715a(this.f9122w, this.f9122w, 0, 0);
            this.f9111l.setVisibility(0);
            this.f9109j.setBackgroundColor(getResources().getColor(C2425R.color.float_window_bg_border_edit));
            this.f9099I = true;
        }
    }

    /* renamed from: a */
    private void m10715a(int i, int i2, int i3, int i4) {
        if (this.f9109j != null) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.f9109j.getLayoutParams();
            layoutParams.setMargins(i, i2, i3, i4);
            this.f9109j.setLayoutParams(layoutParams);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: m */
    private void m10749m() {
        this.f9099I = false;
        this.f9111l.setVisibility(8);
        this.f9109j.setBackgroundColor(getResources().getColor(C2425R.color.float_window_bg_border_normal));
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: n */
    private void m10750n() {
        removeCallbacks(this.f9100J);
        postDelayed(this.f9100J, StatsManager.DEFAULT_TIMEOUT_MILLIS);
    }

    @Override // com.gbcom.gwifi.p235c.p236a.IFloatView
    /* renamed from: b */
    public FloatViewParams mo24465b() {
        this.f9114o.f9070e = mo24489a();
        this.f9114o.f9068c = this.f9113n.x;
        this.f9114o.f9069d = this.f9113n.y;
        this.f9114o.f9066a = this.f9113n.width;
        this.f9114o.f9067b = this.f9113n.height;
        return this.f9114o;
    }

    @Override // com.gbcom.gwifi.p235c.p236a.IFloatView
    /* renamed from: a */
    public void mo24463a(FloatViewListener cVar) {
        this.f9115p = cVar;
    }

    /* renamed from: a */
    public void mo24490a(int i) {
        if (i == 11) {
            this.f9108i.setText(getResources().getString(C2425R.C2429string.title_float_window_dialog));
        } else if (i == 12) {
            this.f9108i.setText(getResources().getString(C2425R.C2429string.title_alert_window));
        }
    }
}
