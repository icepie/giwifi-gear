package com.gbcom.gwifi.functions.loading;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.util.C3467s;
import com.gbcom.gwifi.util.HttpUtil;

public class LoginResultActivity extends GBActivity {

    /* renamed from: a */
    private Button f9548a;

    /* renamed from: b */
    private Button f9549b;

    /* renamed from: c */
    private TextView f9550c;

    /* renamed from: d */
    private LinearLayout f9551d;

    /* renamed from: e */
    private LinearLayout f9552e;

    /* renamed from: f */
    private ImageView f9553f;

    @Override // android.support.p009v4.app.BaseFragmentActivityGingerbread, com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        setActivityName("试用用户界面");
        super.onCreate(bundle);
        setContentView(C2425R.layout.login_result);
        mo24935a();
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.base.activity.GBActivity
    public int customTitleType() {
        return 2;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo24935a() {
        this.f9551d = (LinearLayout) findViewById(C2425R.C2427id.login_success);
        this.f9552e = (LinearLayout) findViewById(C2425R.C2427id.login_fail);
        this.f9550c = (TextView) findViewById(C2425R.C2427id.err_info_tv);
        String stringExtra = getIntent().getStringExtra("showText");
        this.f9551d.setVisibility(0);
        this.f9552e.setVisibility(8);
        TextView textView = (TextView) findViewById(C2425R.C2427id.num_one);
        if (!TextUtils.isEmpty(stringExtra)) {
            textView.setText(stringExtra);
        } else {
            finish();
        }
        this.f9548a = (Button) findViewById(C2425R.C2427id.online_btn);
        this.f9549b = (Button) findViewById(C2425R.C2427id.re_login_btn);
        this.f9548a.setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.loading.LoginResultActivity.View$OnClickListenerC26731 */

            public void onClick(View view) {
                LoginResultActivity.this.finish();
            }
        });
        this.f9549b.setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.loading.LoginResultActivity.View$OnClickListenerC26742 */

            public void onClick(View view) {
            }
        });
        this.f9553f = (ImageView) findViewById(C2425R.C2427id.login_result_cancel);
        this.f9553f.setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.loading.LoginResultActivity.View$OnClickListenerC26753 */

            public void onClick(View view) {
                LoginResultActivity.this.finish();
            }
        });
    }

    /* renamed from: b */
    private void m11195b() {
        String i = HttpUtil.m14329i();
        if (!C3467s.m14513e(i)) {
            GBActivity.openBackWebView(i, "上网及套餐介绍");
        } else {
            GBActivity.showMessageToast("链接无效...");
        }
        finish();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        m11195b();
        return true;
    }

    public void finish() {
        setResult(-1);
        GBApplication.instance().removeActivity(this);
        super.finish();
    }
}
