package com.gbcom.gwifi.functions.wifi;

import android.app.Dialog;
import android.app.StatsManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.AnimationDrawable;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.base.p233b.GBGlobalConfig;
import com.gbcom.gwifi.functions.wifi.entity.BaseWifi;
import com.gbcom.gwifi.functions.wifi.entity.EapWifi;
import com.gbcom.gwifi.functions.wifi.entity.NoneWifi;
import com.gbcom.gwifi.functions.wifi.entity.PskWifi;
import com.gbcom.gwifi.functions.wifi.entity.WepWifi;
import com.gbcom.gwifi.functions.wifi.p253a.WifiState;
import com.gbcom.gwifi.third.umeng.analytics.GiwifiMobclickAgent;
import com.gbcom.gwifi.third.umeng.analytics.UmengCount;
import com.gbcom.gwifi.util.C3467s;
import com.gbcom.gwifi.util.Log;
import com.gbcom.gwifi.util.NetworkUtils;
import com.gbcom.gwifi.util.cache.CacheApp;
import com.gbcom.gwifi.util.cache.CacheAuth;
import com.gbcom.gwifi.util.cache.CacheWiFi;
import com.gbcom.gwifi.util.p257b.UIUtil;
import com.umeng.message.proguard.MessageStore;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import p419io.netty.handler.traffic.AbstractTrafficShapingHandler;

public class WiFiActivity extends GBActivity implements ExpandableListView.OnChildClickListener, ExpandableListView.OnGroupClickListener, GBGlobalConfig.AbstractC2565a {

    /* renamed from: b */
    private static final String f12777b = "WiFiActivity";
    public static WifiState currentState = WifiState.TRY_CONNECT;

    /* renamed from: o */
    private static final int f12778o = 2;

    /* renamed from: p */
    private static final int f12779p = 8;

    /* renamed from: q */
    private static final int f12780q = 9;

    /* renamed from: r */
    private static final int f12781r = 10;

    /* renamed from: s */
    private static final int f12782s = 12;

    /* renamed from: t */
    private static final int f12783t = 21;

    /* renamed from: u */
    private static final int f12784u = 22;

    /* renamed from: v */
    private static final int f12785v = 23;

    /* renamed from: A */
    private int f12786A = 0;

    /* renamed from: B */
    private RelativeLayout f12787B;

    /* renamed from: C */
    private Button f12788C;

    /* renamed from: D */
    private ImageView f12789D;

    /* renamed from: E */
    private AnimationDrawable f12790E;

    /* renamed from: F */
    private boolean f12791F = false;

    /* renamed from: G */
    private ExpandableListView f12792G;

    /* renamed from: H */
    private String[] f12793H;

    /* renamed from: I */
    private CheckBox f12794I;

    /* renamed from: J */
    private long f12795J;

    /* renamed from: K */
    private boolean f12796K = false;

    /* renamed from: L */
    private Locale f12797L;

    /* renamed from: M */
    private TextView f12798M;

    /* renamed from: N */
    private LinkedHashMap<String, ArrayList<BaseWifi>> f12799N = new LinkedHashMap<>();

    /* renamed from: O */
    private Handler f12800O = new Handler() {
        /* class com.gbcom.gwifi.functions.wifi.WiFiActivity.HandlerC33847 */

        public void handleMessage(Message message) {
            switch (message.what) {
                case 9:
                    if (!WiFiActivity.this.f12809h.isWifiEnabled()) {
                        GBActivity.showMessageToast("WiFi启动失败，请手动启动WiFi");
                        WiFiActivity.this.m13861e();
                        return;
                    }
                    return;
                case 10:
                case 22:
                default:
                    return;
                case 12:
                    WiFiActivity.this.dealWifiList((List) message.obj);
                    return;
                case 21:
                    WiFiActivity.this.m13863f();
                    return;
                case 23:
                    if (!WiFiActivity.this.f12796K) {
                    }
                    return;
            }
        }
    };

