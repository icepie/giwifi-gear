package com.gbcom.gwifi.util;

import android.text.TextUtils;
import com.gbcom.gwifi.functions.wifi.p253a.AuthResultCode;

/* renamed from: com.gbcom.gwifi.util.p */
public class ResultCode {

    /* renamed from: a */
    public static final int f13477a = 101;

    /* renamed from: b */
    public static final int f13478b = 102;

    /* renamed from: a */
    public static String m14474a(CommonMsg commonMsg) {
        if (!TextUtils.isEmpty(commonMsg.getResultMsg())) {
            return commonMsg.getResultMsg();
        }
        switch (commonMsg.getResultCode().intValue()) {
            case 101:
                return "该用户已在其它手机登录";
            case 102:
                return "请稍后尝试";
            default:
                return AuthResultCode.m13884a(commonMsg.getResultCode().intValue());
        }
    }

    /* renamed from: a */
    public static boolean m14475a(Integer num) {
        if (num != null && num.intValue() == 0) {
            return true;
        }
        return false;
    }

    /* renamed from: b */
    public static boolean m14476b(CommonMsg commonMsg) {
        if (commonMsg == null || commonMsg.getResultCode() == null || commonMsg.getResultCode().intValue() != 0) {
            return false;
        }
        return true;
    }
}
