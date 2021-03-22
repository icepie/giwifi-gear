package com.gbcom.gwifi.functions.template.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.base.activity.GBFragment;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.functions.template.builder.ViewBuilder;
import com.gbcom.gwifi.functions.template.builder.ViewBuilderFactory;
import com.gbcom.gwifi.p221a.p226d.OkRequestHandler;
import com.gbcom.gwifi.util.C3467s;
import com.gbcom.gwifi.util.Constant;
import com.gbcom.gwifi.util.HttpUtil;
import com.gbcom.gwifi.util.ResultCode;
import com.gbcom.gwifi.util.cache.CacheApp;
import com.gbcom.gwifi.widget.LoadingView;
import com.taobao.agoo.p359a.p360a.AbstractC5718b;
import org.json.JSONException;
import org.json.JSONObject;
import p041c.Request;

/* renamed from: com.gbcom.gwifi.functions.template.fragment.g */
public class NewsFragment extends GBFragment {

    /* renamed from: a */
    public static final int f12430a = 4;

    /* renamed from: c */
    private static final Object f12431c = "NewsFragment";

    /* renamed from: b */
    OkRequestHandler<String> f12432b = new OkRequestHandler<String>() {
        /* class com.gbcom.gwifi.functions.template.fragment.NewsFragment.C33082 */

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestStart(Request abVar) {
            NewsFragment.this.mo27318a();
            if (NewsFragment.this.f12435f.getVisibility() == 8 && abVar == NewsFragment.this.f12434e) {
                ((GBActivity) NewsFragment.this.getActivity()).showProgressDialog("正在加载...");
            }
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestFailed(Request abVar, Exception exc) {
            if (NewsFragment.this.isAdded() && GBApplication.instance().getCurrentActivity() != null) {
                GBApplication.instance().getCurrentActivity().dismissProgressDialog();
                NewsFragment.this.mo27319b();
            }
        }

        /* renamed from: a */
        public void onRequestFinish(Request abVar, String str) {
            if (NewsFragment.this.isAdded() && GBApplication.instance().getCurrentActivity() != null) {
                GBApplication.instance().getCurrentActivity().dismissProgressDialog();
                if (NewsFragment.this.f12434e == abVar) {
                    boolean z = false;
                    if (!C3467s.m14513e(str)) {
                        try {
                            JSONObject jSONObject = new JSONObject(str);
                            int i = jSONObject.getInt(AbstractC5718b.JSON_ERRORCODE);
                            Object obj = jSONObject.get("data");
                            if (ResultCode.m14475a(Integer.valueOf(i)) && obj != null && (obj instanceof JSONObject)) {
                                z = true;
                                CacheApp.getInstance().setCacheContent(4, str);
                                NewsFragment.this.m13586a((NewsFragment) ((JSONObject) obj));
                            }
                            z = z;
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {
                        GBActivity.showMessageToast("请求失败");
                    }
                    if (!z) {
                        NewsFragment.this.mo27319b();
                    }
                }
            }
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestProgress(Request abVar, long j, long j2) {
        }
    };

    /* renamed from: d */
    private RelativeLayout f12433d;

    /* renamed from: e */
    private Request f12434e;

    /* renamed from: f */
    private LoadingView f12435f;

    /* renamed from: g */
    private BroadcastReceiver f12436g = new BroadcastReceiver() {
        /* class com.gbcom.gwifi.functions.template.fragment.NewsFragment.C33071 */

        public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getAction().equals(Constant.f13238bM)) {
                m13595a();
            }
        }

        /* renamed from: a */
        private void m13595a() {
            NewsFragment.this.f12434e = HttpUtil.m14321g(GBApplication.instance(), NewsFragment.this.f12432b, NewsFragment.f12431c);
        }
    };

    /* renamed from: h */
    private LoadingView.AbstractC3488a f12437h = new LoadingView.AbstractC3488a() {
        /* class com.gbcom.gwifi.functions.template.fragment.NewsFragment.C33093 */

        @Override // com.gbcom.gwifi.widget.LoadingView.AbstractC3488a
        /* renamed from: a */
        public void mo24655a(View view) {
            HttpUtil.m14287a(NewsFragment.f12431c);
            NewsFragment.this.m13590e();
        }
    };

    @Override // android.support.p009v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(C2425R.layout.fragment_news, viewGroup, false);
    }

    @Override // android.support.p009v4.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f12435f = (LoadingView) view.findViewById(C2425R.C2427id.loading_view);
        this.f12435f.mo28298a(this.f12437h);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constant.f13238bM);
        GBApplication.instance().registerReceiver(this.f12436g, intentFilter);
        this.f12433d = (RelativeLayout) view.findViewById(C2425R.C2427id.rl_whole);
        m13590e();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: e */
    private void m13590e() {
        this.f12434e = HttpUtil.m14321g(GBApplication.instance(), this.f12432b, f12431c);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m13586a(JSONObject jSONObject) throws JSONException {
        View a;
        ViewBuilder agVar = null;
        if (jSONObject.has("news_list") && jSONObject.getJSONArray("news_list").length() > 0) {
            agVar = ViewBuilderFactory.m12846a("5002");
        }
        if (agVar != null && (a = agVar.mo26982a(getActivity(), this.f12433d, jSONObject, getFragmentManager())) != null) {
            this.f12433d.removeAllViews();
            this.f12433d.addView(a);
            mo27320c();
        }
    }

    /* renamed from: f */
    private void m13591f() {
        String cacheContent = CacheApp.getInstance().getCacheContent(4);
        if (!C3467s.m14513e(cacheContent)) {
            mo27320c();
            try {
                m13586a(new JSONObject(cacheContent).getJSONObject("data"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override // android.support.p009v4.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        GBApplication.instance().unregisterReceiver(this.f12436g);
    }

    /* renamed from: a */
    public void mo27318a() {
        this.f12435f.mo28307i();
        this.f12435f.mo28313o();
        this.f12435f.mo28315q();
        if (this.f12435f.mo28310l()) {
            this.f12435f.mo28309k();
        }
        this.f12435f.mo28297a();
        this.f12435f.mo28302d();
        this.f12435f.mo28304f();
        this.f12435f.mo28300b();
    }

    /* renamed from: b */
    public void mo27319b() {
        this.f12433d.setVisibility(8);
        this.f12435f.setVisibility(0);
        this.f12435f.mo28303e();
        this.f12435f.mo28301c();
        this.f12435f.mo28305g();
        this.f12435f.mo28306h();
        this.f12435f.mo28308j();
        this.f12435f.mo28312n();
        this.f12435f.mo28314p();
    }

    /* renamed from: c */
    public void mo27320c() {
        this.f12433d.setVisibility(0);
        this.f12435f.setVisibility(8);
        this.f12435f.mo28301c();
    }
}
