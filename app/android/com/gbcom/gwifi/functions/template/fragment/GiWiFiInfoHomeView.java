package com.gbcom.gwifi.functions.template.fragment;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.util.p257b.DensityUtil;

public class GiWiFiInfoHomeView extends BaseGiWiFiInfoView {

    /* renamed from: g */
    private View f12212g;

    /* renamed from: bd */
    private void m13352bd() {
        this.f12212g.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        addView(this.f12212g);
    }

    public GiWiFiInfoHomeView(Context context) {
        super(context);
        m13352bd();
    }

    public GiWiFiInfoHomeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m13352bd();
    }

    public GiWiFiInfoHomeView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m13352bd();
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView
    /* renamed from: a */
    public View mo27099a(Context context) {
        this.f12212g = LayoutInflater.from(context).inflate(C2425R.layout.fragment_home_wifi_info, (ViewGroup) null);
        return this.f12212g;
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView
    /* renamed from: a */
    public RelativeLayout mo27100a() {
        return null;
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView
    /* renamed from: b */
    public LinearLayout mo27175b() {
        return (LinearLayout) this.f12212g.findViewById(C2425R.C2427id.wifi_image);
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView
    /* renamed from: c */
    public Button mo27180c() {
        return (Button) this.f12212g.findViewById(C2425R.C2427id.one_key_connect_btn);
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView
    /* renamed from: d */
    public int mo27182d() {
        return C2425R.C2426drawable.gi_btn_bg_common;
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView
    /* renamed from: e */
    public int mo27184e() {
        return C2425R.C2426drawable.gi_btn_bg_grey;
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView
    /* renamed from: f */
    public Button mo27186f() {
        return (Button) this.f12212g.findViewById(C2425R.C2427id.wifi_state_bt);
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView
    /* renamed from: g */
    public Button mo27188g() {
        return (Button) this.f12212g.findViewById(C2425R.C2427id.wifi_check_network_btn);
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView
    /* renamed from: h */
    public Button mo27189h() {
        return (Button) this.f12212g.findViewById(C2425R.C2427id.connect_gwifi);
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView
    /* renamed from: i */
    public LinearLayout mo27190i() {
        return null;
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView
    /* renamed from: j */
    public TextView mo27191j() {
        return (TextView) this.f12212g.findViewById(C2425R.C2427id.wifi_state_info_tv);
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView
    /* renamed from: k */
    public LinearLayout mo27192k() {
        return (LinearLayout) this.f12212g.findViewById(C2425R.C2427id.wifi_fragment_online_layout);
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView
    /* renamed from: l */
    public TextView mo27193l() {
        return (TextView) this.f12212g.findViewById(C2425R.C2427id.connect_time_tv);
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView
    /* renamed from: m */
    public TextView mo27194m() {
        return (TextView) this.f12212g.findViewById(C2425R.C2427id.no_net_tv);
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView
    /* renamed from: n */
    public TextView mo27195n() {
        return (TextView) this.f12212g.findViewById(2131755156);
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView
    /* renamed from: o */
    public ImageView mo27196o() {
        return null;
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView
    /* renamed from: p */
    public ImageView mo27197p() {
        return null;
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView
    /* renamed from: q */
    public ImageView mo27198q() {
        return null;
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView
    /* renamed from: r */
    public ImageView mo27199r() {
        return null;
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView
    /* renamed from: s */
    public ImageView mo27200s() {
        return null;
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView
    /* renamed from: t */
    public ImageView mo27201t() {
        return null;
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView
    /* renamed from: u */
    public ImageView mo27202u() {
        return null;
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView
    /* renamed from: v */
    public ImageView mo27203v() {
        return null;
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView
    /* renamed from: w */
    public ImageView mo27204w() {
        return null;
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView
    /* renamed from: x */
    public ImageView mo27205x() {
        return (ImageView) this.f12212g.findViewById(C2425R.C2427id.wifi_0_title);
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView
    /* renamed from: y */
    public ImageView mo27206y() {
        return (ImageView) this.f12212g.findViewById(C2425R.C2427id.wifi_1_title);
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView
    /* renamed from: z */
    public ImageView mo27207z() {
        return (ImageView) this.f12212g.findViewById(C2425R.C2427id.wifi_2_title);
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView
    /* renamed from: A */
    public ImageView mo27073A() {
        return (ImageView) this.f12212g.findViewById(C2425R.C2427id.wifi_3_title);
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView
    /* renamed from: B */
    public ImageView mo27074B() {
        return (ImageView) this.f12212g.findViewById(C2425R.C2427id.wifi_4_title);
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView
    /* renamed from: C */
    public ImageView mo27075C() {
        return (ImageView) this.f12212g.findViewById(C2425R.C2427id.wifi_5_title);
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView
    /* renamed from: D */
    public ImageView mo27076D() {
        return (ImageView) this.f12212g.findViewById(C2425R.C2427id.wifi_6_title);
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView
    /* renamed from: E */
    public ImageView mo27077E() {
        return (ImageView) this.f12212g.findViewById(C2425R.C2427id.wifi_7_title);
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView
    /* renamed from: F */
    public ImageView mo27078F() {
        return (ImageView) this.f12212g.findViewById(C2425R.C2427id.mobile_network_title);
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView
    /* renamed from: G */
    public LinearLayout mo27079G() {
        return null;
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView
    /* renamed from: H */
    public LinearLayout mo27080H() {
        return null;
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView
    /* renamed from: I */
    public LinearLayout mo27081I() {
        return null;
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView
    /* renamed from: J */
    public ImageView mo27082J() {
        return null;
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView
    /* renamed from: K */
    public ImageView mo27083K() {
        return null;
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView
    /* renamed from: L */
    public ImageView mo27084L() {
        return null;
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView
    /* renamed from: M */
    public TextView mo27085M() {
        return null;
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView
    /* renamed from: N */
    public TextView mo27086N() {
        return null;
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView
    /* renamed from: O */
    public TextView mo27087O() {
        return null;
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView
    /* renamed from: P */
    public TextView mo27088P() {
        return null;
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView
    /* renamed from: Q */
    public ViewGroup mo27089Q() {
        return (ViewGroup) this.f12212g.findViewById(C2425R.C2427id.game_btn_layout);
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView
    /* renamed from: R */
    public ViewGroup mo27090R() {
        return (ViewGroup) this.f12212g.findViewById(C2425R.C2427id.game_help_layout);
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView
    /* renamed from: S */
    public Button mo27091S() {
        return (Button) this.f12212g.findViewById(C2425R.C2427id.game_btn);
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView
    /* renamed from: T */
    public ImageView mo27092T() {
        return (ImageView) this.f12212g.findViewById(C2425R.C2427id.game_help);
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView
    /* renamed from: aY */
    public void mo27147aY() {
        Button c = mo27180c();
        Button f = mo27186f();
        Button g = mo27188g();
        Button h = mo27189h();
        ViewGroup.LayoutParams layoutParams = h.getLayoutParams();
        layoutParams.width = DensityUtil.m14129a(getResources(), 250);
        c.setLayoutParams(layoutParams);
        f.setLayoutParams(layoutParams);
        g.setLayoutParams(layoutParams);
        h.setLayoutParams(layoutParams);
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView
    /* renamed from: aZ */
    public void mo27148aZ() {
        Button c = mo27180c();
        Button f = mo27186f();
        Button g = mo27188g();
        Button h = mo27189h();
        ViewGroup.LayoutParams layoutParams = h.getLayoutParams();
        layoutParams.width = DensityUtil.m14129a(getResources(), 125);
        c.setLayoutParams(layoutParams);
        f.setLayoutParams(layoutParams);
        g.setLayoutParams(layoutParams);
        h.setLayoutParams(layoutParams);
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView
    /* renamed from: U */
    public ViewGroup mo27093U() {
        return (ViewGroup) this.f12212g.findViewById(C2425R.C2427id.common_help_layout);
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView
    /* renamed from: V */
    public ImageView mo27094V() {
        return (ImageView) this.f12212g.findViewById(C2425R.C2427id.common_help);
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView
    /* renamed from: W */
    public LinearLayout mo27095W() {
        return (LinearLayout) this.f12212g.findViewById(C2425R.C2427id.rl_wifi_authority);
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.functions.template.fragment.BaseGiWiFiInfoView
    /* renamed from: X */
    public TextView mo27096X() {
        return (TextView) this.f12212g.findViewById(C2425R.C2427id.wifi_authority);
    }
}
