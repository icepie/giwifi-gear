package com.gbcom.gwifi.functions.wifi;

import android.app.Activity;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.StatsManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.net.wifi.ScanResult;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.hotspot2.pps.Credential;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.p009v4.app.ActivityCompat;
import android.support.p009v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import com.anthonycr.grant.PermissionsManager;
import com.anthonycr.grant.PermissionsResultAction;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.base.p233b.GBGlobalConfig;
import com.gbcom.gwifi.functions.wifi.entity.BaseWifi;
import com.gbcom.gwifi.functions.wifi.entity.EapWifi;
import com.gbcom.gwifi.functions.wifi.entity.NoneWifi;
import com.gbcom.gwifi.functions.wifi.entity.PskWifi;
import com.gbcom.gwifi.functions.wifi.entity.WepWifi;
import com.gbcom.gwifi.functions.wifi.entity.WiFiScanResult;
import com.gbcom.gwifi.util.C3467s;
import com.gbcom.gwifi.util.CheckUtil;
import com.gbcom.gwifi.util.JsonUtil;
import com.gbcom.gwifi.util.Log;
import com.gbcom.gwifi.util.SystemUtil;
import com.gbcom.gwifi.util.cache.CacheWiFi;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import p419io.netty.util.internal.StringUtil;

/* renamed from: com.gbcom.gwifi.functions.wifi.f */
public class WiFiService {

    /* renamed from: A */
    private static final int f13041A = 1;

    /* renamed from: B */
    private static final int f13042B = 2;

    /* renamed from: f */
    private static final String f13043f = "WiFiService";

    /* renamed from: g */
    private static WiFiService f13044g = null;

    /* renamed from: k */
    private static final String f13045k = "private_key";

    /* renamed from: l */
    private static final String f13046l = "phase2";

    /* renamed from: m */
    private static final String f13047m = "password";

    /* renamed from: n */
    private static final String f13048n = "identity";

    /* renamed from: o */
    private static final String f13049o = "eap";

    /* renamed from: p */
    private static final String f13050p = "client_cert";

    /* renamed from: q */
    private static final String f13051q = "ca_cert";

    /* renamed from: r */
    private static final String f13052r = "anonymous_identity";

    /* renamed from: t */
    private static final int f13053t = 99999;

    /* renamed from: C */
    private BroadcastReceiver f13054C = new BroadcastReceiver() {
        /* class com.gbcom.gwifi.functions.wifi.WiFiService.C34046 */

        public void onReceive(Context context, Intent intent) {
            if (WifiManager.SCAN_RESULTS_AVAILABLE_ACTION.equals(intent.getAction())) {
                List<ScanResult> scanResults = WiFiService.this.f13063h.getScanResults();
                Log.m14398b(WiFiService.f13043f, "获取Wi-Fi列表：-------->  wifi list size is " + scanResults.size());
                if (scanResults == null || scanResults.size() == 0) {
                }
                WiFiService.this.m13978a((WiFiService) ((ArrayList) scanResults));
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                if (scanResults != null && scanResults.size() > 0) {
                    int i = 0;
                    int i2 = 0;
                    int i3 = 0;
                    while (i < scanResults.size()) {
                        String str = scanResults.get(i).SSID;
                        String str2 = scanResults.get(i).BSSID;
                        int i4 = scanResults.get(i).level;
                        int i5 = scanResults.get(i).frequency;
                        String str3 = scanResults.get(i).capabilities;
                        if (str != null && !C3467s.m14513e(str) && str2 != null && !C3467s.m14513e(str2) && CheckUtil.m14084b(str2) && !str2.equals("00:00:00:00:00:00") && !str2.equals(WifiInfo.DEFAULT_MAC_ADDRESS)) {
                            if (WiFiUtil.m14036e(i5)) {
                                if (i3 < WiFiService.this.f13071y) {
                                    arrayList.add(new WiFiScanResult(str, str2, i4, WiFiUtil.m14029c(i5), str3));
                                    i3++;
                                    if (i3 >= WiFiService.this.f13071y) {
                                        if (i2 >= WiFiService.this.f13072z) {
                                            break;
                                        }
                                    }
                                } else if (i2 >= WiFiService.this.f13072z) {
                                    break;
                                }
                            }
                            if (WiFiUtil.m14037f(i5)) {
                                if (i2 < WiFiService.this.f13072z) {
                                    arrayList2.add(new WiFiScanResult(str, str2, i4, WiFiUtil.m14029c(i5), str3));
                                    i2++;
                                    if (i2 >= WiFiService.this.f13072z) {
                                        if (i3 >= WiFiService.this.f13071y) {
                                            break;
                                        }
                                    }
                                } else if (i3 >= WiFiService.this.f13071y) {
                                    break;
                                }
                            }
                            if (i3 >= WiFiService.this.f13071y && i2 >= WiFiService.this.f13072z) {
                                break;
                            }
                        }
                        i++;
                        i2 = i2;
                        i3 = i3;
                    }
                }
                HashMap hashMap = new HashMap();
                hashMap.put("timestamp", Long.valueOf(System.currentTimeMillis() / 1000));
                hashMap.put("wifi2gList", arrayList);
                hashMap.put("wifi5gList", arrayList2);
                String b = JsonUtil.m14390b(hashMap);
                Log.m14398b(WiFiService.f13043f, "获取Wi-Fi列表：-------->  " + b);
                CacheWiFi.getInstance().setScanWifiList(b);
            }
        }
    };

