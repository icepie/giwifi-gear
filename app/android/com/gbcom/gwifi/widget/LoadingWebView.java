package com.gbcom.gwifi.widget;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.widget.LoadingView;
import java.util.Random;

public class LoadingWebView extends RelativeLayout {

    /* renamed from: a */
    private Context f13683a;

    /* renamed from: b */
    private View f13684b;

    /* renamed from: c */
    private ImageView f13685c;

    /* renamed from: d */
    private ImageView f13686d;

    /* renamed from: e */
    private AnimationDrawable f13687e;

    /* renamed from: f */
    private ImageView f13688f;

    /* renamed from: g */
    private AnimationDrawable f13689g;

    /* renamed from: h */
    private TextView f13690h;

    /* renamed from: i */
    private Button f13691i;

    /* renamed from: j */
    private LoadingView.AbstractC3488a f13692j;

    /* renamed from: k */
    private final int[] f13693k = {C2425R.C2426drawable.loading_1, C2425R.C2426drawable.loading_2, C2425R.C2426drawable.loading_3, C2425R.C2426drawable.loading_4, C2425R.C2426drawable.loading_5, C2425R.C2426drawable.loading_6, C2425R.C2426drawable.loading_7, C2425R.C2426drawable.loading_8};

    /* renamed from: com.gbcom.gwifi.widget.LoadingWebView$a */
    public interface AbstractC3490a {
        /* renamed from: a */
        void mo28337a(View view);
    }

    public LoadingWebView(Context context) {
        super(context);
        m14722a(context);
    }

    public LoadingWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m14722a(context);
    }

    public LoadingWebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m14722a(context);
    }

    /* renamed from: a */
    private void m14722a(Context context) {
        this.f13683a = context;
        this.f13684b = LayoutInflater.from(context).inflate(C2425R.layout.check_reload_layout_web_new, (ViewGroup) null);
        this.f13685c = (ImageView) this.f13684b.findViewById(C2425R.C2427id.loading_icon);
        this.f13686d = (ImageView) this.f13684b.findViewById(C2425R.C2427id.loading_point);
        this.f13687e = (AnimationDrawable) this.f13686d.getBackground();
        this.f13688f = (ImageView) this.f13684b.findViewById(C2425R.C2427id.loading_icon_error);
        this.f13689g = (AnimationDrawable) this.f13688f.getBackground();
        this.f13690h = (TextView) this.f13684b.findViewById(C2425R.C2427id.loading_tv_error);
        this.f13691i = (Button) this.f13684b.findViewById(C2425R.C2427id.loading_btn_error);
        this.f13691i.setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.widget.LoadingWebView.View$OnClickListenerC34891 */

            public void onClick(View view) {
                if (LoadingWebView.this.f13692j != null) {
                    LoadingWebView.this.f13692j.mo24655a(view);
                }
            }
        });
        this.f13684b.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        addView(this.f13684b);
    }

    /* renamed from: a */
    public void mo28317a() {
        this.f13685c.setImageResource(this.f13693k[new Random().nextInt(8)]);
    }

    /* renamed from: b */
    public void mo28320b() {
        this.f13687e.start();
    }

    /* renamed from: c */
    public void mo28321c() {
        this.f13687e.stop();
    }

    /* renamed from: d */
    public void mo28322d() {
        this.f13685c.setVisibility(0);
    }

    /* renamed from: e */
    public void mo28323e() {
        this.f13685c.setVisibility(8);
    }

    /* renamed from: f */
    public void mo28324f() {
        this.f13686d.setVisibility(0);
    }

    /* renamed from: g */
    public void mo28325g() {
        this.f13686d.setVisibility(8);
    }

    /* renamed from: h */
    public void mo28326h() {
        this.f13688f.setVisibility(0);
    }

    /* renamed from: i */
    public void mo28327i() {
        this.f13688f.setVisibility(8);
    }

    /* renamed from: j */
    public void mo28328j() {
        this.f13689g.start();
    }

    /* renamed from: k */
    public void mo28329k() {
        this.f13689g.stop();
    }

    /* renamed from: l */
    public boolean mo28330l() {
        return this.f13689g.isRunning();
    }

    /* renamed from: m */
    public int mo28331m() {
        return this.f13688f.getVisibility();
    }

    /* renamed from: n */
    public void mo28332n() {
        this.f13690h.setVisibility(0);
    }

    /* renamed from: o */
    public void mo28333o() {
        this.f13690h.setVisibility(8);
    }

    /* renamed from: a */
    public void mo28319a(String str) {
        this.f13690h.setText(str);
    }

    /* renamed from: p */
    public void mo28334p() {
        this.f13691i.setVisibility(0);
    }

    /* renamed from: q */
    public void mo28335q() {
        this.f13691i.setVisibility(8);
    }

    /* renamed from: a */
    public void mo28318a(LoadingView.AbstractC3488a aVar) {
        this.f13692j = aVar;
    }
}
