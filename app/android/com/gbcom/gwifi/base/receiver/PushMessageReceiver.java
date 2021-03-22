package com.gbcom.gwifi.base.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.gbcom.gwifi.p221a.RequestHandler;
import com.gbcom.gwifi.p221a.p223b.HttpRequest;
import com.gbcom.gwifi.p221a.p223b.HttpResponse;

public class PushMessageReceiver extends BroadcastReceiver implements RequestHandler<HttpRequest, HttpResponse> {

    /* renamed from: a */
    public static final String f8943a = PushMessageReceiver.class.getSimpleName();

    /* renamed from: b */
    private HttpRequest f8944b;

    /* renamed from: c */
    private Context f8945c;

    /* renamed from: d */
    private String f8946d;

    public void onReceive(Context context, Intent intent) {
        this.f8945c = context;
        Log.d(f8943a, ">>> Receive intent: \r\n" + ((Object) intent));
    }

    /* renamed from: a */
    public void mo24084b(HttpRequest cVar, HttpResponse dVar) {
    }

    /* renamed from: b */
    public void mo24083a(HttpRequest cVar, HttpResponse dVar) {
    }

    /* renamed from: a */
    public void mo24082a(HttpRequest cVar, long j, long j2) {
    }

    /* renamed from: a */
    public void mo24081a(HttpRequest cVar) {
    }
}
