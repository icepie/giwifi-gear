package com.gbcom.gwifi.functions.template.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.HandlerThread;
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
import org.json.JSONException;
import org.json.JSONObject;
import p041c.Request;

/* renamed from: com.gbcom.gwifi.functions.template.fragment.i */
public class PostFragment extends GBFragment implements OnDestoryBuilder {

    /* renamed from: a */
    public static final int f12505a = SecondTabFragment.f12528a;

    /* renamed from: d */
    private static final Object f12506d = "PostFragment";

    /* renamed from: b */
    OkRequestHandler<String> f12507b = new OkRequestHandler<String>() {
        /* class com.gbcom.gwifi.functions.template.fragment.PostFragment.C33275 */

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestFailed(Request abVar, Exception exc) {
            if (PostFragment.this.isAdded() && GBApplication.instance().getCurrentActivity() != null) {
                GBApplication.instance().getCurrentActivity().dismissProgressDialog();
                PostFragment.this.f12511g.mo28398a("更新于:" + PostFragment.this.f12510f.format(new Date(System.currentTimeMillis())));
                PostFragment.this.mo27343c();
                PostFragment.this.m13658g();
            }
        }

        /* renamed from: a */
        public void onRequestFinish(Request abVar, String str) {
            boolean z;
            if (PostFragment.this.isAdded() && GBApplication.instance().getCurrentActivity() != null) {
                GBApplication.instance().getCurrentActivity().dismissProgressDialog();
                PostFragment.this.f12511g.mo28398a("更新于:" + PostFragment.this.f12510f.format(new Date(System.currentTimeMillis())));
                if (PostFragment.this.f12516l == abVar) {
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
                                CacheApp.getInstance().setCacheContent(PostFragment.f12505a, str);
                                PostFragment.this.m13649a((PostFragment) ((JSONObject) obj));
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
                        PostFragment.this.mo27343c();
                        PostFragment.this.m13658g();
                    }
                }
            }
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestProgress(Request abVar, long j, long j2) {
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestStart(Request abVar) {
            PostFragment.this.mo27342b();
            if (PostFragment.this.f12513i.getVisibility() == 8 && abVar == PostFragment.this.f12516l) {
                ((GBActivity) PostFragment.this.getActivity()).showProgressDialog("正在加载...");
            }
        }
    };

    /* renamed from: c */
    private View f12508c;

    /* renamed from: e */
    private GbScrollView f12509e;

    /* renamed from: f */
    private SimpleDateFormat f12510f;

    /* renamed from: g */
    private PullToRefreshView f12511g;

    /* renamed from: h */
    private LinearLayout f12512h;

    /* renamed from: i */
    private LoadingView f12513i;

    /* renamed from: j */
    private VerticalLinearLayoutBuilder f12514j;

    /* renamed from: k */
    private SchoolNotifyBuilder f12515k;

    /* renamed from: l */
    private Request f12516l;

    /* renamed from: m */
    private BroadcastReceiver f12517m = new BroadcastReceiver() {
        /* class com.gbcom.gwifi.functions.template.fragment.PostFragment.C33264 */

        public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getAction().equals(Constant.f13238bM)) {
                m13665a();
            }
        }

