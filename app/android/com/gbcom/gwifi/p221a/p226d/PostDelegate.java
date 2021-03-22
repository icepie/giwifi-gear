package com.gbcom.gwifi.p221a.p226d;

import com.gbcom.gwifi.util.SystemUtil;
import java.p456io.File;
import java.p456io.IOException;
import java.util.HashMap;
import java.util.Map;
import p041c.FormBody;
import p041c.MediaType;
import p041c.OkHttpClient;
import p041c.Request;
import p041c.RequestBody;
import p041c.Response;

/* renamed from: com.gbcom.gwifi.a.d.g */
public class PostDelegate {

    /* renamed from: a */
    private final MediaType f8572a = MediaType.m2874a("application/octet-stream;charset=utf-8");

    /* renamed from: b */
    private final MediaType f8573b = MediaType.m2874a("text/plain;charset=utf-8");

    /* renamed from: a */
    public Response mo24135a(String str, Param[] fVarArr) throws IOException {
        return mo24136a(str, fVarArr, (Object) null);
    }

    /* renamed from: a */
    public Response mo24136a(String str, Param[] fVarArr, Object obj) throws IOException {
        return m10281a().mo16200a(m10284c(str, fVarArr, obj)).mo16074b();
    }

    /* renamed from: b */
    public String mo24140b(String str, Param[] fVarArr) throws IOException {
        return mo24141b(str, fVarArr, (Object) null);
    }

    /* renamed from: b */
    public String mo24141b(String str, Param[] fVarArr, Object obj) throws IOException {
        return mo24136a(str, fVarArr, obj).mo16127h().string();
    }

    /* renamed from: a */
    public String mo24137a(String str, HashMap<String, Object> hashMap, Object obj) throws IOException {
        return mo24136a(str, m10282a(hashMap), obj).mo16127h().string();
    }

    /* renamed from: a */
    public Request mo24122a(String str, HashMap<String, Object> hashMap, OkRequestHandler eVar) {
        return mo24123a(str, hashMap, eVar, (Object) null);
    }

    /* renamed from: a */
    public Request mo24123a(String str, HashMap<String, Object> hashMap, OkRequestHandler eVar, Object obj) {
        return mo24128a(str, m10282a(hashMap), eVar, obj);
    }

    /* renamed from: a */
    public Request mo24127a(String str, Param[] fVarArr, OkRequestHandler eVar) {
        return mo24128a(str, fVarArr, eVar, (Object) null);
    }

    /* renamed from: a */
    public Request mo24128a(String str, Param[] fVarArr, OkRequestHandler eVar, Object obj) {
        Request c = m10284c(str, fVarArr, obj);
        OkHttpService.m10255a(c, eVar);
        return c;
    }

    /* renamed from: a */
    public Response mo24131a(String str, String str2) throws IOException {
        return mo24132a(str, str2, (Object) null);
    }

    /* renamed from: a */
    public Response mo24132a(String str, String str2, Object obj) throws IOException {
        return m10281a().mo16200a(m10280a(str, RequestBody.m2255a(this.f8573b, str2), obj)).mo16074b();
    }

    /* renamed from: a */
    public Response mo24129a(String str, File file) throws IOException {
        return mo24130a(str, file, (Object) null);
    }

    /* renamed from: a */
    public Response mo24130a(String str, File file, Object obj) throws IOException {
        return m10281a().mo16200a(m10280a(str, RequestBody.m2254a(this.f8572a, file), obj)).mo16074b();
    }

    /* renamed from: a */
    public Response mo24133a(String str, byte[] bArr) throws IOException {
        return mo24134a(str, bArr, (Object) null);
    }

    /* renamed from: a */
    public Response mo24134a(String str, byte[] bArr, Object obj) throws IOException {
        return m10281a().mo16200a(m10280a(str, RequestBody.m2257a(this.f8572a, bArr), obj)).mo16074b();
    }

    /* renamed from: a */
    public Request mo24120a(String str, String str2, OkRequestHandler eVar) {
        return mo24121a(str, str2, eVar, (Object) null);
    }

    /* renamed from: a */
    public Request mo24121a(String str, String str2, OkRequestHandler eVar, Object obj) {
        return mo24119a(str, str2, MediaType.m2874a("text/plain;charset=utf-8"), eVar, obj);
    }

