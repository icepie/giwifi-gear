package com.gbcom.gwifi.codec;

import com.android.org.bouncycastle.jce.provider.BouncyCastleProvider;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/* renamed from: com.gbcom.gwifi.codec.a */
public class AES {

    /* renamed from: a */
    static final String f9148a = "AES";

    /* renamed from: b */
    public static boolean f9149b = false;

    /* renamed from: c */
    public static final String f9150c = "AES/ECB/PKCS7Padding";

    /* renamed from: a */
    public static void m10812a() {
        if (!f9149b) {
            f9149b = true;
        }
    }

    /* renamed from: a */
    public static byte[] m10813a(String str, byte[] bArr) {
        m10812a();
        try {
            Cipher instance = Cipher.getInstance(f9150c, BouncyCastleProvider.PROVIDER_NAME);
            instance.init(1, new SecretKeySpec(bArr, f9148a));
            return instance.doFinal(str.getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