    /* renamed from: D */
    private Timer f13055D;

    /* renamed from: E */
    private boolean f13056E = false;

    /* renamed from: F */
    private C3406a f13057F;

    /* renamed from: a */
    final String f13058a = Credential.UserCredential.AUTH_METHOD_PAP;

    /* renamed from: b */
    final String f13059b = "ABC";

    /* renamed from: c */
    final String f13060c = "";

    /* renamed from: d */
    final String f13061d = "android.net.wifi.WifiConfiguration$EnterpriseField";

    /* renamed from: e */
    Handler f13062e = new Handler() {
        /* class com.gbcom.gwifi.functions.wifi.WiFiService.HandlerC34057 */

        public void handleMessage(Message message) {
            if (message.what == 1) {
                if (WiFiService.this.mo27578a(64, 64)) {
                    Log.m14398b(WiFiService.f13043f, "获取Wi-Fi列表：-------->  succeed");
                } else {
                    Log.m14398b(WiFiService.f13043f, "获取Wi-Fi列表：-------->  fail");
                }
            }
            super.handleMessage(message);
        }
    };

    /* renamed from: h */
    private WifiManager f13063h;

    /* renamed from: i */
    private Context f13064i;

    /* renamed from: j */
    private Map<String, WifiConfiguration> f13065j;

    /* renamed from: s */
    private Map<String, BaseWifi> f13066s = new HashMap();

    /* renamed from: u */
    private boolean f13067u = false;

    /* renamed from: v */
    private SupplicantState f13068v = null;

    /* renamed from: w */
    private int f13069w = 0;

    /* renamed from: x */
    private BaseWifi f13070x = null;

    /* renamed from: y */
    private int f13071y = 32;

    /* renamed from: z */
    private int f13072z = 32;

    private WiFiService(Context context) {
        this.f13064i = context;
        this.f13063h = (WifiManager) context.getApplicationContext().getSystemService("wifi");
    }

    /* renamed from: a */
    public static final WiFiService m13974a() {
        if (f13044g == null) {
            f13044g = new WiFiService(GBApplication.instance());
        }
        return f13044g;
    }

    /* renamed from: a */
    public static int m13972a(ScanResult scanResult) {
        return m13973a(scanResult.capabilities);
    }

    /* renamed from: a */
    public static int m13973a(String str) {
        if (str.contains(WiFi.f13009c)) {
            return 1;
        }
        if (str.contains("PSK")) {
            return 2;
        }
        if (str.contains("EAP")) {
            return 3;
        }
        return 0;
    }

    /* renamed from: a */
    public void mo27576a(BaseWifi baseWifi) {
        int i;
        List<ScanResult> scanResults = this.f13063h.getScanResults();
        int level = baseWifi.getLevel();
        ScanResult scanResult = null;
        if (scanResults != null) {
            for (ScanResult scanResult2 : scanResults) {
                if (scanResult2.SSID == null || !scanResult2.SSID.equals(baseWifi.getSsid()) || scanResult2.frequency < 5180 || scanResult2.frequency > 5825 || (scanResult2.level <= -75 && scanResult2.level <= level)) {
                    scanResult2 = scanResult;
                    i = level;
                } else {
                    i = scanResult2.level;
                }
                level = i;
                scanResult = scanResult2;
            }
        }
        if (scanResult != null) {
            if (this.f13066s.containsKey(baseWifi.getBssid())) {
                this.f13066s.remove(baseWifi.getBssid());
            }
            baseWifi.setSsid(scanResult.SSID);
            baseWifi.setBssid(scanResult.BSSID);
            baseWifi.setLevel(scanResult.level);
            baseWifi.setFrequency(scanResult.frequency);
            baseWifi.setCapabilities(scanResult.capabilities);
            this.f13066s.put(scanResult.BSSID, baseWifi);
        }
    }

