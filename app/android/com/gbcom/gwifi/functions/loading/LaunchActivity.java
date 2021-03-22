package com.gbcom.gwifi.functions.loading;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.p009v4.app.ActivityCompat;
import android.support.p009v4.app.Fragment;
import android.support.p009v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.base.p233b.GBGlobalConfig;
import com.gbcom.gwifi.codec.EncryptUtil;
import com.gbcom.gwifi.functions.template.fragment.GiWiFiInfoExView;
import com.gbcom.gwifi.functions.template.fragment.GiWiFiInfoHomeView;
import com.gbcom.gwifi.functions.template.fragment.HomeFragment;
import com.gbcom.gwifi.functions.wifi.WiFiUtil;
import com.gbcom.gwifi.p221a.p226d.OkRequestHandler;
import com.gbcom.gwifi.third.p254ad.gdt.GDTAdHelper;
import com.gbcom.gwifi.third.umeng.analytics.UmengCount;
import com.gbcom.gwifi.util.C3467s;
import com.gbcom.gwifi.util.CheckUtil;
import com.gbcom.gwifi.util.CommonMsg;
import com.gbcom.gwifi.util.Constant;
import com.gbcom.gwifi.util.HttpUtil;
import com.gbcom.gwifi.util.JsonUtil;
import com.gbcom.gwifi.util.Log;
import com.gbcom.gwifi.util.NetworkUtils;
import com.gbcom.gwifi.util.ResultCode;
import com.gbcom.gwifi.util.SystemUtil;
import com.gbcom.gwifi.util.VersionUtil;
import com.gbcom.gwifi.util.cache.CacheAccount;
import com.gbcom.gwifi.util.cache.CacheAd;
import com.gbcom.gwifi.util.cache.CacheApp;
import com.gbcom.gwifi.util.cache.CacheAuth;
import com.gbcom.gwifi.util.p257b.DensityUtil;
import com.gbcom.gwifi.util.p257b.ImageTools;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.umeng.message.MsgConstant;
import java.net.URLDecoder;
import java.p456io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.android.agoo.message.MessageService;
import p041c.Request;

public class LaunchActivity extends GBActivity implements View.OnClickListener {

    /* renamed from: c */
    private static final int f9491c = 1;

    /* renamed from: d */
    private static final String f9492d = LaunchActivity.class.getSimpleName();

    /* renamed from: e */
    private static Boolean f9493e = false;

    /* renamed from: m */
    private static final int f9494m = 1;

    /* renamed from: A */
    private LinearLayout f9495A;

    /* renamed from: B */
    private ImageView f9496B;

    /* renamed from: C */
    private Request f9497C;

    /* renamed from: D */
    private Request f9498D;

    /* renamed from: E */
    private boolean f9499E = false;

    /* renamed from: F */
    private boolean f9500F = false;

    /* renamed from: G */
    private String f9501G;

    /* renamed from: H */
    private String f9502H;

    /* renamed from: I */
    private Request f9503I;

    /* renamed from: J */
    private Request f9504J;

    /* renamed from: K */
    private boolean f9505K;

    /* renamed from: L */
    private long f9506L;

    /* renamed from: M */
    private AbstractC2671a f9507M = new AbstractC2671a() {
        /* class com.gbcom.gwifi.functions.loading.LaunchActivity.C26643 */

        @Override // com.gbcom.gwifi.functions.loading.LaunchActivity.AbstractC2671a
        /* renamed from: a */
        public void mo24920a() {
            View view;
            GiWiFiInfoHomeView giWiFiInfoHomeView;
            MainActivity currentMainActivity = GBApplication.instance().getCurrentMainActivity();
            if (currentMainActivity != null) {
                GiWiFiInfoExView wiFiInfoView = currentMainActivity.getWiFiInfoView();
                if (wiFiInfoView != null) {
                    wiFiInfoView.mo27141aS();
                }
                if (currentMainActivity.getHomeView() != null) {
                    Fragment fragment = currentMainActivity.getFragment("0");
                    if ((fragment instanceof HomeFragment) && (view = fragment.getView()) != null && (giWiFiInfoHomeView = (GiWiFiInfoHomeView) view.findViewById(C2425R.C2427id.home_wifi_info_layout)) != null) {
                        giWiFiInfoHomeView.mo27141aS();
                    }
                }
            }
        }

        @Override // com.gbcom.gwifi.functions.loading.LaunchActivity.AbstractC2671a
        /* renamed from: b */
        public void mo24921b() {
        }
    };

