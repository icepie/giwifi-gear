package com.gbcom.gwifi.codec;

import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.p456io.File;
import java.p456io.FileInputStream;
import java.p456io.IOException;
import java.security.MessageDigest;

/* renamed from: com.gbcom.gwifi.codec.e */
public class MD5Util {
    /* renamed from: a */
    public static final String m10825a(String str) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] bytes = str.getBytes();
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(bytes);
            byte[] digest = instance.digest();
            int length = digest.length;
            char[] cArr2 = new char[(length * 2)];
            int i = 0;
            for (byte b : digest) {
                int i2 = i + 1;
                cArr2[i] = cArr[(b >>> 4) & 15];
                i = i2 + 1;
                cArr2[i2] = cArr[b & 15];
            }
            return new String(cArr2);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static String m10824a(File file) throws IOException {
        return m10826a(new FileInputStream(file).getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length()));
    }

    /* renamed from: a */
    public static final String m10826a(MappedByteBuffer mappedByteBuffer) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(mappedByteBuffer);
            byte[] digest = instance.digest();
            int length = digest.length;
            char[] cArr2 = new char[(length * 2)];
            int i = 0;
            for (byte b : digest) {
                int i2 = i + 1;
                cArr2[i] = cArr[(b >>> 4) & 15];
                i = i2 + 1;
                cArr2[i2] = cArr[b & 15];
            }
            return new String(cArr2).toLowerCase();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