    /* renamed from: b */
    public BaseWifi mo27581b(BaseWifi baseWifi) {
        ScanResult scanResult;
        int i;
        if (baseWifi == null) {
            return null;
        }
        List<ScanResult> scanResults = this.f13063h.getScanResults();
        if (scanResults != null) {
            scanResult = null;
            int i2 = -75;
            for (ScanResult scanResult2 : scanResults) {
                if (scanResult2.SSID == null || !scanResult2.SSID.equals(baseWifi.getSsid()) || scanResult2.frequency < 2401 || scanResult2.frequency > 2495 || (scanResult2.level <= -75 && scanResult2.level <= i2)) {
                    scanResult2 = scanResult;
                    i = i2;
                } else {
                    i = scanResult2.level;
                }
                i2 = i;
                scanResult = scanResult2;
            }
        } else {
            scanResult = null;
        }
        if (scanResult == null) {
            return null;
        }
        if (this.f13066s.containsKey(baseWifi.getBssid())) {
            this.f13066s.remove(baseWifi.getBssid());
        }
        baseWifi.setSsid(scanResult.SSID);
        baseWifi.setBssid(scanResult.BSSID);
        baseWifi.setLevel(scanResult.level);
        baseWifi.setFrequency(scanResult.frequency);
        baseWifi.setCapabilities(scanResult.capabilities);
        this.f13066s.put(scanResult.BSSID, baseWifi);
        return baseWifi;
    }

    /* renamed from: d */
    private void m13985d(BaseWifi baseWifi) {
        if (baseWifi != null) {
            this.f13069w = 0;
            this.f13068v = null;
            if (baseWifi.getFrequency() < 5180 || baseWifi.getFrequency() > 5825) {
                this.f13067u = false;
            } else {
                this.f13067u = true;
            }
        }
    }

    /* renamed from: a */
    public void mo27574a(SupplicantState supplicantState) {
        if (this.f13067u && supplicantState != null && supplicantState != SupplicantState.SCANNING) {
            if (this.f13068v == null) {
                this.f13068v = supplicantState;
            } else if (this.f13068v == SupplicantState.ASSOCIATED && supplicantState == SupplicantState.COMPLETED) {
                this.f13067u = false;
            } else if (this.f13068v != SupplicantState.ASSOCIATING || supplicantState != SupplicantState.DISCONNECTED) {
                this.f13068v = supplicantState;
            } else if (this.f13069w >= 1) {
                this.f13067u = false;
                this.f13069w = 0;
                m13986h();
            } else {
                this.f13069w++;
                this.f13068v = null;
            }
        }
    }

    /* renamed from: h */
    private void m13986h() {
        Log.m14400c(f13043f, "choose 2.4g...");
        BaseWifi b = mo27581b(this.f13070x);
        if (b != null) {
            Log.m14400c(f13043f, "choose 2.4g and connecting");
            int b2 = mo27579b(mo27567a(b, (Boolean) false));
            Log.m14400c(f13043f, "connect result:" + b.getSsid() + ":" + b2);
            if (b2 == -1) {
                mo27579b(mo27567a(b, (Boolean) true));
                Log.m14400c(f13043f, "connect result2:" + b.getSsid() + ":" + b2);
            }
        }
    }

    /* renamed from: b */
    public BaseWifi mo27580b(ScanResult scanResult) {
        BaseWifi baseWifi = null;
        if (scanResult != null) {
            if (this.f13066s.containsKey(scanResult.BSSID)) {
                baseWifi = this.f13066s.get(scanResult.BSSID);
            } else {
                switch (m13972a(scanResult)) {
                    case 0:
                        baseWifi = new NoneWifi();
                        break;
                    case 1:
                        baseWifi = new WepWifi();
                        break;
                    case 2:
                        baseWifi = new PskWifi();
                        break;
                    case 3:
                        baseWifi = new EapWifi();
                        break;
                }
                this.f13066s.put(scanResult.BSSID, baseWifi);
                baseWifi.setBssid(scanResult.BSSID);
            }
            baseWifi.setSsid(scanResult.SSID);
            baseWifi.setLevel(scanResult.level);
            baseWifi.setFrequency(scanResult.frequency);
            baseWifi.setCapabilities(scanResult.capabilities);
        }
        return baseWifi;
    }