    /* renamed from: N */
    private HashMap<Integer, AbstractC2671a> f9508N = new HashMap<>();

    /* renamed from: O */
    private OkRequestHandler<String> f9509O = new OkRequestHandler<String>() {
        /* class com.gbcom.gwifi.functions.loading.LaunchActivity.C26654 */

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestStart(Request abVar) {
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestFailed(Request abVar, Exception exc) {
            if (abVar == LaunchActivity.this.f9497C) {
                LaunchActivity.this.m11146a(false, "", false);
            } else if (LaunchActivity.this.f9498D == abVar) {
            } else {
                if (LaunchActivity.this.f9503I == abVar) {
                    Intent intent = new Intent(LaunchActivity.this, LoginThirdActivity.class);
                    intent.setFlags(268435456);
                    intent.putExtra(Constant.f13323i, "");
                    LaunchActivity.this.startActivity(intent);
                    LaunchActivity.this.finish();
                } else if (abVar == LaunchActivity.this.f9504J) {
                    Intent intent2 = new Intent(LaunchActivity.this, MainActivity.class);
                    intent2.putExtra(Constant.f13323i, LaunchActivity.this.f9501G);
                    intent2.putExtra("staticPassword", LaunchActivity.this.f9502H);
                    LaunchActivity.this.startActivity(intent2);
                    LaunchActivity.this.finish();
                }
            }
        }

        /* renamed from: a */
        public void onRequestFinish(Request abVar, String str) {
            CommonMsg.deSerializeJson(str.getBytes());
            if (abVar == LaunchActivity.this.f9497C) {
                synchronized (LaunchActivity.f9493e) {
                    Boolean unused = LaunchActivity.f9493e = true;
                }
                LaunchActivity.this.f9512a.removeMessages(1);
                LaunchActivity.this.m11159d();
            } else if (LaunchActivity.this.f9503I == abVar) {
                LaunchActivity.this.m11145a((LaunchActivity) str);
            } else if (abVar == LaunchActivity.this.f9504J) {
                LaunchActivity.this.m11153b((LaunchActivity) str);
                GBGlobalConfig.m10510c().mo24414n();
            }
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestProgress(Request abVar, long j, long j2) {
        }
    };

    /* renamed from: P */
    private GDTAdHelper f9510P;

    /* renamed from: Q */
    private AbstractC2671a f9511Q = new AbstractC2671a() {
        /* class com.gbcom.gwifi.functions.loading.LaunchActivity.C26687 */

        @Override // com.gbcom.gwifi.functions.loading.LaunchActivity.AbstractC2671a
        /* renamed from: a */
        public void mo24920a() {
            LaunchActivity.this.f9510P.loadGDTSplashAD(LaunchActivity.this, LaunchActivity.this.f9525r, LaunchActivity.this.f9524q);
        }

        @Override // com.gbcom.gwifi.functions.loading.LaunchActivity.AbstractC2671a
        /* renamed from: b */
        public void mo24921b() {
            LaunchActivity.this.f9510P.loadGDTSplashAD(LaunchActivity.this, LaunchActivity.this.f9525r, LaunchActivity.this.f9524q);
        }
    };

    /* renamed from: a */
    Handler f9512a = new Handler() {
        /* class com.gbcom.gwifi.functions.loading.LaunchActivity.HandlerC26621 */

        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message != null) {
                switch (message.what) {
                    case 1:
                        synchronized (LaunchActivity.f9493e) {
                            if (!LaunchActivity.f9493e.booleanValue()) {
                                LaunchActivity.this.m11159d();
                            }
                        }
                        return;
                    default:
                        return;
                }
            }
        }
    };

