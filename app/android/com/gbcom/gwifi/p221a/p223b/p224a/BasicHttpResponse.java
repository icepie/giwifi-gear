package com.gbcom.gwifi.p221a.p223b.p224a;

import com.gbcom.gwifi.p221a.p223b.HttpResponse;
import com.gbcom.gwifi.p221a.p225c.BasicResponse;
import java.util.List;
import org.apache.http.NameValuePair;

/* renamed from: com.gbcom.gwifi.a.b.a.b */
public class BasicHttpResponse extends BasicResponse implements HttpResponse {

    /* renamed from: b */
    private List<NameValuePair> f8502b;

    /* renamed from: c */
    private int f8503c;

    public BasicHttpResponse(int i, Object obj, List<NameValuePair> list, Object obj2) {
        super(obj, obj2);
        this.f8503c = i;
        this.f8502b = list;
    }

    @Override // com.gbcom.gwifi.p221a.p223b.HttpResponse
    /* renamed from: c */
    public List<NameValuePair> mo24070c() {
        return this.f8502b;
    }

    @Override // com.gbcom.gwifi.p221a.p223b.HttpResponse
    /* renamed from: d */
    public int mo24071d() {
        return this.f8503c;
    }
}
