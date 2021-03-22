package com.gbcom.gwifi.functions.redbag.msg;

@UserMsgAndExecAnnotation(msgType = MsgType.LUCKY_REDBAG_LIST)
public class LuckyRedbagIntoRequest extends RequestMsgBase {
    private int accountId;

    public LuckyRedbagIntoRequest() {
        super(MsgType.LUCKY_REDBAG_LIST);
        this.activityId = 1;
    }

    public int getAccountId() {
        return this.accountId;
    }

    public void setAccountId(int i) {
        this.accountId = i;
    }
}
