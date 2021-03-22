package com.gbcom.gwifi.util;

import com.taobao.agoo.p359a.p360a.AbstractC5718b;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import java.p456io.UnsupportedEncodingException;
import java.util.HashMap;

public class CommonMsg {
    private static final String TAG = "CommonMsg";
    private String data;
    private Integer resultCode;
    private String resultMsg;

    public static CommonMsg deSerializeJson(byte[] bArr) {
        String str;
        UnsupportedEncodingException e;
        try {
            str = new String(bArr, "UTF-8").trim();
            try {
                return (CommonMsg) JsonUtil.m14386a(str, CommonMsg.class);
            } catch (UnsupportedEncodingException e2) {
                e = e2;
            }
        } catch (UnsupportedEncodingException e3) {
            e = e3;
            str = null;
            e.printStackTrace();
            Log.m14403e(TAG, str + e.getMessage());
            return null;
        }
    }

    public static CommonMsg deSerializeJson(String str) {
        return (CommonMsg) JsonUtil.m14386a(str, CommonMsg.class);
    }

    public static HashMap<String, Object> commonMsgMap(Integer num, String str) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put(AbstractC5718b.JSON_ERRORCODE, num);
        hashMap.put("resultMsg", str);
        return hashMap;
    }

    public static HashMap<String, Object> commonMsgMap(Integer num, String str, Object obj) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put(AbstractC5718b.JSON_ERRORCODE, num);
        hashMap.put("resultMsg", str);
        hashMap.put("data", obj);
        return hashMap;
    }

    public static HashMap<String, Object> createMap(String str, String str2, String str3) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_MAC, str);
        hashMap.put("token", str2);
        hashMap.put("data", str3);
        return hashMap;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String str) {
        this.data = str;
    }

    public Integer getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(Integer num) {
        this.resultCode = num;
    }

    public String getResultMsg() {
        return this.resultMsg;
    }

    public void setResultMsg(String str) {
        this.resultMsg = str;
    }
}