    /* renamed from: a */
    public BaseWifi mo27572a(WiFiScanResult wiFiScanResult) {
        BaseWifi baseWifi = null;
        if (wiFiScanResult != null) {
            if (this.f13066s.containsKey(wiFiScanResult.BSSID)) {
                baseWifi = this.f13066s.get(wiFiScanResult.BSSID);
            } else {
                switch (m13973a(wiFiScanResult.capabilities)) {
                    case 0:
                        baseWifi = new NoneWifi();
                        break;
                    case 1:
                        baseWifi = new WepWifi();
                        break;
                    case 2:
                        baseWifi = new PskWifi();
                        break;
                    case 3:
                        baseWifi = new EapWifi();
                        break;
                }
                this.f13066s.put(wiFiScanResult.BSSID, baseWifi);
                baseWifi.setBssid(wiFiScanResult.BSSID);
            }
            baseWifi.setSsid(wiFiScanResult.SSID);
            baseWifi.setLevel(wiFiScanResult.level);
            baseWifi.setFrequency(WiFiUtil.m14032d(wiFiScanResult.channel));
            baseWifi.setCapabilities(wiFiScanResult.capabilities);
        }
        return baseWifi;
    }

    /* renamed from: b */
    public static String m13981b(String str) {
        return "\"" + str + "\"";
    }

    /* renamed from: a */
    public WifiConfiguration mo27567a(BaseWifi baseWifi, Boolean bool) {
        WifiConfiguration wifiConfiguration = null;
        if (baseWifi == null) {
            return null;
        }
        if (!bool.booleanValue() && (wifiConfiguration = WiFi.m13957a(this.f13063h, baseWifi, (String) null)) != null) {
            wifiConfiguration.BSSID = baseWifi.getBssid();
            return wifiConfiguration;
        } else if (baseWifi instanceof NoneWifi) {
            return mo27569a((NoneWifi) baseWifi);
        } else {
            if (baseWifi instanceof WepWifi) {
                return mo27571a((WepWifi) baseWifi);
            }
            if (baseWifi instanceof PskWifi) {
                return mo27570a((PskWifi) baseWifi);
            }
            if (baseWifi instanceof EapWifi) {
                return mo27568a((EapWifi) baseWifi);
            }
            return wifiConfiguration;
        }
    }

