package com.gbcom.gwifi.functions.wifi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.DhcpInfo;
import android.net.LinkProperties;
import android.net.MacAddress;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiNetworkSpecifier;
import android.net.wifi.WifiNetworkSuggestion;
import android.os.Build;
import android.os.PatternMatcher;
import android.text.format.Formatter;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.functions.wifi.entity.BaseWifi;
import com.gbcom.gwifi.util.C3467s;
import com.gbcom.gwifi.util.Log;
import com.umeng.message.MsgConstant;
import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.p456io.BufferedReader;
import java.p456io.FileReader;
import java.p456io.InputStreamReader;
import java.p456io.LineNumberReader;
import java.p456io.Reader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import org.apache.xalan.templates.Constants;

/* renamed from: com.gbcom.gwifi.functions.wifi.g */
public class WiFiUtil {

    /* renamed from: c */
    public static final String[] f13082c = {"2G网络", "3G网络", "4G网络", "3G/4G网络"};

    /* renamed from: e */
    private static final String f13083e = "WiFiUtil";

    /* renamed from: k */
    private static WiFiUtil f13084k;

    /* renamed from: a */
    public List<ScanResult> f13085a = new ArrayList();

    /* renamed from: b */
    WifiManager.WifiLock f13086b;

    /* renamed from: d */
    BroadcastReceiver f13087d = new BroadcastReceiver() {
        /* class com.gbcom.gwifi.functions.wifi.WiFiUtil.C34071 */

        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(WifiManager.ACTION_WIFI_NETWORK_SUGGESTION_POST_CONNECTION)) {
                Log.m14398b(WiFiUtil.f13083e, "recv ACTION_WIFI_NETWORK_SUGGESTION_POST_CONNECTION");
            }
        }
    };

    /* renamed from: f */
    private WifiManager f13088f;

    /* renamed from: g */
    private List<WifiConfiguration> f13089g;

    /* renamed from: h */
    private ConnectivityManager f13090h;

    /* renamed from: i */
    private WifiInfo f13091i;

    /* renamed from: j */
    private WifiManager.MulticastLock f13092j;

    /* renamed from: l */
    private Context f13093l;

    /* renamed from: a */
    public static synchronized WiFiUtil m14022a(Context context) {
        WiFiUtil gVar;
        synchronized (WiFiUtil.class) {
            if (f13084k == null) {
                f13084k = new WiFiUtil(context);
            }
            gVar = f13084k;
        }
        return gVar;
    }

    /* renamed from: a */
    public static final synchronized WiFiUtil m14021a() {
        WiFiUtil gVar;
        synchronized (WiFiUtil.class) {
            if (f13084k == null) {
                f13084k = new WiFiUtil(GBApplication.instance());
            }
            gVar = f13084k;
        }
        return gVar;
    }

    private WiFiUtil(Context context) {
        this.f13088f = (WifiManager) context.getApplicationContext().getSystemService("wifi");
        this.f13090h = (ConnectivityManager) context.getSystemService("connectivity");
        this.f13093l = context;
    }

    /* renamed from: b */
    public void mo27604b() {
        if (this.f13088f.isWifiEnabled()) {
            this.f13088f.setWifiEnabled(false);
        }
        this.f13088f.setWifiEnabled(true);
    }

    /* renamed from: c */
    public void mo27607c() {
        if (this.f13088f.isWifiEnabled()) {
            this.f13088f.setWifiEnabled(false);
        }
    }

    /* renamed from: d */
    public WifiInfo mo27608d() {
        return this.f13088f.getConnectionInfo();
    }

    /* renamed from: e */
    public void mo27609e() {
        this.f13086b.acquire();
    }

    /* renamed from: f */
    public void mo27610f() {
        if (this.f13086b.isHeld()) {
            this.f13086b.acquire();
        }
    }

    /* renamed from: a */
    public void mo27603a(String str) {
        this.f13086b = this.f13088f.createWifiLock(str);
    }

    /* renamed from: g */
    public List<WifiConfiguration> mo27612g() {
        return this.f13089g;
    }

    /* renamed from: a */
    public void mo27601a(int i) {
        if (i <= this.f13089g.size()) {
            this.f13088f.enableNetwork(this.f13089g.get(i).networkId, true);
        }
    }

    /* renamed from: h */
    public String mo27614h() {
        if (this.f13088f.getConnectionInfo() != null) {
            String ssid = this.f13088f.getConnectionInfo().getSSID();
            if (!C3467s.m14513e(ssid)) {
                if (!ssid.startsWith("\"") || !ssid.endsWith("\"")) {
                    return ssid;
                }
                return ssid.substring(1, ssid.length() - 1);
            }
        }
        return "";
    }

    /* renamed from: i */
    public String mo27615i() {
        String bssid;
        if (this.f13088f.getConnectionInfo() == null || (bssid = this.f13088f.getConnectionInfo().getBSSID()) == null) {
            return "";
        }
        return bssid.toUpperCase();
    }

    /* renamed from: j */
    public String mo27616j() {
        int ipAddress = this.f13088f.getConnectionInfo() == null ? 0 : this.f13088f.getConnectionInfo().getIpAddress();
        return (ipAddress & 255) + Constants.ATTRVAL_THIS + ((ipAddress >> 8) & 255) + Constants.ATTRVAL_THIS + ((ipAddress >> 16) & 255) + Constants.ATTRVAL_THIS + ((ipAddress >> 24) & 255);
    }

    /* renamed from: k */
    public String mo27617k() {
        String str = "";
        if (this.f13088f.getDhcpInfo() != null) {
            str = Formatter.formatIpAddress(this.f13088f.getDhcpInfo().gateway);
        }
        if (C3467s.m14513e(str) || "0.0.0.0".equals(str)) {
            return "down.gwifi.com.cn";
        }
        return str;
    }

    /* renamed from: l */
    public int mo27618l() {
        if (this.f13088f.getConnectionInfo() == null) {
            return 0;
        }
        return this.f13088f.getConnectionInfo().getNetworkId();
    }

    /* renamed from: m */
    public WifiInfo mo27619m() {
        return this.f13088f.getConnectionInfo();
    }

    /* renamed from: n */
    public boolean mo27620n() {
        NetworkInfo activeNetworkInfo = this.f13090h.getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            return false;
        }
        return activeNetworkInfo.isAvailable();
    }

    /* renamed from: o */
    public boolean mo27621o() {
        NetworkInfo activeNetworkInfo = this.f13090h.getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected() || activeNetworkInfo.getType() != 1) {
            return false;
        }
        return activeNetworkInfo.isAvailable();
    }

    /* renamed from: p */
    public boolean mo27622p() {
        NetworkInfo activeNetworkInfo = this.f13090h.getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected() || activeNetworkInfo.getType() != 0) {
            return false;
        }
        return activeNetworkInfo.isAvailable();
    }

    /* renamed from: a */
    public int mo27598a(WifiConfiguration wifiConfiguration) {
        return this.f13088f.addNetwork(wifiConfiguration);
    }

    /* renamed from: b */
    public void mo27605b(int i) {
        this.f13088f.disableNetwork(i);
        this.f13088f.disconnect();
    }

    /* renamed from: a */
    public WifiConfiguration mo27600a(String str, String str2, String str3, int i) {
        WifiConfiguration wifiConfiguration = new WifiConfiguration();
        wifiConfiguration.allowedAuthAlgorithms.clear();
        wifiConfiguration.allowedGroupCiphers.clear();
        wifiConfiguration.allowedKeyManagement.clear();
        wifiConfiguration.allowedPairwiseCiphers.clear();
        wifiConfiguration.allowedProtocols.clear();
        wifiConfiguration.SSID = "\"" + str + "\"";
        wifiConfiguration.BSSID = str2;
        WifiConfiguration c = m14030c(str);
        if (c != null) {
            this.f13088f.removeNetwork(c.networkId);
        }
        if (i == 1) {
            wifiConfiguration.allowedAuthAlgorithms.set(0);
            wifiConfiguration.allowedAuthAlgorithms.set(1);
            wifiConfiguration.allowedKeyManagement.set(0);
            wifiConfiguration.wepTxKeyIndex = 0;
        }
        if (i == 2) {
            wifiConfiguration.wepKeys[0] = "\"" + str3 + "\"";
            wifiConfiguration.allowedAuthAlgorithms.set(1);
            wifiConfiguration.allowedGroupCiphers.set(3);
            wifiConfiguration.allowedGroupCiphers.set(2);
            wifiConfiguration.allowedGroupCiphers.set(0);
            wifiConfiguration.allowedGroupCiphers.set(1);
            wifiConfiguration.allowedKeyManagement.set(0);
            wifiConfiguration.wepTxKeyIndex = 0;
        }
        if (i == 3) {
            wifiConfiguration.preSharedKey = "\"" + str3 + "\"";
            wifiConfiguration.allowedAuthAlgorithms.set(0);
            wifiConfiguration.allowedGroupCiphers.set(2);
            wifiConfiguration.allowedKeyManagement.set(1);
            wifiConfiguration.allowedPairwiseCiphers.set(1);
            wifiConfiguration.allowedGroupCiphers.set(3);
            wifiConfiguration.allowedPairwiseCiphers.set(2);
            wifiConfiguration.status = 2;
        }
        return wifiConfiguration;
    }

    /* renamed from: c */
    private WifiConfiguration m14030c(String str) {
        for (WifiConfiguration wifiConfiguration : this.f13088f.getConfiguredNetworks()) {
            if (wifiConfiguration.SSID.equals("\"" + str + "\"")) {
                return wifiConfiguration;
            }
        }
        return null;
    }

    /* renamed from: q */
    public void mo27623q() {
        if (this.f13092j == null) {
            this.f13092j = this.f13088f.createMulticastLock("multicast.device");
            this.f13092j.acquire();
        }
    }

    /* renamed from: r */
    public void mo27624r() {
        if (this.f13092j != null) {
            this.f13092j.release();
        }
    }

    /* renamed from: b */
    public void mo27606b(WifiConfiguration wifiConfiguration) {
        try {
            Field declaredField = WifiConfiguration.class.getDeclaredField("mWifiApProfile");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(wifiConfiguration);
            declaredField.setAccessible(false);
            if (obj != null) {
                Field declaredField2 = obj.getClass().getDeclaredField("SSID");
                declaredField2.setAccessible(true);
                declaredField2.set(obj, wifiConfiguration.SSID);
                declaredField2.setAccessible(false);
                Field declaredField3 = obj.getClass().getDeclaredField("key");
                declaredField3.setAccessible(true);
                declaredField3.set(obj, wifiConfiguration.preSharedKey);
                declaredField3.setAccessible(false);
                Field declaredField4 = obj.getClass().getDeclaredField("dhcpEnable");
                declaredField4.setAccessible(true);
                declaredField4.setInt(obj, 1);
                declaredField4.setAccessible(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: s */
    public WifiManager mo27625s() {
        return this.f13088f;
    }

    /* renamed from: a */
    public void mo27602a(ScanResult scanResult, String str, boolean z) {
        int a;
        List<WifiConfiguration> configuredNetworks = this.f13088f.getConfiguredNetworks();
        Iterator<WifiConfiguration> it = configuredNetworks.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            WifiConfiguration next = it.next();
            if (scanResult.BSSID.equals(next.BSSID)) {
                int i = next.networkId;
                break;
            }
        }
        if (scanResult.capabilities.toUpperCase().contains(WiFi.f13008b)) {
            a = mo27598a(mo27600a(scanResult.SSID, scanResult.BSSID, str, 3));
        } else if (scanResult.capabilities.toUpperCase().contains(WiFi.f13009c)) {
            a = mo27598a(mo27600a(scanResult.SSID, scanResult.BSSID, str, 2));
        } else {
            a = mo27598a(mo27600a(scanResult.SSID, scanResult.BSSID, str, 1));
        }
        WifiInfo connectionInfo = this.f13088f.getConnectionInfo();
        if (connectionInfo == null || connectionInfo.getNetworkId() != a || z) {
            WifiConfiguration wifiConfiguration = null;
            for (WifiConfiguration wifiConfiguration2 : configuredNetworks) {
                if (wifiConfiguration != null && wifiConfiguration2.priority <= wifiConfiguration.priority) {
                    wifiConfiguration2 = wifiConfiguration;
                }
                wifiConfiguration = wifiConfiguration2;
            }
            if (wifiConfiguration != null) {
                if (wifiConfiguration.priority >= 1000000) {
                    for (WifiConfiguration wifiConfiguration3 : configuredNetworks) {
                        WifiConfiguration wifiConfiguration4 = new WifiConfiguration();
                        wifiConfiguration4.networkId = wifiConfiguration3.networkId;
                        wifiConfiguration4.priority /= 2;
                        this.f13088f.updateNetwork(wifiConfiguration4);
                    }
                }
                WifiConfiguration wifiConfiguration5 = new WifiConfiguration();
                wifiConfiguration5.networkId = a;
                wifiConfiguration5.priority = wifiConfiguration.priority + 1;
                this.f13088f.updateNetwork(wifiConfiguration5);
                this.f13088f.enableNetwork(a, true);
                this.f13088f.saveConfiguration();
                this.f13088f.disconnect();
                this.f13088f.reconnect();
            }
        }
    }

    /* renamed from: a */
    public static boolean m14026a(ScanResult scanResult) {
        if (scanResult.capabilities.toUpperCase().contains(WiFi.f13008b) || scanResult.capabilities.toUpperCase().contains(WiFi.f13009c)) {
            return true;
        }
        return false;
    }

    /* renamed from: b */
    public static int m14027b(Context context) {
        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService("wifi");
        WifiInfo connectionInfo = wifiManager.getConnectionInfo();
        for (ScanResult scanResult : wifiManager.getScanResults()) {
            if (scanResult.BSSID.equalsIgnoreCase(connectionInfo.getBSSID()) && scanResult.SSID.equalsIgnoreCase(connectionInfo.getSSID().substring(1, connectionInfo.getSSID().length() - 1))) {
                return m14029c(scanResult.frequency);
            }
        }
        return -1;
    }

    /* renamed from: c */
    public static int m14029c(int i) {
        switch (i) {
            case ScanResult.BAND_24_GHZ_START_FREQ_MHZ:
                return 1;
            case 2417:
                return 2;
            case 2422:
                return 3;
            case 2427:
                return 4;
            case 2432:
                return 5;
            case 2437:
                return 6;
            case 2442:
                return 7;
            case 2447:
                return 8;
            case 2452:
                return 9;
            case 2457:
                return 10;
            case 2462:
                return 11;
            case 2467:
                return 12;
            case 2472:
                return 13;
            case ScanResult.BAND_24_GHZ_END_FREQ_MHZ:
                return 14;
            case 5745:
                return 149;
            case 5765:
                return 153;
            case 5785:
                return 157;
            case 5805:
                return 161;
            case 5825:
                return 165;
            default:
                return -1;
        }
    }

    /* renamed from: d */
    public static int m14032d(int i) {
        switch (i) {
            case 1:
                return ScanResult.BAND_24_GHZ_START_FREQ_MHZ;
            case 2:
                return 2417;
            case 3:
                return 2422;
            case 4:
                return 2427;
            case 5:
                return 2432;
            case 6:
                return 2437;
            case 7:
                return 2442;
            case 8:
                return 2447;
            case 9:
                return 2452;
            case 10:
                return 2457;
            case 11:
                return 2462;
            case 12:
                return 2467;
            case 13:
                return 2472;
            case 14:
                return ScanResult.BAND_24_GHZ_END_FREQ_MHZ;
            case 149:
                return 5745;
            case 153:
                return 5765;
            case 157:
                return 5785;
            case 161:
                return 5805;
            case 165:
                return 5825;
            default:
                return -1;
        }
    }

    /* renamed from: e */
    public static boolean m14036e(int i) {
        return i > 2400 && i < 2500;
    }

    /* renamed from: f */
    public static boolean m14037f(int i) {
        return i > 4900 && i < 5900;
    }

    /* renamed from: t */
    public String mo27626t() {
        NetworkInfo activeNetworkInfo = this.f13090h.getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            return "";
        }
        if (activeNetworkInfo.getType() == 1) {
            return "WIFI";
        }
        if (activeNetworkInfo.getType() != 0) {
            return "";
        }
        String subtypeName = activeNetworkInfo.getSubtypeName();
        switch (activeNetworkInfo.getSubtype()) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
                return f13082c[0];
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
                return f13082c[1];
            case 13:
                return f13082c[2];
            default:
                if (subtypeName.equalsIgnoreCase("TD-SCDMA") || subtypeName.equalsIgnoreCase("WCDMA") || subtypeName.equalsIgnoreCase("CDMA2000")) {
                    return f13082c[1];
                }
                return f13082c[3];
        }
    }

    /* renamed from: b */
    public static boolean m14028b(String str) {
        return str.equals(f13082c[0]) || str.equals(f13082c[1]) || str.equals(f13082c[2]) || str.equals(f13082c[3]);
    }

    /* renamed from: u */
    public String mo27627u() {
        DhcpInfo dhcpInfo = this.f13088f.getDhcpInfo();
        if (dhcpInfo == null || dhcpInfo.leaseDuration != 0) {
            return "DHCP";
        }
        return "静态";
    }

    /* renamed from: v */
    public String mo27628v() {
        DhcpInfo dhcpInfo = this.f13088f.getDhcpInfo();
        if (dhcpInfo != null) {
            return Formatter.formatIpAddress(dhcpInfo.dns1);
        }
        return "";
    }

    /* renamed from: w */
    public String mo27629w() {
        DhcpInfo dhcpInfo = this.f13088f.getDhcpInfo();
        if (dhcpInfo != null) {
            return Formatter.formatIpAddress(dhcpInfo.dns2);
        }
        return "";
    }

    /* renamed from: c */
    public static String m14031c(Context context) {
        return ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getMacAddress();
    }

    /* renamed from: d */
    public static String m14033d(Context context) {
        if (Build.VERSION.SDK_INT < 23) {
            String h = m14038h(context);
            if (!C3467s.m14513e(h)) {
                return h;
            }
        }
        String str = "";
        String str2 = "";
        try {
            LineNumberReader lineNumberReader = new LineNumberReader(new InputStreamReader(Runtime.getRuntime().exec("cat /sys/class/net/wlan0/address").getInputStream()));
            while (true) {
                if (str != null) {
                    str = lineNumberReader.readLine();
                    if (str != null) {
                        str2 = str.trim();
                        break;
                    }
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            Log.m14400c("----->NetInfoManager", "getMacAddress:" + e.toString());
        }
        if (str2 != null && !"".equals(str2)) {
            return str2;
        }
        try {
            return m14034d("/sys/class/net/eth0/address").toUpperCase().substring(0, 17);
        } catch (Exception e2) {
            e2.printStackTrace();
            Log.m14400c("----->NetInfoManager", "getMacAddress:" + e2.toString());
            return str2;
        }
    }

    /* renamed from: h */
    private static String m14038h(Context context) {
        if (m14039i(context)) {
            try {
                return ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getMacAddress();
            } catch (Exception e) {
                Log.m14400c("----->NetInfoManager", "getMacAddress0:" + e.toString());
            }
        }
        return "";
    }

    /* renamed from: i */
    private static boolean m14039i(Context context) {
        if (context.checkCallingOrSelfPermission(MsgConstant.PERMISSION_ACCESS_WIFI_STATE) != 0) {
            return false;
        }
        Log.m14400c("----->NetInfoManager", "isAccessWifiStateAuthorized:access wifi state is enabled");
        return true;
    }

    /* renamed from: d */
    private static String m14034d(String str) throws Exception {
        FileReader fileReader = new FileReader(str);
        String a = m14023a(fileReader);
        fileReader.close();
        return a;
    }

    /* renamed from: a */
    private static String m14023a(Reader reader) throws Exception {
        StringBuilder sb = new StringBuilder();
        char[] cArr = new char[4096];
        int read = reader.read(cArr);
        while (read >= 0) {
            sb.append(cArr, 0, read);
            read = reader.read(cArr);
        }
        return sb.toString();
    }

    /* renamed from: x */
    public static String m14040x() {
        try {
            byte[] hardwareAddress = NetworkInterface.getByInetAddress(m14018A()).getHardwareAddress();
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < hardwareAddress.length; i++) {
                if (i != 0) {
                    stringBuffer.append(':');
                }
                String hexString = Integer.toHexString(hardwareAddress[i] & 255);
                if (hexString.length() == 1) {
                    hexString = 0 + hexString;
                }
                stringBuffer.append(hexString);
            }
            return stringBuffer.toString().toUpperCase();
        } catch (Exception e) {
            return null;
        }
    }

    /* renamed from: A */
    private static InetAddress m14018A() {
        SocketException e;
        InetAddress inetAddress;
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress inetAddress2 = null;
            while (networkInterfaces.hasMoreElements()) {
                try {
                    Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                    while (true) {
                        if (!inetAddresses.hasMoreElements()) {
                            inetAddress = inetAddress2;
                            break;
                        }
                        inetAddress = inetAddresses.nextElement();
                        try {
                            if (!inetAddress.isLoopbackAddress() && inetAddress.getHostAddress().indexOf(":") == -1) {
                                break;
                            }
                            inetAddress2 = null;
                        } catch (SocketException e2) {
                            e = e2;
                            e.printStackTrace();
                            return inetAddress;
                        }
                    }
                    if (inetAddress != null) {
                        return inetAddress;
                    }
                    inetAddress2 = inetAddress;
                } catch (SocketException e3) {
                    inetAddress = inetAddress2;
                    e = e3;
                    e.printStackTrace();
                    return inetAddress;
                }
            }
            return inetAddress2;
        } catch (SocketException e4) {
            e = e4;
            inetAddress = null;
            e.printStackTrace();
            return inetAddress;
        }
    }

    /* renamed from: B */
    private static String m14019B() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                while (true) {
                    if (inetAddresses.hasMoreElements()) {
                        InetAddress nextElement = inetAddresses.nextElement();
                        if (!nextElement.isLoopbackAddress()) {
                            return nextElement.getHostAddress().toString();
                        }
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: CFG modification limit reached, blocks count: 122
        	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.processBlocksTree(BlockProcessor.java:72)
        	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.visit(BlockProcessor.java:46)
        */
    /* renamed from: y */
    public static java.lang.String m14041y() {
        /*
            r0 = 0
            java.util.Enumeration r1 = java.net.NetworkInterface.getNetworkInterfaces()     // Catch:{ SocketException -> 0x0009 }
            r2 = r1
        L_0x0006:
            if (r2 != 0) goto L_0x002e
        L_0x0008:
            return r0
        L_0x0009:
            r1 = move-exception
            r1.printStackTrace()
            r2 = r0
            goto L_0x0006
        L_0x000f:
            r1 = r0
        L_0x0010:
            boolean r0 = r2.hasMoreElements()
            if (r0 == 0) goto L_0x002c
            java.lang.Object r0 = r2.nextElement()
            java.net.NetworkInterface r0 = (java.net.NetworkInterface) r0
            byte[] r0 = r0.getHardwareAddress()     // Catch:{ SocketException -> 0x0027 }
            java.lang.String r0 = m14025a(r0)     // Catch:{ SocketException -> 0x0027 }
            if (r0 == 0) goto L_0x000f
            goto L_0x0008
        L_0x0027:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0010
        L_0x002c:
            r0 = r1
            goto L_0x0008
        L_0x002e:
            r1 = r0
            goto L_0x0010
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gbcom.gwifi.functions.wifi.WiFiUtil.m14041y():java.lang.String");
    }

    /* renamed from: a */
    private static String m14025a(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        int length = bArr.length;
        for (int i = 0; i < length; i++) {
            sb.append(String.format("%02X:", Byte.valueOf(bArr[i])));
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    /* renamed from: z */
    public static String m14042z() {
        String a = m14024a("busybox ifconfig", "HWaddr");
        if (a == null) {
            return "网络异常";
        }
        if (a.length() <= 0 || !a.contains("HWaddr")) {
            return a;
        }
        return a.substring(a.indexOf("HWaddr") + 6, a.length() - 1);
    }

    /* renamed from: a */
    private static String m14024a(String str, String str2) {
        String str3 = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec(str).getInputStream()));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null || readLine.contains(str2)) {
                    return readLine;
                }
                str3 = str3 + readLine;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return str3;
        }
    }

    /* renamed from: e */
    public static String m14035e(Context context) {
        if (Build.VERSION.SDK_INT < 23) {
            Log.m14400c("=====", "6.0以下");
            return m14031c(context);
        } else if (Build.VERSION.SDK_INT < 24 && Build.VERSION.SDK_INT >= 23) {
            Log.m14400c("=====", "6.0以上7.0以下");
            return m14033d(context);
        } else if (Build.VERSION.SDK_INT < 24) {
            return WifiInfo.DEFAULT_MAC_ADDRESS;
        } else {
            Log.m14400c("=====", "7.0以上");
            String x = m14040x();
            if (!C3467s.m14513e(x)) {
                Log.m14400c("=====", "7.0以上1");
                return x;
            }
            Log.m14400c("=====", "7.0以上3");
            return m14042z();
        }
    }

    /* renamed from: a */
    public int mo27599a(BaseWifi baseWifi) {
        if (Build.VERSION.SDK_INT < 29) {
            return 0;
        }
        int removeNetworkSuggestions = this.f13088f.removeNetworkSuggestions(new ArrayList());
        if (removeNetworkSuggestions != 0) {
            Log.m14398b(f13083e, "removeNetworkSuggestions fail " + removeNetworkSuggestions);
        } else {
            Log.m14398b(f13083e, "removeNetworkSuggestions succeed");
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new WifiNetworkSuggestion.Builder().setSsid(baseWifi.getSsid()).build());
        int addNetworkSuggestions = this.f13088f.addNetworkSuggestions(arrayList);
        if (addNetworkSuggestions != 0) {
            Log.m14398b(f13083e, "addNetworkSuggestions fail " + addNetworkSuggestions);
        } else {
            Log.m14398b(f13083e, "addNetworkSuggestions succeed " + baseWifi.getSsid());
        }
        C34082 r0 = new ConnectivityManager.NetworkCallback() {
            /* class com.gbcom.gwifi.functions.wifi.WiFiUtil.C34082 */

            public void onAvailable(Network network) {
                Log.m14398b(WiFiUtil.f13083e, "OnAvailable " + network.toString());
                if (Build.VERSION.SDK_INT >= 29) {
                    WiFiUtil.this.f13090h.unregisterNetworkCallback(this);
                }
            }

            public void onLosing(Network network, int i) {
                super.onLosing(network, i);
                Log.m14398b(WiFiUtil.f13083e, "onLosing " + network.toString() + ", MaxMsToLive=" + i);
            }

            public void onLost(Network network) {
                super.onLost(network);
                Log.m14398b(WiFiUtil.f13083e, "onLost " + network.toString());
                if (Build.VERSION.SDK_INT >= 29) {
                    WiFiUtil.this.f13090h.unregisterNetworkCallback(this);
                }
            }

            public void onUnavailable() {
                super.onUnavailable();
                Log.m14398b(WiFiUtil.f13083e, "onUnavailable");
                if (Build.VERSION.SDK_INT >= 29) {
                    WiFiUtil.this.f13090h.unregisterNetworkCallback(this);
                }
            }

            public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
                Log.m14398b(WiFiUtil.f13083e, "onCapabilitiesChanged " + network.toString() + ", networkCapabilities=" + ((Object) networkCapabilities));
                NetworkInfo networkInfo = WiFiUtil.this.f13090h.getNetworkInfo(1);
                if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
                    Log.m14398b(WiFiUtil.f13083e, "onCapabilitiesChanged isConnectedOrConnecting");
                }
            }

            public void onLinkPropertiesChanged(Network network, LinkProperties linkProperties) {
                Log.m14398b(WiFiUtil.f13083e, "onLinkPropertiesChanged " + network.toString() + ", linkProperties=" + ((Object) linkProperties));
                NetworkInfo networkInfo = WiFiUtil.this.f13090h.getNetworkInfo(1);
                if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
                    Log.m14398b(WiFiUtil.f13083e, "onLinkPropertiesChanged isConnectedOrConnecting");
                }
            }

            public void onBlockedStatusChanged(Network network, boolean z) {
                Log.m14398b(WiFiUtil.f13083e, "onBlockedStatusChanged " + network.toString() + ", blocked=" + z);
                NetworkInfo networkInfo = WiFiUtil.this.f13090h.getNetworkInfo(1);
                if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
                    Log.m14398b(WiFiUtil.f13083e, "onBlockedStatusChanged isConnectedOrConnecting");
                }
            }
        };
        this.f13090h.requestNetwork(new NetworkRequest.Builder().addTransportType(1).addCapability(12).setNetworkSpecifier(new WifiNetworkSpecifier.Builder().setSsidPattern(new PatternMatcher(baseWifi.getSsid(), 1)).setBssidPattern(MacAddress.fromString(baseWifi.getBssid()), MacAddress.fromString("00:00:00:ff:ff:ff")).build()).build(), r0);
        return 0;
    }

    /* renamed from: f */
    public void mo27611f(Context context) {
        context.registerReceiver(this.f13087d, new IntentFilter(WifiManager.ACTION_WIFI_NETWORK_SUGGESTION_POST_CONNECTION));
    }

    /* renamed from: g */
    public void mo27613g(Context context) {
        context.unregisterReceiver(this.f13087d);
    }
}
