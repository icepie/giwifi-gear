package com.gbcom.gwifi.util.p256a;

import android.content.Intent;
import com.gbcom.gwifi.base.activity.GBActivity;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.util.C3467s;
import com.gbcom.gwifi.util.Constant;
import com.gbcom.gwifi.util.Log;
import com.gbcom.gwifi.util.cache.CacheAccount;
import org.json.JSONException;
import org.json.JSONObject;
import p041c.HttpUrl;
import p041c.Request;

/* renamed from: com.gbcom.gwifi.util.a.b */
public class TokenUtil {

    /* renamed from: a */
    public static final String f13105a = "gi752e58b11af83d96";

    /* renamed from: b */
    public static final String f13106b = "YXJjc29mdGZhY2VyZWNvZ25pemVkZXRlY3Q";

    /* renamed from: c */
    private static final String f13107c = "TokenUtil";

    /* renamed from: a */
    public static boolean m14093a() {
        String loginToken = CacheAccount.getInstance().getLoginToken();
        Log.m14398b(f13107c, "checkLoginTokenValid loginToken=" + loginToken);
        if (!C3467s.m14513e(loginToken)) {
            long loginTokenExpires = CacheAccount.getInstance().getLoginTokenExpires();
            Log.m14398b(f13107c, "checkLoginTokenValid loginTokenExpires=" + loginTokenExpires);
            if (loginTokenExpires > (System.currentTimeMillis() / 1000) + 300) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    public static void m14091a(Request abVar, String str) {
        HttpUrl a;
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.getInt("errcode") != 0) {
                GBActivity.showMessageToast(jSONObject.getString("errmsg"));
                return;
            }
            Object obj = jSONObject.get("data");
            if (obj == null || !(obj instanceof JSONObject)) {
                Log.m14403e(f13107c, "invalid token info");
                GBActivity.showMessageToast("invalid token info");
                return;
            }
            JSONObject jSONObject2 = (JSONObject) obj;
            String string = jSONObject2.getString("access_token");
            long currentTimeMillis = (System.currentTimeMillis() / 1000) + ((long) jSONObject2.getInt("expires_in"));
            CacheAccount.getInstance().setLoginToken(string);
            CacheAccount.getInstance().setLoginTokenExpires(currentTimeMillis);
            String str2 = "";
            if (!(abVar == null || (a = abVar.mo16085a()) == null)) {
                str2 = a.mo16382i();
            }
            Log.m14398b(f13107c, "access_token=" + string);
            Log.m14398b(f13107c, "token_expires=" + currentTimeMillis);
            Log.m14398b(f13107c, "token_platform=" + str2);
            CacheAccount.getInstance().setLoginTokenPlatform(str2);
            GBApplication.instance().sendBroadcast(new Intent(Constant.f13234bI));
            TokenTimer.m14088b();
        } catch (JSONException e) {
            e.printStackTrace();
            Log.m14403e(f13107c, e.getMessage());
        }
    }

    /* renamed from: a */
    public static void m14092a(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.getInt("errcode") != 0) {
                Log.m14403e(f13107c, "getRegisteredFaceId " + jSONObject.getString("errmsg"));
                return;
            }
            Object obj = jSONObject.get("data");
            if (obj == null || !(obj instanceof JSONObject)) {
                Log.m14403e(f13107c, "getRegisteredFaceId invalid face info");
                return;
            }
            JSONObject jSONObject2 = (JSONObject) obj;
            String string = jSONObject2.getString("face_photo_url");
            long j = jSONObject2.getLong("face_photo_size");
            String string2 = jSONObject2.getString("face_photo_md5");
            if (C3467s.m14513e(string)) {
                Log.m14403e(f13107c, "getRegisteredFaceId face_url is empty");
                return;
            }
            Log.m14398b(f13107c, "getRegisteredFaceId setFaceFileUrl " + string);
            CacheAccount.getInstance().setFaceFileUrl(string);
            CacheAccount.getInstance().setFaceFileSize(j);
            CacheAccount.getInstance().setFaceFileMd5(string2);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.m14403e(f13107c, e.getMessage());
        }
    }
}
