package com.gbcom.gwifi.functions.wifi;

import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import android.util.Log;
import com.gbcom.gwifi.functions.wifi.entity.BaseWifi;
import com.gbcom.gwifi.util.C3467s;
import com.gbcom.gwifi.util.CheckUtil;
import java.util.BitSet;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* renamed from: com.gbcom.gwifi.functions.wifi.d */
public class WiFi {

    /* renamed from: a */
    public static final String f13007a = "WPA2";

    /* renamed from: b */
    public static final String f13008b = "WPA";

    /* renamed from: c */
    public static final String f13009c = "WEP";

    /* renamed from: d */
    public static final String f13010d = "Open";

    /* renamed from: e */
    public static final String f13011e = "WPA-EAP";

    /* renamed from: f */
    public static final String f13012f = "IEEE8021X";

    /* renamed from: g */
    public static final String[] f13013g = {"PEAP", "TLS", "TTLS"};

    /* renamed from: h */
    public static final int f13014h = 0;

    /* renamed from: i */
    public static final int f13015i = 1;

    /* renamed from: j */
    public static final int f13016j = 2;

    /* renamed from: k */
    static final String[] f13017k = {f13009c, f13008b, f13007a, f13011e, f13012f};

    /* renamed from: l */
    private static final String f13018l = "Wifi Connecter";

    /* renamed from: m */
    private static final int f13019m = 99999;

    /* renamed from: a */
    public static boolean m13965a(WifiManager wifiManager, WifiConfiguration wifiConfiguration, String str, int i) {
        m13961a(wifiConfiguration, m13959a(wifiConfiguration), str);
        if (wifiManager.updateNetwork(wifiConfiguration) == -1) {
            return false;
        }
        return m13966a(wifiManager, wifiConfiguration, true);
    }

    /* renamed from: a */
    public static boolean m13964a(WifiManager wifiManager, ScanResult scanResult, String str) {
        WifiConfiguration a;
        String a2 = m13958a(scanResult);
        if (a2.equals(f13010d)) {
            m13963a(wifiManager, 10);
        }
        WifiConfiguration wifiConfiguration = new WifiConfiguration();
        wifiConfiguration.SSID = C3467s.m14503a(scanResult.SSID);
        wifiConfiguration.BSSID = scanResult.BSSID;
        m13961a(wifiConfiguration, a2, str);
        if (wifiManager.addNetwork(wifiConfiguration) == -1 || !wifiManager.saveConfiguration() || (a = m13956a(wifiManager, wifiConfiguration, a2)) == null) {
            return false;
        }
        return m13966a(wifiManager, a, true);
    }

    /* renamed from: a */
    public static boolean m13966a(WifiManager wifiManager, WifiConfiguration wifiConfiguration, boolean z) {
        String a = m13959a(wifiConfiguration);
        int i = wifiConfiguration.priority;
        int b = m13967b(wifiManager) + 1;
        if (b > f13019m) {
            b = m13955a(wifiManager);
            wifiConfiguration = m13956a(wifiManager, wifiConfiguration, a);
            if (wifiConfiguration == null) {
                return false;
            }
        }
        wifiConfiguration.priority = b;
        int updateNetwork = wifiManager.updateNetwork(wifiConfiguration);
        if (updateNetwork == -1) {
            return false;
        }
        if (!wifiManager.enableNetwork(updateNetwork, false)) {
            wifiConfiguration.priority = i;
            return false;
        } else if (!wifiManager.saveConfiguration()) {
            wifiConfiguration.priority = i;
            return false;
        } else {
            WifiConfiguration a2 = m13956a(wifiManager, wifiConfiguration, a);
            if (a2 == null) {
                return false;
            }
            if (!wifiManager.enableNetwork(a2.networkId, true)) {
                return false;
            }
            return z ? wifiManager.reassociate() : wifiManager.reconnect();
        }
    }

    /* renamed from: a */
    private static void m13962a(List<WifiConfiguration> list) {
        Collections.sort(list, new Comparator<WifiConfiguration>() {
            /* class com.gbcom.gwifi.functions.wifi.WiFi.C33981 */

            /* renamed from: a */
            public int compare(WifiConfiguration wifiConfiguration, WifiConfiguration wifiConfiguration2) {
                return wifiConfiguration.priority - wifiConfiguration2.priority;
            }
        });
    }

