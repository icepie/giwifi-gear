package com.gbcom.gwifi.util;

import com.gbcom.gwifi.codec.EncryptUtil;
import com.gbcom.gwifi.codec.MD5Util;
import com.gbcom.gwifi.util.cache.CacheAccount;
import com.gbcom.gwifi.util.cache.CacheApp;
import com.gbcom.gwifi.util.cache.CacheAuth;
import com.j256.ormlite.stmt.query.SimpleComparison;
import com.umeng.message.proguard.MessageStore;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;
import java.p456io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* renamed from: com.gbcom.gwifi.util.u */
public class URLUtil {

    /* renamed from: a */
    private static final String f13484a = "URLUtil";

    /* renamed from: b */
    private static String[] f13485b = {"url_encode", "gb_md5", "gb_substr"};

    /* renamed from: a */
    public static String m14541a(String str) {
        String b = m14545b(str);
        List<String> g = m14551g(b);
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < g.size(); i++) {
            C3469a c = m14547c(g.get(i));
            String e = c.mo28173e();
            if (e != null) {
                arrayList.add(c);
                for (int i2 = i + 1; i2 < g.size(); i2++) {
                    String str2 = g.get(i2);
                    if (str2.contains(c.mo28165a()) && !str2.equals(c.mo28165a())) {
                        g.add(i2, str2.replace(c.mo28172d(), e));
                        g.remove(i2 + 1);
                    }
                }
            }
        }
        String str3 = b;
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            C3469a aVar = (C3469a) arrayList.get(i3);
            str3 = str3.replace(aVar.mo28172d(), aVar.mo28170c());
        }
        if (str3.contains("gb_sign(")) {
            String substring = str3.substring(str3.indexOf("gb_sign("));
            C3469a c2 = m14547c(substring.substring(0, substring.indexOf(MessageStore.f23536t) + 1));
            String str4 = "";
            if (c2.mo28169b() != null && c2.mo28169b().length > 0) {
                str4 = c2.mo28169b()[0].toString();
            }
            TreeMap treeMap = new TreeMap();
            String substring2 = str3.substring(str3.indexOf("?") + 1);
            if (substring2 != null) {
                String[] split = substring2.split("&");
                for (String str5 : split) {
                    String[] split2 = str5.split(SimpleComparison.EQUAL_TO_OPERATION);
                    if (split2 != null && split2.length == 2 && !C3467s.m14513e(split2[0]) && !C3467s.m14513e(split2[1]) && !split2[1].contains("gb_sign(")) {
                        treeMap.put(split2[0], split2[1]);
                    }
                }
            }
            c2.mo28168b(m14542a(str4, treeMap));
            str3 = str3.replace(c2.mo28172d(), c2.mo28170c());
        }
        if (!str3.contains("encrypt=gb_self")) {
            return str3;
        }
        String str6 = "";
        Map<String, String> f = m14550f(str3);
        for (String str7 : f.keySet()) {
            str6 = !str7.contains("encrypt") ? str6 + str7 + SimpleComparison.EQUAL_TO_OPERATION + f.get(str7) + "&" : str6;
        }
        String[] split3 = str3.split("[?]");
        try {
            String substring3 = str6.substring(0, str6.length() - 1);
            return (split3[0] + "?data=" + URLEncoder.encode(EncryptUtil.getEncrypt(substring3), "UTF-8")) + "&length=" + substring3.length();
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            return str3;
        }
    }

    /* renamed from: a */
    private static String m14542a(String str, TreeMap<String, Object> treeMap) {
        StringBuffer stringBuffer = new StringBuffer();
        for (Map.Entry<String, Object> entry : treeMap.entrySet()) {
            stringBuffer.append(entry.getKey() + SimpleComparison.EQUAL_TO_OPERATION + entry.getValue() + "&");
        }
        String stringBuffer2 = stringBuffer.toString();
        String trim = stringBuffer2.substring(0, stringBuffer2.length() - 1).trim();
        Log.m14403e(f13484a, "sort str:" + trim);
        return MD5Util.m10825a(trim + str).toLowerCase();
    }

    /* renamed from: g */
    private static List<String> m14551g(String str) {
        final ArrayList arrayList = new ArrayList();
        String[] strArr = f13485b;
        for (String str2 : strArr) {
            if (str.contains(str2)) {
                List<String> a = m14543a(str, str2);
                if (a.size() > 0) {
                    arrayList.addAll(a);
                }
            }
        }
        Collections.sort(arrayList, new Comparator<String>() {
            /* class com.gbcom.gwifi.util.URLUtil.C34681 */

            /* renamed from: a */
            public int compare(String str, String str2) {
                if (str.equals(str2)) {
                    return 0;
                }
                if (str.contains(str2)) {
                    return 1;
                }
                if (str2.contains(str)) {
                    return -1;
                }
                boolean b = URLUtil.m14546b(str, arrayList);
                boolean b2 = URLUtil.m14546b(str, arrayList);
                if (b && b2) {
                    return str.hashCode() <= str2.hashCode() ? 0 : 1;
                }
                if (b && !b2) {
                    return 1;
                }
                if (b || !b2) {
                    return str.hashCode() <= str2.hashCode() ? 0 : 1;
                }
                return -1;
            }
        });
        return arrayList;
    }

    /* renamed from: b */
    public static String m14545b(String str) {
        if (str.contains("{cloud_platform}")) {
            str = str.replace("{cloud_platform}", CacheAuth.getInstance().getStationCloud());
        }
        if (str.contains("{gw_id}")) {
            str = str.replace("{gw_id}", CacheAuth.getInstance().getGwId());
        }
        if (str.contains("{name}")) {
            str = str.replace("{name}", CacheAccount.getInstance().getLoginPhone());
        }
        if (str.contains("{phone}")) {
            str = str.replace("{phone}", CacheAccount.getInstance().getLoginPhone());
        }
        if (str.contains("{password}")) {
            str = str.replace("{password}", CacheAccount.getInstance().getLoginStaticPassword());
        }
        if (str.contains("{mac}")) {
            str = str.replace("{mac}", CacheAuth.getInstance().getLocalMac());
        }
        if (str.contains("{sn}")) {
            str = str.replace("{sn}", CacheAuth.getInstance().getGwSn());
        }
        String str2 = (System.currentTimeMillis() / 1000) + "";
        if (str.contains("{time}")) {
            str = str.replace("{time}", str2);
        }
        if (str.contains("{code1}")) {
            str = str.replace("{code1}", MD5Util.m10825a(str2 + CacheAccount.getInstance().getLoginPhone() + "www.xxx.com").toLowerCase());
        }
        if (str.contains("{identity_type}")) {
            str = str.replace("{identity_type}", CacheAccount.getInstance().getIdentityType().toString());
        }
        if (str.contains("{college_id}")) {
            str = str.replace("{college_id}", CacheAccount.getInstance().getCollegeId() + "");
        }
        if (str.contains("{hotspot_group_id}")) {
            str = str.replace("{hotspot_group_id}", ((Object) CacheAccount.getInstance().getHotspotGroupId()) + "");
        }
        if (str.contains("{orgId}")) {
            str = str.replace("{orgId}", CacheAuth.getInstance().getOrgId());
        }
        if (str.contains("{userId}")) {
            str = str.replace("{userId}", CacheAccount.getInstance().getUserId() + "");
        }
        if (str.contains("{app_uuid}")) {
            str = str.replace("{app_uuid}", CacheApp.getInstance().getAppUuid());
        }
        if (str.contains("{version}")) {
            str = str.replace("{version}", VersionUtil.m14565a());
        }
        if (str.contains("{access_token}")) {
            return str.replace("{access_token}", CacheAccount.getInstance().getLoginToken());
        }
        return str;
    }

    /* renamed from: a */
    private static List<String> m14543a(String str, String str2) {
        int h;
        ArrayList arrayList = new ArrayList();
        int indexOf = str.indexOf(str2 + MessageStore.f23535s);
        while (indexOf != -1 && (h = m14552h(str.substring(indexOf))) > 0) {
            arrayList.add(str.substring(indexOf, indexOf + h + 1));
            str = str.substring(indexOf + h + 1);
            indexOf = str.indexOf(str2 + MessageStore.f23535s);
        }
        return arrayList;
    }

    /* renamed from: h */
    private static int m14552h(String str) {
        int i = 0;
        for (int i2 = 0; i2 < str.length(); i2++) {
            if (str.charAt(i2) == '(') {
                i++;
            } else if (str.charAt(i2) == ')' && i - 1 == 0) {
                return i2;
            }
        }
        return 0;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.gbcom.gwifi.util.u$a */
    /* compiled from: URLUtil */
    public static class C3469a {

        /* renamed from: a */
        private String f13487a;

        /* renamed from: b */
        private String f13488b;

        /* renamed from: c */
        private Object[] f13489c;

        /* renamed from: d */
        private String f13490d;

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public String mo28165a() {
            return this.f13487a;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public void mo28166a(String str) {
            this.f13487a = str;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: b */
        public Object[] mo28169b() {
            return this.f13489c;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public void mo28167a(Object[] objArr) {
            this.f13489c = objArr;
        }

        C3469a(String str, Object[] objArr, String str2) {
            this.f13487a = str;
            this.f13489c = objArr;
            this.f13490d = str2;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: c */
        public String mo28170c() {
            return this.f13488b;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: b */
        public void mo28168b(String str) {
            this.f13488b = str;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: d */
        public String mo28172d() {
            return this.f13490d;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: c */
        public void mo28171c(String str) {
            this.f13490d = str;
        }

        /* renamed from: e */
        public String mo28173e() {
            Class[] clsArr = new Class[this.f13489c.length];
            for (int i = 0; i < this.f13489c.length; i++) {
                clsArr[i] = this.f13489c[i].getClass();
            }
            try {
                Object invoke = URLFunctions.class.getMethod(this.f13487a, clsArr).invoke(new URLFunctions(), this.f13489c);
                if (invoke != null) {
                    this.f13488b = invoke.toString();
                }
                return invoke.toString();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
                return null;
            } catch (InvocationTargetException e2) {
                e2.printStackTrace();
                return null;
            } catch (IllegalAccessException e3) {
                e3.printStackTrace();
                return null;
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public static boolean m14546b(String str, List<String> list) {
        for (String str2 : list) {
            if (str2.contains(str) && !str2.equals(str)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: c */
    public static C3469a m14547c(String str) {
        int indexOf = str.indexOf(MessageStore.f23535s);
        return new C3469a(str.substring(0, indexOf), str.substring(indexOf + 1, str.length() - 1).split(","), str);
    }

    /* renamed from: d */
    public static Map<String, String> m14548d(String str) {
        HashMap hashMap = new HashMap();
        for (String str2 : str.split("[&]")) {
            String[] split = str2.split("[=]");
            if (split.length > 1) {
                hashMap.put(split[0], split[1]);
            } else if (split[0] != "") {
                hashMap.put(split[0], "");
            }
        }
        return hashMap;
    }

    /* renamed from: e */
    public static String m14549e(String str) {
        String lowerCase = str.trim().toLowerCase();
        String[] split = lowerCase.split("[?]");
        if (lowerCase.length() <= 1 || split.length <= 1 || split[1] == null) {
            return null;
        }
        return split[1];
    }

    /* renamed from: f */
    public static Map<String, String> m14550f(String str) {
        HashMap hashMap = new HashMap();
        String e = m14549e(str);
        if (e != null) {
            String[] split = e.split("[&]");
            for (String str2 : split) {
                String[] split2 = str2.split("[=]");
                if (split2.length > 1) {
                    hashMap.put(split2[0], split2[1]);
                } else if (split2[0] != "") {
                    hashMap.put(split2[0], "");
                }
            }
        }
        return hashMap;
    }
}
