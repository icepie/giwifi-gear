package com.gbcom.gwifi.functions.aboutus;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBActivity;

public class IntroduceActivity extends GBActivity implements View.OnClickListener {

    /* renamed from: a */
    private RelativeLayout f9164a;

    /* renamed from: b */
    private TextView f9165b;

    /* renamed from: c */
    private TextView f9166c;

    @Override // android.support.p009v4.app.BaseFragmentActivityGingerbread, com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle, "产品介绍界面", C2425R.layout.sys_activity_introduce, true);
        m10835a();
    }

    /* renamed from: a */
    private void m10835a() {
        this.f9164a = (RelativeLayout) findViewById(C2425R.C2427id.title_back_layout);
        this.f9164a.setOnClickListener(this);
        this.f9166c = (TextView) findViewById(C2425R.C2427id.title_main_tv);
        this.f9165b = (TextView) findViewById(C2425R.C2427id.title_edit_tv);
        this.f9166c.setText("产品介绍");
        this.f9165b.setText((CharSequence) null);
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
