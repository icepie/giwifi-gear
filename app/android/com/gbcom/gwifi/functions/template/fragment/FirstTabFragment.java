package com.gbcom.gwifi.functions.template.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.gbcom.edu.functionModule.main.circle.bean.CircleUserBean;
import com.gbcom.edu.functionModule.main.manager.ISDKUser;
import com.gbcom.edu.functionModule.main.manager.SDKUserManager;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.base.activity.GBFragment;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.base.p233b.GBGlobalConfig;
import com.gbcom.gwifi.codec.EncryptUtil;
import com.gbcom.gwifi.functions.loading.MainActivity;
import com.gbcom.gwifi.functions.loading.domain.TabDatas;
import com.gbcom.gwifi.functions.template.builder.VerticalLinearLayoutBuilder;
import com.gbcom.gwifi.functions.template.builder.ViewBuilder;
import com.gbcom.gwifi.functions.template.builder.ViewBuilderFactory;
import com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView;
import com.gbcom.gwifi.functions.template.p252a.CommonGiWiFiAdClickListener;
import com.gbcom.gwifi.p221a.p226d.OkRequestHandler;
import com.gbcom.gwifi.third.umeng.analytics.GiwifiMobclickAgent;
import com.gbcom.gwifi.util.C3467s;
import com.gbcom.gwifi.util.Config;
import com.gbcom.gwifi.util.Constant;
import com.gbcom.gwifi.util.HttpUtil;
import com.gbcom.gwifi.util.Log;
import com.gbcom.gwifi.util.ResultCode;
import com.gbcom.gwifi.util.VersionUtil;
import com.gbcom.gwifi.util.cache.CacheAccount;
import com.gbcom.gwifi.util.cache.CacheAd;
import com.gbcom.gwifi.util.cache.CacheApp;
import com.gbcom.gwifi.util.cache.CacheAuth;
import com.gbcom.gwifi.util.cache.CacheWiFi;
import com.gbcom.gwifi.util.p257b.DensityUtil;
import com.gbcom.gwifi.util.p257b.ImageTools;
import com.gbcom.gwifi.util.p257b.UIUtil;
import com.gbcom.gwifi.widget.GbScrollView;
import com.gbcom.gwifi.widget.PullToRefreshView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.taobao.agoo.p359a.p360a.AbstractC5718b;
import com.umeng.message.proguard.MessageStore;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.apache.xpath.compiler.PsuedoNames;
import org.json.JSONException;
import org.json.JSONObject;
import p041c.Request;

/* renamed from: com.gbcom.gwifi.functions.template.fragment.e */
public class FirstTabFragment extends GBFragment {

    /* renamed from: d */
    private static final Object f12325d = "FirstTabFragment";

    /* renamed from: A */
    private Request f12326A;

    /* renamed from: B */
    private long f12327B = -1;

    /* renamed from: C */
    private final long f12328C = 3000;

    /* renamed from: D */
    private BroadcastReceiver f12329D = new BroadcastReceiver() {
        /* class com.gbcom.gwifi.functions.template.fragment.FirstTabFragment.C32865 */

        public void onReceive(Context context, Intent intent) {
            FirstTabFragment.this.m13502g();
            FirstTabFragment.this.m13505i();
        }
    };

    /* renamed from: a */
    public int f12330a = 1;

    /* renamed from: b */
    public long f12331b = -1;