    /* renamed from: b */
    public Request mo24139b(String str, String str2, OkRequestHandler eVar, Object obj) {
        return mo24138b(str, str2, MediaType.m2874a("text/plain;charset=utf-8"), eVar, obj);
    }

    /* renamed from: a */
    public Request mo24125a(String str, byte[] bArr, OkRequestHandler eVar) {
        return mo24126a(str, bArr, eVar, (Object) null);
    }

    /* renamed from: a */
    public Request mo24126a(String str, byte[] bArr, OkRequestHandler eVar, Object obj) {
        return mo24124a(str, bArr, MediaType.m2874a("application/octet-stream;charset=utf-8"), eVar, obj);
    }

    /* renamed from: a */
    public Request mo24117a(String str, File file, OkRequestHandler eVar) {
        return mo24118a(str, file, eVar, (Object) null);
    }

    /* renamed from: a */
    public Request mo24118a(String str, File file, OkRequestHandler eVar, Object obj) {
        return mo24116a(str, file, MediaType.m2874a("application/octet-stream;charset=utf-8"), eVar, obj);
    }

    /* renamed from: a */
    public Request mo24119a(String str, String str2, MediaType wVar, OkRequestHandler eVar, Object obj) {
        Request a = m10280a(str, RequestBody.m2255a(wVar, str2), obj);
        OkHttpService.m10255a(a, eVar);
        return a;
    }

    /* renamed from: b */
    public Request mo24138b(String str, String str2, MediaType wVar, OkRequestHandler eVar, Object obj) {
        Request b = m10283b(str, RequestBody.m2255a(wVar, str2), obj);
        OkHttpService.m10255a(b, eVar);
        return b;
    }

    /* renamed from: a */
    public Request mo24124a(String str, byte[] bArr, MediaType wVar, OkRequestHandler eVar, Object obj) {
        Request a = m10280a(str, RequestBody.m2257a(wVar, bArr), obj);
        OkHttpService.m10255a(a, eVar);
        return a;
    }

    /* renamed from: a */
    public Request mo24116a(String str, File file, MediaType wVar, OkRequestHandler eVar, Object obj) {
        Request a = m10280a(str, RequestBody.m2254a(wVar, file), obj);
        OkHttpService.m10255a(a, eVar);
        return a;
    }

    /* renamed from: a */
    private Request m10280a(String str, RequestBody acVar, Object obj) {
        Request.C1190a a = new Request.C1190a().mo16101a(str).mo16096a(acVar);
        if (obj != null) {
            a.mo16100a(obj);
        }
        return a.mo16110c();
    }

    /* renamed from: b */
    private Request m10283b(String str, RequestBody acVar, Object obj) {
        Request.C1190a a = new Request.C1190a().mo16107b("User-Agent").mo16108b("User-Agent", SystemUtil.m14526c()).mo16101a(str).mo16096a(acVar);
        if (obj != null) {
            a.mo16100a(obj);
        }
        return a.mo16110c();
    }

    /* renamed from: c */
    private Request m10284c(String str, Param[] fVarArr, Object obj) {
        if (fVarArr == null) {
            fVarArr = new Param[0];
        }
        FormBody.C1219a aVar = new FormBody.C1219a();
        for (Param fVar : fVarArr) {
            aVar.mo16275a(fVar.f8570a, (String) fVar.f8571b);
        }
        FormBody a = aVar.mo16276a();
        Request.C1190a aVar2 = new Request.C1190a();
        aVar2.mo16101a(str).mo16096a((RequestBody) a);
        if (obj != null) {
            aVar2.mo16100a(obj);
        }
        return aVar2.mo16110c();
    }

    /* renamed from: a */
    private Param[] m10282a(Map<String, Object> map) {
        if (map == null) {
            return new Param[0];
        }
        Param[] fVarArr = new Param[map.size()];
        int i = 0;
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            fVarArr[i] = new Param(entry.getKey(), entry.getValue());
            i++;
        }
        return fVarArr;
    }

    /* renamed from: a */
    private OkHttpClient m10281a() {
        return OkHttpService.m10252a().mo24102b();
    }
}
