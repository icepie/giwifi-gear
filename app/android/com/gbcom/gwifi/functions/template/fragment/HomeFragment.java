package com.gbcom.gwifi.functions.template.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.gbcom.edu.functionModule.main.circle.activitys.CircleUserDetailActivity;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.base.activity.GBFragment;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.base.p233b.GBGlobalConfig;
import com.gbcom.gwifi.base.p234c.CallBack;
import com.gbcom.gwifi.functions.loading.MainActivity;
import com.gbcom.gwifi.functions.notify.NotifyListActivity;
import com.gbcom.gwifi.functions.notify.domain.Notify;
import com.gbcom.gwifi.functions.notify.p245a.NotifyDao;
import com.gbcom.gwifi.functions.profile.MyProfileActivity;
import com.gbcom.gwifi.functions.template.builder.OfficeModuleBuilder;
import com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView;
import com.gbcom.gwifi.functions.template.p252a.CommonGiWiFiAdClickListener;
import com.gbcom.gwifi.functions.wifi.MultiCheckNetWorkUtils;
import com.gbcom.gwifi.p221a.p226d.OkRequestHandler;
import com.gbcom.gwifi.third.umeng.analytics.GiwifiMobclickAgent;
import com.gbcom.gwifi.third.umeng.analytics.UmengCount;
import com.gbcom.gwifi.util.C3467s;
import com.gbcom.gwifi.util.Config;
import com.gbcom.gwifi.util.Constant;
import com.gbcom.gwifi.util.HttpUtil;
import com.gbcom.gwifi.util.Log;
import com.gbcom.gwifi.util.SystemUtil;
import com.gbcom.gwifi.util.cache.CacheAccount;
import com.gbcom.gwifi.util.cache.CacheAd;
import com.gbcom.gwifi.util.cache.CacheApp;
import com.gbcom.gwifi.util.cache.CacheAuth;
import com.gbcom.gwifi.util.cache.CacheWiFi;
import com.gbcom.gwifi.util.p257b.DensityUtil;
import com.gbcom.gwifi.util.p257b.ImageTools;
import com.gbcom.gwifi.util.p257b.UIUtil;
import com.gbcom.gwifi.widget.PullToRefreshView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.taobao.agoo.p359a.p360a.AbstractC5718b;
import com.umeng.socialize.common.SocializeConstants;
import com.xiaomi.stat.MiStat;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.apache.xpath.compiler.PsuedoNames;
import org.json.JSONException;
import org.json.JSONObject;
import p041c.Request;

/* renamed from: com.gbcom.gwifi.functions.template.fragment.f */
public class HomeFragment extends GBFragment implements View.OnClickListener {

    /* renamed from: d */
    private static final String f12369d = "HomeFragment";

    /* renamed from: e */
    private static final Object f12370e = f12369d;

    /* renamed from: o */
    private static final int f12371o = 14;

    /* renamed from: A */
    private View f12372A;

    /* renamed from: B */
    private LinearLayout f12373B;

    /* renamed from: C */
    private ImageView f12374C;

    /* renamed from: D */
    private TextView f12375D;

    /* renamed from: E */
    private TextView f12376E;

    /* renamed from: F */
    private View f12377F;

    /* renamed from: G */
    private ImageView f12378G;

    /* renamed from: H */
    private ImageView f12379H;

    /* renamed from: I */
    private long f12380I = -1;

    /* renamed from: J */
    private final long f12381J = 3000;

    /* renamed from: K */
    private long f12382K = -1;

    /* renamed from: L */
    private BroadcastReceiver f12383L = new BroadcastReceiver() {
        /* class com.gbcom.gwifi.functions.template.fragment.HomeFragment.C329614 */

        public void onReceive(Context context, Intent intent) {
            if (HomeFragment.this.getActivity() != null) {
                ((MainActivity) HomeFragment.this.getActivity()).setReadMsgState();
            }
        }
    };

