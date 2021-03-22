package com.gbcom.gwifi.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.util.p257b.DensityUtil;

public class XListViewFooter extends LinearLayout {

    /* renamed from: a */
    public static final int f13833a = 0;

    /* renamed from: b */
    public static final int f13834b = 1;

    /* renamed from: c */
    public static final int f13835c = 2;

    /* renamed from: d */
    public static final int f13836d = 3;

    /* renamed from: e */
    private int f13837e = 0;

    /* renamed from: f */
    private Context f13838f;

    /* renamed from: g */
    private View f13839g;

    /* renamed from: h */
    private CircularProgressBar f13840h;

    /* renamed from: i */
    private TextView f13841i;

    /* renamed from: j */
    private ImageView f13842j;

    /* renamed from: k */
    private RelativeLayout f13843k;

    /* renamed from: l */
    private Animation f13844l;

    /* renamed from: m */
    private Animation f13845m;

    /* renamed from: n */
    private final int f13846n = 180;

    /* renamed from: o */
    private RelativeLayout f13847o;

    /* renamed from: p */
    private int f13848p;

    public XListViewFooter(Context context) {
        super(context);
        m14851a(context);
    }

    public XListViewFooter(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m14851a(context);
    }

    /* renamed from: a */
    public int mo28439a() {
        return this.f13848p;
    }

    /* renamed from: a */
    public void mo28440a(int i) {
        if (i != this.f13837e) {
            switch (i) {
                case 0:
                    this.f13839g.setVisibility(0);
                    this.f13843k.setVisibility(4);
                    this.f13841i.setVisibility(0);
                    this.f13841i.setText(C2425R.C2429string.pull_to_refresh_footer_pull_label);
                    this.f13842j.setVisibility(0);
                    this.f13840h.setVisibility(4);
                    if (this.f13837e == 1) {
                        this.f13842j.startAnimation(this.f13845m);
                    }
                    if (this.f13837e == 2) {
                        this.f13842j.clearAnimation();
                        break;
                    }
                    break;
                case 1:
                    this.f13839g.setVisibility(0);
                    this.f13843k.setVisibility(4);
                    this.f13841i.setVisibility(0);
                    this.f13841i.setText(C2425R.C2429string.pull_to_refresh_footer_release_label);
                    this.f13842j.setVisibility(0);
                    this.f13840h.setVisibility(4);
                    this.f13842j.clearAnimation();
                    this.f13842j.startAnimation(this.f13844l);
                    break;
                case 2:
                    this.f13840h.setVisibility(0);
                    this.f13839g.setVisibility(0);
                    this.f13843k.setVisibility(4);
                    this.f13841i.setVisibility(4);
                    this.f13842j.clearAnimation();
                    this.f13842j.setVisibility(4);
                    break;
                case 3:
                    this.f13839g.setVisibility(4);
                    this.f13843k.setVisibility(0);
                    break;
            }
            this.f13837e = i;
        }
    }

    /* renamed from: b */
    public void mo28442b(int i) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.f13847o.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new LinearLayout.LayoutParams(-1, -2);
        }
        layoutParams.bottomMargin = i;
        this.f13847o.setLayoutParams(layoutParams);
    }

    /* renamed from: b */
    public int mo28441b() {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.f13847o.getLayoutParams();
        if (layoutParams == null) {
            return 0;
        }
        return layoutParams.bottomMargin;
    }

    /* renamed from: a */
    private void m14851a(Context context) {
        this.f13838f = context;
        this.f13847o = (RelativeLayout) LayoutInflater.from(this.f13838f).inflate(C2425R.layout.common_xlistview_footer, (ViewGroup) null);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, 0);
        layoutParams.height = DensityUtil.m14128a(context, 70.0f);
        this.f13848p = layoutParams.height;
        addView(this.f13847o, layoutParams);
        this.f13839g = this.f13847o.findViewById(C2425R.C2427id.xlistview_footer_content);
        this.f13840h = (CircularProgressBar) findViewById(2131755320);
        this.f13843k = (RelativeLayout) findViewById(C2425R.C2427id.fail_rl);
        this.f13841i = (TextView) this.f13847o.findViewById(C2425R.C2427id.xlistview_footer_hint_textview);
        this.f13842j = (ImageView) this.f13847o.findViewById(C2425R.C2427id.xlistview_header_arrow);
        this.f13844l = new RotateAnimation(0.0f, -180.0f, 1, 0.5f, 1, 0.5f);
        this.f13844l.setDuration(180);
        this.f13844l.setFillAfter(true);
        this.f13845m = new RotateAnimation(-180.0f, 0.0f, 1, 0.5f, 1, 0.5f);
        this.f13845m.setDuration(180);
        this.f13845m.setFillAfter(true);
        mo28442b(-this.f13848p);
    }
}
