package com.gbcom.gwifi.functions.webview;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.p009v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;
import com.gbcom.gwifi.base.app.GBApplication;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.TbsConfig;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.p373b.p374a.C5939i;
import com.tencent.smtt.sdk.p373b.p374a.DialogC5936f;
import java.p456io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.android.agoo.common.AgooConstants;
import org.android.agoo.message.MessageService;
import org.apache.xalan.templates.Constants;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.gbcom.gwifi.functions.webview.d */
public class TbsReaderAssist {
    /* renamed from: a */
    public static int m13825a(Context context, String str, HashMap<String, String> hashMap, ValueCallback<String> valueCallback) {
        if (str != null) {
            String substring = str.substring(str.lastIndexOf(Constants.ATTRVAL_THIS) + 1, str.length());
            if (!m13832a(context, str, substring)) {
                m13828a(context, str, valueCallback);
                return 3;
            } else if (m13830a(context, str, 4, 0, substring, m13827a(context, hashMap))) {
                if (valueCallback != null) {
                    valueCallback.onReceiveValue("open QB");
                }
                return 1;
            } else {
                Log.d("QbSdk", "openFileReader startQBForDoc return false");
            }
        } else {
            Log.d("QbSdk", "openFileReader QQ browser not installed");
        }
        if (hashMap == null) {
            hashMap = new HashMap<>();
        }
        hashMap.put(AgooConstants.MESSAGE_LOCAL, "true");
        m13828a(context, str, valueCallback);
        return 3;
    }

    /* renamed from: a */
    public static boolean m13830a(Context context, String str, int i, int i2, String str2, Bundle bundle) {
        HashMap hashMap = new HashMap();
        hashMap.put(QbSdk.LOGIN_TYPE_KEY_PARTNER_ID, context.getApplicationContext().getApplicationInfo().processName);
        hashMap.put(QbSdk.LOGIN_TYPE_KEY_PARTNER_CALL_POS, Integer.toString(i));
        return m13831a(context, str, i2, str2, hashMap, bundle);
    }