    /* renamed from: b */
    ImageLoadingListener f9513b = new ImageLoadingListener() {
        /* class com.gbcom.gwifi.functions.loading.LaunchActivity.C26698 */

        @Override // com.nostra13.universalimageloader.core.listener.ImageLoadingListener
        public void onLoadingStarted(String str, View view) {
        }

        @Override // com.nostra13.universalimageloader.core.listener.ImageLoadingListener
        public void onLoadingFailed(String str, View view, FailReason failReason) {
            LaunchActivity.this.m11146a(false, "", false);
        }

        @Override // com.nostra13.universalimageloader.core.listener.ImageLoadingListener
        public void onLoadingComplete(String str, View view, Bitmap bitmap) {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            float min = Math.min(((float) DensityUtil.m14127a(LaunchActivity.this)) / ((float) width), ((float) (DensityUtil.m14130b(LaunchActivity.this) - LaunchActivity.this.f9495A.getMeasuredHeight())) / ((float) height));
            ViewGroup.LayoutParams layoutParams = LaunchActivity.this.f9496B.getLayoutParams();
            layoutParams.width = (int) (((float) width) * min);
            layoutParams.height = (int) (((float) height) * min);
            LaunchActivity.this.f9496B.setLayoutParams(layoutParams);
            LaunchActivity.this.f9517i.setVisibility(8);
            LaunchActivity.this.f9496B.setVisibility(0);
            if (LaunchActivity.this.f9527t != null) {
                LaunchActivity.this.f9527t.start();
            }
            LaunchActivity.this.f9496B.setOnClickListener(new View.OnClickListener() {
                /* class com.gbcom.gwifi.functions.loading.LaunchActivity.C26698.View$OnClickListenerC26701 */

                public void onClick(View view) {
                    Log.m14400c(LaunchActivity.f9492d, "click 开屏广告");
                    switch (CacheAd.getInstance().getAdStartState()) {
                        case 1:
                            LaunchActivity.this.f9531x = true;
                            LaunchActivity.this.f9532y = true;
                            if (LaunchActivity.this.f9527t != null) {
                                LaunchActivity.this.f9527t.cancel();
                                LaunchActivity.this.f9527t = null;
                            }
                            LaunchActivity.this.m11146a(false, "", false);
                            return;
                        case 2:
                            LaunchActivity.this.m11176m();
                            return;
                        default:
                            return;
                    }
                }
            });
        }

        @Override // com.nostra13.universalimageloader.core.listener.ImageLoadingListener
        public void onLoadingCancelled(String str, View view) {
        }
    };

    /* renamed from: f */
    private int f9514f = 0;

    /* renamed from: g */
    private List<ImageView> f9515g;

    /* renamed from: h */
    private int f9516h = 0;

    /* renamed from: i */
    private ImageView f9517i;

    /* renamed from: j */
    private Animation f9518j;

    /* renamed from: k */
    private Animation f9519k;

    /* renamed from: l */
    private ImageView f9520l;

    /* renamed from: n */
    private TextView f9521n;

    /* renamed from: o */
    private Animation f9522o;

    /* renamed from: p */
    private ImageView f9523p;

    /* renamed from: q */
    private LinearLayout f9524q;

    /* renamed from: r */
    private ViewGroup f9525r;

    /* renamed from: s */
    private TextView f9526s;

    /* renamed from: t */
    private CountDownTimer f9527t;

    /* renamed from: u */
    private int f9528u = 1;

    /* renamed from: v */
    private String f9529v;

    /* renamed from: w */
    private String f9530w;

    /* renamed from: x */
    private boolean f9531x = false;

    /* renamed from: y */
    private boolean f9532y = false;

    /* renamed from: z */
    private boolean f9533z = false;

    /* renamed from: com.gbcom.gwifi.functions.loading.LaunchActivity$a */
    public interface AbstractC2671a {
        /* renamed from: a */
        void mo24920a();

        /* renamed from: b */
        void mo24921b();
    }

