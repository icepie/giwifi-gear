package com.gbcom.gwifi.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.util.p257b.DensityUtil;

public class XListViewHeader extends LinearLayout {

    /* renamed from: a */
    public static final int f13849a = 0;

    /* renamed from: b */
    public static final int f13850b = 1;

    /* renamed from: c */
    public static final int f13851c = 2;

    /* renamed from: d */
    public static final int f13852d = 3;

    /* renamed from: e */
    private RelativeLayout f13853e;

    /* renamed from: f */
    private RelativeLayout f13854f;

    /* renamed from: g */
    private RelativeLayout f13855g;

    /* renamed from: h */
    private ImageView f13856h;

    /* renamed from: i */
    private CircularProgressBar f13857i;

    /* renamed from: j */
    private TextView f13858j;

    /* renamed from: k */
    private Animation f13859k;

    /* renamed from: l */
    private Animation f13860l;

    /* renamed from: m */
    private final int f13861m = 180;

    /* renamed from: n */
    private int f13862n = 0;

    /* renamed from: o */
    private int f13863o;

    /* renamed from: p */
    private Context f13864p;

    public XListViewHeader(Context context) {
        super(context);
        m14856a(context);
    }

    public XListViewHeader(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m14856a(context);
    }

    /* renamed from: a */
    private void m14856a(Context context) {
        this.f13853e = (RelativeLayout) LayoutInflater.from(context).inflate(C2425R.layout.common_xlistview_header, (ViewGroup) null);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, 0);
        layoutParams.height = DensityUtil.m14128a(context, 60.0f);
        this.f13863o = layoutParams.height;
        addView(this.f13853e, layoutParams);
        setGravity(80);
        this.f13856h = (ImageView) findViewById(C2425R.C2427id.xlistview_header_arrow);
        this.f13854f = (RelativeLayout) findViewById(C2425R.C2427id.xlistview_header_content);
        this.f13855g = (RelativeLayout) findViewById(C2425R.C2427id.fail_rl);
        this.f13858j = (TextView) findViewById(C2425R.C2427id.xlistview_header_hint_textview);
        this.f13857i = (CircularProgressBar) findViewById(C2425R.C2427id.circular_progress);
        this.f13859k = new RotateAnimation(0.0f, -180.0f, 1, 0.5f, 1, 0.5f);
        this.f13859k.setDuration(180);
        this.f13859k.setFillAfter(true);
        this.f13860l = new RotateAnimation(-180.0f, 0.0f, 1, 0.5f, 1, 0.5f);
        this.f13860l.setDuration(180);
        this.f13860l.setFillAfter(true);
        mo28446b(-this.f13863o);
    }

    /* renamed from: a */
    public int mo28443a() {
        return this.f13863o;
    }

    /* renamed from: a */
    public void mo28444a(int i) {
        if (i != this.f13862n) {
            switch (i) {
                case 0:
                    this.f13854f.setVisibility(0);
                    this.f13855g.setVisibility(4);
                    this.f13856h.setVisibility(0);
                    this.f13857i.setVisibility(4);
                    if (this.f13862n == 1) {
                        this.f13856h.startAnimation(this.f13860l);
                    }
                    if (this.f13862n == 2) {
                        this.f13856h.clearAnimation();
                    }
                    this.f13858j.setText(C2425R.C2429string.pull_to_refresh_pull_label);
                    break;
                case 1:
                    this.f13854f.setVisibility(0);
                    this.f13855g.setVisibility(4);
                    this.f13856h.setVisibility(0);
                    this.f13857i.setVisibility(4);
                    this.f13856h.clearAnimation();
                    this.f13856h.startAnimation(this.f13859k);
                    this.f13858j.setText(C2425R.C2429string.pull_to_refresh_release_label);
                    break;
                case 2:
                    this.f13854f.setVisibility(0);
                    this.f13855g.setVisibility(4);
                    this.f13856h.clearAnimation();
                    this.f13856h.setVisibility(4);
                    this.f13857i.setVisibility(0);
                    this.f13858j.setText(C2425R.C2429string.loading);
                    break;
                case 3:
                    this.f13854f.setVisibility(4);
                    this.f13856h.clearAnimation();
                    this.f13855g.setVisibility(0);
                    break;
            }
            this.f13862n = i;
        }
    }

    /* renamed from: b */
    public void mo28446b(int i) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.f13853e.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new LinearLayout.LayoutParams(-1, -2);
        }
        layoutParams.topMargin = i;
        this.f13853e.setLayoutParams(layoutParams);
    }

    /* renamed from: b */
    public int mo28445b() {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.f13853e.getLayoutParams();
        if (layoutParams == null) {
            return 0;
        }
        return layoutParams.topMargin;
    }
}
