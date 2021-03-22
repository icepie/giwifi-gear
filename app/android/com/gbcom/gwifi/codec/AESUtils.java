package com.gbcom.gwifi.codec;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* renamed from: com.gbcom.gwifi.codec.b */
public class AESUtils {
    /* renamed from: a */
    public static String m10814a(String str, String str2, String str3) throws Exception {
        Cipher instance = Cipher.getInstance("AES/CBC/NoPadding");
        int blockSize = instance.getBlockSize();
        byte[] bytes = str.getBytes();
        int length = bytes.length;
        if (length % blockSize != 0) {
            length += blockSize - (length % blockSize);
        }
        byte[] bArr = new byte[length];
        System.arraycopy((Object) bytes, 0, (Object) bArr, 0, bytes.length);
        instance.init(1, new SecretKeySpec(str2.getBytes(), "AES"), new IvParameterSpec(str3.getBytes()));
        return m10815a(instance.doFinal(bArr)).toLowerCase();
    }

    /* renamed from: b */
    public static String m10817b(String str, String str2, String str3) throws Exception {
        byte[] a = m10816a(str);
        Cipher instance = Cipher.getInstance("AES/CBC/NoPadding");
        instance.init(2, new SecretKeySpec(str2.getBytes(), "AES"), new IvParameterSpec(str3.getBytes()));
        return new String(instance.doFinal(a));
    }

    /* renamed from: a */
    public static byte[] m10816a(String str) {
        if (str == null) {
            return null;
        }
        int length = str.length();
        if (length % 2 == 1) {
            return null;
        }
        byte[] bArr = new byte[(length / 2)];
        for (int i = 0; i != length / 2; i++) {
            bArr[i] = (byte) Integer.parseInt(str.substring(i * 2, (i * 2) + 2), 16);
        }
        return bArr;
    }

    /* renamed from: a */
    public static String m10815a(byte[] bArr) {
        String str = "";
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            str = hexString.length() == 1 ? str + "0" + hexString : str + hexString;
        }
        return str.toUpperCase();
    }
}
