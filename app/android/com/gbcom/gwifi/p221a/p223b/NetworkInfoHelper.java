package com.gbcom.gwifi.p221a.p223b;

import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.util.Log;
import com.tencent.map.geoloclite.tsa.TencentLiteLocation;
import com.umeng.message.proguard.MessageStore;
import org.apache.http.HttpHost;
import p419io.netty.handler.codec.rtsp.RtspHeaders;

/* renamed from: com.gbcom.gwifi.a.b.g */
public class NetworkInfoHelper {

    /* renamed from: a */
    private ConnectivityManager f8515a;

    /* renamed from: b */
    private Context f8516b;

    public NetworkInfoHelper(Context context) {
        this.f8516b = context;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public ConnectivityManager mo24074a() {
        if (this.f8515a == null) {
        }
        try {
            this.f8515a = (ConnectivityManager) this.f8516b.getSystemService("connectivity");
        } catch (Exception e) {
            Log.w(TencentLiteLocation.NETWORK_PROVIDER, "cannot get connectivity manager, maybe the permission is missing in AndroidManifest.xml?", e);
        }
        return this.f8515a;
    }

    /* renamed from: b */
    public String mo24075b() {
        NetworkInfo activeNetworkInfo;
        ConnectivityManager a = mo24074a();
        if (a == null || (activeNetworkInfo = a.getActiveNetworkInfo()) == null) {
            return "unknown";
        }
        switch (activeNetworkInfo.getType()) {
            case 0:
                return "mobile(" + activeNetworkInfo.getSubtypeName() + "," + activeNetworkInfo.getExtraInfo() + MessageStore.f23536t;
            case 1:
                return "wifi";
            default:
                return activeNetworkInfo.getTypeName();
        }
    }

    /* renamed from: c */
    public HttpHost mo24076c() {
        String str;
        HttpHost httpHost;
        ConnectivityManager a = mo24074a();
        if (a == null) {
            return null;
        }
        NetworkInfo activeNetworkInfo = a.getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            str = null;
        } else if (activeNetworkInfo.getType() == 1) {
            str = null;
        } else if (activeNetworkInfo.getType() == 0) {
            str = activeNetworkInfo.getExtraInfo();
        } else {
            str = null;
        }
        if (str != null) {
            String lowerCase = str.toLowerCase();
            if (lowerCase.contains("cmnet")) {
                httpHost = null;
            } else if (lowerCase.contains("cmwap")) {
                httpHost = new HttpHost("10.0.0.172");
            } else if (lowerCase.contains("3gnet")) {
                httpHost = null;
            } else if (lowerCase.contains("3gwap")) {
                httpHost = new HttpHost("10.0.0.172");
            } else if (lowerCase.contains("uninet")) {
                httpHost = null;
            } else if (lowerCase.contains("uniwap")) {
                httpHost = new HttpHost("10.0.0.172");
            } else if (lowerCase.contains("ctnet")) {
                httpHost = null;
            } else {
                httpHost = lowerCase.contains("ctwap") ? new HttpHost("10.0.0.200") : null;
            }
            if (lowerCase.contains("#777")) {
                Cursor query = this.f8516b.getContentResolver().query(Uri.parse("content://telephony/carriers/preferapn"), new String[]{"proxy", RtspHeaders.Values.PORT}, null, null, null);
                if (query.moveToFirst() && query.getString(0).length() > 3) {
                    int parseInt = Integer.parseInt(query.getString(1));
                    if (parseInt <= 0) {
                        parseInt = 80;
                    }
                    httpHost = new HttpHost(query.getString(0), parseInt);
                }
                if (query != null) {
                    query.close();
                }
            }
        } else {
            httpHost = null;
        }
        return httpHost;
    }
}
