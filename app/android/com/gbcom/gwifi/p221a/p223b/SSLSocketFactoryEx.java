package com.gbcom.gwifi.p221a.p223b;

import java.net.Socket;
import java.net.UnknownHostException;
import java.p456io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import org.apache.http.conn.ssl.SSLSocketFactory;

/* renamed from: com.gbcom.gwifi.a.b.i */
public class SSLSocketFactoryEx extends SSLSocketFactory {

    /* renamed from: a */
    SSLContext f8517a = SSLContext.getInstance("TLS");

    public SSLSocketFactoryEx(KeyStore keyStore, KeyManagerFactory keyManagerFactory, TrustManagerFactory trustManagerFactory) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException {
        super(keyStore);
        this.f8517a.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), null);
    }

    public SSLSocketFactoryEx(KeyStore keyStore) throws KeyManagementException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException {
        super(keyStore);
        C24381 r0 = new X509TrustManager() {
            /* class com.gbcom.gwifi.p221a.p223b.SSLSocketFactoryEx.C24381 */

            @Override // javax.net.ssl.X509TrustManager
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            @Override // javax.net.ssl.X509TrustManager
            public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            }

            @Override // javax.net.ssl.X509TrustManager
            public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            }
        };
        this.f8517a.init(null, new TrustManager[]{r0}, null);
    }

    @Override // org.apache.http.conn.scheme.LayeredSocketFactory, org.apache.http.conn.ssl.SSLSocketFactory
    public Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException, UnknownHostException {
        return this.f8517a.getSocketFactory().createSocket(socket, str, i, z);
    }

    @Override // org.apache.http.conn.scheme.SocketFactory, org.apache.http.conn.ssl.SSLSocketFactory
    public Socket createSocket() throws IOException {
        return this.f8517a.getSocketFactory().createSocket();
    }
}
