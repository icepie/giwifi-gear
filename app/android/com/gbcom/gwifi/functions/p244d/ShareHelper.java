package com.gbcom.gwifi.functions.p244d;

import android.app.Activity;
import android.widget.Toast;
import com.gbcom.gwifi.C2425R;
import com.gbcom.gwifi.util.C3467s;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

/* renamed from: com.gbcom.gwifi.functions.d.a */
public class ShareHelper {

    /* renamed from: a */
    private static final String f9184a = "com.umeng.share";

    /* renamed from: b */
    private static String f9185b = "http://down.gwifi.com.cn";

    /* renamed from: c */
    private static String f9186c = "有WiFi的宿舍才是家园！";

    /* renamed from: d */
    private static String f9187d = "";

    /* renamed from: e */
    private static Activity f9188e;

    /* renamed from: f */
    private static ShareHelper f9189f;

    /* renamed from: g */
    private UMShareListener f9190g = new UMShareListener() {
        /* class com.gbcom.gwifi.functions.p244d.ShareHelper.C25981 */

        @Override // com.umeng.socialize.UMShareListener
        public void onStart(SHARE_MEDIA share_media) {
        }

        @Override // com.umeng.socialize.UMShareListener
        public void onResult(SHARE_MEDIA share_media) {
            Toast.makeText(ShareHelper.f9188e, "分享成功", 1).show();
        }

        @Override // com.umeng.socialize.UMShareListener
        public void onError(SHARE_MEDIA share_media, Throwable th) {
            Toast.makeText(ShareHelper.f9188e, "分享失败" + th.getMessage(), 1).show();
        }

        @Override // com.umeng.socialize.UMShareListener
        public void onCancel(SHARE_MEDIA share_media) {
            Toast.makeText(ShareHelper.f9188e, "取消分享", 1).show();
        }
    };

    /* renamed from: a */
    public static ShareHelper m10859a(Activity activity) {
        f9188e = activity;
        if (f9189f == null) {
            f9189f = new ShareHelper();
        }
        return f9189f;
    }

    /* renamed from: a */
    public void mo24539a() {
        new ShareAction(f9188e).withText(f9186c).setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE).setCallback(this.f9190g).open();
    }

    /* renamed from: a */
    private void m10860a(String str, String str2, String str3, String str4, SHARE_MEDIA share_media) {
        UMImage uMImage;
        f9185b = str4;
        f9186c = str2;
        f9187d = str3;
        if (C3467s.m14513e(f9187d)) {
            uMImage = new UMImage(f9188e, (int) C2425R.C2426drawable.icon);
        } else {
            uMImage = new UMImage(f9188e, f9187d);
        }
        if (C3467s.m14513e(f9185b)) {
            new ShareAction(f9188e).setPlatform(share_media).withText(f9186c).withMedia(uMImage).setCallback(this.f9190g).share();
            return;
        }
        UMWeb uMWeb = new UMWeb(f9185b);
        uMWeb.setTitle(str);
        uMWeb.setThumb(uMImage);
        uMWeb.setDescription(str2);
        new ShareAction(f9188e).setPlatform(share_media).withText(f9186c).withMedia(uMWeb).setCallback(this.f9190g).share();
    }

    /* renamed from: a */
    public void mo24540a(String str, String str2, String str3, String str4, int i) {
        SHARE_MEDIA share_media = null;
        switch (i) {
            case 1:
                share_media = SHARE_MEDIA.WEIXIN;
                break;
            case 2:
                share_media = SHARE_MEDIA.WEIXIN_CIRCLE;
                break;
            case 3:
                share_media = SHARE_MEDIA.QZONE;
                break;
            case 4:
                share_media = SHARE_MEDIA.SINA;
                break;
            case 5:
                share_media = SHARE_MEDIA.QQ;
                break;
        }
        if (share_media != null) {
            m10860a(str, str2, str3, str4, share_media);
        }
    }

    /* renamed from: b */
    public void mo24541b(String str, String str2, String str3, String str4, int i) {
        SHARE_MEDIA share_media = null;
        switch (i) {
            case C2425R.C2427id.wechat_layout /*{ENCODED_INT: 2131755959}*/:
                share_media = SHARE_MEDIA.WEIXIN;
                break;
            case C2425R.C2427id.wxcircle_layout /*{ENCODED_INT: 2131755960}*/:
                share_media = SHARE_MEDIA.WEIXIN_CIRCLE;
                break;
            case C2425R.C2427id.qzone_layout /*{ENCODED_INT: 2131755961}*/:
                share_media = SHARE_MEDIA.QZONE;
                break;
            case C2425R.C2427id.sina_layout /*{ENCODED_INT: 2131755962}*/:
                share_media = SHARE_MEDIA.SINA;
                break;
            case C2425R.C2427id.qq_layout /*{ENCODED_INT: 2131755963}*/:
                share_media = SHARE_MEDIA.QQ;
                break;
        }
        if (share_media != null) {
            m10860a(str, str2, str3, str4, share_media);
        }
    }
}