    /* renamed from: a */
    private static boolean m13963a(WifiManager wifiManager, int i) {
        int i2;
        boolean z;
        int i3 = 0;
        List<WifiConfiguration> configuredNetworks = wifiManager.getConfiguredNetworks();
        m13962a(configuredNetworks);
        int size = configuredNetworks.size() - 1;
        boolean z2 = false;
        while (size >= 0) {
            WifiConfiguration wifiConfiguration = configuredNetworks.get(size);
            if (!m13959a(wifiConfiguration).equals(f13010d) || (i3 = i3 + 1) < i) {
                i2 = i3;
                z = z2;
            } else {
                wifiManager.removeNetwork(wifiConfiguration.networkId);
                i2 = i3;
                z = true;
            }
            size--;
            z2 = z;
            i3 = i2;
        }
        if (z2) {
            return wifiManager.saveConfiguration();
        }
        return true;
    }

    /* renamed from: a */
    private static int m13955a(WifiManager wifiManager) {
        List<WifiConfiguration> configuredNetworks = wifiManager.getConfiguredNetworks();
        m13962a(configuredNetworks);
        int size = configuredNetworks.size();
        for (int i = 0; i < size; i++) {
            WifiConfiguration wifiConfiguration = configuredNetworks.get(i);
            wifiConfiguration.priority = i;
            wifiManager.updateNetwork(wifiConfiguration);
        }
        wifiManager.saveConfiguration();
        return size;
    }

    /* renamed from: b */
    private static int m13967b(WifiManager wifiManager) {
        int i = 0;
        for (WifiConfiguration wifiConfiguration : wifiManager.getConfiguredNetworks()) {
            if (wifiConfiguration.priority > i) {
                i = wifiConfiguration.priority;
            } else {
                i = i;
            }
        }
        return i;
    }

    /* renamed from: b */
    public static WifiConfiguration m13968b(WifiManager wifiManager, ScanResult scanResult, String str) {
        String a = C3467s.m14503a(scanResult.SSID);
        if (a.length() == 0) {
            return null;
        }
        String str2 = scanResult.BSSID;
        if (str2 == null) {
            return null;
        }
        if (str == null) {
            str = m13958a(scanResult);
        }
        List<WifiConfiguration> configuredNetworks = wifiManager.getConfiguredNetworks();
        if (configuredNetworks == null) {
            return null;
        }
        for (WifiConfiguration wifiConfiguration : configuredNetworks) {
            if (wifiConfiguration.SSID != null && a.equals(wifiConfiguration.SSID)) {
                if ((wifiConfiguration.BSSID == null || str2.equals(wifiConfiguration.BSSID)) && str.equals(m13959a(wifiConfiguration))) {
                    return wifiConfiguration;
                }
            }
        }
        return null;
    }

    /* renamed from: a */
    public static WifiConfiguration m13957a(WifiManager wifiManager, BaseWifi baseWifi, String str) {
        String a = C3467s.m14503a(baseWifi.getSsid());
        if (a.length() == 0) {
            return null;
        }
        String bssid = baseWifi.getBssid();
        if (bssid == null) {
            return null;
        }
        if (str == null) {
            str = m13960a(baseWifi.getCapabilities());
        }
        List<WifiConfiguration> configuredNetworks = wifiManager.getConfiguredNetworks();
        if (configuredNetworks == null) {
            return null;
        }
        for (WifiConfiguration wifiConfiguration : configuredNetworks) {
            if (wifiConfiguration.SSID != null && a.equals(wifiConfiguration.SSID)) {
                if ((wifiConfiguration.BSSID == null || !CheckUtil.m14084b(wifiConfiguration.BSSID) || bssid.equals(wifiConfiguration.BSSID)) && str.equals(m13959a(wifiConfiguration))) {
                    return wifiConfiguration;
                }
            }
        }
        return null;
    }

    /* renamed from: a */
    public static WifiConfiguration m13956a(WifiManager wifiManager, WifiConfiguration wifiConfiguration, String str) {
        String str2 = wifiConfiguration.SSID;
        if (str2.length() == 0) {
            return null;
        }
        String str3 = wifiConfiguration.BSSID;
        if (str == null) {
            str = m13959a(wifiConfiguration);
        }
        for (WifiConfiguration wifiConfiguration2 : wifiManager.getConfiguredNetworks()) {
            if (wifiConfiguration2.SSID != null && str2.equals(wifiConfiguration2.SSID)) {
                if ((wifiConfiguration2.BSSID == null || str3 == null || str3.equals(wifiConfiguration2.BSSID)) && str.equals(m13959a(wifiConfiguration2))) {
                    return wifiConfiguration2;
                }
            }
        }
        return null;
    }

