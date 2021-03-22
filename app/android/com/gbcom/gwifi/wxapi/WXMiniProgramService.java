package com.gbcom.gwifi.wxapi;

import android.content.Context;
import android.text.TextUtils;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.base.app.GBApplication;
import com.gbcom.gwifi.third.umeng.analytics.UmengCount;
import com.tencent.p369mm.opensdk.modelbiz.WXLaunchMiniProgram;
import com.tencent.p369mm.opensdk.openapi.IWXAPI;
import com.tencent.p369mm.opensdk.openapi.WXAPIFactory;

/* renamed from: com.gbcom.gwifi.wxapi.a */
public class WXMiniProgramService {

    /* renamed from: a */
    private static WXMiniProgramService f13882a = null;

    /* renamed from: b */
    private Context f13883b;

    /* renamed from: c */
    private String f13884c;

    private WXMiniProgramService(Context context) {
        this.f13883b = context;
        this.f13884c = context.getResources().getString(C2425R.C2429string.weixinAppId);
    }

    /* renamed from: a */
    public static final WXMiniProgramService m14865a() {
        if (f13882a == null) {
            f13882a = new WXMiniProgramService(GBApplication.instance());
        }
        return f13882a;
    }

    /* renamed from: a */
    public void mo28458a(String str, String str2, int i) {
        if (!TextUtils.isEmpty(str)) {
            if (i == 0 || i == 1 || i == 2) {
                UmengCount.queryMiniProgramLaunchAction(GBApplication.instance(), str);
                IWXAPI createWXAPI = WXAPIFactory.createWXAPI(this.f13883b, this.f13884c);
                WXLaunchMiniProgram.Req req = new WXLaunchMiniProgram.Req();
                req.userName = str;
                req.path = str2;
                req.miniprogramType = i;
                createWXAPI.sendReq(req);
            }
        }
    }
}
