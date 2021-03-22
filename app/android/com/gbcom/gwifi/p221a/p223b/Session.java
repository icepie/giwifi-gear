package com.gbcom.gwifi.p221a.p223b;

import com.gbcom.gwifi.p221a.RequestHandler;

/* renamed from: com.gbcom.gwifi.a.b.j */
public class Session {

    /* renamed from: a */
    public RequestHandler<HttpRequest, HttpResponse> f8519a;

    /* renamed from: b */
    public HttpRequest f8520b;

    /* renamed from: c */
    public int f8521c = 2;

    public Session() {
    }

    public Session(HttpRequest cVar, RequestHandler<HttpRequest, HttpResponse> cVar2) {
        this.f8520b = cVar;
        this.f8519a = cVar2;
    }
}
