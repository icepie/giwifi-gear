package com.gbcom.gwifi.p221a.p226d;

import java.p456io.IOException;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;
import p041c.MediaType;
import p041c.Request;
import p041c.ResponseBody;

/* renamed from: com.gbcom.gwifi.a.d.i */
public class ProgressResponseBody extends ResponseBody {

    /* renamed from: a */
    private ResponseBody f8581a;

    /* renamed from: b */
    private BufferedSource f8582b;

    /* renamed from: c */
    private Request f8583c;

    /* renamed from: d */
    private OkRequestHandler f8584d;

    public ProgressResponseBody(Request abVar, ResponseBody aeVar, OkRequestHandler eVar) {
        this.f8581a = aeVar;
        this.f8584d = eVar;
    }

    @Override // p041c.ResponseBody
    public long contentLength() {
        return this.f8581a.contentLength();
    }

    @Override // p041c.ResponseBody
    public MediaType contentType() {
        return this.f8581a.contentType();
    }

    @Override // p041c.ResponseBody
    public BufferedSource source() {
        if (this.f8582b == null) {
            this.f8582b = Okio.buffer(m10317a(this.f8581a.source()));
        }
        return this.f8582b;
    }

    /* renamed from: a */
    private Source m10317a(Source source) {
        return new ForwardingSource(source) {
            /* class com.gbcom.gwifi.p221a.p226d.ProgressResponseBody.C24511 */

            /* renamed from: a */
            long f8585a = 0;

            @Override // okio.Source, okio.ForwardingSource
            public long read(Buffer buffer, long j) throws IOException {
                long read = super.read(buffer, j);
                this.f8585a = (read != -1 ? read : 0) + this.f8585a;
                OkHttpService.m10254a(ProgressResponseBody.this.f8583c, this.f8585a, ProgressResponseBody.this.f8581a.contentLength(), ProgressResponseBody.this.f8584d);
                return read;
            }
        };
    }
}
