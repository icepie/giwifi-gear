package com.gbcom.gwifi.third.pay.alipay;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.alipay.sdk.app.C1473d;
import com.alipay.sdk.p114h.C1523a;
import com.alipay.sdk.p116j.C1536i;
import com.alipay.sdk.p116j.C1538k;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.functions.js2app.X5JsFunctions;
import com.gbcom.gwifi.util.Log;
import com.taobao.agoo.p359a.p360a.AbstractC5718b;
import java.net.URLEncoder;
import java.p456io.UnsupportedEncodingException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.HashMap;

public class AliPayUtil {
    private static final String ALGORITHM = "RSA";
    public static final String ALIPAY_CANCEL = "6001";
    public static final String ALIPAY_DEALING = "8000";
    public static final String ALIPAY_ERROR = "4000";
    public static final String ALIPAY_NET_ERROR = "6002";
    public static final String ALIPAY_SUCCESS = "9000";
    private static final int BASELENGTH = 128;
    private static final String DEFAULT_CHARSET = "UTF-8";
    private static final int EIGHTBIT = 8;
    private static final int FOURBYTE = 4;
    private static final int LOOKUPLENGTH = 64;
    private static char PAD = '=';
    public static final String PARTNER = "2088911577543048";
    public static final String RSA_PRIVATE = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALAWefmq7X99PkHZLNquZRT9rECyDrZVhdQYc8cJsnJjsGQLGFn9OZANRY0L1tueirVRskg7zp89wggczfxbnRCuKrt7LvSPiFfj+wj/rIyjgLZ10idSiJm8OpsxngRjnx82cAI39eVFUosnlCJG4iDnUcFQaHZEyQriNqL8fx0nAgMBAAECgYBivofyEP8T4hjadj3n96nphoNk2DTV3MmqcXfjYrW13d9ZirtCI3V3KWoaOUxoZdSk70ZJqXgp5kj7oaOFY56EgI4+p6yAoc2dR1nI+/H/N9W1s4uD8N60+MmwueOgIq79Po6udmUhsP7Qq/kO9govLc4zS9GVxlAIpKCgXkzpIQJBAOEfaORTHZlVc91D6cUEjZPXWJIc/vvNBOFz1E6H/QfRPEHUrp7rzH/SBpURIkqgRJVzNfWCLHdrBBoK5eiI0/cCQQDIPVXn0/yzRQsDmB1ptdfb3UsEcgSRFnHFejged46DiUmyex+gHpi0VbOQp1ragAA/3C6ndFoBYBFZ9MWB0VRRAkAzsozZ8iRX9AG5jEEA13zTuJ5EX5hGptw6EeltZ1k6FFisHXVEVY4OxGJylQ606H0XfO+lQ5GGkvLMMDh/3/wdAkBWHZLJbfDaxFG1TQExSkQ4SVaO5d4y650oWaSy7aX7ydpJCQyT1zTDMaxVBLRdDfmNfUsPN0nSF5e+wTcD2pvRAkEAsuEyyLT9Vrgw7QBdQ7wcMLn1UAEvP6V7xjYc8+6re583Zom+BtCKwK0Bqz5kcrPTPLqrJANYdn71KvU89SOABA==";
    private static final int SDK_PAY_FLAG = 1;
    private static final int SIGN = -128;
    private static final String SIGN_ALGORITHMS = "SHA1WithRSA";
    private static final int SIXTEENBIT = 16;
    private static final String TAG = "AliPayUtil";
    private static final int TWENTYFOURBITGROUP = 24;
    private static byte[] base64Alphabet = new byte[128];
    private static char[] lookUpBase64Alphabet = new char[64];
    private Activity mActivity;
    public Handler mHandler = new Handler() {
        /* class com.gbcom.gwifi.third.pay.alipay.AliPayUtil.HandlerC34223 */

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    PayResult payResult = new PayResult((String) message.obj);
                    dealTask(payResult.getResult(), payResult.getResultStatus());
                    return;
                default:
                    return;
            }
        }

        public HashMap<String, Object> StringToJson(Integer num, String str) {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put(AbstractC5718b.JSON_ERRORCODE, num);
            hashMap.put("resultMsg", str);
            return hashMap;
        }

        public void dealTask(String str, String str2) {
            if (TextUtils.equals(str2, AliPayUtil.ALIPAY_SUCCESS)) {
                X5JsFunctions.getInstance().callJSFunction(X5JsFunctions.getWebView(), X5JsFunctions.getFuncSuccess().toString(), StringToJson(Integer.valueOf(Integer.parseInt(AliPayUtil.ALIPAY_SUCCESS)), ""));
            } else if (TextUtils.equals(str2, AliPayUtil.ALIPAY_DEALING)) {
                X5JsFunctions.getInstance().callJSFunction(X5JsFunctions.getWebView(), X5JsFunctions.getFuncError().toString(), StringToJson(Integer.valueOf(Integer.parseInt(AliPayUtil.ALIPAY_DEALING)), ""));
            } else if (TextUtils.equals(str2, AliPayUtil.ALIPAY_CANCEL)) {
                X5JsFunctions.getInstance().callJSFunction(X5JsFunctions.getWebView(), X5JsFunctions.getFuncError().toString(), StringToJson(Integer.valueOf(Integer.parseInt(AliPayUtil.ALIPAY_CANCEL)), ""));
            } else if (TextUtils.equals(str2, AliPayUtil.ALIPAY_ERROR)) {
                X5JsFunctions.getInstance().callJSFunction(X5JsFunctions.getWebView(), X5JsFunctions.getFuncError().toString(), StringToJson(Integer.valueOf(Integer.parseInt(AliPayUtil.ALIPAY_ERROR)), ""));
            } else {
                X5JsFunctions.getInstance().callJSFunction(X5JsFunctions.getWebView(), X5JsFunctions.getFuncError().toString(), StringToJson(Integer.valueOf(Integer.parseInt(AliPayUtil.ALIPAY_NET_ERROR)), ""));
            }
        }
    };

    static {
        int i = 0;
        for (int i2 = 0; i2 < 128; i2++) {
            base64Alphabet[i2] = -1;
        }
        for (int i3 = 90; i3 >= 65; i3--) {
            base64Alphabet[i3] = (byte) (i3 - 65);
        }
        for (int i4 = 122; i4 >= 97; i4--) {
            base64Alphabet[i4] = (byte) ((i4 - 97) + 26);
        }
        for (int i5 = 57; i5 >= 48; i5--) {
            base64Alphabet[i5] = (byte) ((i5 - 48) + 52);
        }
        base64Alphabet[43] = 62;
        base64Alphabet[47] = 63;
        for (int i6 = 0; i6 <= 25; i6++) {
            lookUpBase64Alphabet[i6] = (char) (i6 + 65);
        }
        int i7 = 26;
        int i8 = 0;
        while (i7 <= 51) {
            lookUpBase64Alphabet[i7] = (char) (i8 + 97);
            i7++;
            i8++;
        }
        int i9 = 52;
        while (i9 <= 61) {
            lookUpBase64Alphabet[i9] = (char) (i + 48);
            i9++;
            i++;
        }
        lookUpBase64Alphabet[62] = '+';
        lookUpBase64Alphabet[63] = '/';
    }

    public void aliPay(final String str) {
        new Thread(new Runnable() {
            /* class com.gbcom.gwifi.third.pay.alipay.AliPayUtil.RunnableC34201 */

            @Override // java.lang.Runnable
            public void run() {
                String a = new C1473d(GBApplication.instance().getCurrentActivity()).mo17969a(str, true);
                Message message = new Message();
                message.what = 1;
                message.obj = a;
                AliPayUtil.this.mHandler.sendMessage(message);
            }
        }).start();
    }

    public void aliPay(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        String orderInfo = getOrderInfo(str, str2, str3, str4, str5, str6, str7);
        Log.m14398b(TAG, "orderInfo:+ " + orderInfo);
        String sign = sign(orderInfo);
        try {
            sign = URLEncoder.encode(sign, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        final String str8 = orderInfo + "&sign=\"" + sign + C1523a.f3448a + getSignType();
        new Thread(new Runnable() {
            /* class com.gbcom.gwifi.third.pay.alipay.AliPayUtil.RunnableC34212 */

            @Override // java.lang.Runnable
            public void run() {
                String a = new C1473d(GBApplication.instance().getCurrentActivity()).mo17969a(str8, true);
                Message message = new Message();
                message.what = 1;
                message.obj = a;
                AliPayUtil.this.mHandler.sendMessage(message);
            }
        }).start();
    }

    public String getSignType() {
        return "sign_type=\"RSA\"";
    }

    public String sign(String str) {
        try {
            PrivateKey generatePrivate = KeyFactory.getInstance(ALGORITHM).generatePrivate(new PKCS8EncodedKeySpec(decode(RSA_PRIVATE)));
            Signature instance = Signature.getInstance(SIGN_ALGORITHMS);
            instance.initSign(generatePrivate);
            instance.update(str.getBytes("UTF-8"));
            return encode(instance.sign());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getOrderInfo(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        return ((((((((("partner=\"2088911577543048\"" + "&seller_id=\"" + str + "\"") + "&out_trade_no=\"" + str2 + "\"") + "&subject=\"" + str3 + "\"") + "&body=\"" + str4 + "\"") + "&total_fee=\"" + str5 + "\"") + "&notify_url=\"" + str6 + "\"") + "&service=\"mobile.securitypay.pay\"") + "&payment_type=\"1\"") + "&_input_charset=\"utf-8\"") + "&it_b_pay=\"" + str7 + "\"";
    }

    public byte[] decode(String str) {
        if (str == null) {
            return null;
        }
        char[] charArray = str.toCharArray();
        int removeWhiteSpace = removeWhiteSpace(charArray);
        if (removeWhiteSpace % 4 != 0) {
            return null;
        }
        int i = removeWhiteSpace / 4;
        if (i == 0) {
            return new byte[0];
        }
        byte[] bArr = new byte[(i * 3)];
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i4 < i - 1) {
            int i5 = i2 + 1;
            char c = charArray[i2];
            if (!isData(c)) {
                return null;
            }
            int i6 = i5 + 1;
            char c2 = charArray[i5];
            if (!isData(c2)) {
                return null;
            }
            int i7 = i6 + 1;
            char c3 = charArray[i6];
            if (!isData(c3)) {
                return null;
            }
            i2 = i7 + 1;
            char c4 = charArray[i7];
            if (!isData(c4)) {
                return null;
            }
            byte b = base64Alphabet[c];
            byte b2 = base64Alphabet[c2];
            byte b3 = base64Alphabet[c3];
            byte b4 = base64Alphabet[c4];
            int i8 = i3 + 1;
            bArr[i3] = (byte) ((b << 2) | (b2 >> 4));
            int i9 = i8 + 1;
            bArr[i8] = (byte) (((b2 & 15) << 4) | ((b3 >> 2) & 15));
            i3 = i9 + 1;
            bArr[i9] = (byte) ((b3 << 6) | b4);
            i4++;
        }
        int i10 = i2 + 1;
        char c5 = charArray[i2];
        if (!isData(c5)) {
            return null;
        }
        int i11 = i10 + 1;
        char c6 = charArray[i10];
        if (!isData(c6)) {
            return null;
        }
        byte b5 = base64Alphabet[c5];
        byte b6 = base64Alphabet[c6];
        int i12 = i11 + 1;
        char c7 = charArray[i11];
        int i13 = i12 + 1;
        char c8 = charArray[i12];
        if (isData(c7) && isData(c8)) {
            byte b7 = base64Alphabet[c7];
            byte b8 = base64Alphabet[c8];
            int i14 = i3 + 1;
            bArr[i3] = (byte) ((b5 << 2) | (b6 >> 4));
            int i15 = i14 + 1;
            bArr[i14] = (byte) (((b6 & 15) << 4) | ((b7 >> 2) & 15));
            int i16 = i15 + 1;
            bArr[i15] = (byte) ((b7 << 6) | b8);
            return bArr;
        } else if (!isPad(c7) || !isPad(c8)) {
            if (isPad(c7) || !isPad(c8)) {
                return null;
            }
            byte b9 = base64Alphabet[c7];
            if ((b9 & 3) != 0) {
                return null;
            }
            byte[] bArr2 = new byte[((i4 * 3) + 2)];
            System.arraycopy((Object) bArr, 0, (Object) bArr2, 0, i4 * 3);
            bArr2[i3] = (byte) ((b5 << 2) | (b6 >> 4));
            bArr2[i3 + 1] = (byte) (((b6 & 15) << 4) | ((b9 >> 2) & 15));
            return bArr2;
        } else if ((b6 & 15) != 0) {
            return null;
        } else {
            byte[] bArr3 = new byte[((i4 * 3) + 1)];
            System.arraycopy((Object) bArr, 0, (Object) bArr3, 0, i4 * 3);
            bArr3[i3] = (byte) ((b5 << 2) | (b6 >> 4));
            return bArr3;
        }
    }

    public boolean isWhiteSpace(char c) {
        return c == ' ' || c == '\r' || c == '\n' || c == '\t';
    }

    public boolean isPad(char c) {
        return c == PAD;
    }

    public boolean isData(char c) {
        return c < 128 && base64Alphabet[c] != -1;
    }

    public String encode(byte[] bArr) {
        int i = 0;
        if (bArr == null) {
            return null;
        }
        int length = bArr.length * 8;
        if (length == 0) {
            return "";
        }
        int i2 = length % 24;
        int i3 = length / 24;
        char[] cArr = new char[((i2 != 0 ? i3 + 1 : i3) * 4)];
        int i4 = 0;
        int i5 = 0;
        while (i4 < i3) {
            int i6 = i + 1;
            byte b = bArr[i];
            int i7 = i6 + 1;
            byte b2 = bArr[i6];
            int i8 = i7 + 1;
            byte b3 = bArr[i7];
            byte b4 = (byte) (b2 & 15);
            byte b5 = (byte) (b & 3);
            byte b6 = (b & Byte.MIN_VALUE) == 0 ? (byte) (b >> 2) : (byte) ((b >> 2) ^ 192);
            byte b7 = (b2 & Byte.MIN_VALUE) == 0 ? (byte) (b2 >> 4) : (byte) ((b2 >> 4) ^ 240);
            int i9 = (b3 & Byte.MIN_VALUE) == 0 ? b3 >> 6 : (b3 >> 6) ^ 252;
            int i10 = i5 + 1;
            cArr[i5] = lookUpBase64Alphabet[b6];
            int i11 = i10 + 1;
            cArr[i10] = lookUpBase64Alphabet[b7 | (b5 << 4)];
            int i12 = i11 + 1;
            cArr[i11] = lookUpBase64Alphabet[((byte) i9) | (b4 << 2)];
            cArr[i12] = lookUpBase64Alphabet[b3 & 63];
            i4++;
            i5 = i12 + 1;
            i = i8;
        }
        if (i2 == 8) {
            byte b8 = bArr[i];
            byte b9 = (byte) (b8 & 3);
            int i13 = i5 + 1;
            cArr[i5] = lookUpBase64Alphabet[(b8 & Byte.MIN_VALUE) == 0 ? (byte) (b8 >> 2) : (byte) ((b8 >> 2) ^ 192)];
            int i14 = i13 + 1;
            cArr[i13] = lookUpBase64Alphabet[b9 << 4];
            int i15 = i14 + 1;
            cArr[i14] = PAD;
            int i16 = i15 + 1;
            cArr[i15] = PAD;
        } else if (i2 == 16) {
            byte b10 = bArr[i];
            byte b11 = bArr[i + 1];
            byte b12 = (byte) (b11 & 15);
            byte b13 = (byte) (b10 & 3);
            byte b14 = (b10 & Byte.MIN_VALUE) == 0 ? (byte) (b10 >> 2) : (byte) ((b10 >> 2) ^ 192);
            byte b15 = (b11 & Byte.MIN_VALUE) == 0 ? (byte) (b11 >> 4) : (byte) ((b11 >> 4) ^ 240);
            int i17 = i5 + 1;
            cArr[i5] = lookUpBase64Alphabet[b14];
            int i18 = i17 + 1;
            cArr[i17] = lookUpBase64Alphabet[b15 | (b13 << 4)];
            int i19 = i18 + 1;
            cArr[i18] = lookUpBase64Alphabet[b12 << 2];
            int i20 = i19 + 1;
            cArr[i19] = PAD;
        }
        return new String(cArr);
    }

    private int removeWhiteSpace(char[] cArr) {
        int i;
        int i2 = 0;
        if (cArr != null) {
            int length = cArr.length;
            int i3 = 0;
            while (i3 < length) {
                if (!isWhiteSpace(cArr[i3])) {
                    i = i2 + 1;
                    cArr[i2] = cArr[i3];
                } else {
                    i = i2;
                }
                i3++;
                i2 = i;
            }
        }
        return i2;
    }

    class PayResult {
        private String memo;
        private String result;
        private String resultStatus;

        public PayResult(String str) {
            if (!TextUtils.isEmpty(str)) {
                String[] split = str.split(C1536i.f3510b);
                for (String str2 : split) {
                    if (str2.startsWith(C1538k.f3517a)) {
                        this.resultStatus = gatValue(str2, C1538k.f3517a);
                    }
                    if (str2.startsWith("result")) {
                        this.result = gatValue(str2, "result");
                    }
                    if (str2.startsWith(C1538k.f3518b)) {
                        this.memo = gatValue(str2, C1538k.f3518b);
                    }
                }
            }
        }

        public String toString() {
            return "resultStatus={" + this.resultStatus + "};memo={" + this.memo + "};result={" + this.result + C1536i.f3512d;
        }

        public String gatValue(String str, String str2) {
            String str3 = str2 + "={";
            return str.substring(str3.length() + str.indexOf(str3), str.lastIndexOf(C1536i.f3512d));
        }

        public String getResultStatus() {
            return this.resultStatus;
        }

        public String getMemo() {
            return this.memo;
        }

        public String getResult() {
            return this.result;
        }
    }
}
