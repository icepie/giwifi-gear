package com.gbcom.gwifi.util;

import com.alipay.sdk.p116j.C1536i;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;
import p000a.JSONDeserializer;
import p000a.JSONSerializer;

/* renamed from: com.gbcom.gwifi.util.j */
public class JsonUtil {

    /* renamed from: a */
    public static JSONSerializer f13441a = new JSONSerializer();

    /* renamed from: b */
    public static JSONDeserializer f13442b = new JSONDeserializer();

    /* renamed from: a */
    public static <T> T m14386a(String str, Class<T> cls) {
        if (!m14389a(str)) {
            return null;
        }
        try {
            return (T) f13442b.mo139a(str, (Class) cls);
        } catch (Exception e) {
            return null;
        }
    }

    /* renamed from: a */
    public static boolean m14389a(String str) {
        if (str == null) {
            return false;
        }
        if ((!str.startsWith("{") || !str.endsWith(C1536i.f3512d)) && (!str.startsWith("[") || !str.endsWith("]"))) {
            return false;
        }
        return true;
    }

    /* renamed from: a */
    public static String m14387a(Object obj) {
        return f13441a.mo160b(obj);
    }

    /* renamed from: a */
    public static String m14388a(HashMap<String, Object> hashMap) {
        return f13441a.mo160b(hashMap);
    }

    /* renamed from: b */
    public static String m14390b(HashMap<String, Object> hashMap) {
        return f13441a.mo150a("*.class").mo160b(hashMap);
    }

    /* renamed from: b */
    public static HashMap<String, String> m14391b(String str) throws JSONException {
        HashMap<String, String> hashMap = new HashMap<>();
        JSONObject jSONObject = new JSONObject(str);
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String valueOf = String.valueOf(keys.next());
            hashMap.put(valueOf, jSONObject.get(valueOf) + "");
        }
        return hashMap;
    }

    /* renamed from: c */
    public static boolean m14392c(String str) {
        try {
            new JSONObject(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
