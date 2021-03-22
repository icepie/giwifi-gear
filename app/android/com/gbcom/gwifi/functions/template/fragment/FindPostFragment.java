package com.gbcom.gwifi.functions.template.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.HandlerThread;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.base.activity.GBFragment;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.functions.template.builder.OnDestoryBuilder;
import com.gbcom.gwifi.functions.template.builder.SchoolNotifyBuilder;
import com.gbcom.gwifi.functions.template.builder.VerticalLinearLayoutBuilder;
import com.gbcom.gwifi.functions.template.builder.ViewBuilder;
import com.gbcom.gwifi.functions.template.builder.ViewBuilderFactory;
import com.gbcom.gwifi.p221a.p226d.OkRequestHandler;
import com.gbcom.gwifi.third.umeng.analytics.GiwifiMobclickAgent;
import com.gbcom.gwifi.util.C3467s;
import com.gbcom.gwifi.util.Constant;
import com.gbcom.gwifi.util.HttpUtil;
import com.gbcom.gwifi.util.ResultCode;
import com.gbcom.gwifi.util.cache.CacheApp;
import com.gbcom.gwifi.widget.GbScrollView;
import com.gbcom.gwifi.widget.LoadingView;
import com.gbcom.gwifi.widget.PullToRefreshView;
import com.taobao.agoo.p359a.p360a.AbstractC5718b;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.xalan.templates.Constants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p041c.Request;

/* renamed from: com.gbcom.gwifi.functions.template.fragment.c */
public class FindPostFragment extends GBFragment implements OnDestoryBuilder {

    /* renamed from: a */
    public static final int f12262a = 3;

    /* renamed from: d */
    private static final Object f12263d = "FindPostFragment";

    /* renamed from: b */
    OkRequestHandler<String> f12264b = new OkRequestHandler<String>() {
        /* class com.gbcom.gwifi.functions.template.fragment.FindPostFragment.C32674 */

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestFailed(Request abVar, Exception exc) {
            if (FindPostFragment.this.isAdded() && GBApplication.instance().getCurrentActivity() != null) {
                GBApplication.instance().getCurrentActivity().dismissProgressDialog();
                FindPostFragment.this.f12268g.mo28398a("更新于:" + FindPostFragment.this.f12267f.format(new Date(System.currentTimeMillis())));
                FindPostFragment.this.mo27279c();
                FindPostFragment.this.m13459g();
            }
        }

        /* renamed from: a */
        public void onRequestFinish(Request abVar, String str) {
            boolean z;
            if (FindPostFragment.this.isAdded() && GBApplication.instance().getCurrentActivity() != null) {
                GBApplication.instance().getCurrentActivity().dismissProgressDialog();
                FindPostFragment.this.f12268g.mo28398a("更新于:" + FindPostFragment.this.f12267f.format(new Date(System.currentTimeMillis())));
                if (FindPostFragment.this.f12275n == abVar) {
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
                                CacheApp.getInstance().setCacheContent(3, str);
                                FindPostFragment.this.m13451a((FindPostFragment) ((JSONObject) obj));
                                z = true;
                            }
                            z2 = z;
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {
                        GBActivity.showMessageToast("请求失败");
                    }
                    if (!z2) {
                        FindPostFragment.this.mo27279c();
                        FindPostFragment.this.m13459g();
                    }
                }
            }
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestProgress(Request abVar, long j, long j2) {
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestStart(Request abVar) {
            FindPostFragment.this.mo27278b();
            if (FindPostFragment.this.f12270i.getVisibility() == 8 && abVar == FindPostFragment.this.f12275n) {
                ((GBActivity) FindPostFragment.this.getActivity()).showProgressDialog("正在加载...");
            }
        }
    };

    /* renamed from: c */
    private View f12265c;

    /* renamed from: e */
    private GbScrollView f12266e;

    /* renamed from: f */
    private SimpleDateFormat f12267f;

    /* renamed from: g */
    private PullToRefreshView f12268g;

    /* renamed from: h */
    private LinearLayout f12269h;

    /* renamed from: i */
    private LoadingView f12270i;

    /* renamed from: j */
    private VerticalLinearLayoutBuilder f12271j;

    /* renamed from: k */
    private SchoolNotifyBuilder f12272k;

    /* renamed from: l */
    private boolean f12273l;

    /* renamed from: m */
    private boolean f12274m;

    /* renamed from: n */
    private Request f12275n;

    /* renamed from: o */
    private BroadcastReceiver f12276o = new BroadcastReceiver() {
        /* class com.gbcom.gwifi.functions.template.fragment.FindPostFragment.C32663 */

        public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getAction().equals(Constant.f13238bM)) {
                m13467a();
            }
        }

