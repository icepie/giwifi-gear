package com.gbcom.gwifi.p221a.p227e;

import com.gbcom.gwifi.p221a.p227e.UdpRequest;
import com.gbcom.gwifi.p221a.p227e.UdpResponse;

/* renamed from: com.gbcom.gwifi.a.e.c */
public interface UdpRequestHandler<T extends UdpRequest, R extends UdpResponse> {
    /* renamed from: a */
    void mo24183a(T t);

    /* renamed from: a */
    void mo24184a(T t, int i, int i2);

    /* renamed from: a */
    void mo24185a(T t, R r);

    /* renamed from: b */
    void mo24186b(T t, R r);
}
