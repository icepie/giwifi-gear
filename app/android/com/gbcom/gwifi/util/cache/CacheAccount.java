package com.gbcom.gwifi.util.cache;

import android.content.SharedPreferences;
import android.util.Base64;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.functions.location.RabbitMQParam;
import com.gbcom.gwifi.third.umeng.push.GiwifiPushAgent;
import com.gbcom.gwifi.util.C3467s;
import com.gbcom.gwifi.util.Constant;
import com.gbcom.gwifi.util.GsonUtil;
import com.gbcom.gwifi.util.JsonUtil;
import com.gbcom.gwifi.util.Log;
import com.gbcom.gwifi.util.SystemUtil;
import com.gbcom.gwifi.util.p256a.TokenTimer;
import java.p456io.ByteArrayInputStream;
import java.p456io.ByteArrayOutputStream;
import java.p456io.IOException;
import java.p456io.ObjectInputStream;
import java.p456io.ObjectOutputStream;
import java.util.HashMap;

public class CacheAccount extends CacheBase {
    private static final String CACHE_APP_CUSTOM_ICON_STATE = "CACHE_APP_CUSTOM_ICON_STATE";
    private static final String CACHE_APP_KEFU_STATE = "CACHE_APP_KEFU_STATE";
    private static final String CACHE_APP_LOGIN_SN_STATE = "CACHE_APP_LOGIN_SN_STATE";
    private static final String CACHE_AVATAR_URL = "CACHE_AVATAR_URL";
    private static final String CACHE_BALANCE = "CACHE_BALANCE";
    private static final String CACHE_CIRCLE_AVATAR_URL = "CACHE_CIRCLE_AVATAR_URL";
    private static final String CACHE_CIRCLE_BIRTH = "CACHE_CIRCLE_BIRTH";
    private static final String CACHE_CIRCLE_CITY = "CACHE_CIRCLE_CITY";
    private static final String CACHE_CIRCLE_SEX = "CACHE_CIRCLE_SEX";
    private static final String CACHE_COLLEGE_ID = "CACHE_COLLEGE_ID";
    private static final String CACHE_DEPARTMENT_ID = "CACHE_DEPARTMENT_ID";
    private static final String CACHE_EMAIL = "CACHE_EMAIL";
    private static final String CACHE_GENDER = "CACHE_GENDER";
    private static final String CACHE_GENDER_NAME = "CACHE_GENDER_NAME";
    private static final String CACHE_HOTSPOT_GROUP_ID = "CACHE_HOTSPOT_GROUP_ID";
    private static final String CACHE_HOTSPOT_GROUP_ID_SHOW = "CACHE_HOTSPOT_GROUP_ID_SHOW";
    private static final String CACHE_HOTSPOT_GROUP_NAME = "CACHE_HOTSPOT_GROUP_NAME";
    private static final String CACHE_HOTSPOT_GROUP_TYPE = "CACHE_HOTSPOT_GROUP_TYPE";
    private static final String CACHE_IDENTITY_NAME = "CACHE_IDENTITY_NAME";
    private static final String CACHE_IDENTITY_TYPE = "CACHE_IDENTITY_TYPE";
    private static final String CACHE_IDENTITY_TYPE_NAME = "CACHE_IDENTITY_TYPE_NAME";
    private static final String CACHE_LAN_REMAIN_TIME = "CACHE_LAN_REMAIN_TIME";
    private static final String CACHE_LAST_LOGIN_PHONE = "CACHE_LAST_LOGIN_PHONE";
    private static final String CACHE_LAST_LOGIN_TYPE = "CACHE_LAST_LOGIN_TYPE";
    private static final String CACHE_LAST_LOGIN_TYPE_2 = "CACHE_LAST_LOGIN_TYPE_2";
    private static final String CACHE_LAST_SIGN_TIME = "CACHE_LAST_SIGN_TIME";
    private static final String CACHE_LOGIN_PHONE = "CACHE_LOGIN_PHONE";
    private static final String CACHE_LOGIN_SERVICE_TYPE = "CACHE_LOGIN_SERVICE_TYPE";
    private static final String CACHE_LOGIN_STATIC_PASSWORD = "CACHE_LOGIN_STATIC_PASSWORD";
    private static final String CACHE_NICK_NAME = "CACHE_NICK_NAME";
    private static final String CACHE_REMAIN_TIME = "CACHE_REMAIN_TIME";
    private static final String CACHE_REMAIN_TIME_SECOND = "CACHE_REMAIN_TIME_SECOND";
    private static final String CACHE_REMAIN_TIME_SECOND_VALUE = "CACHE_REMAIN_TIME_SECOND_VALUE";
    private static final String CACHE_REPORT_LOCATION_PARAM = "CACHE_REPORT_LOCATION_PARAM";
    private static final String CACHE_REPORT_LOCATION_SWITCH = "CACHE_REPORT_LOCATION_SWITCH";
    private static final String CACHE_SEND_CONTENT = "CACHE_SEND_CONTENT";
    private static final String CACHE_SEND_PHONE = "CACHE_SEND_PHONE";
    private static final String CACHE_SIGN_USER_AGREEMENT = "CACHE_SIGN_USER_AGREEMENT";
    private static final String CACHE_SIGN_USER_AGREEMENT_STATE = "CACHE_SIGN_USER_AGREEMENT_STATE";
    private static final String CACHE_STUDENT_NUMBER = "CACHE_STUDENT_NUMBER";
    private static final String CACHE_SUB_DATA = "CACHE_SUB_DATA";
    private static final String CACHE_TOTAL_SCORE = "CACHE_TOTAL_SCORE";
    private static final String CACHE_USER_BALANCE = "CACHE_USER_BALANCE";
    private static final String CACHE_USER_ID = "CACHE_USER_ID";
    private static final String CACHE_USER_LEVEL = "CACHE_USER_LEVEL";
    private static final String CACHE_USER_LEVEL_NAME = "CACHE_USER_LEVEL_NAME";
    private static final String CACHE_USER_TOKEN = "CACHE_USER_TOKEN";
    private static final String CACHE_WILL_STOP_TIME = "CACHE_WILL_STOP_TIME";
    private static final String TAG = "CacheAccount";
    private static CacheAccount instance = null;
    private final String FACE_FILE_MD5 = "face_file_md5";
    private final String FACE_FILE_SIZE = "face_file_size";
    private final String FACE_FILE_URL = "face_file_url";
    private final String LOGIN_TOKEN = "login_token";
    private final String LOGIN_TOKEN_EXPIRES = "login_token_expires";
    private final String LOGIN_TOKEN_PLATFORM = "LOGIN_TOKEN_PLATFORM";

