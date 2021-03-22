package com.gbcom.gwifi.functions.test;

import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.base.p234c.CallBack;
import com.gbcom.gwifi.functions.ping.PingUtil;
import com.gbcom.gwifi.functions.wifi.DeviceCheckNetWorkUtils;
import com.gbcom.gwifi.functions.wifi.WiFiUtil;
import com.gbcom.gwifi.functions.wifi.entity.AuthInfo;
import com.gbcom.gwifi.functions.wifi.entity.CheckResult;
import com.gbcom.gwifi.p221a.RequestHandler;
import com.gbcom.gwifi.p221a.p223b.HttpRequest;
import com.gbcom.gwifi.p221a.p226d.OkRequestHandler;
import com.gbcom.gwifi.third.kefu.easemob.GiwifiHXService;
import com.gbcom.gwifi.util.C3467s;
import com.gbcom.gwifi.util.CommonMsg;
import com.gbcom.gwifi.util.Constant;
import com.gbcom.gwifi.util.HttpUtil;
import com.gbcom.gwifi.util.JsonUtil;
import com.gbcom.gwifi.util.Log;
import com.gbcom.gwifi.util.NetworkUtils;
import com.gbcom.gwifi.util.cache.CacheAccount;
import com.gbcom.gwifi.util.cache.CacheAuth;
import com.gbcom.gwifi.widget.CircleProgress;
import com.umeng.message.proguard.MessageStore;
import java.util.HashMap;
import java.util.LinkedHashMap;
import p041c.Request;

public class DeviceTest2Activity extends GBActivity implements View.OnClickListener {
    public static Handler noUIHandler;

    /* renamed from: A */
    private ImageView f12568A;

    /* renamed from: B */
    private ImageView f12569B;

    /* renamed from: C */
    private LinkedHashMap<String, Integer> f12570C;

    /* renamed from: D */
    private LinearLayout f12571D;

    /* renamed from: E */
    private LinearLayout f12572E;

    /* renamed from: F */
    private LinearLayout f12573F;

    /* renamed from: G */
    private int f12574G;

    /* renamed from: H */
    private CheckResult f12575H;

    /* renamed from: I */
    private boolean f12576I = false;

    /* renamed from: J */
    private CircleProgress f12577J;

    /* renamed from: K */
    private boolean f12578K = false;

    /* renamed from: L */
    private TextView f12579L;

    /* renamed from: M */
    private TextView f12580M;

    /* renamed from: N */
    private TextView f12581N;

    /* renamed from: O */
    private TextView f12582O;

    /* renamed from: P */
    private TextView f12583P;

    /* renamed from: Q */
    private TextView f12584Q;

    /* renamed from: R */
    private TextView f12585R;

    /* renamed from: S */
    private TextView f12586S;

    /* renamed from: T */
    private TextView f12587T;

    /* renamed from: U */
    private TextView f12588U;

    /* renamed from: V */
    private TextView f12589V;

    /* renamed from: W */
    private LinearLayout f12590W;

    /* renamed from: X */
    private int f12591X = 0;

    /* renamed from: Y */
    private TextView f12592Y;

    /* renamed from: Z */
    private TextView f12593Z;

    /* renamed from: a */
    private final String f12594a = "DeviceTest2Activity";

    /* renamed from: aa */
    private final int f12595aa = 1;

    /* renamed from: ab */
    private final int f12596ab = 2;

    /* renamed from: ac */
    private final int f12597ac = 3;

