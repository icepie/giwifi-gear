package com.gbcom.gwifi.util;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.support.p009v4.content.FileProvider;
import android.text.format.Formatter;
import com.gbcom.gwifi.base.app.GBApplication;
import java.net.HttpURLConnection;
import java.net.URL;
import java.p456io.File;
import java.p456io.FileInputStream;
import java.p456io.FileNotFoundException;
import java.p456io.FileOutputStream;
import java.p456io.IOException;
import java.p456io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.xpath.compiler.PsuedoNames;

/* renamed from: com.gbcom.gwifi.util.q */
public class StorageUtils {

    /* renamed from: a */
    public static final String f13479a = "/sdcard/Download";

    /* renamed from: b */
    private static final String f13480b = "StorageUtils";

    /* renamed from: c */
    private static final long f13481c = 10485760;

    /* renamed from: d */
    private static List<String> f13482d = null;

    /* renamed from: a */
    public static boolean m14484a() {
        if (Environment.getExternalStorageState().equals("mounted")) {
            return true;
        }
        return false;
    }

    /* renamed from: b */
    public static long m14485b() {
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().toString());
            return m14477a(statFs) * m14489c(statFs);
        } catch (RuntimeException e) {
            return 0;
        }
    }

    /* renamed from: c */
    public static boolean m14491c() {
        if (m14485b() < f13481c) {
            return false;
        }
        return true;
    }

    /* renamed from: d */
    public static boolean m14495d() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    /* renamed from: e */
    public static boolean m14496e() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    /* renamed from: f */
    public static void m14497f() throws IOException {
        File file = new File(f13479a);
        if (!file.exists() || !file.isDirectory()) {
            file.mkdir();
        }
    }

    /* renamed from: a */
    public static Bitmap m14478a(String str) {
        try {
            return BitmapFactory.decodeStream(new FileInputStream(str));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static String m14480a(long j) {
        if (j / WifiManager.WIFI_FEATURE_MKEEP_ALIVE > 0) {
            return "" + new DecimalFormat("#.##").format((double) (((float) j) / 1048576.0f)) + "MB";
        } else if (j / 1024 > 0) {
            return "" + (j / 1024) + "KB";
        } else {
            return "" + j + "B";
        }
    }

    /* renamed from: a */
    public static void m14482a(Context context, String str) {
        Uri fromFile;
        File file = new File(str);
        Intent intent = new Intent("android.intent.action.VIEW");
        try {
            if (Build.VERSION.SDK_INT >= 24) {
                fromFile = FileProvider.getUriForFile(context, GBApplication.instance().getPackageName() + ".provider", file);
                intent.setAction("android.intent.action.INSTALL_PACKAGE");
                intent.addFlags(1);
            } else {
                fromFile = Uri.fromFile(file);
                intent.setAction("android.intent.action.VIEW");
                intent.setFlags(268435456);
            }
            intent.setDataAndType(fromFile, "application/vnd.android.package-archive");
            context.startActivity(intent);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (ActivityNotFoundException e2) {
            e2.printStackTrace();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    /* renamed from: b */
    public static void m14488b(Context context, String str) {
        Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(str);
        if (launchIntentForPackage != null) {
            context.startActivity(launchIntentForPackage);
        }
    }

    public static boolean delete(File file) {
        boolean z = true;
        if (file.exists()) {
            if (file.isDirectory()) {
                for (File file2 : file.listFiles()) {
                    z &= delete(file2);
                }
                z &= file.delete();
            }
            if (file.isFile()) {
                z &= file.delete();
            }
            if (z) {
                return z;
            }
            Log.m14403e(null, "Delete failed;");
            return z;
        }
        Log.m14403e(null, "File does not exist.");
        return false;
    }

    /* renamed from: c */
    public static boolean m14492c(Context context, String str) {
        if (f13482d == null) {
            m14481a(context);
        }
        return f13482d.contains(str);
    }

    /* renamed from: a */
    public static void m14481a(Context context) {
        f13482d = new ArrayList();
        for (PackageInfo packageInfo : context.getPackageManager().getInstalledPackages(0)) {
            f13482d.add(packageInfo.packageName);
        }
    }

    /* renamed from: d */
    public static String m14494d(Context context, String str) {
        PackageInfo packageArchiveInfo = context.getPackageManager().getPackageArchiveInfo(str, 1);
        if (packageArchiveInfo != null) {
            return packageArchiveInfo.applicationInfo.packageName;
        }
        return null;
    }

    /* renamed from: b */
    public static String m14487b(Context context) {
        StatFs statFs = new StatFs("/mnt/sdcard");
        return Formatter.formatFileSize(context, m14489c(statFs) * m14477a(statFs));
    }

    /* renamed from: c */
    public static String m14490c(Context context) {
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        long a = m14477a(statFs);
        return Formatter.formatFileSize(context, (m14486b(statFs) * a) - (m14489c(statFs) * a));
    }

    /* renamed from: d */
    public static int m14493d(Context context) {
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        long a = m14477a(statFs);
        long b = m14486b(statFs) * a;
        return (int) (((b - (m14489c(statFs) * a)) * 100) / b);
    }

    /* renamed from: g */
    public static boolean m14498g() {
        return Build.VERSION.SDK_INT >= 18;
    }

    /* renamed from: a */
    private static long m14477a(StatFs statFs) {
        if (m14498g()) {
            return statFs.getBlockSizeLong();
        }
        return (long) statFs.getBlockSize();
    }

    /* renamed from: b */
    private static long m14486b(StatFs statFs) {
        if (m14498g()) {
            return statFs.getBlockCountLong();
        }
        return (long) statFs.getBlockCount();
    }

    /* renamed from: c */
    private static long m14489c(StatFs statFs) {
        if (m14498g()) {
            return statFs.getAvailableBlocksLong();
        }
        return (long) statFs.getAvailableBlocks();
    }

    /* renamed from: a */
    public static File m14479a(String str, String str2) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setConnectTimeout(6000);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setUseCaches(false);
            InputStream inputStream = httpURLConnection.getInputStream();
            String substring = str.substring(str.lastIndexOf(PsuedoNames.PSEUDONAME_ROOT) + 1);
            Log.m14398b(f13480b, "getFileInputStream: filename " + substring);
            String str3 = str2 + PsuedoNames.PSEUDONAME_ROOT + substring;
            Log.m14398b(f13480b, "getFileInputStream: storagePath " + str3);
            File file = new File(str3);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] bArr = new byte[8192];
            while (true) {
                int read = inputStream.read(bArr, 0, 8192);
                if (read != -1) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    fileOutputStream.close();
                    inputStream.close();
                    return file;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.m14403e(f13480b, "getFileInputStream:" + e.getMessage());
            return null;
        }
    }

    /* renamed from: a */
    public static void m14483a(File file) {
        File[] listFiles;
        Log.m14398b(f13480b, "delete file path=" + file.getAbsolutePath());
        if (file.exists()) {
            if (file.isFile()) {
                file.delete();
            } else if (file.isDirectory()) {
                for (File file2 : file.listFiles()) {
                    m14483a(file2);
                }
            }
            file.delete();
            return;
        }
        Log.m14403e(f13480b, "delete file no exists " + file.getAbsolutePath());
    }
}