    /* renamed from: a */
    private static boolean m13832a(Context context, String str, String str2) {
        Intent intent = new Intent("com.tencent.QQBrowser.action.sdk.document");
        intent.setDataAndType(m13826a(context, new File(str)), "mtt/" + str2);
        for (ResolveInfo resolveInfo : context.getPackageManager().queryIntentActivities(intent, 0)) {
            if (resolveInfo.activityInfo.packageName.contains(TbsConfig.APP_QB)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    private static Bundle m13827a(Context context, Map<String, String> map) {
        String str;
        if (map == null) {
            return null;
        }
        try {
            Bundle bundle = new Bundle();
            if (map.get("style") == null) {
                str = "0";
            } else {
                str = map.get("style");
            }
            bundle.putString("style", str);
            try {
                bundle.putInt("topBarBgColor", Color.parseColor(map.get("topBarBgColor")));
            } catch (Exception e) {
            }
            if (map != null && map.containsKey(QbSdk.FILERADER_MENUDATA)) {
                JSONObject jSONObject = new JSONObject(map.get(QbSdk.FILERADER_MENUDATA));
                JSONArray jSONArray = jSONObject.getJSONArray("menuItems");
                if (jSONArray != null) {
                    ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
                    int i = 0;
                    while (i < jSONArray.length() && i < 5) {
                        try {
                            JSONObject jSONObject2 = (JSONObject) jSONArray.get(i);
                            arrayList.add(i, BitmapFactory.decodeResource(context.getResources(), jSONObject2.getInt("iconResId")));
                            jSONObject2.put("iconResId", i);
                        } catch (Exception e2) {
                        }
                        i++;
                    }
                    bundle.putParcelableArrayList("resArray", arrayList);
                }
                bundle.putString(QbSdk.FILERADER_MENUDATA, jSONObject.toString());
            }
            return bundle;
        } catch (Exception e3) {
            e3.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static void m13828a(Context context, String str, ValueCallback<String> valueCallback) {
        if (context != null && !context.getApplicationInfo().packageName.equals("com.tencent.androidqqmail")) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.addCategory("android.intent.category.DEFAULT");
            String c = C5939i.m24173c(str);
            intent.setDataAndType(m13826a(context, new File(str)), c);
            intent.setFlags(1);
            QbSdk.isDefaultDialog = false;
            DialogC5936f fVar = new DialogC5936f(context, "选择其它应用打开", intent, valueCallback, c);
            String a = fVar.mo39967a();
            if (a != null && !TextUtils.isEmpty(a) && m13829a(context, a)) {
                if (TbsConfig.APP_QB.equals(a)) {
                    intent.putExtra(QbSdk.LOGIN_TYPE_KEY_PARTNER_ID, context.getApplicationContext().getPackageName());
                    intent.putExtra(QbSdk.LOGIN_TYPE_KEY_PARTNER_CALL_POS, MessageService.MSG_ACCS_READY_REPORT);
                }
                intent.setPackage(a);
                context.startActivity(intent);
                if (valueCallback != null) {
                    valueCallback.onReceiveValue("default browser:" + a);
                }
            } else if ("com.tencent.rtxlite".equalsIgnoreCase(context.getApplicationContext().getPackageName()) && QbSdk.isDefaultDialog) {
            } else {
                if (!QbSdk.isDefaultDialog) {
                    fVar.show();
                } else if (valueCallback != null) {
                    valueCallback.onReceiveValue("can not open");
                }
            }
        }
    }

    /* renamed from: a */
    public static boolean m13829a(Context context, String str) {
        if (str == null || "".equals(str)) {
            return false;
        }
        try {
            if (Build.VERSION.SDK_INT >= 24) {
                context.getPackageManager().getApplicationInfo(str, 8192);
            } else {
                context.getPackageManager().getApplicationInfo(str, 8192);
            }
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    /* renamed from: a */
    public static boolean m13831a(Context context, String str, int i, String str2, HashMap<String, String> hashMap, Bundle bundle) {
        Set<String> keySet;
        try {
            Intent intent = new Intent("com.tencent.QQBrowser.action.sdk.document");
            if (!(hashMap == null || (keySet = hashMap.keySet()) == null)) {
                for (String str3 : keySet) {
                    String str4 = hashMap.get(str3);
                    if (!TextUtils.isEmpty(str4)) {
                        intent.putExtra(str3, str4);
                    }
                }
            }
            File file = new File(str);
            intent.putExtra("key_reader_sdk_id", 3);
            intent.putExtra("key_reader_sdk_type", i);
            if (i == 0) {
                intent.putExtra("key_reader_sdk_path", str);
            } else if (i == 1) {
                intent.putExtra("key_reader_sdk_url", str);
            }
            intent.putExtra("key_reader_sdk_format", str2);
            intent.setDataAndType(m13826a(context, file), "mtt/" + str2);
            intent.putExtra("loginType", m13824a(context.getApplicationContext()));
            if (bundle != null) {
                intent.putExtra("key_reader_sdk_extrals", bundle);
            }
            context.startActivity(intent);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: a */
    private static int m13824a(Context context) {
        String str = context.getApplicationInfo().processName;
        if (str.equals("com.tencent.mobileqq")) {
            return 13;
        }
        if (str.equals(TbsConfig.APP_QZONE)) {
            return 14;
        }
        if (str.equals("com.tencent.WBlog")) {
            return 15;
        }
        if (str.equals("com.tencent.mm")) {
            return 24;
        }
        return 26;
    }

    /* renamed from: a */
    private static Uri m13826a(Context context, @NonNull File file) {
        if (Build.VERSION.SDK_INT >= 24) {
            return FileProvider.getUriForFile(context, GBApplication.instance().getPackageName() + ".provider", file);
        }
        return Uri.fromFile(file);
    }
}
