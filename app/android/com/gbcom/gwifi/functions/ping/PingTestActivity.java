package com.gbcom.gwifi.functions.ping;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.functions.wifi.WiFiUtil;
import com.gbcom.gwifi.util.C3467s;
import com.gbcom.gwifi.util.cache.CacheAuth;
import org.apache.xalan.templates.Constants;

public class PingTestActivity extends GBActivity implements View.OnClickListener {

    /* renamed from: l */
    private static final int f9946l = 0;

    /* renamed from: m */
    private static final int f9947m = 1;

    /* renamed from: n */
    private static final int f9948n = 2;

    /* renamed from: a */
    SpannableStringBuilder f9949a = new SpannableStringBuilder();

    /* renamed from: b */
    PingObserver f9950b = new PingObserver() {
        /* class com.gbcom.gwifi.functions.ping.PingTestActivity.C27932 */

        @Override // com.gbcom.gwifi.functions.ping.PingObserver
        /* renamed from: a */
        public void mo25264a(String str, int i) {
            ForegroundColorSpan foregroundColorSpan;
            super.mo25264a(str, i);
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str + "\r\n");
            ForegroundColorSpan foregroundColorSpan2 = null;
            if (str.contains("time=")) {
                String substring = str.substring(str.indexOf("time="), str.length());
                String substring2 = substring.substring(5, substring.indexOf("ms"));
                if (substring2.contains(Constants.ATTRVAL_THIS)) {
                    substring2 = substring2.substring(0, substring2.indexOf(Constants.ATTRVAL_THIS));
                }
                if (Integer.parseInt(substring2.trim()) < 100) {
                    foregroundColorSpan = new ForegroundColorSpan(-16711936);
                } else {
                    foregroundColorSpan = new ForegroundColorSpan(-65536);
                }
                spannableStringBuilder.setSpan(foregroundColorSpan, 0, spannableStringBuilder.length(), 33);
            } else if (str.contains("packets transmitted")) {
                String substring3 = str.substring(0, str.indexOf("%"));
                String substring4 = substring3.substring(substring3.lastIndexOf(",") + 2, substring3.length());
                if (substring4.contains(Constants.ATTRVAL_THIS)) {
                    foregroundColorSpan2 = new ForegroundColorSpan(-65536);
                } else if (!C3467s.m14513e(substring4.trim())) {
                    if (Integer.parseInt(substring4.trim()) != 0) {
                        foregroundColorSpan2 = new ForegroundColorSpan(-65536);
                    } else {
                        foregroundColorSpan2 = new ForegroundColorSpan(-16711936);
                    }
                }
                spannableStringBuilder.setSpan(foregroundColorSpan2, 0, spannableStringBuilder.length(), 33);
            }
            PingTestActivity.this.f9949a.append((CharSequence) spannableStringBuilder);
            Message obtain = Message.obtain();
            obtain.obj = PingTestActivity.this.f9949a;
            obtain.what = 0;
            PingTestActivity.this.f9960o.sendMessage(obtain);
        }

        @Override // com.gbcom.gwifi.functions.ping.PingObserver
        /* renamed from: a */
        public void mo25263a() {
            super.mo25263a();
            PingTestActivity.this.f9960o.sendEmptyMessage(1);
        }