    public static CacheAccount getInstance() {
        if (instance == null) {
            instance = new CacheAccount();
        }
        return instance;
    }

    /* access modifiers changed from: protected */
    @Override // com.gbcom.gwifi.util.cache.CacheBase
    public void setIntValue(String str, int i) {
        if (!str.equals(CACHE_HOTSPOT_GROUP_ID)) {
            super.setIntValue(str, i);
        } else if (!SystemUtil.m14533f()) {
            int intValue = getIntValue(str);
            Log.m14398b(TAG, "setHotspotGroupId: oldValue=" + intValue + ",value=" + i);
            super.setIntValue(str, i);
            if (i != intValue && GBApplication.instance().getCurrentActivity() != null) {
                GBApplication.instance().getCurrentActivity().switchIcon(intValue, i, true);
            }
        }
    }

    public void setUserLevel(int i) {
        setIntValue(CACHE_USER_LEVEL, i);
    }

    public int getUserLevel() {
        return getIntValue(CACHE_USER_LEVEL, 0);
    }

    public void setUserLevelName(String str) {
        setStringValue(CACHE_USER_LEVEL_NAME, str);
    }

    public String getUserLevelName() {
        return getStringValue(CACHE_USER_LEVEL_NAME);
    }

    public int getUserId() {
        return getIntValue(CACHE_USER_ID, 0);
    }

