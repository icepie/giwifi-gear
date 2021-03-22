package com.gbcom.gwifi.p221a.p223b;

import java.p456io.IOException;
import java.p456io.InputStream;

/* renamed from: com.gbcom.gwifi.a.b.k */
public class WatchedInputStream extends InputStream {

    /* renamed from: a */
    private AbstractC2439a f8522a;

    /* renamed from: b */
    private int f8523b;

    /* renamed from: c */
    private int f8524c;

    /* renamed from: d */
    private InputStream f8525d;

    /* renamed from: com.gbcom.gwifi.a.b.k$a */
    /* compiled from: WatchedInputStream */
    public interface AbstractC2439a {
        /* renamed from: a */
        void mo24063a(int i);
    }

    public WatchedInputStream(InputStream inputStream, int i) {
        this.f8525d = inputStream;
        this.f8523b = i;
    }

    @Override // java.p456io.InputStream
    public int available() throws IOException {
        return this.f8525d.available();
    }

    @Override // java.p456io.Closeable, java.lang.AutoCloseable, java.p456io.InputStream
    public void close() throws IOException {
        this.f8525d.close();
    }

    @Override // java.p456io.InputStream
    public void mark(int i) {
        throw new UnsupportedOperationException();
    }

    @Override // java.p456io.InputStream
    public boolean markSupported() {
        return false;
    }

    @Override // java.p456io.InputStream
    public int read() throws IOException {
        int read = this.f8525d.read();
        if (read >= 0) {
            this.f8524c++;
            if (this.f8524c > this.f8523b) {
                if (this.f8522a != null) {
                    this.f8522a.mo24063a(this.f8524c);
                }
                this.f8524c = 0;
            }
        }
        return read;
    }

    @Override // java.p456io.InputStream
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.p456io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int read = this.f8525d.read(bArr, i, i2);
        if (read >= 0) {
            this.f8524c += read;
            if (this.f8524c > this.f8523b && this.f8522a != null) {
                this.f8522a.mo24063a(this.f8524c);
                this.f8524c = 0;
            }
        } else {
            this.f8522a.mo24063a(this.f8524c);
        }
        return read;
    }

    @Override // java.p456io.InputStream
    public void reset() throws IOException {
        throw new IOException("not supported operation: reset");
    }

    /* renamed from: a */
    public void mo24080a(AbstractC2439a aVar) {
        this.f8522a = aVar;
    }

    @Override // java.p456io.InputStream
    public long skip(long j) throws IOException {
        throw new IOException("not supported operation: skip");
    }
}
