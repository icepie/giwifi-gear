package com.gbcom.gwifi.functions.p241b;

/* renamed from: com.gbcom.gwifi.functions.b.a */
public class DownloadException extends Exception {

    /* renamed from: a */
    private static final long f9170a = 1;

    /* renamed from: b */
    private String f9171b;

    public DownloadException(String str) {
        super(str);
    }

    public DownloadException(String str, String str2) {
        super(str);
        this.f9171b = str2;
    }

    /* renamed from: a */
    public String mo24517a() {
        return this.f9171b;
    }
}
