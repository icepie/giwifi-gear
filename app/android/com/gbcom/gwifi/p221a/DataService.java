package com.gbcom.gwifi.p221a;

import com.gbcom.gwifi.p221a.AbstractC2436b;
import com.gbcom.gwifi.p221a.AbstractC2440d;

/* renamed from: com.gbcom.gwifi.a.a */
public interface DataService<T extends AbstractC2436b, R extends AbstractC2440d> {
    /* renamed from: a */
    R mo24035a(T t);

    /* renamed from: a */
    void mo24036a(T t, RequestHandler<T, R> cVar);

    /* renamed from: a */
    void mo24037a(T t, RequestHandler<T, R> cVar, boolean z);
}
