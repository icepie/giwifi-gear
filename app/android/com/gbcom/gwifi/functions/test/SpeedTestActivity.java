package com.gbcom.gwifi.functions.test;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBActivity;

public class SpeedTestActivity extends GBActivity {

    /* renamed from: a */
    private RelativeLayout f12632a;

    /* renamed from: b */
    private TextView f12633b;

    /* renamed from: c */
    private TextView f12634c;

    /* renamed from: d */
    private ImageView f12635d;

    /* renamed from: e */
    private RelativeLayout f12636e;

    @Override // android.support.p009v4.app.BaseFragmentActivityGingerbread, com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle, "网络测速", C2425R.layout.sys_activity_speedtest, true);
        m13709b();
        m13707a();
    }

    /* renamed from: a */
    private void m13707a() {
        this.f12635d = (ImageView) findViewById(C2425R.C2427id.point);
        this.f12636e = (RelativeLayout) findViewById(C2425R.C2427id.rl_speed);
        this.f12636e.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            /* class com.gbcom.gwifi.functions.test.SpeedTestActivity.ViewTreeObserver$OnGlobalLayoutListenerC33451 */

            public void onGlobalLayout() {
                SpeedTestActivity.this.f12636e.getLeft();
                SpeedTestActivity.this.f12636e.getRight();
                SpeedTestActivity.this.f12636e.getTop();
                SpeedTestActivity.this.f12636e.getBottom();
                SpeedTestActivity.this.f12636e.getMeasuredHeight();
                SpeedTestActivity.this.f12636e.getMeasuredWidth();
            }
        });
        this.f12635d.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            /* class com.gbcom.gwifi.functions.test.SpeedTestActivity.ViewTreeObserver$OnGlobalLayoutListenerC33462 */

            public void onGlobalLayout() {
                SpeedTestActivity.this.f12635d.setPivotX((float) (SpeedTestActivity.this.f12635d.getMeasuredWidth() / 2));
                SpeedTestActivity.this.f12635d.setPivotY((float) SpeedTestActivity.this.f12635d.getMeasuredHeight());
                ObjectAnimator ofFloat = ObjectAnimator.ofFloat(SpeedTestActivity.this.f12635d, "rotation", 0.0f, -125.0f);
                ofFloat.setDuration(0L);
                ofFloat.setRepeatCount(0);
                ofFloat.start();
            }
        });
    }

    /* renamed from: b */
    private void m13709b() {
        this.f12632a = (RelativeLayout) findViewById(C2425R.C2427id.title_back_layout);
        this.f12632a.setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.test.SpeedTestActivity.View$OnClickListenerC33473 */

            public void onClick(View view) {
                SpeedTestActivity.this.finish();
            }
        });
        this.f12633b = (TextView) findViewById(C2425R.C2427id.title_main_tv);
        this.f12634c = (TextView) findViewById(C2425R.C2427id.title_edit_tv);
        this.f12633b.setText("网络测速");
        this.f12634c.setVisibility(8);
    }

    public void click(View view) {
        this.f12635d.setPivotX((float) (this.f12635d.getMeasuredWidth() / 2));
        this.f12635d.setPivotY((float) this.f12635d.getMeasuredHeight());
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.f12635d, "rotation", -125.0f, 122.0f);
        ofFloat.setDuration(3000L);
        ofFloat.setRepeatCount(0);
        ofFloat.start();
    }
}
