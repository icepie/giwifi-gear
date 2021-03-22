package com.gbcom.gwifi.functions.template.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.alipay.sdk.p105a.C1459a;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.base.activity.GBFragment;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.base.p233b.GBGlobalConfig;
import com.gbcom.gwifi.codec.EncryptUtil;
import com.gbcom.gwifi.functions.loading.MainActivity;
import com.gbcom.gwifi.functions.loading.domain.TabDatas;
import com.gbcom.gwifi.functions.notify.NotifyListActivity;
import com.gbcom.gwifi.functions.notify.p245a.NotifyDao;
import com.gbcom.gwifi.functions.template.builder.VerticalLinearLayoutBuilder;
import com.gbcom.gwifi.functions.template.builder.ViewBuilder;
import com.gbcom.gwifi.functions.template.builder.ViewBuilderFactory;
import com.gbcom.gwifi.functions.template.p252a.CommonGiWiFiAdClickListener;
import com.gbcom.gwifi.p221a.p226d.OkRequestHandler;
import com.gbcom.gwifi.third.umeng.analytics.GiwifiMobclickAgent;
import com.gbcom.gwifi.third.umeng.analytics.UmengCount;
import com.gbcom.gwifi.third.zxing.CaptureActivity;
import com.gbcom.gwifi.util.C3467s;
import com.gbcom.gwifi.util.Constant;
import com.gbcom.gwifi.util.HttpUtil;
import com.gbcom.gwifi.util.Log;
import com.gbcom.gwifi.util.ResultCode;
import com.gbcom.gwifi.util.VersionUtil;
import com.gbcom.gwifi.util.cache.CacheAd;
import com.gbcom.gwifi.util.cache.CacheApp;
import com.gbcom.gwifi.util.p257b.DensityUtil;
import com.gbcom.gwifi.util.p257b.ImageTools;
import com.gbcom.gwifi.util.p257b.UIUtil;
import com.gbcom.gwifi.widget.GbScrollView;
import com.gbcom.gwifi.widget.PullToRefreshView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.taobao.agoo.p359a.p360a.AbstractC5718b;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import p041c.Request;

/* renamed from: com.gbcom.gwifi.functions.template.fragment.a */
public class EduFragment extends GBFragment {

    /* renamed from: c */
    private static final Object f12213c = "EduFragment";

    /* renamed from: v */
    private static BroadcastReceiver f12214v;

    /* renamed from: a */
    public int f12215a = 1;

