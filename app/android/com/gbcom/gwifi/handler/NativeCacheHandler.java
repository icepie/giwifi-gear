package com.gbcom.gwifi.handler;

import android.util.Log;

public class NativeCacheHandler {
    private native boolean nativeMakeError();

    private native int nativeRegisterHandler();

    static {
        System.loadLibrary("wlibcatchs");
    }

    /* renamed from: a */
    public void mo27638a() {
        nativeRegisterHandler();
    }

    /* renamed from: b */
    public void mo27639b() {
        Log.d("wtest", "捕获到本地异常,执行到这里");
    }

    @Deprecated
    /* renamed from: c */
    public void mo27640c() {
        nativeMakeError();
    }
}
