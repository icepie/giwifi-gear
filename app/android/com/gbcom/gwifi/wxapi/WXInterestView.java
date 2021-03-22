package com.gbcom.gwifi.wxapi;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.app.GBWindowManager;

public class WXInterestView extends LinearLayout {

    /* renamed from: a */
    public static int f13870a;

    /* renamed from: b */
    public static int f13871b;

    /* renamed from: c */
    private static int f13872c;

    /* renamed from: d */
    private WindowManager f13873d;

    /* renamed from: e */
    private WindowManager.LayoutParams f13874e;

    /* renamed from: f */
    private float f13875f;

    /* renamed from: g */
    private float f13876g;

    /* renamed from: h */
    private float f13877h;

    public WXInterestView(Context context) {
        this(context, null);
    }

    public WXInterestView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public WXInterestView(final Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f13873d = (WindowManager) context.getSystemService("window");
        LayoutInflater.from(context).inflate(C2425R.layout.wifi_weixin_interest_view, this);
        View findViewById = findViewById(C2425R.C2427id.wx_interest_layout);
        f13870a = findViewById.getLayoutParams().width;
        f13871b = findViewById.getLayoutParams().height;
        ((Button) findViewById(C2425R.C2427id.copy_bt)).setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.wxapi.WXInterestView.View$OnClickListenerC35001 */

            public void onClick(View view) {
                GBWindowManager.m10493a(context);
                context.stopService(new Intent(context, WXInterestService.class));
            }
        });
        ((ImageView) findViewById(C2425R.C2427id.close_iv)).setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.wxapi.WXInterestView.View$OnClickListenerC35012 */

            public void onClick(View view) {
                GBWindowManager.m10493a(context);
                context.stopService(new Intent(context, WXInterestService.class));
            }
        });
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                this.f13877h = motionEvent.getY();
                this.f13876g = motionEvent.getRawX() - ((float) m14863b());
                this.f13875f = motionEvent.getRawX() - ((float) m14863b());
                return true;
            case 1:
            default:
                return true;
            case 2:
                this.f13875f = motionEvent.getRawY() - ((float) m14863b());
                m14862a();
                return true;
        }
    }

    /* renamed from: a */
    public void mo28454a(WindowManager.LayoutParams layoutParams) {
        this.f13874e = layoutParams;
    }

    /* renamed from: a */
    private void m14862a() {
        this.f13874e.y = (int) (this.f13875f - this.f13877h);
        this.f13873d.updateViewLayout(this, this.f13874e);
    }

    /* renamed from: b */
    private int m14863b() {
        if (f13872c == 0) {
            try {
                Class<?> cls = Class.forName("com.android.internal.R$dimen");
                f13872c = getResources().getDimensionPixelSize(((Integer) cls.getField("status_bar_height").get(cls.newInstance())).intValue());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return f13872c;
    }
}