    /* renamed from: ad */
    private Handler f12598ad = new Handler(Looper.getMainLooper()) {
        /* class com.gbcom.gwifi.functions.test.DeviceTest2Activity.HandlerC33381 */

        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 1:
                    DeviceTest2Activity.this.f12577J.mo28200a(Integer.valueOf(((Integer) message.obj).intValue()));
                    return;
                case 2:
                    DeviceTest2Activity.this.m13702g();
                    return;
                case 3:
                    DeviceTest2Activity.this.finish();
                    return;
                default:
                    return;
            }
        }
    };

    /* renamed from: ae */
    private OkRequestHandler<String> f12599ae = new OkRequestHandler<String>() {
        /* class com.gbcom.gwifi.functions.test.DeviceTest2Activity.C33446 */

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestStart(Request abVar) {
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestFailed(Request abVar, Exception exc) {
            DeviceTest2Activity.this.f12622x.setEnabled(true);
            GBActivity.showMessageToast("上报失败");
        }

        /* renamed from: a */
        public void onRequestFinish(Request abVar, String str) {
            DeviceTest2Activity.this.f12622x.setEnabled(true);
            CommonMsg deSerializeJson = CommonMsg.deSerializeJson(str.getBytes());
            if (deSerializeJson == null || deSerializeJson.getResultCode().intValue() != 0) {
                GBActivity.showMessageToast("上报失败");
            } else {
                GBActivity.showMessageToast("上报成功");
            }
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestProgress(Request abVar, long j, long j2) {
        }
    };

    /* renamed from: b */
    private TextView f12600b;

    /* renamed from: c */
    private TextView f12601c;

    /* renamed from: d */
    private TextView f12602d;

    /* renamed from: e */
    private Button f12603e;

    /* renamed from: f */
    private RelativeLayout f12604f;

    /* renamed from: g */
    private RelativeLayout f12605g;

    /* renamed from: h */
    private RelativeLayout f12606h;

    /* renamed from: i */
    private RelativeLayout f12607i;

    /* renamed from: j */
    private TextView f12608j;

    /* renamed from: k */
    private TextView f12609k;

    /* renamed from: l */
    private HttpRequest f12610l;

    /* renamed from: m */
    private final int f12611m = 1;

    /* renamed from: n */
    private final int f12612n = 2;

    /* renamed from: o */
    private final int f12613o = 3;

    /* renamed from: p */
    private String f12614p;

    /* renamed from: q */
    private boolean f12615q = false;

    /* renamed from: r */
    private boolean f12616r = false;

    /* renamed from: s */
    private boolean f12617s = false;

    /* renamed from: t */
    private LinearLayout f12618t;

    /* renamed from: u */
    private ScrollView f12619u;

    /* renamed from: v */
    private LinearLayout f12620v;

    /* renamed from: w */
    private LinearLayout f12621w;

    /* renamed from: x */
    private LinearLayout f12622x;

    /* renamed from: y */
    private TextView f12623y;

    /* renamed from: z */
    private ImageView f12624z;

    @Override // android.support.p009v4.app.BaseFragmentActivityGingerbread, com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle, "终端检测界面", C2425R.layout.sys_activity_device_test2, true);
        m13683a();
    }

    /* renamed from: a */
    private void m13683a() {
        HandlerThread handlerThread = new HandlerThread("noUiHandlerThread");
        handlerThread.start();
        noUIHandler = new Handler(handlerThread.getLooper()) {
            /* class com.gbcom.gwifi.functions.test.DeviceTest2Activity.HandlerC33392 */

            public void handleMessage(Message message) {
                super.handleMessage(message);
                Object[] objArr = (Object[]) message.obj;
                ((DeviceTestHttpService) GBApplication.instance().getProtocolServiceDeviceTest("http")).mo27368a((String) objArr[0], (HashMap) objArr[1], (RequestHandler) objArr[2], (HttpRequest) objArr[3]);
            }
        };
        String stringExtra = getIntent().getStringExtra("type");
        this.f12607i = (RelativeLayout) findViewById(C2425R.C2427id.title_back_layout);
        this.f12607i.setOnClickListener(this);
        this.f12608j = (TextView) findViewById(C2425R.C2427id.title_main_tv);
        this.f12608j.setText("故障检测");
        this.f12609k = (TextView) findViewById(C2425R.C2427id.title_edit_tv);
        this.f12609k.setText((CharSequence) null);
        this.f12618t = (LinearLayout) findViewById(C2425R.C2427id.start_check);
        this.f12619u = (ScrollView) findViewById(C2425R.C2427id.check_result);
        this.f12620v = (LinearLayout) findViewById(C2425R.C2427id.check_btn);
        this.f12621w = (LinearLayout) findViewById(C2425R.C2427id.re_check_btn);
        this.f12622x = (LinearLayout) findViewById(C2425R.C2427id.upload_log_btn);
        this.f12623y = (TextView) findViewById(C2425R.C2427id.upload_btn);
        if (stringExtra.equals("hy")) {
            this.f12623y.setText("上报客服");
        }
        this.f12624z = (ImageView) findViewById(C2425R.C2427id.ping_gateway_state);
        this.f12568A = (ImageView) findViewById(C2425R.C2427id.ping_cloud_state);
        this.f12571D = (LinearLayout) findViewById(C2425R.C2427id.base_info);
        this.f12572E = (LinearLayout) findViewById(C2425R.C2427id.auth_info);
        this.f12573F = (LinearLayout) findViewById(C2425R.C2427id.ap_info);
        this.f12579L = (TextView) findViewById(C2425R.C2427id.f8361sn);
        this.f12580M = (TextView) findViewById(C2425R.C2427id.gw_ip);
        this.f12581N = (TextView) findViewById(C2425R.C2427id.phone);
        this.f12582O = (TextView) findViewById(C2425R.C2427id.client_mac);
        this.f12583P = (TextView) findViewById(C2425R.C2427id.ap_rssi);
        this.f12584Q = (TextView) findViewById(C2425R.C2427id.bssid);
        this.f12585R = (TextView) findViewById(C2425R.C2427id.road);
        this.f12586S = (TextView) findViewById(C2425R.C2427id.auth_method);
        this.f12587T = (TextView) findViewById(C2425R.C2427id.auth_state);
        this.f12588U = (TextView) findViewById(C2425R.C2427id.online_state);
        this.f12589V = (TextView) findViewById(C2425R.C2427id.auth_result);
        this.f12590W = (LinearLayout) findViewById(C2425R.C2427id.offline_reason_list);
        this.f12621w.setOnClickListener(this);
        this.f12622x.setOnClickListener(this);
        this.f12577J = (CircleProgress) findViewById(C2425R.C2427id.start_check_btn);
        this.f12577J.setOnClickListener(this);
        this.f12569B = (ImageView) findViewById(C2425R.C2427id.dns_state);
        this.f12592Y = (TextView) findViewById(C2425R.C2427id.phone_ip);
        this.f12593Z = (TextView) findViewById(C2425R.C2427id.phone_dns);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C2425R.C2427id.title_back_layout /*{ENCODED_INT: 2131755757}*/:
                finish();
                return;
            case C2425R.C2427id.re_check_btn /*{ENCODED_INT: 2131756015}*/:
                if (!this.f12578K) {
                    this.f12578K = true;
                    this.f12576I = false;
                    String i = WiFiUtil.m14022a(GBApplication.instance()).mo27615i();
                    if (NetworkUtils.m14430b(i)) {
                        this.f12614p = i.toLowerCase().trim();
                        m13699f();
                        m13697e();
                        m13694d();
                        return;
                    }
                    showMessageToast("请先连接" + getResources().getString(C2425R.C2429string.wifi_name) + "网络");
                    this.f12578K = false;
                    return;
                }
                return;
            case C2425R.C2427id.upload_log_btn /*{ENCODED_INT: 2131756017}*/:
                if (this.f12623y.getText().toString().equals("上报结果")) {
                    m13691c();
                    return;
                } else if (this.f12623y.getText().toString().equals("上报客服")) {
                    m13687b();
                    return;
                } else {
                    return;
                }
            case C2425R.C2427id.start_check_btn /*{ENCODED_INT: 2131756020}*/:
                if (!this.f12578K) {
                    this.f12578K = true;
                    this.f12576I = false;
                    String i2 = WiFiUtil.m14022a(GBApplication.instance()).mo27615i();
                    if (NetworkUtils.m14430b(i2)) {
                        this.f12614p = i2.toLowerCase().trim();
                        m13697e();
                        m13694d();
                        return;
                    }
                    showMessageToast("请先连接" + getResources().getString(C2425R.C2429string.wifi_name) + "网络");
                    this.f12578K = false;
                    return;
                }
                return;
            default:
                return;
        }
    }

    /* renamed from: b */
    private void m13687b() {
        String saveDeviceTestPic = GiwifiHXService.getInstance(GBApplication.instance()).saveDeviceTestPic(DeviceTest2Util.m13710a(DeviceTest2Util.m13711a(this.f12619u)));
        Intent intent = new Intent(Constant.f13244bS);
        intent.putExtra("file_absolutepath", saveDeviceTestPic);
        GBApplication.instance().sendBroadcast(intent);
        this.f12598ad.sendMessage(this.f12598ad.obtainMessage(3));
    }

    /* renamed from: c */
    private void m13691c() {
        String a;
        if (this.f12575H != null) {
            this.f12622x.setEnabled(false);
            GBApplication instance = GBApplication.instance();
            int intValue = this.f12575H.getGwReqState().intValue();
            String gwReqMessage = this.f12575H.getGwReqMessage();
            int intValue2 = this.f12575H.getInternetReqState().intValue();
            String internetReqMessage = this.f12575H.getInternetReqMessage();
            int intValue3 = this.f12575H.getLoginReqState().intValue();
            String loginReqMessage = this.f12575H.getLoginReqMessage();
            String k = WiFiUtil.m14022a(GBApplication.instance()).mo27617k();
            int i = this.f12615q ? 1 : 0;
            int i2 = this.f12616r ? 1 : 0;
            String charSequence = this.f12583P.getText().toString();
            int i3 = this.f12591X;
            if (this.f12570C == null) {
                a = "";
            } else {
                a = JsonUtil.m14387a((Object) this.f12570C);
            }
            HttpUtil.m14250a(instance, intValue, gwReqMessage, intValue2, internetReqMessage, intValue3, loginReqMessage, k, i, i2, charSequence, i3, a, this.f12614p, this.f12599ae, "");
        } else if (!this.f12615q) {
            showMessageToast("网关异常，请重新检测");
        } else {
            showMessageToast("请检测网络后再上报");
        }
    }

    /* renamed from: d */
    private void m13694d() {
        new Thread(new Runnable() {
            /* class com.gbcom.gwifi.functions.test.DeviceTest2Activity.RunnableC33403 */

            @Override // java.lang.Runnable
            public void run() {
                DeviceTest2Activity.this.f12574G = 0;
                DeviceTest2Activity.this.f12570C = null;
                DeviceTest2Activity.this.f12575H = null;
                DeviceTest2Activity.this.f12591X = 0;
                WiFiUtil a = WiFiUtil.m14022a(GBApplication.instance());
                String k = a.mo27617k();
                Log.m14398b("DeviceTest2Activity", "gatewayIp=" + k);
                DeviceTest2Activity.this.f12615q = PingUtil.m11523a(k);
                if (DeviceTest2Activity.this.f12615q) {
                    Log.m14398b("DeviceTest2Activity", "pingGateway succeed");
                } else {
                    Log.m14398b("DeviceTest2Activity", "pingGateway fail");
                }
                if (!DeviceTest2Activity.this.f12576I) {
                    DeviceTest2Activity.this.f12616r = DeviceTest2Activity.this.pingCloud();
                    if (!DeviceTest2Activity.this.f12576I) {
                        String v = a.mo27628v();
                        Log.m14398b("DeviceTest2Activity", "wifiDns=" + v);
                        if (v.equals(k)) {
                            DeviceTest2Activity.this.f12617s = DeviceTest2Activity.this.f12615q;
                        } else {
                            DeviceTest2Activity.this.f12617s = PingUtil.m11523a(v);
                            if (DeviceTest2Activity.this.f12617s) {
                                Log.m14398b("DeviceTest2Activity", "pingDns succeed");
                            } else {
                                Log.m14398b("DeviceTest2Activity", "pingDns fail");
                            }
                        }
                        DeviceTest2Activity.this.checkGateWay();
                    }
                }
            }
        }).start();
    }

    /* renamed from: e */
    private void m13697e() {
        new Thread(new Runnable() {
            /* class com.gbcom.gwifi.functions.test.DeviceTest2Activity.RunnableC33414 */

            @Override // java.lang.Runnable
            public void run() {
                int i = 0;
                while (DeviceTest2Activity.this.f12578K) {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        DeviceTest2Activity.this.f12578K = false;
                        e.printStackTrace();
                    }
                    Message obtainMessage = DeviceTest2Activity.this.f12598ad.obtainMessage(1);
                    obtainMessage.obj = Integer.valueOf(i);
                    DeviceTest2Activity.this.f12598ad.sendMessage(obtainMessage);
                    if (i == 100) {
                        i = 0;
                    }
                    i++;
                }
            }
        }).start();
    }

    /* renamed from: f */
    private void m13699f() {
        this.f12618t.setVisibility(0);
        this.f12619u.setVisibility(8);
        this.f12620v.setVisibility(8);
        this.f12571D.setVisibility(0);
        this.f12572E.setVisibility(8);
        this.f12573F.setVisibility(8);
    }

    public boolean pingCloud() {
        String stationCloud;
        if (CacheAuth.getInstance().isPortal()) {
            stationCloud = CacheAuth.getInstance().getPortalHost();
            if (C3467s.m14513e(stationCloud)) {
                stationCloud = "";
            }
        } else {
            stationCloud = CacheAuth.getInstance().getStationCloud();
            if (TextUtils.isEmpty(stationCloud)) {
                stationCloud = "login.gwifi.com.cn";
            }
        }
        if (C3467s.m14513e(stationCloud)) {
            Log.m14403e("DeviceTest2Activity", "pingCloud empty stationCloud");
            return false;
        }
        Log.m14398b("DeviceTest2Activity", "begin pingCloud " + stationCloud);
        boolean a = PingUtil.m11523a(stationCloud);
        if (a) {
            Log.m14398b("DeviceTest2Activity", "pingCloud succeed");
            return a;
        }
        Log.m14398b("DeviceTest2Activity", "pingCloud fail");
        return a;
    }

    public void checkGateWay() {
        DeviceCheckNetWorkUtils.m13906a(GBApplication.instance(), new CallBack() {
            /* class com.gbcom.gwifi.functions.test.DeviceTest2Activity.C33425 */

            @Override // com.gbcom.gwifi.base.p234c.CallBack
            /* renamed from: a */
            public void mo24437a(Object obj) {
                DeviceTest2Activity.this.f12575H = (CheckResult) obj;
                if (!DeviceTest2Activity.this.f12576I) {
                    new Thread(new Runnable() {
                        /* class com.gbcom.gwifi.functions.test.DeviceTest2Activity.C33425.RunnableC33431 */

                        @Override // java.lang.Runnable
                        public void run() {
                            DeviceTest2Activity.this.checkAp();
                        }
                    }).start();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: g */
    private void m13702g() {
        int i;
        this.f12618t.setVisibility(8);
        this.f12619u.setVisibility(0);
        this.f12620v.setVisibility(0);
        this.f12578K = false;
        if (this.f12615q) {
            this.f12624z.setImageResource(C2425R.C2426drawable.ic_success_ping);
        } else {
            this.f12624z.setImageResource(C2425R.C2426drawable.ic_fail_ping);
        }
        if (this.f12616r) {
            this.f12568A.setImageResource(C2425R.C2426drawable.ic_success_ping);
        } else {
            this.f12568A.setImageResource(C2425R.C2426drawable.ic_fail_ping);
        }
        WiFiUtil a = WiFiUtil.m14022a(GBApplication.instance());
        String j = a.mo27616j();
        String u = a.mo27627u();
        String v = a.mo27628v();
        String w = a.mo27629w();
        String k = a.mo27617k();
        this.f12579L.setText(CacheAuth.getInstance().getGwSn());
        this.f12580M.setText(k);
        this.f12581N.setText(CacheAccount.getInstance().getLoginPhone());
        this.f12592Y.setText(j + "  (" + u + MessageStore.f23536t);
        this.f12593Z.setText(v + "," + w);
        if (v.equals(k)) {
            if (this.f12615q) {
                this.f12569B.setImageResource(C2425R.C2426drawable.ic_success_ping);
            } else {
                this.f12569B.setImageResource(C2425R.C2426drawable.ic_fail_ping);
            }
        } else if (this.f12617s) {
            this.f12569B.setImageResource(C2425R.C2426drawable.ic_success_ping);
        } else {
            this.f12569B.setImageResource(C2425R.C2426drawable.ic_fail_ping);
        }
        this.f12582O.setText(CacheAuth.getInstance().getLocalMac());
        if (this.f12575H == null) {
            this.f12572E.setVisibility(8);
            this.f12573F.setVisibility(8);
            return;
        }
        this.f12589V.setText("未请求");
        this.f12587T.setText("未请求");
        this.f12588U.setText("未请求");
        this.f12589V.setTextColor(getResources().getColor(C2425R.color.grey_3));
        this.f12587T.setTextColor(getResources().getColor(C2425R.color.grey_3));
        this.f12588U.setTextColor(getResources().getColor(C2425R.color.grey_3));
        int authMethod = CacheAuth.getInstance().getAuthMethod();
        if (authMethod == 1) {
            this.f12586S.setText("云认证");
            this.f12586S.setTextColor(getResources().getColor(C2425R.color.green_3));
        } else if (authMethod == 2) {
            this.f12586S.setText("本地认证");
            this.f12586S.setTextColor(getResources().getColor(C2425R.color.green_3));
        } else {
            this.f12586S.setText(Constant.f13330p);
            this.f12586S.setTextColor(getResources().getColor(C2425R.color.grey_3));
        }
        if (this.f12575H.getGwReqState().intValue() == 1) {
            AuthInfo authInfo = this.f12575H.getAuthInfo();
            String stationSn = authInfo.getStationSn();
            Integer authState = authInfo.getAuthState();
            Integer loginReqState = this.f12575H.getLoginReqState();
            this.f12579L.setText(stationSn);
            this.f12587T.setText(m13690c(authState));
            this.f12587T.setTextColor(getResources().getColor(C2425R.color.green_3));
            if (!(authState.intValue() == 0 || authState.intValue() == 2 || authState.intValue() == 200 || authState.intValue() == 253)) {
                this.f12589V.setText(m13686b(loginReqState));
                if (loginReqState.intValue() == 1) {
                    this.f12589V.setTextColor(getResources().getColor(C2425R.color.green_3));
                } else {
                    this.f12589V.setTextColor(getResources().getColor(C2425R.color.red));
                }
            }
        } else {
            if (this.f12575H.getGwReqState().intValue() == 1001) {
                this.f12587T.setText("超时");
            } else if (this.f12575H.getGwReqState().intValue() == 1002) {
                this.f12587T.setText("解析失败");
            } else if (this.f12575H.getGwReqState().intValue() == 0) {
                this.f12587T.setText(DeviceTestReport.f12667o);
            } else {
                this.f12587T.setText("未知状态:" + ((Object) this.f12575H.getGwReqState()));
            }
            this.f12587T.setTextColor(getResources().getColor(C2425R.color.red));
        }
        this.f12588U.setTextColor(getResources().getColor(C2425R.color.grey_3));
        if (this.f12575H.getInternetReqState().intValue() == 0) {
            this.f12588U.setText("上不了外网");
        } else if (this.f12575H.getInternetReqState().intValue() == 1) {
            int intValue = this.f12575H.getOnlineState().intValue();
            this.f12588U.setText(m13681a(Integer.valueOf(intValue)));
            if (intValue == 2) {
                this.f12588U.setTextColor(getResources().getColor(C2425R.color.green_3));
            } else {
                this.f12588U.setTextColor(getResources().getColor(C2425R.color.red));
            }
        }
        this.f12572E.setVisibility(0);
        this.f12584Q.setText(this.f12614p);
        WifiInfo m = WiFiUtil.m14021a().mo27619m();
        if (m != null) {
            i = m.getRssi();
        } else {
            i = 0;
        }
        if (i != 0) {
            this.f12583P.setText("" + i);
            this.f12583P.setVisibility(0);
        } else {
            this.f12583P.setVisibility(8);
        }
        this.f12591X = WiFiUtil.m14027b(this);
        if (this.f12591X > 0) {
            this.f12585R.setText(this.f12591X + "");
            this.f12585R.setVisibility(0);
        } else {
            this.f12585R.setVisibility(8);
        }
        this.f12573F.setVisibility(0);
    }

    /* renamed from: a */
    private String m13681a(Integer num) {
        if (num == null) {
            return "";
        }
        switch (num.intValue()) {
            case 0:
                return "上不了外网";
            case 1:
                return "需要认证";
            case 2:
                return "可上网";
            default:
                return "未知状态:" + ((Object) num);
        }
    }

    /* renamed from: b */
    private String m13686b(Integer num) {
        if (num == null) {
            return "";
        }
        switch (num.intValue()) {
            case 0:
                return DeviceTestReport.f12667o;
            case 1:
                return "成功";
            case 1001:
                return "超时";
            case 1002:
                return "解析失败";
            case 1003:
                return "业务失败";
            default:
                return "未知状态:" + ((Object) num);
        }
    }

    /* renamed from: c */
    private String m13690c(Integer num) {
        if (num == null) {
            return "";
        }
        switch (num.intValue()) {
            case 0:
                return "直通";
            case 2:
                return "已认证";
            case 200:
                return "已认证，只能访问内网";
            case 253:
                return "临时放行";
            default:
                return "需要认证";
        }
    }

    public void checkAp() {
        this.f12598ad.sendMessage(this.f12598ad.obtainMessage(2));
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onDestroy() {
        super.onDestroy();
        this.f12578K = false;
    }
}
