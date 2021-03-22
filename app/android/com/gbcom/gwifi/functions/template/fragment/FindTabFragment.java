package com.gbcom.gwifi.functions.template.fragment;

import android.os.Bundle;
import android.os.HandlerThread;
import android.support.p009v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBFragment;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.functions.loading.MainActivity;
import com.gbcom.gwifi.functions.loading.domain.TabDatas;
import com.gbcom.gwifi.functions.template.builder.VerticalLinearLayoutBuilder;
import com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView;
import com.gbcom.gwifi.functions.template.p252a.CommonGiWiFiAdClickListener;
import com.gbcom.gwifi.third.umeng.analytics.GiwifiMobclickAgent;
import com.gbcom.gwifi.util.cache.CacheAd;
import com.gbcom.gwifi.util.cache.CacheApp;
import com.gbcom.gwifi.util.cache.CacheWiFi;
import com.gbcom.gwifi.util.p257b.DensityUtil;
import com.gbcom.gwifi.util.p257b.ImageTools;
import com.gbcom.gwifi.util.p257b.UIUtil;
import com.gbcom.gwifi.widget.LoadingView;
import com.gbcom.gwifi.widget.PullToRefreshView;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import p041c.Request;

/* renamed from: com.gbcom.gwifi.functions.template.fragment.d */
public class FindTabFragment extends GBFragment implements RadioGroup.OnCheckedChangeListener {

    /* renamed from: a */
    public static final int f12286a = 3;

    /* renamed from: b */
    public static final int f12287b = 1;

    /* renamed from: c */
    public static final int f12288c = 2;

    /* renamed from: f */
    private static final Object f12289f = "FindTabFragment";

    /* renamed from: A */
    private int f12290A = 2;

    /* renamed from: B */
    private FindNewsFragment f12291B;

    /* renamed from: C */
    private FindPostFragment f12292C;

    /* renamed from: D */
    private int f12293D;

    /* renamed from: E */
    private Request f12294E;

    /* renamed from: F */
    private BaseGiWiFiInfoView.AbstractC3242l f12295F = new BaseGiWiFiInfoView.AbstractC3242l() {
        /* class com.gbcom.gwifi.functions.template.fragment.FindTabFragment.C32744 */

        @Override // com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView.AbstractC3242l
        /* renamed from: a */
        public void mo27257a(BaseGiWiFiInfoView.EnumC3246p pVar) {
            FindTabFragment.this.f12308q.mo27103a(FindTabFragment.this.f12309r, pVar);
        }
    };

    /* renamed from: d */
    private boolean f12296d = false;

    /* renamed from: e */
    private View f12297e;

    /* renamed from: g */
    private TextView f12298g;

    /* renamed from: h */
    private ScrollView f12299h;

    /* renamed from: i */
    private SimpleDateFormat f12300i;

    /* renamed from: j */
    private PullToRefreshView f12301j;

    /* renamed from: k */
    private LinearLayout f12302k;

    /* renamed from: l */
    private LoadingView f12303l;

    /* renamed from: m */
    private View f12304m;

    /* renamed from: n */
    private TextView f12305n;

    /* renamed from: o */
    private VerticalLinearLayoutBuilder f12306o;

    /* renamed from: p */
    private LinearLayout f12307p;

    /* renamed from: q */
    private GiWiFiInfoExView f12308q;

    /* renamed from: r */
    private ImageView f12309r;

    /* renamed from: s */
    private LinearLayout f12310s;

    /* renamed from: t */
    private ImageView f12311t;

    /* renamed from: u */
    private ImageView f12312u;

    /* renamed from: v */
    private RadioGroup f12313v;

    /* renamed from: w */
    private RadioButton f12314w;

    /* renamed from: x */
    private RadioButton f12315x;

    /* renamed from: y */
    private List<TabDatas.DataBean.TabListBean.ChildTabBean> f12316y = new ArrayList();

    /* renamed from: z */
    private int f12317z = 1;

