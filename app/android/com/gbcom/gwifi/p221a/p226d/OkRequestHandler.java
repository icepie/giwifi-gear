package com.gbcom.gwifi.p221a.p226d;

import com.p136b.p137a.p139b.C$Gson$Types;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import p041c.Request;

/* renamed from: com.gbcom.gwifi.a.d.e */
public abstract class OkRequestHandler<T> {
    Type mType = getSuperclassTypeParameter(getClass());

    public abstract void onRequestFailed(Request abVar, Exception exc);

    public abstract void onRequestFinish(Request abVar, T t);

    public abstract void onRequestProgress(Request abVar, long j, long j2);

    public abstract void onRequestStart(Request abVar);

    static Type getSuperclassTypeParameter(Class<?> cls) {
        Type genericSuperclass = cls.getGenericSuperclass();
        if (!(genericSuperclass instanceof Class)) {
            return C$Gson$Types.m4955d(((ParameterizedType) genericSuperclass).getActualTypeArguments()[0]);
        }
        throw new RuntimeException("Missing type parameter.");
    }

    public void onAfter() {
    }
}