    public Integer getHotspotGroupId() {
        int i = 0;
        if (SystemUtil.m14533f()) {
            String string = GBApplication.instance().getResources().getString(C2425R.C2429string.project_id);
            if (C3467s.m14518j(string)) {
                i = Integer.parseInt(string);
            }
        } else {
            i = getIntValue(CACHE_HOTSPOT_GROUP_ID, 0);
        }
        return Integer.valueOf(i);
    }

    public Integer getHotspotGroupIdShow() {
        return Integer.valueOf(getIntValue(CACHE_HOTSPOT_GROUP_ID_SHOW));
    }

    public void setHotspotGroupIdShow(int i) {
        setIntValue(CACHE_HOTSPOT_GROUP_ID_SHOW, i);
    }

    public int getCollegeId() {
        return getIntValue(CACHE_COLLEGE_ID, 0);
    }

    public Integer getIdentityType() {
        return Integer.valueOf(getIntValue(CACHE_IDENTITY_TYPE, 0));
    }

    public void setIdentityName(String str) {
        setStringValue(CACHE_IDENTITY_NAME, str);
    }

    public String getIdentityName() {
        return getStringValue(CACHE_IDENTITY_NAME, "");
    }

    public int getGender() {
        return getIntValue(CACHE_GENDER, 0);
    }

    public void setGenderName(String str) {
        setStringValue(CACHE_GENDER_NAME, str);
    }

    public String getGenderName() {
        return getStringValue(CACHE_GENDER_NAME, Constant.f13330p);
    }

    public void setNickName(String str) {
        setStringValue(CACHE_NICK_NAME, str);
    }

    public String getNickName() {
        return getStringValue(CACHE_NICK_NAME, "");
    }

    public void setAvatarUrl(String str) {
        setStringValue(CACHE_AVATAR_URL, str);
    }

    public String getAvatarUrl() {
        return getStringValue(CACHE_AVATAR_URL, "");
    }

    public void setEmail(String str) {
        setStringValue(CACHE_EMAIL, str);
    }

    public String getEmail() {
        return getStringValue(CACHE_EMAIL, "");
    }

    public String getRemainTime() {
        return getStringValue(CACHE_REMAIN_TIME, "");
    }

    public String getLanRemainTime() {
        return getStringValue(CACHE_LAN_REMAIN_TIME, "");
    }

    public String getBalance() {
        return getStringValue(CACHE_BALANCE, "");
    }

    public String getTotalScore() {
        return getStringValue(CACHE_TOTAL_SCORE, "");
    }

    public HashMap<String, Object> getSubdata() {
        String stringValue = getStringValue(CACHE_SUB_DATA);
        if (!stringValue.equals("")) {
            return (HashMap) JsonUtil.m14386a(stringValue, HashMap.class);
        }
        return null;
    }

    public void resetCacheAccountBean() {
        setStringValue(CACHE_IDENTITY_TYPE_NAME, "");
        setStringValue(CACHE_IDENTITY_NAME, "");
        setStringValue(CACHE_STUDENT_NUMBER, "");
    }

