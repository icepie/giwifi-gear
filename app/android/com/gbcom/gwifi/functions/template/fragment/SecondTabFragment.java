package com.gbcom.gwifi.functions.template.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.p009v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBFragment;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.functions.loading.MainActivity;
import com.gbcom.gwifi.functions.loading.domain.TabDatas;
import com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView;
import com.gbcom.gwifi.functions.template.p252a.CommonGiWiFiAdClickListener;
import com.gbcom.gwifi.third.umeng.analytics.GiwifiMobclickAgent;
import com.gbcom.gwifi.util.Constant;
import com.gbcom.gwifi.util.VersionUtil;
import com.gbcom.gwifi.util.cache.CacheAd;
import com.gbcom.gwifi.util.cache.CacheApp;
import com.gbcom.gwifi.util.cache.CacheWiFi;
import com.gbcom.gwifi.util.p257b.DensityUtil;
import com.gbcom.gwifi.util.p257b.ImageTools;
import com.gbcom.gwifi.util.p257b.UIUtil;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* renamed from: com.gbcom.gwifi.functions.template.fragment.j */
public class SecondTabFragment extends GBFragment implements RadioGroup.OnCheckedChangeListener {

    /* renamed from: a */
    public static int f12528a = 2;

    /* renamed from: b */
    public static final int f12529b = 1;

    /* renamed from: c */
    public static final int f12530c = 2;

    /* renamed from: f */
    private static final Object f12531f = "SecondTabFragment";

    /* renamed from: A */
    private String f12532A = "娱乐";

    /* renamed from: B */
    private String f12533B = "新闻";

    /* renamed from: C */
    private int f12534C = 1;

    /* renamed from: D */
    private int f12535D = 2;