    /* renamed from: a */
    public static String m13959a(WifiConfiguration wifiConfiguration) {
        if (wifiConfiguration.allowedKeyManagement.get(0)) {
            if (wifiConfiguration.allowedGroupCiphers.get(3) || (!wifiConfiguration.allowedGroupCiphers.get(0) && !wifiConfiguration.allowedGroupCiphers.get(1))) {
                return f13010d;
            }
            return f13009c;
        } else if (wifiConfiguration.allowedProtocols.get(1)) {
            return f13007a;
        } else {
            if (wifiConfiguration.allowedKeyManagement.get(2)) {
                return f13011e;
            }
            if (wifiConfiguration.allowedKeyManagement.get(3)) {
                return f13012f;
            }
            if (wifiConfiguration.allowedProtocols.get(0)) {
                return f13008b;
            }
            Log.w(f13018l, "Unknown security type from WifiConfiguration, falling back on open.");
            return f13010d;
        }
    }

    /* renamed from: a */
    private static void m13961a(WifiConfiguration wifiConfiguration, String str, String str2) {
        int i = 0;
        wifiConfiguration.allowedAuthAlgorithms.clear();
        wifiConfiguration.allowedGroupCiphers.clear();
        wifiConfiguration.allowedKeyManagement.clear();
        wifiConfiguration.allowedPairwiseCiphers.clear();
        wifiConfiguration.allowedProtocols.clear();
        wifiConfiguration.wepKeys = new String[4];
        for (int i2 = 0; i2 < wifiConfiguration.wepKeys.length; i2++) {
            wifiConfiguration.wepKeys[i2] = null;
        }
        if (TextUtils.isEmpty(str)) {
            str = f13010d;
            Log.w(f13018l, "Empty security, assuming open");
        }
        if (str.equals(f13009c)) {
            if (!TextUtils.isEmpty(str2)) {
                if (m13969b(str2)) {
                    wifiConfiguration.wepKeys[0] = str2;
                } else {
                    wifiConfiguration.wepKeys[0] = C3467s.m14503a(str2);
                }
            }
            wifiConfiguration.wepTxKeyIndex = 0;
            wifiConfiguration.allowedAuthAlgorithms.set(0);
            wifiConfiguration.allowedAuthAlgorithms.set(1);
            wifiConfiguration.allowedKeyManagement.set(0);
            wifiConfiguration.allowedGroupCiphers.set(0);
            wifiConfiguration.allowedGroupCiphers.set(1);
        } else if (str.equals(f13008b) || str.equals(f13007a)) {
            wifiConfiguration.allowedGroupCiphers.set(2);
            wifiConfiguration.allowedGroupCiphers.set(3);
            wifiConfiguration.allowedKeyManagement.set(1);
            wifiConfiguration.allowedPairwiseCiphers.set(2);
            wifiConfiguration.allowedPairwiseCiphers.set(1);
            BitSet bitSet = wifiConfiguration.allowedProtocols;
            if (str.equals(f13007a)) {
                i = 1;
            }
            bitSet.set(i);
            if (TextUtils.isEmpty(str2)) {
                return;
            }
            if (str2.length() != 64 || !m13970c(str2)) {
                wifiConfiguration.preSharedKey = C3467s.m14503a(str2);
            } else {
                wifiConfiguration.preSharedKey = str2;
            }
        } else if (str.equals(f13010d)) {
            wifiConfiguration.allowedKeyManagement.set(0);
        } else if (str.equals(f13011e) || str.equals(f13012f)) {
            wifiConfiguration.allowedGroupCiphers.set(2);
            wifiConfiguration.allowedGroupCiphers.set(3);
            if (str.equals(f13011e)) {
                wifiConfiguration.allowedKeyManagement.set(2);
            } else {
                wifiConfiguration.allowedKeyManagement.set(3);
            }
            if (!TextUtils.isEmpty(str2)) {
                wifiConfiguration.preSharedKey = C3467s.m14503a(str2);
            }
        }
    }

    /* renamed from: b */
    private static boolean m13969b(String str) {
        int length = str.length();
        if (length == 10 || length == 26 || length == 58) {
            return m13970c(str);
        }
        return false;
    }

    /* renamed from: c */
    private static boolean m13970c(String str) {
        for (int length = str.length() - 1; length >= 0; length--) {
            char charAt = str.charAt(length);
            if ((charAt < '0' || charAt > '9') && ((charAt < 'A' || charAt > 'F') && (charAt < 'a' || charAt > 'f'))) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: a */
    public static String m13958a(ScanResult scanResult) {
        String str = scanResult.capabilities;
        for (int length = f13017k.length - 1; length >= 0; length--) {
            if (str.contains(f13017k[length])) {
                return f13017k[length];
            }
        }
        return f13010d;
    }

    /* renamed from: a */
    public static String m13960a(String str) {
        for (int length = f13017k.length - 1; length >= 0; length--) {
            if (str.contains(f13017k[length])) {
                return f13017k[length];
            }
        }
        return f13010d;
    }
}
