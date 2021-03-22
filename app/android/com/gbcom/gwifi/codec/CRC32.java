package com.gbcom.gwifi.codec;

/* renamed from: com.gbcom.gwifi.codec.c */
public class CRC32 {
    /* renamed from: a */
    public static int m10820a(byte[] bArr) {
        return m10819a(-1, bArr);
    }

    /* renamed from: a */
    public static int m10819a(int i, byte[] bArr) {
        for (int i2 = 0; i2 < bArr.length; i2++) {
            byte b = bArr[i2];
            i = m10818a((bArr[i2] ^ i) & 255) ^ (i >>> 8);
        }
        return i;
    }

    /* renamed from: a */
    public static int m10818a(int i) {
        int i2;
        int i3 = 0;
        while (i3 < 8) {
            if ((i & 1) == 1) {
                i2 = (i >>> 1) ^ -306674912;
            } else {
                i2 = i >>> 1;
            }
            i3++;
            i = i2;
        }
        return i;
    }
}
