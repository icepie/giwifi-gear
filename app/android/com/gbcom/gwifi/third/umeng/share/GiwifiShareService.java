package com.gbcom.gwifi.third.umeng.share;

import android.content.Context;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.util.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.common.SocializeConstants;

public class GiwifiShareService {
    private static GiwifiShareService instance;

    public static GiwifiShareService getInstance(Context context) {
        if (instance == null) {
            instance = new GiwifiShareService(context);
        }
        return instance;
    }

    public GiwifiShareService(Context context) {
        SocializeConstants.APPKEY = Config.m14094a().mo27802e();
        PlatformConfig.setWeixin(context.getResources().getString(C2425R.C2429string.weixinAppId), context.getResources().getString(C2425R.C2429string.weixinAppKey));
        PlatformConfig.setSinaWeibo(context.getResources().getString(C2425R.C2429string.sinaWeiboAppId), context.getResources().getString(C2425R.C2429string.sinaWeiboAppKey), "http://sns.whalecloud.com");
        PlatformConfig.setQQZone(context.getResources().getString(C2425R.C2429string.qqZoneAppId), context.getResources().getString(C2425R.C2429string.qqZoneAppKey));
        UMShareAPI.get(context);
    }
}