    /* renamed from: P */
    private BroadcastReceiver f12801P = new BroadcastReceiver() {
        /* class com.gbcom.gwifi.functions.wifi.WiFiActivity.C33858 */

        public void onReceive(Context context, Intent intent) {
            WifiInfo connectionInfo;
            if (intent.getAction().equals(WifiManager.NETWORK_STATE_CHANGED_ACTION)) {
                NetworkInfo networkInfo = (NetworkInfo) intent.getParcelableExtra("networkInfo");
                if (networkInfo.getState() == NetworkInfo.State.DISCONNECTING) {
                    WiFiActivity.this.changeWifiState(WifiState.CLOSING);
                } else if (networkInfo.getState() == NetworkInfo.State.DISCONNECTED) {
                    WiFiActivity.this.changeWifiState(WifiState.DISCONNECTED);
                } else if (networkInfo.getState() == NetworkInfo.State.CONNECTING) {
                    WiFiActivity.this.changeWifiState(WifiState.CONNECTING);
                } else if (networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                    WiFiActivity.this.changeWifiState(WifiState.CONNECTED);
                }
            } else if (intent.getAction().equals(WifiManager.WIFI_STATE_CHANGED_ACTION)) {
                int intExtra = intent.getIntExtra("wifi_state", 1);
                int intExtra2 = intent.getIntExtra("previous_wifi_state", 1);
                switch (intExtra) {
                    case 0:
                        if (intExtra2 != 1) {
                            WiFiActivity.this.changeWifiState(WifiState.CLOSING);
                            return;
                        }
                        return;
                    case 1:
                        if (intExtra2 != 1) {
                            WiFiActivity.this.changeWifiState(WifiState.CLOSED);
                            return;
                        }
                        return;
                    case 2:
                        if (intExtra2 == 1 || intExtra2 == 0) {
                            WiFiActivity.this.changeWifiState(WifiState.OPENING);
                            return;
                        }
                        return;
                    case 3:
                        if (intExtra2 != 3) {
                            WiFiActivity.this.changeWifiState(WifiState.OPENED);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            } else if (intent.getAction().equals(WifiManager.SUPPLICANT_STATE_CHANGED_ACTION)) {
                SupplicantState supplicantState = (SupplicantState) intent.getParcelableExtra(WifiManager.EXTRA_NEW_STATE);
                if (intent.getIntExtra(WifiManager.EXTRA_SUPPLICANT_ERROR, -1) == 1) {
                    WiFiActivity.this.changeWifiState(WifiState.AUTH_FAILED);
                } else if (supplicantState == SupplicantState.INACTIVE) {
                    WiFiActivity.this.changeWifiState(WifiState.INACTIVE);
                }
            } else if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                if (WiFiUtil.m14022a(WiFiActivity.this).mo27622p()) {
                    CacheAuth.getInstance().resetCacheAuthBean();
                }
            } else if (intent.getAction().equals(WifiManager.RSSI_CHANGED_ACTION) && (connectionInfo = WiFiActivity.this.f12809h.getConnectionInfo()) != null && WiFiUtil.m14021a().mo27626t().equals("WIFI")) {
                synchronized (WiFiActivity.this.f12802Q) {
                    if (WiFiActivity.this.f12807f != null) {
                        WiFiActivity.this.m13846a((WiFiActivity) WiFiActivity.this.f12807f, (ImageView) WifiManager.calculateSignalLevel(connectionInfo.getRssi(), 4));
                    }
                }
            }
        }
    };

    /* renamed from: Q */
    private BaseExpandableListAdapter f12802Q = new BaseExpandableListAdapter() {
        /* class com.gbcom.gwifi.functions.wifi.WiFiActivity.C33792 */

        public boolean isChildSelectable(int i, int i2) {
            return true;
        }

        public boolean hasStableIds() {
            return false;
        }

        public View getGroupView(int i, boolean z, View view, ViewGroup viewGroup) {
            BaseWifi baseWifi = (BaseWifi) WiFiActivity.this.f12808g.get(i);
            if (baseWifi.getIsTitle().booleanValue()) {
                return m13880a(baseWifi.getSsid());
            }
            if (baseWifi.getIsGroup().booleanValue()) {
                return m13879a(baseWifi, view, null, true, z, 0);
            }
            return m13878a(baseWifi, view, (C3390d) null, false);
        }

        /* renamed from: a */
        private View m13880a(String str) {
            View inflate = ((LayoutInflater) WiFiActivity.this.getSystemService("layout_inflater")).inflate(C2425R.layout.wifi_group_title, (ViewGroup) null);
            ((TextView) inflate.findViewById(C2425R.C2427id.wifi_divide_title)).setText(str);
            return inflate;
        }

        /* renamed from: a */
        private View m13878a(BaseWifi baseWifi, View view, C3390d dVar, boolean z) {
            if (z) {
                return m13879a(baseWifi, view, dVar, z, true, 0);
            }
            return m13879a(baseWifi, view, dVar, z, false, -1);
        }

        /* renamed from: a */
        private View m13879a(BaseWifi baseWifi, View view, C3390d dVar, boolean z, boolean z2, int i) {
            C3390d dVar2;
            if (view == null || view.getTag() == null) {
                view = ((LayoutInflater) WiFiActivity.this.getSystemService("layout_inflater")).inflate(C2425R.layout.wifi_group_item, (ViewGroup) null);
                dVar2 = new C3390d();
                dVar2.f12840a = (LinearLayout) view.findViewById(C2425R.C2427id.group_title_layout);
                dVar2.f12841b = (TextView) view.findViewById(C2425R.C2427id.group_title_tv);
                dVar2.f12842c = (ImageView) view.findViewById(C2425R.C2427id.group_iv);
                dVar2.f12843d = (LinearLayout) view.findViewById(C2425R.C2427id.group_sub_layout);
                dVar2.f12844e = (TextView) view.findViewById(16908310);
                dVar2.f12845f = (ImageView) view.findViewById(C2425R.C2427id.sub_wifi_signal);
                dVar2.f12847h = (ImageView) view.findViewById(C2425R.C2427id.sub_wifi_auth_type_iv);
                dVar2.f12846g = (ImageView) view.findViewById(C2425R.C2427id.sub_wifi_rotate_icon);
                view.setTag(dVar2);
            } else {
                dVar2 = (C3390d) view.getTag();
            }
            dVar2.f12846g.clearAnimation();
            dVar2.f12846g.setVisibility(4);
            dVar2.f12840a.setVisibility(8);
            dVar2.f12843d.setVisibility(8);
            if (!z) {
                dVar2.f12843d.setVisibility(0);
                String a = WiFi.m13960a(baseWifi.getCapabilities());
                int calculateSignalLevel = WifiManager.calculateSignalLevel(baseWifi.getLevel(), 4);
                Log.m14398b(WiFiActivity.f12777b, "SSID#:" + baseWifi.getSsid() + "#" + calculateSignalLevel);
                dVar2.f12844e.setText(baseWifi.getSsid());
                dVar2.f12844e.setTextColor(WiFiActivity.this.getResources().getColor(C2425R.color.grey_4));
                if (a.equals(WiFi.f13010d)) {
                    dVar2.f12847h.setVisibility(4);
                } else {
                    dVar2.f12847h.setImageResource(C2425R.C2426drawable.input_password_lock_icon);
                    dVar2.f12847h.setVisibility(0);
                }
                if (WiFiActivity.this.m13852a((WiFiActivity) baseWifi)) {
                    dVar2.f12847h.setImageResource(C2425R.C2426drawable.wifi_connected_icon);
                    dVar2.f12847h.setVisibility(0);
                    dVar2.f12846g.setVisibility(4);
                    WiFiActivity.this.f12807f = dVar2.f12845f;
                    dVar2.f12844e.setTextColor(WiFiActivity.this.getResources().getColor(C2425R.color.current_wifi_title));
                } else if (WiFiActivity.this.f12810i != null && WiFiActivity.this.f12810i.getSsid().equalsIgnoreCase(baseWifi.getSsid()) && (WiFiActivity.currentState == WifiState.TRY_CONNECT || WiFiActivity.currentState == WifiState.CONNECTING)) {
                    dVar2.f12846g.setVisibility(0);
                    dVar2.f12846g.startAnimation(WiFiActivity.this.f12811j);
                    dVar2.f12847h.setVisibility(4);
                }
                WiFiActivity.this.m13846a((WiFiActivity) dVar2.f12845f, (ImageView) calculateSignalLevel);
            } else {
                dVar2.f12840a.setVisibility(0);
                String ssid = baseWifi.getSsid();
                dVar2.f12841b.setText(ssid + MessageStore.f23535s + ((ArrayList) WiFiActivity.this.f12799N.get(ssid)).size() + MessageStore.f23536t);
                dVar2.f12840a.setVisibility(0);
                if (z2) {
                    dVar2.f12842c.setImageResource(C2425R.C2426drawable.arrow_up);
                } else {
                    dVar2.f12842c.setImageResource(C2425R.C2426drawable.arrow_down);
                }
            }
            return view;
        }

        public long getGroupId(int i) {
            return (long) i;
        }

        public int getGroupCount() {
            return WiFiActivity.this.f12808g.size();
        }

        public Object getGroup(int i) {
            return WiFiActivity.this.f12808g.get(i);
        }

        public View getChildView(int i, int i2, boolean z, View view, ViewGroup viewGroup) {
            return m13877a(view, i, i2, (C3389c) null);
        }

        /* renamed from: a */
        private View m13877a(View view, int i, int i2, C3389c cVar) {
            C3389c cVar2;
            ArrayList arrayList = (ArrayList) WiFiActivity.this.f12799N.get(((BaseWifi) WiFiActivity.this.f12808g.get(i)).getSsid());
            if (arrayList == null || arrayList.size() <= i2) {
                return null;
            }
            BaseWifi baseWifi = (BaseWifi) arrayList.get(i2);
            if (view == null) {
                C3389c cVar3 = new C3389c();
                view = ((LayoutInflater) WiFiActivity.this.getSystemService("layout_inflater")).inflate(C2425R.layout.wifi_list_item, (ViewGroup) null);
                cVar3.f12836a = (TextView) view.findViewById(16908310);
                cVar3.f12839d = (ImageView) view.findViewById(C2425R.C2427id.wifi_auth_type_iv);
                cVar3.f12837b = (ImageView) view.findViewById(C2425R.C2427id.wifi_signal);
                cVar3.f12838c = (ImageView) view.findViewById(C2425R.C2427id.wifi_rotate_icon);
                view.setTag(cVar3);
                cVar2 = cVar3;
            } else {
                cVar2 = (C3389c) view.getTag();
                cVar2.f12839d.setVisibility(4);
                cVar2.f12838c.clearAnimation();
                cVar2.f12838c.setVisibility(4);
            }
            String a = WiFi.m13960a(baseWifi.getCapabilities());
            int calculateSignalLevel = WifiManager.calculateSignalLevel(baseWifi.getLevel(), 4);
            cVar2.f12836a.setText(baseWifi.getSsid());
            if (a.equals(WiFi.f13010d)) {
                cVar2.f12839d.setVisibility(4);
            } else {
                cVar2.f12839d.setImageResource(C2425R.C2426drawable.input_password_lock_icon);
                cVar2.f12839d.setVisibility(0);
            }
            if (WiFiActivity.this.m13852a((WiFiActivity) baseWifi)) {
                cVar2.f12839d.setImageResource(C2425R.C2426drawable.wifi_connected_icon);
                cVar2.f12839d.setVisibility(0);
                WiFiActivity.this.f12807f = cVar2.f12837b;
                cVar2.f12838c.setVisibility(4);
            } else if (WiFiActivity.this.f12810i != null && WiFiActivity.this.f12810i.getSsid().equalsIgnoreCase(baseWifi.getSsid()) && (WiFiActivity.currentState == WifiState.TRY_CONNECT || WiFiActivity.currentState == WifiState.CONNECTING)) {
                cVar2.f12838c.setVisibility(0);
                cVar2.f12838c.startAnimation(WiFiActivity.this.f12811j);
                cVar2.f12839d.setVisibility(4);
            }
            WiFiActivity.this.m13846a((WiFiActivity) cVar2.f12837b, (ImageView) calculateSignalLevel);
            return view;
        }

        public int getChildrenCount(int i) {
            ArrayList arrayList = (ArrayList) WiFiActivity.this.f12799N.get(((BaseWifi) WiFiActivity.this.f12808g.get(i)).getSsid());
            if (arrayList == null) {
                return 0;
            }
            return arrayList.size();
        }

        public long getChildId(int i, int i2) {
            return (long) ((i * 100) + i2);
        }

        public Object getChild(int i, int i2) {
            ArrayList arrayList = (ArrayList) WiFiActivity.this.f12799N.get(((BaseWifi) WiFiActivity.this.f12808g.get(i)).getSsid());
            if (arrayList == null || arrayList.size() <= i2) {
                return null;
            }
            return arrayList.get(i2);
        }
    };

    /* renamed from: a */
    Handler f12803a = new Handler() {
        /* class com.gbcom.gwifi.functions.wifi.WiFiActivity.HandlerC337811 */

        public void handleMessage(Message message) {
            ImageView imageView = (ImageView) message.obj;
            if (WiFiActivity.this.f12791F) {
                imageView.setImageResource(C2425R.C2426drawable.show_password);
            } else {
                imageView.setImageResource(C2425R.C2426drawable.noshow_password);
            }
        }
    };

    /* renamed from: c */
    private RelativeLayout f12804c;

    /* renamed from: d */
    private TextView f12805d;

    /* renamed from: e */
    private TextView f12806e;

    /* renamed from: f */
    private ImageView f12807f;

    /* renamed from: g */
    private ArrayList<BaseWifi> f12808g;

    /* renamed from: h */
    private WifiManager f12809h;

    /* renamed from: i */
    private BaseWifi f12810i;

    /* renamed from: j */
    private Animation f12811j;

    /* renamed from: k */
    private long f12812k = -1;

    /* renamed from: l */
    private long f12813l = -1;

    /* renamed from: m */
    private ArrayList<BaseWifi> f12814m = new ArrayList<>();

    /* renamed from: n */
    private C3388b f12815n;

    /* renamed from: w */
    private HandlerC3387a f12816w;

    /* renamed from: x */
    private boolean f12817x = false;

    /* renamed from: y */
    private IntentFilter f12818y;

    /* renamed from: z */
    private BroadcastReceiver f12819z;

    @Override // android.support.p009v4.app.BaseFragmentActivityGingerbread, com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle, "WiFi列表界面", C2425R.layout.wifi_activity, true);
        m13857c();
        this.f12817x = this.f12809h.isWifiEnabled();
        m13861e();
        this.f12811j = AnimationUtils.loadAnimation(this, C2425R.anim.wifi_rotate_anim);
        if (this.f12817x) {
            this.f12812k = System.currentTimeMillis();
        }
        this.f12792G.setOnGroupClickListener(this);
        this.f12792G.setOnChildClickListener(this);
        m13845a();
        if (this.f12817x) {
            m13853b();
        }
    }

