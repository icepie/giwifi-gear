package com.gbcom.gwifi.util.cache;

import android.util.Base64;
import com.gbcom.gwifi.functions.template.builder.GridBuilder;
import com.gbcom.gwifi.util.C3467s;
import java.p456io.ByteArrayInputStream;
import java.p456io.ByteArrayOutputStream;
import java.p456io.IOException;
import java.p456io.ObjectInputStream;
import java.p456io.ObjectOutputStream;
import java.util.List;

public class CacheEdu extends CacheBase {
    private static CacheEdu instance = null;
    private final String EDU_LIST = "edu_list";
    private final String FIRST_EDU = "first_edu";
    private final String NOTIFY_TITLE = "notify_title";
    private final String SCHOOL_NOTIFY_NUM = "school_notify_num";
    private final String SCHOOL_NOTIFY_TIME = "school_notify_time";

    public static CacheEdu getInstance() {
        if (instance == null) {
            instance = new CacheEdu();
        }
        return instance;
    }

    public void setEduNotifyTitle(String str) {
        setStringValue("notify_title", str);
    }

    public String getEduNotifyTitle() {
        return getStringValue("notify_title", "");
    }

    public void setEduNotifyNum(int i) {
        setIntValue("school_notify_num", i);
    }

    public int getEduNotifyNum() {
        return getIntValue("school_notify_num", 0);
    }

    public void setEduNotifyLastTime(long j) {
        setLongValue("school_notify_time", j);
    }

    public Long getEduNotifyLastTime() {
        return Long.valueOf(getLongValue("school_notify_time", 0));
    }

    public void setEduList(List<GridBuilder.C3146a> list) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(list);
            String str = new String(Base64.encode(byteArrayOutputStream.toByteArray(), 0));
            objectOutputStream.close();
            setStringValue("edu_list", str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<GridBuilder.C3146a> getEduList() {
        String stringValue = getStringValue("edu_list", "");
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

    public void setFirstEdu(boolean z) {
        setBooleanValue("first_edu", z);
    }

    public boolean getFirstEdu() {
        return getBooleanValue("first_edu", true);
    }
}
