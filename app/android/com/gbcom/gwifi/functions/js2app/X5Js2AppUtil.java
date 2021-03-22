package com.gbcom.gwifi.functions.js2app;

import com.gbcom.gwifi.util.JsonUtil;
import com.gbcom.gwifi.util.Log;
import com.tencent.smtt.sdk.WebView;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.p456io.UnsupportedEncodingException;
import java.util.HashMap;

public class X5Js2AppUtil {
    private static String TAG = "X5Js2AppUtil";
    private static String protocolPrefix = "giwifijs2app://";

    public static boolean processURL(WebView webView, String str) {
        String str2;
        str.indexOf(protocolPrefix);
        Log.m14398b("ProcessURL", str);
        if (str.indexOf(protocolPrefix) != 0) {
            return false;
        }
        String substring = str.substring(protocolPrefix.length());
        try {
            str2 = URLDecoder.decode(substring, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            str2 = substring;
        }
        HashMap hashMap = (HashMap) JsonUtil.m14386a(str2, HashMap.class);
        Log.m14398b(TAG, "URL:+ " + str2);
        if (hashMap == null) {
            return false;
        }
        Object obj = hashMap.get("functionName");
        if (obj == null) {
            return false;
        }
        Object obj2 = hashMap.get("success");
        Object obj3 = hashMap.get("error");
        Object obj4 = hashMap.get("args");
        Log.m14398b(TAG, "functionName:+ " + ((String) obj));
        callNativeFunction(webView, (String) obj, obj4, obj2, obj3);
        return true;
    }

    private static void callNativeFunction(WebView webView, String str, Object obj, Object obj2, Object obj3) {
        try {
            X5JsFunctions.class.getMethod(str, WebView.class, Object.class, Object.class, Object.class).invoke(X5JsFunctions.getInstance(), webView, obj, obj2, obj3);
        } catch (NoSuchMethodException e) {
        } catch (InvocationTargetException e2) {
            e2.printStackTrace();
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
        }
    }

    public static boolean checkNativeFunction(String str) {
        try {
            X5JsFunctions.class.getMethod(str, WebView.class, Object.class, Object.class, Object.class);
            return true;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return false;
        }
    }
}
