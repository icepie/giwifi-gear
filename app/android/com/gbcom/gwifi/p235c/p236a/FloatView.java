package com.gbcom.gwifi.p235c.p236a;

import android.app.StatsManager;
import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.functions.loading.MainActivity;
import com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView;
import com.gbcom.gwifi.functions.template.fragment.GiWiFiInfoExView;
import com.gbcom.gwifi.functions.wifi.WiFiUtil;
import com.gbcom.gwifi.util.C3467s;
import com.gbcom.gwifi.util.cache.CacheWiFi;
import com.umeng.message.proguard.MessageStore;
import org.apache.xpath.compiler.PsuedoNames;

/* renamed from: com.gbcom.gwifi.c.a.b */
public class FloatView extends FrameLayout implements IFloatView {

    /* renamed from: a */
    private static final String f9014a = FloatView.class.getSimpleName();

    /* renamed from: A */
    private boolean f9015A = false;

    /* renamed from: B */
    private int f9016B = 0;

    /* renamed from: C */
    private int f9017C = 0;

    /* renamed from: D */
    private final View.OnLayoutChangeListener f9018D = new View.OnLayoutChangeListener() {
        /* class com.gbcom.gwifi.p235c.p236a.FloatView.View$OnLayoutChangeListenerC25868 */

        public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            if (i3 != FloatView.this.f9019E || i4 != FloatView.this.f9020F) {
                Log.d(FloatView.f9014a, "dq onLayoutChange111 left=" + i + ",top=" + i2 + ",right=" + i3 + ",bottom=" + i4);
                int width = FloatView.this.f9035j.getWidth();
                int height = FloatView.this.f9035j.getHeight();
                int i9 = FloatView.this.f9019E - width;
                int i10 = FloatView.this.f9020F - height;
                int i11 = FloatView.this.f9019E;
                int i12 = FloatView.this.f9020F;
                if (i9 < (-FloatView.this.f9044s)) {
                    i9 = -FloatView.this.f9044s;
                    i11 = i9 + width;
                }
                if (i10 < (-FloatView.this.f9044s)) {
                    i10 = -FloatView.this.f9044s;
                    i12 = i10 + height;
                }
                FloatView.this.f9035j.layout(i9, i10, i11, i12);
                FloatView.this.f9037l.f9068c = i9;
                FloatView.this.f9037l.f9069d = i10;
            }
        }
    };

    /* renamed from: E */
    private int f9019E = 0;

    /* renamed from: F */
    private int f9020F = 0;

    /* renamed from: G */
    private final View.OnTouchListener f9021G = new View.OnTouchListener() {
        /* class com.gbcom.gwifi.p235c.p236a.FloatView.View$OnTouchListenerC25879 */

        /* renamed from: a */
        float f9063a = 0.0f;

        /* renamed from: b */
        float f9064b = 0.0f;

        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction()) {
                case 0:
                    FloatView.this.f9024J = true;
                    this.f9063a = motionEvent.getRawX();
                    this.f9064b = motionEvent.getRawY();
                    FloatView.this.f9019E = FloatView.this.f9035j.getRight();
                    FloatView.this.f9020F = FloatView.this.f9035j.getBottom();
                    break;
                case 1:
                    if (FloatView.this.f9038m != null) {
                        FloatView.this.f9038m.mo24484d();
                    }
                    FloatView.this.m10689k();
                    FloatView.this.f9024J = false;
                    break;
                case 2:
                    FloatView.this.m10684i();
                    m10698a(motionEvent);
                    break;
            }
            return true;
        }

        /* renamed from: a */
        private void m10698a(MotionEvent motionEvent) {
            FloatView.this.f9024J = true;
            float rawX = motionEvent.getRawX();
            float rawY = motionEvent.getRawY();
            float f = rawX - this.f9063a;
            float f2 = rawY - this.f9064b;
            double sqrt = Math.sqrt((double) ((f * f) + (f2 * f2)));
            if (sqrt >= 5.0d) {
                int width = FloatView.this.f9035j.getWidth();
                if (rawY <= this.f9064b || rawX <= this.f9063a) {
                    if (width == FloatView.this.f9042q) {
                        return;
                    }
                } else if (width != FloatView.this.f9041p) {
                    sqrt = -sqrt;
                } else {
                    return;
                }
                FloatView.this.m10663a((FloatView) ((int) (sqrt * Math.cos(45.0d))));
            }
            this.f9063a = rawX;
            this.f9064b = rawY;
        }
    };

    /* renamed from: H */
    private boolean f9022H = false;

    /* renamed from: I */
    private final View.OnTouchListener f9023I = new View.OnTouchListener() {
        /* class com.gbcom.gwifi.p235c.p236a.FloatView.View$OnTouchListenerC257910 */

        public boolean onTouch(View view, MotionEvent motionEvent) {
            return FloatView.this.mo24470a(motionEvent);
        }
    };

    /* renamed from: J */
    private boolean f9024J = false;

    /* renamed from: K */
    private boolean f9025K = false;

    /* renamed from: L */
    private final Runnable f9026L = new Runnable() {
        /* class com.gbcom.gwifi.p235c.p236a.FloatView.RunnableC25802 */

        @Override // java.lang.Runnable
        public void run() {
            FloatView.this.m10687j();
        }
    };

    /* renamed from: b */
    private float f9027b;

    /* renamed from: c */
    private float f9028c;

    /* renamed from: d */
    private float f9029d;

    /* renamed from: e */
    private float f9030e;

    /* renamed from: f */
    private float f9031f;

    /* renamed from: g */
    private float f9032g;

    /* renamed from: h */
    private Context f9033h;

    /* renamed from: i */
    private RelativeLayout f9034i;

    /* renamed from: j */
    private RelativeLayout f9035j;

    /* renamed from: k */
    private ImageView f9036k;

    /* renamed from: l */
    private FloatViewParams f9037l = null;

    /* renamed from: m */
    private FloatViewListener f9038m;

    /* renamed from: n */
    private int f9039n;

    /* renamed from: o */
    private int f9040o;

    /* renamed from: p */
    private int f9041p;

    /* renamed from: q */
    private int f9042q;

    /* renamed from: r */
    private float f9043r = 1.77f;

    /* renamed from: s */
    private int f9044s;

    /* renamed from: t */
    private View f9045t;

    /* renamed from: u */
    private GiWiFiInfoExView f9046u;

    /* renamed from: v */
    private LinearLayout f9047v;

    /* renamed from: w */
    private ImageView f9048w;

    /* renamed from: x */
    private TextView f9049x;

    /* renamed from: y */
    private TextView f9050y;

    /* renamed from: z */
    private Button f9051z;

    public FloatView(Context context) {
        super(context);
        m10675d();
    }

    public FloatView(@NonNull Context context, FloatViewParams dVar) {
        super(context);
        this.f9037l = dVar;
        m10675d();
    }

    /* renamed from: d */
    private void m10675d() {
        m10679f();
        m10677e();
    }

    /* renamed from: e */
    private void m10677e() {
        this.f9045t = LayoutInflater.from(getContext()).inflate(C2425R.layout.float_view_inner_layout, (ViewGroup) null);
        this.f9035j = (RelativeLayout) this.f9045t.findViewById(C2425R.C2427id.content_wrap);
        this.f9034i = (RelativeLayout) this.f9045t.findViewById(C2425R.C2427id.videoViewWrap);
        this.f9036k = (ImageView) this.f9045t.findViewById(C2425R.C2427id.iv_zoom_btn);
        ((TextView) this.f9045t.findViewById(C2425R.C2427id.tv_info)).setText(getResources().getString(C2425R.C2429string.title_app_float_view));
        this.f9045t.findViewById(C2425R.C2427id.iv_close_window).setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.p235c.p236a.FloatView.View$OnClickListenerC25781 */

            public void onClick(View view) {
                if (FloatView.this.f9038m != null) {
                    FloatView.this.f9038m.mo24481a();
                }
            }
        });
        final GBActivity currentActivity = GBApplication.instance().getCurrentActivity();
        if (currentActivity != null && (currentActivity instanceof MainActivity)) {
            this.f9046u = (GiWiFiInfoExView) currentActivity.findViewById(C2425R.C2427id.wifi_info_layout);
            this.f9046u.mo27115a(new BaseGiWiFiInfoView.AbstractC3243m() {
                /* class com.gbcom.gwifi.p235c.p236a.FloatView.C25813 */

                @Override // com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView.AbstractC3243m
                /* renamed from: a */
                public void mo24474a(String str) {
                    if (!C3467s.m14513e(str)) {
                        String apLocation = CacheWiFi.getInstance().getApLocation();
                        if (!C3467s.m14513e(apLocation)) {
                            int lastIndexOf = apLocation.lastIndexOf(PsuedoNames.PSEUDONAME_ROOT);
                            if (lastIndexOf == -1) {
                                str = str + MessageStore.f23535s + apLocation + MessageStore.f23536t;
                            } else {
                                str = str + MessageStore.f23535s + apLocation.substring(lastIndexOf + 1) + MessageStore.f23536t;
                            }
                        }
                        FloatView.this.f9050y.setText(str);
                    }
                }
            });
            this.f9046u.mo27109a(new BaseGiWiFiInfoView.AbstractC3237g() {
                /* class com.gbcom.gwifi.p235c.p236a.FloatView.C25824 */

                @Override // com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView.AbstractC3237g
                /* renamed from: a */
                public void mo24475a(String str) {
                    String charSequence = FloatView.this.f9050y.getText().toString();
                    int lastIndexOf = charSequence.lastIndexOf(MessageStore.f23535s);
                    if (lastIndexOf != -1) {
                        String substring = charSequence.substring(0, lastIndexOf);
                        if (!C3467s.m14513e(str)) {
                            substring = substring + MessageStore.f23535s + str + MessageStore.f23536t;
                        }
                        FloatView.this.f9050y.setText(substring);
                    } else if (!C3467s.m14513e(str)) {
                        FloatView.this.f9050y.setText(charSequence + MessageStore.f23535s + str + MessageStore.f23536t);
                    }
                }
            });
            this.f9046u.mo27111a(new BaseGiWiFiInfoView.AbstractC3239i() {
                /* class com.gbcom.gwifi.p235c.p236a.FloatView.C25835 */

                @Override // com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView.AbstractC3239i
                /* renamed from: a */
                public void mo24476a(BaseGiWiFiInfoView.EnumC3246p pVar) {
                }
            });
        }
        this.f9047v = (LinearLayout) this.f9045t.findViewById(C2425R.C2427id.wifi_state_ll);
        this.f9048w = (ImageView) this.f9045t.findViewById(C2425R.C2427id.wifi_state_iv);
        this.f9047v.setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.p235c.p236a.FloatView.View$OnClickListenerC25846 */

            public void onClick(View view) {
                if (currentActivity != null && (currentActivity instanceof MainActivity)) {
                    ((MainActivity) currentActivity).showAndClose();
                }
                if (FloatView.this.f9038m != null) {
                    FloatView.this.f9038m.mo24481a();
                }
            }
        });
        this.f9049x = (TextView) this.f9045t.findViewById(C2425R.C2427id.wifi_state_ssid_tv);
        this.f9050y = (TextView) this.f9045t.findViewById(C2425R.C2427id.wifi_state_tv);
        if (this.f9046u != null) {
            int wifiControlState = CacheWiFi.getInstance().getWifiControlState();
            if (wifiControlState == BaseGiWiFiInfoView.EnumC3246p.unconnectShow.mo27260a()) {
                this.f9049x.setText("无网络");
            } else if (wifiControlState == BaseGiWiFiInfoView.EnumC3246p.mobileShow.mo27260a()) {
                this.f9049x.setText(WiFiUtil.m14021a().mo27626t());
            } else {
                String currentSsid = CacheWiFi.getInstance().getCurrentSsid();
                if (C3467s.m14513e(currentSsid)) {
                    this.f9049x.setText("Unknown");
                } else {
                    this.f9049x.setText(currentSsid);
                }
            }
            String aw = this.f9046u.mo27171aw();
            if (!C3467s.m14513e(aw)) {
                String apLocation = CacheWiFi.getInstance().getApLocation();
                if (!C3467s.m14513e(apLocation)) {
                    int lastIndexOf = apLocation.lastIndexOf(PsuedoNames.PSEUDONAME_ROOT);
                    if (lastIndexOf == -1) {
                        aw = aw + MessageStore.f23535s + apLocation + MessageStore.f23536t;
                    } else {
                        aw = aw + MessageStore.f23535s + apLocation.substring(lastIndexOf + 1) + MessageStore.f23536t;
                    }
                }
                this.f9050y.setText(aw);
            }
        }
        this.f9051z = (Button) this.f9045t.findViewById(C2425R.C2427id.wifi_close_btn);
        this.f9051z.setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.p235c.p236a.FloatView.View$OnClickListenerC25857 */

            public void onClick(View view) {
                if (currentActivity != null && (currentActivity instanceof MainActivity)) {
                    ((MainActivity) currentActivity).showAndClose();
                }
                if (FloatView.this.f9038m != null) {
                    FloatView.this.f9038m.mo24481a();
                }
            }
        });
        m10664a(this.f9037l.f9070e, this.f9037l.f9071f);
        addView(this.f9045t);
    }

    /* renamed from: f */
    private void m10679f() {
        this.f9033h = getContext();
        this.f9044s = this.f9037l.f9078m;
        this.f9039n = this.f9037l.f9072g;
        this.f9040o = this.f9037l.f9073h;
        this.f9042q = this.f9037l.f9076k;
        this.f9041p = this.f9037l.f9075j;
        this.f9043r = this.f9037l.f9077l;
        this.f9016B = this.f9037l.f9068c;
        this.f9017C = this.f9037l.f9069d;
        this.f9019E = this.f9037l.f9068c + this.f9037l.f9066a;
        this.f9020F = this.f9037l.f9069d + this.f9037l.f9067b;
        Log.d(f9014a, " width=" + this.f9037l.f9066a + ",heigh=" + this.f9037l.f9067b);
        Log.d(f9014a, " x=" + this.f9037l.f9068c + ",y=" + this.f9037l.f9069d);
        Log.d(f9014a, " mRight=" + this.f9019E + PsuedoNames.PSEUDONAME_ROOT + this.f9020F + ",rangeWidth=" + this.f9041p + "-" + this.f9042q + ",mRatio=" + this.f9043r);
    }

    /* renamed from: a */
    private void m10664a(int i, int i2) {
        if (this.f9035j != null) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.f9035j.getLayoutParams();
            layoutParams.height = i2;
            layoutParams.width = i;
            this.f9035j.setLayoutParams(layoutParams);
            this.f9037l.f9066a = i;
            this.f9037l.f9067b = i2;
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        Log.d(f9014a, "changed:" + z);
        Log.d(f9014a, "left=" + i + ",top=" + i2);
        Log.d(f9014a, "right=" + i3 + ",bottom=" + i4);
        Log.d(f9014a, "isRestorePosition=" + this.f9015A);
        Log.d(f9014a, "oldX=" + this.f9016B + ",oldY=" + this.f9017C);
        Log.d(f9014a, "param.width=" + this.f9037l.f9066a + ",param.height=" + this.f9037l.f9067b);
        super.onLayout(z, i, i2, i3, i4);
        if (!this.f9015A) {
            this.f9035j.layout(this.f9016B, this.f9017C, this.f9016B + this.f9037l.f9066a, this.f9017C + this.f9037l.f9067b);
            this.f9015A = true;
        }
    }

    /* renamed from: a */
    public int mo24469a() {
        return this.f9035j != null ? this.f9035j.getWidth() : this.f9041p;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m10663a(int i) {
        int b = m10667b(this.f9035j.getWidth() + i);
        m10664a(b, (int) (((float) b) * this.f9043r));
    }

    /* renamed from: b */
    private int m10667b(int i) {
        if (i > this.f9042q) {
            i = this.f9042q;
        }
        if (i < this.f9041p) {
            return this.f9041p;
        }
        return i;
    }

    /* renamed from: a */
    public boolean mo24470a(MotionEvent motionEvent) {
        boolean z = false;
        if (this.f9024J) {
            return true;
        }
        switch (motionEvent.getAction()) {
            case 0:
                m10684i();
                this.f9022H = false;
                this.f9027b = motionEvent.getX();
                this.f9028c = motionEvent.getY();
                Rect rect = new Rect();
                this.f9045t.getGlobalVisibleRect(rect);
                if (!rect.contains((int) this.f9027b, (int) this.f9028c)) {
                    return false;
                }
                this.f9031f = motionEvent.getRawX();
                this.f9032g = motionEvent.getRawY();
                this.f9029d = this.f9031f;
                this.f9030e = this.f9032g;
                return true;
            case 1:
                if (m10681g()) {
                    if (this.f9038m != null) {
                        this.f9038m.mo24482b();
                    }
                } else if (this.f9038m != null) {
                    this.f9038m.mo24483c();
                }
                m10689k();
                this.f9022H = false;
                return true;
            case 2:
                m10684i();
                this.f9029d = motionEvent.getRawX();
                this.f9030e = motionEvent.getRawY();
                if (!this.f9022H) {
                    if (!m10681g()) {
                        z = true;
                    }
                    this.f9022H = z;
                    return true;
                }
                m10682h();
                return true;
            default:
                return true;
        }
    }

    /* renamed from: g */
    private boolean m10681g() {
        int scaledTouchSlop = ViewConfiguration.get(this.f9033h).getScaledTouchSlop();
        if (Math.abs(this.f9031f - this.f9029d) > ((float) scaledTouchSlop) || Math.abs(this.f9032g - this.f9030e) > ((float) scaledTouchSlop)) {
            return false;
        }
        return true;
    }

    /* renamed from: h */
    private synchronized void m10682h() {
        int i = (int) (this.f9029d - this.f9027b);
        int i2 = (int) (this.f9030e - this.f9028c);
        if (i <= (-this.f9044s)) {
            i = -this.f9044s;
        }
        if (i2 <= (-this.f9044s)) {
            i2 = -this.f9044s;
        }
        int width = this.f9039n - this.f9035j.getWidth();
        if (i >= width) {
            i = width;
        }
        int height = this.f9040o - this.f9035j.getHeight();
        if (i2 < height) {
            height = i2;
        }
        if (i >= width) {
            i = width - 1;
        }
        Log.d(f9014a, "dq updateViewPosition x=" + i + ",y=" + height);
        m10670b(i, height);
    }

    /* renamed from: b */
    private void m10670b(int i, int i2) {
        this.f9037l.f9068c = i;
        this.f9037l.f9069d = i2;
        this.f9019E = this.f9035j.getWidth() + i;
        this.f9020F = this.f9035j.getHeight() + i2;
        this.f9035j.layout(i, i2, this.f9019E, this.f9020F);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: i */
    private void m10684i() {
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: j */
    private void m10687j() {
    }

    /* renamed from: a */
    private void m10665a(int i, int i2, int i3, int i4) {
        if (this.f9034i != null) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.f9034i.getLayoutParams();
            layoutParams.setMargins(i, i2, i3, i4);
            this.f9034i.setLayoutParams(layoutParams);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: k */
    private void m10689k() {
        removeCallbacks(this.f9026L);
        postDelayed(this.f9026L, StatsManager.DEFAULT_TIMEOUT_MILLIS);
    }

    @Override // com.gbcom.gwifi.p235c.p236a.IFloatView
    /* renamed from: b */
    public FloatViewParams mo24465b() {
        this.f9037l.f9070e = mo24469a();
        return this.f9037l;
    }

    @Override // com.gbcom.gwifi.p235c.p236a.IFloatView
    /* renamed from: a */
    public void mo24463a(FloatViewListener cVar) {
        this.f9038m = cVar;
    }
}
