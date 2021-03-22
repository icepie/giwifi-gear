package com.gbcom.gwifi.util;

import android.util.Base64;
import com.gbcom.gwifi.codec.AES;
import com.gbcom.gwifi.codec.MD5Util;
import java.net.URLEncoder;

public class URLFunctions {
    public String aes_ecb_pkcs7(String str, String str2) {
        return Base64.encodeToString(AES.m10813a(str, str2.getBytes()), 0);
    }

    public String url_encode(String str) {
        return URLEncoder.encode(str);
    }

    public String gb_md5(String str) {
        return MD5Util.m10825a(str).toLowerCase();
    }

    public String gb_substr(String str, String str2, String str3) {
        return str.substring(Integer.parseInt(str2), Integer.parseInt(str3));
    }
}
