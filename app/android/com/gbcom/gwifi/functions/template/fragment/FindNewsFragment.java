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

/* renamed from: com.gbcom.gwifi.functions.template.fragment.b */
public class FindNewsFragment extends GBFragment {

    /* renamed from: a */
    public static final int f12251a = 4;

    /* renamed from: c */
    private static final Object f12252c = "FindNewsFragment";

    /* renamed from: b */
    OkRequestHandler<String> f12253b = new OkRequestHandler<String>() {
        /* class com.gbcom.gwifi.functions.template.fragment.FindNewsFragment.C32622 */

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestStart(Request abVar) {
            FindNewsFragment.this.mo27273a();
            if (FindNewsFragment.this.f12256f.getVisibility() == 8 && abVar == FindNewsFragment.this.f12255e) {
                ((GBActivity) FindNewsFragment.this.getActivity()).showProgressDialog("正在加载...");
            }
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestFailed(Request abVar, Exception exc) {
            if (FindNewsFragment.this.isAdded() && GBApplication.instance().getCurrentActivity() != null) {
                GBApplication.instance().getCurrentActivity().dismissProgressDialog();
                FindNewsFragment.this.mo27274b();
            }
        }

        /* renamed from: a */
        public void onRequestFinish(Request abVar, String str) {
            if (FindNewsFragment.this.isAdded() && GBApplication.instance().getCurrentActivity() != null) {
                GBApplication.instance().getCurrentActivity().dismissProgressDialog();
                if (FindNewsFragment.this.f12255e == abVar) {
                    boolean z = false;
                    if (!C3467s.m14513e(str)) {
                        try {
                            JSONObject jSONObject = new JSONObject(str);
                            int i = jSONObject.getInt(AbstractC5718b.JSON_ERRORCODE);
                            Object obj = jSONObject.get("data");
                            if (ResultCode.m14475a(Integer.valueOf(i)) && obj != null && (obj instanceof JSONObject)) {
                                z = true;
                                CacheApp.getInstance().setCacheContent(4, str);
                                FindNewsFragment.this.m13436a((FindNewsFragment) ((JSONObject) obj));
                            }
                            z = z;
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {
                        GBActivity.showMessageToast("请求失败");
                    }
                    if (!z) {
                        FindNewsFragment.this.mo27274b();
                    }
                }
            }
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestProgress(Request abVar, long j, long j2) {
        }
    };

    /* renamed from: d */
    private RelativeLayout f12254d;

    /* renamed from: e */
    private Request f12255e;

    /* renamed from: f */
    private LoadingView f12256f;

    /* renamed from: g */
    private BroadcastReceiver f12257g = new BroadcastReceiver() {
        /* class com.gbcom.gwifi.functions.template.fragment.FindNewsFragment.C32611 */

        public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getAction().equals(Constant.f13238bM)) {
                m13445a();
            }
        }

        /* renamed from: a */
        private void m13445a() {
            FindNewsFragment.this.f12255e = HttpUtil.m14321g(GBApplication.instance(), FindNewsFragment.this.f12253b, FindNewsFragment.f12252c);
        }
    };

    /* renamed from: h */
    private LoadingView.AbstractC3488a f12258h = new LoadingView.AbstractC3488a() {
        /* class com.gbcom.gwifi.functions.template.fragment.FindNewsFragment.C32633 */

        @Override // com.gbcom.gwifi.widget.LoadingView.AbstractC3488a
        /* renamed from: a */
        public void mo24655a(View view) {
            HttpUtil.m14287a(FindNewsFragment.f12252c);
            FindNewsFragment.this.m13440e();
        }
    };

    @Override // android.support.p009v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(C2425R.layout.fragment_news, viewGroup, false);
    }

    @Override // android.support.p009v4.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f12256f = (LoadingView) view.findViewById(C2425R.C2427id.loading_view);
        this.f12256f.mo28298a(this.f12258h);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constant.f13238bM);
        GBApplication.instance().registerReceiver(this.f12257g, intentFilter);
        this.f12254d = (RelativeLayout) view.findViewById(C2425R.C2427id.rl_whole);
        m13440e();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: e */
    private void m13440e() {
        this.f12255e = HttpUtil.m14321g(GBApplication.instance(), this.f12253b, f12252c);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m13436a(JSONObject jSONObject) throws JSONException {
        View a;
        ViewBuilder agVar = null;
        if (jSONObject.has("news_list") && jSONObject.getJSONArray("news_list").length() > 0) {
            agVar = ViewBuilderFactory.m12846a("3002");
        }
        if (agVar != null && (a = agVar.mo26982a(getActivity(), this.f12254d, jSONObject, getFragmentManager())) != null) {
            this.f12254d.removeAllViews();
            this.f12254d.addView(a);
            mo27275c();
        }
    }

    /* renamed from: f */
    private void m13441f() {
        String cacheContent = CacheApp.getInstance().getCacheContent(4);
        if (!C3467s.m14513e(cacheContent)) {
            mo27275c();
            try {
                m13436a(new JSONObject(cacheContent).getJSONObject("data"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override // android.support.p009v4.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        GBApplication.instance().unregisterReceiver(this.f12257g);
    }

    /* renamed from: a */
    public void mo27273a() {
        this.f12256f.mo28307i();
        this.f12256f.mo28313o();
        this.f12256f.mo28315q();
        if (this.f12256f.mo28310l()) {
            this.f12256f.mo28309k();
        }
        this.f12256f.mo28297a();
        this.f12256f.mo28302d();
        this.f12256f.mo28304f();
        this.f12256f.mo28300b();
    }

    /* renamed from: b */
    public void mo27274b() {
        this.f12254d.setVisibility(8);
        this.f12256f.setVisibility(0);
        this.f12256f.mo28303e();
        this.f12256f.mo28301c();
        this.f12256f.mo28305g();
        this.f12256f.mo28306h();
        this.f12256f.mo28308j();
        this.f12256f.mo28312n();
        this.f12256f.mo28314p();
    }

    /* renamed from: c */
    public void mo27275c() {
        this.f12254d.setVisibility(0);
        this.f12256f.setVisibility(8);
        this.f12256f.mo28301c();
    }
}
