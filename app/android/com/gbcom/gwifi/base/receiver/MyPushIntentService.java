package com.gbcom.gwifi.base.receiver;

import android.content.Context;
import android.content.Intent;
import com.gbcom.gwifi.util.Log;
import com.umeng.message.UmengBaseIntentService;
import com.umeng.message.entity.UMessage;
import org.android.agoo.common.AgooConstants;
import org.json.JSONObject;

public class MyPushIntentService extends UmengBaseIntentService {

    /* renamed from: a */
    private static final String f8942a = MyPushIntentService.class.getName();

    /* access modifiers changed from: protected */
    @Override // com.umeng.message.UmengBaseIntentService, org.android.agoo.control.BaseIntentService, com.taobao.agoo.TaobaoBaseIntentService
    public void onMessage(Context context, Intent intent) {
        super.onMessage(context, intent);
        try {
            String stringExtra = intent.getStringExtra(AgooConstants.MESSAGE_BODY);
            UMessage uMessage = new UMessage(new JSONObject(stringExtra));
            Log.m14398b(f8942a, "message=" + stringExtra);
            Log.m14398b(f8942a, "custom=" + uMessage.custom);
            Log.m14398b(f8942a, "title=" + uMessage.title);
            Log.m14398b(f8942a, "text=" + uMessage.text);
        } catch (Exception e) {
            Log.m14403e(f8942a, e.getMessage());
        }
    }
}
