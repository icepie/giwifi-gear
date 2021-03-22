package com.gbcom.gwifi.functions.test;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import com.gbcom.gwifi.p221a.RequestHandler;
import com.gbcom.gwifi.p221a.p223b.HttpRequest;
import com.gbcom.gwifi.p221a.p223b.HttpResponse;
import com.gbcom.gwifi.p221a.p223b.HttpService;
import com.gbcom.gwifi.p221a.p223b.NetworkInfoHelper;
import java.util.HashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;

/* renamed from: com.gbcom.gwifi.functions.test.b */
public class DeviceTestHttpService implements HttpService {

    /* renamed from: h */
    private static final String f12640h = "http";

    /* renamed from: k */
    private static final int f12641k = 0;

    /* renamed from: l */
    private static final int f12642l = 1;

    /* renamed from: a */
    protected Context f12643a;

    /* renamed from: b */
    protected Executor f12644b;

    /* renamed from: c */
    protected final int f12645c = 5000;

    /* renamed from: d */
    protected final ConcurrentLinkedQueue f12646d = new ConcurrentLinkedQueue();

    /* renamed from: e */
    protected NetworkInfoHelper f12647e;

    /* renamed from: f */
    protected int f12648f;

    /* renamed from: g */
    protected int f12649g;

    /* renamed from: i */
    private RequestHandler<HttpRequest, HttpResponse> f12650i;

    /* renamed from: j */
    private Handler f12651j;

    public DeviceTestHttpService(Context context, Executor executor) {
        this.f12643a = context;
        this.f12644b = executor;
        this.f12647e = new NetworkInfoHelper(this.f12643a);
        this.f12651j = new Handler(context.getMainLooper()) {
            /* class com.gbcom.gwifi.functions.test.DeviceTestHttpService.HandlerC33481 */

            public void handleMessage(Message message) {
                super.handleMessage(message);
                Object[] objArr = (Object[]) message.obj;
                switch (message.what) {
                    case 0:
                        DeviceTestHttpService.this.f12650i.mo24084b((HttpRequest) objArr[0], (HttpResponse) objArr[1]);
                        return;
                    case 1:
                        DeviceTestHttpService.this.f12650i.mo24083a((HttpRequest) objArr[0], (HttpResponse) objArr[1]);
                        return;
                    default:
                        return;
                }
            }
        };
    }

    /* renamed from: a */
    public void mo24037a(HttpRequest cVar, RequestHandler<HttpRequest, HttpResponse> cVar2, boolean z) {
    }

    /* renamed from: a */
    public void mo24036a(HttpRequest cVar, RequestHandler<HttpRequest, HttpResponse> cVar2) {
    }

    /* renamed from: a */
    public void mo27368a(String str, HashMap<String, Object> hashMap, RequestHandler<HttpRequest, HttpResponse> cVar, HttpRequest cVar2) {
        this.f12650i = cVar;
    }

    /* renamed from: a */
    public HttpResponse mo24035a(HttpRequest cVar) {
        return null;
    }

    /* renamed from: a */
    private void m13713a(HttpRequest cVar, Object obj) {
        HttpResponse dVar = (HttpResponse) obj;
        Object[] objArr = {cVar, dVar};
        if (dVar.mo24086b() == null) {
            this.f12651j.sendMessage(this.f12651j.obtainMessage(0, objArr));
        } else {
            this.f12651j.sendMessage(this.f12651j.obtainMessage(1, objArr));
        }
    }
}
