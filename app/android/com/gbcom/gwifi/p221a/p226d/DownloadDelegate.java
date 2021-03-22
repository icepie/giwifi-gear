package com.gbcom.gwifi.p221a.p226d;

import android.util.Log;
import java.p456io.IOException;
import org.apache.xpath.compiler.PsuedoNames;
import p041c.Call;
import p041c.Callback;
import p041c.Interceptor;
import p041c.OkHttpClient;
import p041c.Request;
import p041c.Response;

@Deprecated
/* renamed from: com.gbcom.gwifi.a.d.a */
public class DownloadDelegate {
    /* renamed from: a */
    public Request mo24087a(final String str, final String str2, final OkRequestHandler eVar, Object obj) {
        final Request c = new Request.C1190a().mo16101a(str).mo16100a(obj).mo16110c();
        m10217a().mo16464x().add(new Interceptor() {
            /* class com.gbcom.gwifi.p221a.p226d.DownloadDelegate.C24411 */

            @Override // p041c.Interceptor
            public Response intercept(Interceptor.AbstractC1264a aVar) throws IOException {
                Log.i("TAG", "下载进度的开始。。。。。。");
                Response a = aVar.mo15972a(aVar.mo15971a());
                return a.mo16128i().mo16141a(new ProgressResponseBody(c, a.mo16127h(), eVar)).mo16147a();
            }
        });
        m10217a().mo16200a(c).mo16072a(new Callback() {
            /* class com.gbcom.gwifi.p221a.p226d.DownloadDelegate.C24422 */

            /* JADX WARNING: Removed duplicated region for block: B:17:0x0049 A[SYNTHETIC, Splitter:B:17:0x0049] */
            /* JADX WARNING: Removed duplicated region for block: B:20:0x004e A[SYNTHETIC, Splitter:B:20:0x004e] */
            /* JADX WARNING: Removed duplicated region for block: B:33:0x0071 A[SYNTHETIC, Splitter:B:33:0x0071] */
            /* JADX WARNING: Removed duplicated region for block: B:36:0x0076 A[SYNTHETIC, Splitter:B:36:0x0076] */
            /* JADX WARNING: Removed duplicated region for block: B:55:? A[RETURN, SYNTHETIC] */
            @Override // p041c.Callback
            /* renamed from: a */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void mo16201a(p041c.Call r8, p041c.Response r9) throws java.p456io.IOException {
                /*
                // Method dump skipped, instructions count: 146
                */
                throw new UnsupportedOperationException("Method not decompiled: com.gbcom.gwifi.p221a.p226d.DownloadDelegate.C24422.mo16201a(c.e, c.ad):void");
            }

            @Override // p041c.Callback
            /* renamed from: a */
            public void mo16202a(Call eVar, IOException iOException) {
                OkHttpService.m10256a(c, iOException, eVar);
            }
        });
        return c;
    }

    /* renamed from: a */
    public void mo24088a(String str, String str2, OkRequestHandler eVar) {
        mo24087a(str, str2, eVar, null);
    }

    /* renamed from: a */
    private OkHttpClient m10217a() {
        return OkHttpService.m10252a().mo24104c();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: a */
    private String m10219a(String str) {
        int lastIndexOf = str.lastIndexOf(PsuedoNames.PSEUDONAME_ROOT);
        Log.i("TAG", "FileName :" + (lastIndexOf < 0 ? str : str.substring(lastIndexOf + 1, str.length())));
        return lastIndexOf < 0 ? str : str.substring(lastIndexOf + 1, str.length());
    }
}
