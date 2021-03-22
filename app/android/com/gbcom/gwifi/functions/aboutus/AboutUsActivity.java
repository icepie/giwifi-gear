package com.gbcom.gwifi.functions.aboutus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.util.C3467s;
import com.gbcom.gwifi.util.Constant;
import com.gbcom.gwifi.util.FormatUtil;
import com.gbcom.gwifi.util.HttpUtil;
import com.gbcom.gwifi.util.SystemUtil;
import com.gbcom.gwifi.util.VersionUtil;

public class AboutUsActivity extends GBActivity implements View.OnClickListener {

    /* renamed from: a */
    private RelativeLayout f9153a;

    /* renamed from: b */
    private TextView f9154b;

    /* renamed from: c */
    private TextView f9155c;

    /* renamed from: d */
    private RelativeLayout f9156d;

    /* renamed from: e */
    private RelativeLayout f9157e;

    /* renamed from: f */
    private RelativeLayout f9158f;

    /* renamed from: g */
    private RelativeLayout f9159g;

    /* renamed from: h */
    private TextView f9160h;

    /* renamed from: i */
    private RelativeLayout f9161i;

    /* renamed from: j */
    private TextView f9162j;

    /* renamed from: k */
    private TextView f9163k;

    @Override // android.support.p009v4.app.BaseFragmentActivityGingerbread, com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle, "关于我们界面", C2425R.layout.sys_activity_about_us, true);
        m10834a();
    }

    /* renamed from: a */
    private void m10834a() {
        this.f9153a = (RelativeLayout) findViewById(C2425R.C2427id.title_back_layout);
        this.f9153a.setOnClickListener(this);
        this.f9155c = (TextView) findViewById(C2425R.C2427id.title_main_tv);
        this.f9154b = (TextView) findViewById(C2425R.C2427id.title_edit_tv);
        this.f9155c.setText("关于我们");
        this.f9154b.setText((CharSequence) null);
        this.f9160h = (TextView) findViewById(C2425R.C2427id.about_version_tv);
        try {
            this.f9160h.setText("当前版本 " + (VersionUtil.m14565a() + ""));
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.f9156d = (RelativeLayout) findViewById(C2425R.C2427id.version_layout);
        this.f9157e = (RelativeLayout) findViewById(C2425R.C2427id.introduce_layout);
        this.f9158f = (RelativeLayout) findViewById(C2425R.C2427id.protocol_layout);
        this.f9159g = (RelativeLayout) findViewById(C2425R.C2427id.protect_layout);
        this.f9161i = (RelativeLayout) findViewById(C2425R.C2427id.phone_layout);
        if (SystemUtil.m14531e()) {
            this.f9156d.setOnClickListener(this);
        } else {
            this.f9156d.setVisibility(8);
        }
        this.f9157e.setOnClickListener(this);
        this.f9158f.setOnClickListener(this);
        this.f9159g.setOnClickListener(this);
        this.f9161i.setOnClickListener(this);
        this.f9162j = (TextView) findViewById(C2425R.C2427id.show_copyright);
        this.f9162j.setText("Copyright @ 2014-" + FormatUtil.m14232e());
        this.f9163k = (TextView) findViewById(C2425R.C2427id.show_company);
        if (SystemUtil.m14533f()) {
            this.f9158f.setVisibility(8);
            this.f9159g.setVisibility(8);
            this.f9161i.setVisibility(8);
            findViewById(C2425R.C2427id.protocol_separator).setVisibility(8);
            findViewById(C2425R.C2427id.protect_separator).setVisibility(8);
            findViewById(C2425R.C2427id.phone_separator).setVisibility(8);
            return;
        }
        this.f9163k.setText("上海寰创网络科技有限公司");
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C2425R.C2427id.title_back_layout /*{ENCODED_INT: 2131755757}*/:
                finish();
                return;
            case C2425R.C2427id.version_layout /*{ENCODED_INT: 2131755973}*/:
                checkVersion();
                return;
            case C2425R.C2427id.introduce_layout /*{ENCODED_INT: 2131755975}*/:
                startActivity(new Intent(this, IntroduceActivity.class));
                return;
            case C2425R.C2427id.protocol_layout /*{ENCODED_INT: 2131755978}*/:
                String g = HttpUtil.m14323g();
                if (!C3467s.m14513e(g)) {
                    GBActivity.openBackWebView(g, "");
                    return;
                } else {
                    GBActivity.showMessageToast("链接无效...");
                    return;
                }
            case C2425R.C2427id.protect_layout /*{ENCODED_INT: 2131755981}*/:
                String h = HttpUtil.m14326h();
                if (!C3467s.m14513e(h)) {
                    GBActivity.openBackWebView(h, "");
                    return;
                } else {
                    GBActivity.showMessageToast("链接无效...");
                    return;
                }
            case C2425R.C2427id.phone_layout /*{ENCODED_INT: 2131755984}*/:
                try {
                    callPhone(Constant.f13225b);
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    GBApplication.instance().showMessageToast("你好像没有安装 拨号 应用！");
                    return;
                }
            default:
                return;
        }
    }
}
