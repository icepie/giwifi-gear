package com.gbcom.gwifi.functions.test;

import java.util.HashMap;

/* renamed from: com.gbcom.gwifi.functions.test.c */
public class DeviceTestReport {

    /* renamed from: a */
    public static final String f12653a = "no_net_error";

    /* renamed from: b */
    public static final String f12654b = "wangguan_request_failed_error";

    /* renamed from: c */
    public static final String f12655c = "wanguan_parse_error";

    /* renamed from: d */
    public static final String f12656d = "lanxun_dns_error";

    /* renamed from: e */
    public static final String f12657e = "lanxun_dns_host_exception";

    /* renamed from: f */
    public static final String f12658f = "lanxun_net_error";

    /* renamed from: g */
    public static final String f12659g = "relogin_request_failed_error";

    /* renamed from: h */
    public static final String f12660h = "relogin_parse_error";

    /* renamed from: i */
    public static final String f12661i = "wangguan_parse_success";

    /* renamed from: j */
    public static final String f12662j = "lanxun_success";

    /* renamed from: k */
    public static final String f12663k = "relogin_success";

    /* renamed from: l */
    public static final String f12664l = "wangguan";

    /* renamed from: m */
    public static final String f12665m = "lanxun";

    /* renamed from: n */
    public static final String f12666n = "正常";

    /* renamed from: o */
    public static final String f12667o = "异常";

    /* renamed from: p */
    public static HashMap<String, Object> f12668p = new HashMap<>();

    /* renamed from: a */
    public static void m13722a(String str, Object obj) {
        f12668p.put(str, obj);
    }

    /* renamed from: a */
    public static void m13721a() {
        f12668p.clear();
    }

    /* renamed from: b */
    public static HashMap<String, Object> m13723b() {
        return f12668p;
    }
}
