package com.gbcom.gwifi.base.receiver;

import android.content.Intent;
import android.os.Bundle;
import com.umeng.message.UmengNotifyClickActivity;
import org.android.agoo.common.AgooConstants;

public class MipushTestActivity extends UmengNotifyClickActivity {

    /* renamed from: a */
    private static String f8941a = MipushTestActivity.class.getName();

    /* access modifiers changed from: protected */
    @Override // com.taobao.agoo.BaseNotifyClickActivity, com.umeng.message.UmengNotifyClickActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // com.taobao.agoo.BaseNotifyClickActivity, com.umeng.message.UmengNotifyClickActivity
    public void onMessage(Intent intent) {
        super.onMessage(intent);
        intent.getStringExtra(AgooConstants.MESSAGE_BODY);
    }
}