    @Override // android.support.p009v4.app.BaseFragmentActivityGingerbread, com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        setActivityName("启动界面");
        super.onCreate(bundle);
        if (GBApplication.instance().isStarted()) {
            GBApplication.instance().showCurrentActivity(this);
            finish();
            return;
        }
        setContentView(C2425R.layout.launch_activity);
        m11167h();
        this.f9522o = AnimationUtils.loadAnimation(this, C2425R.anim.alpha);
        this.f9522o.setAnimationListener(new Animation.AnimationListener() {
            /* class com.gbcom.gwifi.functions.loading.LaunchActivity.animationAnimation$AnimationListenerC26632 */

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                LaunchActivity.this.m11156c();
            }

            public void onAnimationRepeat(Animation animation) {
            }
        });
        this.f9517i.startAnimation(this.f9522o);
        GBGlobalConfig.m10510c().mo24396a(f9492d, this);
        GBGlobalConfig.m10510c().mo24403b(f9492d, this);
        this.f9506L = System.currentTimeMillis();
        if (!(VersionUtil.m14564a(this) == CacheApp.getInstance().getFirstGuideCode().intValue() || CacheApp.getInstance().getFirstGuideCode().intValue() == 1)) {
            CacheApp.getInstance().setQuanziGuide(false);
            CacheApp.getInstance().setFirstGuide(false);
            CacheApp.getInstance().setSecondGuide(false);
        }
        requestLocationPermission();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: c */
    private void m11156c() {
        if (!NetworkUtils.m14426a(this) || CacheAccount.getInstance().getUserLevel() == 3) {
            m11146a(false, "", false);
            return;
        }
        Log.m14398b(f9492d, "adStartState:" + CacheAd.getInstance().getAdStartState());
        if (CacheAuth.getInstance().isPortal()) {
            Log.m14398b(f9492d, "no need requestRelease");
            m11159d();
        } else if (m11163f()) {
            Log.m14398b(f9492d, "requestRelease succeed");
            this.f9512a.sendEmptyMessageDelayed(1, 3000);
        } else {
            Log.m14398b(f9492d, "requestRelease fail");
            m11159d();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: d */
    private void m11159d() {
        switch (CacheAd.getInstance().getAdStartState()) {
            case 2:
                m11175l();
                this.f9521n.setVisibility(0);
                return;
            case 3:
                m11146a(false, "", false);
                return;
            case 4:
                m11146a(false, "", false);
                return;
            case 5:
                if (SystemUtil.m14533f()) {
                    m11146a(false, "", false);
                    return;
                }
                m11173k();
                this.f9521n.setVisibility(0);
                return;
            case 6:
            default:
                m11146a(false, "", false);
                return;
            case 7:
                m11146a(false, "", false);
                return;
        }
    }

    public void requestLocationPermission() {
        requestPermissions(this, new String[]{MsgConstant.PERMISSION_ACCESS_WIFI_STATE, "android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"}, 1000, this.f9507M);
    }

    /* renamed from: a */
    private boolean m11147a(Context context, String[] strArr) {
        for (String str : strArr) {
            if (ContextCompat.checkSelfPermission(context, str) == -1) {
                return false;
            }
        }
        return true;
    }

    public void requestPermissions(Context context, String[] strArr, int i, AbstractC2671a aVar) {
        this.f9508N.put(Integer.valueOf(i), aVar);
        if (!m11147a(context, strArr)) {
            ActivityCompat.requestPermissions((Activity) context, strArr, i);
        } else {
            aVar.mo24920a();
        }
    }

    /* renamed from: a */
    private boolean m11149a(int[] iArr) {
        for (int i : iArr) {
            if (i == -1) {
                return false;
            }
        }
        return true;
    }

    @Override // com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.ActivityCompat.OnRequestPermissionsResultCallback, android.support.p009v4.app.FragmentActivity
    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        AbstractC2671a aVar;
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (!this.f9508N.containsKey(Integer.valueOf(i)) || (aVar = this.f9508N.get(Integer.valueOf(i))) == null) {
            return;
        }
        if (m11149a(iArr)) {
            aVar.mo24920a();
        } else {
            aVar.mo24921b();
        }
    }

    /* renamed from: e */
    private void m11161e() {
        if (this.f9505K) {
            m11146a(false, "", false);
        } else {
            this.f9505K = true;
        }
    }

    /* renamed from: f */
    private boolean m11163f() {
        if (CacheAd.getInstance().getAdAccessStrategy() != 1) {
            return false;
        }
        if (!WiFiUtil.m14022a(GBApplication.instance()).mo27621o()) {
            return false;
        }
        WifiInfo connectionInfo = ((WifiManager) getApplicationContext().getSystemService("wifi")).getConnectionInfo();
        if (connectionInfo == null) {
            return false;
        }
        if (!NetworkUtils.m14430b(connectionInfo.getBSSID())) {
            return false;
        }
        String localMac = CacheAuth.getInstance().getLocalMac();
        String j = WiFiUtil.m14022a(GBApplication.instance()).mo27616j();
        if (localMac == null || j == null) {
            return false;
        }
        this.f9497C = HttpUtil.m14267a(this.f9509O, (Integer) 50, (Integer) 1024, (Integer) 4096, (Integer) 1, (Object) "");
        return true;
    }

    /* renamed from: g */
    private void m11165g() {
        Intent intent = new Intent(this, LoginThirdActivity.class);
        intent.setFlags(268435456);
        intent.putExtra(Constant.f13323i, "");
        startActivity(intent);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m11145a(String str) {
        CommonMsg deSerializeJson = CommonMsg.deSerializeJson(str.getBytes());
        if (!ResultCode.m14476b(deSerializeJson)) {
            if (deSerializeJson != null) {
                String data = deSerializeJson.getData();
                if (!C3467s.m14513e(data)) {
                    HashMap hashMap = (HashMap) JsonUtil.m14386a(data, HashMap.class);
                    String obj = hashMap.get("dpTipContent") != null ? hashMap.get("dpTipContent").toString() : "";
                    if (!C3467s.m14513e(obj)) {
                        CacheApp.getInstance().setAppDptipShow(true);
                        CacheApp.getInstance().setAppDptipContent(obj);
                    }
                }
            }
            m11165g();
            return;
        }
        String data2 = deSerializeJson.getData();
        if (C3467s.m14513e(data2)) {
            m11165g();
            return;
        }
        try {
            HashMap hashMap2 = (HashMap) JsonUtil.m14386a(URLDecoder.decode(data2, "UTF-8"), HashMap.class);
            String obj2 = hashMap2.get("dpTipContent") != null ? hashMap2.get("dpTipContent").toString() : "";
            if (!C3467s.m14513e(obj2)) {
                CacheApp.getInstance().setAppDptipShow(true);
                CacheApp.getInstance().setAppDptipContent(obj2);
            }
            String obj3 = hashMap2.get("field1") != null ? hashMap2.get("field1").toString() : "";
            if (C3467s.m14513e(obj3)) {
                m11165g();
                return;
            }
            String obj4 = hashMap2.get("field2") != null ? hashMap2.get("field2").toString() : "";
            if (C3467s.m14513e(obj4)) {
                m11165g();
                return;
            }
            String decrypt = EncryptUtil.decrypt(obj3);
            if (C3467s.m14513e(decrypt)) {
                m11165g();
            } else if (!CheckUtil.m14086d(decrypt)) {
                m11165g();
            } else {
                String decrypt2 = EncryptUtil.decrypt(obj4);
                if (C3467s.m14513e(decrypt2)) {
                    m11165g();
                    return;
                }
                this.f9501G = decrypt;
                this.f9502H = decrypt2;
                CacheAccount.getInstance().setLastLoginType(1);
                CacheAccount.getInstance().setLoginPhone(this.f9501G);
                CacheAccount.getInstance().setLoginStaticPassword(this.f9502H);
                CacheAccount.getInstance().setLastLoginPhone(this.f9501G);
                CacheAccount.getInstance().resetCacheAccountBean();
                CacheAccount.getInstance().setLoginServiceType(1);
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra(Constant.f13323i, this.f9501G);
                intent.putExtra("staticPassword", this.f9502H);
                startActivity(intent);
                finish();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            m11165g();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: b */
    private void m11153b(String str) {
        int i;
        int i2;
        int i3 = 0;
        CommonMsg deSerializeJson = CommonMsg.deSerializeJson(str.getBytes());
        if (!GBActivity.dealException(deSerializeJson)) {
            HashMap hashMap = (HashMap) JsonUtil.m14386a(deSerializeJson.getData(), HashMap.class);
            int intValue = ((Integer) hashMap.get("uid")).intValue();
            String str2 = (String) hashMap.get("avatarUrl");
            if (TextUtils.isEmpty(str2)) {
                str2 = "";
            }
            Integer valueOf = Integer.valueOf(hashMap.get("hotspot_group_id") != null ? Integer.parseInt(hashMap.get("hotspot_group_id").toString()) : 0);
            if (hashMap.get("college_id") != null) {
                i = ((Integer) hashMap.get("college_id")).intValue();
            } else {
                i = 0;
            }
            if (hashMap.get("identity_type") != null) {
                i2 = ((Integer) hashMap.get("identity_type")).intValue();
            } else {
                i2 = 0;
            }
            if (hashMap.get("department_id") != null) {
                i3 = ((Integer) hashMap.get("department_id")).intValue();
            }
            CacheAccount.getInstance().setCacheAccountBean(intValue, str2, valueOf.intValue(), i, i3, i2);
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(Constant.f13323i, this.f9501G);
            intent.putExtra("staticPassword", this.f9502H);
            startActivity(intent);
            finish();
        }
    }

    /* renamed from: h */
    private void m11167h() {
        this.f9495A = (LinearLayout) findViewById(C2425R.C2427id.bottom_ll);
        this.f9517i = (ImageView) findViewById(C2425R.C2427id.launch_img);
        this.f9496B = (ImageView) findViewById(C2425R.C2427id.launch_ad_img);
        this.f9521n = (TextView) findViewById(C2425R.C2427id.countdown);
        this.f9524q = (LinearLayout) findViewById(C2425R.C2427id.adparent);
        this.f9526s = (TextView) findViewById(C2425R.C2427id.jump_tv);
        this.f9528u = CacheAd.getInstance().getAdJumpIgnore();
        Log.m14398b(f9492d, "jumpIgnore:" + this.f9528u);
        if (this.f9528u != 1) {
            this.f9524q.setEnabled(false);
        } else {
            this.f9524q.setEnabled(true);
        }
        this.f9524q.setOnClickListener(this);
        this.f9525r = (ViewGroup) findViewById(C2425R.C2427id.ad_layout);
        TextView textView = (TextView) findViewById(C2425R.C2427id.launch_version);
        if (textView != null) {
            textView.setText(VersionUtil.m14565a());
        }
    }

    /* renamed from: a */
    private void m11141a(int i) {
        long j = 5000;
        switch (i) {
            case 2:
                this.f9521n.setVisibility(0);
                j = (long) (CacheAd.getInstance().getAdShowTime() * 1000);
                break;
        }
        this.f9527t = new CountDownTimer(j, 500) {
            /* class com.gbcom.gwifi.functions.loading.LaunchActivity.CountDownTimerC26665 */

            public void onTick(long j) {
                long j2 = j / 1000;
                Log.m14398b("millisUntilFinished", j + "---------");
                LaunchActivity.this.f9524q.setBackgroundResource(C2425R.C2426drawable.gi_bg_launch_corner);
                if (LaunchActivity.this.f9528u != 1) {
                    LaunchActivity.this.f9526s.setVisibility(8);
                }
                LaunchActivity.this.f9521n.setText(j2 + "s");
            }

            public void onFinish() {
                LaunchActivity.this.m11146a(false, "", false);
            }
        };
    }

    /* renamed from: i */
    private void m11169i() {
        this.f9510P = new GDTAdHelper();
        this.f9510P.registerViewSplashADListener(new GDTAdHelper.ViewSplashADListener() {
            /* class com.gbcom.gwifi.functions.loading.LaunchActivity.C26676 */

            @Override // com.gbcom.gwifi.third.p254ad.gdt.GDTAdHelper.ViewSplashADListener
            public void onADDismissed() {
                if (LaunchActivity.this.f9500F) {
                    LaunchActivity.this.m11146a(false, "", false);
                } else {
                    LaunchActivity.this.f9500F = true;
                }
            }

            @Override // com.gbcom.gwifi.third.p254ad.gdt.GDTAdHelper.ViewSplashADListener
            public void onNoAD() {
                LaunchActivity.this.m11146a(false, "", false);
            }

            @Override // com.gbcom.gwifi.third.p254ad.gdt.GDTAdHelper.ViewSplashADListener
            public void onADClicked() {
                LaunchActivity.this.f9531x = true;
            }

            @Override // com.gbcom.gwifi.third.p254ad.gdt.GDTAdHelper.ViewSplashADListener
            public void onADTick(long j) {
                long j2 = j / 1000;
                Log.m14398b(LaunchActivity.f9492d, "millisUntilFinished=" + j + "---------");
                if (j2 <= 2) {
                    LaunchActivity.this.f9524q.setBackgroundResource(C2425R.C2426drawable.gi_bg_launch_corner);
                    if (LaunchActivity.this.f9528u != 1) {
                        LaunchActivity.this.f9526s.setVisibility(8);
                    }
                    LaunchActivity.this.f9521n.setText(j2 + "s");
                }
            }
        });
    }

    @TargetApi(23)
    /* renamed from: j */
    private void m11171j() {
        ArrayList arrayList = new ArrayList();
        if (checkSelfPermission(MsgConstant.PERMISSION_READ_PHONE_STATE) != 0) {
            arrayList.add(MsgConstant.PERMISSION_READ_PHONE_STATE);
        }
        if (checkSelfPermission("android.permission.ACCESS_FINE_LOCATION") != 0) {
            arrayList.add("android.permission.ACCESS_FINE_LOCATION");
        }
        if (checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE") != 0) {
            arrayList.add("android.permission.READ_EXTERNAL_STORAGE");
        }
        if (checkSelfPermission(MsgConstant.PERMISSION_WRITE_EXTERNAL_STORAGE) != 0) {
            arrayList.add(MsgConstant.PERMISSION_WRITE_EXTERNAL_STORAGE);
        }
        if (arrayList.size() == 0) {
            this.f9510P.loadGDTSplashAD(this, this.f9525r, this.f9524q);
            return;
        }
        String[] strArr = new String[arrayList.size()];
        arrayList.toArray(strArr);
        requestPermissions(this, strArr, 1024, this.f9511Q);
    }

    /* renamed from: k */
    private void m11173k() {
        if (this.f9521n != null) {
            this.f9521n.setVisibility(0);
        }
        if (this.f9525r != null) {
            this.f9525r.setVisibility(0);
        }
        if (this.f9524q != null) {
            this.f9524q.setVisibility(0);
        }
        if (this.f9510P == null) {
            m11169i();
        }
        if (Build.VERSION.SDK_INT >= 23) {
            m11171j();
        } else {
            this.f9510P.loadGDTSplashAD(this, this.f9525r, this.f9524q);
        }
    }

    /* renamed from: l */
    private void m11175l() {
        if (this.f9524q != null) {
            this.f9524q.setVisibility(0);
        }
        m11141a(2);
        this.f9529v = CacheAd.getInstance().getAdImgUrl();
        this.f9530w = CacheAd.getInstance().getAdWebUrl();
        if (!C3467s.m14513e(this.f9529v)) {
            ImageTools.m14153a(this.f9529v, this.f9496B, this.f9513b);
        } else {
            m11146a(false, "", false);
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.base.activity.GBActivity
    public int customTitleType() {
        return 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: m */
    private void m11176m() {
        switch (CacheAd.getInstance().getAdSourceType()) {
            case 1:
                this.f9533z = true;
                UmengCount.queryCustomAdCount(GBApplication.instance(), "产品", String.valueOf(CacheAd.getInstance().getAdProductId()));
                this.f9531x = true;
                m11146a(false, "", false);
                return;
            case 2:
                this.f9531x = true;
                UmengCount.queryCustomAdCount(GBApplication.instance(), "网页", this.f9530w);
                if (this.f9527t != null) {
                    this.f9527t.cancel();
                    this.f9527t = null;
                }
                m11146a(false, this.f9530w, false);
                return;
            case 3:
                UmengCount.queryCustomAdCount(GBApplication.instance(), "点游", "");
                if (this.f9527t != null) {
                    this.f9527t.cancel();
                    this.f9527t = null;
                }
                m11146a(false, "", true);
                return;
            case 4:
                m11146a(false, "", false);
                return;
            default:
                m11146a(false, "", false);
                return;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m11146a(boolean z, String str, boolean z2) {
        if (!z) {
            if (this.f9531x) {
                UmengCount.queryLauncherScreenAd(GBApplication.instance(), "开屏广告");
                this.f9531x = false;
            }
            if (!(CacheAd.getInstance().getAdStartState() == 2 && CacheAd.getInstance().getAdSourceType() == 2)) {
                str = "";
            }
            CacheApp.getInstance().setAppDptipShow(false);
            String loginPhone = CacheAccount.getInstance().getLoginPhone();
            String loginStaticPassword = CacheAccount.getInstance().getLoginStaticPassword();
            if (loginPhone.equals("") || loginStaticPassword.equals("")) {
                SharedPreferences sharedPreferences = GBApplication.instance().getSharedPreferences();
                String string = sharedPreferences.getString(Constant.f13323i, "");
                String string2 = sharedPreferences.getString("user_static_password", "");
                if (string.equals("") || string2.equals("")) {
                    int lastLoginType = CacheAccount.getInstance().getLastLoginType();
                    String lastLoginPhone = CacheAccount.getInstance().getLastLoginPhone();
                    if (lastLoginType == 2) {
                        Intent intent = new Intent(this, StatusLoginActivity.class);
                        intent.putExtra(Constant.f13323i, lastLoginPhone);
                        startActivity(intent);
                        finish();
                    } else if (lastLoginType == 1) {
                        Intent intent2 = new Intent(this, LoginThirdActivity.class);
                        intent2.setFlags(268435456);
                        intent2.putExtra(Constant.f13323i, lastLoginPhone);
                        startActivity(intent2);
                        finish();
                    } else if (!SystemUtil.m14531e()) {
                        Intent intent3 = new Intent(this, LoginThirdActivity.class);
                        intent3.setFlags(268435456);
                        intent3.putExtra(Constant.f13323i, lastLoginPhone);
                        startActivity(intent3);
                        finish();
                    } else {
                        String localMac = CacheAuth.getInstance().getLocalMac();
                        if (C3467s.m14513e(localMac)) {
                            localMac = WiFiUtil.m14035e(GBApplication.instance().getApplicationContext());
                            CacheAuth.getInstance().setLocalMac(localMac);
                        }
                        String c = SystemUtil.m14527c(this);
                        String f = SystemUtil.m14532f(this);
                        if (C3467s.m14513e(localMac) && C3467s.m14513e(c) && C3467s.m14513e(f)) {
                            Intent intent4 = new Intent(this, LoginThirdActivity.class);
                            intent4.setFlags(268435456);
                            intent4.putExtra(Constant.f13323i, lastLoginPhone);
                            startActivity(intent4);
                            finish();
                        } else if (SystemUtil.m14533f()) {
                            Intent intent5 = new Intent(this, LoginThirdActivity.class);
                            intent5.setFlags(268435456);
                            intent5.putExtra(Constant.f13323i, lastLoginPhone);
                            startActivity(intent5);
                            finish();
                        } else {
                            this.f9503I = HttpUtil.m14274a(localMac, c, f, this.f9509O, "LaunchActivity");
                        }
                    }
                } else {
                    CacheAccount.getInstance().setLoginPhone(string);
                    CacheAccount.getInstance().setLoginStaticPassword(string2);
                    CacheAccount.getInstance().setLastLoginPhone(string);
                    String string3 = sharedPreferences.getString("last_login_type", "");
                    if (!string3.isEmpty()) {
                        if (string3.equals("1")) {
                            CacheAccount.getInstance().setLastLoginType(1);
                        } else if (string3.equals(MessageService.MSG_DB_NOTIFY_CLICK)) {
                            CacheAccount.getInstance().setLastLoginType(2);
                        }
                    }
                    Intent intent6 = new Intent(this, MainActivity.class);
                    intent6.setFlags(268435456);
                    intent6.putExtra(Constant.f13323i, loginPhone);
                    intent6.putExtra("webUrl", str);
                    intent6.putExtra("isClickJesgooAd", this.f9532y);
                    intent6.putExtra("isAdProductType", this.f9533z);
                    intent6.putExtra("staticPassword", loginStaticPassword);
                    intent6.putExtra("openDY", z2);
                    startActivity(intent6);
                    finish();
                }
            } else {
                Intent intent7 = new Intent(this, MainActivity.class);
                intent7.setFlags(268435456);
                intent7.putExtra(Constant.f13323i, loginPhone);
                intent7.putExtra("webUrl", str);
                intent7.putExtra("isClickJesgooAd", this.f9532y);
                intent7.putExtra("isAdProductType", this.f9533z);
                intent7.putExtra("staticPassword", loginStaticPassword);
                intent7.putExtra("openDY", z2);
                startActivity(intent7);
                finish();
            }
        }
    }

    @Override // com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onDestroy() {
        super.onDestroy();
        this.f9522o = null;
        if (this.f9517i != null) {
            this.f9517i.clearAnimation();
        }
        System.m38963gc();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C2425R.C2427id.adparent /*{ENCODED_INT: 2131755619}*/:
                m11179n();
                return;
            default:
                return;
        }
    }

    /* renamed from: n */
    private void m11179n() {
        if (this.f9528u == 1) {
            if (this.f9527t != null) {
                this.f9527t.cancel();
                this.f9527t = null;
            }
            m11146a(false, "", false);
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onPause() {
        super.onPause();
        Log.m14400c("RSplashActivity", "onPause");
        this.f9499E = false;
        this.f9500F = false;
        this.f9505K = false;
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onResume() {
        super.onResume();
        Log.m14400c("RSplashActivity", "onResume");
        switch (CacheAd.getInstance().getAdStartState()) {
            case 3:
                if (this.f9499E) {
                    this.f9499E = false;
                    this.f9528u = 1;
                    m11179n();
                    return;
                }
                this.f9499E = true;
                UmengCount.queryStartApp(GBApplication.instance());
                return;
            case 4:
            case 6:
            default:
                return;
            case 5:
                if (this.f9500F) {
                    m11146a(false, "", false);
                    return;
                }
                this.f9500F = true;
                UmengCount.queryStartApp(GBApplication.instance());
                return;
            case 7:
                m11161e();
                return;
        }
    }
}
