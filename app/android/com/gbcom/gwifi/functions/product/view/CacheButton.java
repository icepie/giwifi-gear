package com.gbcom.gwifi.functions.product.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.gbcom.gwifi.C2425R;

public class CacheButton extends RelativeLayout {

    /* renamed from: a */
    private Button f10805a;

    /* renamed from: b */
    private ImageView f10806b;

    /* renamed from: c */
    private EnumC2945a f10807c = EnumC2945a.NORMAL;

    /* renamed from: d */
    private AbstractC2946b f10808d;

    /* renamed from: com.gbcom.gwifi.functions.product.view.CacheButton$a */
    public enum EnumC2945a {
        NORMAL,
        CACHEING,
        FINISH
    }

    /* renamed from: com.gbcom.gwifi.functions.product.view.CacheButton$b */
    public interface AbstractC2946b {
        /* renamed from: a */
        void mo25382a(View view);
    }

    public CacheButton(Context context) {
        super(context);
    }

    public CacheButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m12212a(context, attributeSet);
    }

    public CacheButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setEnabled(boolean z) {
        super.setEnabled(z);
        this.f10805a.setEnabled(z);
    }

    /* renamed from: a */
    private void m12212a(Context context, AttributeSet attributeSet) {
        this.f10805a = new Button(context, attributeSet);
        this.f10805a.setBackgroundResource(C2425R.C2426drawable.gi_bg_grey);
        this.f10805a.setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.product.view.CacheButton.View$OnClickListenerC29431 */

            public void onClick(View view) {
                if (CacheButton.this.f10808d != null) {
                    CacheButton.this.f10808d.mo25382a(CacheButton.this);
                }
            }
        });
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.setMargins(0, 0, 20, 0);
        addView(this.f10805a, layoutParams);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(12);
        layoutParams2.addRule(11);
        this.f10806b = new ImageView(context, attributeSet);
        this.f10806b.setVisibility(8);
        addView(this.f10806b, layoutParams2);
    }

    /* renamed from: g */
    private EnumC2945a m12214g() {
        return this.f10807c;
    }

    /* renamed from: a */
    private void m12213a(EnumC2945a aVar) {
        this.f10807c = aVar;
        switch (aVar) {
            case NORMAL:
                this.f10806b.setVisibility(8);
                return;
            case CACHEING:
                this.f10806b.setVisibility(0);
                this.f10806b.setImageResource(C2425R.C2426drawable.catching);
                return;
            case FINISH:
                this.f10806b.setVisibility(0);
                this.f10806b.setImageResource(C2425R.C2426drawable.catched);
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    public void mo25916a() {
        m12213a(EnumC2945a.NORMAL);
    }

    /* renamed from: b */
    public void mo25919b() {
        m12213a(EnumC2945a.CACHEING);
    }

    /* renamed from: c */
    public void mo25920c() {
        m12213a(EnumC2945a.FINISH);
    }

    /* renamed from: a */
    public void mo25918a(String str) {
        this.f10805a.setText(str.toString());
    }

    /* renamed from: a */
    public void mo25917a(AbstractC2946b bVar) {
        this.f10808d = bVar;
    }

    /* renamed from: d */
    public boolean mo25921d() {
        return EnumC2945a.NORMAL == this.f10807c;
    }

    /* renamed from: e */
    public boolean mo25922e() {
        return EnumC2945a.CACHEING == this.f10807c;
    }

    /* renamed from: f */
    public boolean mo25923f() {
        return EnumC2945a.FINISH == this.f10807c;
    }
}
