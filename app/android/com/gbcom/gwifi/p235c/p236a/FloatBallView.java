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

/* renamed from: com.gbcom.gwifi.c.a.a */
public class FloatBallView extends FrameLayout implements IFloatView {

    /* renamed from: a */
    private static final String f8971a = FloatBallView.class.getSimpleName();

    /* renamed from: A */
    private int f8972A = 0;

    /* renamed from: B */
    private int f8973B = 0;

    /* renamed from: C */
    private int f8974C = 0;

    /* renamed from: D */
    private int f8975D = 0;

    /* renamed from: E */
    private final Runnable f8976E = new Runnable() {
        /* class com.gbcom.gwifi.p235c.p236a.FloatBallView.RunnableC25753 */

        @Override // java.lang.Runnable
        public void run() {
            FloatBallView.this.m10630c((FloatBallView) (FloatBallView.this.f8994n.x + FloatBallView.this.f8972A), FloatBallView.this.f8994n.y + FloatBallView.this.f8973B);
        }
    };

    /* renamed from: F */
    private boolean f8977F = false;

    /* renamed from: G */
    private final View.OnTouchListener f8978G = new View.OnTouchListener() {
        /* class com.gbcom.gwifi.p235c.p236a.FloatBallView.View$OnTouchListenerC25764 */

        public boolean onTouch(View view, MotionEvent motionEvent) {
            return FloatBallView.this.mo24464a(motionEvent);
        }
    };

    /* renamed from: H */
    private boolean f8979H = false;

    /* renamed from: I */
    private boolean f8980I = false;

    /* renamed from: J */
    private final Runnable f8981J = new Runnable() {
        /* class com.gbcom.gwifi.p235c.p236a.FloatBallView.RunnableC25775 */

        @Override // java.lang.Runnable
        public void run() {
            FloatBallView.this.m10654m();
        }
    };

    /* renamed from: b */
    private float f8982b;

    /* renamed from: c */
    private float f8983c;

    /* renamed from: d */
    private float f8984d;

    /* renamed from: e */
    private float f8985e;

    /* renamed from: f */
    private float f8986f;

    /* renamed from: g */
    private float f8987g;

    /* renamed from: h */
    private Context f8988h;

    /* renamed from: i */
    private TextView f8989i;

    /* renamed from: j */
    private RelativeLayout f8990j;

    /* renamed from: k */
    private RelativeLayout f8991k;

    /* renamed from: l */
    private ImageView f8992l;

    /* renamed from: m */
    private WindowManager f8993m = null;

    /* renamed from: n */
    private WindowManager.LayoutParams f8994n = null;

    /* renamed from: o */
    private FloatViewParams f8995o = null;

    /* renamed from: p */
    private FloatViewListener f8996p;

    /* renamed from: q */
    private int f8997q = 0;

    /* renamed from: r */
    private int f8998r;

    /* renamed from: s */
    private int f8999s;

    /* renamed from: t */
    private int f9000t;

    /* renamed from: u */
    private int f9001u;

    /* renamed from: v */
    private float f9002v = 1.77f;

    /* renamed from: w */
    private int f9003w;

    /* renamed from: x */
    private boolean f9004x = false;