    /* renamed from: a */
    public WifiConfiguration mo27568a(EapWifi eapWifi) {
        Class<?> cls;
        boolean z;
        Field field;
        Field field2;
        Field field3;
        Field field4;
        if (eapWifi == null) {
            return null;
        }
        String ssid = eapWifi.getSsid();
        String password = eapWifi.getPassword();
        eapWifi.getEapMethod();
        eapWifi.getPhase2Method();
        String identity = eapWifi.getIdentity();
        eapWifi.getAnonymousIdentity();
        if (!TextUtils.isEmpty(ssid) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(identity)) {
            return null;
        }
        WifiConfiguration d = mo27588d(eapWifi.getBssid());
        if (d == null) {
            d = new WifiConfiguration();
            mo27577a(eapWifi.getBssid(), d);
            m13983c(d);
        }
        d.SSID = m13981b(ssid);
        d.allowedKeyManagement.clear();
        d.allowedKeyManagement.set(2);
        d.allowedKeyManagement.set(3);
        d.allowedGroupCiphers.clear();
        d.allowedPairwiseCiphers.set(2);
        d.allowedPairwiseCiphers.set(1);
        d.allowedProtocols.clear();
        d.allowedProtocols.set(1);
        d.allowedProtocols.set(0);
        if (Build.VERSION.SDK_INT >= 18) {
            return d;
        }
        try {
            Class<?>[] classes = WifiConfiguration.class.getClasses();
            int length = classes.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    cls = null;
                    break;
                }
                Class<?> cls2 = classes[i];
                if (cls2.getName().equals("android.net.wifi.WifiConfiguration$EnterpriseField")) {
                    cls = cls2;
                    break;
                }
                i++;
            }
            if (cls == null) {
                z = true;
            } else {
                z = false;
            }
            Field field5 = null;
            Field field6 = null;
            Field field7 = null;
            Field field8 = null;
            Field field9 = null;
            Field[] fields = WifiConfiguration.class.getFields();
            int length2 = fields.length;
            int i2 = 0;
            Field field10 = null;
            while (i2 < length2) {
                Field field11 = fields[i2];
                if (field11.getName().equals("anonymous_identity")) {
                    field = field8;
                    field2 = field7;
                    field3 = field6;
                    field4 = field11;
                    field11 = field9;
                } else if (field11.getName().equals("ca_cert")) {
                    field4 = field5;
                    field2 = field7;
                    field3 = field11;
                    field11 = field9;
                    field = field8;
                } else if (field11.getName().equals("client_cert")) {
                    field11 = field9;
                    field = field8;
                    field2 = field7;
                    field3 = field6;
                    field4 = field5;
                } else if (field11.getName().equals("eap")) {
                    field3 = field6;
                    field4 = field5;
                    field11 = field9;
                    field = field8;
                    field2 = field11;
                } else if (field11.getName().equals("identity")) {
                    field2 = field7;
                    field3 = field6;
                    field4 = field5;
                    field11 = field9;
                    field = field11;
                } else if (field11.getName().equals("password")) {
                    field = field8;
                    field2 = field7;
                    field3 = field6;
                    field4 = field5;
                } else if (field11.getName().equals("phase2")) {
                    field10 = field11;
                    field11 = field9;
                    field = field8;
                    field2 = field7;
                    field3 = field6;
                    field4 = field5;
                } else if (field11.getName().equals(f13045k)) {
                    field11 = field9;
                    field = field8;
                    field2 = field7;
                    field3 = field6;
                    field4 = field5;
                } else {
                    field11 = field9;
                    field = field8;
                    field2 = field7;
                    field3 = field6;
                    field4 = field5;
                }
                i2++;
                field5 = field4;
                field6 = field3;
                field7 = field2;
                field8 = field;
                field9 = field11;
            }
            Method method = null;
            if (!z) {
                Method[] methods = cls.getMethods();
                int length3 = methods.length;
                int i3 = 0;
                while (i3 < length3) {
                    Method method2 = methods[i3];
                    if (!method2.getName().trim().equals("setValue")) {
                        method2 = method;
                    }
                    i3++;
                    method = method2;
                }
            }
            if (!z) {
                method.invoke(field7.get(d), Integer.valueOf(eapWifi.getEapMethod()));
            }
            if (!z) {
                method.invoke(field10.get(d), Integer.valueOf(eapWifi.getPhase2Method()));
            }
            if (!z) {
                method.invoke(field5.get(d), eapWifi.getAnonymousIdentity());
            }
            if (!z) {
                method.invoke(field6.get(d), "");
            }
            if (!z) {
                method.invoke(field8.get(d), eapWifi.getSsid());
            }
            if (z) {
                return d;
            }
            method.invoke(field9.get(d), eapWifi.getPassword());
            return d;
        } catch (Exception e) {
            e.printStackTrace();
            return d;
        }
    }

    /* renamed from: a */
    public WifiConfiguration mo27569a(NoneWifi noneWifi) {
        if (noneWifi == null) {
            return null;
        }
        WifiConfiguration wifiConfiguration = new WifiConfiguration();
        m13983c(wifiConfiguration);
        mo27577a(noneWifi.getBssid(), wifiConfiguration);
        wifiConfiguration.SSID = m13981b(noneWifi.getSsid());
        wifiConfiguration.allowedKeyManagement.set(0);
        wifiConfiguration.BSSID = noneWifi.getBssid();
        return wifiConfiguration;
    }

    /* renamed from: a */
    public WifiConfiguration mo27570a(PskWifi pskWifi) {
        if (pskWifi == null) {
            return null;
        }
        WifiConfiguration d = mo27588d(pskWifi.getBssid());
        if (d == null) {
            d = new WifiConfiguration();
            m13983c(d);
            mo27577a(pskWifi.getBssid(), d);
        }
        d.SSID = m13981b(pskWifi.getSsid());
        d.allowedKeyManagement.set(1);
        String password = pskWifi.getPassword();
        if (TextUtils.isEmpty(password)) {
            return null;
        }
        if (password.matches("[0-9A-Fa-f]{64}")) {
            d.preSharedKey = password;
            return d;
        }
        d.preSharedKey = StringUtil.DOUBLE_QUOTE + password + StringUtil.DOUBLE_QUOTE;
        return d;
    }

    /* renamed from: a */
    public WifiConfiguration mo27571a(WepWifi wepWifi) {
        if (wepWifi == null) {
            return null;
        }
        WifiConfiguration d = mo27588d(wepWifi.getBssid());
        if (d == null) {
            d = new WifiConfiguration();
            m13983c(d);
            mo27577a(wepWifi.getBssid(), d);
        }
        d.SSID = m13981b(wepWifi.getSsid());
        d.allowedKeyManagement.set(0);
        d.allowedAuthAlgorithms.set(0);
        d.allowedAuthAlgorithms.set(1);
        String password = wepWifi.getPassword();
        if (TextUtils.isEmpty(password)) {
            return null;
        }
        int length = password.length();
        if ((length == 10 || length == 26 || length == 58) && password.matches("[0-9A-Fa-f]*")) {
            d.wepKeys[0] = password;
            return d;
        }
        d.wepKeys[0] = StringUtil.DOUBLE_QUOTE + password + StringUtil.DOUBLE_QUOTE;
        return d;
    }

    /* renamed from: a */
    public void mo27577a(String str, WifiConfiguration wifiConfiguration) {
        if (str != null && wifiConfiguration != null) {
            if (this.f13065j == null) {
                this.f13065j = new HashMap();
            }
            this.f13065j.put(str, wifiConfiguration);
        }
    }

    /* renamed from: c */
    public void mo27586c(String str) {
        if (str != null && this.f13065j != null) {
            this.f13065j.remove(str);
        }
    }

    /* renamed from: d */
    public WifiConfiguration mo27588d(String str) {
        if (str == null || this.f13065j == null || !this.f13065j.containsKey(str)) {
            return null;
        }
        return this.f13065j.get(str);
    }

    /* renamed from: a */
    public void mo27575a(WifiConfiguration wifiConfiguration) {
        int i = wifiConfiguration.priority;
        int i2 = m13987i() + 1;
        if (i2 > f13053t) {
            i2 = m13988j();
        }
        wifiConfiguration.priority = i2;
    }

    /* renamed from: i */
    private int m13987i() {
        List<WifiConfiguration> configuredNetworks = this.f13063h.getConfiguredNetworks();
        int i = 0;
        if (configuredNetworks == null) {
            return 0;
        }
        for (WifiConfiguration wifiConfiguration : configuredNetworks) {
            if (wifiConfiguration.priority > i) {
                i = wifiConfiguration.priority;
            } else {
                i = i;
            }
        }
        return i;
    }

    /* renamed from: j */
    private int m13988j() {
        List<WifiConfiguration> configuredNetworks = this.f13063h.getConfiguredNetworks();
        if (configuredNetworks == null) {
            return 0;
        }
        m13979a(configuredNetworks);
        int size = configuredNetworks.size();
        for (int i = 0; i < size; i++) {
            WifiConfiguration wifiConfiguration = configuredNetworks.get(i);
            wifiConfiguration.priority = i;
            this.f13063h.updateNetwork(wifiConfiguration);
        }
        this.f13063h.saveConfiguration();
        return size;
    }

    /* renamed from: a */
    private static void m13979a(List<WifiConfiguration> list) {
        Collections.sort(list, new Comparator<WifiConfiguration>() {
            /* class com.gbcom.gwifi.functions.wifi.WiFiService.C33991 */

            /* renamed from: a */
            public int compare(WifiConfiguration wifiConfiguration, WifiConfiguration wifiConfiguration2) {
                return wifiConfiguration.priority - wifiConfiguration2.priority;
            }
        });
    }

    /* renamed from: b */
    public int mo27579b(WifiConfiguration wifiConfiguration) {
        if (wifiConfiguration == null) {
            return -1;
        }
        wifiConfiguration.hiddenSSID = false;
        mo27575a(wifiConfiguration);
        Log.m14400c(f13043f, "configuration.status:" + wifiConfiguration.status);
        Log.m14400c(f13043f, "wifiConfiguration.networkId:" + wifiConfiguration.networkId);
        if (wifiConfiguration.networkId != -1) {
            this.f13063h.saveConfiguration();
            this.f13063h.enableNetwork(wifiConfiguration.networkId, true);
            Log.m14400c(f13043f, "wifiConfiguration.networkId result:" + this.f13063h.reconnect());
            return wifiConfiguration.networkId;
        }
        int addNetwork = this.f13063h.addNetwork(wifiConfiguration);
        if (addNetwork != -1) {
            this.f13063h.enableNetwork(addNetwork, true);
            this.f13063h.saveConfiguration();
            Log.m14400c(f13043f, "wifiConfiguration.networkId result:" + this.f13063h.reconnect());
        }
        wifiConfiguration.networkId = addNetwork;
        return addNetwork;
    }

    /* renamed from: c */
    public void mo27585c(BaseWifi baseWifi) {
        mo27576a(baseWifi);
        m13985d(baseWifi);
        this.f13070x = baseWifi;
        int b = mo27579b(mo27567a(baseWifi, (Boolean) false));
        Log.m14400c(f13043f, "connect result:" + baseWifi.getSsid() + ":" + b);
        if (b == -1) {
            Log.m14400c(f13043f, "connect result2:" + baseWifi.getSsid() + ":" + mo27579b(mo27567a(baseWifi, (Boolean) true)));
        }
    }

    /* renamed from: c */
    public static WifiConfiguration m13983c(WifiConfiguration wifiConfiguration) {
        wifiConfiguration.networkId = -1;
        wifiConfiguration.SSID = null;
        wifiConfiguration.BSSID = null;
        wifiConfiguration.priority = 0;
        wifiConfiguration.hiddenSSID = false;
        wifiConfiguration.allowedKeyManagement.clear();
        wifiConfiguration.allowedProtocols.clear();
        wifiConfiguration.allowedAuthAlgorithms.clear();
        wifiConfiguration.allowedPairwiseCiphers.clear();
        wifiConfiguration.allowedGroupCiphers.clear();
        wifiConfiguration.wepKeys = new String[4];
        for (int i = 0; i < wifiConfiguration.wepKeys.length; i++) {
            wifiConfiguration.wepKeys[i] = null;
        }
        return wifiConfiguration;
    }

    /* renamed from: a */
    public void mo27573a(Activity activity) {
        if (ContextCompat.checkSelfPermission(activity, "android.permission.ACCESS_COARSE_LOCATION") != 0) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, "android.permission.ACCESS_COARSE_LOCATION")) {
            }
            ActivityCompat.requestPermissions(activity, new String[]{"android.permission.ACCESS_COARSE_LOCATION"}, 1);
        }
        if (ContextCompat.checkSelfPermission(activity, "android.permission.ACCESS_FINE_LOCATION") != 0) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, "android.permission.ACCESS_FINE_LOCATION")) {
            }
            ActivityCompat.requestPermissions(activity, new String[]{"android.permission.ACCESS_FINE_LOCATION"}, 2);
        }
    }

    /* renamed from: b */
    public void mo27582b() {
        GBActivity currentActivity = GBApplication.instance().getCurrentActivity();
        if (currentActivity != null) {
            mo27573a((Activity) currentActivity);
        }
    }

    /* renamed from: c */
    public boolean mo27587c() {
        return this.f13063h.isWifiEnabled();
    }

    /* renamed from: d */
    public boolean mo27589d() {
        GBActivity currentActivity = GBApplication.instance().getCurrentActivity();
        if (currentActivity != null && ContextCompat.checkSelfPermission(currentActivity, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: k */
    private void m13989k() {
        GBActivity currentActivity = GBApplication.instance().getCurrentActivity();
        if (currentActivity != null) {
            currentActivity.startActivityForResult(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"), 0);
        }
    }

    /* renamed from: a */
    public static final void m13975a(Context context) {
        Intent intent = new Intent();
        intent.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
        intent.addCategory("android.intent.category.ALTERNATIVE");
        intent.setData(Uri.parse("custom:3"));
        try {
            PendingIntent.getBroadcast(context, 0, intent, 0).send();
        } catch (PendingIntent.CanceledException e) {
            e.printStackTrace();
        }
        Log.m14403e(f13043f, "帮助用户强制打开GPS");
    }

    /* renamed from: b */
    public void mo27583b(Context context) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        context.registerReceiver(this.f13054C, intentFilter);
        Log.m14398b(f13043f, "获取Wi-Fi列表：-------->  注册接收器");
        WiFiUtil.m14021a().mo27611f(context);
    }

    /* renamed from: c */
    public void mo27584c(Context context) {
        context.unregisterReceiver(this.f13054C);
        Log.m14398b(f13043f, "获取Wi-Fi列表：-------->  取消注册接收器");
        WiFiUtil.m14021a().mo27613g(context);
    }

    /* renamed from: a */
    public boolean mo27578a(int i, int i2) {
        GBActivity currentActivity;
        Log.m14398b(f13043f, "获取Wi-Fi列表：-------->  开始");
        mo27582b();
        if (!this.f13063h.isWifiEnabled()) {
            this.f13063h.setWifiEnabled(true);
        }
        if (!SystemUtil.m14536i(GBApplication.instance()) && (currentActivity = GBApplication.instance().getCurrentActivity()) != null) {
            currentActivity.showSecondDialog("", "开启位置服务,获取精准定位", "开启定位", new GBActivity.AbstractC2517a() {
                /* class com.gbcom.gwifi.functions.wifi.WiFiService.C34002 */

                @Override // com.gbcom.gwifi.base.activity.GBActivity.AbstractC2517a
                public void onClick(Dialog dialog, View view) {
                    dialog.dismiss();
                    WiFiService.this.m13989k();
                }
            }, new DialogInterface.OnCancelListener() {
                /* class com.gbcom.gwifi.functions.wifi.WiFiService.DialogInterface$OnCancelListenerC34013 */

                public void onCancel(DialogInterface dialogInterface) {
                    dialogInterface.dismiss();
                }
            });
        }
        this.f13071y = i;
        this.f13072z = i2;
        return this.f13063h.startScan();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private void m13978a(ArrayList<ScanResult> arrayList) {
        Collections.sort(arrayList, new Comparator<ScanResult>() {
            /* class com.gbcom.gwifi.functions.wifi.WiFiService.C34024 */

            /* renamed from: a */
            public int compare(ScanResult scanResult, ScanResult scanResult2) {
                return scanResult2.level - scanResult.level;
            }
        });
    }

    /* renamed from: e */
    public void mo27590e() {
        final GBActivity currentActivity = GBApplication.instance().getCurrentActivity();
        if (currentActivity != null) {
            String[] strArr = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"};
            final String str = "在设置-应用-" + SystemUtil.m14528d() + "-权限中开启位置权限，以正常使用功能";
            if (GBActivity.checkPermissions(currentActivity, strArr)) {
                return;
            }
            if (ActivityCompat.shouldShowRequestPermissionRationale(currentActivity, "android.permission.ACCESS_FINE_LOCATION")) {
                GBGlobalConfig.m10510c().mo24391a((Activity) currentActivity, str);
            } else {
                PermissionsManager.m4670a().mo21377a(currentActivity, strArr, new PermissionsResultAction() {
                    /* class com.gbcom.gwifi.functions.wifi.WiFiService.C34035 */

                    @Override // com.anthonycr.grant.PermissionsResultAction
                    public void onGranted() {
                    }

                    @Override // com.anthonycr.grant.PermissionsResultAction
                    public void onDenied(String str) {
                        GBGlobalConfig.m10510c().mo24391a((Activity) currentActivity, str);
                    }
                });
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.gbcom.gwifi.functions.wifi.f$a */
    /* compiled from: WiFiService */
    public class C3406a extends TimerTask {
        C3406a() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            Message message = new Message();
            message.what = 1;
            WiFiService.this.f13062e.sendMessage(message);
        }
    }

    /* renamed from: f */
    public void mo27591f() {
        if (this.f13056E) {
            this.f13057F.cancel();
            this.f13057F = null;
            this.f13055D.cancel();
            this.f13055D.purge();
            this.f13055D = null;
            this.f13056E = false;
        }
        this.f13057F = new C3406a();
        this.f13055D = new Timer();
        this.f13055D.schedule(this.f13057F, StatsManager.DEFAULT_TIMEOUT_MILLIS, 35000);
        this.f13056E = true;
    }

    /* renamed from: g */
    public void mo27592g() {
        if (this.f13056E) {
            this.f13057F.cancel();
            this.f13057F = null;
            this.f13055D.cancel();
            this.f13055D.purge();
            this.f13055D = null;
            this.f13056E = false;
        }
    }
}