    @Override // android.support.p009v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.f12316y = ((TabDatas.DataBean.TabListBean) arguments.get("tabListBean")).getChild_tab();
        }
        return layoutInflater.inflate(C2425R.layout.tp_find_fragment, viewGroup, false);
    }

    @Override // android.support.p009v4.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f12304m = view.findViewById(C2425R.C2427id.include);
        this.f12298g = (TextView) this.f12304m.findViewById(C2425R.C2427id.title_menu);
        this.f12298g.setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.template.fragment.FindTabFragment.View$OnClickListenerC32711 */

            public void onClick(View view) {
                UIUtil.m14204b(view);
                ((MainActivity) FindTabFragment.this.getActivity()).showPopWindow(GBApplication.instance());
            }
        });
        this.f12305n = (TextView) this.f12304m.findViewById(2131755156);
        this.f12313v = (RadioGroup) this.f12304m.findViewById(C2425R.C2427id.title_rg);
        this.f12314w = (RadioButton) this.f12304m.findViewById(C2425R.C2427id.tite_rb1);
        this.f12315x = (RadioButton) this.f12304m.findViewById(C2425R.C2427id.tite_rb2);
        this.f12313v.setOnCheckedChangeListener(this);
        this.f12308q = (GiWiFiInfoExView) getActivity().findViewById(C2425R.C2427id.wifi_info_layout);
        this.f12308q.mo27114a(this.f12295F);
        this.f12309r = (ImageView) this.f12304m.findViewById(C2425R.C2427id.wifi_state_iv);
        this.f12307p = (LinearLayout) this.f12304m.findViewById(C2425R.C2427id.wifi_state_ll);
        this.f12307p.setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.template.fragment.FindTabFragment.View$OnClickListenerC32722 */

            public void onClick(View view) {
                ((MainActivity) FindTabFragment.this.getActivity()).showAndClose();
            }
        });
        this.f12300i = new SimpleDateFormat("MM-dd HH:mm");
        this.f12310s = (LinearLayout) view.findViewById(C2425R.C2427id.ll_tp_ad);
        this.f12311t = (ImageView) view.findViewById(C2425R.C2427id.iv_tp_ad);
        this.f12312u = (ImageView) view.findViewById(C2425R.C2427id.btn_tp_ad);
        this.f12312u.setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.template.fragment.FindTabFragment.View$OnClickListenerC32733 */

            public void onClick(View view) {
                FindTabFragment.this.f12310s.setVisibility(8);
            }
        });
        new HandlerThread("cache_news").start();
        if (this.f12316y.size() == 2) {
            this.f12314w.setText(this.f12316y.get(0).getName());
            this.f12315x.setText(this.f12316y.get(1).getName());
            this.f12317z = this.f12316y.get(0).getType();
            this.f12290A = this.f12316y.get(1).getType();
            m13477e();
        } else if (this.f12316y.size() != 1) {
            this.f12314w.setText("");
            m13472a(1, "");
        } else if (this.f12316y.get(0).getType() == 1) {
            m13472a(1, this.f12316y.get(0).getName());
        } else {
            m13472a(1, "");
        }
        m13478f();
        this.f12308q.mo27103a(this.f12309r, BaseGiWiFiInfoView.EnumC3246p.m13296a(CacheWiFi.getInstance().getWifiControlState()));
    }

    /* renamed from: a */
    private void m13472a(int i, String str) {
        this.f12305n.setText(str);
        this.f12313v.setVisibility(8);
        m13474b(i);
    }

    /* renamed from: d */
    private void m13476d() {
        this.f12305n.setText(this.f12316y.get(0).getName());
        this.f12313v.setVisibility(8);
        m13474b(this.f12316y.get(0).getType());
    }

    /* renamed from: e */
    private void m13477e() {
        this.f12314w.setChecked(true);
        this.f12315x.setChecked(false);
        this.f12305n.setVisibility(8);
        this.f12313v.setVisibility(0);
        int afterAuthJumpTabType = CacheApp.getInstance().getAfterAuthJumpTabType();
        if (afterAuthJumpTabType == 1) {
            this.f12314w.setChecked(true);
        } else if (afterAuthJumpTabType == 4) {
            this.f12315x.setChecked(true);
        } else {
            this.f12314w.setChecked(true);
        }
    }

    /* renamed from: a */
    public void mo27283a() {
        this.f12303l.mo28307i();
        this.f12303l.mo28313o();
        this.f12303l.mo28315q();
        if (this.f12303l.mo28310l()) {
            this.f12303l.mo28309k();
        }
        this.f12303l.mo28297a();
        this.f12303l.mo28302d();
        this.f12303l.mo28304f();
        this.f12303l.mo28300b();
    }

    /* renamed from: b */
    public void mo27284b() {
        this.f12303l.setVisibility(0);
        this.f12303l.mo28303e();
        this.f12303l.mo28301c();
        this.f12303l.mo28305g();
        this.f12303l.mo28306h();
        this.f12303l.mo28308j();
        this.f12303l.mo28312n();
        this.f12303l.mo28314p();
    }

    /* renamed from: c */
    public void mo27285c() {
        this.f12303l.setVisibility(8);
        this.f12303l.mo28301c();
    }

    @Override // com.gbcom.gwifi.base.activity.GBFragment, android.support.p009v4.app.Fragment
    public void onResume() {
        super.onResume();
        this.f12308q.mo27141aS();
        if (this.f12306o != null) {
            this.f12306o.mo26984b();
        }
        GiwifiMobclickAgent.onPageStart("本地界面");
    }

    @Override // com.gbcom.gwifi.base.activity.GBFragment, android.support.p009v4.app.Fragment
    public void onPause() {
        super.onPause();
        GiwifiMobclickAgent.onPageEnd("本地界面");
    }

    @Override // android.support.p009v4.app.Fragment
    public void onDestroy() {
        super.onDestroy();
    }

    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if (i == this.f12314w.getId()) {
            if (this.f12317z == 1) {
                m13471a(1);
            } else {
                m13471a(2);
            }
            this.f12314w.setTextColor(getResources().getColor(2131624113));
            this.f12315x.setTextColor(getResources().getColor(C2425R.color.black));
            this.f12314w.setBackgroundResource(C2425R.C2426drawable.left_sel);
            this.f12315x.setBackgroundResource(C2425R.C2426drawable.right_nosel);
        } else if (i == this.f12315x.getId()) {
            if (this.f12290A == 1) {
                m13471a(1);
            } else {
                m13471a(2);
            }
            this.f12314w.setTextColor(getResources().getColor(C2425R.color.black));
            this.f12315x.setTextColor(getResources().getColor(2131624113));
            this.f12314w.setBackgroundResource(C2425R.C2426drawable.left_nosel);
            this.f12315x.setBackgroundResource(C2425R.C2426drawable.right_sel);
        }
    }

    /* renamed from: a */
    private void m13471a(int i) {
        switch (i) {
            case 1:
                m13474b(1);
                return;
            case 2:
                m13474b(2);
                return;
            default:
                return;
        }
    }

    /* renamed from: b */
    private void m13474b(int i) {
        FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
        if (i == 2) {
            if (this.f12291B == null) {
                this.f12291B = new FindNewsFragment();
                beginTransaction.add(C2425R.C2427id.fl_find_content, this.f12291B, "findNews");
            } else {
                beginTransaction.show(this.f12291B);
            }
            if (this.f12292C != null) {
                beginTransaction.hide(this.f12292C);
            }
            this.f12293D = 1;
        } else {
            if (this.f12292C == null) {
                this.f12292C = new FindPostFragment();
                beginTransaction.add(C2425R.C2427id.fl_find_content, this.f12292C, "findPost");
            } else {
                beginTransaction.show(this.f12292C);
            }
            if (this.f12291B != null) {
                beginTransaction.hide(this.f12291B);
            }
            this.f12293D = 2;
        }
        beginTransaction.commitAllowingStateLoss();
    }

    /* renamed from: com.gbcom.gwifi.functions.template.fragment.d$a */
    /* compiled from: FindTabFragment */
    static class C3276a {

        /* renamed from: a */
        ImageView f12323a;

        /* renamed from: b */
        TextView f12324b;

        C3276a() {
        }
    }

    @Override // android.support.p009v4.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (z && this.f12306o != null) {
            this.f12306o.mo26983a();
        }
    }

    /* renamed from: f */
    private void m13478f() {
        List<HashMap<String, Object>> adByLayoutType;
        if (!(this.f12310s.getVisibility() == 0 || (adByLayoutType = CacheAd.getInstance().getAdByLayoutType(3)) == null || adByLayoutType.size() == 0)) {
            for (HashMap<String, Object> hashMap : adByLayoutType) {
                this.f12311t.getLayoutParams().height = DensityUtil.m14127a(getActivity());
                ImageTools.m14152a((String) hashMap.get("localImgUrl"), this.f12311t, GBApplication.instance().options, ImageLoader.DEALIMAGE.FILLWIDTH);
                this.f12310s.setVisibility(0);
                this.f12311t.setTag(new CommonGiWiFiAdClickListener(getActivity(), hashMap, 0));
                this.f12311t.setOnClickListener(new View.OnClickListener() {
                    /* class com.gbcom.gwifi.functions.template.fragment.FindTabFragment.View$OnClickListenerC32755 */

                    public void onClick(View view) {
                        ((CommonGiWiFiAdClickListener) view.getTag()).onClick(view);
                        FindTabFragment.this.f12310s.setVisibility(8);
                    }
                });
            }
        }
    }
}
