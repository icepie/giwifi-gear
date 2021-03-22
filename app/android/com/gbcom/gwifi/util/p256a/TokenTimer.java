package com.gbcom.gwifi.util.p256a;

import android.os.Handler;
import android.os.Message;
import com.gbcom.gwifi.p221a.p226d.OkRequestHandler;
import com.gbcom.gwifi.util.HttpUtil;
import com.gbcom.gwifi.util.Log;
import java.util.Timer;
import java.util.TimerTask;
import p041c.Request;

/* renamed from: com.gbcom.gwifi.util.a.a */
public class TokenTimer {

    /* renamed from: a */
    static OkRequestHandler<String> f13099a = new OkRequestHandler<String>() {
        /* class com.gbcom.gwifi.util.p256a.TokenTimer.C34491 */

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestStart(Request abVar) {
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestFailed(Request abVar, Exception exc) {
        }

        /* renamed from: a */
        public void onRequestFinish(Request abVar, String str) {
            TokenUtil.m14091a(abVar, str);
        }

        @Override // com.gbcom.gwifi.p221a.p226d.OkRequestHandler
        public void onRequestProgress(Request abVar, long j, long j2) {
        }
    };

    /* renamed from: b */
    static Handler f13100b = new Handler() {
        /* class com.gbcom.gwifi.util.p256a.TokenTimer.HandlerC34502 */

        public void handleMessage(Message message) {
            if (message.what == 1) {
                TokenTimer.m14087a();
            }
            super.handleMessage(message);
        }
    };

    /* renamed from: c */
    private static final String f13101c = "TokenTimer";

    /* renamed from: d */
    private static final Timer f13102d = new Timer();

    /* renamed from: e */
    private static boolean f13103e = false;

    /* renamed from: f */
    private static TimerTask f13104f = new TimerTask() {
        /* class com.gbcom.gwifi.util.p256a.TokenTimer.C34513 */

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            Message message = new Message();
            message.what = 1;
            TokenTimer.f13100b.sendMessage(message);
            Log.m14398b(TokenTimer.f13101c, "CheckRefreshLoginToken");
        }
    };

    /* renamed from: a */
    public static void m14087a() {
        if (!TokenUtil.m14093a()) {
            HttpUtil.m14334k(f13099a, "");
        }
    }

    /* renamed from: b */
    public static void m14088b() {
        if (!f13103e) {
            f13102d.schedule(f13104f, 10000, 120000);
            f13103e = true;
        }
    }

    /* renamed from: c */
    public static void m14089c() {
        if (f13103e) {
            f13102d.cancel();
            f13103e = false;
        }
    }
}