        @Override // com.gbcom.gwifi.functions.ping.PingObserver
        /* renamed from: a */
        public void mo25265a(String str, int i, int i2) {
            ForegroundColorSpan foregroundColorSpan;
            super.mo25265a(str, i, i2);
            String str2 = "--- " + str + " ping statistics ---\r\n";
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(i2 + " packets transmitted,");
            stringBuffer.append(i + " received,");
            if (i == i2) {
                stringBuffer.append("0% packet loss");
            } else {
                float f = 0.0f;
                if (i2 != 0) {
                    f = ((float) (i2 - i)) / ((float) i2);
                }
                stringBuffer.append(((double) Math.round(f * 100.0f)) + "% packet loss");
            }
            stringBuffer.append("\r\n");
            PingTestActivity.this.f9949a.append((CharSequence) str2);
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(stringBuffer.toString());
            if (i == i2) {
                foregroundColorSpan = new ForegroundColorSpan(-16711936);
            } else {
                foregroundColorSpan = new ForegroundColorSpan(-65536);
            }
            spannableStringBuilder.setSpan(foregroundColorSpan, 0, spannableStringBuilder.length(), 33);
            PingTestActivity.this.f9949a.append((CharSequence) spannableStringBuilder);
            Message obtain = Message.obtain();
            obtain.obj = PingTestActivity.this.f9949a;
            obtain.what = 0;
            PingTestActivity.this.f9960o.sendMessage(obtain);
        }
    };

    /* renamed from: c */
    private RelativeLayout f9951c;

    /* renamed from: d */
    private TextView f9952d;

    /* renamed from: e */
    private TextView f9953e;

    /* renamed from: f */
    private Button f9954f;

    /* renamed from: g */
    private Button f9955g;

    /* renamed from: h */
    private Button f9956h;

    /* renamed from: i */
    private TextView f9957i;

    /* renamed from: j */
    private ScrollView f9958j;

    /* renamed from: k */
    private int f9959k = 1;

    /* renamed from: o */
    private Handler f9960o = new Handler() {
        /* class com.gbcom.gwifi.functions.ping.PingTestActivity.HandlerC27921 */

        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 0:
                    SpannableStringBuilder spannableStringBuilder = (SpannableStringBuilder) message.obj;
                    PingTestActivity.this.f9957i.setText(spannableStringBuilder);
                    sendEmptyMessageDelayed(2, 500);
                    PingTestActivity.this.dismissProgressDialog();
                    if (spannableStringBuilder.toString().contains("packets transmitted")) {
                        PingTestActivity.this.f9956h.setText("开始");
                        return;
                    }
                    return;
                case 1:
                    PingTestActivity.this.f9949a.clear();
                    return;
                case 2:
                    PingTestActivity.this.f9958j.fullScroll(130);
                    return;
                default:
                    return;
            }
        }
    };

    @Override // android.support.p009v4.app.BaseFragmentActivityGingerbread, com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle, "Ping检测界面", C2425R.layout.sys_activity_ping_test, true);
        m11502a();
    }

    /* renamed from: a */
    private void m11502a() {
        this.f9951c = (RelativeLayout) findViewById(C2425R.C2427id.title_back_layout);
        this.f9951c.setOnClickListener(this);
        this.f9952d = (TextView) findViewById(C2425R.C2427id.title_main_tv);
        this.f9952d.setText("Ping检测");
        this.f9953e = (TextView) findViewById(C2425R.C2427id.title_edit_tv);
        this.f9953e.setText((CharSequence) null);
        this.f9954f = (Button) findViewById(C2425R.C2427id.ping_gateway);
        this.f9955g = (Button) findViewById(C2425R.C2427id.ping_website);
        this.f9956h = (Button) findViewById(C2425R.C2427id.ping);
        this.f9957i = (TextView) findViewById(C2425R.C2427id.ping_result);
        this.f9954f.setOnClickListener(this);
        this.f9955g.setOnClickListener(this);
        this.f9956h.setOnClickListener(this);
        PingSrvUtil.m11515a(this.f9950b);
        this.f9958j = (ScrollView) findViewById(2131755600);
    }

    public void onClick(View view) {
        String stationCloud;
        switch (view.getId()) {
            case C2425R.C2427id.title_back_layout /*{ENCODED_INT: 2131755757}*/:
                finish();
                return;
            case C2425R.C2427id.ping_gateway /*{ENCODED_INT: 2131756024}*/:
                this.f9955g.setBackgroundResource(C2425R.C2426drawable.gi_btn_disable);
                this.f9954f.setBackgroundResource(C2425R.C2426drawable.gi_btn_enable);
                this.f9959k = 1;
                return;
            case C2425R.C2427id.ping_website /*{ENCODED_INT: 2131756025}*/:
                this.f9955g.setBackgroundResource(C2425R.C2426drawable.gi_btn_enable);
                this.f9954f.setBackgroundResource(C2425R.C2426drawable.gi_btn_disable);
                this.f9959k = 2;
                return;
            case C2425R.C2427id.ping /*{ENCODED_INT: 2131756026}*/:
                if (TextUtils.equals("开始", this.f9956h.getText().toString().trim())) {
                    if (this.f9959k == 1) {
                        m11504a(WiFiUtil.m14022a(GBApplication.instance()).mo27617k());
                    } else if (this.f9959k == 2) {
                        if (CacheAuth.getInstance().isPortal()) {
                            stationCloud = CacheAuth.getInstance().getPortalHost();
                        } else {
                            stationCloud = CacheAuth.getInstance().getStationCloud();
                        }
                        m11504a(stationCloud);
                    }
                    this.f9956h.setText("停止");
                    this.f9955g.setEnabled(false);
                    this.f9954f.setEnabled(false);
                    return;
                }
                m11503a((Button) view);
                this.f9956h.setText("开始");
                this.f9955g.setEnabled(true);
                this.f9954f.setEnabled(true);
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    private void m11504a(String str) {
        this.f9956h.setText("停止");
        this.f9958j.fullScroll(33);
        PingSrvUtil.m11516a(str);
        this.f9960o.sendEmptyMessage(1);
    }

    /* renamed from: a */
    private void m11503a(Button button) {
        if (button.getText().equals("停止")) {
            PingSrvUtil.m11520b();
            button.setText("开始");
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onDestroy() {
        PingSrvUtil.m11520b();
        super.onDestroy();
    }
}
