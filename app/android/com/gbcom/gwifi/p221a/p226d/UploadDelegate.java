package com.gbcom.gwifi.p221a.p226d;

import com.p136b.p137a.Gson;
import java.net.URLConnection;
import java.p456io.File;
import java.p456io.IOException;
import java.util.Map;
import p041c.Call;
import p041c.Callback;
import p041c.Headers;
import p041c.MediaType;
import p041c.MultipartBody;
import p041c.OkHttpClient;
import p041c.Request;
import p041c.RequestBody;
import p041c.Response;
import p419io.netty.handler.codec.http.multipart.HttpPostBodyUtil;

/* renamed from: com.gbcom.gwifi.a.d.j */
public class UploadDelegate {
    /* renamed from: a */
    private void m10323a(Request abVar, final OkRequestHandler eVar) {
        m10321a().mo16200a(abVar).mo16072a(new Callback() {
            /* class com.gbcom.gwifi.p221a.p226d.UploadDelegate.C24521 */

            @Override // p041c.Callback
            /* renamed from: a */
            public void mo16202a(Call eVar, IOException iOException) {
                OkHttpService.m10256a(eVar.mo16070a(), iOException, eVar);
            }

            @Override // p041c.Callback
            /* renamed from: a */
            public void mo16201a(Call eVar, Response adVar) throws IOException {
                String string = adVar.mo16127h().string();
                if (eVar.mType == String.class) {
                    OkHttpService.m10258a(string, eVar.mo16070a(), eVar);
                } else {
                    OkHttpService.m10258a(new Gson().mo21589a(string, eVar.mType), eVar.mo16070a(), eVar);
                }
            }
        });
    }

    /* renamed from: a */
    public Response mo24144a(String str, String str2, File file, OkRequestHandler eVar, Object obj) throws IOException {
        return mo24146a(str, new String[]{str2}, new File[]{file}, (Param[]) null, eVar, obj);
    }

    /* renamed from: a */
    public Response mo24146a(String str, String[] strArr, File[] fileArr, Param[] fVarArr, OkRequestHandler eVar, Object obj) throws IOException {
        return m10321a().mo16200a(m10320a(str, fileArr, strArr, fVarArr, eVar, obj)).mo16074b();
    }

    /* renamed from: a */
    public Response mo24145a(String str, String str2, File file, Param[] fVarArr, OkRequestHandler eVar, Object obj) throws IOException {
        return mo24146a(str, new String[]{str2}, new File[]{file}, fVarArr, eVar, obj);
    }

    /* renamed from: b */
    public void mo24149b(String str, String[] strArr, File[] fileArr, Param[] fVarArr, OkRequestHandler eVar, Object obj) {
        m10323a(m10320a(str, fileArr, strArr, fVarArr, eVar, obj), eVar);
    }

    /* renamed from: b */
    public void mo24147b(String str, String str2, File file, OkRequestHandler eVar, Object obj) throws IOException {
        mo24149b(str, new String[]{str2}, new File[]{file}, (Param[]) null, eVar, obj);
    }

    /* renamed from: b */
    public void mo24148b(String str, String str2, File file, Param[] fVarArr, OkRequestHandler eVar, Object obj) {
        mo24149b(str, new String[]{str2}, new File[]{file}, fVarArr, eVar, obj);
    }

    /* renamed from: a */
    public Request mo24143a(String str, Map<String, String> map, Map<String, File> map2, OkRequestHandler eVar, Object obj) {
        Request b = m10325b(str, map, map2, eVar, obj);
        m10323a(b, eVar);
        return b;
    }

    /* renamed from: a */
    private Request m10320a(String str, File[] fileArr, String[] strArr, Param[] fVarArr, OkRequestHandler eVar, Object obj) {
        Param[] a = m10324a(fVarArr);
        MultipartBody.C1265a a2 = new MultipartBody.C1265a().mo16436a(MultipartBody.f2383e);
        for (Param fVar : a) {
            a2.mo16435a(Headers.m2543a("Content-Disposition", "form-data; name=\"" + fVar.f8570a + "\""), RequestBody.m2255a((MediaType) null, (String) fVar.f8571b));
        }
        if (fileArr != null) {
            for (int i = 0; i < fileArr.length; i++) {
                File file = fileArr[i];
                String name = file.getName();
                a2.mo16435a(Headers.m2543a("Content-Disposition", "form-data; name=\"" + strArr[i] + "\"; filename=\"" + name + "\""), RequestBody.m2254a(MediaType.m2874a(m10322a(name)), file));
            }
        }
        return new Request.C1190a().mo16101a(str).mo16100a(obj).mo16096a((RequestBody) new ProgressRequestBody(a2.mo16440a(), null, eVar)).mo16110c();
    }

    /* renamed from: b */
    private Request m10325b(String str, Map<String, String> map, Map<String, File> map2, OkRequestHandler eVar, Object obj) {
        MultipartBody.C1265a a = new MultipartBody.C1265a().mo16436a(MultipartBody.f2383e);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            a.mo16435a(Headers.m2543a("Content-Disposition", "form-data; name=\"" + entry.getKey() + "\""), RequestBody.m2255a((MediaType) null, entry.getValue()));
        }
        if (map2 != null) {
            for (Map.Entry<String, File> entry2 : map2.entrySet()) {
                File value = entry2.getValue();
                String name = value.getName();
                a.mo16435a(Headers.m2543a("Content-Disposition", "form-data; name=\"" + entry2.getKey() + "\"; filename=\"" + name + "\""), RequestBody.m2254a(MediaType.m2874a(m10322a(name)), value));
            }
        }
        return new Request.C1190a().mo16101a(str).mo16100a(obj).mo16096a((RequestBody) new ProgressRequestBody(a.mo16440a(), null, eVar)).mo16110c();
    }

    /* renamed from: a */
    private String m10322a(String str) {
        String contentTypeFor = URLConnection.getFileNameMap().getContentTypeFor(str);
        if (contentTypeFor == null) {
            return HttpPostBodyUtil.DEFAULT_BINARY_CONTENT_TYPE;
        }
        return contentTypeFor;
    }

    /* renamed from: a */
    private Param[] m10324a(Param[] fVarArr) {
        if (fVarArr == null) {
            return new Param[0];
        }
        return fVarArr;
    }

    /* renamed from: a */
    private OkHttpClient m10321a() {
        return OkHttpService.m10252a().mo24102b();
    }

    /* renamed from: b */
    private OkHttpClient m10326b() {
        return OkHttpService.m10252a().mo24104c();
    }
}