    /* renamed from: b */
    OkRequestHandler<String> f12216b = new OkRequestHandler<String>() {
        /* class com.gbcom.gwifi.functions.template.fragment.EduFragment.C325011 */

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestStart(Request abVar) {
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestFailed(Request abVar, Exception exc) {
            if (EduFragment.this.isAdded()) {
                EduFragment.this.f12227n.mo28398a("更新于:" + new SimpleDateFormat("MM-dd HH:mm").format(new Date(System.currentTimeMillis())));
                if (abVar == EduFragment.this.f12232s) {
                    EduFragment.this.m13427i();
                } else {
                    if (abVar == EduFragment.this.f12233t) {
                    }
                }
            }
        }

        /* renamed from: a */
        public void onRequestFinish(Request abVar, String str) {
            if (EduFragment.this.isAdded()) {
                EduFragment.this.f12227n.mo28398a("更新于:" + new SimpleDateFormat("MM-dd HH:mm").format(new Date(System.currentTimeMillis())));
                if (abVar == EduFragment.this.f12232s) {
                    EduFragment.this.m13411a((EduFragment) str);
                } else if (abVar != EduFragment.this.f12233t) {
                    Log.m14398b("EduFragment", str);
                    Log.m14398b("EduFragment", abVar.toString());
                    try {
                        Log.m14398b("EduFragment", EncryptUtil.decrypt(URLDecoder.decode(new JSONObject(str).getString("data"), "UTF-8")));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestProgress(Request abVar, long j, long j2) {
        }
    };

    /* renamed from: d */
    private ImageView f12217d;

    /* renamed from: e */
    private ImageView f12218e;

    /* renamed from: f */
    private TextView f12219f;

    /* renamed from: g */
    private TextView f12220g;

    /* renamed from: h */
    private String f12221h;

    /* renamed from: i */
    private View f12222i;

    /* renamed from: j */
    private View f12223j;

    /* renamed from: k */
    private GbScrollView f12224k;

    /* renamed from: l */
    private LinearLayout f12225l;

    /* renamed from: m */
    private VerticalLinearLayoutBuilder f12226m;

    /* renamed from: n */
    private PullToRefreshView f12227n;

    /* renamed from: o */
    private LinearLayout f12228o;

    /* renamed from: p */
    private ImageView f12229p;

    /* renamed from: q */
    private ImageView f12230q;

    /* renamed from: r */
    private View f12231r;

    /* renamed from: s */
    private Request f12232s;

    /* renamed from: t */
    private Request f12233t;

    /* renamed from: u */
    private boolean f12234u = true;

    /* renamed from: w */
    private BroadcastReceiver f12235w = new BroadcastReceiver() {
        /* class com.gbcom.gwifi.functions.template.fragment.EduFragment.C325112 */

        public void onReceive(Context context, Intent intent) {
            EduFragment.this.m13426h();
        }
    };

    /* renamed from: x */
    private BroadcastReceiver f12236x = new BroadcastReceiver() {
        /* class com.gbcom.gwifi.functions.template.fragment.EduFragment.C32533 */

        public void onReceive(Context context, Intent intent) {
            if (EduFragment.this.getActivity() != null) {
                ((MainActivity) EduFragment.this.getActivity()).setReadMsgState();
            }
        }
    };

    /* renamed from: y */
    private BroadcastReceiver f12237y = new BroadcastReceiver() {
        /* class com.gbcom.gwifi.functions.template.fragment.EduFragment.C32544 */

        public void onReceive(Context context, Intent intent) {
            EduFragment.this.m13421f();
        }
    };

    /* renamed from: c */
    private void m13416c() {
        this.f12223j = this.f12222i.findViewById(C2425R.C2427id.include);
        this.f12217d = (ImageView) this.f12223j.findViewById(C2425R.C2427id.edu_state_iv);
        this.f12217d.setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.template.fragment.EduFragment.View$OnClickListenerC32481 */

            public void onClick(View view) {
                UmengCount.queryNotifyClick(GBApplication.instance());
                EduFragment.this.startActivity(new Intent(GBApplication.instance(), NotifyListActivity.class));
            }
        });
        this.f12218e = (ImageView) this.f12223j.findViewById(C2425R.C2427id.edu_scan_iv);
        this.f12218e.setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.template.fragment.EduFragment.View$OnClickListenerC32555 */

            public void onClick(View view) {
                Intent intent = new Intent(GBApplication.instance(), CaptureActivity.class);
                intent.setFlags(268435456);
                intent.putExtra("from", C1459a.f3177g);
                ((MainActivity) EduFragment.this.getActivity()).startActivity(intent);
                if (EduFragment.f12214v == null) {
                    IntentFilter intentFilter = new IntentFilter(GBApplication.GIWIFI_QRCODE_SCAN_FUNC_CALLBACK);
                    BroadcastReceiver unused = EduFragment.f12214v = new BroadcastReceiver() {
                        /* class com.gbcom.gwifi.functions.template.fragment.EduFragment.View$OnClickListenerC32555.C32561 */

                        public void onReceive(Context context, Intent intent) {
                            String stringExtra = intent.getStringExtra("text");
                            GBGlobalConfig.m10510c().mo24394a((GBActivity) ((MainActivity) EduFragment.this.getActivity()), stringExtra);
                        }
                    };
                    GBApplication.instance().registerReceiver(EduFragment.f12214v, intentFilter);
                }
            }
        });
        this.f12219f = (TextView) this.f12223j.findViewById(C2425R.C2427id.title_menu);
        this.f12219f.setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.template.fragment.EduFragment.View$OnClickListenerC32576 */

            public void onClick(View view) {
                UIUtil.m14204b(view);
                ((MainActivity) EduFragment.this.getActivity()).showPopWindow(GBApplication.instance());
            }
        });
        if (!C3467s.m14513e(this.f12221h)) {
            this.f12220g = (TextView) this.f12223j.findViewById(2131755156);
            this.f12220g.setText(this.f12221h);
        }
    }

    @Override // android.support.p009v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.f12221h = ((TabDatas.DataBean.TabListBean) arguments.get("tabListBean")).getTab_name();
        }
        this.f12222i = layoutInflater.inflate(C2425R.layout.fragment_edu, viewGroup, false);
        this.f12224k = (GbScrollView) this.f12222i.findViewById(C2425R.C2427id.office_scroll);
        this.f12224k.mo28265a(new GbScrollView.AbstractC3481a() {
            /* class com.gbcom.gwifi.functions.template.fragment.EduFragment.C32587 */

            @Override // com.gbcom.gwifi.widget.GbScrollView.AbstractC3481a
            /* renamed from: a */
            public void mo27271a() {
            }

            @Override // com.gbcom.gwifi.widget.GbScrollView.AbstractC3481a
            /* renamed from: b */
            public void mo27272b() {
            }
        });
        return this.f12222i;
    }

    @Override // android.support.p009v4.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        m13409a(view);
        m13418d();
        m13420e();
        super.onViewCreated(view, bundle);
    }

    /* renamed from: a */
    private void m13409a(View view) {
        m13416c();
        this.f12225l = (LinearLayout) this.f12222i.findViewById(2131755301);
        this.f12227n = (PullToRefreshView) this.f12222i.findViewById(C2425R.C2427id.home_pull_refresh_view);
        this.f12227n.mo28397a(new PullToRefreshView.AbstractC3494b() {
            /* class com.gbcom.gwifi.functions.template.fragment.EduFragment.C32598 */

            @Override // com.gbcom.gwifi.widget.PullToRefreshView.AbstractC3494b
            public void onHeaderRefresh(PullToRefreshView pullToRefreshView) {
                if (EduFragment.this.f12226m != null) {
                    EduFragment.this.f12226m.mo26983a();
                }
                HttpUtil.m14287a(EduFragment.f12213c);
                EduFragment.this.m13426h();
            }
        });
        this.f12227n.mo28396a(new PullToRefreshView.AbstractC3493a() {
            /* class com.gbcom.gwifi.functions.template.fragment.EduFragment.C32609 */

            @Override // com.gbcom.gwifi.widget.PullToRefreshView.AbstractC3493a
            public void onFooterRefresh(PullToRefreshView pullToRefreshView) {
                EduFragment.this.f12227n.mo28403c();
            }
        });
    }

    /* renamed from: d */
    private void m13418d() {
        try {
            List query = NotifyDao.m11495b().mo24212d(GBApplication.instance()).where().mo33357eq("read", false).query();
            if (query != null && query.size() > 0) {
                this.f12234u = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: e */
    private void m13420e() {
        GBApplication.instance().registerReceiver(this.f12235w, new IntentFilter(Constant.f13238bM));
        GBApplication.instance().registerReceiver(this.f12236x, new IntentFilter(Constant.f13286cH));
        GBApplication.instance().registerReceiver(this.f12237y, new IntentFilter(Constant.f13287cI));
        if (!this.f12234u) {
            m13421f();
        }
        m13426h();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: f */
    private void m13421f() {
        if (getActivity() != null) {
            ((MainActivity) getActivity()).setUnReadMsgState();
        }
    }

    /* renamed from: g */
    private void m13424g() {
        int a = VersionUtil.m14564a(getContext());
        if (!CacheApp.getInstance().getFirstGuide() || a != CacheApp.getInstance().getFirstGuideCode().intValue()) {
            CacheApp.getInstance().setFirstGuide(true);
            CacheApp.getInstance().setFirstGuideCode(Integer.valueOf(a));
            this.f12231r = ((ViewStub) getActivity().findViewById(C2425R.C2427id.vs_first)).inflate();
            ((ImageView) ((LinearLayout) getActivity().findViewById(C2425R.C2427id.viewstub_first)).findViewById(C2425R.C2427id.ll_first)).setOnClickListener(new View.OnClickListener() {
                /* class com.gbcom.gwifi.functions.template.fragment.EduFragment.View$OnClickListenerC324910 */

                public void onClick(View view) {
                    EduFragment.this.f12231r.setVisibility(8);
                }
            });
            return;
        }
        m13429j();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: h */
    private void m13426h() {
        this.f12232s = HttpUtil.m14302c(getContext(), this.f12215a, this.f12216b, getClass().toString());
    }

    @Override // com.gbcom.gwifi.base.activity.GBFragment, android.support.p009v4.app.Fragment
    public void onResume() {
        super.onResume();
        if (this.f12226m != null) {
            this.f12226m.mo26984b();
        }
        GiwifiMobclickAgent.onPageStart("智慧校园界面");
    }

    @Override // com.gbcom.gwifi.base.activity.GBFragment, android.support.p009v4.app.Fragment
    public void onPause() {
        super.onPause();
        GiwifiMobclickAgent.onPageEnd("智慧校园界面");
    }

    @Override // android.support.p009v4.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        GBApplication.instance().unregisterReceiver(this.f12235w);
        GBApplication.instance().unregisterReceiver(this.f12236x);
        GBApplication.instance().unregisterReceiver(this.f12237y);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m13411a(String str) {
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
                    CacheApp.getInstance().setCacheContent(this.f12215a, str);
                    m13412a((JSONObject) obj);
                    z = true;
                }
                z2 = z;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if (!z2) {
            m13427i();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: i */
    private void m13427i() {
        String cacheContent = CacheApp.getInstance().getCacheContent(this.f12215a);
        if (!C3467s.m14513e(cacheContent)) {
            try {
                m13412a(new JSONObject(cacheContent).getJSONObject("data"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    private void m13412a(JSONObject jSONObject) throws JSONException {
        int i = jSONObject.getInt("layout_id");
        jSONObject.getString("layout_wap_url");
        ViewBuilder a = ViewBuilderFactory.m12847a(i + "", this.f12215a);
        if (a != null) {
            if (a instanceof VerticalLinearLayoutBuilder) {
                this.f12226m = (VerticalLinearLayoutBuilder) a;
            }
            View a2 = a.mo26982a(getActivity(), this.f12225l, jSONObject, null);
            if (a2 != null) {
                this.f12225l.removeAllViews();
                this.f12225l.addView(a2);
            }
        }
    }

    /* renamed from: j */
    private void m13429j() {
        List<HashMap<String, Object>> adByLayoutType;
        if (!(!GBGlobalConfig.f8857h || this.f12228o.getVisibility() == 0 || (adByLayoutType = CacheAd.getInstance().getAdByLayoutType(this.f12215a)) == null || adByLayoutType.size() == 0)) {
            for (HashMap<String, Object> hashMap : adByLayoutType) {
                this.f12230q.getLayoutParams().height = DensityUtil.m14127a(getContext());
                ImageTools.m14152a((String) hashMap.get("localImgUrl"), this.f12230q, GBApplication.instance().options, ImageLoader.DEALIMAGE.FILLWIDTH);
                this.f12228o.setVisibility(0);
                this.f12230q.setTag(new CommonGiWiFiAdClickListener(getContext(), hashMap, 0));
                this.f12230q.setOnClickListener(new View.OnClickListener() {
                    /* class com.gbcom.gwifi.functions.template.fragment.EduFragment.View$OnClickListenerC32522 */

                    public void onClick(View view) {
                        ((CommonGiWiFiAdClickListener) view.getTag()).onClick(view);
                        EduFragment.this.f12228o.setVisibility(8);
                    }
                });
            }
        }
    }

    @Override // android.support.p009v4.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (z && this.f12226m != null) {
            this.f12226m.mo26983a();
        }
    }

    @Override // android.support.p009v4.app.Fragment
    public void onSaveInstanceState(@NonNull Bundle bundle) {
    }
}
