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
import com.gbcom.gwifi.util.SystemUtil;
import java.util.Random;

public class LoadingView extends RelativeLayout {

    /* renamed from: a */
    private Context f13671a;

    /* renamed from: b */
    private View f13672b;

    /* renamed from: c */
    private ImageView f13673c;

    /* renamed from: d */
    private ImageView f13674d;

    /* renamed from: e */
    private AnimationDrawable f13675e;

    /* renamed from: f */
    private ImageView f13676f;

    /* renamed from: g */
    private AnimationDrawable f13677g;

    /* renamed from: h */
    private TextView f13678h;

    /* renamed from: i */
    private Button f13679i;

    /* renamed from: j */
    private AbstractC3488a f13680j;

    /* renamed from: k */
    private final int[] f13681k = {C2425R.C2426drawable.loading_1, C2425R.C2426drawable.loading_2, C2425R.C2426drawable.loading_3, C2425R.C2426drawable.loading_4, C2425R.C2426drawable.loading_5, C2425R.C2426drawable.loading_6, C2425R.C2426drawable.loading_7, C2425R.C2426drawable.loading_8};

    /* renamed from: com.gbcom.gwifi.widget.LoadingView$a */
    public interface AbstractC3488a {
        /* renamed from: a */
        void mo24655a(View view);
    }

    public LoadingView(Context context) {
        super(context);
        m14700a(context);
    }

    public LoadingView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m14700a(context);
    }

    public LoadingView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m14700a(context);
    }

    /* renamed from: a */
    private void m14700a(Context context) {
        this.f13671a = context;
        this.f13672b = LayoutInflater.from(context).inflate(C2425R.layout.check_reload_layout_new, (ViewGroup) null);
        this.f13673c = (ImageView) this.f13672b.findViewById(C2425R.C2427id.loading_icon);
        this.f13674d = (ImageView) this.f13672b.findViewById(C2425R.C2427id.loading_point);
        this.f13675e = (AnimationDrawable) this.f13674d.getBackground();
        this.f13676f = (ImageView) this.f13672b.findViewById(C2425R.C2427id.loading_icon_error);
        this.f13677g = (AnimationDrawable) this.f13676f.getBackground();
        this.f13678h = (TextView) this.f13672b.findViewById(C2425R.C2427id.loading_tv_error);
        this.f13679i = (Button) this.f13672b.findViewById(C2425R.C2427id.loading_btn_error);
        this.f13679i.setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.widget.LoadingView.View$OnClickListenerC34871 */

            public void onClick(View view) {
                if (LoadingView.this.f13680j != null) {
                    LoadingView.this.f13680j.mo24655a(view);
                }
            }
        });
        this.f13672b.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        addView(this.f13672b);
    }

    /* renamed from: a */
    public void mo28297a() {
        if (!SystemUtil.m14531e()) {
            this.f13673c.setImageResource(this.f13681k[new Random().nextInt(8)]);
        }
    }

    /* renamed from: b */
    public void mo28300b() {
        this.f13675e.start();
    }

    /* renamed from: c */
    public void mo28301c() {
        this.f13675e.stop();
    }

    /* renamed from: d */
    public void mo28302d() {
        this.f13673c.setVisibility(0);
    }

    /* renamed from: e */
    public void mo28303e() {
        this.f13673c.setVisibility(8);
    }

    /* renamed from: f */
    public void mo28304f() {
        this.f13674d.setVisibility(0);
    }

    /* renamed from: g */
    public void mo28305g() {
        this.f13674d.setVisibility(8);
    }

    /* renamed from: h */
    public void mo28306h() {
        this.f13676f.setVisibility(0);
    }

    /* renamed from: i */
    public void mo28307i() {
        this.f13676f.setVisibility(8);
    }

    /* renamed from: j */
    public void mo28308j() {
        this.f13677g.start();
    }

    /* renamed from: k */
    public void mo28309k() {
        this.f13677g.stop();
    }

    /* renamed from: l */
    public boolean mo28310l() {
        return this.f13677g.isRunning();
    }

    /* renamed from: m */
    public int mo28311m() {
        return this.f13676f.getVisibility();
    }

    /* renamed from: n */
    public void mo28312n() {
        this.f13678h.setVisibility(0);
    }

    /* renamed from: o */
    public void mo28313o() {
        this.f13678h.setVisibility(8);
    }

    /* renamed from: a */
    public void mo28299a(String str) {
        this.f13678h.setText(str);
    }

    /* renamed from: p */
    public void mo28314p() {
        this.f13679i.setVisibility(0);
    }

    /* renamed from: q */
    public void mo28315q() {
        this.f13679i.setVisibility(8);
    }

    /* renamed from: a */
    public void mo28298a(AbstractC3488a aVar) {
        this.f13680j = aVar;
    }
}
