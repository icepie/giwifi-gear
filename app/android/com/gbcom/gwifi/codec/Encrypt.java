package com.gbcom.gwifi.codec;

import android.util.Base64;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/* renamed from: com.gbcom.gwifi.codec.d */
public class Encrypt {

    /* renamed from: a */
    private static final String f9151a = "AES/ECB/PKCS5Padding";

    /* renamed from: a */
    public static String m10821a(String str, String str2) {
        if (str2 == null) {
            return "";
        }
        try {
            Key a = m10822a(str);
            Cipher instance = Cipher.getInstance(f9151a);
            instance.init(1, a);
            return Base64.encodeToString(instance.doFinal(str2.getBytes()), 2);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /* renamed from: b */
    public static String m10823b(String str, String str2) {
        try {
            Key a = m10822a(str);
            Cipher instance = Cipher.getInstance(f9151a);
            instance.init(2, a);
            return new String(instance.doFinal(Base64.decode(str2, 2))).trim();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /* renamed from: a */
    private static Key m10822a(String str) throws Exception {
        try {
            return new SecretKeySpec(str.getBytes(), "AES");
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