    /* renamed from: c */
    OkRequestHandler<String> f12332c = new OkRequestHandler<String>() {
        /* class com.gbcom.gwifi.functions.template.fragment.FirstTabFragment.C32832 */

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestStart(Request abVar) {
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestFailed(Request abVar, Exception exc) {
            if (FirstTabFragment.this.isAdded()) {
                FirstTabFragment.this.f12345q.mo28398a("更新于:" + new SimpleDateFormat("MM-dd HH:mm").format(new Date(System.currentTimeMillis())));
                if (abVar == FirstTabFragment.this.f12350v) {
                    ((GBActivity) FirstTabFragment.this.getActivity()).dismissProgressDialog();
                } else if (abVar == FirstTabFragment.this.f12351w) {
                    FirstTabFragment.this.m13500f();
                }
            }
        }

        /* renamed from: a */
        public void onRequestFinish(Request abVar, String str) {
            if (FirstTabFragment.this.isAdded()) {
                FirstTabFragment.this.f12345q.mo28398a("更新于:" + new SimpleDateFormat("MM-dd HH:mm").format(new Date(System.currentTimeMillis())));
                if (abVar == FirstTabFragment.this.f12351w) {
                    FirstTabFragment.this.m13485a((FirstTabFragment) str);
                } else if (abVar == FirstTabFragment.this.f12352x) {
                    FirstTabFragment.this.m13490b((FirstTabFragment) str);
                    GBGlobalConfig.m10510c().mo24414n();
                } else if (abVar == FirstTabFragment.this.f12353y) {
                } else {
                    if (abVar == FirstTabFragment.this.f12326A) {
                        Log.m14398b("locationRequest", str);
                    } else if (abVar == FirstTabFragment.this.f12354z) {
                        FirstTabFragment.this.m13494c((FirstTabFragment) str);
                    } else {
                        try {
                            Log.m14398b("otherRequest", EncryptUtil.decrypt(URLDecoder.decode(new JSONObject(str).getString("data"), "UTF-8")));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestProgress(Request abVar, long j, long j2) {
        }
    };

    /* renamed from: e */
    private WifiManager.MulticastLock f12333e;

    /* renamed from: f */
    private LinearLayout f12334f;

    /* renamed from: g */
    private GiWiFiInfoExView f12335g;

    /* renamed from: h */
    private View f12336h;

    /* renamed from: i */
    private LinearLayout f12337i;

    /* renamed from: j */
    private ImageView f12338j;

    /* renamed from: k */
    private TextView f12339k;

    /* renamed from: l */
    private TextView f12340l;

    /* renamed from: m */
    private View f12341m;

    /* renamed from: n */
    private GbScrollView f12342n;

    /* renamed from: o */
    private LinearLayout f12343o;

    /* renamed from: p */
    private VerticalLinearLayoutBuilder f12344p;

    /* renamed from: q */
    private PullToRefreshView f12345q;

    /* renamed from: r */
    private LinearLayout f12346r;

    /* renamed from: s */
    private ImageView f12347s;

    /* renamed from: t */
    private ImageView f12348t;

    /* renamed from: u */
    private View f12349u;

    /* renamed from: v */
    private Request f12350v;

    /* renamed from: w */
    private Request f12351w;

    /* renamed from: x */
    private Request f12352x;

    /* renamed from: y */
    private Request f12353y;

    /* renamed from: z */
    private Request f12354z;

    /* renamed from: b */
    private void m13488b() {
        this.f12333e = ((WifiManager) getActivity().getApplicationContext().getSystemService("wifi")).createMulticastLock("multicast.test");
        this.f12333e.acquire();
    }

    /* renamed from: c */
    private void m13492c() {
        this.f12334f = (LinearLayout) getActivity().findViewById(C2425R.C2427id.wifi_info_layout_bg);
        this.f12335g = (GiWiFiInfoExView) getActivity().findViewById(C2425R.C2427id.wifi_info_layout);
        this.f12335g.mo27115a(new BaseGiWiFiInfoView.AbstractC3243m() {
            /* class com.gbcom.gwifi.functions.template.fragment.FirstTabFragment.C32771 */

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
                    FirstTabFragment.this.f12339k.setText(str);
                }
            }
        });
        this.f12335g.mo27109a(new BaseGiWiFiInfoView.AbstractC3237g() {
            /* class com.gbcom.gwifi.functions.template.fragment.FirstTabFragment.C32887 */

            @Override // com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView.AbstractC3237g
            /* renamed from: a */
            public void mo24475a(String str) {
                String charSequence = FirstTabFragment.this.f12339k.getText().toString();
                int lastIndexOf = charSequence.lastIndexOf(MessageStore.f23535s);
                if (lastIndexOf != -1) {
                    String substring = charSequence.substring(0, lastIndexOf);
                    if (!C3467s.m14513e(str)) {
                        substring = substring + MessageStore.f23535s + str + MessageStore.f23536t;
                    }
                    FirstTabFragment.this.f12339k.setText(substring);
                } else if (!C3467s.m14513e(str)) {
                    FirstTabFragment.this.f12339k.setText(charSequence + MessageStore.f23535s + str + MessageStore.f23536t);
                }
            }
        });
        this.f12335g.mo27112a(new BaseGiWiFiInfoView.AbstractC3240j() {
            /* class com.gbcom.gwifi.functions.template.fragment.FirstTabFragment.C32898 */

            @Override // com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView.AbstractC3240j
            /* renamed from: a */
            public void mo27255a(BaseGiWiFiInfoView.EnumC3246p pVar) {
                FirstTabFragment.this.f12335g.mo27103a(FirstTabFragment.this.f12338j, pVar);
            }
        });
        this.f12336h = this.f12341m.findViewById(C2425R.C2427id.include);
        this.f12337i = (LinearLayout) this.f12336h.findViewById(C2425R.C2427id.wifi_state_ll);
        this.f12338j = (ImageView) this.f12336h.findViewById(C2425R.C2427id.wifi_state_iv);
        this.f12337i.setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.template.fragment.FirstTabFragment.View$OnClickListenerC32909 */

            public void onClick(View view) {
                ((MainActivity) FirstTabFragment.this.getActivity()).showAndClose();
            }
        });
        this.f12335g.mo27103a(this.f12338j, BaseGiWiFiInfoView.EnumC3246p.m13296a(CacheWiFi.getInstance().getWifiControlState()));
        this.f12339k = (TextView) this.f12336h.findViewById(2131755156);
        String aw = this.f12335g.mo27171aw();
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
            this.f12339k.setText(aw);
        }
        this.f12340l = (TextView) this.f12336h.findViewById(C2425R.C2427id.title_menu);
        this.f12340l.setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.template.fragment.FirstTabFragment.View$OnClickListenerC327810 */

            public void onClick(View view) {
                UIUtil.m14204b(view);
                ((MainActivity) FirstTabFragment.this.getActivity()).showPopWindow(GBApplication.instance());
            }
        });
    }

    @Override // android.support.p009v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.f12330a = ((TabDatas.DataBean.TabListBean) arguments.get("tabListBean")).getLayout_type();
        }
        this.f12341m = layoutInflater.inflate(C2425R.layout.tp_first_fragment, viewGroup, false);
        this.f12342n = (GbScrollView) this.f12341m.findViewById(C2425R.C2427id.office_scroll);
        this.f12342n.mo28265a(new GbScrollView.AbstractC3481a() {
            /* class com.gbcom.gwifi.functions.template.fragment.FirstTabFragment.C327911 */

            @Override // com.gbcom.gwifi.widget.GbScrollView.AbstractC3481a
            /* renamed from: a */
            public void mo27271a() {
            }

            @Override // com.gbcom.gwifi.widget.GbScrollView.AbstractC3481a
            /* renamed from: b */
            public void mo27272b() {
            }
        });
        m13492c();
        this.f12343o = (LinearLayout) this.f12341m.findViewById(2131755301);
        this.f12345q = (PullToRefreshView) this.f12341m.findViewById(C2425R.C2427id.home_pull_refresh_view);
        this.f12345q.mo28396a(new PullToRefreshView.AbstractC3493a() {
            /* class com.gbcom.gwifi.functions.template.fragment.FirstTabFragment.C328012 */

            @Override // com.gbcom.gwifi.widget.PullToRefreshView.AbstractC3493a
            public void onFooterRefresh(PullToRefreshView pullToRefreshView) {
                FirstTabFragment.this.f12345q.mo28403c();
            }
        });
        this.f12346r = (LinearLayout) this.f12341m.findViewById(C2425R.C2427id.ll_tp_ad);
        this.f12348t = (ImageView) this.f12341m.findViewById(C2425R.C2427id.iv_tp_ad);
        this.f12347s = (ImageView) this.f12341m.findViewById(C2425R.C2427id.btn_tp_ad);
        this.f12347s.setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.template.fragment.FirstTabFragment.View$OnClickListenerC328113 */

            public void onClick(View view) {
                FirstTabFragment.this.f12346r.setVisibility(8);
            }
        });
        m13496d();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constant.f13238bM);
        GBApplication.instance().registerReceiver(this.f12329D, intentFilter);
        CacheApp.getInstance().setAfterAuthJumpTabType(0);
        m13498e();
        return this.f12341m;
    }

    /* renamed from: d */
    private void m13496d() {
        int a = VersionUtil.m14564a(getContext());
        if (!CacheApp.getInstance().getFirstGuide() || a != CacheApp.getInstance().getFirstGuideCode().intValue()) {
            CacheApp.getInstance().setFirstGuide(true);
            CacheApp.getInstance().setFirstGuideCode(Integer.valueOf(a));
            this.f12349u = ((ViewStub) getActivity().findViewById(C2425R.C2427id.vs_first)).inflate();
            ((ImageView) ((LinearLayout) getActivity().findViewById(C2425R.C2427id.viewstub_first)).findViewById(C2425R.C2427id.ll_first)).setOnClickListener(new View.OnClickListener() {
                /* class com.gbcom.gwifi.functions.template.fragment.FirstTabFragment.View$OnClickListenerC328214 */

                public void onClick(View view) {
                    FirstTabFragment.this.f12349u.setVisibility(8);
                }
            });
            return;
        }
        m13505i();
    }

    /* renamed from: e */
    private void m13498e() {
        this.f12351w = HttpUtil.m14302c(getContext(), this.f12330a, this.f12332c, getClass().toString());
    }

    @Override // com.gbcom.gwifi.base.activity.GBFragment, android.support.p009v4.app.Fragment
    public void onResume() {
        super.onResume();
        if (this.f12344p != null) {
            this.f12344p.mo26984b();
        }
        GiwifiMobclickAgent.onPageStart("连接界面");
    }

    @Override // com.gbcom.gwifi.base.activity.GBFragment, android.support.p009v4.app.Fragment
    public void onPause() {
        super.onPause();
        GiwifiMobclickAgent.onPageEnd("连接界面");
    }

    @Override // android.support.p009v4.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        GBApplication.instance().unregisterReceiver(this.f12329D);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m13485a(String str) {
        boolean z;
        boolean z2 = false;
        if (!C3467s.m14513e(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                int i = jSONObject.getInt(AbstractC5718b.JSON_ERRORCODE);
                String string = jSONObject.getString("resultMsg");
                Object obj = jSONObject.get("data");
                if (!ResultCode.m14475a(Integer.valueOf(i)) || obj == null || !(obj instanceof JSONObject)) {
                    if (!C3467s.m14513e(string)) {
                        GBActivity.showMessageToast(string);
                    }
                    z = false;
                } else {
                    CacheApp.getInstance().setCacheContent(this.f12330a, str);
                    m13486a((JSONObject) obj);
                    z = true;
                }
                z2 = z;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if (!z2) {
            m13500f();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: b */
    private void m13490b(String str) {
        HashMap<String, Integer> hashMap;
        int i;
        HashMap<String, Integer> hashMap2;
        int i2;
        if (GBGlobalConfig.m10510c().mo24406d(str)) {
            if (CacheAccount.getInstance().getSignAgreementState() == 1) {
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
            if (remainTimeSecondValue < 259200 && remainTimeSecondValue >= 0 && currentTimeMillis2 - this.f12331b > 86400000) {
                this.f12331b = currentTimeMillis2;
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
    /* renamed from: c */
    private void m13494c(String str) {
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
    private void m13500f() {
        String cacheContent = CacheApp.getInstance().getCacheContent(this.f12330a);
        if (!C3467s.m14513e(cacheContent)) {
            try {
                m13486a(new JSONObject(cacheContent).getJSONObject("data"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: g */
    private boolean m13502g() {
        if (System.currentTimeMillis() - this.f12327B < 3000) {
            return false;
        }
        this.f12327B = System.currentTimeMillis();
        HttpUtil.m14287a(f12325d);
        GBGlobalConfig.m10510c().mo24415o();
        m13498e();
        mo27291a();
        if (Config.m14094a().mo27812o() && !CacheAuth.getInstance().isPortal() && !CacheAuth.getInstance().isCircleLogin()) {
            m13504h();
        }
        return true;
    }

    /* renamed from: a */
    private void m13486a(JSONObject jSONObject) throws JSONException {
        jSONObject.getString("tab_title");
        int i = jSONObject.getInt("layout_id");
        jSONObject.getString("layout_wap_url");
        ViewBuilder a = ViewBuilderFactory.m12847a(i + "", this.f12330a);
        if (a != null) {
            if (a instanceof VerticalLinearLayoutBuilder) {
                this.f12344p = (VerticalLinearLayoutBuilder) a;
            }
            View a2 = a.mo26982a(getActivity(), this.f12343o, jSONObject, null);
            if (a2 != null) {
                this.f12343o.removeAllViews();
                this.f12343o.addView(a2);
                this.f12345q.mo28397a(new PullToRefreshView.AbstractC3494b() {
                    /* class com.gbcom.gwifi.functions.template.fragment.FirstTabFragment.C32843 */

                    @Override // com.gbcom.gwifi.widget.PullToRefreshView.AbstractC3494b
                    public void onHeaderRefresh(PullToRefreshView pullToRefreshView) {
                        if (FirstTabFragment.this.f12344p != null) {
                            FirstTabFragment.this.f12344p.mo26983a();
                        }
                        FirstTabFragment.this.m13502g();
                    }
                });
            }
        }
    }

    /* renamed from: h */
    private void m13504h() {
        new SDKUserManager(getActivity()).mo24032a(Constant.f13315cx, CacheAccount.getInstance().getLoginPhone(), CacheAccount.getInstance().getLoginStaticPassword(), Integer.parseInt(CacheAuth.getInstance().getOrgId()), CacheAuth.getInstance().getOrgType(), Constant.f13316cy, new ISDKUser() {
            /* class com.gbcom.gwifi.functions.template.fragment.FirstTabFragment.C32854 */

            @Override // com.gbcom.edu.functionModule.main.manager.ISDKUser
            /* renamed from: a */
            public void mo24034a(int i, CircleUserBean circleUserBean) {
                Log.m14398b("aaaa", "i:" + i);
                if (i == 200) {
                    Log.m14398b("aaaa", "login=200");
                    if (circleUserBean != null) {
                        String i2 = circleUserBean.mo23997i();
                        if (!C3467s.m14513e(i2)) {
                            CacheAccount.getInstance().setCircleAvatarUrl(i2);
                        }
                        CacheAccount.getInstance().setCircleSex(circleUserBean.mo24010s());
                        CacheAccount.getInstance().setCircleBirth(circleUserBean.mo24003l());
                        CacheAccount.getInstance().setCircleCity(circleUserBean.mo24005n());
                    }
                    CacheAuth.getInstance().setCircleLogin(true);
                    return;
                }
                Log.m14398b("aaaa", "login=fail");
            }

            @Override // com.gbcom.edu.functionModule.main.manager.ISDKUser
            /* renamed from: a */
            public void mo24033a(int i) {
                Log.m14398b("aaa", "ccccccc");
            }
        });
    }

    /* renamed from: a */
    public void mo27291a() {
        this.f12352x = HttpUtil.m14290b(GBApplication.instance(), this.f12332c, f12325d);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: i */
    private void m13505i() {
        List<HashMap<String, Object>> adByLayoutType;
        if (!(!GBGlobalConfig.f8857h || this.f12346r.getVisibility() == 0 || (adByLayoutType = CacheAd.getInstance().getAdByLayoutType(this.f12330a)) == null || adByLayoutType.size() == 0)) {
            for (HashMap<String, Object> hashMap : adByLayoutType) {
                this.f12348t.getLayoutParams().height = DensityUtil.m14127a(getContext());
                ImageTools.m14152a((String) hashMap.get("localImgUrl"), this.f12348t, GBApplication.instance().options, ImageLoader.DEALIMAGE.FILLWIDTH);
                this.f12346r.setVisibility(0);
                this.f12348t.setTag(new CommonGiWiFiAdClickListener(getContext(), hashMap, 0));
                this.f12348t.setOnClickListener(new View.OnClickListener() {
                    /* class com.gbcom.gwifi.functions.template.fragment.FirstTabFragment.View$OnClickListenerC32876 */

                    public void onClick(View view) {
                        ((CommonGiWiFiAdClickListener) view.getTag()).onClick(view);
                        FirstTabFragment.this.f12346r.setVisibility(8);
                    }
                });
            }
        }
    }

    @Override // android.support.p009v4.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (z && this.f12344p != null) {
            this.f12344p.mo26983a();
        }
    }

    @Override // android.support.p009v4.app.Fragment
    public void onSaveInstanceState(@NonNull Bundle bundle) {
    }
}
