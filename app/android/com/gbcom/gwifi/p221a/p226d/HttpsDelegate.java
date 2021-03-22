package com.gbcom.gwifi.p221a.p226d;

import java.p456io.IOException;
import java.p456io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import p041c.OkHttpClient;

/* renamed from: com.gbcom.gwifi.a.d.c */
public class HttpsDelegate {
    /* renamed from: a */
    public void mo24098a(InputStream... inputStreamArr) {
        mo24099a(inputStreamArr, null, null);
    }

    /* renamed from: b */
    public TrustManager[] mo24101b(InputStream... inputStreamArr) {
        int i = 0;
        if (inputStreamArr == null || inputStreamArr.length <= 0) {
            return null;
        }
        try {
            CertificateFactory instance = CertificateFactory.getInstance("X.509");
            KeyStore instance2 = KeyStore.getInstance(KeyStore.getDefaultType());
            instance2.load(null);
            int length = inputStreamArr.length;
            int i2 = 0;
            while (i < length) {
                InputStream inputStream = inputStreamArr[i];
                int i3 = i2 + 1;
                instance2.setCertificateEntry(Integer.toString(i2), instance.generateCertificate(inputStream));
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                    }
                }
                i++;
                i2 = i3;
            }
            TrustManagerFactory instance3 = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            instance3.init(instance2);
            return instance3.getTrustManagers();
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return null;
        } catch (CertificateException e3) {
            e3.printStackTrace();
            return null;
        } catch (KeyStoreException e4) {
            e4.printStackTrace();
            return null;
        } catch (Exception e5) {
            e5.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public KeyManager[] mo24100a(InputStream inputStream, String str) {
        if (inputStream == null || str == null) {
            return null;
        }
        try {
            KeyStore instance = KeyStore.getInstance("BKS");
            instance.load(inputStream, str.toCharArray());
            KeyManagerFactory instance2 = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            instance2.init(instance, str.toCharArray());
            return instance2.getKeyManagers();
        } catch (KeyStoreException e) {
            e.printStackTrace();
            return null;
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return null;
        } catch (UnrecoverableKeyException e3) {
            e3.printStackTrace();
            return null;
        } catch (CertificateException e4) {
            e4.printStackTrace();
            return null;
        } catch (IOException e5) {
            e5.printStackTrace();
            return null;
        } catch (Exception e6) {
            e6.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public void mo24099a(InputStream[] inputStreamArr, InputStream inputStream, String str) {
        try {
            TrustManager[] b = mo24101b(inputStreamArr);
            KeyManager[] a = mo24100a(inputStream, str);
            SSLContext instance = SSLContext.getInstance("TLS");
            instance.init(a, new TrustManager[]{new C2443a(m10238a(b))}, new SecureRandom());
            m10236a().mo16465y().mo16480a(instance.getSocketFactory());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e2) {
            e2.printStackTrace();
        } catch (KeyStoreException e3) {
            e3.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private X509TrustManager m10238a(TrustManager[] trustManagerArr) {
        for (TrustManager trustManager : trustManagerArr) {
            if (trustManager instanceof X509TrustManager) {
                return (X509TrustManager) trustManager;
            }
        }
        return null;
    }

    /* renamed from: com.gbcom.gwifi.a.d.c$a */
    /* compiled from: HttpsDelegate */
    public class C2443a implements X509TrustManager {

        /* renamed from: b */
        private X509TrustManager f8542b;

        /* renamed from: c */
        private X509TrustManager f8543c;

        public C2443a(X509TrustManager x509TrustManager) throws NoSuchAlgorithmException, KeyStoreException {
            TrustManagerFactory instance = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            instance.init((KeyStore) null);
            this.f8542b = HttpsDelegate.this.m10238a((HttpsDelegate) instance.getTrustManagers());
            this.f8543c = x509TrustManager;
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            try {
                this.f8542b.checkServerTrusted(x509CertificateArr, str);
            } catch (CertificateException e) {
                this.f8543c.checkServerTrusted(x509CertificateArr, str);
            }
        }

        @Override // javax.net.ssl.X509TrustManager
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    /* renamed from: a */
    private OkHttpClient m10236a() {
        return OkHttpService.m10252a().mo24102b();
    }
}
