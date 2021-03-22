package com.gbcom.gwifi.base.p233b;

import android.content.Context;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.util.Log;
import java.p456io.ByteArrayOutputStream;
import java.p456io.File;
import java.p456io.FileInputStream;
import java.p456io.FileOutputStream;
import org.apache.xpath.compiler.PsuedoNames;

/* renamed from: com.gbcom.gwifi.base.b.d */
public class SoFile {

    /* renamed from: a */
    private static final String f8940a = "SoFile";

    /* renamed from: a */
    public static void m10598a(Context context, String str, String[] strArr) {
        File dir = context.getDir("libs", 0);
        boolean z = false;
        for (String str2 : strArr) {
            if (!m10599a(dir, str2)) {
                z = true;
            }
        }
        if (z) {
            Log.m14398b(f8940a, "offlineFaceDetect: loadFaceSo not isLoadSoFile: " + dir.getAbsolutePath());
            Log.m14398b(f8940a, "offlineFaceDetect: loadFaceSo fromPath: " + str);
            copy(str, dir.getAbsolutePath());
        }
        try {
            GBLoadLibrary.m10579a(GBApplication.instance().getBaseContext().getClassLoader(), dir);
        } catch (Throwable th) {
            th.printStackTrace();
            Log.m14403e(f8940a, th.getMessage());
        }
    }

    /* renamed from: a */
    public static void m10597a(Context context, String str, String str2) {
        File dir = context.getDir("libs", 0);
        Log.m14398b(f8940a, "offlineFaceDetect: loadFaceSo not isLoadSoFile: " + dir.getAbsolutePath());
        Log.m14398b(f8940a, "offlineFaceDetect: loadFaceSo fromPath: " + str);
        copy(str, dir.getAbsolutePath(), str2);
    }

    /* renamed from: a */
    public static boolean m10599a(File file, String str) {
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            return false;
        }
        boolean z = false;
        for (File file2 : listFiles) {
            if (file2.getName().contains(str)) {
                z = true;
            }
        }
        return z;
    }

    public static int copy(String str, String str2) {
        File file = new File(str);
        if (!file.exists()) {
            Log.m14403e(f8940a, "offlineFaceDetect: loadFaceSo root not exist: " + file.getAbsolutePath());
            return -1;
        }
        Log.m14398b(f8940a, "offlineFaceDetect: loadFaceSo root exist: " + file.getAbsolutePath());
        File[] listFiles = file.listFiles();
        File file2 = new File(str2);
        if (!file2.exists()) {
            file2.mkdirs();
        }
        if (listFiles == null || listFiles.length <= 0) {
            return 0;
        }
        for (File file3 : listFiles) {
            if (file3.isDirectory()) {
                Log.m14398b(f8940a, "offlineFaceDetect: loadFaceSo isDirectory: " + file3.getAbsolutePath());
                copy(file3.getPath() + PsuedoNames.PSEUDONAME_ROOT, str2 + file3.getName() + PsuedoNames.PSEUDONAME_ROOT);
            } else {
                Log.m14398b(f8940a, "offlineFaceDetect: loadFaceSo isFile: " + file3.getAbsolutePath());
                Log.m14398b(f8940a, "offlineFaceDetect: loadFaceSo isFile: " + file3.getName());
                if (file3.getName().contains(".so")) {
                    Log.m14398b(f8940a, "offlineFaceDetect: loadFaceSo copySdcardFile: " + file3.getPath());
                    m10596a(file3.getPath(), str2 + File.separator + file3.getName());
                }
            }
        }
        return 0;
    }

    public static int copy(String str, String str2, String str3) {
        File file = new File(str);
        if (!file.exists()) {
            Log.m14403e(f8940a, "offlineFaceDetect: loadFaceSo root not exist: " + file.getAbsolutePath());
            return -1;
        }
        Log.m14398b(f8940a, "offlineFaceDetect: loadFaceSo root exist: " + file.getAbsolutePath());
        File[] listFiles = file.listFiles();
        File file2 = new File(str2);
        if (!file2.exists()) {
            file2.mkdirs();
        }
        if (listFiles == null || listFiles.length <= 0) {
            return 0;
        }
        for (File file3 : listFiles) {
            if (!file3.isDirectory()) {
                Log.m14398b(f8940a, "offlineFaceDetect: loadFaceSo isFile: " + file3.getAbsolutePath());
                Log.m14398b(f8940a, "offlineFaceDetect: loadFaceSo isFile: " + file3.getName());
                if (file3.getName().equals(str3 + ".so")) {
                    Log.m14398b(f8940a, "offlineFaceDetect: loadFaceSo copySdcardFile: " + file3.getPath());
                    m10596a(file3.getPath(), str2 + File.separator + file3.getName());
                }
            }
        }
        return 0;
    }

    /* renamed from: a */
    public static int m10596a(String str, String str2) {
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
            FileOutputStream fileOutputStream = new FileOutputStream(str2);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[1024];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read != -1) {
                    byteArrayOutputStream.write(bArr, 0, read);
                } else {
                    fileOutputStream.write(byteArrayOutputStream.toByteArray());
                    byteArrayOutputStream.close();
                    fileOutputStream.close();
                    fileInputStream.close();
                    return 0;
                }
            }
        } catch (Exception e) {
            Log.m14403e(f8940a, "offlineFaceDetect: loadFaceSo " + e.getMessage());
            return -1;
        }
    }
}