    /* renamed from: y */
    private final View.OnTouchListener f9005y = new View.OnTouchListener() {
        /* class com.gbcom.gwifi.p235c.p236a.FloatBallView.View$OnTouchListenerC25742 */

        /* renamed from: a */
        float f9008a = 0.0f;

        /* renamed from: b */
        float f9009b = 0.0f;

        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction()) {
                case 0:
                    FloatBallView.this.f8979H = true;
                    this.f9008a = motionEvent.getRawX();
                    this.f9009b = motionEvent.getRawY();
                    FloatBallView.this.f9006z = 0;
                    break;
                case 1:
                    if (FloatBallView.this.f8996p != null) {
                        FloatBallView.this.f8996p.mo24484d();
                    }
                    FloatBallView.this.m10655n();
                    if (!FloatBallView.this.f9004x) {
                        FloatBallView.this.m10641f();
                    }
                    FloatBallView.this.f8979H = false;
                    FloatBallView.this.f9006z = 0;
                    break;
                case 2:
                    FloatBallView.this.m10652l();
                    m10660a(motionEvent);
                    break;
            }
            return true;
        }

        /* renamed from: a */
        private void m10660a(MotionEvent motionEvent) {
            FloatBallView.this.f8979H = true;
            float rawX = motionEvent.getRawX();
            float rawY = motionEvent.getRawY();
            float f = rawX - this.f9008a;
            float f2 = rawY - this.f9009b;
            double sqrt = Math.sqrt((double) ((f * f) + (f2 * f2)));
            if (sqrt >= 2.0d) {
                int width = FloatBallView.this.f8991k.getWidth();
                if (rawY <= this.f9009b || rawX <= this.f9008a) {
                    if (width == FloatBallView.this.f9001u) {
                        return;
                    }
                } else if (width != FloatBallView.this.f9000t) {
                    sqrt = -sqrt;
                } else {
                    return;
                }
                int cos = (int) (sqrt * Math.cos(45.0d));
                if (!FloatBallView.this.f9004x) {
                    if (FloatBallView.this.f8994n.width != FloatBallView.this.f9001u) {
                        FloatBallView.this.m10634d((FloatBallView) FloatBallView.this.f9001u);
                    }
                    FloatBallView.this.m10629c((FloatBallView) cos);
                } else {
                    FloatBallView.this.m10619a((FloatBallView) cos);
                }
            }
            this.f9008a = rawX;
            this.f9009b = rawY;
        }
    };

    /* renamed from: z */
    private int f9006z = 0;

    public FloatBallView(Context context, FloatViewParams dVar, WindowManager.LayoutParams layoutParams) {
        super(context);
        this.f8995o = dVar;
        this.f8994n = layoutParams;
        m10628c();
    }

    /* renamed from: c */
    private void m10628c() {
        m10638e();
        m10633d();
    }

    /* renamed from: d */
    private void m10633d() {
        View inflate = LayoutInflater.from(getContext()).inflate(C2425R.layout.float_view_desktop_layout, (ViewGroup) null);
        this.f8991k = (RelativeLayout) inflate.findViewById(C2425R.C2427id.content_wrap);
        this.f8990j = (RelativeLayout) inflate.findViewById(C2425R.C2427id.videoViewWrap);
        this.f8989i = (TextView) inflate.findViewById(C2425R.C2427id.tv_info);
        this.f8989i.setText(getResources().getString(C2425R.C2429string.title_alert_window));
        this.f8992l = (ImageView) inflate.findViewById(C2425R.C2427id.iv_zoom_btn);
        this.f8992l.setOnTouchListener(this.f9005y);
        this.f8991k.setOnTouchListener(this.f8978G);
        inflate.findViewById(C2425R.C2427id.iv_close_window).setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.p235c.p236a.FloatBallView.View$OnClickListenerC25731 */

            public void onClick(View view) {
                if (FloatBallView.this.f8996p != null) {
                    FloatBallView.this.f8996p.mo24481a();
                }
            }
        });
        int i = this.f8995o.f9070e;
        m10620a(i, (int) (((float) i) * this.f9002v));
        addView(inflate);
    }

    /* renamed from: e */
    private void m10638e() {
        this.f8988h = getContext();
        this.f8993m = SystemUtils.m10807c(this.f8988h);
        this.f8997q = this.f8995o.f9074i;
        this.f8998r = this.f8995o.f9072g;
        this.f8999s = this.f8995o.f9073h - this.f8997q;
        this.f9003w = this.f8995o.f9078m;
        this.f9001u = this.f8995o.f9076k;
        this.f9000t = this.f8995o.f9075j;
        this.f9002v = this.f8995o.f9077l;
        this.f8974C = this.f8995o.f9068c;
        this.f8975D = this.f8995o.f9069d;
    }

    /* renamed from: a */
    private void m10620a(int i, int i2) {
        if (this.f8991k != null) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.f8991k.getLayoutParams();
            layoutParams.height = i2;
            layoutParams.width = i;
            this.f8991k.setLayoutParams(layoutParams);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m10619a(int i) {
        int e = m10637e(this.f8994n.width + i);
        int i2 = (int) (((float) e) * this.f9002v);
        m10624b(i);
        m10625b(e, i2);
        m10620a(e, i2);
    }

    /* renamed from: b */
    private void m10624b(int i) {
        this.f9006z += i / 2;
        int i2 = this.f8974C - this.f9006z;
        int i3 = (int) (((float) this.f8975D) - (((float) this.f9006z) * this.f9002v));
        int i4 = this.f8994n.width;
        if (i4 >= this.f9000t && i4 <= this.f9001u) {
            this.f8994n.x = i2;
            this.f8994n.y = i3;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: f */
    private void m10641f() {
        this.f8972A = this.f8991k.getLeft();
        this.f8973B = this.f8991k.getTop();
        m10643g();
        m10634d(this.f8991k.getWidth());
        if (this.f8972A > 0 && this.f8973B > 0) {
            removeCallbacks(this.f8976E);
            postDelayed(this.f8976E, 0);
        }
    }

    /* renamed from: a */
    public int mo24462a() {
        return this.f8991k != null ? this.f8991k.getWidth() : this.f9000t;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: c */
    private void m10629c(int i) {
        int e = m10637e(this.f8991k.getWidth() + i);
        m10620a(e, (int) (((float) e) * this.f9002v));
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: d */
    private void m10634d(int i) {
        int e = m10637e(i);
        m10625b(e, (int) (((float) e) * this.f9002v));
    }

    /* renamed from: b */
    private synchronized void m10625b(int i, int i2) {
        if (this.f8993m != null) {
            this.f8994n.width = i;
            this.f8994n.height = i2;
            this.f8993m.updateViewLayout(this, this.f8994n);
        }
    }

    /* renamed from: e */
    private int m10637e(int i) {
        if (i > this.f9001u) {
            i = this.f9001u;
        }
        if (i < this.f9000t) {
            return this.f9000t;
        }
        return i;
    }

    /* renamed from: g */
    private void m10643g() {
        int width = this.f8991k.getWidth();
        if (this.f8994n.x + width >= this.f8998r) {
            this.f8994n.x = (this.f8998r - width) - 1;
        }
        if (this.f8994n.x <= 0) {
            this.f8994n.x = 0;
        }
        int height = this.f8991k.getHeight();
        if (this.f8994n.y + height >= this.f8999s) {
            this.f8994n.y = (this.f8999s - height) - 1;
        }
        if (this.f8994n.y <= this.f8997q) {
            this.f8994n.y = this.f8997q;
        }
    }

    /* renamed from: a */
    public boolean mo24464a(MotionEvent motionEvent) {
        boolean z = false;
        if (!this.f8979H) {
            switch (motionEvent.getAction()) {
                case 0:
                    this.f8977F = false;
                    this.f8982b = motionEvent.getX();
                    this.f8983c = motionEvent.getY();
                    this.f8986f = motionEvent.getRawX();
                    this.f8987g = motionEvent.getRawY();
                    this.f8984d = this.f8986f;
                    this.f8985e = this.f8987g;
                    break;
                case 1:
                    if (m10645h()) {
                        if (this.f8996p != null) {
                            this.f8996p.mo24482b();
                        }
                    } else if (this.f8996p != null) {
                        this.f8996p.mo24483c();
                    }
                    m10649j();
                    this.f8977F = false;
                    break;
                case 2:
                    m10652l();
                    this.f8984d = motionEvent.getRawX();
                    this.f8985e = motionEvent.getRawY();
                    if (this.f8977F) {
                        m10647i();
                        break;
                    } else {
                        if (!m10645h()) {
                            z = true;
                        }
                        this.f8977F = z;
                        break;
                    }
            }
        }
        return true;
    }

    /* renamed from: h */
    private boolean m10645h() {
        int scaledTouchSlop = ViewConfiguration.get(this.f8988h).getScaledTouchSlop();
        if (Math.abs(this.f8986f - this.f8984d) > ((float) scaledTouchSlop) || Math.abs(this.f8987g - this.f8985e) > ((float) scaledTouchSlop)) {
            return false;
        }
        return true;
    }

    /* renamed from: i */
    private void m10647i() {
        int i = (int) (this.f8984d - this.f8982b);
        int i2 = (int) (this.f8985e - this.f8983c);
        if (i2 < this.f8997q) {
            i2 = this.f8997q;
        }
        this.f8974C = i;
        this.f8975D = i2;
        m10630c(i, i2);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: c */
    private synchronized void m10630c(int i, int i2) {
        if (this.f8993m != null) {
            this.f8994n.x = i;
            this.f8994n.y = i2;
            this.f8993m.updateViewLayout(this, this.f8994n);
        }
    }

    /* renamed from: j */
    private void m10649j() {
        m10651k();
        m10655n();
    }

    /* renamed from: k */
    private void m10651k() {
        boolean z = true;
        boolean z2 = this.f8994n.x <= 0;
        if (this.f8994n.y > this.f8997q) {
            z = false;
        }
        if (z2 || z) {
            m10654m();
            if (z2 && z) {
                m10621a(0, 0, this.f9003w, this.f9003w);
            } else if (z2) {
                m10621a(0, this.f9003w, this.f9003w, 0);
            } else if (z) {
                m10621a(this.f9003w, 0, 0, this.f9003w);
            }
        } else {
            m10652l();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: l */
    private void m10652l() {
        if (!this.f8980I) {
            m10621a(this.f9003w, this.f9003w, 0, 0);
            this.f8992l.setVisibility(0);
            this.f8990j.setBackgroundColor(getResources().getColor(C2425R.color.float_window_bg_border_edit));
            this.f8980I = true;
        }
    }

    /* renamed from: a */
    private void m10621a(int i, int i2, int i3, int i4) {
        if (this.f8990j != null) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.f8990j.getLayoutParams();
            layoutParams.setMargins(i, i2, i3, i4);
            this.f8990j.setLayoutParams(layoutParams);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: m */
    private void m10654m() {
        this.f8980I = false;
        this.f8992l.setVisibility(8);
        this.f8990j.setBackgroundColor(getResources().getColor(C2425R.color.float_window_bg_border_normal));
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: n */
    private void m10655n() {
        removeCallbacks(this.f8981J);
        postDelayed(this.f8981J, StatsManager.DEFAULT_TIMEOUT_MILLIS);
    }

    @Override // com.gbcom.gwifi.p235c.p236a.IFloatView
    /* renamed from: b */
    public FloatViewParams mo24465b() {
        this.f8995o.f9070e = mo24462a();
        this.f8995o.f9068c = this.f8994n.x;
        this.f8995o.f9069d = this.f8994n.y;
        this.f8995o.f9066a = this.f8994n.width;
        this.f8995o.f9067b = this.f8994n.height;
        return this.f8995o;
    }

    @Override // com.gbcom.gwifi.p235c.p236a.IFloatView
    /* renamed from: a */
    public void mo24463a(FloatViewListener cVar) {
        this.f8996p = cVar;
    }
}