    public void setLastLoginType(int i) {
        setIntValue(CACHE_LAST_LOGIN_TYPE_2, i);
        if (!cacheData.containsKey(CACHE_LAST_LOGIN_TYPE)) {
            cacheData.put(CACHE_LAST_LOGIN_TYPE, "" + i);
        } else if (cacheData.get(CACHE_LAST_LOGIN_TYPE) instanceof String) {
            cacheData.put(CACHE_LAST_LOGIN_TYPE, "" + i);
        } else {
            cacheData.put(CACHE_LAST_LOGIN_TYPE, Integer.valueOf(i));
        }
        SharedPreferences sharedPreferences = GBApplication.instance().getSharedPreferences();
        if (sharedPreferences.contains(CACHE_LAST_LOGIN_TYPE)) {
            try {
                sharedPreferences.getString(CACHE_LAST_LOGIN_TYPE, "");
                sharedPreferences.edit().putString(CACHE_LAST_LOGIN_TYPE, "" + i).commit();
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    sharedPreferences.getInt(CACHE_LAST_LOGIN_TYPE, 0);
                    sharedPreferences.edit().putInt(CACHE_LAST_LOGIN_TYPE, i).commit();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        } else {
            sharedPreferences.edit().putString(CACHE_LAST_LOGIN_TYPE, "" + i).commit();
        }
    }

    public int getLastLoginType() {
        if (cacheData.containsKey(CACHE_LAST_LOGIN_TYPE_2)) {
            return ((Integer) cacheData.get(CACHE_LAST_LOGIN_TYPE_2)).intValue();
        }
        SharedPreferences sharedPreferences = GBApplication.instance().getSharedPreferences();
        if (sharedPreferences.contains(CACHE_LAST_LOGIN_TYPE_2)) {
            int i = sharedPreferences.getInt(CACHE_LAST_LOGIN_TYPE_2, 0);
            cacheData.put(CACHE_LAST_LOGIN_TYPE_2, Integer.valueOf(i));
            return i;
        } else if (cacheData.containsKey(CACHE_LAST_LOGIN_TYPE)) {
            Object obj = cacheData.get(CACHE_LAST_LOGIN_TYPE);
            if (obj instanceof String) {
                int intValue = Integer.valueOf((String) obj).intValue();
                setIntValue(CACHE_LAST_LOGIN_TYPE_2, intValue);
                return intValue;
            }
            int intValue2 = ((Integer) obj).intValue();
            setIntValue(CACHE_LAST_LOGIN_TYPE_2, intValue2);
            return intValue2;
        } else {
            if (sharedPreferences.contains(CACHE_LAST_LOGIN_TYPE)) {
                try {
                    String string = sharedPreferences.getString(CACHE_LAST_LOGIN_TYPE, "");
                    if (!C3467s.m14513e(string)) {
                        int intValue3 = Integer.valueOf(string).intValue();
                        setIntValue(CACHE_LAST_LOGIN_TYPE_2, intValue3);
                        return intValue3;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    int i2 = sharedPreferences.getInt(CACHE_LAST_LOGIN_TYPE, 0);
                    if (i2 != 0) {
                        setIntValue(CACHE_LAST_LOGIN_TYPE_2, i2);
                        return i2;
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            setIntValue(CACHE_LAST_LOGIN_TYPE_2, 0);
            return 0;
        }
    }

    public void setLastLoginPhone(String str) {
        setStringValue(CACHE_LAST_LOGIN_PHONE, str);
    }

    public String getLastLoginPhone() {
        return getStringValue(CACHE_LAST_LOGIN_PHONE, "");
    }

    public void setLoginPhone(String str) {
        String stringValue = getStringValue(CACHE_LOGIN_PHONE, "");
        setStringValue(CACHE_LOGIN_PHONE, str);
        if (!C3467s.m14513e(str) && !str.equals(stringValue)) {
            GiwifiPushAgent.getInstance(GBApplication.instance()).checkReportToken();
        }
    }

    public String getLoginPhone() {
        return getStringValue(CACHE_LOGIN_PHONE, "");
    }

    public void setLoginStaticPassword(String str) {
        setStringValue(CACHE_LOGIN_STATIC_PASSWORD, str);
    }

    public String getLoginStaticPassword() {
        return getStringValue(CACHE_LOGIN_STATIC_PASSWORD, "");
    }

    public void setLoginServiceType(int i) {
        setIntValue(CACHE_LOGIN_SERVICE_TYPE, i);
    }

    public int getLoginServiceType() {
        return getIntValue(CACHE_LOGIN_SERVICE_TYPE, 1);
    }

    public void clearCacheAccountPassword(String str) {
        setUserId(0);
        setLastLoginPhone(str);
        setLoginPhone("");
        setLoginStaticPassword("");
        SharedPreferences sharedPreferences = GBApplication.instance().getSharedPreferences();
        if (sharedPreferences.contains(Constant.f13323i)) {
            sharedPreferences.edit().remove(Constant.f13323i).commit();
        }
        if (sharedPreferences.contains("user_static_password")) {
            sharedPreferences.edit().remove("user_static_password").commit();
        }
        if (sharedPreferences.contains("last_login_type")) {
            sharedPreferences.edit().remove("last_login_type").commit();
        }
        setLoginToken("");
        setLoginTokenExpires(0);
        setFaceFileUrl("");
        setFaceFileMd5("");
        TokenTimer.m14089c();
        setCircleAvatarUrl("");
        setCircleSex(0);
        setCircleBirth("");
        setCircleCity("");
    }

    public void setCacheAccountBean(int i, String str, int i2, int i3, int i4, int i5) {
        setIntValue(CACHE_USER_ID, i);
        setStringValue(CACHE_AVATAR_URL, str);
        setIntValue(CACHE_HOTSPOT_GROUP_ID, i2);
        setIntValue(CACHE_COLLEGE_ID, i3);
        setIntValue(CACHE_DEPARTMENT_ID, i4);
        setIntValue(CACHE_IDENTITY_TYPE, i5);
    }

    public void setCacheAccountBean(int i, int i2, String str, String str2, String str3, String str4) {
        setIntValue(CACHE_HOTSPOT_GROUP_TYPE, i);
        setIntValue(CACHE_HOTSPOT_GROUP_ID, i2);
        setStringValue(CACHE_HOTSPOT_GROUP_NAME, str);
        setStringValue(CACHE_IDENTITY_NAME, str2);
        setStringValue(CACHE_IDENTITY_TYPE_NAME, str3);
        setStringValue(CACHE_STUDENT_NUMBER, str4);
    }

    public void setCacheAccountBean(int i, int i2, String str, int i3, int i4, int i5, int i6, String str2, String str3, int i7, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, HashMap<String, Object> hashMap) {
        setIntValue(CACHE_USER_ID, i);
        setIntValue(CACHE_USER_LEVEL, i2);
        if (str != null) {
            setStringValue(CACHE_USER_LEVEL_NAME, str);
        }
        setIntValue(CACHE_HOTSPOT_GROUP_ID, i3);
        setIntValue(CACHE_IDENTITY_TYPE, i4);
        setIntValue(CACHE_COLLEGE_ID, i5);
        setIntValue(CACHE_DEPARTMENT_ID, i6);
        setStringValue(CACHE_IDENTITY_NAME, str2);
        setStringValue(CACHE_STUDENT_NUMBER, str3);
        setIntValue(CACHE_GENDER, i7);
        setStringValue(CACHE_GENDER_NAME, str4);
        setStringValue(CACHE_NICK_NAME, str5);
        setStringValue(CACHE_AVATAR_URL, str6);
        setStringValue(CACHE_EMAIL, str7);
        if (str8 != null) {
            setStringValue(CACHE_REMAIN_TIME, str8);
        }
        if (str9 != null) {
            setStringValue(CACHE_LAN_REMAIN_TIME, str9);
        }
        if (str10 != null) {
            setStringValue(CACHE_BALANCE, str10);
        }
        if (str11 != null) {
            setStringValue(CACHE_TOTAL_SCORE, str11);
        }
        if (hashMap != null && hashMap.size() > 0) {
            setStringValue(CACHE_SUB_DATA, JsonUtil.m14388a(hashMap));
        }
    }

    public void setUserId(int i) {
        setIntValue(CACHE_USER_ID, i);
    }

    public void setIdentityType(int i) {
        setIntValue(CACHE_IDENTITY_TYPE, i);
    }

    public void setCollegeId(int i) {
        setIntValue(CACHE_COLLEGE_ID, i);
    }

    public void setDepartmentId(int i) {
        setIntValue(CACHE_DEPARTMENT_ID, i);
    }

    public int getDepartmentId() {
        return getIntValue(CACHE_DEPARTMENT_ID, 0);
    }

    public String getUserToken() {
        return getStringValue(CACHE_USER_TOKEN, "");
    }

    public void setUserToken(String str) {
        setStringValue(CACHE_USER_TOKEN, str);
    }

    public void updateInfo(String str, String str2) {
        setLoginPhone(str);
        setUserToken(str2);
    }

    public int getHotspotGroupType() {
        return getIntValue(CACHE_HOTSPOT_GROUP_TYPE, 0);
    }

    public String getHotspotGroupName() {
        return getStringValue(CACHE_HOTSPOT_GROUP_NAME, "");
    }

    public String getIdentityTypeName() {
        return getStringValue(CACHE_IDENTITY_TYPE_NAME, "");
    }

    public String getStudentNumber() {
        return getStringValue(CACHE_STUDENT_NUMBER, "");
    }

    public void setSignAgreementTime(HashMap<String, Integer> hashMap) {
        setIntHashMapValue(CACHE_SIGN_USER_AGREEMENT, hashMap);
    }

    public HashMap getSignAgreementTime() {
        return getHashMapValue(CACHE_SIGN_USER_AGREEMENT);
    }

    public void setSignAgreementState(int i) {
        setIntValue(CACHE_SIGN_USER_AGREEMENT_STATE, i);
    }

    public int getSignAgreementState() {
        return getIntValue(CACHE_SIGN_USER_AGREEMENT_STATE, 0);
    }

    public void setRemainTimeSecond(HashMap<String, Integer> hashMap) {
        setIntHashMapValue(CACHE_REMAIN_TIME_SECOND, hashMap);
    }

    public HashMap getRemainTimeSecond() {
        return getHashMapValue(CACHE_REMAIN_TIME_SECOND);
    }

    public void setRemainTimeSecondValue(long j) {
        setLongValue(CACHE_REMAIN_TIME_SECOND_VALUE, j);
    }

    public long getRemainTimeSecondValue() {
        return getLongValue(CACHE_REMAIN_TIME_SECOND_VALUE, 0);
    }

    public void setWillStopTime(HashMap<String, Integer> hashMap) {
        setIntHashMapValue(CACHE_WILL_STOP_TIME, hashMap);
    }

    public HashMap getWillStopTime() {
        return getHashMapValue(CACHE_WILL_STOP_TIME);
    }

    public void setCacheSendPhone(String str) {
        setStringValue(CACHE_SEND_PHONE, str);
    }

    public String getCacheSendPhone() {
        return getStringValue(CACHE_SEND_PHONE, "106912114");
    }

    public void setCacheSendContent(String str) {
        setStringValue(CACHE_SEND_CONTENT, str);
    }

    public String getCacheSendContent() {
        return getStringValue(CACHE_SEND_CONTENT, "gwifi上网");
    }

    public void setUserBalance(HashMap<Integer, String> hashMap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(hashMap);
            String str = new String(Base64.encode(byteArrayOutputStream.toByteArray(), 0));
            objectOutputStream.close();
            setStringValue(CACHE_USER_BALANCE, str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HashMap<Integer, String> getUserBalance() {
        String stringValue = getStringValue(CACHE_USER_BALANCE);
        if (stringValue == null || C3467s.m14513e(stringValue)) {
            return null;
        }
        try {
            return (HashMap) new ObjectInputStream(new ByteArrayInputStream(Base64.decode(stringValue.getBytes(), 0))).readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setLastSignTime(long j) {
        setLongValue(CACHE_LAST_SIGN_TIME, j);
    }

    public long getLastSignTime() {
        return getLongValue(CACHE_LAST_SIGN_TIME, 0);
    }

    public void setAppLoginSnState(int i) {
        setIntValue(CACHE_APP_LOGIN_SN_STATE, i);
    }

    public int getAppLoginSnState() {
        return getIntValue(CACHE_APP_LOGIN_SN_STATE, 1);
    }

    public void setAppCustomIconState(int i) {
        setIntValue(CACHE_APP_CUSTOM_ICON_STATE, i);
    }

    public int getAppCustomIconState() {
        return getIntValue(CACHE_APP_CUSTOM_ICON_STATE, 0);
    }

    public void setAppKefuState(int i) {
        setIntValue(CACHE_APP_KEFU_STATE, i);
    }

    public int getAppKefuState() {
        return getIntValue(CACHE_APP_KEFU_STATE, 1);
    }

    public void setLoginToken(String str) {
        setStringValue("login_token", str);
    }

    public String getLoginToken() {
        return getStringValue("login_token");
    }

    public void setLoginTokenExpires(long j) {
        setLongValue("login_token_expires", j);
    }

    public long getLoginTokenExpires() {
        return getLongValue("login_token_expires");
    }

    public void setLoginTokenPlatform(String str) {
        setStringValue("LOGIN_TOKEN_PLATFORM", str);
    }

    public String getLoginTokenPlatform() {
        return getStringValue("LOGIN_TOKEN_PLATFORM", "");
    }

    public void setFaceFileUrl(String str) {
        setStringValue("face_file_url", str);
    }

    public String getFaceFileUrl() {
        return getStringValue("face_file_url");
    }

    public void setFaceFileSize(long j) {
        setLongValue("face_file_size", j);
    }

    public long getFaceFileSize() {
        return getLongValue("face_file_size");
    }

    public void setFaceFileMd5(String str) {
        setStringValue("face_file_md5", str);
    }

    public String getFaceFileMd5() {
        return getStringValue("face_file_md5");
    }

    public int getReportLocationSwitch() {
        return getIntValue(CACHE_REPORT_LOCATION_SWITCH);
    }

    public void setReportLocationSwitch(int i) {
        setIntValue(CACHE_REPORT_LOCATION_SWITCH, i);
    }

    public RabbitMQParam getReportLocationParam() {
        String stringValue = getStringValue(CACHE_REPORT_LOCATION_PARAM);
        if (!stringValue.equals("")) {
            return (RabbitMQParam) GsonUtil.m14241a().mo21588a(stringValue, RabbitMQParam.class);
        }
        return null;
    }

    public void setReportLocationParam(RabbitMQParam cVar) {
        if (cVar == null) {
            setStringValue(CACHE_REPORT_LOCATION_PARAM, "");
            return;
        }
        String b = GsonUtil.m14241a().mo21597b(cVar);
        if (!C3467s.m14513e(b)) {
            setStringValue(CACHE_REPORT_LOCATION_PARAM, b);
        }
    }

    public void setCircleAvatarUrl(String str) {
        setStringValue(CACHE_CIRCLE_AVATAR_URL, str);
    }

    public String getCircleAvatarUrl() {
        return getStringValue(CACHE_CIRCLE_AVATAR_URL, "");
    }

    public void setCircleSex(int i) {
        setIntValue(CACHE_CIRCLE_SEX, i);
    }

    public int getCircleSex() {
        return getIntValue(CACHE_CIRCLE_SEX, 0);
    }

    public void setCircleBirth(String str) {
        setStringValue(CACHE_CIRCLE_BIRTH, str);
    }

    public String getCircleBirth() {
        return getStringValue(CACHE_CIRCLE_BIRTH, "");
    }

    public void setCircleCity(String str) {
        setStringValue(CACHE_CIRCLE_CITY, str);
    }

    public String getCircleCity() {
        return getStringValue(CACHE_CIRCLE_CITY, "");
    }
}
