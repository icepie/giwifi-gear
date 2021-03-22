package com.gbcom.gwifi.wxapi;

import android.content.Intent;
import com.tencent.p369mm.opensdk.modelbase.BaseResp;
import com.tencent.p369mm.opensdk.modelbiz.WXLaunchMiniProgram;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.weixin.view.WXCallbackActivity;

public class WXEntryActivity extends WXCallbackActivity {
    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        UMShareAPI.get(this).onActivityResult(i, i2, intent);
    }

    public void onResp(BaseResp baseResp) {
        if (baseResp.getType() == 19) {
            String str = ((WXLaunchMiniProgram.Resp) baseResp).extMsg;
        }
    }
}