        /* renamed from: a */
        private void m13467a() {
            FindPostFragment.this.f12275n = HttpUtil.m14302c(GBApplication.instance(), 3, FindPostFragment.this.f12264b, FindPostFragment.f12263d);
        }
    };

    /* renamed from: p */
    private LoadingView.AbstractC3488a f12277p = new LoadingView.AbstractC3488a() {
        /* class com.gbcom.gwifi.functions.template.fragment.FindPostFragment.C32696 */

        @Override // com.gbcom.gwifi.widget.LoadingView.AbstractC3488a
        /* renamed from: a */
        public void mo24655a(View view) {
            HttpUtil.m14287a(FindPostFragment.f12263d);
            FindPostFragment.this.m13458f();
        }
    };

    @Override // android.support.p009v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(C2425R.layout.fragment_post, viewGroup, false);
    }

    @Override // android.support.p009v4.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f12269h = (LinearLayout) view.findViewById(2131755301);
        this.f12266e = (GbScrollView) view.findViewById(C2425R.C2427id.home_scroll);
        this.f12267f = new SimpleDateFormat("MM-dd HH:mm");
        this.f12268g = (PullToRefreshView) view.findViewById(C2425R.C2427id.home_pull_refresh_view);
        this.f12268g.mo28396a(new PullToRefreshView.AbstractC3493a() {
            /* class com.gbcom.gwifi.functions.template.fragment.FindPostFragment.C32641 */

            @Override // com.gbcom.gwifi.widget.PullToRefreshView.AbstractC3493a
            public void onFooterRefresh(PullToRefreshView pullToRefreshView) {
                FindPostFragment.this.f12268g.mo28403c();
            }
        });
        this.f12266e.mo28265a(new GbScrollView.AbstractC3481a() {
            /* class com.gbcom.gwifi.functions.template.fragment.FindPostFragment.C32652 */

            @Override // com.gbcom.gwifi.widget.GbScrollView.AbstractC3481a
            /* renamed from: a */
            public void mo27271a() {
            }

            @Override // com.gbcom.gwifi.widget.GbScrollView.AbstractC3481a
            /* renamed from: b */
            public void mo27272b() {
            }
        });
        this.f12270i = (LoadingView) view.findViewById(C2425R.C2427id.loading_view);
        this.f12270i.mo28298a(this.f12277p);
        new HandlerThread("cache_news").start();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constant.f13238bM);
        GBApplication.instance().registerReceiver(this.f12276o, intentFilter);
        m13459g();
        m13458f();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: f */
    private void m13458f() {
        this.f12275n = HttpUtil.m14302c(GBApplication.instance(), 3, this.f12264b, f12263d);
        this.f12274m = false;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: g */
    private void m13459g() {
        String cacheContent = CacheApp.getInstance().getCacheContent(3);
        if (!C3467s.m14513e(cacheContent)) {
            mo27280d();
            try {
                m13451a(new JSONObject(cacheContent).getJSONObject("data"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m13451a(JSONObject jSONObject) throws JSONException {
        jSONObject.getString("tab_title");
        JSONArray jSONArray = jSONObject.getJSONArray(Constants.ELEMNAME_CHILDREN_STRING);
        jSONObject.getString("layout_wap_url");
        for (int i = 0; i < jSONArray.length(); i++) {
            String string = jSONArray.getJSONObject(i).getString("child_layout_id");
            if (TextUtils.equals(ViewBuilderFactory.f11716E, string)) {
                this.f12274m = true;
            }
            if (TextUtils.equals(ViewBuilderFactory.f11713B, string)) {
                this.f12273l = true;
            }
        }
        ViewBuilder a = ViewBuilderFactory.m12847a(jSONObject.getInt("layout_id") + "", 3);
        if (a != null) {
            if (a instanceof VerticalLinearLayoutBuilder) {
                this.f12271j = (VerticalLinearLayoutBuilder) a;
            }
            if (a instanceof SchoolNotifyBuilder) {
                this.f12272k = (SchoolNotifyBuilder) a;
            }
            View a2 = a.mo26982a(getActivity(), this.f12269h, jSONObject, null);
            if (a2 != null) {
                this.f12269h.removeAllViews();
                this.f12269h.addView(a2);
                mo27280d();
                this.f12268g.mo28397a(new PullToRefreshView.AbstractC3494b() {
                    /* class com.gbcom.gwifi.functions.template.fragment.FindPostFragment.C32685 */

                    @Override // com.gbcom.gwifi.widget.PullToRefreshView.AbstractC3494b
                    public void onHeaderRefresh(PullToRefreshView pullToRefreshView) {
                        if (FindPostFragment.this.f12271j != null) {
                            FindPostFragment.this.f12271j.mo26983a();
                        }
                        HttpUtil.m14287a(FindPostFragment.f12263d);
                        FindPostFragment.this.m13458f();
                    }
                });
            }
        }
    }

    /* renamed from: b */
    public void mo27278b() {
        this.f12270i.mo28307i();
        this.f12270i.mo28313o();
        this.f12270i.mo28315q();
        if (this.f12270i.mo28310l()) {
            this.f12270i.mo28309k();
        }
        this.f12270i.mo28297a();
        this.f12270i.mo28302d();
        this.f12270i.mo28304f();
        this.f12270i.mo28300b();
    }

    /* renamed from: c */
    public void mo27279c() {
        this.f12266e.setVisibility(8);
        this.f12270i.setVisibility(0);
        this.f12270i.mo28303e();
        this.f12270i.mo28301c();
        this.f12270i.mo28305g();
        this.f12270i.mo28306h();
        this.f12270i.mo28308j();
        this.f12270i.mo28312n();
        this.f12270i.mo28314p();
    }

    /* renamed from: d */
    public void mo27280d() {
        this.f12266e.setVisibility(0);
        this.f12270i.setVisibility(8);
        this.f12270i.mo28301c();
    }

    @Override // com.gbcom.gwifi.base.activity.GBFragment, android.support.p009v4.app.Fragment
    public void onPause() {
        super.onPause();
        GiwifiMobclickAgent.onPageEnd("本地界面");
    }

    @Override // com.gbcom.gwifi.base.activity.GBFragment, android.support.p009v4.app.Fragment
    public void onResume() {
        super.onResume();
        if (this.f12272k != null) {
            this.f12272k.mo27065a();
        }
        GiwifiMobclickAgent.onPageStart("本地界面");
    }

    @Override // android.support.p009v4.app.Fragment
    public void onDestroy() {
        GBApplication.instance().unregisterReceiver(this.f12276o);
        super.onDestroy();
    }

    @Override // com.gbcom.gwifi.functions.template.builder.OnDestoryBuilder
    /* renamed from: a */
    public void mo26983a() {
        if (this.f12271j != null) {
            this.f12271j.mo26983a();
        }
    }

    /* renamed from: com.gbcom.gwifi.functions.template.fragment.c$a */
    /* compiled from: FindPostFragment */
    static class C3270a {

        /* renamed from: a */
        ImageView f12284a;

        /* renamed from: b */
        TextView f12285b;

        C3270a() {
        }
    }

    @Override // android.support.p009v4.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (z && this.f12271j != null) {
            this.f12271j.mo26983a();
        }
    }
}
