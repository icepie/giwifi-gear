package com.gbcom.gwifi.util;

import com.p136b.p137a.Gson;

/* renamed from: com.gbcom.gwifi.util.g */
public class GsonUtil {

    /* renamed from: a */
    private static Gson f13341a = new Gson();

    /* renamed from: a */
    public static synchronized Gson m14241a() {
        Gson fVar;
        synchronized (GsonUtil.class) {
            fVar = f13341a;
        }
        return fVar;
    }
}
