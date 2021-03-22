package com.gbcom.gwifi.p221a.p226d;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import com.gbcom.gwifi.util.C3467s;
import com.p136b.p137a.Gson;
import com.p136b.p137a.JsonParseException;
import java.p456io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import p041c.Call;
import p041c.Callback;
import p041c.OkHttpClient;
import p041c.Request;
import p041c.Response;

/* renamed from: com.gbcom.gwifi.a.d.d */
public class OkHttpService {

    /* renamed from: a */
    public static final OkRequestHandler<String> f8544a = new OkRequestHandler<String>() {
        /* class com.gbcom.gwifi.p221a.p226d.OkHttpService.C24463 */

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestFailed(Request abVar, Exception exc) {
        }

        /* renamed from: a */
        public void onRequestFinish(Request abVar, String str) {
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestProgress(Request abVar, long j, long j2) {
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestStart(Request abVar) {
        }
    };

    /* renamed from: b */
    private static final int f8545b = 10485760;

    /* renamed from: c */
    private static final String f8546c = "OkHttpClientManager";

    /* renamed from: d */
    private static OkHttpService f8547d = new OkHttpService();

    /* renamed from: e */
    private static OkHttpClient f8548e;

    /* renamed from: f */
    private static Handler f8549f;

    /* renamed from: g */
    private static Handler f8550g;

    /* renamed from: h */
    private static Gson f8551h;

    /* renamed from: i */
    private HttpsDelegate f8552i = new HttpsDelegate();

    /* renamed from: j */
    private DownloadDelegate f8553j = new DownloadDelegate();

    /* renamed from: k */
    private GetDelegate f8554k = new GetDelegate();

    /* renamed from: l */
    private UploadDelegate f8555l = new UploadDelegate();

    /* renamed from: m */
    private PostDelegate f8556m = new PostDelegate();

    private OkHttpService() {
        f8548e = new OkHttpClient.C1268a().mo16466a(5, TimeUnit.SECONDS).mo16493c();
        f8549f = new Handler(Looper.getMainLooper());
        HandlerThread handlerThread = new HandlerThread("noUiHandlerThread");
        handlerThread.start();
        f8550g = new Handler(handlerThread.getLooper());
        f8551h = new Gson();
    }

    /* renamed from: a */
    public static OkHttpService m10252a() {
        return f8547d;
    }

    /* renamed from: b */
    public OkHttpClient mo24102b() {
        return f8548e;
    }

    /* renamed from: c */
    public OkHttpClient mo24104c() {
        return f8548e;
    }

    /* renamed from: d */
    public Handler mo24105d() {
        return f8549f;
    }

    /* renamed from: e */
    public Gson mo24106e() {
        return f8551h;
    }

    /* renamed from: f */
    public GetDelegate mo24107f() {
        return this.f8554k;
    }

    /* renamed from: g */
    public PostDelegate mo24108g() {
        return this.f8556m;
    }

    /* renamed from: m */
    private HttpsDelegate m10266m() {
        return this.f8552i;
    }

    /* renamed from: n */
    private DownloadDelegate m10267n() {
        return this.f8553j;
    }

    /* renamed from: o */
    private UploadDelegate m10268o() {
        return this.f8555l;
    }

    /* renamed from: h */
    public static DownloadDelegate m10262h() {
        return m10252a().m10267n();
    }

    /* renamed from: i */
    public static UploadDelegate m10263i() {
        return m10252a().m10268o();
    }

    /* renamed from: j */
    public static HttpsDelegate m10264j() {
        return m10252a().m10266m();
    }

    /* renamed from: a */
    public static Request m10243a(String str, OkRequestHandler eVar) {
        return m10252a().mo24107f().mo24091a(str, eVar, null);
    }

    /* renamed from: a */
    public static Request m10244a(String str, OkRequestHandler eVar, Object obj) {
        return m10252a().mo24107f().mo24091a(str, eVar, obj);
    }

    /* renamed from: b */
    public static Request m10259b(String str, OkRequestHandler eVar, Object obj) {
        return m10252a().mo24107f().mo24095b(str, eVar, obj);
    }

    /* renamed from: a */
    public static Response m10251a(String str) throws IOException {
        return m10252a().mo24107f().mo24093a(str);
    }

    /* renamed from: a */
    public static Request m10249a(String str, Param[] fVarArr, OkRequestHandler eVar) {
        return m10252a().mo24108g().mo24128a(str, fVarArr, eVar, (Object) null);
    }

    /* renamed from: a */
    public static Request m10247a(String str, HashMap<String, Object> hashMap, OkRequestHandler eVar) {
        return m10252a().mo24108g().mo24123a(str, hashMap, eVar, (Object) null);
    }

    /* renamed from: a */
    public static Request m10245a(String str, String str2, OkRequestHandler eVar) {
        return m10252a().mo24108g().mo24121a(str, str2, eVar, (Object) null);
    }

    /* renamed from: a */
    public static Request m10250a(String str, Param[] fVarArr, OkRequestHandler eVar, Object obj) {
        return m10252a().mo24108g().mo24128a(str, fVarArr, eVar, obj);
    }

    /* renamed from: a */
    public static Request m10248a(String str, HashMap<String, Object> hashMap, OkRequestHandler eVar, Object obj) {
        if (str == null || C3467s.m14513e(str)) {
            return null;
        }
        return m10252a().mo24108g().mo24123a(str, hashMap, eVar, obj);
    }

    /* renamed from: a */
    public static Request m10246a(String str, String str2, OkRequestHandler eVar, Object obj) {
        if (str == null || C3467s.m14513e(str)) {
            return null;
        }
        return m10252a().mo24108g().mo24121a(str, str2, eVar, obj);
    }

    /* renamed from: b */
    public static Request m10260b(String str, String str2, OkRequestHandler eVar, Object obj) {
        return m10252a().mo24108g().mo24139b(str, str2, eVar, obj);
    }

    /* renamed from: a */
    public static String m10253a(String str, HashMap<String, Object> hashMap, Object obj) throws IOException {
        return m10252a().mo24108g().mo24137a(str, hashMap, obj);
    }

    /* renamed from: a */
    public static void m10257a(Object obj) {
        m10252a().m10261c(obj);
    }

    /* renamed from: c */
    private void m10261c(final Object obj) {
        f8550g.post(new Runnable() {
            /* class com.gbcom.gwifi.p221a.p226d.OkHttpService.RunnableC24441 */

            @Override // java.lang.Runnable
            public void run() {
                OkHttpService.this.mo24103b(obj);
            }
        });
    }

    /* renamed from: b */
    public void mo24103b(Object obj) {
        if (obj != null) {
            synchronized (f8548e.mo16460t().getClass()) {
                for (Call eVar : f8548e.mo16460t().mo16265e()) {
                    if (obj.equals(eVar.mo16070a().mo16091e())) {
                        eVar.mo16075c();
                    }
                }
                for (Call eVar2 : f8548e.mo16460t().mo16266f()) {
                    if (obj.equals(eVar2.mo16070a().mo16091e())) {
                        eVar2.mo16075c();
                    }
                }
            }
        }
    }

    /* renamed from: k */
    public static OkHttpClient m10265k() {
        return m10252a().mo24109l();
    }

    /* renamed from: l */
    public OkHttpClient mo24109l() {
        return f8548e;
    }

    /* renamed from: a */
    public static void m10255a(Request abVar, final OkRequestHandler eVar) {
        if (eVar == null) {
            eVar = f8544a;
        }
        eVar.onRequestStart(abVar);
        f8548e.mo16200a(abVar).mo16072a(new Callback() {
            /* class com.gbcom.gwifi.p221a.p226d.OkHttpService.C24452 */

            @Override // p041c.Callback
            /* renamed from: a */
            public void mo16201a(Call eVar, Response adVar) throws IOException {
                try {
                    if (eVar.mType == String.class) {
                        OkHttpService.m10258a(adVar.mo16127h().string(), eVar.mo16070a(), eVar);
                    } else {
                        OkHttpService.m10258a(adVar.mo16127h().bytes(), eVar.mo16070a(), eVar);
                    }
                } catch (IOException e) {
                    OkHttpService.m10256a(adVar.mo16116a(), e, eVar);
                } catch (JsonParseException e2) {
                    OkHttpService.m10256a(adVar.mo16116a(), e2, eVar);
                }
            }

            @Override // p041c.Callback
            /* renamed from: a */
            public void mo16202a(Call eVar, IOException iOException) {
                OkHttpService.m10256a(eVar.mo16070a(), iOException, eVar);
            }
        });
    }

    /* renamed from: a */
    public static void m10256a(final Request abVar, final Exception exc, final OkRequestHandler eVar) {
        f8549f.post(new Runnable() {
            /* class com.gbcom.gwifi.p221a.p226d.OkHttpService.RunnableC24474 */

            @Override // java.lang.Runnable
            public void run() {
                eVar.onRequestFailed(abVar, exc);
                eVar.onAfter();
            }
        });
    }

    /* renamed from: a */
    public static void m10258a(final Object obj, final Request abVar, final OkRequestHandler eVar) {
        f8549f.post(new Runnable() {
            /* class com.gbcom.gwifi.p221a.p226d.OkHttpService.RunnableC24485 */

            @Override // java.lang.Runnable
            public void run() {
                eVar.onRequestFinish(abVar, obj);
                eVar.onAfter();
            }
        });
    }

    /* renamed from: a */
    public static void m10254a(final Request abVar, final long j, final long j2, final OkRequestHandler eVar) {
        f8549f.post(new Runnable() {
            /* class com.gbcom.gwifi.p221a.p226d.OkHttpService.RunnableC24496 */

            @Override // java.lang.Runnable
            public void run() {
                eVar.onRequestProgress(abVar, j, j2);
            }
        });
    }
}
