package com.gbcom.gwifi.p221a.p227e.p228a;

import com.gbcom.gwifi.p221a.p227e.UdpRequest;

/* renamed from: com.gbcom.gwifi.a.e.a.a */
public class BaseUdpRequest implements UdpRequest {

    /* renamed from: a */
    private byte[] f8599a;

    /* renamed from: b */
    private int f8600b;

    /* renamed from: c */
    private String f8601c;

    public BaseUdpRequest(String str, int i, byte[] bArr) {
        this.f8601c = str;
        this.f8600b = i;
        this.f8599a = bArr;
    }

    /* renamed from: a */
    public static BaseUdpRequest m10352a(String str, int i, byte[] bArr) {
        return new BaseUdpRequest(str, i, bArr);
    }

    @Override // com.gbcom.gwifi.p221a.p227e.UdpRequest
    /* renamed from: a */
    public byte[] mo24166a() {
        return this.f8599a;
    }

    @Override // com.gbcom.gwifi.p221a.p227e.UdpRequest
    /* renamed from: b */
    public int mo24167b() {
        return this.f8600b;
    }

    @Override // com.gbcom.gwifi.p221a.p227e.UdpRequest
    /* renamed from: c */
    public String mo24168c() {
        return this.f8601c;
    }
}
