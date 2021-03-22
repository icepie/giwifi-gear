package com.gbcom.gwifi.p221a.p227e.p228a;

import com.gbcom.gwifi.p221a.p227e.UdpResponse;

/* renamed from: com.gbcom.gwifi.a.e.a.b */
public class BaseUdpResponse implements UdpResponse {

    /* renamed from: a */
    private Object f8602a;

    /* renamed from: b */
    private Object f8603b;

    public BaseUdpResponse(Object obj, Object obj2) {
        this.f8603b = obj;
        this.f8602a = obj2;
    }

    @Override // com.gbcom.gwifi.p221a.p227e.UdpResponse
    /* renamed from: a */
    public Object mo24169a() {
        return this.f8602a;
    }

    @Override // com.gbcom.gwifi.p221a.p227e.UdpResponse
    /* renamed from: b */
    public Object mo24170b() {
        return this.f8603b;
    }
}
