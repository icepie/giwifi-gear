package com.gbcom.gwifi.p221a.p225c;

import com.gbcom.gwifi.p221a.AbstractC2440d;

/* renamed from: com.gbcom.gwifi.a.c.b */
public class BasicResponse implements AbstractC2440d {

    /* renamed from: b */
    private Object f8530b;

    /* renamed from: c */
    private Object f8531c;

    public BasicResponse(Object obj, Object obj2) {
        this.f8531c = obj;
        this.f8530b = obj2;
    }

    @Override // com.gbcom.gwifi.p221a.AbstractC2440d
    /* renamed from: a */
    public Object mo24085a() {
        return this.f8530b;
    }

    @Override // com.gbcom.gwifi.p221a.AbstractC2440d
    /* renamed from: b */
    public Object mo24086b() {
        return this.f8531c;
    }
}
