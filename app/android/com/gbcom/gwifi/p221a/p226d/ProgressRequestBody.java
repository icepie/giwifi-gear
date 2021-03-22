package com.gbcom.gwifi.p221a.p226d;

import java.p456io.IOException;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;
import p041c.MediaType;
import p041c.Request;
import p041c.RequestBody;

/* renamed from: com.gbcom.gwifi.a.d.h */
public class ProgressRequestBody extends RequestBody {

    /* renamed from: a */
    private RequestBody f8574a;

    /* renamed from: b */
    private BufferedSink f8575b;

    /* renamed from: c */
    private Request f8576c;

    /* renamed from: d */
    private OkRequestHandler f8577d;

    public ProgressRequestBody(RequestBody acVar, Request abVar, OkRequestHandler eVar) {
        this.f8574a = acVar;
        this.f8576c = abVar;
        this.f8577d = eVar;
    }

    @Override // p041c.RequestBody
    /* renamed from: a */
    public MediaType mo16113a() {
        return this.f8574a.mo16113a();
    }

    @Override // p041c.RequestBody
    /* renamed from: b */
    public long mo16115b() throws IOException {
        return this.f8574a.mo16115b();
    }

    @Override // p041c.RequestBody
    /* renamed from: a */
    public void mo16114a(BufferedSink bufferedSink) throws IOException {
        if (this.f8575b == null) {
            this.f8575b = Okio.buffer(mo24142b(bufferedSink));
        }
        this.f8574a.mo16114a(this.f8575b);
        this.f8575b.flush();
    }

    /* renamed from: b */
    public Sink mo24142b(BufferedSink bufferedSink) {
        return new ForwardingSink(bufferedSink) {
            /* class com.gbcom.gwifi.p221a.p226d.ProgressRequestBody.C24501 */

            /* renamed from: a */
            long f8578a = 0;

            /* renamed from: b */
            long f8579b = 0;

            @Override // okio.ForwardingSink, okio.Sink
            public void write(Buffer buffer, long j) throws IOException {
                super.write(buffer, j);
                if (this.f8579b == 0) {
                    this.f8579b = ProgressRequestBody.this.mo16115b();
                }
                this.f8578a += j;
                OkHttpService.m10254a((Request) null, this.f8578a, this.f8579b, ProgressRequestBody.this.f8577d);
            }
        };
    }
}
