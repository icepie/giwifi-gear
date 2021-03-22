package com.gbcom.gwifi.functions.system;

import android.app.StatsManager;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.net.wifi.WifiEnterpriseConfig;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.functions.wifi.WiFiUtil;
import com.gbcom.gwifi.util.Config;
import com.gbcom.gwifi.util.Constant;
import com.gbcom.gwifi.util.SystemUtil;
import com.gbcom.gwifi.util.VersionUtil;
import com.gbcom.gwifi.util.cache.CacheAccount;
import com.gbcom.gwifi.util.cache.CacheApp;
import com.gbcom.gwifi.util.cache.CacheAuth;

public class SysStateActivity extends GBActivity implements View.OnClickListener {

    /* renamed from: a */
    private RelativeLayout f11642a;

    /* renamed from: b */
    private TextView f11643b;

    /* renamed from: c */
    private TextView f11644c;

    /* renamed from: d */
    private TextView f11645d;

    /* renamed from: e */
    private TextView f11646e;

    /* renamed from: f */
    private TextView f11647f;

    /* renamed from: g */
    private TextView f11648g;

    /* renamed from: h */
    private TextView f11649h;

    /* renamed from: i */
    private TextView f11650i;

    /* renamed from: j */
    private RelativeLayout f11651j;

    /* renamed from: k */
    private RelativeLayout f11652k;

    /* renamed from: l */
    private LinearLayout f11653l;

    /* renamed from: m */
    private TextView f11654m;

    /* renamed from: n */
    private TextView f11655n;

    /* renamed from: o */
    private CheckBox f11656o;

    /* renamed from: p */
    private long f11657p = 0;

    /* renamed from: q */
    private long f11658q = 0;

    /* renamed from: r */
    private int f11659r = 0;

    /* renamed from: s */
    private RelativeLayout f11660s;

    /* renamed from: t */
    private ClipboardManager f11661t;

    /* renamed from: u */
    private LinearLayout f11662u;

    /* renamed from: v */
    private LinearLayout f11663v;

    /* renamed from: w */
    private RelativeLayout f11664w;

    /* renamed from: x */
    private CheckBox f11665x;

    /* renamed from: y */
    private TextView f11666y;

    /* renamed from: z */
    private TextView f11667z;

    /* renamed from: a */
    static /* synthetic */ int m12786a(SysStateActivity sysStateActivity) {
        int i = sysStateActivity.f11659r;
        sysStateActivity.f11659r = i + 1;
        return i;
    }

