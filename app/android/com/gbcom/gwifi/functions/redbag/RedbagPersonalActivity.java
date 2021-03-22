package com.gbcom.gwifi.functions.redbag;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBActivity;

public class RedbagPersonalActivity extends GBActivity implements View.OnClickListener {

    /* renamed from: a */
    private RelativeLayout f11253a;

    /* renamed from: b */
    private TextView f11254b;

    /* renamed from: c */
    private TextView f11255c;

    @Override // android.support.p009v4.app.BaseFragmentActivityGingerbread, com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle, "抢红包", C2425R.layout.redbag_activity_personal, true);
        m12521a();
    }

    /* renamed from: a */
    private void m12521a() {
        this.f11253a = (RelativeLayout) findViewById(C2425R.C2427id.title_back_layout);
        this.f11253a.setOnClickListener(this);
        this.f11254b = (TextView) findViewById(C2425R.C2427id.title_main_tv);
        this.f11255c = (TextView) findViewById(C2425R.C2427id.title_edit_tv);
        this.f11254b.setText("抢红包");
        this.f11255c.setVisibility(8);
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