    /* renamed from: M */
    private BroadcastReceiver f12384M = new BroadcastReceiver() {
        /* class com.gbcom.gwifi.functions.template.fragment.HomeFragment.C329715 */

        public void onReceive(Context context, Intent intent) {
            HomeFragment.this.m13566p();
            if (HomeFragment.this.f12404q != null) {
                try {
                    List query = NotifyDao.m11495b().mo24212d(GBApplication.instance()).orderBy("createTime", false).limit((Long) 1L).query();
                    if (query != null && query.size() > 0) {
                        Iterator it = query.iterator();
                        if (it.hasNext()) {
                            HomeFragment.this.f12407t = ((Notify) it.next()).getTitle();
                            HomeFragment.this.m13528a((HomeFragment) HomeFragment.this.f12407t);
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    /* renamed from: N */
    private BroadcastReceiver f12385N = new BroadcastReceiver() {
        /* class com.gbcom.gwifi.functions.template.fragment.HomeFragment.C32992 */

        public void onReceive(Context context, Intent intent) {
            HomeFragment.this.m13568q();
        }
    };

    /* renamed from: O */
    private BroadcastReceiver f12386O = new BroadcastReceiver() {
        /* class com.gbcom.gwifi.functions.template.fragment.HomeFragment.C33003 */

        public void onReceive(Context context, Intent intent) {
            HomeFragment.this.m13551i();
            HomeFragment.this.m13563o();
        }
    };

    /* renamed from: P */
    private BroadcastReceiver f12387P = new BroadcastReceiver() {
        /* class com.gbcom.gwifi.functions.template.fragment.HomeFragment.C33014 */

        public void onReceive(Context context, Intent intent) {
            HttpUtil.m14287a(HomeFragment.f12370e);
            HomeFragment.this.mo27301b();
        }
    };

    /* renamed from: Q */
    private BroadcastReceiver f12388Q = new BroadcastReceiver() {
        /* class com.gbcom.gwifi.functions.template.fragment.HomeFragment.C33025 */

        public void onReceive(Context context, Intent intent) {
        }
    };

    /* renamed from: R */
    private boolean f12389R = false;

    /* renamed from: S */
    private Handler f12390S = new Handler() {
        /* class com.gbcom.gwifi.functions.template.fragment.HomeFragment.HandlerC33058 */

        public void handleMessage(Message message) {
            switch (message.what) {
                case 14:
                    HomeFragment.this.m13568q();
                    return;
                default:
                    return;
            }
        }
    };

    /* renamed from: a */
    public long f12391a = -1;

    /* renamed from: b */
    public int f12392b = 1;

    /* renamed from: c */
    OkRequestHandler<String> f12393c = new OkRequestHandler<String>() {
        /* class com.gbcom.gwifi.functions.template.fragment.HomeFragment.C33047 */

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestStart(Request abVar) {
            HomeFragment.this.mo27303d();
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestFailed(Request abVar, Exception exc) {
            if (HomeFragment.this.isAdded()) {
                HomeFragment.this.f12399k.mo28398a("更新于:" + new SimpleDateFormat("MM-dd HH:mm").format(new Date(System.currentTimeMillis())));
                if (HomeFragment.this.f12400l == abVar) {
                    String officeModules = CacheApp.getInstance().getOfficeModules();
                    if (C3467s.m14513e(officeModules)) {
                        HomeFragment.this.mo27304e();
                        if (!HomeFragment.this.f12389R || HomeFragment.this.f12410w) {
                            HomeFragment.this.m13571s();
                        }
                        HomeFragment.this.f12410w = false;
                        return;
                    }
                    try {
                        JSONObject jSONObject = new JSONObject(officeModules);
                        if (jSONObject.has("charge_url")) {
                            String string = jSONObject.getString("charge_url");
                            HomeFragment.this.f12409v = string;
                            CacheApp.getInstance().setRechargeWebUrl(string);
                        }
                        HomeFragment.this.m13529a((HomeFragment) jSONObject);
                        HomeFragment.this.mo27305f();
                    } catch (JSONException e) {
                        Log.m14403e(HomeFragment.f12369d, "ResourceParse->parseMainPageContent():" + exc.toString());
                        exc.printStackTrace();
                        HomeFragment.this.mo27304e();
                        HomeFragment.this.f12410w = false;
                    }
                } else {
                    if (HomeFragment.this.f12401m == abVar) {
                    }
                }
            }
        }

        /* renamed from: a */
        public void onRequestFinish(Request abVar, String str) {
            if (HomeFragment.this.isAdded()) {
                HomeFragment.this.f12399k.mo28398a("更新于:" + new SimpleDateFormat("MM-dd HH:mm").format(new Date(System.currentTimeMillis())));
                if (abVar == HomeFragment.this.f12400l) {
                    HomeFragment.this.m13536c((HomeFragment) str);
                } else if (abVar == HomeFragment.this.f12401m) {
                    HomeFragment.this.m13539d((HomeFragment) str);
                    GBGlobalConfig.m10510c().mo24414n();
                } else if (abVar == HomeFragment.this.f12402n) {
                    HomeFragment.this.m13542e((HomeFragment) str);
                }
                HomeFragment.this.f12390S.removeMessages(14);
                HomeFragment.this.f12390S.sendEmptyMessage(14);
            }
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestProgress(Request abVar, long j, long j2) {
        }
    };

    /* renamed from: f */
    private String f12394f;

    /* renamed from: g */
    private ImageView f12395g;

    /* renamed from: h */
    private TextView f12396h;

    /* renamed from: i */
    private TextView f12397i;

    /* renamed from: j */
    private LinearLayout f12398j;

    /* renamed from: k */
    private PullToRefreshView f12399k;

    /* renamed from: l */
    private Request f12400l;

    /* renamed from: m */
    private Request f12401m;

    /* renamed from: n */
    private Request f12402n;

    /* renamed from: p */
    private RelativeLayout f12403p;

    /* renamed from: q */
    private TextView f12404q;

    /* renamed from: r */
    private TextView f12405r;

    /* renamed from: s */
    private boolean f12406s = true;

    /* renamed from: t */
    private String f12407t;

    /* renamed from: u */
    private Button f12408u;

    /* renamed from: v */
    private String f12409v = "";

    /* renamed from: w */
    private boolean f12410w = false;

    /* renamed from: x */
    private WifiManager f12411x;

    /* renamed from: y */
    private View f12412y;

    /* renamed from: z */
    private GiWiFiInfoHomeView f12413z;

    @Override // android.support.p009v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f12412y = layoutInflater.inflate(C2425R.layout.fragment_home, viewGroup, false);
        return this.f12412y;
    }

    @Override // android.support.p009v4.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        m13526a(view);
        m13556k();
        m13558l();
        super.onViewCreated(view, bundle);
    }

    /* renamed from: h */
    private void m13549h() {
        this.f12372A = this.f12412y.findViewById(C2425R.C2427id.include);
        this.f12373B = (LinearLayout) this.f12372A.findViewById(C2425R.C2427id.home_state_ll);
        this.f12374C = (ImageView) this.f12372A.findViewById(C2425R.C2427id.home_state_iv);
        this.f12375D = (TextView) this.f12372A.findViewById(C2425R.C2427id.home_title);
        String str = "";
        String apLocation = CacheWiFi.getInstance().getApLocation();
        if (!C3467s.m14513e(apLocation)) {
            int lastIndexOf = apLocation.lastIndexOf(PsuedoNames.PSEUDONAME_ROOT);
            if (lastIndexOf == -1) {
                str = str + apLocation;
            } else {
                str = str + apLocation.substring(lastIndexOf + 1);
            }
        }
        if (C3467s.m14513e(str)) {
            str = getResources().getString(C2425R.C2429string.wifi_name);
        }
        this.f12375D.setText(str);
        this.f12376E = (TextView) this.f12372A.findViewById(C2425R.C2427id.home_title_menu);
        this.f12376E.setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.template.fragment.HomeFragment.View$OnClickListenerC32911 */

            public void onClick(View view) {
                UIUtil.m14204b(view);
                ((MainActivity) HomeFragment.this.getActivity()).showPopWindow(GBApplication.instance());
            }
        });
    }

    /* renamed from: a */
    private void m13526a(View view) {
        m13549h();
        this.f12395g = (ImageView) view.findViewById(C2425R.C2427id.user_head);
        this.f12395g.setOnClickListener(this);
        this.f12396h = (TextView) view.findViewById(C2425R.C2427id.user_phone);
        this.f12397i = (TextView) view.findViewById(C2425R.C2427id.user_level);
        this.f12413z = (GiWiFiInfoHomeView) view.findViewById(C2425R.C2427id.home_wifi_info_layout);
        this.f12413z.mo27109a(new BaseGiWiFiInfoView.AbstractC3237g() {
            /* class com.gbcom.gwifi.functions.template.fragment.HomeFragment.C33069 */

            @Override // com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView.AbstractC3237g
            /* renamed from: a */
            public void mo24475a(String str) {
                String str2 = "";
                if (!C3467s.m14513e(str)) {
                    str2 = str2 + str;
                }
                if (C3467s.m14513e(str2)) {
                    str2 = HomeFragment.this.getResources().getString(C2425R.C2429string.wifi_name);
                }
                HomeFragment.this.f12375D.setText(str2);
            }
        });
        this.f12408u = (Button) view.findViewById(C2425R.C2427id.wifi_charge);
        this.f12408u.setOnClickListener(this);
        this.f12403p = (RelativeLayout) view.findViewById(2131755448);
        this.f12403p.setOnClickListener(this);
        this.f12404q = (TextView) view.findViewById(C2425R.C2427id.message_latest);
        this.f12398j = (LinearLayout) view.findViewById(2131755301);
        this.f12399k = (PullToRefreshView) view.findViewById(C2425R.C2427id.home_pull_refresh_view);
        this.f12399k.mo28395a(this.f12399k.getBackground());
        this.f12399k.mo28399a(true, C2425R.C2426drawable.home_pull_to_refresh);
        this.f12399k.mo28397a(new PullToRefreshView.AbstractC3494b() {
            /* class com.gbcom.gwifi.functions.template.fragment.HomeFragment.C329210 */

            @Override // com.gbcom.gwifi.widget.PullToRefreshView.AbstractC3494b
            public void onHeaderRefresh(PullToRefreshView pullToRefreshView) {
                HomeFragment.this.m13553j();
            }
        });
        this.f12399k.mo28402b(false, 0);
        CacheApp.getInstance().setAfterAuthJumpTabType(0);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: i */
    private boolean m13551i() {
        if (System.currentTimeMillis() - this.f12380I < 3000) {
            return false;
        }
        this.f12380I = System.currentTimeMillis();
        HttpUtil.m14287a(f12370e);
        GBGlobalConfig.m10510c().mo24415o();
        GBGlobalConfig.m10510c().mo24416p();
        if (System.currentTimeMillis() - this.f12382K >= 3000) {
            mo27301b();
            mo27302c();
        }
        return true;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: j */
    private void m13553j() {
        if (!m13551i()) {
            this.f12399k.mo28398a("更新于:" + new SimpleDateFormat("MM-dd HH:mm").format(new Date(System.currentTimeMillis())));
            return;
        }
        this.f12410w = true;
        GBGlobalConfig.m10510c().mo24417q();
        GBGlobalConfig.m10510c().mo24414n();
        if (getActivity() != null) {
            ((MainActivity) getActivity()).checkWifiLocation();
        }
        if (this.f12413z != null) {
            this.f12413z.mo27141aS();
        }
    }

    /* renamed from: k */
    private void m13556k() {
        try {
            List query = NotifyDao.m11495b().mo24212d(GBApplication.instance()).where().mo33357eq("read", false).query();
            if (query != null && query.size() > 0) {
                this.f12406s = false;
            }
            List query2 = NotifyDao.m11495b().mo24212d(GBApplication.instance()).orderBy("createTime", false).limit((Long) 1L).query();
            if (query2 != null && query2.size() > 0) {
                Iterator it = query2.iterator();
                if (it.hasNext()) {
                    this.f12407t = ((Notify) it.next()).getTitle();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: l */
    private void m13558l() {
        if (CacheAuth.getInstance().isPortal()) {
            this.f12382K = System.currentTimeMillis();
            mo27301b();
            mo27302c();
        } else {
            MultiCheckNetWorkUtils.m13944b(GBApplication.instance(), new CallBack() {
                /* class com.gbcom.gwifi.functions.template.fragment.HomeFragment.C329311 */

                @Override // com.gbcom.gwifi.base.p234c.CallBack
                /* renamed from: a */
                public void mo24437a(Object obj) {
                    HomeFragment.this.f12382K = System.currentTimeMillis();
                    HomeFragment.this.mo27301b();
                    HomeFragment.this.mo27302c();
                }
            });
        }
        m13559m();
        m13561n();
        GBApplication.instance().registerReceiver(this.f12386O, new IntentFilter(Constant.f13238bM));
        GBApplication.instance().registerReceiver(this.f12387P, new IntentFilter(Constant.f13233bH));
        GBApplication.instance().registerReceiver(this.f12385N, new IntentFilter(Constant.f13231bF));
        GBApplication.instance().registerReceiver(this.f12383L, new IntentFilter(Constant.f13286cH));
        GBApplication.instance().registerReceiver(this.f12384M, new IntentFilter(Constant.f13287cI));
        GBApplication.instance().registerReceiver(this.f12388Q, new IntentFilter(Constant.f13234bI));
        if (!this.f12406s) {
            m13566p();
        }
        m13528a(this.f12407t);
        m13568q();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m13528a(String str) {
        if (this.f12404q != null) {
            if (C3467s.m14513e(str)) {
                this.f12404q.setText("暂时没有消息通知");
            } else {
                this.f12404q.setText(str);
            }
        }
    }

    /* renamed from: m */
    private void m13559m() {
        this.f12377F = LayoutInflater.from(getActivity().getApplicationContext()).inflate(C2425R.layout.tp_ad, (ViewGroup) null);
        this.f12379H = (ImageView) this.f12377F.findViewById(C2425R.C2427id.iv_tp_ad);
        this.f12378G = (ImageView) this.f12377F.findViewById(C2425R.C2427id.btn_tp_ad);
        this.f12378G.setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.template.fragment.HomeFragment.View$OnClickListenerC329412 */

            public void onClick(View view) {
                HomeFragment.this.f12377F.setVisibility(8);
            }
        });
        this.f12377F.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        ((ViewGroup) getActivity().getWindow().getDecorView()).addView(this.f12377F);
    }

    /* renamed from: n */
    private void m13561n() {
        m13563o();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: o */
    private void m13563o() {
        List<HashMap<String, Object>> adByLayoutType;
        if (!(!GBGlobalConfig.f8857h || this.f12377F.getVisibility() == 0 || (adByLayoutType = CacheAd.getInstance().getAdByLayoutType(this.f12392b)) == null || adByLayoutType.size() == 0)) {
            for (HashMap<String, Object> hashMap : adByLayoutType) {
                this.f12379H.getLayoutParams().height = DensityUtil.m14127a(getContext());
                ImageTools.m14152a((String) hashMap.get("localImgUrl"), this.f12379H, GBApplication.instance().options, ImageLoader.DEALIMAGE.FILLWIDTH);
                this.f12377F.setVisibility(0);
                this.f12379H.setTag(new CommonGiWiFiAdClickListener(getContext(), hashMap, 0));
                this.f12379H.setOnClickListener(new View.OnClickListener() {
                    /* class com.gbcom.gwifi.functions.template.fragment.HomeFragment.View$OnClickListenerC329513 */

                    public void onClick(View view) {
                        ((CommonGiWiFiAdClickListener) view.getTag()).onClick(view);
                        HomeFragment.this.f12377F.setVisibility(8);
                    }
                });
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: p */
    private void m13566p() {
        if (getActivity() != null) {
            ((MainActivity) getActivity()).setUnReadMsgState();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: q */
    private void m13568q() {
        String avatarUrl = CacheAccount.getInstance().getAvatarUrl();
        if (!C3467s.m14513e(avatarUrl)) {
            ImageTools.m14150a(avatarUrl, this.f12395g, GBApplication.instance().head_options, new ImageLoadingListener() {
                /* class com.gbcom.gwifi.functions.template.fragment.HomeFragment.C329816 */

                @Override // com.nostra13.universalimageloader.core.listener.ImageLoadingListener
                public void onLoadingStarted(String str, View view) {
                }

                @Override // com.nostra13.universalimageloader.core.listener.ImageLoadingListener
                public void onLoadingFailed(String str, View view, FailReason failReason) {
                }

                @Override // com.nostra13.universalimageloader.core.listener.ImageLoadingListener
                public void onLoadingComplete(String str, View view, Bitmap bitmap) {
                    if (bitmap != null) {
                        HomeFragment.this.f12395g.setImageBitmap(ImageTools.m14165d(bitmap));
                    }
                }

                @Override // com.nostra13.universalimageloader.core.listener.ImageLoadingListener
                public void onLoadingCancelled(String str, View view) {
                }
            });
        }
        mo27299a();
        m13570r();
    }

    /* renamed from: r */
    private void m13570r() {
        if (this.f12397i != null) {
            String userLevelName = CacheAccount.getInstance().getUserLevelName();
            if (TextUtils.isEmpty(userLevelName)) {
                this.f12397i.setVisibility(8);
                return;
            }
            this.f12397i.setText(userLevelName);
            this.f12397i.setVisibility(0);
        }
    }

    /* renamed from: b */
    private void m13533b(String str) {
        if (this.f12397i != null) {
            if (TextUtils.isEmpty(str)) {
                this.f12397i.setVisibility(8);
                return;
            }
            this.f12397i.setText(str);
            this.f12397i.setVisibility(0);
        }
    }

    /* renamed from: a */
    public void mo27299a() {
        if (this.f12396h != null) {
            String loginPhone = CacheAccount.getInstance().getLoginPhone();
            if (TextUtils.isEmpty(loginPhone)) {
                this.f12396h.setVisibility(8);
                return;
            }
            if (loginPhone.length() == 11) {
                this.f12396h.setText((loginPhone.substring(0, 3) + "****") + loginPhone.substring(7));
            } else {
                this.f12396h.setText(loginPhone);
            }
            this.f12396h.setVisibility(0);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m13529a(JSONObject jSONObject) throws JSONException {
        if (!jSONObject.toString().equals(CacheApp.getInstance().getOfficeModules()) || !this.f12389R || this.f12410w) {
            this.f12410w = false;
            View a = new OfficeModuleBuilder().mo26982a(getActivity(), this.f12398j, jSONObject, null);
            if (a != null) {
                this.f12398j.removeAllViews();
                this.f12398j.addView(a);
                this.f12389R = true;
                CacheApp.getInstance().setOfficeModules(jSONObject.toString());
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: c */
    private void m13536c(String str) {
        int i;
        int userId;
        try {
            HashMap hashMap = new HashMap();
            JSONObject jSONObject = new JSONObject(str);
            int i2 = jSONObject.getInt(AbstractC5718b.JSON_ERRORCODE);
            hashMap.put(AbstractC5718b.JSON_ERRORCODE, Integer.valueOf(i2));
            if (i2 != 0) {
                mo27304e();
                if (C3467s.m14513e(CacheApp.getInstance().getOfficeModules())) {
                    m13571s();
                    return;
                }
                return;
            }
            JSONObject jSONObject2 = (JSONObject) jSONObject.get("data");
            if (!jSONObject2.has(SocializeConstants.TENCENT_UID) || (i = jSONObject2.getInt(SocializeConstants.TENCENT_UID)) == 0 || (userId = CacheAccount.getInstance().getUserId()) == 0 || i == userId) {
                if (jSONObject2.has(MiStat.UserProperty.USER_NAME)) {
                    String string = jSONObject2.getString(MiStat.UserProperty.USER_NAME);
                    if (!C3467s.m14513e(string) && CacheAccount.getInstance().getLastLoginType() == 1) {
                        String loginPhone = CacheAccount.getInstance().getLoginPhone();
                        if (!C3467s.m14513e(loginPhone) && !string.equals(loginPhone)) {
                            Log.m14398b(f12369d, "queryOfficeModule user_name=" + string + ",userName=" + loginPhone);
                            if (CacheAuth.getInstance().getAuthMethod() == 1) {
                                GBGlobalConfig.m10510c().mo24410j();
                                return;
                            }
                            return;
                        }
                    }
                }
                if (jSONObject2.has("charge_url")) {
                    String string2 = jSONObject2.getString("charge_url");
                    this.f12409v = string2;
                    CacheApp.getInstance().setRechargeWebUrl(string2);
                }
                m13529a(jSONObject2);
                mo27305f();
                return;
            }
            Log.m14398b(f12369d, "queryOfficeModule user_id=" + i + ",userId=" + userId);
            if (CacheAuth.getInstance().getAuthMethod() == 1) {
                GBGlobalConfig.m10510c().mo24410j();
            }
        } catch (JSONException e) {
            Log.m14403e(f12369d, "ResourceParse->parseMainPageContent():" + e.toString());
            e.printStackTrace();
            String officeModules = CacheApp.getInstance().getOfficeModules();
            if (C3467s.m14513e(officeModules)) {
                mo27304e();
                m13571s();
                return;
            }
            try {
                JSONObject jSONObject3 = new JSONObject(officeModules);
                if (jSONObject3.has("charge_url")) {
                    String string3 = jSONObject3.getString("charge_url");
                    this.f12409v = string3;
                    CacheApp.getInstance().setRechargeWebUrl(string3);
                }
                m13529a(jSONObject3);
                mo27305f();
            } catch (JSONException e2) {
                Log.m14403e(f12369d, "ResourceParse->parseMainPageContent():" + e2.toString());
                e2.printStackTrace();
                mo27304e();
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: d */
    private void m13539d(String str) {
        HashMap<String, Integer> hashMap;
        int i;
        HashMap<String, Integer> hashMap2;
        int i2;
        if (GBGlobalConfig.m10510c().mo24406d(str)) {
            m13533b(CacheAccount.getInstance().getUserLevelName());
            GBApplication.instance().sendBroadcast(new Intent(Constant.f13231bF));
            int signAgreementState = CacheAccount.getInstance().getSignAgreementState();
            if (!SystemUtil.m14533f() && signAgreementState == 1) {
                int currentTimeMillis = (int) (System.currentTimeMillis() / 86400000);
                HashMap<String, Integer> signAgreementTime = CacheAccount.getInstance().getSignAgreementTime();
                if (signAgreementTime == null) {
                    hashMap2 = new HashMap<>();
                } else {
                    hashMap2 = signAgreementTime;
                }
                String loginPhone = CacheAccount.getInstance().getLoginPhone();
                if (hashMap2.containsKey(loginPhone)) {
                    i2 = hashMap2.get(loginPhone).intValue();
                } else {
                    i2 = -1;
                }
                if (currentTimeMillis > i2) {
                    hashMap2.put(loginPhone, Integer.valueOf(currentTimeMillis));
                    CacheAccount.getInstance().setSignAgreementTime(hashMap2);
                    GBGlobalConfig.m10510c().mo24409i();
                }
            }
            long currentTimeMillis2 = System.currentTimeMillis();
            long remainTimeSecondValue = CacheAccount.getInstance().getRemainTimeSecondValue();
            if (remainTimeSecondValue < 259200 && remainTimeSecondValue >= 0 && currentTimeMillis2 - this.f12391a > 86400000) {
                this.f12391a = currentTimeMillis2;
                int currentTimeMillis3 = (int) (System.currentTimeMillis() / 86400000);
                HashMap<String, Integer> remainTimeSecond = CacheAccount.getInstance().getRemainTimeSecond();
                if (remainTimeSecond == null) {
                    hashMap = new HashMap<>();
                } else {
                    hashMap = remainTimeSecond;
                }
                String loginPhone2 = CacheAccount.getInstance().getLoginPhone();
                if (hashMap.containsKey(loginPhone2)) {
                    i = hashMap.get(loginPhone2).intValue();
                } else {
                    i = -1;
                }
                if (currentTimeMillis3 > i) {
                    hashMap.put(loginPhone2, Integer.valueOf(currentTimeMillis3));
                    CacheAccount.getInstance().setRemainTimeSecond(hashMap);
                    GBGlobalConfig.m10510c().mo24398a("您的套餐或试用期即将到期，是否充值或办理业务？", false);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: e */
    private void m13542e(String str) {
        try {
            if (new JSONObject(str).getInt("reasonCode") == 0) {
                CacheApp.getInstance().setReportAppNameDay(Calendar.getInstance().get(5));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: f */
    private void m13545f(String str) {
        try {
            View a = new OfficeModuleBuilder().mo26982a(getActivity(), this.f12398j, new JSONObject(str), null);
            if (a != null) {
                this.f12398j.removeAllViews();
                this.f12398j.addView(a);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: s */
    private void m13571s() {
        if (CacheAuth.getInstance().isPortal()) {
            m13545f("{\"modules\":[\n            {\"title\":\"\\u5173\\u4e8e\\u6211\\u4eec\",\"icon_url\":\"http:\\/\\/as.gwifi.com.cn\\/shop\\/\\/static\\/apphall\\/about_us.png\",\"source_type\":1,\"module_code\":\"aboutUs\",\"wap_url\":\"\"},\n            {\"title\":\"\\u624b\\u673a\\u72b6\\u6001\",\"icon_url\":\"http:\\/\\/as.gwifi.com.cn\\/shop\\/\\/static\\/apphall\\/sys_status.png\",\"source_type\":1,\"module_code\":\"sysStatus\",\"wap_url\":\"\"},\n            {\"title\":\"\\u6545\\u969c\\u68c0\\u6d4b\",\"icon_url\":\"http:\\/\\/as.gwifi.com.cn\\/shop\\/\\/static\\/apphall\\/fault_detect.png\",\"source_type\":1,\"module_code\":\"faultDetect\",\"wap_url\":\"\"},\n            {\"title\":\"Ping\\u6d4b\\u8bd5\",\"icon_url\":\"http:\\/\\/as.gwifi.com.cn\\/shop\\/\\/static\\/apphall\\/ping_test.png\",\"source_type\":1,\"module_code\":\"pingTest\",\"wap_url\":\"\"},\n            {\"title\":\"\\u5269\\u4f59\\u65f6\\u957f\",\"icon_url\":\"http:\\/\\/as.gwifi.com.cn\\/shop\\/\\/static\\/apphall\\/charge_info.png\",\"source_type\":1,\"module_code\":\"chargeInfo\",\"wap_url\":\"\"}\n            ]}");
        } else {
            MultiCheckNetWorkUtils.m13939a(GBApplication.instance(), new CallBack() {
                /* class com.gbcom.gwifi.functions.template.fragment.HomeFragment.C33036 */

                @Override // com.gbcom.gwifi.base.p234c.CallBack
                /* renamed from: a */
                public void mo24437a(Object obj) {
                    String str;
                    if (CacheAuth.getInstance().isPortal()) {
                        str = "{\"modules\":[\n            {\"title\":\"\\u5173\\u4e8e\\u6211\\u4eec\",\"icon_url\":\"http:\\/\\/as.gwifi.com.cn\\/shop\\/\\/static\\/apphall\\/about_us.png\",\"source_type\":1,\"module_code\":\"aboutUs\",\"wap_url\":\"\"},\n            {\"title\":\"\\u624b\\u673a\\u72b6\\u6001\",\"icon_url\":\"http:\\/\\/as.gwifi.com.cn\\/shop\\/\\/static\\/apphall\\/sys_status.png\",\"source_type\":1,\"module_code\":\"sysStatus\",\"wap_url\":\"\"},\n            {\"title\":\"\\u6545\\u969c\\u68c0\\u6d4b\",\"icon_url\":\"http:\\/\\/as.gwifi.com.cn\\/shop\\/\\/static\\/apphall\\/fault_detect.png\",\"source_type\":1,\"module_code\":\"faultDetect\",\"wap_url\":\"\"},\n            {\"title\":\"Ping\\u6d4b\\u8bd5\",\"icon_url\":\"http:\\/\\/as.gwifi.com.cn\\/shop\\/\\/static\\/apphall\\/ping_test.png\",\"source_type\":1,\"module_code\":\"pingTest\",\"wap_url\":\"\"},\n            {\"title\":\"\\u5269\\u4f59\\u65f6\\u957f\",\"icon_url\":\"http:\\/\\/as.gwifi.com.cn\\/shop\\/\\/static\\/apphall\\/charge_info.png\",\"source_type\":1,\"module_code\":\"chargeInfo\",\"wap_url\":\"\"}\n            ]}";
                    } else {
                        str = "{\"modules\":[\n            {\"title\":\"\\u5173\\u4e8e\\u6211\\u4eec\",\"icon_url\":\"http:\\/\\/down.gwifi.com.cn\\/subpage\\/\\/static\\/apphall\\/about_us.png\",\"source_type\":1,\"module_code\":\"aboutUs\",\"wap_url\":\"\"},\n            {\"title\":\"\\u624b\\u673a\\u72b6\\u6001\",\"icon_url\":\"http:\\/\\/down.gwifi.com.cn\\/subpage\\/\\/static\\/apphall\\/sys_status.png\",\"source_type\":1,\"module_code\":\"sysStatus\",\"wap_url\":\"\"},\n            {\"title\":\"\\u6545\\u969c\\u68c0\\u6d4b\",\"icon_url\":\"http:\\/\\/down.gwifi.com.cn\\/subpage\\/\\/static\\/apphall\\/fault_detect.png\",\"source_type\":1,\"module_code\":\"faultDetect\",\"wap_url\":\"\"},\n            {\"title\":\"Ping\\u6d4b\\u8bd5\",\"icon_url\":\"http:\\/\\/down.gwifi.com.cn\\/subpage\\/\\/static\\/apphall\\/ping_test.png\",\"source_type\":1,\"module_code\":\"pingTest\",\"wap_url\":\"\"},\n            {\"title\":\"\\u5269\\u4f59\\u65f6\\u957f\",\"icon_url\":\"http:\\/\\/down.gwifi.com.cn\\/subpage\\/\\/static\\/apphall\\/charge_info.png\",\"source_type\":1,\"module_code\":\"chargeInfo\",\"wap_url\":\"\"}\n            ]}";
                    }
                    HomeFragment.this.m13545f((HomeFragment) str);
                }
            });
        }
    }

    /* renamed from: b */
    public void mo27301b() {
        this.f12401m = HttpUtil.m14290b(GBApplication.instance(), this.f12393c, f12370e);
    }

    /* renamed from: c */
    public void mo27302c() {
        this.f12400l = HttpUtil.m14318f(getActivity(), this.f12393c, f12370e);
    }

    /* renamed from: d */
    public void mo27303d() {
    }

    /* renamed from: e */
    public void mo27304e() {
    }

    /* renamed from: f */
    public void mo27305f() {
    }

    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case C2425R.C2427id.user_head /*{ENCODED_INT: 2131755443}*/:
                if (CacheAccount.getInstance().getLoginPhone().equals("")) {
                    GBActivity.showMessageToast("对不起，请先注册会员");
                    return;
                }
                if (!Config.m14094a().mo27812o() || !Config.m14094a().mo27811n()) {
                    intent = new Intent(GBApplication.instance(), MyProfileActivity.class);
                } else {
                    intent = new Intent(GBApplication.instance(), CircleUserDetailActivity.class);
                    intent.putExtra("userName", "me");
                }
                startActivity(intent);
                return;
            case C2425R.C2427id.user_phone /*{ENCODED_INT: 2131755444}*/:
            case C2425R.C2427id.user_level /*{ENCODED_INT: 2131755445}*/:
            case C2425R.C2427id.home_wifi_info_layout /*{ENCODED_INT: 2131755447}*/:
            default:
                return;
            case C2425R.C2427id.wifi_charge /*{ENCODED_INT: 2131755446}*/:
                if (C3467s.m14513e(this.f12409v)) {
                    this.f12409v = HttpUtil.m14307c();
                }
                if (!C3467s.m14513e(this.f12409v)) {
                    GBActivity.openOfficeBackWebView(this.f12409v, "充值入口");
                    return;
                } else {
                    GBActivity.showMessageToast("暂不支持此项业务!");
                    return;
                }
            case 2131755448:
                UmengCount.queryNotifyClick(GBApplication.instance());
                startActivity(new Intent(GBApplication.instance(), NotifyListActivity.class));
                return;
        }
    }

    @Override // android.support.p009v4.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (intent != null && i == 0) {
            String string = intent.getExtras().getString("result");
            if (!C3467s.m14513e(string)) {
                GBActivity.openOfficeBackWebView(string, "");
            }
        }
    }

    @Override // com.gbcom.gwifi.base.activity.GBFragment, android.support.p009v4.app.Fragment
    public void onResume() {
        super.onResume();
        GiwifiMobclickAgent.onPageStart("上网界面");
    }

    @Override // com.gbcom.gwifi.base.activity.GBFragment, android.support.p009v4.app.Fragment
    public void onPause() {
        super.onPause();
        GiwifiMobclickAgent.onPageEnd("上网界面");
    }

    @Override // android.support.p009v4.app.Fragment
    public void onDestroy() {
        Log.m14400c(f12369d, "HomeFrgment onDestroy");
        super.onDestroy();
        GBApplication.instance().unregisterReceiver(this.f12386O);
        GBApplication.instance().unregisterReceiver(this.f12387P);
        GBApplication.instance().unregisterReceiver(this.f12385N);
        GBApplication.instance().unregisterReceiver(this.f12383L);
        GBApplication.instance().unregisterReceiver(this.f12384M);
        GBApplication.instance().unregisterReceiver(this.f12388Q);
        if (this.f12413z != null) {
            this.f12413z.mo27143aU();
        }
    }

    /* renamed from: a */
    public void mo27300a(long j) {
        if (this.f12413z != null) {
            this.f12413z.mo27101a(j);
        }
    }

    @Override // android.support.p009v4.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (z) {
        }
    }

    @Override // android.support.p009v4.app.Fragment
    public void onSaveInstanceState(@NonNull Bundle bundle) {
    }
}
