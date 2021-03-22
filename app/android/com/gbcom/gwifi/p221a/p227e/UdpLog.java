package com.gbcom.gwifi.p221a.p227e;

import java.p456io.Serializable;

/* renamed from: com.gbcom.gwifi.a.e.a */
public class UdpLog implements Serializable {

    /* renamed from: a */
    public static int f8589a = 0;

    /* renamed from: b */
    public static int f8590b = 1;

    /* renamed from: c */
    private long f8591c;

    /* renamed from: d */
    private byte[] f8592d;

    /* renamed from: e */
    private String f8593e;

    /* renamed from: f */
    private int f8594f;

    /* renamed from: g */
    private String f8595g;

    /* renamed from: h */
    private String f8596h;

    /* renamed from: i */
    private int f8597i;

    /* renamed from: j */
    private boolean f8598j;

    public UdpLog() {
    }

    public UdpLog(long j, String str, int i, String str2, int i2, byte[] bArr, boolean z, String str3) {
        this.f8591c = j;
        this.f8592d = bArr;
        this.f8593e = str2;
        this.f8594f = i2;
        this.f8595g = str3;
        this.f8597i = i;
        this.f8598j = z;
        this.f8596h = str;
    }

    /* renamed from: a */
    public long mo24150a() {
        return this.f8591c;
    }

    /* renamed from: a */
    public void mo24152a(long j) {
        this.f8591c = j;
    }

    /* renamed from: b */
    public byte[] mo24158b() {
        return this.f8592d;
    }

    /* renamed from: a */
    public void mo24155a(byte[] bArr) {
        this.f8592d = bArr;
    }

    /* renamed from: c */
    public String mo24159c() {
        return this.f8593e;
    }

    /* renamed from: a */
    public void mo24153a(String str) {
        this.f8593e = str;
    }

    /* renamed from: d */
    public int mo24161d() {
        return this.f8594f;
    }

    /* renamed from: a */
    public void mo24151a(int i) {
        this.f8594f = i;
    }

    /* renamed from: e */
    public String mo24162e() {
        return this.f8595g;
    }

    /* renamed from: b */
    public void mo24157b(String str) {
        this.f8595g = str;
    }

    /* renamed from: f */
    public int mo24163f() {
        return this.f8597i;
    }

    /* renamed from: b */
    public void mo24156b(int i) {
        this.f8597i = i;
    }

    /* renamed from: g */
    public boolean mo24164g() {
        return this.f8598j;
    }

    /* renamed from: a */
    public void mo24154a(boolean z) {
        this.f8598j = z;
    }

    /* renamed from: h */
    public String mo24165h() {
        return this.f8596h;
    }

    /* renamed from: c */
    public void mo24160c(String str) {
        this.f8596h = str;
    }
}