    /* renamed from: a */
    private void m13845a() {
        if (this.f12819z == null) {
            this.f12819z = new BroadcastReceiver() {
                /* class com.gbcom.gwifi.functions.wifi.WiFiActivity.C33761 */

                public void onReceive(Context context, Intent intent) {
                    if (System.currentTimeMillis() >= WiFiActivity.this.f12795J + 3000) {
                        WiFiActivity.this.f12795J = System.currentTimeMillis();
                        WiFiActivity.this.m13853b();
                    }
                }
            };
        }
        registerReceiver(this.f12801P, this.f12818y);
        registerReceiver(this.f12819z, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: b */
    private void m13853b() {
        List<ScanResult> scanResults = this.f12809h.getScanResults();
        this.f12800O.removeMessages(12);
        Message obtainMessage = this.f12800O.obtainMessage(12);
        obtainMessage.obj = scanResults;
        this.f12800O.sendMessage(obtainMessage);
    }

    /* renamed from: c */
    private void m13857c() {
        this.f12804c = (RelativeLayout) findViewById(C2425R.C2427id.title_back_layout);
        this.f12804c.setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.wifi.WiFiActivity.View$OnClickListenerC33814 */

            public void onClick(View view) {
                WiFiActivity.this.finish();
            }
        });
        this.f12806e = (TextView) findViewById(C2425R.C2427id.title_main_tv);
        this.f12805d = (TextView) findViewById(C2425R.C2427id.title_edit_tv);
        this.f12806e.setText("附近的WiFi");
        this.f12805d.setText("");
        this.f12809h = (WifiManager) getApplicationContext().getSystemService("wifi");
        this.f12811j = AnimationUtils.loadAnimation(this, C2425R.anim.wifi_rotate_anim);
        this.f12787B = (RelativeLayout) findViewById(C2425R.C2427id.wifi_close_rl);
        this.f12808g = new ArrayList<>();
        m13859d();
        this.f12792G = (ExpandableListView) findViewById(C2425R.C2427id.nearby_lv);
        this.f12792G.setAdapter(this.f12802Q);
        this.f12794I = (CheckBox) findViewById(C2425R.C2427id.wifi_switch_cb);
        this.f12798M = (TextView) findViewById(C2425R.C2427id.wifi_alert_tv);
        System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
        this.f12789D = (ImageView) findViewById(C2425R.C2427id.wifi_state_iv);
        this.f12788C = (Button) findViewById(C2425R.C2427id.wifi_state_bt);
        this.f12790E = (AnimationDrawable) this.f12789D.getBackground();
        this.f12794I.setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.wifi.WiFiActivity.View$OnClickListenerC33825 */

            public void onClick(View view) {
                UmengCount.queryCloseWifi(WiFiActivity.this);
                WiFiUtil.m14022a(WiFiActivity.this).mo27607c();
            }
        });
        this.f12788C.setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.wifi.WiFiActivity.View$OnClickListenerC33836 */

            public void onClick(View view) {
                WiFiActivity.this.f12798M.setText("正在开启WiFi...");
                WiFiActivity.this.f12788C.setText("开启中...");
                WiFiActivity.this.f12788C.setEnabled(false);
                WiFiActivity.this.f12800O.removeMessages(9);
                WiFiActivity.this.f12800O.sendEmptyMessageDelayed(9, AbstractTrafficShapingHandler.DEFAULT_MAX_TIME);
                WiFiActivity.this.f12790E.start();
                WiFiUtil.m14022a(WiFiActivity.this).mo27604b();
            }
        });
        this.f12797L = Locale.getDefault();
        this.f12815n = new C3388b();
        HandlerThread handlerThread = new HandlerThread("cache");
        handlerThread.start();
        this.f12816w = new HandlerC3387a(handlerThread.getLooper());
        this.f12818y = new IntentFilter();
        this.f12818y.addAction(WifiManager.RSSI_CHANGED_ACTION);
        this.f12818y.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        this.f12818y.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        this.f12818y.addAction(WifiManager.SUPPLICANT_STATE_CHANGED_ACTION);
        this.f12818y.addAction(WifiManager.NETWORK_IDS_CHANGED_ACTION);
        this.f12818y.addAction("android.net.conn.CONNECTIVITY_CHANGE");
    }

    /* renamed from: d */
    private void m13859d() {
        this.f12793H = C3467s.m14511c(CacheWiFi.getInstance().getShortSsidPrefix());
    }

    public void updateWifiSwitchView(boolean z) {
        if (this.f12817x != z) {
            this.f12817x = z;
            m13861e();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: e */
    private void m13861e() {
        if (!this.f12817x) {
            this.f12798M.setText("您的手机尚未开启WiFi");
            this.f12788C.setText("开启WiFi");
            this.f12790E.stop();
            this.f12790E.selectDrawable(0);
            this.f12788C.setEnabled(true);
            this.f12787B.setVisibility(0);
            return;
        }
        this.f12794I.setChecked(false);
        this.f12790E.stop();
        this.f12787B.setVisibility(8);
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onResume() {
        super.onResume();
        GiwifiMobclickAgent.onPageStart(f12777b);
    }

    public void updateWifiList(ArrayList<BaseWifi> arrayList) {
        int i = 0;
        if (arrayList != null) {
            String cacheBssidList = CacheApp.getInstance().getCacheBssidList();
            String cacheSsidList = CacheApp.getInstance().getCacheSsidList();
            ArrayList arrayList2 = new ArrayList();
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                if ((arrayList.get(i2).getBssid() != null && cacheBssidList.contains(arrayList.get(i2).getBssid().toUpperCase())) || (arrayList.get(i2).getSsid() != null && cacheSsidList.contains(arrayList.get(i2).getSsid()))) {
                    arrayList2.add(arrayList.get(i2));
                }
            }
            if (arrayList2.size() > 0) {
                arrayList.removeAll(arrayList2);
                Collections.sort(arrayList2, this.f12815n);
                Collections.sort(arrayList, this.f12815n);
                NoneWifi noneWifi = new NoneWifi();
                noneWifi.setSsid(getResources().getString(C2425R.C2429string.wifi_name) + "网络");
                noneWifi.setIsTitle(true);
                arrayList2.add(0, noneWifi);
                arrayList.addAll(0, arrayList2);
                i = arrayList2.size();
            } else if (arrayList.size() <= 0) {
                i = -1;
            }
            if (!(i == -1 || i == arrayList.size())) {
                NoneWifi noneWifi2 = new NoneWifi();
                noneWifi2.setSsid("附近的WiFi");
                noneWifi2.setIsTitle(true);
                arrayList.add(i, noneWifi2);
            }
            m13848a(arrayList);
        }
    }

    /* renamed from: a */
    private void m13848a(ArrayList<BaseWifi> arrayList) {
        if (arrayList != null) {
            LinkedHashMap<String, ArrayList<BaseWifi>> linkedHashMap = new LinkedHashMap<>();
            if (this.f12793H != null) {
                String[] strArr = this.f12793H;
                for (String str : strArr) {
                    ArrayList<BaseWifi> arrayList2 = new ArrayList<>();
                    Iterator<BaseWifi> it = arrayList.iterator();
                    while (it.hasNext()) {
                        BaseWifi next = it.next();
                        if (!next.getIsTitle().booleanValue() && next.getSsid().toLowerCase().contains(str.toLowerCase())) {
                            arrayList2.add(next);
                        }
                    }
                    if (arrayList2.size() != 0) {
                        int indexOf = arrayList.indexOf(arrayList2.get(0));
                        NoneWifi noneWifi = new NoneWifi();
                        noneWifi.setSsid(str);
                        noneWifi.setIsGroup(true);
                        arrayList.add(indexOf, noneWifi);
                        linkedHashMap.put(str, arrayList2);
                        arrayList.removeAll(arrayList2);
                    }
                }
            }
            synchronized (this.f12802Q) {
                this.f12807f = null;
                this.f12808g = arrayList;
                this.f12799N = linkedHashMap;
                this.f12802Q.notifyDataSetChanged();
            }
            this.f12800O.removeMessages(21);
            this.f12800O.sendEmptyMessageDelayed(this.f12800O.obtainMessage(21).what, StatsManager.DEFAULT_TIMEOUT_MILLIS);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.gbcom.gwifi.functions.wifi.WiFiActivity$a */
    public class HandlerC3387a extends Handler {
        public HandlerC3387a(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 2:
                    BaseWifi baseWifi = (BaseWifi) ((Object[]) message.obj)[0];
                    String obj = ((Object[]) message.obj)[1].toString();
                    if (!(baseWifi instanceof NoneWifi)) {
                        if (baseWifi instanceof PskWifi) {
                            ((PskWifi) baseWifi).setPassword(obj);
                        } else if (baseWifi instanceof WepWifi) {
                            ((WepWifi) baseWifi).setPassword(obj);
                        } else if (baseWifi instanceof EapWifi) {
                            ((EapWifi) baseWifi).setPassword(obj);
                        }
                    }
                    WiFiService.m13974a().mo27585c(baseWifi);
                    return;
                default:
                    return;
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private boolean m13852a(BaseWifi baseWifi) {
        WifiInfo connectionInfo = this.f12809h.getConnectionInfo();
        if (!(connectionInfo == null || baseWifi.getSsid() == null || !WiFiUtil.m14022a(this).mo27621o())) {
            String ssid = connectionInfo.getSSID();
            if (ssid != null && ssid.startsWith("\"") && ssid.endsWith("\"")) {
                ssid = ssid.substring(1, ssid.length() - 1);
            }
            if (baseWifi.getSsid().equals(ssid)) {
                return true;
            }
        }
        return false;
    }

    public void dealWifiList(List<ScanResult> list) {
        boolean z;
        WifiInfo connectionInfo;
        ArrayList<BaseWifi> arrayList = new ArrayList<>();
        HashMap hashMap = new HashMap();
        if (list != null) {
            for (ScanResult scanResult : list) {
                if (!TextUtils.isEmpty(scanResult.SSID)) {
                    if (hashMap.containsKey(scanResult.SSID.toString())) {
                        BaseWifi baseWifi = (BaseWifi) hashMap.get(scanResult.SSID.toString());
                        if (WifiManager.calculateSignalLevel(scanResult.level, 4) > WifiManager.calculateSignalLevel(baseWifi.getLevel(), 4)) {
                            arrayList.remove(baseWifi);
                            hashMap.remove(scanResult.SSID.toString());
                            BaseWifi b = WiFiService.m13974a().mo27580b(scanResult);
                            hashMap.put(scanResult.SSID.toString(), b);
                            arrayList.add(b);
                        }
                    } else {
                        BaseWifi b2 = WiFiService.m13974a().mo27580b(scanResult);
                        hashMap.put(scanResult.SSID.toString(), b2);
                        arrayList.add(b2);
                    }
                }
            }
        }
        Iterator<BaseWifi> it = arrayList.iterator();
        while (true) {
            if (it.hasNext()) {
                if (m13852a(it.next())) {
                    z = true;
                    break;
                }
            } else {
                z = false;
                break;
            }
        }
        if (!z && (connectionInfo = this.f12809h.getConnectionInfo()) != null && connectionInfo.getSSID() != null && WiFiUtil.m14022a(this).mo27621o()) {
            String ssid = connectionInfo.getSSID();
            if (ssid.startsWith("\"") && ssid.endsWith("\"")) {
                ssid = ssid.substring(1, ssid.length() - 1);
            }
            if (!hashMap.containsKey(ssid)) {
                NoneWifi noneWifi = new NoneWifi();
                noneWifi.setSsid(ssid);
                noneWifi.setBssid(connectionInfo.getBSSID());
                noneWifi.setCapabilities("");
                noneWifi.setLevel(4);
                arrayList.add(0, noneWifi);
            }
        }
        updateWifiList(arrayList);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.gbcom.gwifi.functions.wifi.WiFiActivity$b */
    public class C3388b implements Comparator<BaseWifi> {
        C3388b() {
        }

        /* renamed from: a */
        public int compare(BaseWifi baseWifi, BaseWifi baseWifi2) {
            int i;
            int i2 = 0;
            if (baseWifi == null && baseWifi2 == null) {
                return 0;
            }
            if (baseWifi != null) {
                i = m13881a(baseWifi);
            } else {
                i = 0;
            }
            if (baseWifi2 != null) {
                i2 = m13881a(baseWifi2);
            }
            if (i != i2) {
                return i <= i2 ? 1 : -1;
            }
            if (baseWifi.getLevel() != baseWifi2.getLevel()) {
                return baseWifi.getLevel() <= baseWifi2.getLevel() ? 1 : -1;
            }
            if (!baseWifi.getSsid().toString().equals(baseWifi2.getSsid().toString())) {
                return baseWifi.getSsid().toString().hashCode() <= baseWifi2.getSsid().toString().hashCode() ? 1 : -1;
            }
            if (C3467s.m14513e(baseWifi.getBssid()) && C3467s.m14513e(baseWifi2.getBssid())) {
                return baseWifi.hashCode() <= baseWifi2.hashCode() ? 1 : -1;
            }
            if (C3467s.m14513e(baseWifi.getBssid())) {
                return 1;
            }
            return (C3467s.m14513e(baseWifi2.getBssid()) || baseWifi.getBssid().hashCode() > baseWifi2.getBssid().hashCode()) ? -1 : 1;
        }

        /* renamed from: a */
        private int m13881a(BaseWifi baseWifi) {
            int i = 1;
            if (NetworkUtils.m14430b(baseWifi.getBssid())) {
                i = 6;
            }
            if ((baseWifi.getSsid() != null && baseWifi.getSsid().toLowerCase(WiFiActivity.this.f12797L).contains("gwifi")) || baseWifi.getSsid().toLowerCase(WiFiActivity.this.f12797L).contains("giwifi")) {
                i += 3;
            }
            if ((baseWifi.getSsid() != null && baseWifi.getSsid().contains("cmcc")) || baseWifi.getSsid().contains("CMCC")) {
                i -= 10;
            }
            if (WiFiActivity.this.m13852a((WiFiActivity) baseWifi)) {
                return i + 11;
            }
            return i;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: f */
    private void m13863f() {
        this.f12809h.startScan();
    }

    /* renamed from: g */
    private void m13865g() {
        this.f12800O.removeMessages(22);
        this.f12800O.sendEmptyMessageDelayed(this.f12800O.obtainMessage(22).what, 500);
    }

    public void changeWifiState(WifiState fVar) {
        Log.m14398b(f12777b, "wifiState:" + fVar.toString());
        if (currentState == WifiState.TRY_CONNECT && fVar == WifiState.DISCONNECTED) {
            this.f12802Q.notifyDataSetChanged();
            return;
        }
        this.f12800O.removeMessages(8);
        if (fVar != WifiState.OPENED || currentState == WifiState.CLOSED || currentState == WifiState.CLOSING || currentState == WifiState.OPENING || currentState == WifiState.TRY_CONNECT) {
            currentState = fVar;
            switch (fVar) {
                case CLOSED:
                    dismissNormalDialog();
                    updateWifiSwitchView(false);
                    this.f12800O.removeMessages(9);
                    break;
                case CLOSING:
                    dismissNormalDialog();
                    break;
                case OPENED:
                    updateWifiSwitchView(true);
                    break;
                case TRY_CONNECT:
                    this.f12800O.removeMessages(8);
                    this.f12800O.sendEmptyMessageDelayed(8, AbstractTrafficShapingHandler.DEFAULT_MAX_TIME);
                    break;
                case SUCCESS:
                    GBActivity.dismissNormalDialog();
                    break;
                case AUTH_FAILED:
                    if (this.f12810i != null) {
                        if (WiFi.m13960a(this.f12810i.getCapabilities()).equals(WiFi.f13010d)) {
                            WifiConfiguration a = WiFi.m13957a(this.f12809h, this.f12810i, (String) null);
                            if (a != null) {
                                this.f12809h.removeNetwork(a.networkId);
                                this.f12809h.saveConfiguration();
                            }
                            if (this.f12786A < 1) {
                                GBActivity.showMessageToast("WIFI无法连接，请重新尝试");
                            } else {
                                this.f12786A++;
                                WiFiService.m13974a().mo27585c(this.f12810i);
                            }
                        } else {
                            WifiConfiguration a2 = WiFi.m13957a(this.f12809h, this.f12810i, (String) null);
                            if (a2 != null) {
                                this.f12809h.removeNetwork(a2.networkId);
                                this.f12809h.saveConfiguration();
                            }
                            m13849a(true);
                        }
                        currentState = WifiState.TRY_CONNECT;
                        this.f12802Q.notifyDataSetChanged();
                        break;
                    } else {
                        return;
                    }
            }
            this.f12802Q.notifyDataSetChanged();
        }
    }

    /* renamed from: a */
    private void m13849a(boolean z) {
        View inflate = getLayoutInflater().inflate(C2425R.layout.wifi_password_input, (ViewGroup) null);
        final EditText editText = (EditText) inflate.findViewById(C2425R.C2427id.wifi_password_et);
        final ImageView imageView = (ImageView) inflate.findViewById(C2425R.C2427id.eye_iv);
        imageView.setOnClickListener(new View.OnClickListener() {
            /* class com.gbcom.gwifi.functions.wifi.WiFiActivity.View$OnClickListenerC33869 */

            public void onClick(View view) {
                UIUtil.m14202a(view);
                WiFiActivity.this.f12791F = !WiFiActivity.this.f12791F;
                if (WiFiActivity.this.f12791F) {
                    editText.setInputType(145);
                } else {
                    editText.setInputType(129);
                }
                editText.setSelection(editText.getText().length());
                WiFiActivity.this.f12803a.removeMessages(1, imageView);
                WiFiActivity.this.f12803a.sendMessageDelayed(WiFiActivity.this.f12803a.obtainMessage(1, imageView), 300);
            }
        });
        if (z) {
            inflate.findViewById(2131755836).setVisibility(0);
        }
        showNormalDialog("输入" + this.f12810i.getSsid() + "密码", inflate, "加入", "取消", new GBActivity.AbstractC2517a() {
            /* class com.gbcom.gwifi.functions.wifi.WiFiActivity.C337710 */

            @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2517a
            public void onClick(Dialog dialog, View view) {
                String obj = editText.getText().toString();
                if (TextUtils.isEmpty(obj)) {
                    GBActivity.showMessageToast("请输入WiFi密码");
                } else if (obj.length() < 8) {
                    GBActivity.showMessageToast("密码至少8位");
                } else {
                    WiFiActivity.this.sendConnectWifi(WiFiActivity.this.f12810i, obj);
                    dialog.dismiss();
                }
            }
        }, (GBActivity.AbstractC2517a) null);
    }

    public void sendConnectWifi(BaseWifi baseWifi, String str) {
        this.f12816w.removeMessages(2);
        changeWifiState(WifiState.TRY_CONNECT);
        Log.m14400c(f12777b, "tryssid:" + baseWifi.getSsid());
        Message obtainMessage = this.f12816w.obtainMessage(2);
        obtainMessage.obj = new Object[]{baseWifi, str};
        this.f12816w.sendMessage(obtainMessage);
    }

    @Override // com.gbcom.gwifi.base.p233b.GBGlobalConfig.AbstractC2565a
    public void notifyAuth(boolean z) {
        if (z) {
            changeWifiState(WifiState.SUCCESS);
        }
    }

    public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long j) {
        BaseWifi baseWifi = this.f12808g.get(i);
        if (baseWifi.getIsTitle().booleanValue()) {
            return true;
        }
        if (baseWifi.getIsGroup().booleanValue()) {
            return false;
        }
        if (m13852a(baseWifi)) {
            return true;
        }
        this.f12810i = baseWifi;
        m13855b(this.f12810i);
        return true;
    }

    /* renamed from: b */
    private boolean m13855b(BaseWifi baseWifi) {
        if (!WiFi.m13960a(baseWifi.getCapabilities()).equals(WiFi.f13010d)) {
            WifiConfiguration a = WiFi.m13957a(this.f12809h, baseWifi, (String) null);
            if (a != null) {
                WiFiService.m13974a().mo27579b(a);
            } else {
                m13849a(false);
            }
        } else {
            sendConnectWifi(baseWifi, "");
        }
        return true;
    }

    public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i2, long j) {
        BaseWifi baseWifi = this.f12799N.get(this.f12808g.get(i).getSsid()).get(i2);
        if (m13852a(baseWifi)) {
            return false;
        }
        this.f12810i = baseWifi;
        return m13855b(this.f12810i);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m13846a(ImageView imageView, int i) {
        if (i == 3) {
            imageView.setImageResource(C2425R.C2426drawable.wifi_signal_04);
        } else if (i == 2) {
            imageView.setImageResource(C2425R.C2426drawable.wifi_signal_03);
        } else if (i == 1) {
            imageView.setImageResource(C2425R.C2426drawable.wifi_signal_02);
        } else {
            imageView.setImageResource(C2425R.C2426drawable.wifi_signal_01);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.gbcom.gwifi.functions.wifi.WiFiActivity$d */
    public static class C3390d {

        /* renamed from: a */
        LinearLayout f12840a;

        /* renamed from: b */
        TextView f12841b;

        /* renamed from: c */
        ImageView f12842c;

        /* renamed from: d */
        LinearLayout f12843d;

        /* renamed from: e */
        TextView f12844e;

        /* renamed from: f */
        ImageView f12845f;

        /* renamed from: g */
        ImageView f12846g;

        /* renamed from: h */
        ImageView f12847h;

        C3390d() {
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.gbcom.gwifi.functions.wifi.WiFiActivity$c */
    public static class C3389c {

        /* renamed from: a */
        TextView f12836a;

        /* renamed from: b */
        ImageView f12837b;

        /* renamed from: c */
        ImageView f12838c;

        /* renamed from: d */
        ImageView f12839d;

        C3389c() {
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.base.activity.GBActivity, android.support.p009v4.app.FragmentActivity
    public void onDestroy() {
        super.onDestroy();
        this.f12800O.removeMessages(9);
        unregisterReceiver(this.f12801P);
        unregisterReceiver(this.f12819z);
        this.f12816w.getLooper().quit();
        if (this.f12790E != null && this.f12790E.isRunning()) {
            this.f12790E.stop();
        }
        this.f12790E = null;
    }
}
