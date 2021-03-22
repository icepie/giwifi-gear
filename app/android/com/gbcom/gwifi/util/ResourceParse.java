package com.gbcom.gwifi.util;

import com.taobao.agoo.p359a.p360a.AbstractC5718b;
import com.umeng.commonsdk.proguard.UMCommonContent;
import com.umeng.socialize.net.dplus.CommonNetImpl;
import com.xiaomi.stat.MiStat;
import java.p456io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.xalan.templates.Constants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import sun.security.x509.InvalidityDateExtension;

/* renamed from: com.gbcom.gwifi.util.o */
public class ResourceParse {

    /* renamed from: a */
    private static final String f13476a = "ResourceParse";

    /* renamed from: a */
    public static HashMap<String, Object> m14446a(byte[] bArr) {
        try {
            HashMap<String, Object> hashMap = new HashMap<>();
            JSONObject jSONObject = new JSONObject(new String(bArr, "UTF-8"));
            int i = jSONObject.getInt(AbstractC5718b.JSON_ERRORCODE);
            hashMap.put(AbstractC5718b.JSON_ERRORCODE, Integer.valueOf(i));
            if (i == 0) {
                HashMap hashMap2 = new HashMap();
                JSONObject jSONObject2 = (JSONObject) jSONObject.get("data");
                JSONArray jSONArray = jSONObject2.getJSONArray("banner");
                ArrayList arrayList = new ArrayList();
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    HashMap hashMap3 = new HashMap();
                    JSONObject jSONObject3 = (JSONObject) jSONArray.get(i2);
                    Integer valueOf = Integer.valueOf(jSONObject3.getInt("cat_id"));
                    String string = jSONObject3.getString("cat_title");
                    String string2 = jSONObject3.getString("cat_type");
                    String string3 = jSONObject3.getString("wap_url");
                    String string4 = jSONObject3.getString("img_url");
                    hashMap3.put("catId", valueOf);
                    hashMap3.put("catTitle", string);
                    hashMap3.put("catType", string2);
                    hashMap3.put("wapUrl", string3);
                    hashMap3.put("imgUrl", string4);
                    arrayList.add(hashMap3);
                }
                hashMap2.put("banners", arrayList);
                ArrayList arrayList2 = new ArrayList();
                JSONArray jSONArray2 = jSONObject2.getJSONArray("products");
                for (int i3 = 0; i3 < jSONArray2.length(); i3++) {
                    HashMap hashMap4 = new HashMap();
                    JSONObject jSONObject4 = (JSONObject) jSONArray2.get(i3);
                    int i4 = jSONObject4.getInt("product_id");
                    String string5 = jSONObject4.getString("product_name");
                    Double valueOf2 = Double.valueOf(jSONObject4.getDouble("file_totalsize"));
                    Integer valueOf3 = Integer.valueOf(jSONObject4.getInt("download_count"));
                    String string6 = jSONObject4.getString("img_url");
                    if (jSONObject4.has("app_points_reward")) {
                        hashMap4.put("appPointsReward", Integer.valueOf(jSONObject4.getInt("app_points_reward")));
                    }
                    if (jSONObject4.has("video_set")) {
                        hashMap4.put("videoSet", Integer.valueOf(jSONObject4.getInt("video_set")));
                    }
                    if (jSONObject4.has("app_package")) {
                        String string7 = jSONObject4.getString("app_package");
                        hashMap4.put("appPackage", string7);
                        Log.m14400c(f13476a, "parseMainPageContent appPackage->" + string7);
                    }
                    hashMap4.put("productId", Integer.valueOf(i4));
                    hashMap4.put("productName", string5);
                    hashMap4.put("fileTotalSize", valueOf2);
                    hashMap4.put("downloadCount", valueOf3);
                    hashMap4.put("imgUrl", string6);
                    hashMap4.put("isSpeed", 0);
                    if (jSONObject4.has("is_speed") && jSONObject4.get("is_speed") != null && jSONObject4.getInt("is_speed") == 1) {
                        hashMap4.put("isSpeed", 1);
                    }
                    ArrayList arrayList3 = new ArrayList();
                    JSONArray jSONArray3 = jSONObject4.getJSONArray(CommonNetImpl.TAG);
                    for (int i5 = 0; i5 < jSONArray3.length(); i5++) {
                        JSONObject jSONObject5 = (JSONObject) jSONArray3.get(i5);
                        arrayList3.add(new String[]{jSONObject5.getString("tag_id"), jSONObject5.getString("tag_name")});
                    }
                    hashMap4.put("tags", arrayList3);
                    JSONArray jSONArray4 = jSONObject4.getJSONArray("download_url");
                    ArrayList arrayList4 = new ArrayList();
                    for (int i6 = 0; i6 < jSONArray4.length(); i6++) {
                        HashMap hashMap5 = new HashMap();
                        JSONObject jSONObject6 = (JSONObject) jSONArray4.get(i6);
                        int i7 = jSONObject6.getInt("file_id");
                        String string8 = jSONObject6.getString("showname");
                        Integer valueOf4 = Integer.valueOf(jSONObject6.getInt("no"));
                        String string9 = jSONObject6.getString("md5");
                        String string10 = jSONObject6.getString("filename");
                        double d = jSONObject6.getDouble("filesize");
                        String string11 = jSONObject6.getString("file_url");
                        if (jSONObject6.has("wap_url")) {
                            hashMap5.put("wapUrl", jSONObject6.getString("wap_url"));
                        }
                        Integer valueOf5 = jSONObject6.has("is_wap") ? Integer.valueOf(jSONObject6.getInt("is_wap")) : 0;
                        hashMap5.put("fileId", Integer.valueOf(i7));
                        hashMap5.put("showName", string8);
                        hashMap5.put("no", valueOf4);
                        hashMap5.put("md5", string9);
                        hashMap5.put("fileName", string10);
                        hashMap5.put("fileSize", Double.valueOf(d));
                        hashMap5.put("fileUrl", string11);
                        hashMap5.put("isWap", valueOf5);
                        arrayList4.add(hashMap5);
                    }
                    hashMap4.put("downloads", arrayList4);
                    arrayList2.add(hashMap4);
                }
                hashMap2.put("products", arrayList2);
                hashMap.put("data", hashMap2);
            }
            return hashMap;
        } catch (JSONException e) {
            Log.m14403e(f13476a, "ResourceParse->parseMainPageContent():" + e.toString());
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e2) {
            Log.m14403e(f13476a, "ResourceParse->parseMainPageContent():" + e2.toString());
            e2.printStackTrace();
            return null;
        }
    }

    /* renamed from: b */
    public static HashMap<String, Object> m14448b(byte[] bArr) {
        try {
            HashMap<String, Object> hashMap = new HashMap<>();
            String str = new String(bArr, "UTF-8");
            Log.m14400c(CommonNetImpl.TAG, "s..>" + str);
            JSONObject jSONObject = new JSONObject(str);
            int i = jSONObject.getInt(AbstractC5718b.JSON_ERRORCODE);
            hashMap.put(AbstractC5718b.JSON_ERRORCODE, Integer.valueOf(i));
            if (i == 0) {
                HashMap hashMap2 = new HashMap();
                JSONArray jSONArray = jSONObject.getJSONArray("data");
                ArrayList arrayList = new ArrayList();
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    HashMap hashMap3 = new HashMap();
                    JSONObject jSONObject2 = (JSONObject) jSONArray.get(i2);
                    int i3 = jSONObject2.getInt("product_id");
                    String string = jSONObject2.getString("product_name");
                    Double valueOf = Double.valueOf(jSONObject2.getDouble("file_totalsize"));
                    Integer valueOf2 = Integer.valueOf(jSONObject2.getInt("download_count"));
                    String string2 = jSONObject2.getString("img_url");
                    if (jSONObject2.has("video_set")) {
                        hashMap3.put("videoSet", Integer.valueOf(jSONObject2.getInt("video_set")));
                    }
                    hashMap3.put("productId", Integer.valueOf(i3));
                    hashMap3.put("productName", string);
                    hashMap3.put("fileTotalSize", valueOf);
                    hashMap3.put("downloadCount", valueOf2);
                    hashMap3.put("imgUrl", string2);
                    ArrayList arrayList2 = new ArrayList();
                    JSONArray jSONArray2 = jSONObject2.getJSONArray(CommonNetImpl.TAG);
                    for (int i4 = 0; i4 < jSONArray2.length(); i4++) {
                        JSONObject jSONObject3 = (JSONObject) jSONArray2.get(i4);
                        arrayList2.add(new String[]{jSONObject3.getString("tag_id"), jSONObject3.getString("tag_name")});
                    }
                    hashMap3.put("tags", arrayList2);
                    JSONArray jSONArray3 = jSONObject2.getJSONArray("download_url");
                    Log.m14400c(f13476a, "downloadUrls->" + jSONArray3.toString());
                    ArrayList arrayList3 = new ArrayList();
                    for (int i5 = 0; i5 < jSONArray3.length(); i5++) {
                        HashMap hashMap4 = new HashMap();
                        JSONObject jSONObject4 = (JSONObject) jSONArray3.get(i5);
                        int i6 = jSONObject4.getInt("file_id");
                        String string3 = jSONObject4.getString("showname");
                        Integer valueOf3 = Integer.valueOf(jSONObject4.getInt("no"));
                        String string4 = jSONObject4.getString("md5");
                        String string5 = jSONObject4.getString("filename");
                        double d = jSONObject4.getDouble("filesize");
                        String string6 = jSONObject4.getString("file_url");
                        int i7 = 0;
                        if (jSONObject4.has("is_wap")) {
                            i7 = jSONObject4.getInt("is_wap");
                        }
                        if (jSONObject4.has("wap_url")) {
                            hashMap4.put("wapUrl", jSONObject4.getString("wap_url"));
                        }
                        hashMap4.put("fileId", Integer.valueOf(i6));
                        hashMap4.put("showName", string3);
                        hashMap4.put("no", valueOf3);
                        hashMap4.put("md5", string4);
                        hashMap4.put("fileName", string5);
                        hashMap4.put("fileSize", Double.valueOf(d));
                        hashMap4.put("fileUrl", string6);
                        hashMap4.put("isWap", Integer.valueOf(i7));
                        arrayList3.add(hashMap4);
                    }
                    hashMap3.put("downloads", arrayList3);
                    if (jSONObject2.has("app_package")) {
                        hashMap3.put("appPackage", jSONObject2.getString("app_package"));
                    }
                    hashMap3.put("isSpeed", 0);
                    if (jSONObject2.has("is_speed") && jSONObject2.get("is_speed") != null && jSONObject2.getInt("is_speed") == 1) {
                        hashMap3.put("isSpeed", 1);
                    }
                    if (jSONObject2.has("app_points_reward")) {
                        hashMap3.put("appPointsReward", Integer.valueOf(jSONObject2.getInt("app_points_reward")));
                    }
                    arrayList.add(hashMap3);
                }
                hashMap2.put("products", arrayList);
                hashMap.put("data", hashMap2);
            }
            return hashMap;
        } catch (JSONException e) {
            Log.m14403e(f13476a, "ResourceParse->parseProductListByPage():" + e.toString());
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e2) {
            Log.m14403e(f13476a, "ResourceParse->parseProductListByPage():" + e2.toString());
            e2.printStackTrace();
            return null;
        }
    }

    /* renamed from: c */
    public static HashMap<String, Object> m14450c(byte[] bArr) {
        try {
            HashMap<String, Object> hashMap = new HashMap<>();
            String str = new String(bArr, "UTF-8");
            Log.m14400c(CommonNetImpl.TAG, "s..>" + str);
            JSONObject jSONObject = new JSONObject(str);
            int i = jSONObject.getInt(AbstractC5718b.JSON_ERRORCODE);
            hashMap.put(AbstractC5718b.JSON_ERRORCODE, Integer.valueOf(i));
            if (i == 0) {
                HashMap hashMap2 = new HashMap();
                JSONArray jSONArray = jSONObject.getJSONArray("data");
                ArrayList arrayList = new ArrayList();
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    HashMap hashMap3 = new HashMap();
                    JSONObject jSONObject2 = (JSONObject) jSONArray.get(i2);
                    int i3 = jSONObject2.getInt("product_id");
                    String string = jSONObject2.getString("product_name");
                    Double valueOf = Double.valueOf(jSONObject2.getDouble("file_totalsize"));
                    Integer valueOf2 = Integer.valueOf(jSONObject2.getInt("download_count"));
                    String string2 = jSONObject2.getString("img_url");
                    if (jSONObject2.has("video_set")) {
                        hashMap3.put("videoSet", Integer.valueOf(jSONObject2.getInt("video_set")));
                    }
                    hashMap3.put("productId", Integer.valueOf(i3));
                    hashMap3.put("productName", string);
                    hashMap3.put("fileTotalSize", valueOf);
                    hashMap3.put("downloadCount", valueOf2);
                    hashMap3.put("imgUrl", string2);
                    ArrayList arrayList2 = new ArrayList();
                    JSONArray jSONArray2 = jSONObject2.getJSONArray(CommonNetImpl.TAG);
                    for (int i4 = 0; i4 < jSONArray2.length(); i4++) {
                        JSONObject jSONObject3 = (JSONObject) jSONArray2.get(i4);
                        arrayList2.add(new String[]{jSONObject3.getString("tag_id"), jSONObject3.getString("tag_name")});
                    }
                    hashMap3.put("tags", arrayList2);
                    JSONArray jSONArray3 = jSONObject2.getJSONArray("download_url");
                    Log.m14400c(f13476a, "downloadUrls->" + jSONArray3.toString());
                    ArrayList arrayList3 = new ArrayList();
                    for (int i5 = 0; i5 < jSONArray3.length(); i5++) {
                        HashMap hashMap4 = new HashMap();
                        JSONObject jSONObject4 = (JSONObject) jSONArray3.get(i5);
                        int i6 = jSONObject4.getInt("file_id");
                        String string3 = jSONObject4.getString("showname");
                        Integer valueOf3 = Integer.valueOf(jSONObject4.getInt("no"));
                        String string4 = jSONObject4.getString("md5");
                        String string5 = jSONObject4.getString("filename");
                        double d = jSONObject4.getDouble("filesize");
                        String string6 = jSONObject4.getString("file_url");
                        hashMap4.put("fileId", Integer.valueOf(i6));
                        hashMap4.put("showName", string3);
                        hashMap4.put("no", valueOf3);
                        hashMap4.put("md5", string4);
                        hashMap4.put("fileName", string5);
                        hashMap4.put("fileSize", Double.valueOf(d));
                        hashMap4.put("fileUrl", string6);
                        arrayList3.add(hashMap4);
                    }
                    hashMap3.put("downloads", arrayList3);
                    if (jSONObject2.has("app_package")) {
                        hashMap3.put("appPackage", jSONObject2.getString("app_package"));
                    }
                    hashMap3.put("isSpeed", 0);
                    if (jSONObject2.has("is_speed") && jSONObject2.get("is_speed") != null && jSONObject2.getInt("is_speed") == 1) {
                        hashMap3.put("isSpeed", 1);
                    }
                    if (jSONObject2.has("app_points_reward")) {
                        hashMap3.put("appPointsReward", Integer.valueOf(jSONObject2.getInt("app_points_reward")));
                    }
                    arrayList.add(hashMap3);
                }
                hashMap2.put("products", arrayList);
                hashMap.put("data", hashMap2);
            }
            return hashMap;
        } catch (JSONException e) {
            Log.m14403e(f13476a, "ResourceParse->parseProductListByPage():" + e.toString());
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e2) {
            Log.m14403e(f13476a, "ResourceParse->parseProductListByPage():" + e2.toString());
            e2.printStackTrace();
            return null;
        }
    }

    /* renamed from: d */
    public static HashMap<String, Object> m14452d(byte[] bArr) {
        try {
            HashMap<String, Object> hashMap = new HashMap<>();
            JSONObject jSONObject = new JSONObject(new String(bArr, "UTF-8"));
            int i = jSONObject.getInt(AbstractC5718b.JSON_ERRORCODE);
            hashMap.put(AbstractC5718b.JSON_ERRORCODE, Integer.valueOf(i));
            if (i == 0) {
                HashMap hashMap2 = new HashMap();
                ArrayList arrayList = new ArrayList();
                JSONArray jSONArray = ((JSONObject) jSONObject.get("data")).getJSONArray("products");
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    HashMap hashMap3 = new HashMap();
                    JSONObject jSONObject2 = (JSONObject) jSONArray.get(i2);
                    int i3 = jSONObject2.getInt("product_id");
                    String string = jSONObject2.getString("product_name");
                    Double valueOf = Double.valueOf(jSONObject2.getDouble("file_totalsize"));
                    Integer valueOf2 = Integer.valueOf(jSONObject2.getInt("download_count"));
                    String string2 = jSONObject2.getString("img_url");
                    if (jSONObject2.has("video_set")) {
                        hashMap3.put("videoSet", Integer.valueOf(jSONObject2.getInt("video_set")));
                    }
                    if (jSONObject2.has("app_package")) {
                        hashMap3.put("appPackage", jSONObject2.getString("app_package"));
                    }
                    if (jSONObject2.has("app_points_reward")) {
                        hashMap3.put("appPointsReward", Integer.valueOf(jSONObject2.getInt("app_points_reward")));
                    }
                    hashMap3.put("productId", Integer.valueOf(i3));
                    hashMap3.put("productName", string);
                    hashMap3.put("fileTotalSize", valueOf);
                    hashMap3.put("downloadCount", valueOf2);
                    hashMap3.put("imgUrl", string2);
                    hashMap3.put("isSpeed", 0);
                    if (jSONObject2.has("is_speed") && jSONObject2.get("is_speed") != null && jSONObject2.getInt("is_speed") == 1) {
                        hashMap3.put("isSpeed", 1);
                    }
                    ArrayList arrayList2 = new ArrayList();
                    JSONArray jSONArray2 = jSONObject2.getJSONArray(CommonNetImpl.TAG);
                    for (int i4 = 0; i4 < jSONArray2.length(); i4++) {
                        JSONObject jSONObject3 = (JSONObject) jSONArray2.get(i4);
                        arrayList2.add(new String[]{jSONObject3.getString("tag_id"), jSONObject3.getString("tag_name")});
                    }
                    hashMap3.put("tags", arrayList2);
                    JSONArray jSONArray3 = jSONObject2.getJSONArray("download_url");
                    ArrayList arrayList3 = new ArrayList();
                    for (int i5 = 0; i5 < jSONArray3.length(); i5++) {
                        HashMap hashMap4 = new HashMap();
                        JSONObject jSONObject4 = (JSONObject) jSONArray3.get(i5);
                        int i6 = jSONObject4.getInt("file_id");
                        String string3 = jSONObject4.getString("showname");
                        Integer valueOf3 = Integer.valueOf(jSONObject4.getInt("no"));
                        String string4 = jSONObject4.getString("md5");
                        String string5 = jSONObject4.getString("filename");
                        double d = jSONObject4.getDouble("filesize");
                        String string6 = jSONObject4.getString("file_url");
                        hashMap4.put("fileId", Integer.valueOf(i6));
                        hashMap4.put("showName", string3);
                        hashMap4.put("no", valueOf3);
                        hashMap4.put("md5", string4);
                        hashMap4.put("fileName", string5);
                        hashMap4.put("fileSize", Double.valueOf(d));
                        hashMap4.put("fileUrl", string6);
                        arrayList3.add(hashMap4);
                    }
                    hashMap3.put("downloads", arrayList3);
                    arrayList.add(hashMap3);
                }
                hashMap2.put("products", arrayList);
                hashMap.put("data", hashMap2);
            }
            return hashMap;
        } catch (JSONException e) {
            Log.m14403e(f13476a, "ResourceParse->parseCatInfo():" + e.toString());
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e2) {
            Log.m14403e(f13476a, "ResourceParse->parseCatInfo():" + e2.toString());
            e2.printStackTrace();
            return null;
        }
    }

    /* renamed from: e */
    public static HashMap<String, Object> m14454e(byte[] bArr) {
        int i;
        UnsupportedEncodingException e;
        JSONException e2;
        String str;
        String str2;
        int i2 = -1;
        try {
            HashMap<String, Object> hashMap = new HashMap<>();
            String str3 = new String(bArr, "UTF-8");
            Log.m14400c(CommonNetImpl.TAG, "s..+" + str3);
            JSONObject jSONObject = new JSONObject(str3);
            int i3 = jSONObject.getInt(AbstractC5718b.JSON_ERRORCODE);
            hashMap.put(AbstractC5718b.JSON_ERRORCODE, Integer.valueOf(i3));
            if (i3 == 0) {
                HashMap hashMap2 = new HashMap();
                JSONObject jSONObject2 = (JSONObject) jSONObject.get("data");
                HashMap hashMap3 = new HashMap();
                i = jSONObject2.getInt("product_id");
                try {
                    String string = jSONObject2.getString("product_name");
                    Double valueOf = Double.valueOf(jSONObject2.getDouble("file_totalsize"));
                    Integer valueOf2 = Integer.valueOf(jSONObject2.getInt("download_count"));
                    String string2 = jSONObject2.getString("img_url");
                    String string3 = jSONObject2.getString("remark");
                    if (jSONObject2.has(MiStat.Param.PRICE)) {
                        hashMap3.put(MiStat.Param.PRICE, Integer.valueOf(jSONObject2.getInt(MiStat.Param.PRICE)));
                    }
                    if (jSONObject2.has("product_pubdate")) {
                        hashMap3.put(InvalidityDateExtension.DATE, jSONObject2.getString("product_pubdate"));
                    }
                    if (jSONObject2.has(MiStat.Param.SCORE)) {
                        hashMap3.put(MiStat.Param.SCORE, jSONObject2.getString(MiStat.Param.SCORE));
                    }
                    hashMap3.put("productId", Integer.valueOf(i));
                    hashMap3.put("productName", string);
                    hashMap3.put("fileTotalSize", valueOf);
                    hashMap3.put("downloadCount", valueOf2);
                    hashMap3.put("imgUrl", string2);
                    hashMap3.put("remark", string3);
                    ArrayList arrayList = new ArrayList();
                    JSONArray jSONArray = jSONObject2.getJSONArray(CommonNetImpl.TAG);
                    for (int i4 = 0; i4 < jSONArray.length(); i4++) {
                        JSONObject jSONObject3 = (JSONObject) jSONArray.get(i4);
                        arrayList.add(new String[]{jSONObject3.getString("tag_id"), jSONObject3.getString("tag_name")});
                    }
                    hashMap3.put("tags", arrayList);
                    if (jSONObject2.has("app_package")) {
                        hashMap3.put("appPackage", jSONObject2.getString("app_package"));
                    }
                    hashMap2.put("product", hashMap3);
                    JSONArray jSONArray2 = jSONObject2.getJSONArray("download_url");
                    ArrayList arrayList2 = new ArrayList();
                    for (int i5 = 0; i5 < jSONArray2.length(); i5++) {
                        HashMap hashMap4 = new HashMap();
                        JSONObject jSONObject4 = (JSONObject) jSONArray2.get(i5);
                        int i6 = jSONObject4.getInt("file_id");
                        String string4 = jSONObject4.getString("showname");
                        Integer valueOf3 = Integer.valueOf(jSONObject4.getInt("no"));
                        String string5 = jSONObject4.getString("md5");
                        String string6 = jSONObject4.getString("filename");
                        double d = jSONObject4.getDouble("filesize");
                        String string7 = jSONObject4.getString("file_url");
                        int i7 = jSONObject4.has("is_wap") ? jSONObject4.getInt("is_wap") : 0;
                        if (jSONObject4.has("file_source_list")) {
                            JSONArray jSONArray3 = jSONObject4.getJSONArray("file_source_list");
                            ArrayList arrayList3 = new ArrayList();
                            for (int i8 = 0; i8 < jSONArray3.length(); i8++) {
                                String[] strArr = new String[3];
                                JSONObject jSONObject5 = jSONArray3.getJSONObject(i8);
                                Integer valueOf4 = jSONObject5.has("source_type") ? Integer.valueOf(jSONObject5.getInt("source_type")) : 1;
                                Integer valueOf5 = jSONObject5.has("source_quality") ? Integer.valueOf(jSONObject5.getInt("source_quality")) : 2;
                                String str4 = "";
                                if (jSONObject5.has("source_url")) {
                                    str4 = jSONObject5.getString("source_url");
                                }
                                strArr[0] = valueOf4.toString();
                                strArr[1] = valueOf5.toString();
                                strArr[2] = str4;
                                arrayList3.add(strArr);
                            }
                            hashMap4.put("fileSourceList", arrayList3);
                        }
                        hashMap4.put("fileId", Integer.valueOf(i6));
                        hashMap4.put("showName", string4);
                        hashMap4.put("no", valueOf3);
                        hashMap4.put("md5", string5);
                        hashMap4.put("fileName", string6);
                        hashMap4.put("fileSize", Double.valueOf(d));
                        hashMap4.put("fileUrl", string7);
                        hashMap4.put("isWap", Integer.valueOf(i7));
                        arrayList2.add(hashMap4);
                    }
                    hashMap2.put("downloads", arrayList2);
                    JSONArray jSONArray4 = jSONObject2.getJSONArray("img_preview");
                    ArrayList arrayList4 = new ArrayList();
                    for (int i9 = 0; i9 < jSONArray4.length(); i9++) {
                        arrayList4.add(jSONArray4.getJSONObject(i9).getString("img_url"));
                    }
                    hashMap2.put("imgPreview", arrayList4);
                    JSONArray jSONArray5 = jSONObject2.getJSONArray("recommend");
                    ArrayList arrayList5 = new ArrayList();
                    for (int i10 = 0; i10 < jSONArray5.length(); i10++) {
                        JSONObject jSONObject6 = jSONArray5.getJSONObject(i10);
                        HashMap hashMap5 = new HashMap();
                        int i11 = jSONObject6.getInt("product_id");
                        String string8 = jSONObject6.getString("product_name");
                        String str5 = "";
                        if (jSONObject6.has("img_url")) {
                            str5 = jSONObject6.getString("img_url");
                        }
                        hashMap5.put("productId", Integer.valueOf(i11));
                        hashMap5.put("productName", string8);
                        hashMap5.put("imgUrl", str5);
                        if (jSONObject6.has("download_count")) {
                            hashMap5.put("downloadCount", Integer.valueOf(jSONObject6.getInt("download_count")));
                        }
                        if (jSONObject6.has("tag_name")) {
                            hashMap5.put("tagName", jSONObject6.getString("tag_name"));
                        }
                        arrayList5.add(hashMap5);
                    }
                    hashMap2.put("recommend", arrayList5);
                    String string9 = jSONObject2.getString("product_author");
                    if (jSONObject2.has("video_director")) {
                        str = jSONObject2.getString("video_director");
                    } else {
                        str = "";
                    }
                    int i12 = jSONObject2.has("episode_total") ? jSONObject2.getInt("episode_total") : 0;
                    int i13 = jSONObject2.has("episode_available") ? jSONObject2.getInt("episode_available") : 0;
                    if (jSONObject2.has("duration")) {
                        str2 = jSONObject2.getString("duration");
                    } else {
                        str2 = "";
                    }
                    int i14 = 0;
                    if (jSONObject2.has("product_status")) {
                        i14 = jSONObject2.getInt("product_status");
                    }
                    HashMap hashMap6 = new HashMap();
                    if (jSONObject2.has("video_set")) {
                        hashMap6.put("videoSet", Integer.valueOf(jSONObject2.getInt("video_set")));
                    }
                    hashMap6.put("productAuthor", string9);
                    hashMap6.put("videoDirector", str);
                    hashMap6.put("episodeTotal", Integer.valueOf(i12));
                    hashMap6.put("episodeAvailable", Integer.valueOf(i13));
                    hashMap6.put("duration", str2);
                    hashMap6.put("productStatus", Integer.valueOf(i14));
                    hashMap2.put("other", hashMap6);
                    if (jSONObject2.has("source")) {
                        JSONArray jSONArray6 = jSONObject2.getJSONArray("source");
                        ArrayList arrayList6 = new ArrayList();
                        for (int i15 = 0; i15 < jSONArray6.length(); i15++) {
                            JSONObject jSONObject7 = jSONArray6.getJSONObject(i15);
                            arrayList6.add(new String[]{Integer.valueOf(jSONObject7.getInt("source_type")).toString(), Integer.valueOf(jSONObject7.getInt("source_quality")).toString(), jSONObject7.getString("source_url")});
                        }
                        hashMap2.put("sources", arrayList6);
                    }
                    hashMap.put("data", hashMap2);
                } catch (JSONException e3) {
                    e2 = e3;
                    i2 = i;
                    e2.printStackTrace();
                    Log.m14403e(f13476a, "ResourceParse->parseProductInfo():productId:" + i2 + "," + e2.toString());
                    return null;
                } catch (UnsupportedEncodingException e4) {
                    e = e4;
                    e.printStackTrace();
                    Log.m14403e(f13476a, "ResourceParse->parseProductInfo():productId:" + i + "," + e.toString());
                    return null;
                }
            }
            return hashMap;
        } catch (JSONException e5) {
            e2 = e5;
            e2.printStackTrace();
            Log.m14403e(f13476a, "ResourceParse->parseProductInfo():productId:" + i2 + "," + e2.toString());
            return null;
        } catch (UnsupportedEncodingException e6) {
            e = e6;
            i = -1;
            e.printStackTrace();
            Log.m14403e(f13476a, "ResourceParse->parseProductInfo():productId:" + i + "," + e.toString());
            return null;
        }
    }

    /* renamed from: f */
    public static HashMap<String, Object> m14455f(byte[] bArr) {
        int i;
        UnsupportedEncodingException e;
        JSONException e2;
        String str;
        String str2;
        int i2 = -1;
        try {
            HashMap<String, Object> hashMap = new HashMap<>();
            String str3 = new String(bArr, "UTF-8");
            Log.m14400c(CommonNetImpl.TAG, "s..+" + str3);
            JSONObject jSONObject = new JSONObject(str3);
            int i3 = jSONObject.getInt(AbstractC5718b.JSON_ERRORCODE);
            hashMap.put(AbstractC5718b.JSON_ERRORCODE, Integer.valueOf(i3));
            if (i3 == 0) {
                HashMap hashMap2 = new HashMap();
                JSONObject jSONObject2 = (JSONObject) jSONObject.get("data");
                HashMap hashMap3 = new HashMap();
                i = jSONObject2.getInt("product_id");
                try {
                    String string = jSONObject2.getString("product_name");
                    Double valueOf = Double.valueOf(jSONObject2.getDouble("file_totalsize"));
                    Integer valueOf2 = Integer.valueOf(jSONObject2.getInt("download_count"));
                    if (jSONObject2.has("app_points_reward")) {
                        hashMap3.put("appPointsReward", Integer.valueOf(jSONObject2.getInt("app_points_reward")));
                    }
                    String string2 = jSONObject2.getString("img_url");
                    String string3 = jSONObject2.getString("remark");
                    if (jSONObject2.has(MiStat.Param.PRICE)) {
                        hashMap3.put(MiStat.Param.PRICE, Integer.valueOf(jSONObject2.getInt(MiStat.Param.PRICE)));
                    }
                    if (jSONObject2.has("product_pubdate")) {
                        hashMap3.put(InvalidityDateExtension.DATE, jSONObject2.getString("product_pubdate"));
                    }
                    if (jSONObject2.has(MiStat.Param.SCORE)) {
                        hashMap3.put(MiStat.Param.SCORE, jSONObject2.getString(MiStat.Param.SCORE));
                    }
                    hashMap3.put("productId", Integer.valueOf(i));
                    hashMap3.put("productName", string);
                    hashMap3.put("fileTotalSize", valueOf);
                    hashMap3.put("downloadCount", valueOf2);
                    hashMap3.put("imgUrl", string2);
                    hashMap3.put("remark", string3);
                    ArrayList arrayList = new ArrayList();
                    JSONArray jSONArray = jSONObject2.getJSONArray(CommonNetImpl.TAG);
                    for (int i4 = 0; i4 < jSONArray.length(); i4++) {
                        JSONObject jSONObject3 = (JSONObject) jSONArray.get(i4);
                        arrayList.add(new String[]{jSONObject3.getString("tag_id"), jSONObject3.getString("tag_name")});
                    }
                    hashMap3.put("tags", arrayList);
                    if (jSONObject2.has("app_package")) {
                        hashMap3.put("appPackage", jSONObject2.getString("app_package"));
                    }
                    hashMap2.put("product", hashMap3);
                    JSONArray jSONArray2 = jSONObject2.getJSONArray("download_url");
                    ArrayList arrayList2 = new ArrayList();
                    for (int i5 = 0; i5 < jSONArray2.length(); i5++) {
                        HashMap hashMap4 = new HashMap();
                        JSONObject jSONObject4 = (JSONObject) jSONArray2.get(i5);
                        int i6 = jSONObject4.getInt("file_id");
                        String string4 = jSONObject4.getString("showname");
                        Integer valueOf3 = Integer.valueOf(jSONObject4.getInt("no"));
                        String string5 = jSONObject4.getString("md5");
                        String string6 = jSONObject4.getString("filename");
                        double d = jSONObject4.getDouble("filesize");
                        String string7 = jSONObject4.getString("file_url");
                        int i7 = 0;
                        if (jSONObject4.has("is_wap")) {
                            i7 = Integer.valueOf(jSONObject4.getInt("is_wap"));
                        }
                        if (jSONObject4.has("wap_url")) {
                            hashMap4.put("wapUrl", jSONObject4.getString("wap_url"));
                        }
                        hashMap4.put("fileId", Integer.valueOf(i6));
                        hashMap4.put("showName", string4);
                        hashMap4.put("no", valueOf3);
                        hashMap4.put("md5", string5);
                        hashMap4.put("fileName", string6);
                        hashMap4.put("fileSize", Double.valueOf(d));
                        hashMap4.put("fileUrl", string7);
                        hashMap4.put("isWap", i7);
                        arrayList2.add(hashMap4);
                    }
                    hashMap2.put("downloads", arrayList2);
                    JSONArray jSONArray3 = jSONObject2.getJSONArray("img_preview");
                    ArrayList arrayList3 = new ArrayList();
                    for (int i8 = 0; i8 < jSONArray3.length(); i8++) {
                        arrayList3.add(jSONArray3.getJSONObject(i8).getString("img_url"));
                    }
                    hashMap2.put("preImages", arrayList3);
                    JSONArray jSONArray4 = jSONObject2.getJSONArray("recommend");
                    ArrayList arrayList4 = new ArrayList();
                    for (int i9 = 0; i9 < jSONArray4.length(); i9++) {
                        JSONObject jSONObject5 = jSONArray4.getJSONObject(i9);
                        HashMap hashMap5 = new HashMap();
                        int i10 = jSONObject5.getInt("product_id");
                        String string8 = jSONObject5.getString("product_name");
                        String str4 = "";
                        if (jSONObject5.has("img_url")) {
                            str4 = jSONObject5.getString("img_url");
                        }
                        hashMap5.put("productId", Integer.valueOf(i10));
                        hashMap5.put("productName", string8);
                        hashMap5.put("imgUrl", str4);
                        if (jSONObject5.has("download_count")) {
                            hashMap5.put("downloadCount", Integer.valueOf(jSONObject5.getInt("download_count")));
                        }
                        if (jSONObject5.has("tag_name")) {
                            hashMap5.put("tagName", jSONObject5.getString("tag_name"));
                        }
                        arrayList4.add(hashMap5);
                    }
                    hashMap2.put("recommends", arrayList4);
                    String string9 = jSONObject2.getString("product_author");
                    if (jSONObject2.has("video_director")) {
                        str = jSONObject2.getString("video_director");
                    } else {
                        str = "";
                    }
                    int i11 = jSONObject2.has("episode_total") ? jSONObject2.getInt("episode_total") : 0;
                    int i12 = jSONObject2.has("episode_available") ? jSONObject2.getInt("episode_available") : 0;
                    if (jSONObject2.has("duration")) {
                        str2 = jSONObject2.getString("duration");
                    } else {
                        str2 = "";
                    }
                    int i13 = 0;
                    if (jSONObject2.has("product_status")) {
                        i13 = jSONObject2.getInt("product_status");
                    }
                    HashMap hashMap6 = new HashMap();
                    if (jSONObject2.has("video_set")) {
                        hashMap6.put("videoSet", Integer.valueOf(jSONObject2.getInt("video_set")));
                    }
                    hashMap6.put("productAuthor", string9);
                    hashMap6.put("videoDirector", str);
                    hashMap6.put("episodeTotal", Integer.valueOf(i11));
                    hashMap6.put("episodeAvailable", Integer.valueOf(i12));
                    hashMap6.put("duration", str2);
                    hashMap6.put("productStatus", Integer.valueOf(i13));
                    hashMap2.put("other", hashMap6);
                    hashMap.put("data", hashMap2);
                } catch (JSONException e3) {
                    e2 = e3;
                    i2 = i;
                    e2.printStackTrace();
                    Log.m14403e(f13476a, "ResourceParse->parseProductInfo():productId:" + i2 + "," + e2.toString());
                    return null;
                } catch (UnsupportedEncodingException e4) {
                    e = e4;
                    e.printStackTrace();
                    Log.m14403e(f13476a, "ResourceParse->parseProductInfo():productId:" + i + "," + e.toString());
                    return null;
                }
            }
            return hashMap;
        } catch (JSONException e5) {
            e2 = e5;
            e2.printStackTrace();
            Log.m14403e(f13476a, "ResourceParse->parseProductInfo():productId:" + i2 + "," + e2.toString());
            return null;
        } catch (UnsupportedEncodingException e6) {
            e = e6;
            i = -1;
            e.printStackTrace();
            Log.m14403e(f13476a, "ResourceParse->parseProductInfo():productId:" + i + "," + e.toString());
            return null;
        }
    }

    /* renamed from: g */
    public static HashMap<String, Object> m14456g(byte[] bArr) {
        String string;
        try {
            HashMap<String, Object> hashMap = new HashMap<>();
            String str = new String(bArr, "UTF-8");
            Log.m14400c(f13476a, "parseTvMobileByPage:" + str);
            JSONObject jSONObject = new JSONObject(str);
            int i = jSONObject.getInt(AbstractC5718b.JSON_ERRORCODE);
            hashMap.put(AbstractC5718b.JSON_ERRORCODE, Integer.valueOf(i));
            if (i == 0) {
                HashMap hashMap2 = new HashMap();
                JSONArray jSONArray = jSONObject.getJSONArray("data");
                ArrayList arrayList = new ArrayList();
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    HashMap hashMap3 = new HashMap();
                    JSONObject jSONObject2 = (JSONObject) jSONArray.get(i2);
                    int i3 = jSONObject2.getInt("tv_station_id");
                    String string2 = jSONObject2.getString("tv_station_name");
                    if (jSONObject2.has("tv_station_logo_small")) {
                        string = jSONObject2.getString("tv_station_logo_small");
                    } else {
                        string = jSONObject2.getString("tv_station_logo");
                    }
                    int i4 = jSONObject2.getInt("need_member");
                    JSONArray jSONArray2 = jSONObject2.getJSONArray("tv_live_url");
                    ArrayList arrayList2 = new ArrayList();
                    ArrayList arrayList3 = new ArrayList();
                    for (int i5 = 0; i5 < jSONArray2.length(); i5++) {
                        String string3 = jSONArray2.getJSONObject(i5).getString("source_url");
                        arrayList2.add(string3);
                        Log.m14398b(f13476a, "..>" + string3);
                        arrayList3.add(jSONArray2.getJSONObject(i5).getString("source"));
                    }
                    hashMap3.put("id", Integer.valueOf(i3));
                    hashMap3.put("name", string2);
                    hashMap3.put("logoUrl", string);
                    hashMap3.put("needMember", Integer.valueOf(i4));
                    hashMap3.put("tvLiveUrls", arrayList2);
                    hashMap3.put("tvLiveSourceUrls", arrayList3);
                    arrayList.add(hashMap3);
                }
                hashMap2.put("tvs", arrayList);
                hashMap.put("data", hashMap2);
            }
            return hashMap;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    /* renamed from: h */
    public static HashMap<String, Object> m14457h(byte[] bArr) {
        try {
            HashMap<String, Object> hashMap = new HashMap<>();
            String str = new String(bArr, "UTF-8");
            Log.m14398b(f13476a, "parseFilterTag-->" + str);
            JSONObject jSONObject = new JSONObject(str);
            int i = jSONObject.getInt(AbstractC5718b.JSON_ERRORCODE);
            hashMap.put(AbstractC5718b.JSON_ERRORCODE, Integer.valueOf(i));
            if (i == 0) {
                HashMap hashMap2 = new HashMap();
                JSONArray jSONArray = jSONObject.getJSONArray("data");
                ArrayList arrayList = new ArrayList();
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    HashMap hashMap3 = new HashMap();
                    JSONObject jSONObject2 = (JSONObject) jSONArray.get(i2);
                    String string = jSONObject2.getString("group_id");
                    String string2 = jSONObject2.getString("group_name");
                    String string3 = jSONObject2.getString("product_type");
                    hashMap3.put("group_id", string);
                    hashMap3.put("group_name", string2);
                    hashMap3.put("product_type", string3);
                    JSONArray jSONArray2 = jSONObject2.getJSONArray("tags");
                    ArrayList arrayList2 = new ArrayList();
                    for (int i3 = 0; i3 < jSONArray2.length(); i3++) {
                        JSONObject jSONObject3 = (JSONObject) jSONArray2.get(i3);
                        arrayList2.add(new String[]{jSONObject3.getString("tag_id"), jSONObject3.getString("tag_name")});
                    }
                    hashMap3.put("tags", arrayList2);
                    arrayList.add(hashMap3);
                }
                hashMap2.put("groups", arrayList);
                hashMap.put("data", hashMap2);
                return hashMap;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
        return null;
    }

    /* renamed from: i */
    public static HashMap<String, Object> m14458i(byte[] bArr) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        String str;
        try {
            HashMap<String, Object> hashMap = new HashMap<>();
            JSONObject jSONObject = new JSONObject(new String(bArr, "UTF-8"));
            int i6 = jSONObject.getInt(AbstractC5718b.JSON_ERRORCODE);
            hashMap.put(AbstractC5718b.JSON_ERRORCODE, Integer.valueOf(i6));
            if (i6 == 0) {
                HashMap hashMap2 = new HashMap();
                JSONObject jSONObject2 = (JSONObject) jSONObject.get("data");
                if (jSONObject2.has("video")) {
                    i = jSONObject2.getInt("video");
                } else {
                    i = 0;
                }
                if (jSONObject2.has("music")) {
                    i2 = jSONObject2.getInt("music");
                } else {
                    i2 = 0;
                }
                if (jSONObject2.has("app")) {
                    i3 = jSONObject2.getInt("app");
                } else {
                    i3 = 0;
                }
                if (jSONObject2.has("book")) {
                    i4 = jSONObject2.getInt("book");
                } else {
                    i4 = 0;
                }
                if (jSONObject2.has("game")) {
                    i5 = jSONObject2.getInt("game");
                } else {
                    i5 = 0;
                }
                if (jSONObject2.has("match_type")) {
                    str = jSONObject2.getString("match_type");
                } else {
                    str = "00";
                }
                hashMap2.put("video", Integer.valueOf(i));
                hashMap2.put("music", Integer.valueOf(i2));
                hashMap2.put("app", Integer.valueOf(i3));
                hashMap2.put("book", Integer.valueOf(i4));
                hashMap2.put("game", Integer.valueOf(i5));
                hashMap2.put("match_type", str);
                hashMap.put("data", hashMap2);
                return hashMap;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
        return null;
    }

    /* renamed from: j */
    public static HashMap<String, Object> m14459j(byte[] bArr) {
        try {
            HashMap<String, Object> hashMap = new HashMap<>();
            JSONObject jSONObject = new JSONObject(new String(bArr, "UTF-8"));
            int i = jSONObject.getInt(AbstractC5718b.JSON_ERRORCODE);
            hashMap.put(AbstractC5718b.JSON_ERRORCODE, Integer.valueOf(i));
            if (i == 0) {
                HashMap hashMap2 = new HashMap();
                JSONArray jSONArray = ((JSONObject) jSONObject.get("data")).getJSONArray("hot_search");
                ArrayList arrayList = new ArrayList();
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    arrayList.add(jSONArray.getJSONObject(i2).getString("key_word"));
                }
                hashMap2.put("words", arrayList);
                hashMap.put("data", hashMap2);
                return hashMap;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
        return null;
    }

    /* renamed from: k */
    public static HashMap<String, Object> m14460k(byte[] bArr) {
        try {
            HashMap<String, Object> hashMap = new HashMap<>();
            JSONObject jSONObject = new JSONObject(new String(bArr, "UTF-8"));
            int i = jSONObject.getInt(AbstractC5718b.JSON_ERRORCODE);
            hashMap.put(AbstractC5718b.JSON_ERRORCODE, Integer.valueOf(i));
            if (i == 0) {
                HashMap hashMap2 = new HashMap();
                JSONObject jSONObject2 = (JSONObject) jSONObject.get("data");
                JSONArray jSONArray = jSONObject2.getJSONArray("homepage_banner");
                ArrayList arrayList = new ArrayList();
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    HashMap hashMap3 = new HashMap();
                    JSONObject jSONObject3 = (JSONObject) jSONArray.get(i2);
                    Integer valueOf = Integer.valueOf(jSONObject3.getInt("cat_id"));
                    String string = jSONObject3.getString("cat_title");
                    String string2 = jSONObject3.getString("cat_type");
                    String string3 = jSONObject3.getString("wap_url");
                    String string4 = jSONObject3.getString("img_url");
                    hashMap3.put("catId", valueOf);
                    hashMap3.put("catTitle", string);
                    hashMap3.put("catType", string2);
                    hashMap3.put("wapUrl", string3);
                    hashMap3.put("imgUrl", string4);
                    arrayList.add(hashMap3);
                }
                hashMap2.put("banners", arrayList);
                if (jSONObject2.has("top_product_list")) {
                    JSONArray jSONArray2 = jSONObject2.getJSONArray("top_product_list");
                    ArrayList arrayList2 = new ArrayList();
                    for (int i3 = 0; i3 < jSONArray2.length(); i3++) {
                        HashMap hashMap4 = new HashMap();
                        JSONObject jSONObject4 = (JSONObject) jSONArray2.get(i3);
                        Integer valueOf2 = Integer.valueOf(jSONObject4.getInt("cat_id"));
                        Integer valueOf3 = Integer.valueOf(jSONObject4.getInt("product_id"));
                        String string5 = jSONObject4.getString("product_type");
                        String string6 = jSONObject4.getString("wap_url");
                        String string7 = jSONObject4.getString("img_url");
                        String string8 = jSONObject4.getString("title");
                        hashMap4.put("catId", valueOf2);
                        hashMap4.put("productId", valueOf3);
                        hashMap4.put("productType", string5);
                        hashMap4.put("wapUrl", string6);
                        hashMap4.put("imgUrl", string7);
                        hashMap4.put("title", string8);
                        arrayList2.add(hashMap4);
                    }
                    hashMap2.put("tops", arrayList2);
                }
                if (jSONObject2.has("hot_area_list")) {
                    JSONArray jSONArray3 = jSONObject2.getJSONArray("hot_area_list");
                    ArrayList arrayList3 = new ArrayList();
                    for (int i4 = 0; i4 < jSONArray3.length(); i4++) {
                        HashMap hashMap5 = new HashMap();
                        JSONObject jSONObject5 = (JSONObject) jSONArray3.get(i4);
                        String string9 = jSONObject5.getString("hot_area_title");
                        String string10 = jSONObject5.getString("hot_area_image_url");
                        Integer valueOf4 = Integer.valueOf(jSONObject5.getInt("hot_area_type"));
                        String string11 = jSONObject5.getString("hot_area_product_type");
                        String string12 = jSONObject5.getString("hot_area_product_tag");
                        String string13 = jSONObject5.getString("hot_area_wap_url");
                        hashMap5.put("hotAreaTitle", string9);
                        hashMap5.put("hotAreaImageUrl", string10);
                        hashMap5.put("hotAreaType", valueOf4);
                        hashMap5.put("hotAreaProductType", string11);
                        hashMap5.put("hotAreaProductTag", string12);
                        hashMap5.put("hotAreaWapUrl", string13);
                        arrayList3.add(hashMap5);
                    }
                    hashMap2.put("areas", arrayList3);
                }
                if (jSONObject2.has("hot_video_category_list")) {
                    JSONArray jSONArray4 = jSONObject2.getJSONArray("hot_video_category_list");
                    ArrayList arrayList4 = new ArrayList();
                    for (int i5 = 0; i5 < jSONArray4.length(); i5++) {
                        HashMap hashMap6 = new HashMap();
                        JSONObject jSONObject6 = (JSONObject) jSONArray4.get(i5);
                        Integer valueOf5 = Integer.valueOf(jSONObject6.getInt("hot_video_category_id"));
                        String string14 = jSONObject6.getString("hot_video_category_name");
                        String string15 = jSONObject6.getString("hot_video_category_tag");
                        hashMap6.put("categotyId", valueOf5);
                        hashMap6.put("categotyName", string14);
                        hashMap6.put("categoryTag", string15);
                        arrayList4.add(hashMap6);
                    }
                    hashMap2.put("videos", arrayList4);
                }
                if (jSONObject2.has("hot_category_list")) {
                    JSONArray jSONArray5 = jSONObject2.getJSONArray("hot_category_list");
                    ArrayList arrayList5 = new ArrayList();
                    for (int i6 = 0; i6 < jSONArray5.length(); i6++) {
                        HashMap hashMap7 = new HashMap();
                        JSONObject jSONObject7 = (JSONObject) jSONArray5.get(i6);
                        Integer valueOf6 = Integer.valueOf(jSONObject7.getInt("hot_category_id"));
                        String string16 = jSONObject7.getString("hot_category_name");
                        String string17 = jSONObject7.getString("hot_category_subname");
                        Integer valueOf7 = Integer.valueOf(jSONObject7.getInt("hot_category_type"));
                        String string18 = jSONObject7.getString("hot_category_product_type");
                        String string19 = jSONObject7.getString("hot_category_tag_list");
                        String string20 = jSONObject7.getString("hot_category_wap_url");
                        String string21 = jSONObject7.getString("hot_category_image_url");
                        hashMap7.put("categoryId", valueOf6);
                        hashMap7.put("categoryName", string16);
                        hashMap7.put("categorySubName", string17);
                        hashMap7.put("categoryType", valueOf7);
                        hashMap7.put("categoryProductType", string18);
                        hashMap7.put("categoryTag", string19);
                        hashMap7.put("categoryWapUrl", string20);
                        hashMap7.put("categoryImgUrl", string21);
                        arrayList5.add(hashMap7);
                    }
                    hashMap2.put("hotTags", arrayList5);
                }
                if (jSONObject2.has("tv_list")) {
                    JSONArray jSONArray6 = jSONObject2.getJSONArray("tv_list");
                    ArrayList arrayList6 = new ArrayList();
                    for (int i7 = 0; i7 < jSONArray6.length(); i7++) {
                        HashMap hashMap8 = new HashMap();
                        JSONObject jSONObject8 = (JSONObject) jSONArray6.get(i7);
                        Integer valueOf8 = Integer.valueOf(jSONObject8.getInt("tv_station_id"));
                        String string22 = jSONObject8.getString("tv_station_name");
                        String string23 = jSONObject8.getString("tv_station_logo");
                        Integer valueOf9 = Integer.valueOf(jSONObject8.getInt("need_member"));
                        JSONArray jSONArray7 = jSONObject8.getJSONArray("tv_live_url");
                        ArrayList arrayList7 = new ArrayList();
                        ArrayList arrayList8 = new ArrayList();
                        for (int i8 = 0; i8 < jSONArray7.length(); i8++) {
                            arrayList7.add(jSONArray7.getJSONObject(i8).getString("source_url"));
                            arrayList8.add(jSONArray7.getJSONObject(i8).getString("source"));
                        }
                        hashMap8.put("tvId", valueOf8);
                        hashMap8.put("tvName", string22);
                        hashMap8.put("logoUrl", string23);
                        hashMap8.put("needMember", valueOf9);
                        hashMap8.put("tvLiveUrls", arrayList7);
                        hashMap8.put("tvLiveSourceUrls", arrayList8);
                        arrayList6.add(hashMap8);
                    }
                    hashMap2.put("tvList", arrayList6);
                }
                JSONObject jSONObject9 = jSONObject2.getJSONObject("homepage_tv");
                ArrayList arrayList9 = new ArrayList();
                JSONArray jSONArray8 = jSONObject9.getJSONArray("product_list");
                String string24 = jSONObject9.getString("tag_id");
                String string25 = jSONObject9.getString("name");
                Integer valueOf10 = Integer.valueOf(jSONObject9.getInt(Constants.ELEMNAME_SORT_STRING));
                for (int i9 = 0; i9 < jSONArray8.length(); i9++) {
                    HashMap hashMap9 = new HashMap();
                    JSONObject jSONObject10 = (JSONObject) jSONArray8.get(i9);
                    int i10 = jSONObject10.getInt("product_id");
                    String string26 = jSONObject10.getString("product_name");
                    Double valueOf11 = Double.valueOf(jSONObject10.getDouble("file_totalsize"));
                    Integer valueOf12 = Integer.valueOf(jSONObject10.getInt("download_count"));
                    String string27 = jSONObject10.getString("img_url");
                    if (jSONObject10.has("video_set")) {
                        hashMap9.put("videoSet", Integer.valueOf(jSONObject10.getInt("video_set")));
                    }
                    if (jSONObject10.has("app_package")) {
                        String string28 = jSONObject10.getString("app_package");
                        hashMap9.put("appPackage", string28);
                        Log.m14400c(f13476a, "parseMainPageContent appPackage->" + string28);
                    }
                    hashMap9.put("productId", Integer.valueOf(i10));
                    hashMap9.put("productName", string26);
                    hashMap9.put("fileTotalSize", valueOf11);
                    hashMap9.put("downloadCount", valueOf12);
                    hashMap9.put("imgUrl", string27);
                    hashMap9.put("isSpeed", 0);
                    if (jSONObject10.has("is_speed") && jSONObject10.get("is_speed") != null && jSONObject10.getInt("is_speed") == 1) {
                        hashMap9.put("isSpeed", 1);
                    }
                    ArrayList arrayList10 = new ArrayList();
                    JSONArray jSONArray9 = jSONObject10.getJSONArray(CommonNetImpl.TAG);
                    for (int i11 = 0; i11 < jSONArray9.length(); i11++) {
                        HashMap hashMap10 = new HashMap();
                        JSONObject jSONObject11 = (JSONObject) jSONArray9.get(i11);
                        String string29 = jSONObject11.getString("tag_id");
                        String string30 = jSONObject11.getString("tag_name");
                        hashMap10.put("tag_id", string29);
                        hashMap10.put("tag_name", string30);
                        arrayList10.add(hashMap10);
                    }
                    hashMap9.put("tags", arrayList10);
                    arrayList9.add(hashMap9);
                }
                hashMap2.put("tvTagId", string24);
                hashMap2.put("tvName", string25);
                hashMap2.put("tvSort", valueOf10);
                hashMap2.put("homeTvList", arrayList9);
                JSONObject jSONObject12 = jSONObject2.getJSONObject("homepage_movie");
                ArrayList arrayList11 = new ArrayList();
                JSONArray jSONArray10 = jSONObject12.getJSONArray("product_list");
                String string31 = jSONObject12.getString("tag_id");
                String string32 = jSONObject12.getString("name");
                Integer valueOf13 = Integer.valueOf(jSONObject12.getInt(Constants.ELEMNAME_SORT_STRING));
                for (int i12 = 0; i12 < jSONArray10.length(); i12++) {
                    HashMap hashMap11 = new HashMap();
                    JSONObject jSONObject13 = (JSONObject) jSONArray10.get(i12);
                    Integer valueOf14 = Integer.valueOf(jSONObject13.getInt("product_id"));
                    String string33 = jSONObject13.getString("product_name");
                    Double valueOf15 = Double.valueOf(jSONObject13.getDouble("file_totalsize"));
                    Integer valueOf16 = Integer.valueOf(jSONObject13.getInt("download_count"));
                    String string34 = jSONObject13.getString("img_url");
                    if (jSONObject13.has("video_set")) {
                        hashMap11.put("videoSet", Integer.valueOf(jSONObject13.getInt("video_set")));
                    }
                    if (jSONObject13.has("app_package")) {
                        String string35 = jSONObject13.getString("app_package");
                        hashMap11.put("appPackage", string35);
                        Log.m14400c(f13476a, "parseMainPageContent appPackage->" + string35);
                    }
                    hashMap11.put("productId", valueOf14);
                    hashMap11.put("productName", string33);
                    hashMap11.put("fileTotalSize", valueOf15);
                    hashMap11.put("downloadCount", valueOf16);
                    hashMap11.put("imgUrl", string34);
                    hashMap11.put("isSpeed", 0);
                    if (jSONObject13.has("is_speed") && jSONObject13.get("is_speed") != null && jSONObject13.getInt("is_speed") == 1) {
                        hashMap11.put("isSpeed", 1);
                    }
                    ArrayList arrayList12 = new ArrayList();
                    JSONArray jSONArray11 = jSONObject13.getJSONArray(CommonNetImpl.TAG);
                    for (int i13 = 0; i13 < jSONArray11.length(); i13++) {
                        HashMap hashMap12 = new HashMap();
                        JSONObject jSONObject14 = (JSONObject) jSONArray11.get(i13);
                        String string36 = jSONObject14.getString("tag_id");
                        String string37 = jSONObject14.getString("tag_name");
                        hashMap12.put("tag_id", string36);
                        hashMap12.put("tag_name", string37);
                        arrayList12.add(hashMap12);
                    }
                    hashMap11.put("tags", arrayList12);
                    arrayList11.add(hashMap11);
                }
                hashMap2.put("movieTagId", string31);
                hashMap2.put("movieName", string32);
                hashMap2.put("movieSort", valueOf13);
                hashMap2.put("homeMovieList", arrayList11);
                JSONObject jSONObject15 = jSONObject2.getJSONObject("homepage_variety");
                ArrayList arrayList13 = new ArrayList();
                JSONArray jSONArray12 = jSONObject15.getJSONArray("product_list");
                String string38 = jSONObject15.getString("tag_id");
                String string39 = jSONObject15.getString("name");
                Integer valueOf17 = Integer.valueOf(jSONObject15.getInt(Constants.ELEMNAME_SORT_STRING));
                for (int i14 = 0; i14 < jSONArray12.length(); i14++) {
                    HashMap hashMap13 = new HashMap();
                    JSONObject jSONObject16 = (JSONObject) jSONArray12.get(i14);
                    Integer valueOf18 = Integer.valueOf(jSONObject16.getInt("product_id"));
                    String string40 = jSONObject16.getString("product_name");
                    Double valueOf19 = Double.valueOf(jSONObject16.getDouble("file_totalsize"));
                    Integer valueOf20 = Integer.valueOf(jSONObject16.getInt("download_count"));
                    if (jSONObject16.has("img_url")) {
                        hashMap13.put("imgUrl", jSONObject16.getString("img_url"));
                    }
                    if (jSONObject16.has("video_set")) {
                        hashMap13.put("videoSet", Integer.valueOf(jSONObject16.getInt("video_set")));
                    }
                    if (jSONObject16.has("app_package")) {
                        String string41 = jSONObject16.getString("app_package");
                        hashMap13.put("appPackage", string41);
                        Log.m14400c(f13476a, "parseMainPageContent appPackage->" + string41);
                    }
                    hashMap13.put("productId", valueOf18);
                    hashMap13.put("productName", string40);
                    hashMap13.put("fileTotalSize", valueOf19);
                    hashMap13.put("downloadCount", valueOf20);
                    hashMap13.put("isSpeed", 0);
                    if (jSONObject16.has("is_speed") && jSONObject16.get("is_speed") != null && jSONObject16.getInt("is_speed") == 1) {
                        hashMap13.put("isSpeed", 1);
                    }
                    ArrayList arrayList14 = new ArrayList();
                    JSONArray jSONArray13 = jSONObject16.getJSONArray(CommonNetImpl.TAG);
                    for (int i15 = 0; i15 < jSONArray13.length(); i15++) {
                        HashMap hashMap14 = new HashMap();
                        JSONObject jSONObject17 = (JSONObject) jSONArray13.get(i15);
                        String string42 = jSONObject17.getString("tag_id");
                        String string43 = jSONObject17.getString("tag_name");
                        hashMap14.put("tag_id", string42);
                        hashMap14.put("tag_name", string43);
                        arrayList14.add(hashMap14);
                    }
                    hashMap13.put("tags", arrayList14);
                    arrayList13.add(hashMap13);
                }
                hashMap2.put("varietyTagId", string38);
                hashMap2.put("varietyname", string39);
                hashMap2.put("varietySort", valueOf17);
                hashMap2.put("homeVarietyList", arrayList13);
                JSONObject jSONObject18 = jSONObject2.getJSONObject("homepage_cartoon");
                ArrayList arrayList15 = new ArrayList();
                JSONArray jSONArray14 = jSONObject18.getJSONArray("product_list");
                String string44 = jSONObject18.getString("tag_id");
                String string45 = jSONObject18.getString("name");
                Integer valueOf21 = Integer.valueOf(jSONObject18.getInt(Constants.ELEMNAME_SORT_STRING));
                for (int i16 = 0; i16 < jSONArray14.length(); i16++) {
                    HashMap hashMap15 = new HashMap();
                    JSONObject jSONObject19 = (JSONObject) jSONArray14.get(i16);
                    Integer valueOf22 = Integer.valueOf(jSONObject19.getInt("product_id"));
                    String string46 = jSONObject19.getString("product_name");
                    Double valueOf23 = Double.valueOf(jSONObject19.getDouble("file_totalsize"));
                    Integer valueOf24 = Integer.valueOf(jSONObject19.getInt("download_count"));
                    String string47 = jSONObject19.getString("img_url");
                    if (jSONObject19.has("video_set")) {
                        hashMap15.put("videoSet", Integer.valueOf(jSONObject19.getInt("video_set")));
                    }
                    if (jSONObject19.has("app_package")) {
                        String string48 = jSONObject19.getString("app_package");
                        hashMap15.put("appPackage", string48);
                        Log.m14400c(f13476a, "parseMainPageContent appPackage->" + string48);
                    }
                    hashMap15.put("productId", valueOf22);
                    hashMap15.put("productName", string46);
                    hashMap15.put("fileTotalSize", valueOf23);
                    hashMap15.put("downloadCount", valueOf24);
                    hashMap15.put("imgUrl", string47);
                    hashMap15.put("isSpeed", 0);
                    if (jSONObject19.has("is_speed") && jSONObject19.get("is_speed") != null && jSONObject19.getInt("is_speed") == 1) {
                        hashMap15.put("isSpeed", 1);
                    }
                    ArrayList arrayList16 = new ArrayList();
                    JSONArray jSONArray15 = jSONObject19.getJSONArray(CommonNetImpl.TAG);
                    for (int i17 = 0; i17 < jSONArray15.length(); i17++) {
                        HashMap hashMap16 = new HashMap();
                        JSONObject jSONObject20 = (JSONObject) jSONArray15.get(i17);
                        String string49 = jSONObject20.getString("tag_id");
                        String string50 = jSONObject20.getString("tag_name");
                        hashMap16.put("tag_id", string49);
                        hashMap16.put("tag_name", string50);
                        arrayList16.add(hashMap16);
                    }
                    hashMap15.put("tags", arrayList16);
                    arrayList15.add(hashMap15);
                }
                hashMap2.put("cartoonTagId", string44);
                hashMap2.put("cartoonName", string45);
                hashMap2.put("cartoonSort", valueOf21);
                hashMap2.put("homeCartoonList", arrayList15);
                JSONObject jSONObject21 = jSONObject2.getJSONObject("homepage_game");
                ArrayList arrayList17 = new ArrayList();
                JSONArray jSONArray16 = jSONObject21.getJSONArray("product_list");
                Integer valueOf25 = Integer.valueOf(jSONObject21.getInt(Constants.ELEMNAME_SORT_STRING));
                for (int i18 = 0; i18 < jSONArray16.length(); i18++) {
                    HashMap hashMap17 = new HashMap();
                    JSONObject jSONObject22 = (JSONObject) jSONArray16.get(i18);
                    Integer valueOf26 = Integer.valueOf(jSONObject22.getInt("product_id"));
                    String string51 = jSONObject22.getString("product_name");
                    Double valueOf27 = Double.valueOf(jSONObject22.getDouble("file_totalsize"));
                    Integer valueOf28 = Integer.valueOf(jSONObject22.getInt("download_count"));
                    String string52 = jSONObject22.getString("img_url");
                    if (jSONObject22.has("video_set")) {
                        hashMap17.put("videoSet", Integer.valueOf(jSONObject22.getInt("video_set")));
                    }
                    if (jSONObject22.has("app_package")) {
                        String string53 = jSONObject22.getString("app_package");
                        hashMap17.put("appPackage", string53);
                        Log.m14400c(f13476a, "parseMainPageContent appPackage->" + string53);
                    }
                    if (jSONObject22.has("app_points_reward")) {
                        hashMap17.put("appPointsReward", Integer.valueOf(jSONObject22.getInt("app_points_reward")));
                    }
                    hashMap17.put("productId", valueOf26);
                    hashMap17.put("productName", string51);
                    hashMap17.put("fileTotalSize", valueOf27);
                    hashMap17.put("downloadCount", valueOf28);
                    hashMap17.put("imgUrl", string52);
                    hashMap17.put("isSpeed", 0);
                    if (jSONObject22.has("is_speed") && jSONObject22.get("is_speed") != null && jSONObject22.getInt("is_speed") == 1) {
                        hashMap17.put("isSpeed", 1);
                    }
                    ArrayList arrayList18 = new ArrayList();
                    JSONArray jSONArray17 = jSONObject22.getJSONArray(CommonNetImpl.TAG);
                    for (int i19 = 0; i19 < jSONArray17.length(); i19++) {
                        HashMap hashMap18 = new HashMap();
                        JSONObject jSONObject23 = (JSONObject) jSONArray17.get(i19);
                        String string54 = jSONObject23.getString("tag_id");
                        String string55 = jSONObject23.getString("tag_name");
                        hashMap18.put("tag_id", string54);
                        hashMap18.put("tag_name", string55);
                        arrayList18.add(hashMap18);
                    }
                    hashMap17.put("tags", arrayList18);
                    arrayList17.add(hashMap17);
                }
                hashMap2.put("gameSort", valueOf25);
                hashMap2.put("homeGameList", arrayList17);
                JSONObject jSONObject24 = jSONObject2.getJSONObject("homepage_app");
                ArrayList arrayList19 = new ArrayList();
                JSONArray jSONArray18 = jSONObject24.getJSONArray("product_list");
                Integer valueOf29 = Integer.valueOf(jSONObject24.getInt(Constants.ELEMNAME_SORT_STRING));
                for (int i20 = 0; i20 < jSONArray18.length(); i20++) {
                    HashMap hashMap19 = new HashMap();
                    JSONObject jSONObject25 = (JSONObject) jSONArray18.get(i20);
                    Integer valueOf30 = Integer.valueOf(jSONObject25.getInt("product_id"));
                    String string56 = jSONObject25.getString("product_name");
                    Double valueOf31 = Double.valueOf(jSONObject25.getDouble("file_totalsize"));
                    Integer valueOf32 = Integer.valueOf(jSONObject25.getInt("download_count"));
                    String string57 = jSONObject25.getString("img_url");
                    if (jSONObject25.has("video_set")) {
                        hashMap19.put("videoSet", Integer.valueOf(jSONObject25.getInt("video_set")));
                    }
                    if (jSONObject25.has("app_package")) {
                        String string58 = jSONObject25.getString("app_package");
                        hashMap19.put("appPackage", string58);
                        Log.m14400c(f13476a, "parseMainPageContent appPackage->" + string58);
                    }
                    if (jSONObject25.has("app_points_reward")) {
                        hashMap19.put("appPointsReward", Integer.valueOf(jSONObject25.getInt("app_points_reward")));
                    }
                    hashMap19.put("productId", valueOf30);
                    hashMap19.put("productName", string56);
                    hashMap19.put("fileTotalSize", valueOf31);
                    hashMap19.put("downloadCount", valueOf32);
                    hashMap19.put("imgUrl", string57);
                    hashMap19.put("isSpeed", 0);
                    if (jSONObject25.has("is_speed") && jSONObject25.get("is_speed") != null && jSONObject25.getInt("is_speed") == 1) {
                        hashMap19.put("isSpeed", 1);
                    }
                    ArrayList arrayList20 = new ArrayList();
                    JSONArray jSONArray19 = jSONObject25.getJSONArray(CommonNetImpl.TAG);
                    for (int i21 = 0; i21 < jSONArray19.length(); i21++) {
                        HashMap hashMap20 = new HashMap();
                        JSONObject jSONObject26 = (JSONObject) jSONArray19.get(i21);
                        String string59 = jSONObject26.getString("tag_id");
                        String string60 = jSONObject26.getString("tag_name");
                        hashMap20.put("tag_id", string59);
                        hashMap20.put("tag_name", string60);
                        arrayList20.add(hashMap20);
                    }
                    hashMap19.put("tags", arrayList20);
                    arrayList19.add(hashMap19);
                }
                hashMap2.put("appSort", valueOf29);
                hashMap2.put("homeAppList", arrayList19);
                hashMap.put("data", hashMap2);
            }
            return hashMap;
        } catch (JSONException e) {
            Log.m14403e(f13476a, "ResourceParse->parseMainPageContent():" + e.toString());
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e2) {
            Log.m14403e(f13476a, "ResourceParse->parseMainPageContent():" + e2.toString());
            e2.printStackTrace();
            return null;
        }
    }

    /* renamed from: l */
    public static HashMap<String, Object> m14461l(byte[] bArr) {
        try {
            HashMap<String, Object> hashMap = new HashMap<>();
            JSONObject jSONObject = new JSONObject(new String(bArr, "UTF-8"));
            int i = jSONObject.getInt(AbstractC5718b.JSON_ERRORCODE);
            hashMap.put(AbstractC5718b.JSON_ERRORCODE, Integer.valueOf(i));
            if (i == 0) {
                HashMap hashMap2 = new HashMap();
                JSONObject jSONObject2 = (JSONObject) jSONObject.get("data");
                JSONObject jSONObject3 = jSONObject2.getJSONObject("new_video");
                ArrayList arrayList = new ArrayList();
                JSONArray jSONArray = jSONObject3.getJSONArray("product_list");
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    HashMap hashMap3 = new HashMap();
                    JSONObject jSONObject4 = (JSONObject) jSONArray.get(i2);
                    int i3 = jSONObject4.getInt("product_id");
                    String string = jSONObject4.getString("product_name");
                    Double valueOf = Double.valueOf(jSONObject4.getDouble("file_totalsize"));
                    Integer valueOf2 = Integer.valueOf(jSONObject4.getInt("download_count"));
                    String string2 = jSONObject4.getString("img_url");
                    hashMap3.put("productId", Integer.valueOf(i3));
                    hashMap3.put("productName", string);
                    hashMap3.put("fileTotalSize", valueOf);
                    hashMap3.put("downloadCount", valueOf2);
                    hashMap3.put("imgUrl", string2);
                    hashMap3.put("isSpeed", 0);
                    if (jSONObject4.has("is_speed") && jSONObject4.get("is_speed") != null && jSONObject4.getInt("is_speed") == 1) {
                        hashMap3.put("isSpeed", 1);
                    }
                    ArrayList arrayList2 = new ArrayList();
                    JSONArray jSONArray2 = jSONObject4.getJSONArray(CommonNetImpl.TAG);
                    for (int i4 = 0; i4 < jSONArray2.length(); i4++) {
                        JSONObject jSONObject5 = (JSONObject) jSONArray2.get(i4);
                        arrayList2.add(new String[]{jSONObject5.getString("tag_id"), jSONObject5.getString("tag_name")});
                    }
                    hashMap3.put("tags", arrayList2);
                    arrayList.add(hashMap3);
                }
                hashMap2.put("newVideoList", arrayList);
                JSONObject jSONObject6 = jSONObject2.getJSONObject("new_game");
                ArrayList arrayList3 = new ArrayList();
                JSONArray jSONArray3 = jSONObject6.getJSONArray("product_list");
                for (int i5 = 0; i5 < jSONArray3.length(); i5++) {
                    HashMap hashMap4 = new HashMap();
                    JSONObject jSONObject7 = (JSONObject) jSONArray3.get(i5);
                    int i6 = jSONObject7.getInt("product_id");
                    String string3 = jSONObject7.getString("product_name");
                    Double valueOf3 = Double.valueOf(jSONObject7.getDouble("file_totalsize"));
                    Integer valueOf4 = Integer.valueOf(jSONObject7.getInt("download_count"));
                    String string4 = jSONObject7.getString("img_url");
                    if (jSONObject7.has("app_package")) {
                        String string5 = jSONObject7.getString("app_package");
                        hashMap4.put("appPackage", string5);
                        Log.m14400c(f13476a, "parseMainPageContent appPackage->" + string5);
                    }
                    hashMap4.put("productId", Integer.valueOf(i6));
                    hashMap4.put("productName", string3);
                    hashMap4.put("fileTotalSize", valueOf3);
                    hashMap4.put("downloadCount", valueOf4);
                    hashMap4.put("imgUrl", string4);
                    hashMap4.put("isSpeed", 0);
                    if (jSONObject7.has("is_speed") && jSONObject7.get("is_speed") != null && jSONObject7.getInt("is_speed") == 1) {
                        hashMap4.put("isSpeed", 1);
                    }
                    ArrayList arrayList4 = new ArrayList();
                    JSONArray jSONArray4 = jSONObject7.getJSONArray(CommonNetImpl.TAG);
                    for (int i7 = 0; i7 < jSONArray4.length(); i7++) {
                        JSONObject jSONObject8 = (JSONObject) jSONArray4.get(i7);
                        arrayList4.add(new String[]{jSONObject8.getString("tag_id"), jSONObject8.getString("tag_name")});
                    }
                    hashMap4.put("tags", arrayList4);
                    JSONArray jSONArray5 = jSONObject7.getJSONArray("download_url");
                    ArrayList arrayList5 = new ArrayList();
                    for (int i8 = 0; i8 < jSONArray5.length(); i8++) {
                        HashMap hashMap5 = new HashMap();
                        JSONObject jSONObject9 = (JSONObject) jSONArray5.get(i8);
                        int i9 = jSONObject9.getInt("file_id");
                        String string6 = jSONObject9.getString("showname");
                        Integer valueOf5 = Integer.valueOf(jSONObject9.getInt("no"));
                        String string7 = jSONObject9.getString("md5");
                        String string8 = jSONObject9.getString("filename");
                        double d = jSONObject9.getDouble("filesize");
                        String string9 = jSONObject9.getString("file_url");
                        hashMap5.put("fileId", Integer.valueOf(i9));
                        hashMap5.put("showName", string6);
                        hashMap5.put("no", valueOf5);
                        hashMap5.put("md5", string7);
                        hashMap5.put("fileName", string8);
                        hashMap5.put("fileSize", Double.valueOf(d));
                        hashMap5.put("fileUrl", string9);
                        arrayList5.add(hashMap5);
                    }
                    hashMap4.put("downloads", arrayList5);
                    arrayList3.add(hashMap4);
                }
                hashMap2.put("newGameList", arrayList3);
                JSONObject jSONObject10 = jSONObject2.getJSONObject("new_app");
                ArrayList arrayList6 = new ArrayList();
                JSONArray jSONArray6 = jSONObject10.getJSONArray("product_list");
                for (int i10 = 0; i10 < jSONArray6.length(); i10++) {
                    HashMap hashMap6 = new HashMap();
                    JSONObject jSONObject11 = (JSONObject) jSONArray6.get(i10);
                    int i11 = jSONObject11.getInt("product_id");
                    String string10 = jSONObject11.getString("product_name");
                    Double valueOf6 = Double.valueOf(jSONObject11.getDouble("file_totalsize"));
                    Integer valueOf7 = Integer.valueOf(jSONObject11.getInt("download_count"));
                    String string11 = jSONObject11.getString("img_url");
                    if (jSONObject11.has("app_package")) {
                        String string12 = jSONObject11.getString("app_package");
                        hashMap6.put("appPackage", string12);
                        Log.m14400c(f13476a, "parseMainPageContent appPackage->" + string12);
                    }
                    hashMap6.put("productId", Integer.valueOf(i11));
                    hashMap6.put("productName", string10);
                    hashMap6.put("fileTotalSize", valueOf6);
                    hashMap6.put("downloadCount", valueOf7);
                    hashMap6.put("imgUrl", string11);
                    hashMap6.put("isSpeed", 0);
                    if (jSONObject11.has("is_speed") && jSONObject11.get("is_speed") != null && jSONObject11.getInt("is_speed") == 1) {
                        hashMap6.put("isSpeed", 1);
                    }
                    ArrayList arrayList7 = new ArrayList();
                    JSONArray jSONArray7 = jSONObject11.getJSONArray(CommonNetImpl.TAG);
                    for (int i12 = 0; i12 < jSONArray7.length(); i12++) {
                        JSONObject jSONObject12 = (JSONObject) jSONArray7.get(i12);
                        arrayList7.add(new String[]{jSONObject12.getString("tag_id"), jSONObject12.getString("tag_name")});
                    }
                    hashMap6.put("tags", arrayList7);
                    JSONArray jSONArray8 = jSONObject11.getJSONArray("download_url");
                    ArrayList arrayList8 = new ArrayList();
                    for (int i13 = 0; i13 < jSONArray8.length(); i13++) {
                        HashMap hashMap7 = new HashMap();
                        JSONObject jSONObject13 = (JSONObject) jSONArray8.get(i13);
                        int i14 = jSONObject13.getInt("file_id");
                        String string13 = jSONObject13.getString("showname");
                        Integer valueOf8 = Integer.valueOf(jSONObject13.getInt("no"));
                        String string14 = jSONObject13.getString("md5");
                        String string15 = jSONObject13.getString("filename");
                        double d2 = jSONObject13.getDouble("filesize");
                        String string16 = jSONObject13.getString("file_url");
                        hashMap7.put("fileId", Integer.valueOf(i14));
                        hashMap7.put("showName", string13);
                        hashMap7.put("no", valueOf8);
                        hashMap7.put("md5", string14);
                        hashMap7.put("fileName", string15);
                        hashMap7.put("fileSize", Double.valueOf(d2));
                        hashMap7.put("fileUrl", string16);
                        arrayList8.add(hashMap7);
                    }
                    hashMap6.put("downloads", arrayList8);
                    arrayList6.add(hashMap6);
                }
                hashMap2.put("newAppList", arrayList6);
                hashMap.put("data", hashMap2);
            }
            return hashMap;
        } catch (JSONException e) {
            Log.m14403e(f13476a, "ResourceParse->parseMainPageContent():" + e.toString());
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e2) {
            Log.m14403e(f13476a, "ResourceParse->parseMainPageContent():" + e2.toString());
            e2.printStackTrace();
            return null;
        }
    }

    /* renamed from: m */
    public static HashMap<String, Object> m14462m(byte[] bArr) {
        int i;
        try {
            HashMap<String, Object> hashMap = new HashMap<>();
            JSONObject jSONObject = new JSONObject(new String(bArr, "UTF-8"));
            int i2 = jSONObject.getInt(AbstractC5718b.JSON_ERRORCODE);
            hashMap.put(AbstractC5718b.JSON_ERRORCODE, Integer.valueOf(i2));
            if (i2 == 0) {
                HashMap hashMap2 = new HashMap();
                JSONObject jSONObject2 = (JSONObject) jSONObject.get("data");
                JSONArray jSONArray = jSONObject2.getJSONArray("modules");
                ArrayList arrayList = new ArrayList();
                for (int i3 = 0; i3 < jSONArray.length(); i3++) {
                    HashMap hashMap3 = new HashMap();
                    JSONObject jSONObject3 = jSONArray.getJSONObject(i3);
                    String string = jSONObject3.getString("image_url");
                    String string2 = jSONObject3.getString("wap_url");
                    Integer valueOf = Integer.valueOf(jSONObject3.getInt("need_internet"));
                    String string3 = jSONObject3.getString("title");
                    Object obj = jSONObject3.get("has_submenu");
                    Integer num = jSONObject3.has("has_title_bar") ? (Integer) jSONObject3.get("has_title_bar") : 0;
                    if (obj == null) {
                        i = 0;
                    } else {
                        i = obj;
                    }
                    hashMap3.put("has_submenu", i);
                    hashMap3.put("has_title_bar", num);
                    hashMap3.put("image_url", string);
                    hashMap3.put("wap_url", string2);
                    hashMap3.put("need_internet", valueOf);
                    hashMap3.put("title", string3);
                    arrayList.add(hashMap3);
                }
                hashMap2.put("modules", arrayList);
                JSONObject jSONObject4 = jSONObject2.getJSONObject(UMCommonContent.f22183an);
                HashMap hashMap4 = new HashMap();
                String string4 = jSONObject4.getString("image_url");
                String string5 = jSONObject4.getString("wap_url");
                hashMap4.put("image_url", string4);
                hashMap4.put("wap_url", string5);
                if (jSONObject2.has("extend_infos")) {
                    JSONObject jSONObject5 = jSONObject2.getJSONObject("extend_infos");
                    HashMap hashMap5 = new HashMap();
                    String string6 = jSONObject5.getString("share_url");
                    String string7 = jSONObject5.getString("signin_url");
                    hashMap5.put("share_url", string6);
                    hashMap5.put("signin_url", string7);
                    hashMap2.put("extend_infos", hashMap5);
                }
                hashMap2.put(UMCommonContent.f22183an, hashMap4);
                hashMap.put("data", hashMap2);
            }
            return hashMap;
        } catch (UnsupportedEncodingException e) {
            Log.m14403e(f13476a, "ResourceParse->parseMainPageContent():" + e.toString());
            e.printStackTrace();
            return null;
        } catch (JSONException e2) {
            Log.m14403e(f13476a, "ResourceParse->parseMainPageContent():" + e2.toString());
            e2.printStackTrace();
            return null;
        }
    }

    /* renamed from: n */
    public static HashMap<String, Object> m14463n(byte[] bArr) {
        try {
            HashMap<String, Object> hashMap = new HashMap<>();
            JSONObject jSONObject = new JSONObject(new String(bArr, "UTF-8"));
            int i = jSONObject.getInt(AbstractC5718b.JSON_ERRORCODE);
            hashMap.put(AbstractC5718b.JSON_ERRORCODE, Integer.valueOf(i));
            if (i == 0) {
                HashMap hashMap2 = new HashMap();
                JSONObject jSONObject2 = (JSONObject) jSONObject.get("data");
                JSONArray jSONArray = jSONObject2.getJSONArray("groups");
                String string = jSONObject2.getString("top_image_url");
                String string2 = jSONObject2.getString("logo_image_url");
                String string3 = jSONObject2.getString("name");
                String string4 = jSONObject2.getString("short_name");
                String string5 = jSONObject2.getString("home_url");
                ArrayList arrayList = new ArrayList();
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    HashMap hashMap3 = new HashMap();
                    JSONObject jSONObject3 = jSONArray.getJSONObject(i2);
                    String string6 = jSONObject3.getString("title");
                    String string7 = jSONObject3.getString("url");
                    JSONArray jSONArray2 = jSONObject3.getJSONArray("news");
                    ArrayList arrayList2 = new ArrayList();
                    for (int i3 = 0; i3 < jSONArray2.length(); i3++) {
                        HashMap hashMap4 = new HashMap();
                        JSONObject jSONObject4 = jSONArray2.getJSONObject(i3);
                        Integer valueOf = Integer.valueOf(jSONObject4.getInt("type"));
                        String string8 = jSONObject4.getString("wap_url");
                        String string9 = jSONObject4.getString("title");
                        String string10 = jSONObject4.getString("sub_title");
                        hashMap4.put("type", valueOf);
                        hashMap4.put("wap_url", string8);
                        hashMap4.put("title", string9);
                        hashMap4.put("sub_title", string10);
                        arrayList2.add(hashMap4);
                    }
                    hashMap3.put("title", string6);
                    hashMap3.put("url", string7);
                    hashMap3.put("news", arrayList2);
                    arrayList.add(hashMap3);
                }
                hashMap2.put("top_image_url", string);
                hashMap2.put("logo_image_url", string2);
                hashMap2.put("name", string3);
                hashMap2.put("short_name", string4);
                hashMap2.put("home_url", string5);
                hashMap2.put("groups", arrayList);
                hashMap.put("data", hashMap2);
            }
            return hashMap;
        } catch (UnsupportedEncodingException e) {
            Log.m14403e(f13476a, "ResourceParse->parseMainPageContent():" + e.toString());
            e.printStackTrace();
            return null;
        } catch (JSONException e2) {
            Log.m14403e(f13476a, "ResourceParse->parseMainPageContent():" + e2.toString());
            e2.printStackTrace();
            return null;
        }
    }

    /* renamed from: o */
    public static HashMap<String, Object> m14464o(byte[] bArr) {
        try {
            HashMap<String, Object> hashMap = new HashMap<>();
            JSONObject jSONObject = new JSONObject(new String(bArr, "UTF-8"));
            int i = jSONObject.getInt(AbstractC5718b.JSON_ERRORCODE);
            hashMap.put(AbstractC5718b.JSON_ERRORCODE, Integer.valueOf(i));
            if (i == 0) {
                HashMap hashMap2 = new HashMap();
                JSONArray jSONArray = ((JSONObject) jSONObject.get("data")).getJSONArray("navigations");
                ArrayList arrayList = new ArrayList();
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    HashMap hashMap3 = new HashMap();
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i2);
                    String string = jSONObject2.getString("logo_url");
                    String string2 = jSONObject2.getString("wap_url");
                    String string3 = jSONObject2.getString("title");
                    hashMap3.put("logo_url", string);
                    hashMap3.put("wap_url", string2);
                    hashMap3.put("title", string3);
                    arrayList.add(hashMap3);
                }
                hashMap2.put("navigations", arrayList);
                hashMap.put("data", hashMap2);
            }
            return hashMap;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        } catch (JSONException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    /* renamed from: p */
    public static HashMap<String, Object> m14465p(byte[] bArr) {
        try {
            HashMap<String, Object> hashMap = new HashMap<>();
            JSONObject jSONObject = new JSONObject(new String(bArr, "UTF-8"));
            int i = jSONObject.getInt(AbstractC5718b.JSON_ERRORCODE);
            hashMap.put(AbstractC5718b.JSON_ERRORCODE, Integer.valueOf(i));
            if (i == 0) {
                HashMap hashMap2 = new HashMap();
                JSONObject jSONObject2 = (JSONObject) jSONObject.get("data");
                ArrayList arrayList = new ArrayList();
                JSONArray jSONArray = jSONObject2.getJSONArray("group");
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    HashMap hashMap3 = new HashMap();
                    JSONObject jSONObject3 = (JSONObject) jSONArray.get(i2);
                    String string = jSONObject3.getString("group_id");
                    String string2 = jSONObject3.getString("group_name");
                    hashMap3.put("group_id", string);
                    hashMap3.put("group_name", string2);
                    JSONArray jSONArray2 = jSONObject3.getJSONArray("tags");
                    ArrayList arrayList2 = new ArrayList();
                    for (int i3 = 0; i3 < jSONArray2.length(); i3++) {
                        JSONObject jSONObject4 = (JSONObject) jSONArray2.get(i3);
                        arrayList2.add(new String[]{jSONObject4.getString("tag_id"), jSONObject4.getString("tag_name")});
                    }
                    hashMap3.put("tags", arrayList2);
                    arrayList.add(hashMap3);
                }
                hashMap2.put("groups", arrayList);
                JSONArray jSONArray3 = jSONObject2.getJSONArray("product_list");
                ArrayList arrayList3 = new ArrayList();
                for (int i4 = 0; i4 < jSONArray3.length(); i4++) {
                    HashMap hashMap4 = new HashMap();
                    JSONObject jSONObject5 = (JSONObject) jSONArray3.get(i4);
                    Integer valueOf = Integer.valueOf(jSONObject5.getInt("product_id"));
                    String string3 = jSONObject5.getString("product_name");
                    String string4 = jSONObject5.getString("img_url");
                    String string5 = jSONObject5.getString("sub_title");
                    Integer valueOf2 = Integer.valueOf(jSONObject5.getInt("is_speed"));
                    if (jSONObject5.has("video_set")) {
                        hashMap4.put("videoset", Integer.valueOf(jSONObject5.getInt("video_set")));
                    }
                    Integer valueOf3 = Integer.valueOf(jSONObject5.getInt("episode_total"));
                    Integer valueOf4 = Integer.valueOf(jSONObject5.getInt("episode_available"));
                    hashMap4.put("productId", valueOf);
                    hashMap4.put("productName", string3);
                    hashMap4.put("imgUrl", string4);
                    hashMap4.put("subTitle", string5);
                    hashMap4.put("isSpeed", valueOf2);
                    hashMap4.put("totalEpisode", valueOf3);
                    hashMap4.put("availableEpisode", valueOf4);
                    arrayList3.add(hashMap4);
                }
                hashMap2.put("videos", arrayList3);
                hashMap.put("data", hashMap2);
                return hashMap;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
        return null;
    }

    /* renamed from: q */
    public static HashMap<String, Object> m14466q(byte[] bArr) {
        HashMap<String, Object> hashMap = new HashMap<>();
        try {
            JSONObject jSONObject = new JSONObject(new String(bArr, "UTF-8"));
            int i = jSONObject.getInt(AbstractC5718b.JSON_ERRORCODE);
            String string = jSONObject.getString("resultMsg");
            hashMap.put(AbstractC5718b.JSON_ERRORCODE, Integer.valueOf(i));
            hashMap.put("resultMsg", string);
            if (i != 0) {
                return hashMap;
            }
            HashMap hashMap2 = new HashMap();
            JSONObject jSONObject2 = (JSONObject) jSONObject.get("data");
            Integer valueOf = Integer.valueOf(jSONObject2.getInt("point_value"));
            Integer valueOf2 = Integer.valueOf(jSONObject2.getInt("point_change_category"));
            hashMap2.put("point_type", (Integer) jSONObject2.get("point_type"));
            hashMap2.put("point_value", valueOf);
            hashMap2.put("point_change_category", valueOf2);
            hashMap.put("data", hashMap2);
            return hashMap;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        } catch (JSONException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    /* renamed from: r */
    public static HashMap<String, Object> m14467r(byte[] bArr) {
        HashMap<String, Object> hashMap = new HashMap<>();
        try {
            JSONObject jSONObject = new JSONObject(new String(bArr, "UTF-8"));
            int i = jSONObject.getInt(AbstractC5718b.JSON_ERRORCODE);
            String string = jSONObject.getString("resultMsg");
            hashMap.put(AbstractC5718b.JSON_ERRORCODE, Integer.valueOf(i));
            hashMap.put("resultMsg", string);
            if (i != 0) {
                return hashMap;
            }
            HashMap hashMap2 = new HashMap();
            JSONObject jSONObject2 = (JSONObject) jSONObject.get("data");
            Integer valueOf = Integer.valueOf(jSONObject2.getInt("point_value"));
            Integer valueOf2 = Integer.valueOf(jSONObject2.getInt("point_change_category"));
            hashMap2.put("point_type", (Integer) jSONObject2.get("point_type"));
            hashMap2.put("point_value", valueOf);
            hashMap2.put("point_change_category", valueOf2);
            hashMap.put("data", hashMap2);
            return hashMap;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        } catch (JSONException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    /* renamed from: s */
    public static HashMap<String, Object> m14468s(byte[] bArr) {
        HashMap<String, Object> hashMap = new HashMap<>();
        try {
            JSONObject jSONObject = new JSONObject(new String(bArr, "UTF-8"));
            int i = jSONObject.getInt(AbstractC5718b.JSON_ERRORCODE);
            hashMap.put(AbstractC5718b.JSON_ERRORCODE, Integer.valueOf(i));
            if (i != 0) {
                return hashMap;
            }
            hashMap.put("data", jSONObject.getJSONArray("data"));
            return hashMap;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        } catch (JSONException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    /* renamed from: t */
    public static HashMap<String, Object> m14469t(byte[] bArr) {
        HashMap<String, Object> hashMap = new HashMap<>();
        try {
            JSONObject jSONObject = new JSONObject(new String(bArr, "UTF-8"));
            int i = jSONObject.getInt(AbstractC5718b.JSON_ERRORCODE);
            hashMap.put(AbstractC5718b.JSON_ERRORCODE, Integer.valueOf(i));
            if (i == 0) {
                HashMap hashMap2 = new HashMap();
                JSONObject jSONObject2 = (JSONObject) jSONObject.get("data");
                ArrayList arrayList = new ArrayList();
                JSONArray jSONArray = jSONObject2.getJSONArray("hots");
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    HashMap hashMap3 = new HashMap();
                    JSONObject jSONObject3 = (JSONObject) jSONArray.get(i2);
                    if (jSONObject3.has("icon_url")) {
                        hashMap3.put("icon_url", jSONObject3.getString("icon_url"));
                    }
                    if (jSONObject3.has("tag_name")) {
                        hashMap3.put("tag_name", jSONObject3.getString("tag_name"));
                    }
                    if (jSONObject3.has("tag_bg_color")) {
                        hashMap3.put("tag_bg_color", jSONObject3.getString("tag_bg_color"));
                    }
                    if (jSONObject3.has("source_type")) {
                        hashMap3.put("source_type", Integer.valueOf(jSONObject3.getInt("source_type")));
                    }
                    if (jSONObject3.has("product_type")) {
                        hashMap3.put("product_type", jSONObject3.getString("product_type"));
                    }
                    if (jSONObject3.has("product_id")) {
                        hashMap3.put("product_id", Integer.valueOf(jSONObject3.getInt("product_id")));
                    }
                    if (jSONObject3.has("wap_url")) {
                        hashMap3.put("wap_url", jSONObject3.getString("wap_url"));
                    }
                    arrayList.add(hashMap3);
                }
                ArrayList arrayList2 = new ArrayList();
                JSONArray jSONArray2 = jSONObject2.getJSONArray("navigations");
                for (int i3 = 0; i3 < jSONArray2.length(); i3++) {
                    HashMap hashMap4 = new HashMap();
                    JSONObject jSONObject4 = (JSONObject) jSONArray2.get(i3);
                    if (jSONObject4.has("logo_url")) {
                        hashMap4.put("logo_url", jSONObject4.getString("logo_url"));
                    }
                    if (jSONObject4.has("title")) {
                        hashMap4.put("title", jSONObject4.getString("title"));
                    }
                    if (jSONObject4.has("title_color")) {
                        hashMap4.put("title_color", jSONObject4.getString("title_color"));
                    }
                    if (jSONObject4.has("sub_title")) {
                        hashMap4.put("sub_title", jSONObject4.getString("sub_title"));
                    }
                    if (jSONObject4.has("source_type")) {
                        hashMap4.put("source_type", Integer.valueOf(jSONObject4.getInt("source_type")));
                    }
                    if (jSONObject4.has("product_type")) {
                        hashMap4.put("product_type", jSONObject4.getString("product_type"));
                    }
                    int i4 = 0;
                    if (jSONObject4.has("product_id")) {
                        i4 = Integer.valueOf(jSONObject4.getInt("product_id"));
                    }
                    hashMap4.put("product_id", i4);
                    if (jSONObject4.has("product_tag")) {
                        hashMap4.put("product_tag", jSONObject4.getString("product_tag"));
                    }
                    if (jSONObject4.has("wap_url")) {
                        hashMap4.put("wap_url", jSONObject4.getString("wap_url"));
                    }
                    arrayList2.add(hashMap4);
                }
                hashMap2.put("hots", arrayList);
                hashMap2.put("navigations", arrayList2);
                hashMap.put("data", hashMap2);
                return hashMap;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return null;
    }

    /* renamed from: u */
    public static HashMap<String, Object> m14470u(byte[] bArr) {
        HashMap<String, Object> hashMap = new HashMap<>();
        try {
            JSONObject jSONObject = new JSONObject(new String(bArr, "UTF-8"));
            int i = jSONObject.getInt(AbstractC5718b.JSON_ERRORCODE);
            hashMap.put(AbstractC5718b.JSON_ERRORCODE, Integer.valueOf(i));
            if (i == 0) {
                HashMap hashMap2 = new HashMap();
                JSONObject jSONObject2 = jSONObject.getJSONObject("data");
                hashMap2.put("tab_title", jSONObject2.getString("tab_title"));
                ArrayList arrayList = new ArrayList();
                JSONArray jSONArray = jSONObject2.getJSONArray("banner_list");
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    JSONObject jSONObject3 = (JSONObject) jSONArray.get(i2);
                    HashMap hashMap3 = new HashMap();
                    String string = jSONObject3.getString("title");
                    String string2 = jSONObject3.getString("icon_url");
                    long j = jSONObject3.getLong("update_time");
                    int i3 = jSONObject3.getInt("type");
                    String string3 = jSONObject3.getString("web_url");
                    hashMap3.put("title", string);
                    hashMap3.put("icon_url", string2);
                    hashMap3.put("update_time", Long.valueOf(j));
                    hashMap3.put("type", Integer.valueOf(i3));
                    hashMap3.put("web_url", string3);
                    arrayList.add(hashMap3);
                }
                hashMap2.put("banner_list", arrayList);
                ArrayList arrayList2 = new ArrayList();
                JSONArray jSONArray2 = jSONObject2.getJSONArray("function_list");
                for (int i4 = 0; i4 < jSONArray2.length(); i4++) {
                    JSONObject jSONObject4 = (JSONObject) jSONArray2.get(i4);
                    HashMap hashMap4 = new HashMap();
                    String string4 = jSONObject4.getString("title");
                    String string5 = jSONObject4.getString("icon_url");
                    long j2 = jSONObject4.getLong("update_time");
                    int i5 = jSONObject4.getInt("type");
                    String string6 = jSONObject4.getString("web_url");
                    hashMap4.put("title", string4);
                    hashMap4.put("icon_url", string5);
                    hashMap4.put("update_time", Long.valueOf(j2));
                    hashMap4.put("type", Integer.valueOf(i5));
                    hashMap4.put("web_url", string6);
                    arrayList2.add(hashMap4);
                }
                hashMap2.put("function_list", arrayList2);
                hashMap.put("data", hashMap2);
                return hashMap;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return null;
    }

    /* renamed from: a */
    public static HashMap<String, Object> m14445a(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            HashMap<String, Object> hashMap = new HashMap<>();
            String string = jSONObject.getString("title");
            String string2 = jSONObject.getString("action");
            if (jSONObject.has("params")) {
                hashMap.put("params", jSONObject.get("params"));
            }
            hashMap.put("title", string);
            hashMap.put("action", string2);
            ArrayList arrayList = new ArrayList();
            JSONArray jSONArray = jSONObject.getJSONArray("fields");
            for (int i = 0; i < jSONArray.length(); i++) {
                HashMap hashMap2 = new HashMap();
                JSONObject jSONObject2 = (JSONObject) jSONArray.get(i);
                String string3 = jSONObject2.getString("field_label");
                String string4 = jSONObject2.getString("field_name");
                Integer valueOf = Integer.valueOf(jSONObject2.getInt("field_type"));
                String string5 = jSONObject2.getString("field_value");
                Object obj = jSONObject2.get("field_select_value");
                String string6 = jSONObject2.getString("field_is_must");
                String string7 = jSONObject2.getString("field_rule");
                String string8 = jSONObject2.getString("field_check_error_msg");
                hashMap2.put("field_label", string3);
                hashMap2.put("field_name", string4);
                hashMap2.put("field_type", valueOf);
                hashMap2.put("field_value", string5);
                hashMap2.put("field_select_value", obj);
                hashMap2.put("field_is_must", string6);
                hashMap2.put("field_rule", string7);
                hashMap2.put("field_check_error_msg", string8);
                arrayList.add(hashMap2);
            }
            hashMap.put("fields", arrayList);
            return hashMap;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: v */
    public static HashMap<String, Object> m14471v(byte[] bArr) {
        HashMap<String, Object> hashMap = new HashMap<>();
        try {
            JSONObject jSONObject = new JSONObject(new String(bArr, "UTF-8"));
            int i = jSONObject.getInt(AbstractC5718b.JSON_ERRORCODE);
            hashMap.put(AbstractC5718b.JSON_ERRORCODE, Integer.valueOf(i));
            if (i == 0) {
                HashMap hashMap2 = new HashMap();
                JSONObject jSONObject2 = (JSONObject) jSONObject.get("data");
                if (jSONObject2.has("total_score")) {
                    hashMap2.put("totalScore", Integer.valueOf(jSONObject2.getInt("total_score")));
                }
                if (jSONObject2.has("banner_list")) {
                    ArrayList arrayList = new ArrayList();
                    JSONArray jSONArray = jSONObject2.getJSONArray("banner_list");
                    for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                        JSONObject jSONObject3 = (JSONObject) jSONArray.get(i2);
                        HashMap hashMap3 = new HashMap();
                        hashMap3.put("title", jSONObject3.getString("title"));
                        hashMap3.put("iconUrl", jSONObject3.getString("icon_url"));
                        hashMap3.put("sourceType", Integer.valueOf(jSONObject3.getInt("source_type")));
                        if (jSONObject3.has("product_type")) {
                            hashMap3.put("productType", jSONObject3.getString("product_type"));
                        }
                        if (jSONObject3.has("product_id")) {
                            hashMap3.put("productId", Integer.valueOf(jSONObject3.getInt("product_id")));
                        }
                        hashMap3.put("wapUrl", jSONObject3.getString("wap_url"));
                        arrayList.add(hashMap3);
                    }
                    hashMap2.put("bannerList", arrayList);
                }
                if (jSONObject2.has("function_list")) {
                    ArrayList arrayList2 = new ArrayList();
                    JSONArray jSONArray2 = jSONObject2.getJSONArray("function_list");
                    for (int i3 = 0; i3 < jSONArray2.length(); i3++) {
                        JSONObject jSONObject4 = (JSONObject) jSONArray2.get(i3);
                        HashMap hashMap4 = new HashMap();
                        hashMap4.put("title", jSONObject4.getString("title"));
                        hashMap4.put("iconUrl", jSONObject4.getString("icon_url"));
                        hashMap4.put("sourceType", Integer.valueOf(jSONObject4.getInt("source_type")));
                        if (jSONObject4.has("product_type")) {
                            hashMap4.put("productType", jSONObject4.getString("product_type"));
                        }
                        if (jSONObject4.has("product_id")) {
                            hashMap4.put("productId", Integer.valueOf(jSONObject4.getInt("product_id")));
                        }
                        hashMap4.put("wapUrl", jSONObject4.getString("wap_url"));
                        arrayList2.add(hashMap4);
                    }
                    hashMap2.put("functionList", arrayList2);
                }
                if (jSONObject2.has("score_wall_list")) {
                    ArrayList arrayList3 = new ArrayList();
                    JSONArray jSONArray3 = jSONObject2.getJSONArray("score_wall_list");
                    for (int i4 = 0; i4 < jSONArray3.length(); i4++) {
                        JSONObject jSONObject5 = (JSONObject) jSONArray3.get(i4);
                        HashMap hashMap5 = new HashMap();
                        String string = jSONObject5.getString("title");
                        String string2 = jSONObject5.getString("sub_title");
                        String string3 = jSONObject5.getString("icon_url");
                        String string4 = jSONObject5.getString("wap_url");
                        int i5 = jSONObject5.getInt("type");
                        hashMap5.put("title", string);
                        hashMap5.put("subTitle", string2);
                        hashMap5.put("iconUrl", string3);
                        hashMap5.put("type", Integer.valueOf(i5));
                        hashMap5.put("wap_url", string4);
                        arrayList3.add(hashMap5);
                    }
                    hashMap2.put("scoreList", arrayList3);
                }
                hashMap.put("data", hashMap2);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return hashMap;
    }

    /* renamed from: w */
    public static HashMap<String, Object> m14472w(byte[] bArr) {
        HashMap<String, Object> hashMap = new HashMap<>();
        try {
            JSONObject jSONObject = new JSONObject(new String(bArr, "UTF-8"));
            int i = jSONObject.getInt(AbstractC5718b.JSON_ERRORCODE);
            hashMap.put(AbstractC5718b.JSON_ERRORCODE, Integer.valueOf(i));
            if (jSONObject.has("resultMsg")) {
                hashMap.put("resultMsg", jSONObject.getString("resultMsg"));
            }
            if (i == 0) {
                HashMap hashMap2 = new HashMap();
                JSONObject jSONObject2 = (JSONObject) jSONObject.get("data");
                if (jSONObject2.has("findList")) {
                    ArrayList arrayList = new ArrayList();
                    JSONArray jSONArray = jSONObject2.getJSONArray("findList");
                    for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                        JSONObject jSONObject3 = (JSONObject) jSONArray.get(i2);
                        HashMap hashMap3 = new HashMap();
                        if (jSONObject3.has("imgUrl")) {
                            hashMap3.put("imgUrl", jSONObject3.getString("imgUrl"));
                        }
                        if (jSONObject3.has("subTitle")) {
                            hashMap3.put("subTitle", jSONObject3.getString("subTitle"));
                        }
                        if (jSONObject3.has("title")) {
                            hashMap3.put("title", jSONObject3.getString("title"));
                        }
                        if (jSONObject3.has("wapUrl")) {
                            hashMap3.put("wapUrl", jSONObject3.getString("wapUrl"));
                        }
                        if (jSONObject3.has("id")) {
                            hashMap3.put("id", Integer.valueOf(jSONObject3.getInt("id")));
                        }
                        if (jSONObject3.has("hitBeans")) {
                            hashMap3.put("hitBeans", Integer.valueOf(jSONObject3.getInt("hitBeans")));
                        }
                        if (jSONObject3.has("baseNo")) {
                            if (!jSONObject3.isNull("baseNo")) {
                                hashMap3.put("baseNo", Integer.valueOf(jSONObject3.getInt("baseNo")));
                            } else {
                                hashMap3.put("baseNo", 0);
                            }
                        }
                        if (jSONObject3.has("adType")) {
                            hashMap3.put("adType", Integer.valueOf(jSONObject3.getInt("adType")));
                        }
                        if (jSONObject3.has("second")) {
                            if (!jSONObject3.isNull("second")) {
                                hashMap3.put("second", Integer.valueOf(jSONObject3.getInt("second")));
                            } else {
                                hashMap3.put("second", 0);
                            }
                        }
                        arrayList.add(hashMap3);
                    }
                    hashMap2.put("findList", arrayList);
                }
                hashMap.put("data", hashMap2);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return hashMap;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:122:0x0391, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:123:0x0392, code lost:
        r5.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:124:0x0397, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x0398, code lost:
        r2.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:130:0x03ad, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:131:0x03ae, code lost:
        r2.printStackTrace();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:124:0x0397 A[ExcHandler: UnsupportedEncodingException (r2v2 'e' java.io.UnsupportedEncodingException A[CUSTOM_DECLARE]), Splitter:B:1:0x0005] */
    /* renamed from: x */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.HashMap<java.lang.String, java.lang.Object> m14473x(byte[] r29) {
        /*
        // Method dump skipped, instructions count: 1686
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gbcom.gwifi.util.ResourceParse.m14473x(byte[]):java.util.HashMap");
    }

    /* renamed from: b */
    public static HashMap<String, Object> m14447b(String str) {
        int i;
        String str2;
        HashMap<String, Object> hashMap = new HashMap<>();
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("type")) {
                i = jSONObject.getInt("type");
            } else {
                i = 0;
            }
            if (jSONObject.has("contactPhone")) {
                str2 = jSONObject.getString("contactPhone");
            } else {
                str2 = "";
            }
            String str3 = "";
            if (jSONObject.has("suggestPhone")) {
                str3 = jSONObject.getString("suggestPhone");
            }
            hashMap.put("type", Integer.valueOf(i));
            hashMap.put("contactPhone", str2);
            hashMap.put("suggestPhone", str3);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return hashMap;
    }

    /* renamed from: c */
    public static HashMap<String, Object> m14449c(String str) {
        HashMap<String, Object> hashMap = new HashMap<>();
        try {
            JSONObject jSONObject = new JSONObject(str);
            int i = jSONObject.getInt(AbstractC5718b.JSON_ERRORCODE);
            hashMap.put(AbstractC5718b.JSON_ERRORCODE, Integer.valueOf(i));
            hashMap.put("resultMsg", jSONObject.getString("resultMsg"));
            if (i == 0) {
                ArrayList arrayList = new ArrayList();
                JSONArray jSONArray = jSONObject.getJSONArray("data");
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    HashMap hashMap2 = new HashMap();
                    JSONObject jSONObject2 = (JSONObject) jSONArray.get(i2);
                    if (jSONObject2.has("id")) {
                        hashMap2.put("id", Integer.valueOf(jSONObject2.getInt("id")));
                    }
                    if (jSONObject2.has("name")) {
                        hashMap2.put("name", jSONObject2.getString("name"));
                    }
                    if (jSONObject2.has("firstLetter")) {
                        hashMap2.put("firstLetter", jSONObject2.getString("firstLetter"));
                    }
                    arrayList.add(hashMap2);
                }
                hashMap.put("univerLists", arrayList);
                return hashMap;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /* renamed from: d */
    public static HashMap<String, Object> m14451d(String str) {
        HashMap<String, Object> hashMap = new HashMap<>();
        try {
            JSONObject jSONObject = new JSONObject(str);
            int i = jSONObject.getInt(AbstractC5718b.JSON_ERRORCODE);
            hashMap.put(AbstractC5718b.JSON_ERRORCODE, Integer.valueOf(i));
            hashMap.put("resultMsg", jSONObject.getString("resultMsg"));
            if (i == 0) {
                JSONObject jSONObject2 = (JSONObject) jSONObject.get("data");
                if (jSONObject2.has(Constant.f13323i)) {
                    hashMap.put(Constant.f13323i, jSONObject2.getString(Constant.f13323i));
                }
            }
            return hashMap;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Deprecated
    /* renamed from: e */
    public static HashMap<String, Object> m14453e(String str) {
        HashMap<String, Object> hashMap = new HashMap<>();
        try {
            JSONObject jSONObject = new JSONObject(str);
            int i = jSONObject.getInt(AbstractC5718b.JSON_ERRORCODE);
            hashMap.put(AbstractC5718b.JSON_ERRORCODE, Integer.valueOf(i));
            if (i == 0) {
                JSONObject jSONObject2 = (JSONObject) jSONObject.get("data");
                if (jSONObject2.has("hotspot_group_id")) {
                    hashMap.put("groupId", Integer.valueOf(jSONObject2.getInt("hotspot_group_id")));
                }
                if (jSONObject2.has("hotspot_group_name")) {
                    hashMap.put("groupName", jSONObject2.getString("hotspot_group_name"));
                }
                if (jSONObject2.has("hotspot_group_type")) {
                    hashMap.put("groupType", Integer.valueOf(jSONObject2.getInt("hotspot_group_type")));
                }
            }
            return hashMap;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