    /* renamed from: E */
    private BroadcastReceiver f12536E = new BroadcastReceiver() {
        /* class com.gbcom.gwifi.functions.template.fragment.SecondTabFragment.C33366 */

        public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getAction().equals(Constant.f13238bM)) {
                SecondTabFragment.this.m13672b();
            }
        }
    };

    /* renamed from: F */
    private BaseGiWiFiInfoView.AbstractC3241k f12537F = new BaseGiWiFiInfoView.AbstractC3241k() {
        /* class com.gbcom.gwifi.functions.template.fragment.SecondTabFragment.C33377 */

        @Override // com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView.AbstractC3241k
        /* renamed from: a */
        public void mo27256a(BaseGiWiFiInfoView.EnumC3246p pVar) {
            SecondTabFragment.this.f12556w.mo27103a(SecondTabFragment.this.f12557x, pVar);
        }
    };

    /* renamed from: d */
    public int f12538d = -1;

    /* renamed from: e */
    private View f12539e;

    /* renamed from: g */
    private TextView f12540g;

    /* renamed from: h */
    private TextView f12541h;

    /* renamed from: i */
    private RadioGroup f12542i;

    /* renamed from: j */
    private RadioButton f12543j;

    /* renamed from: k */
    private RadioButton f12544k;

    /* renamed from: l */
    private RelativeLayout f12545l;

    /* renamed from: m */
    private TextView f12546m;

    /* renamed from: n */
    private boolean f12547n = true;

    /* renamed from: o */
    private int f12548o;

    /* renamed from: p */
    private PostFragment f12549p;

    /* renamed from: q */
    private NewsFragment f12550q;

    /* renamed from: r */
    private LinearLayout f12551r;

    /* renamed from: s */
    private ImageView f12552s;

    /* renamed from: t */
    private ImageView f12553t;

    /* renamed from: u */
    private View f12554u;

    /* renamed from: v */
    private LinearLayout f12555v;

    /* renamed from: w */
    private GiWiFiInfoExView f12556w;

    /* renamed from: x */
    private ImageView f12557x;

    /* renamed from: y */
    private TabDatas.DataBean.TabListBean f12558y;

    /* renamed from: z */
    private List<TabDatas.DataBean.TabListBean.ChildTabBean> f12559z = new ArrayList();

    @Override // android.support.p009v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.f12558y = (TabDatas.DataBean.TabListBean) arguments.get("tabListBean");
            f12528a = this.f12558y.getLayout_type();
            if (this.f12558y != null) {
                this.f12559z = this.f12558y.getChild_tab();
                if (this.f12559z.size() >= 2) {
                    this.f12534C = this.f12559z.get(0).getType();
                    this.f12532A = this.f12559z.get(0).getName();
                    this.f12535D = this.f12559z.get(1).getType();
                    this.f12533B = this.f12559z.get(1).getName();
                } else if (this.f12559z.size() > 0 && this.f12559z.size() < 2) {
                    this.f12534C = this.f12559z.get(0).getType();
                    this.f12532A = this.f12559z.get(0).getName();
                }
            }
        }
        return layoutInflater.inflate(C2425R.layout.tp_second_fragment, viewGroup, false);
    }

    @Override // android.support.p009v4.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f12556w = (GiWiFiInfoExView) getActivity().findViewById(C2425R.C2427id.wifi_info_layout);
        this.f12556w.mo27113a(this.f12537F);
        this.f12554u = view.findViewById(C2425R.C2427id.include);
        this.f12557x = (ImageView) this.f12554u.findViewById(C2425R.C2427id.wifi_state_iv);
        this.f12555v = (LinearLayout) this.f12554u.findViewById(C2425R.C2427id.wifi_state_ll);
        this.f12555v.setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.template.fragment.SecondTabFragment.View$OnClickListenerC33311 */

            public void onClick(View view) {
                ((MainActivity) SecondTabFragment.this.getActivity()).showAndClose();
            }
        });
        this.f12554u = view.findViewById(C2425R.C2427id.include);
        this.f12541h = (TextView) this.f12554u.findViewById(2131755156);
        this.f12542i = (RadioGroup) this.f12554u.findViewById(C2425R.C2427id.title_rg);
        this.f12543j = (RadioButton) this.f12554u.findViewById(C2425R.C2427id.tite_rb1);
        this.f12544k = (RadioButton) this.f12554u.findViewById(C2425R.C2427id.tite_rb2);
        this.f12542i.setOnCheckedChangeListener(this);
        this.f12540g = (TextView) this.f12554u.findViewById(C2425R.C2427id.title_menu);
        this.f12540g.setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.template.fragment.SecondTabFragment.View$OnClickListenerC33322 */

            public void onClick(View view) {
                UIUtil.m14204b(view);
                ((MainActivity) SecondTabFragment.this.getActivity()).showPopWindow(GBApplication.instance());
            }
        });
        this.f12551r = (LinearLayout) view.findViewById(C2425R.C2427id.ll_tp_ad);
        this.f12552s = (ImageView) view.findViewById(C2425R.C2427id.iv_tp_ad);
        this.f12553t = (ImageView) view.findViewById(C2425R.C2427id.btn_tp_ad);
        this.f12553t.setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.template.fragment.SecondTabFragment.View$OnClickListenerC33333 */

            public void onClick(View view) {
                SecondTabFragment.this.f12551r.setVisibility(8);
            }
        });
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constant.f13238bM);
        GBApplication.instance().registerReceiver(this.f12536E, intentFilter);
        int a = VersionUtil.m14564a(getActivity());
        if (!CacheApp.getInstance().getSecondGuide() || a != CacheApp.getInstance().getSecondGuideCode().intValue()) {
            CacheApp.getInstance().setSecondGuide(true);
            CacheApp.getInstance().setSecondGuideCode(Integer.valueOf(a));
            final View inflate = ((ViewStub) getActivity().findViewById(C2425R.C2427id.vs_second)).inflate();
            ((RelativeLayout) ((LinearLayout) getActivity().findViewById(C2425R.C2427id.viewstub_second)).findViewById(C2425R.C2427id.rl_second)).setOnClickListener(new View.OnClickListener() {
                /* class com.gbcom.gwifi.functions.template.fragment.SecondTabFragment.View$OnClickListenerC33344 */

                public void onClick(View view) {
                    inflate.setVisibility(8);
                }
            });
        } else {
            m13672b();
        }
        if (this.f12559z.size() == 2) {
            this.f12543j.setText(this.f12532A);
            this.f12544k.setText(this.f12533B);
            m13669a();
        } else if (this.f12559z.size() == 1) {
            if (this.f12534C == 1) {
                m13671a(1, this.f12532A);
            } else {
                m13671a(1, "");
            }
        } else if (this.f12559z.size() == 0) {
            this.f12543j.setText("");
            m13671a(1, "");
        }
        this.f12556w.mo27103a(this.f12557x, BaseGiWiFiInfoView.EnumC3246p.m13296a(CacheWiFi.getInstance().getWifiControlState()));
    }

    /* renamed from: a */
    private void m13671a(int i, String str) {
        this.f12541h.setText(str);
        this.f12542i.setVisibility(8);
        m13670a(i);
    }

    /* renamed from: a */
    private void m13669a() {
        this.f12543j.setChecked(true);
        this.f12544k.setChecked(false);
        this.f12541h.setVisibility(8);
        this.f12542i.setVisibility(0);
        int afterAuthJumpTabType = CacheApp.getInstance().getAfterAuthJumpTabType();
        if (afterAuthJumpTabType == 1) {
            this.f12543j.setChecked(true);
        } else if (afterAuthJumpTabType == 4) {
            this.f12544k.setChecked(true);
        } else {
            this.f12543j.setChecked(true);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: b */
    private void m13672b() {
        List<HashMap<String, Object>> adByLayoutType;
        if (!(this.f12551r.getVisibility() == 0 || (adByLayoutType = CacheAd.getInstance().getAdByLayoutType(f12528a)) == null || adByLayoutType.size() == 0)) {
            for (HashMap<String, Object> hashMap : adByLayoutType) {
                this.f12552s.getLayoutParams().height = DensityUtil.m14127a(getActivity());
                ImageTools.m14152a((String) hashMap.get("localImgUrl"), this.f12552s, GBApplication.instance().options, ImageLoader.DEALIMAGE.FILLWIDTH);
                this.f12551r.setVisibility(0);
                this.f12552s.setTag(new CommonGiWiFiAdClickListener(getActivity(), hashMap, 0));
                this.f12552s.setOnClickListener(new View.OnClickListener() {
                    /* class com.gbcom.gwifi.functions.template.fragment.SecondTabFragment.View$OnClickListenerC33355 */

                    public void onClick(View view) {
                        ((CommonGiWiFiAdClickListener) view.getTag()).onClick(view);
                        SecondTabFragment.this.f12551r.setVisibility(8);
                    }
                });
            }
        }
    }

    /* renamed from: a */
    private void m13670a(int i) {
        FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
        if (i == 2) {
            if (this.f12550q == null) {
                this.f12550q = new NewsFragment();
                beginTransaction.add(C2425R.C2427id.fl_content, this.f12550q, "news");
            } else {
                beginTransaction.show(this.f12550q);
            }
            if (this.f12549p != null) {
                beginTransaction.hide(this.f12549p);
            }
            this.f12538d = 1;
        } else {
            if (this.f12549p == null) {
                this.f12549p = new PostFragment();
                beginTransaction.add(C2425R.C2427id.fl_content, this.f12549p, "post");
            } else {
                beginTransaction.show(this.f12549p);
            }
            if (this.f12550q != null) {
                beginTransaction.hide(this.f12550q);
            }
            this.f12538d = 2;
        }
        beginTransaction.commitAllowingStateLoss();
    }

    @Override // com.gbcom.gwifi.base.activity.GBFragment, android.support.p009v4.app.Fragment
    public void onResume() {
        super.onResume();
        GiwifiMobclickAgent.onPageStart("娱乐界面");
    }

    @Override // com.gbcom.gwifi.base.activity.GBFragment, android.support.p009v4.app.Fragment
    public void onPause() {
        super.onPause();
        GiwifiMobclickAgent.onPageEnd("娱乐界面");
    }

    @Override // android.support.p009v4.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        GBApplication.instance().unregisterReceiver(this.f12536E);
    }

    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if (i == this.f12543j.getId()) {
            if (this.f12534C == 1) {
                m13673b(1);
            } else {
                m13673b(2);
            }
            this.f12543j.setTextColor(getResources().getColor(2131624113));
            this.f12544k.setTextColor(getResources().getColor(C2425R.color.black));
            this.f12543j.setBackgroundResource(C2425R.C2426drawable.left_sel);
            this.f12544k.setBackgroundResource(C2425R.C2426drawable.right_nosel);
        } else if (i == this.f12544k.getId()) {
            if (this.f12535D == 1) {
                m13673b(1);
            } else {
                m13673b(2);
            }
            this.f12543j.setTextColor(getResources().getColor(C2425R.color.black));
            this.f12544k.setTextColor(getResources().getColor(2131624113));
            this.f12543j.setBackgroundResource(C2425R.C2426drawable.left_nosel);
            this.f12544k.setBackgroundResource(C2425R.C2426drawable.right_sel);
        }
    }

    /* renamed from: b */
    private void m13673b(int i) {
        switch (i) {
            case 1:
                m13670a(1);
                return;
            case 2:
                m13670a(2);
                return;
            default:
                return;
        }
    }

    @Override // android.support.p009v4.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (z && this.f12549p != null) {
            this.f12549p.mo26983a();
        }
    }
}
