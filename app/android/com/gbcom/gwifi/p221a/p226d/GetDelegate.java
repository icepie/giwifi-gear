package com.gbcom.gwifi.p221a.p226d;

import com.gbcom.gwifi.util.SystemUtil;
import java.p456io.IOException;
import p041c.OkHttpClient;
import p041c.Request;
import p041c.Response;

/* renamed from: com.gbcom.gwifi.a.d.b */
public class GetDelegate {
    /* renamed from: c */
    private Request m10225c(String str, Object obj) {
        Request.C1190a a = new Request.C1190a().mo16103a("connection", "close").mo16101a(str);
        if (obj != null) {
            a.mo16100a(obj);
        }
        return a.mo16110c();
    }

    /* renamed from: d */
    private Request m10226d(String str, Object obj) {
        Request.C1190a a = new Request.C1190a().mo16103a("connection", "close").mo16107b("User-Agent").mo16108b("User-Agent", SystemUtil.m14526c()).mo16101a(str);
        if (obj != null) {
            a.mo16100a(obj);
        }
        return a.mo16110c();
    }

    /* renamed from: a */
    public Response mo24092a(Request abVar) throws IOException {
        return m10224a().mo16200a(abVar).mo16074b();
    }

    /* renamed from: a */
    public Response mo24093a(String str) throws IOException {
        return mo24094a(str, (Object) null);
    }

    /* renamed from: a */
    public Response mo24094a(String str, Object obj) throws IOException {
        return mo24092a(m10225c(str, obj));
    }

    /* renamed from: b */
    public String mo24096b(String str) throws IOException {
        return mo24097b(str, null);
    }

    /* renamed from: b */
    public String mo24097b(String str, Object obj) throws IOException {
        return mo24094a(str, obj).mo16127h().string();
    }

    /* renamed from: a */
    public Request mo24089a(Request abVar, OkRequestHandler eVar) {
        OkHttpService.m10255a(abVar, eVar);
        return abVar;
    }

    /* renamed from: a */
    public Request mo24090a(String str, OkRequestHandler eVar) {
        return mo24091a(str, eVar, null);
    }

    /* renamed from: a */
    public Request mo24091a(String str, OkRequestHandler eVar, Object obj) {
        Request c = m10225c(str, obj);
        mo24089a(c, eVar);
        return c;
    }

    /* renamed from: b */
    public Request mo24095b(String str, OkRequestHandler eVar, Object obj) {
        Request d = m10226d(str, obj);
        mo24089a(d, eVar);
        return d;
    }

    /* renamed from: a */
    private OkHttpClient m10224a() {
        return OkHttpService.m10252a().mo24102b();
    }
}
