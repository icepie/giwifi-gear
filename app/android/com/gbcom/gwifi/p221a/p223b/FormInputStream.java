package com.gbcom.gwifi.p221a.p223b;

import com.gbcom.gwifi.util.JsonUtil;
import java.nio.charset.UnsupportedCharsetException;
import java.p456io.ByteArrayInputStream;
import java.p456io.IOException;
import java.p456io.InputStream;
import java.p456io.UnsupportedEncodingException;

/* renamed from: com.gbcom.gwifi.a.b.b */
public class FormInputStream extends WrapInputStream {

    /* renamed from: a */
    public static final String f8504a = "UTF-8";

    /* renamed from: b */
    private String f8505b;

    /* renamed from: c */
    private Object f8506c;

    public FormInputStream() {
        this("UTF-8");
    }

    public FormInputStream(String str) {
        this.f8505b = str;
    }

    public FormInputStream(Object obj) {
        this.f8506c = obj;
    }

    /* renamed from: c */
    private String m10192c() throws UnsupportedEncodingException {
        this.f8505b = "UTF-8";
        return JsonUtil.m14387a(this.f8506c);
    }

    /* renamed from: a */
    public String mo24072a() {
        return this.f8505b;
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.p221a.p223b.WrapInputStream
    /* renamed from: b */
    public InputStream mo24073b() throws IOException {
        try {
            return new ByteArrayInputStream(m10192c().getBytes(this.f8505b));
        } catch (UnsupportedCharsetException e) {
            throw new IOException(e.getMessage());
        }
    }
}
