package com.gbcom.gwifi.functions.aboutus;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBActivity;

public class ProtocolActivity extends GBActivity implements View.OnClickListener {

    /* renamed from: a */
    private RelativeLayout f9167a;

    /* renamed from: b */
    private TextView f9168b;

    /* renamed from: c */
    private TextView f9169c;

    @Override // android.support.p009v4.app.BaseFragmentActivityGingerbread, com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle, "用户协议界面", C2425R.layout.sys_activity_protocol, true);
        m10836a();
    }

    /* renamed from: a */
    private void m10836a() {
        this.f9167a = (RelativeLayout) findViewById(C2425R.C2427id.title_back_layout);
        this.f9167a.setOnClickListener(this);
        this.f9168b = (TextView) findViewById(C2425R.C2427id.title_main_tv);
        this.f9168b.setText("用户服务协议");
        this.f9169c = (TextView) findViewById(C2425R.C2427id.title_edit_tv);
        this.f9169c.setText((CharSequence) null);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C2425R.C2427id.title_back_layout /*{ENCODED_INT: 2131755757}*/:
                finish();
                return;
            default:
                return;
        }
    }
}
