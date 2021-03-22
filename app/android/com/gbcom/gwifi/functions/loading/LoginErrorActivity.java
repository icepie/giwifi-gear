package com.gbcom.gwifi.functions.loading;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBActivity;

public class LoginErrorActivity extends GBActivity {

    /* renamed from: a */
    private TextView f9543a;

    /* renamed from: b */
    private RelativeLayout f9544b;

    /* renamed from: c */
    private TextView f9545c;

    /* renamed from: d */
    private TextView f9546d;

    @Override // android.support.p009v4.app.BaseFragmentActivityGingerbread, com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle, "异常界面", C2425R.layout.login_error, true);
        m11193a();
        m11194b();
    }

    /* renamed from: a */
    private void m11193a() {
        this.f9544b = (RelativeLayout) findViewById(C2425R.C2427id.title_back_layout);
        this.f9544b.setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.loading.LoginErrorActivity.View$OnClickListenerC26721 */

            public void onClick(View view) {
                LoginErrorActivity.this.finish();
            }
        });
        this.f9545c = (TextView) findViewById(C2425R.C2427id.title_main_tv);
        this.f9546d = (TextView) findViewById(C2425R.C2427id.title_edit_tv);
        this.f9545c.setText("");
        this.f9546d.setVisibility(8);
    }

    /* access modifiers changed from: protected */
    @Override // android.support.p009v4.app.FragmentActivity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        m11194b();
    }

    /* renamed from: b */
    private void m11194b() {
        String stringExtra = getIntent().getStringExtra("errinfo");
        this.f9543a = (TextView) findViewById(C2425R.C2427id.login_error_info);
        this.f9543a.setText(stringExtra);
    }
}
