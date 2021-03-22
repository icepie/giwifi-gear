package com.gbcom.gwifi.util.cache;

import android.util.Base64;
import com.gbcom.gwifi.util.C3467s;
import java.p456io.ByteArrayInputStream;
import java.p456io.ByteArrayOutputStream;
import java.p456io.IOException;
import java.p456io.ObjectInputStream;
import java.p456io.ObjectOutputStream;
import java.util.HashMap;
import java.util.List;

public class CacheAd extends CacheBase {
    private static final String AD_ACCESS_STRATEGY = "ad_access_strategy";
    private static final String AD_CONN_FLOW_LIST = "ad_conn_flow_list";
    private static final String AD_CONN_TP_LIST = "ad_conn_tp_list";
    private static final String AD_ENTERTAINMENT_FLOW_LIST = "ad_entertainment_flow_list";
    private static final String AD_ENTERTAINMENT_TP_LIST = "ad_entertainment_tp_list";
    private static final String AD_EVENT_LIST = "ad_event_list";
    private static final String AD_EXIT_VIEW_LIST = "ad_exit_flow_list";
    private static final String AD_IMG__URL = "ad_img_url";
    private static final String AD_JUMP_IGNORE = "ad_jump_ignore";
    private static final String AD_LOCAL_FLOW_LIST = "ad_local_flow_list";
    private static final String AD_LOCAL_TP_LIST = "ad_local_tp_list";
    private static final String AD_PRODUCT_ID = "ad_product_id";
    private static final String AD_PRODUCT_TYPE = "ad_product_type";
    private static final String AD_SHOW_TIME_SECOND = "ad_show_time_second";
    private static final String AD_SOURCE_TYPE = "ad_source_type";
    private static final String AD_START_STATE = "ad_start_state";
    private static final String AD_WEB_URL = "ad_web_url";
    private static CacheAd instance = null;

    public static CacheAd getInstance() {
        if (instance == null) {
            instance = new CacheAd();
        }
        return instance;
    }

    public void setAdList(List<HashMap<String, Object>> list, String str) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(list);
            String str2 = new String(Base64.encode(byteArrayOutputStream.toByteArray(), 0));
            objectOutputStream.close();
            setStringValue(str, str2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<HashMap<String, Object>> getAdList(String str) {
        String stringValue = getStringValue(str);
        if (stringValue == null || C3467s.m14513e(stringValue)) {
            return null;
        }
        try {
            return (List) new ObjectInputStream(new ByteArrayInputStream(Base64.decode(stringValue.getBytes(), 0))).readObject();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public void setAdConnFlowList(List<HashMap<String, Object>> list) {
        setAdList(list, AD_CONN_FLOW_LIST);
    }

    public List<HashMap<String, Object>> getAdConnFlowList() {
        return getAdList(AD_CONN_FLOW_LIST);
    }

    public void setAdEntertainmentFlowList(List<HashMap<String, Object>> list) {
        setAdList(list, AD_ENTERTAINMENT_FLOW_LIST);
    }

    public List<HashMap<String, Object>> getAdEntertainmentFlowList() {
        return getAdList(AD_ENTERTAINMENT_FLOW_LIST);
    }

    public void setAdLocalFlowList(List<HashMap<String, Object>> list) {
        setAdList(list, AD_LOCAL_FLOW_LIST);
    }

    public List<HashMap<String, Object>> getAdLocalFlowList() {
        return getAdList(AD_LOCAL_FLOW_LIST);
    }

    public void setAdConnTpList(List<HashMap<String, Object>> list) {
        setAdList(list, AD_CONN_TP_LIST);
    }

    public List<HashMap<String, Object>> getAdConnTpList() {
        return getAdList(AD_CONN_TP_LIST);
    }

    public void setAdEntertainmentTpList(List<HashMap<String, Object>> list) {
        setAdList(list, AD_ENTERTAINMENT_TP_LIST);
    }

    public List<HashMap<String, Object>> getAdEntertainmentTpList() {
        return getAdList(AD_ENTERTAINMENT_TP_LIST);
    }

    public void setAdLocalTpList(List<HashMap<String, Object>> list) {
        setAdList(list, AD_LOCAL_TP_LIST);
    }

    public List<HashMap<String, Object>> getAdLocalTpList() {
        return getAdList(AD_LOCAL_TP_LIST);
    }

    public void setAdExitViewList(List<HashMap<String, Object>> list) {
        setAdList(list, AD_EXIT_VIEW_LIST);
    }

    public List<HashMap<String, Object>> getAdExitViewList() {
        return getAdList(AD_EXIT_VIEW_LIST);
    }

    public void setAdEventList(List<HashMap<String, Object>> list) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(list);
            String str = new String(Base64.encode(byteArrayOutputStream.toByteArray(), 0));
            objectOutputStream.close();
            setStringValue(AD_EVENT_LIST, str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<HashMap<String, Object>> getAdEventList() {
        List<HashMap<String, Object>> list;
        String stringValue = getStringValue(AD_EVENT_LIST);
        if (C3467s.m14513e(stringValue)) {
            return null;
        }
        try {
            list = (List) new ObjectInputStream(new ByteArrayInputStream(Base64.decode(stringValue.getBytes(), 0))).readObject();
        } catch (IOException e) {
            e.printStackTrace();
            list = null;
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
            list = null;
        }
        return list;
    }

    public void setAdImgUrl(String str) {
        setStringValue(AD_IMG__URL, str);
    }

    public String getAdImgUrl() {
        return getStringValue(AD_IMG__URL);
    }

    public void setAdWebUrl(String str) {
        setStringValue(AD_WEB_URL, str);
    }

    public String getAdWebUrl() {
        return getStringValue(AD_WEB_URL);
    }

    public void setAdStartState(int i) {
        setIntValue(AD_START_STATE, i);
    }

    public int getAdStartState() {
        return getIntValue(AD_START_STATE);
    }

    public void setAdProductType(String str) {
        setStringValue(AD_PRODUCT_TYPE, str);
    }

    public String getAdProductType() {
        return getStringValue(AD_PRODUCT_TYPE);
    }

    public void setAdProductId(int i) {
        setIntValue(AD_PRODUCT_ID, i);
    }

    public int getAdProductId() {
        return getIntValue(AD_PRODUCT_ID);
    }

    public void setAdSourceType(int i) {
        setIntValue(AD_SOURCE_TYPE, i);
    }

    public int getAdSourceType() {
        return getIntValue(AD_SOURCE_TYPE);
    }

    public void setAdShowTime(int i) {
        setIntValue(AD_SHOW_TIME_SECOND, i);
    }

    public int getAdShowTime() {
        return getIntValue(AD_SHOW_TIME_SECOND, 5);
    }

    public void setAdJumpIgnore(int i) {
        setIntValue(AD_JUMP_IGNORE, i);
    }

    public int getAdJumpIgnore() {
        return getIntValue(AD_JUMP_IGNORE, 1);
    }

    public void setAdAccessStrategy(int i) {
        setIntValue(AD_ACCESS_STRATEGY, i);
    }

    public int getAdAccessStrategy() {
        return getIntValue(AD_ACCESS_STRATEGY, 0);
    }

    public List<HashMap<String, Object>> getAdByLayoutType(int i) {
        if (i == 1) {
            return getInstance().getAdConnTpList();
        }
        if (i == 2) {
            return getInstance().getAdEntertainmentTpList();
        }
        if (i == 3) {
            return getInstance().getAdLocalTpList();
        }
        return null;
    }
}
