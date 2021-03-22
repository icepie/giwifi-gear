package com.gbcom.gwifi.p221a.p223b;

import android.content.Context;
import android.os.SystemClock;
import com.gbcom.gwifi.p221a.RequestHandler;
import com.gbcom.gwifi.p221a.p223b.WatchedInputStream;
import com.gbcom.gwifi.util.Log;
import com.gbcom.gwifi.util.MyTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import p419io.netty.util.internal.StringUtil;

/* renamed from: com.gbcom.gwifi.a.b.a */
public class DefaultHttpService implements HttpService {

    /* renamed from: g */
    private static final String f8477g = "http";

    /* renamed from: a */
    protected Context f8478a;

    /* renamed from: b */
    protected Executor f8479b;

    /* renamed from: c */
    protected final int f8480c = 5000;

    /* renamed from: d */
    protected final ConcurrentLinkedQueue f8481d = new ConcurrentLinkedQueue();

    /* renamed from: e */
    protected NetworkInfoHelper f8482e;

    /* renamed from: f */
    protected final ConcurrentHashMap<HttpRequest, C2437a> f8483f = new ConcurrentHashMap<>();

    public DefaultHttpService(Context context, Executor executor) {
        this.f8478a = context;
        this.f8479b = executor;
        this.f8482e = new NetworkInfoHelper(this.f8478a);
    }

    /* renamed from: a */
    public void mo24037a(HttpRequest cVar, RequestHandler<HttpRequest, HttpResponse> cVar2, boolean z) {
        C2437a aVar = this.f8483f.get(cVar);
        if (aVar != null && aVar.f8486c == cVar2) {
            this.f8483f.remove(cVar, aVar);
            aVar.mo28156a(z);
        }
    }

    /* renamed from: a */
    public void mo24057a() {
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public C2437a mo24055a(HttpRequest cVar, RequestHandler<HttpRequest, HttpResponse> cVar2) {
        return new C2437a(cVar, cVar2);
    }

    /* renamed from: b */
    public void mo24036a(HttpRequest cVar, RequestHandler<HttpRequest, HttpResponse> cVar2) {
        C2437a a = mo24055a(cVar, cVar2);
        if (this.f8483f.putIfAbsent(cVar, a) != null) {
            Log.m14403e("http", "DefaultHttpService->exec:cannot exec duplicate request (same instance)");
        } else {
            a.mo28154a(this.f8479b, new Void[0]);
        }
    }

    /* renamed from: a */
    public HttpResponse mo24035a(HttpRequest cVar) {
        return (HttpResponse) mo24055a(cVar, (RequestHandler<HttpRequest, HttpResponse>) null).mo24061a(new Object[0]);
    }

    /* renamed from: b */
    public int mo24059b() {
        return this.f8483f.size();
    }

    /* access modifiers changed from: protected */
    /* renamed from: com.gbcom.gwifi.a.b.a$a */
    /* compiled from: DefaultHttpService */
    public class C2437a extends MyTask implements WatchedInputStream.AbstractC2439a {

        /* renamed from: a */
        protected int f8484a;

        /* renamed from: b */
        protected int f8485b;

        /* renamed from: c */
        protected final RequestHandler<HttpRequest, HttpResponse> f8486c;

        /* renamed from: d */
        protected boolean f8487d;

        /* renamed from: e */
        protected long f8488e;

        /* renamed from: f */
        protected int f8489f;

        /* renamed from: g */
        protected final HttpRequest f8490g;

        /* renamed from: h */
        protected int f8491h;

        /* renamed from: i */
        protected long f8492i;

        /* renamed from: j */
        protected int f8493j;

        public C2437a(HttpRequest cVar, RequestHandler<HttpRequest, HttpResponse> cVar2) {
            this.f8490g = cVar;
            this.f8486c = cVar2;
        }

        @Override // com.gbcom.gwifi.util.MyTask
        /* renamed from: a */
        public Object mo24061a(Object[] objArr) {
            return null;
        }

        @Override // com.gbcom.gwifi.p221a.p223b.WatchedInputStream.AbstractC2439a
        /* renamed from: a */
        public void mo24063a(int i) {
            if (this.f8486c != null && this.f8487d) {
                this.f8491h += i;
                if (this.f8491h < this.f8484a) {
                    long elapsedRealtime = SystemClock.elapsedRealtime();
                    if (elapsedRealtime - this.f8488e > 200) {
                        mo28159c((Object[]) new Void[0]);
                        this.f8488e = elapsedRealtime;
                        return;
                    }
                    return;
                }
                mo28159c((Object[]) new Void[0]);
            }
        }

        /* access modifiers changed from: protected */
        @Override // com.gbcom.gwifi.util.MyTask
        /* renamed from: a */
        public void mo24062a() {
            long elapsedRealtime = SystemClock.elapsedRealtime() - this.f8492i;
            StringBuilder sb = new StringBuilder();
            sb.append("abort (");
            sb.append(this.f8490g.mo24069d()).append(StringUtil.COMMA);
            sb.append(this.f8493j).append(StringUtil.COMMA);
            sb.append(elapsedRealtime).append("ms");
            sb.append(") ").append(this.f8490g.mo24054a());
            Log.m14398b("http", sb.toString());
            if (this.f8490g.mo24068c() instanceof FormInputStream) {
                Log.m14398b("http", ((FormInputStream) this.f8490g.mo24068c()).toString());
            }
        }

        /* access modifiers changed from: protected */
        @Override // com.gbcom.gwifi.util.MyTask
        /* renamed from: a */
        public void mo24064a(Object obj) {
            HttpResponse dVar = (HttpResponse) obj;
            if (DefaultHttpService.this.f8483f.remove(this.f8490g, this)) {
                if (dVar.mo24086b() == null) {
                    this.f8486c.mo24084b(this.f8490g, dVar);
                } else {
                    this.f8486c.mo24083a(this.f8490g, dVar);
                }
                long elapsedRealtime = SystemClock.elapsedRealtime() - this.f8492i;
                StringBuilder sb = new StringBuilder();
                if (dVar.mo24086b() == null) {
                    sb.append("fail (");
                } else {
                    sb.append("finish (");
                }
                sb.append(this.f8490g.mo24069d()).append(StringUtil.COMMA);
                sb.append(this.f8493j).append(StringUtil.COMMA);
                sb.append(elapsedRealtime).append("ms");
                sb.append(") ").append(this.f8490g.mo24054a());
                Log.m14398b("http", "DefaultHttpService:" + sb.toString());
                if (this.f8490g.mo24068c() instanceof FormInputStream) {
                    Log.m14398b("http", ((FormInputStream) this.f8490g.mo24068c()).toString());
                }
                if (dVar.mo24086b() == null) {
                    Log.m14398b("http", dVar.mo24085a().toString());
                }
            }
        }

        /* access modifiers changed from: protected */
        @Override // com.gbcom.gwifi.util.MyTask
        /* renamed from: b */
        public void mo24065b() {
            this.f8486c.mo24081a(this.f8490g);
            this.f8492i = SystemClock.elapsedRealtime();
        }

        /* access modifiers changed from: protected */
        @Override // com.gbcom.gwifi.util.MyTask
        /* renamed from: b */
        public void mo24066b(Object[] objArr) {
            if (!this.f8487d) {
                this.f8486c.mo24082a(this.f8490g, (long) this.f8489f, (long) this.f8485b);
            } else {
                this.f8486c.mo24082a(this.f8490g, (long) this.f8491h, (long) this.f8484a);
            }
        }
    }
}
