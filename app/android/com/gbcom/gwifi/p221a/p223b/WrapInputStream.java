package com.gbcom.gwifi.p221a.p223b;

import java.p456io.IOException;
import java.p456io.InputStream;

/* renamed from: com.gbcom.gwifi.a.b.l */
public abstract class WrapInputStream extends InputStream {

    /* renamed from: a */
    private IOException f8526a;

    /* renamed from: b */
    private InputStream f8527b;

    /* renamed from: c */
    private int f8528c;

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public abstract InputStream mo24073b() throws IOException;

    /* renamed from: a */
    private InputStream mo24072a() throws IOException {
        if (this.f8527b == null) {
            try {
                this.f8527b = mo24073b();
            } catch (IOException e) {
                throw e;
            }
        }
        return this.f8527b;
    }

    @Override // java.p456io.InputStream
    public int available() throws IOException {
        return mo24072a().available();
    }

    @Override // java.p456io.Closeable, java.lang.AutoCloseable, java.p456io.InputStream
    public void close() throws IOException {
        if (this.f8527b != null) {
            mo24072a().close();
        }
    }

    @Override // java.p456io.InputStream
    public void mark(int i) {
        if (this.f8527b != null) {
            this.f8527b.mark(i);
            this.f8528c++;
        }
    }

    @Override // java.p456io.InputStream
    public boolean markSupported() {
        if (this.f8527b != null) {
            return this.f8527b.markSupported();
        }
        return false;
    }

    @Override // java.p456io.InputStream
    public int read() throws IOException {
        return mo24072a().read();
    }

    @Override // java.p456io.InputStream
    public int read(byte[] bArr) throws IOException {
        return mo24072a().read(bArr);
    }

    @Override // java.p456io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        return mo24072a().read(bArr, i, i2);
    }

    @Override // java.p456io.InputStream
    public void reset() throws IOException {
        if (this.f8528c == 0) {
            this.f8527b = null;
            return;
        }
        mo24072a().reset();
        this.f8528c--;
    }

    @Override // java.p456io.InputStream
    public long skip(long j) throws IOException {
        return mo24072a().skip(j);
    }
}
