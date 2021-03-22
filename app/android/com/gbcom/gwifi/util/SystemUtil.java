package com.gbcom.gwifi.util;

import android.app.ActivityManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Process;
import android.provider.Settings;
import android.support.p009v4.app.ActivityCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.WindowManager;
import com.alipay.sdk.p116j.C1536i;
import com.gbcom.gwifi.base.app.GBApplication;
import com.tencent.map.geoloclite.tsa.TencentLiteLocation;
import com.umeng.message.MsgConstant;
import com.umeng.message.proguard.MessageStore;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import java.net.NetworkInterface;
import java.p456io.BufferedReader;
import java.p456io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/* renamed from: com.gbcom.gwifi.util.t */
public class SystemUtil {

    /* renamed from: a */
    public static String f13483a = "SystemUtil";

    /* renamed from: a */
    public static int m14519a(Context context) {
        return ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getWidth();
    }

    /* renamed from: b */
    public static int m14523b(Context context) {
        return ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getHeight();
    }

    /* renamed from: c */
    public static String m14527c(Context context) {
        return m14529d(context);
    }

    /* renamed from: d */
    public static String m14529d(Context context) {
        String str;
        String str2 = "35" + (Build.BOARD.length() % 10) + (Build.BRAND.length() % 10) + (Build.CPU_ABI.length() % 10) + (Build.DEVICE.length() % 10) + (Build.DISPLAY.length() % 10) + (Build.HOST.length() % 10) + (Build.ID.length() % 10) + (Build.MANUFACTURER.length() % 10) + (Build.MODEL.length() % 10) + (Build.PRODUCT.length() % 10) + (Build.TAGS.length() % 10) + (Build.TYPE.length() % 10) + (Build.USER.length() % 10);
        try {
            if (Build.VERSION.SDK_INT < 26) {
                str = Build.SERIAL;
            } else if (ActivityCompat.checkSelfPermission(context, MsgConstant.PERMISSION_READ_PHONE_STATE) != 0) {
                str = "serial";
            } else {
                str = Build.getSerial();
            }
            return new UUID((long) str2.hashCode(), (long) str.hashCode()).toString();
        } catch (Exception e) {
            return new UUID((long) str2.hashCode(), (long) "serial".hashCode()).toString();
        }
    }

    /* renamed from: e */
    public static String m14530e(Context context) {
        return "";
    }

    /* renamed from: f */
    public static String m14532f(Context context) {
        try {
            String string = Settings.Secure.getString(context.getContentResolver(), SocializeProtocolConstants.PROTOCOL_KEY_ANDROID_ID);
            if (string == null) {
                return "";
            }
            return string;
        } catch (Exception e) {
            return "";
        }
    }

