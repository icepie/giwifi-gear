package com.gbcom.gwifi.functions.wifi.entity;

import com.gbcom.gwifi.base.p234c.CallBack;

public class CheckTask {
    private CallBack callBack;
    private CheckResult checkResult = new CheckResult();
    private boolean isMainThread;

    public CheckTask(CallBack aVar, boolean z) {
        this.callBack = aVar;
        this.isMainThread = z;
    }

    public CallBack getCallBack() {
        return this.callBack;
    }

    public boolean isMainThread() {
        return this.isMainThread;
    }

    public CheckResult getCheckResult() {
        return this.checkResult;
    }

    public void onResult() {
        if (this.callBack != null) {
            this.callBack.mo24437a(this.checkResult);
        }
    }
}
