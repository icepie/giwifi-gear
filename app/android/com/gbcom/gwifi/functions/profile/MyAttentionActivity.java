package com.gbcom.gwifi.functions.profile;

import android.os.Bundle;
import android.support.p009v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.functions.profile.p250a.TabsAdapter;
import com.gbcom.gwifi.util.Log;

public class MyAttentionActivity extends GBActivity implements View.OnClickListener {

    /* renamed from: a */
    private static final String f10833a = "MyAttentionActivity";

    /* renamed from: b */
    private RelativeLayout f10834b;

    /* renamed from: c */
    private TextView f10835c;

    /* renamed from: d */
    private TextView f10836d;

    /* renamed from: e */
    private TabHost f10837e;

    /* renamed from: f */
    private TabWidget f10838f;

    /* renamed from: g */
    private ViewPager f10839g;

    /* renamed from: h */
    private TabsAdapter f10840h;

    /* renamed from: i */
    private LinearLayout f10841i;

    /* renamed from: j */
    private LinearLayout f10842j;

    /* renamed from: k */
    private LinearLayout f10843k;

    /* renamed from: l */
    private LinearLayout f10844l;

    /* renamed from: m */
    private boolean f10845m = false;

    @Override // android.support.p009v4.app.BaseFragmentActivityGingerbread, com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle, "我的关注界面", C2425R.layout.my_attention_tabs, true);
        m12242a();
    }

    /* renamed from: a */
    private void m12242a() {
        this.f10834b = (RelativeLayout) findViewById(C2425R.C2427id.title_back_layout);
        this.f10834b.setOnClickListener(this);
        this.f10836d = (TextView) findViewById(C2425R.C2427id.title_main_tv);
        this.f10835c = (TextView) findViewById(C2425R.C2427id.title_edit_tv);
        this.f10836d.setText("我的关注");
        this.f10835c.setOnClickListener(this);
        this.f10837e = (TabHost) findViewById(16908306);
        this.f10838f = (TabWidget) findViewById(16908307);
        this.f10839g = (ViewPager) findViewById(C2425R.C2427id.viewPager);
        this.f10839g.setOffscreenPageLimit(1);
        this.f10841i = (LinearLayout) LayoutInflater.from(this).inflate(C2425R.layout.tab_video, (ViewGroup) this.f10838f, false);
        this.f10842j = (LinearLayout) LayoutInflater.from(this).inflate(C2425R.layout.tab_game, (ViewGroup) this.f10838f, false);
        this.f10843k = (LinearLayout) LayoutInflater.from(this).inflate(C2425R.layout.tab_book, (ViewGroup) this.f10838f, false);
        this.f10844l = (LinearLayout) LayoutInflater.from(this).inflate(C2425R.layout.tab_app, (ViewGroup) this.f10838f, false);
        this.f10837e.setup();
        this.f10840h = new TabsAdapter(this, this.f10837e, this.f10839g);
        this.f10840h.mo25989a(this.f10837e.newTabSpec("video").setIndicator(this.f10841i), new MyVideoFragment(), null);
        this.f10840h.mo25989a(this.f10837e.newTabSpec("game").setIndicator(this.f10842j), new MyGameFragment(), null);
        this.f10840h.mo25989a(this.f10837e.newTabSpec("book").setIndicator(this.f10843k), new MyBookFragment(), null);
        this.f10840h.mo25989a(this.f10837e.newTabSpec("app").setIndicator(this.f10844l), new MyAppFragment(), null);
        this.f10840h.mo25990a(new TabsAdapter.AbstractC2983b() {
            /* class com.gbcom.gwifi.functions.profile.MyAttentionActivity.C29501 */

            @Override // com.gbcom.gwifi.functions.profile.p250a.TabsAdapter.AbstractC2983b
            /* renamed from: a */
            public void mo25939a(String str) {
                MyAttentionActivity.this.m12244a(str);
            }
        });
        Log.m14400c(f10833a, this.f10840h.mo25992c());
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C2425R.C2427id.title_back_layout /*{ENCODED_INT: 2131755757}*/:
                finish();
                return;
            case C2425R.C2427id.title_back_iv /*{ENCODED_INT: 2131755758}*/:
            default:
                return;
            case C2425R.C2427id.title_edit_tv /*{ENCODED_INT: 2131755759}*/:
                m12245b();
                return;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m12244a(String str) {
        if (str.equals("video")) {
            ((MyVideoFragment) this.f10840h.mo25987a(str)).mo26012b();
        } else if (str.equals("game")) {
            ((MyGameFragment) this.f10840h.mo25987a(str)).mo26004b();
        } else if (str.equals("book")) {
            ((MyBookFragment) this.f10840h.mo25987a(str)).mo25996b();
        } else {
            ((MyAppFragment) this.f10840h.mo25987a(str)).mo25980b();
        }
    }

    /* renamed from: b */
    private void m12245b() {
        if (this.f10840h.mo25992c().equals("video")) {
            ((MyVideoFragment) this.f10840h.mo25991b()).mo26011a();
        } else if (this.f10840h.mo25992c().equals("game")) {
            ((MyGameFragment) this.f10840h.mo25991b()).mo26003a();
        } else if (this.f10840h.mo25992c().equals("book")) {
            ((MyBookFragment) this.f10840h.mo25991b()).mo25995a();
        } else {
            ((MyAppFragment) this.f10840h.mo25991b()).mo25979a();
        }
    }
}