    /* renamed from: g */
    public static String m14534g(Context context) {
        int myPid = Process.myPid();
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) {
            if (runningAppProcessInfo.pid == myPid) {
                return runningAppProcessInfo.processName;
            }
        }
        return null;
    }

    /* renamed from: a */
    public static boolean m14522a(Context context, String str) {
        if (str == null || "".equals(str)) {
            return false;
        }
        try {
            context.getPackageManager().getApplicationInfo(str, 8192);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /* renamed from: h */
    public static boolean m14535h(Context context) {
        return (context.getResources().getConfiguration().screenLayout & 15) >= 3;
    }

    /* renamed from: i */
    public static final boolean m14536i(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService("location");
        boolean isProviderEnabled = locationManager.isProviderEnabled("gps");
        boolean isProviderEnabled2 = locationManager.isProviderEnabled(TencentLiteLocation.NETWORK_PROVIDER);
        if (isProviderEnabled || isProviderEnabled2) {
            return true;
        }
        return false;
    }

    /* renamed from: j */
    public static final void m14537j(Context context) {
        Intent intent = new Intent();
        intent.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
        intent.addCategory("android.intent.category.ALTERNATIVE");
        intent.setData(Uri.parse("custom:3"));
        try {
            PendingIntent.getBroadcast(context, 0, intent, 0).send();
        } catch (PendingIntent.CanceledException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: k */
    public static List<String> m14538k(Context context) {
        List<PackageInfo> installedPackages = context.getPackageManager().getInstalledPackages(8192);
        ArrayList arrayList = new ArrayList();
        new StringBuilder();
        for (PackageInfo packageInfo : installedPackages) {
            String charSequence = packageInfo.applicationInfo.loadLabel(context.getPackageManager()).toString();
            String str = packageInfo.packageName;
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(charSequence) && !str.startsWith("com.oppo") && !str.startsWith("com.android") && !str.startsWith("com.meizu") && !str.startsWith("com.samsung") && !str.startsWith("com.sec.android") && !str.startsWith("com.huawei") && !str.startsWith("com.oppo") && !str.startsWith("com.xiaomi") && !charSequence.startsWith("com.")) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    public static boolean m14521a() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            if (networkInterfaces != null) {
                Iterator it = Collections.list(networkInterfaces).iterator();
                while (it.hasNext()) {
                    NetworkInterface networkInterface = (NetworkInterface) it.next();
                    if (networkInterface.isUp() && networkInterface.getInterfaceAddresses().size() != 0) {
                        if ("tun0".equals(networkInterface.getName()) || "ppp0".equals(networkInterface.getName())) {
                            return true;
                        }
                    }
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return false;
    }

    /* renamed from: l */
    public static String m14539l(Context context) {
        if (m14535h(context)) {
            return Constant.f13324j;
        }
        return Constant.f13323i;
    }

    /* renamed from: b */
    public static String m14524b() {
        return Build.MANUFACTURER + "," + Build.MODEL + "," + Build.VERSION.SDK + "," + Build.VERSION.RELEASE;
    }

    /* renamed from: c */
    public static String m14526c() {
        return "(GiWiFi;Android" + Build.VERSION.RELEASE + C1536i.f3510b + Build.MANUFACTURER + C1536i.f3510b + Build.MODEL + MessageStore.f23536t;
    }

    /* renamed from: d */
    public static String m14528d() {
        String str;
        try {
            Context applicationContext = GBApplication.instance().getApplicationContext();
            str = applicationContext.getResources().getString(applicationContext.getPackageManager().getPackageInfo(applicationContext.getPackageName(), 0).applicationInfo.labelRes);
        } catch (Exception e) {
            e.printStackTrace();
            str = null;
        }
        if (str == null || C3467s.m14513e(str)) {
            return "GiWiFi助手";
        }
        return str;
    }

    /* renamed from: m */
    public static synchronized String m14540m(Context context) {
        String str;
        synchronized (SystemUtil.class) {
            try {
                str = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).packageName;
                Log.d("", "packageName=" + str);
            } catch (Exception e) {
                e.printStackTrace();
                str = null;
            }
        }
        return str;
    }

    /* renamed from: b */
    public static synchronized boolean m14525b(Context context, String str) {
        boolean z;
        synchronized (SystemUtil.class) {
            List<PackageInfo> installedPackages = context.getPackageManager().getInstalledPackages(0);
            int i = 0;
            while (true) {
                if (i >= installedPackages.size()) {
                    z = false;
                    break;
                } else if (installedPackages.get(i).packageName.equalsIgnoreCase(str)) {
                    z = true;
                    break;
                } else {
                    i++;
                }
            }
        }
        return z;
    }

    /* renamed from: e */
    public static boolean m14531e() {
        if (GBApplication.instance().getPackageName().contains("school")) {
            return true;
        }
        return false;
    }

    /* renamed from: f */
    public static boolean m14533f() {
        if (GBApplication.instance().getPackageName().contains(".school.")) {
            return true;
        }
        return false;
    }

    /* renamed from: a */
    public static String m14520a(String str) {
        Throwable th;
        String str2;
        String str3 = "";
        String str4 = " ";
        try {
            Process exec = Runtime.getRuntime().exec(str);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exec.getErrorStream()));
            BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(exec.getInputStream()));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                str3 = (str3 + readLine) + "/n";
            }
            if (str3 != "") {
                Log.i(f13483a, " the str error message " + str3.toString());
            }
            while (true) {
                try {
                    str2 = str4;
                    str4 = bufferedReader2.readLine();
                    if (str4 == null) {
                        break;
                    }
                    str3 = (str3 + str4) + "/n";
                } catch (Throwable th2) {
                    th = th2;
                    th.printStackTrace();
                    return str2.toString();
                }
            }
            if (str3 != "") {
                Log.i(f13483a, " the standard output message " + str2.toString());
            }
        } catch (Throwable th3) {
            str2 = str4;
            th = th3;
            th.printStackTrace();
            return str2.toString();
        }
        return str2.toString();
    }
}
