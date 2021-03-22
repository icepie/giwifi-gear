package com.gbcom.gwifi.functions.ping;

import com.j256.ormlite.stmt.query.SimpleComparison;
import java.p456io.BufferedReader;
import java.p456io.IOException;
import java.p456io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import p419io.netty.handler.codec.rtsp.RtspHeaders;

/* renamed from: com.gbcom.gwifi.functions.ping.b */
public class PingSrvUtil {

    /* renamed from: a */
    public static boolean f9963a = false;

    /* renamed from: b */
    public static int f9964b = 0;

    /* renamed from: c */
    public static int f9965c = 0;

    /* renamed from: d */
    public static String f9966d;

    /* renamed from: e */
    static Process f9967e;

    /* renamed from: f */
    private static List<PingObserver> f9968f = new ArrayList();

    /* renamed from: a */
    public static void m11515a(PingObserver aVar) {
        if (!f9968f.contains(aVar)) {
            f9968f.add(aVar);
        }
    }

    /* renamed from: a */
    public static void m11514a() {
        for (PingObserver aVar : f9968f) {
            aVar.mo25263a();
        }
    }

    /* renamed from: a */
    public static void m11517a(String str, int i) {
        for (PingObserver aVar : f9968f) {
            aVar.mo25264a(str, i);
        }
    }

    /* renamed from: a */
    public static void m11518a(String str, int i, int i2) {
        for (PingObserver aVar : f9968f) {
            aVar.mo25265a(str, i, i2);
        }
    }

    /* renamed from: a */
    public static void m11516a(final String str) {
        f9963a = false;
        f9966d = str;
        new Thread(new Runnable() {
            /* class com.gbcom.gwifi.functions.ping.PingSrvUtil.RunnableC27941 */

            @Override // java.lang.Runnable
            public void run() {
                PingSrvUtil.m11514a();
                new StringBuffer();
                PingSrvUtil.f9964b = 0;
                PingSrvUtil.f9965c = 0;
                try {
                    PingSrvUtil.f9967e = Runtime.getRuntime().exec("/system/bin/ping -c 100 -i 1 " + str);
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(PingSrvUtil.f9967e.getInputStream()));
                    while (true) {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null || PingSrvUtil.f9963a) {
                            break;
                        }
                        PingSrvUtil.m11517a(readLine, 0);
                        if (readLine.contains("seq=")) {
                            int indexOf = readLine.indexOf(SimpleComparison.EQUAL_TO_OPERATION) + 1;
                            int lastIndexOf = readLine.lastIndexOf(RtspHeaders.Values.TTL);
                            if (lastIndexOf < indexOf) {
                                break;
                            }
                            PingSrvUtil.f9964b = Integer.parseInt(readLine.substring(indexOf, lastIndexOf).split(" ")[0]);
                        }
                        PingSrvUtil.f9965c = PingSrvUtil.m11521c(readLine) + PingSrvUtil.f9965c;
                    }
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /* access modifiers changed from: private */
    /* renamed from: c */
    public static int m11521c(String str) {
        if (Pattern.compile("(ttl=\\d+)", 2).matcher(str).find()) {
            return 1;
        }
        return 0;
    }

    /* renamed from: b */
    public static void m11520b() {
        f9963a = true;
        m11522c();
    }

    /* renamed from: c */
    private static void m11522c() {
        if (f9967e != null) {
            f9967e.destroy();
            m11518a(f9966d, f9965c, f9964b);
        }
    }
}