    @Override // android.support.p009v4.app.BaseFragmentActivityGingerbread, com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle, "手机状态界面", C2425R.layout.sys_activity_state, true);
        m12789a();
    }

    /* renamed from: a */
    private void m12789a() {
        this.f11642a = (RelativeLayout) findViewById(C2425R.C2427id.title_back_layout);
        this.f11642a.setOnClickListener(this);
        this.f11644c = (TextView) findViewById(C2425R.C2427id.title_main_tv);
        this.f11643b = (TextView) findViewById(C2425R.C2427id.title_edit_tv);
        this.f11644c.setText("手机状态");
        this.f11643b.setVisibility(4);
        this.f11655n = (TextView) findViewById(C2425R.C2427id.device_token);
        this.f11655n.setText(CacheApp.getInstance().getUmengPushRegisterId());
        this.f11651j = (RelativeLayout) findViewById(C2425R.C2427id.mac_address);
        this.f11651j.setOnTouchListener(new View.OnTouchListener() {
            /* class com.gbcom.gwifi.functions.system.SysStateActivity.View$OnTouchListenerC31231 */

            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case 0:
                        SysStateActivity.m12786a(SysStateActivity.this);
                        if (SysStateActivity.this.f11659r != 1) {
                            SysStateActivity.this.f11658q = SysStateActivity.this.f11657p;
                            SysStateActivity.this.f11657p = System.currentTimeMillis();
                            if (SysStateActivity.this.f11657p - SysStateActivity.this.f11658q < StatsManager.DEFAULT_TIMEOUT_MILLIS) {
                                if (SysStateActivity.this.f11659r == 5 && SysStateActivity.this.f11657p - SysStateActivity.this.f11658q < StatsManager.DEFAULT_TIMEOUT_MILLIS) {
                                    if (Config.m14094a().mo27810m() != 1) {
                                        GBActivity.showMessageToast("再点5次就开启调试模式");
                                        break;
                                    } else {
                                        GBActivity.showMessageToast("当前已是调试模式");
                                        break;
                                    }
                                } else if (SysStateActivity.this.f11659r == 10 && SysStateActivity.this.f11657p - SysStateActivity.this.f11658q < StatsManager.DEFAULT_TIMEOUT_MILLIS) {
                                    if (Config.m14094a().mo27810m() != 1) {
                                        SysStateActivity.this.f11664w.setVisibility(0);
                                        SysStateActivity.this.f11662u.setVisibility(0);
                                        SysStateActivity.this.f11663v.setVisibility(0);
                                        SysStateActivity.this.f11665x.setChecked(false);
                                        Config.m14094a().mo27798b(1);
                                        Config.m14094a().mo27795a(2);
                                        GBActivity.showMessageToast("已开启调试模式");
                                        break;
                                    } else {
                                        GBActivity.showMessageToast("当前已是调试模式");
                                        break;
                                    }
                                }
                            } else {
                                SysStateActivity.this.f11659r = 0;
                                SysStateActivity.this.f11657p = 0;
                                SysStateActivity.this.f11658q = 0;
                                return false;
                            }
                        } else {
                            SysStateActivity.this.f11657p = System.currentTimeMillis();
                            break;
                        }
                        break;
                }
                return true;
            }
        });
        this.f11645d = (TextView) findViewById(C2425R.C2427id.ip_address_tv);
        this.f11646e = (TextView) findViewById(C2425R.C2427id.mac_tv);
        this.f11647f = (TextView) findViewById(C2425R.C2427id.device_id_tv);
        this.f11648g = (TextView) findViewById(C2425R.C2427id.gateway_ip_tv);
        this.f11649h = (TextView) findViewById(C2425R.C2427id.sn_tv);
        this.f11650i = (TextView) findViewById(C2425R.C2427id.bssid_tv);
        String localMac = CacheAuth.getInstance().getLocalMac();
        this.f11645d.setText(WiFiUtil.m14022a(this).mo27616j());
        if (localMac != null) {
            TextView textView = this.f11646e;
            if (localMac.equals(WifiEnterpriseConfig.EMPTY_VALUE)) {
                localMac = Constant.f13330p;
            }
            textView.setText(localMac);
        }
        this.f11648g.setText(WiFiUtil.m14022a(this).mo27617k());
        this.f11647f.setText(SystemUtil.m14527c(this));
        this.f11649h.setText(CacheAuth.getInstance().getGwSn());
        this.f11650i.setText(WiFiUtil.m14022a(GBApplication.instance()).mo27615i());
        this.f11652k = (RelativeLayout) findViewById(C2425R.C2427id.run_model);
        this.f11656o = (CheckBox) findViewById(C2425R.C2427id.model_check);
        this.f11653l = (LinearLayout) findViewById(C2425R.C2427id.run_model_ll);
        this.f11654m = (TextView) findViewById(C2425R.C2427id.version_code);
        this.f11654m.setText(VersionUtil.m14564a(this) + "");
        if (Config.m14094a().mo27809l().intValue() == 2) {
            this.f11652k.setVisibility(0);
            this.f11653l.setVisibility(0);
        }
        this.f11656o.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            /* class com.gbcom.gwifi.functions.system.SysStateActivity.C31242 */

            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    SysStateActivity.this.f11652k.setVisibility(8);
                    GBActivity.isRunModelChanged = true;
                    CacheAccount.getInstance().setUserId(0);
                    Config.m14094a().mo27796a((Integer) 1);
                    CacheApp.getInstance().setUmengPushMac("");
                    CacheApp.getInstance().setUmengPushRegisterId("");
                    GBActivity.showMessageToast("已开启正式模式,重新启动生效");
                    Config.m14094a();
                    Config.m14098b();
                }
            }
        });
        this.f11666y = (TextView) findViewById(C2425R.C2427id.umeng_register_id);
        this.f11666y.setText(CacheApp.getInstance().getUmengPushRegisterId());
        this.f11667z = (TextView) findViewById(C2425R.C2427id.app_uuid);
        this.f11667z.setText(CacheApp.getInstance().getAppUuid());
        this.f11664w = (RelativeLayout) findViewById(C2425R.C2427id.develop_mode);
        this.f11662u = (LinearLayout) findViewById(C2425R.C2427id.develop_mode_token_ll);
        this.f11663v = (LinearLayout) findViewById(C2425R.C2427id.develop_mode_uuid_ll);
        if (Config.m14094a().mo27810m() == 1) {
            this.f11664w.setVisibility(0);
            this.f11662u.setVisibility(0);
            this.f11663v.setVisibility(0);
        } else {
            this.f11664w.setVisibility(8);
            this.f11662u.setVisibility(8);
            this.f11663v.setVisibility(8);
        }
        this.f11665x = (CheckBox) findViewById(C2425R.C2427id.develop_mode_check);
        this.f11665x.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            /* class com.gbcom.gwifi.functions.system.SysStateActivity.C31253 */

            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (compoundButton.isPressed() && z) {
                    SysStateActivity.this.f11664w.setVisibility(8);
                    SysStateActivity.this.f11662u.setVisibility(8);
                    SysStateActivity.this.f11663v.setVisibility(8);
                    Config.m14094a().mo27798b(0);
                    Config.m14094a().mo27795a(5);
                    GBActivity.showMessageToast("已关闭调试模式");
                }
            }
        });
        this.f11661t = (ClipboardManager) getSystemService("clipboard");
        this.f11660s = (RelativeLayout) findViewById(C2425R.C2427id.rl_sn);
        this.f11660s.setOnLongClickListener(new View.OnLongClickListener() {
            /* class com.gbcom.gwifi.functions.system.SysStateActivity.View$OnLongClickListenerC31264 */

            public boolean onLongClick(View view) {
                SysStateActivity.this.f11661t.setPrimaryClip(ClipData.newPlainText("text", CacheApp.getInstance().getUmengPushRegisterId()));
                return false;
            }
        });
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
