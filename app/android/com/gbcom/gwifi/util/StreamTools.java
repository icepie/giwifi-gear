package com.gbcom.gwifi.util;

import java.p456io.ByteArrayOutputStream;
import java.p456io.InputStream;

/* renamed from: com.gbcom.gwifi.util.r */
public class StreamTools {
    /* renamed from: a */
    public static String m14499a(InputStream inputStream) {
        String str;
        Exception e;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
            inputStream.close();
            str = new String(byteArrayOutputStream.toByteArray(), "UTF-8");
            try {
                byteArrayOutputStream.close();
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Exception e3) {
            str = null;
            e = e3;
            e.printStackTrace();
            return str;
        }
        return str;
    }
}