        /* renamed from: a */
        private void m13665a() {
            PostFragment.this.f12516l = HttpUtil.m14302c(GBApplication.instance(), PostFragment.f12505a, PostFragment.this.f12507b, PostFragment.f12506d);
        }
    };

    /* renamed from: n */
    private LoadingView.AbstractC3488a f12518n = new LoadingView.AbstractC3488a() {
        /* class com.gbcom.gwifi.functions.template.fragment.PostFragment.C33297 */

        @Override // com.gbcom.gwifi.widget.LoadingView.AbstractC3488a
        /* renamed from: a */
        public void mo24655a(View view) {
            HttpUtil.m14287a(PostFragment.f12506d);
            PostFragment.this.m13656f();
        }
    };

    @Override // android.support.p009v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(C2425R.layout.fragment_post, viewGroup, false);
    }

    @Override // android.support.p009v4.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f12512h = (LinearLayout) view.findViewById(2131755301);
        this.f12509e = (GbScrollView) view.findViewById(C2425R.C2427id.home_scroll);
        this.f12510f = new SimpleDateFormat("MM-dd HH:mm");
        this.f12511g = (PullToRefreshView) view.findViewById(C2425R.C2427id.home_pull_refresh_view);
        this.f12511g.mo28397a(new PullToRefreshView.AbstractC3494b() {
            /* class com.gbcom.gwifi.functions.template.fragment.PostFragment.C33231 */

            @Override // com.gbcom.gwifi.widget.PullToRefreshView.AbstractC3494b
            public void onHeaderRefresh(PullToRefreshView pullToRefreshView) {
                if (PostFragment.this.f12514j != null) {
                    PostFragment.this.f12514j.mo26983a();
                }
                HttpUtil.m14287a(PostFragment.f12506d);
                PostFragment.this.m13656f();
            }
        });
        this.f12511g.mo28396a(new PullToRefreshView.AbstractC3493a() {
            /* class com.gbcom.gwifi.functions.template.fragment.PostFragment.C33242 */

            @Override // com.gbcom.gwifi.widget.PullToRefreshView.AbstractC3493a
            public void onFooterRefresh(PullToRefreshView pullToRefreshView) {
                PostFragment.this.f12511g.mo28403c();
            }
        });
        this.f12509e.mo28265a(new GbScrollView.AbstractC3481a() {
            /* class com.gbcom.gwifi.functions.template.fragment.PostFragment.C33253 */

            @Override // com.gbcom.gwifi.widget.GbScrollView.AbstractC3481a
            /* renamed from: a */
            public void mo27271a() {
            }

            @Override // com.gbcom.gwifi.widget.GbScrollView.AbstractC3481a
            /* renamed from: b */
            public void mo27272b() {
            }
        });
        this.f12513i = (LoadingView) view.findViewById(C2425R.C2427id.loading_view);
        this.f12513i.mo28298a(this.f12518n);
        new HandlerThread("cache_news").start();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constant.f13238bM);
        GBApplication.instance().registerReceiver(this.f12517m, intentFilter);
        m13658g();
        m13656f();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: f */
    private void m13656f() {
        this.f12516l = HttpUtil.m14302c(GBApplication.instance(), f12505a, this.f12507b, f12506d);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: g */
    private void m13658g() {
        String cacheContent = CacheApp.getInstance().getCacheContent(f12505a);
        if (!C3467s.m14513e(cacheContent)) {
            mo27344d();
            try {
                m13649a(new JSONObject(cacheContent).getJSONObject("data"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m13649a(JSONObject jSONObject) throws JSONException {
        jSONObject.getString("tab_title");
        ViewBuilder a = ViewBuilderFactory.m12847a(jSONObject.getInt("layout_id") + "", f12505a);
        if (a != null) {
            if (a instanceof VerticalLinearLayoutBuilder) {
                this.f12514j = (VerticalLinearLayoutBuilder) a;
            }
            if (a instanceof SchoolNotifyBuilder) {
                this.f12515k = (SchoolNotifyBuilder) a;
            }
            View a2 = a.mo26982a(getActivity(), this.f12512h, jSONObject, null);
            if (a2 != null) {
                this.f12512h.removeAllViews();
                this.f12512h.addView(a2);
                mo27344d();
                this.f12511g.mo28397a(new PullToRefreshView.AbstractC3494b() {
                    /* class com.gbcom.gwifi.functions.template.fragment.PostFragment.C33286 */

                    @Override // com.gbcom.gwifi.widget.PullToRefreshView.AbstractC3494b
                    public void onHeaderRefresh(PullToRefreshView pullToRefreshView) {
                        if (PostFragment.this.f12514j != null) {
                            PostFragment.this.f12514j.mo26983a();
                        }
                        HttpUtil.m14287a(PostFragment.f12506d);
                        PostFragment.this.m13656f();
                    }
                });
            }
        }
    }

    /* renamed from: b */
    public void mo27342b() {
        this.f12513i.mo28307i();
        this.f12513i.mo28313o();
        this.f12513i.mo28315q();
        if (this.f12513i.mo28310l()) {
            this.f12513i.mo28309k();
        }
        this.f12513i.mo28297a();
        this.f12513i.mo28302d();
        this.f12513i.mo28304f();
        this.f12513i.mo28300b();
    }

    /* renamed from: c */
    public void mo27343c() {
        this.f12509e.setVisibility(8);
        this.f12513i.setVisibility(0);
        this.f12513i.mo28303e();
        this.f12513i.mo28301c();
        this.f12513i.mo28305g();
        this.f12513i.mo28306h();
        this.f12513i.mo28308j();
        this.f12513i.mo28312n();
        this.f12513i.mo28314p();
    }

    /* renamed from: d */
    public void mo27344d() {
        this.f12509e.setVisibility(0);
        this.f12513i.setVisibility(8);
        this.f12513i.mo28301c();
    }

    @Override // com.gbcom.gwifi.base.activity.GBFragment, android.support.p009v4.app.Fragment
    public void onPause() {
        super.onPause();
        GiwifiMobclickAgent.onPageEnd("娱乐界面");
    }

    @Override // com.gbcom.gwifi.base.activity.GBFragment, android.support.p009v4.app.Fragment
    public void onResume() {
        super.onResume();
        if (this.f12515k != null) {
            this.f12515k.mo27065a();
        }
        GiwifiMobclickAgent.onPageStart("娱乐界面");
    }

    @Override // android.support.p009v4.app.Fragment
    public void onDestroy() {
        GBApplication.instance().unregisterReceiver(this.f12517m);
        super.onDestroy();
    }

    @Override // com.gbcom.gwifi.functions.template.builder.OnDestoryBuilder
    /* renamed from: a */
    public void mo26983a() {
        if (this.f12514j != null) {
            this.f12514j.mo26983a();
        }
    }

    /* renamed from: com.gbcom.gwifi.functions.template.fragment.i$a */
    /* compiled from: PostFragment */
    static class C3330a {

        /* renamed from: a */
        ImageView f12526a;

        /* renamed from: b */
        TextView f12527b;

        C3330a() {
        }
    }

    @Override // android.support.p009v4.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (z && this.f12514j != null) {
            this.f12514j.mo26983a();
        }
    }
}
