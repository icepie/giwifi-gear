package com.gbcom.gwifi.p221a.p223b.p224a;

import com.gbcom.gwifi.p221a.p223b.FormInputStream;
import com.gbcom.gwifi.p221a.p223b.HttpRequest;
import com.gbcom.gwifi.p221a.p225c.BasicRequest;
import java.p456io.InputStream;
import java.util.List;
import org.apache.http.NameValuePair;

/* renamed from: com.gbcom.gwifi.a.b.a.a */
public class BasicHttpRequest extends BasicRequest implements HttpRequest {

    /* renamed from: a */
    public static final String f8495a = "DELETE";

    /* renamed from: b */
    public static final String f8496b = "GET";

    /* renamed from: c */
    public static final String f8497c = "POST";

    /* renamed from: d */
    public static final String f8498d = "PUT";

    /* renamed from: e */
    private List<NameValuePair> f8499e;

    /* renamed from: f */
    private InputStream f8500f;

    /* renamed from: g */
    private String f8501g;

    public BasicHttpRequest(String str, String str2, InputStream inputStream) {
        super(str);
        this.f8501g = str2;
        this.f8500f = inputStream;
    }

    /* renamed from: a */
    public static HttpRequest m10185a(String str) {
        return new BasicHttpRequest(str, "GET", null);
    }

    /* renamed from: a */
    public static HttpRequest m10186a(String str, Object obj) {
        return new BasicHttpRequest(str, "POST", new FormInputStream(obj));
    }

    @Override // com.gbcom.gwifi.p221a.p223b.HttpRequest
    /* renamed from: b */
    public List<NameValuePair> mo24067b() {
        return this.f8499e;
    }

    @Override // com.gbcom.gwifi.p221a.p223b.HttpRequest
    /* renamed from: c */
    public InputStream mo24068c() {
        return this.f8500f;
    }

    @Override // com.gbcom.gwifi.p221a.p223b.HttpRequest
    /* renamed from: d */
    public String mo24069d() {
        return this.f8501g;
    }

    @Override // com.gbcom.gwifi.p221a.p225c.BasicRequest
    public String toString() {
        return this.f8501g + ": " + super.toString();
    }
}
