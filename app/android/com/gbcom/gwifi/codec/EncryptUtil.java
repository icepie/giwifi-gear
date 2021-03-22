package com.gbcom.gwifi.codec;

import com.gbcom.gwifi.util.Constant;

public class EncryptUtil {
    public static String getEString(String str) {
        StringBuilder sb = new StringBuilder();
        String[] split = str.split("\n");
        for (String str2 : split) {
            for (int i = 0; i < str2.length(); i++) {
                sb.append((char) (str2.charAt(i) - (i % 5)));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static String getEncrypt(String str) {
        return Encrypt.m10821a(Constant.f13311ct, str);
    }

    public static String decrypt(String str) {
        return Encrypt.m10823b(Constant.f13311ct, str);
    }
}
