package com.gbcom.gwifi.p221a.p227e;

/* renamed from: com.gbcom.gwifi.a.e.f */
public class UdpSession {

    /* renamed from: a */
    public UdpRequestHandler<UdpRequest, UdpResponse> f8628a;

    /* renamed from: b */
    public UdpRequest f8629b;

    /* renamed from: c */
    public int f8630c = 2;

    public UdpSession() {
    }

    public UdpSession(UdpRequest bVar, UdpRequestHandler<UdpRequest, UdpResponse> cVar) {
        this.f8629b = bVar;
        this.f8628a = cVar;
    }
}
