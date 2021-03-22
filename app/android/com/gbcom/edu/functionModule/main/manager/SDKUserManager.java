package com.gbcom.edu.functionModule.main.manager;

import android.app.ProgressDialog;
import android.content.Context;
import java.util.Observer;

public class SDKUserManager {

    /* renamed from: a */
    private Observer f8340a = null;

    /* renamed from: b */
    private ProgressDialog f8341b;

    /* renamed from: c */
    private Context f8342c;

    /* renamed from: d */
    private boolean f8343d;

    public SDKUserManager(Context context) {
        this.f8342c = context;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: b */
    private void m10152b() {
    }

    /* renamed from: a */
    public boolean mo24032a(String str, String str2, String str3, int i, int i2, String str4, ISDKUser aVar) {
        new Thread() {
            /* class com.gbcom.edu.functionModule.main.manager.SDKUserManager.C24241 */

            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                super.run();
                SDKUserManager.this.m10152b();
            }
        }.start();
        return this.f8343d;
    }

    /* renamed from: a */
    public void mo24029a() {
    }

    /* renamed from: a */
    public void mo24030a(int i, String str, String str2, int i2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, String str18, String str19, String str20) {
    }

    /* renamed from: a */
    public void mo24031a(String str, String str2, int i, String str3, ISDKUser aVar) {
    }
}
