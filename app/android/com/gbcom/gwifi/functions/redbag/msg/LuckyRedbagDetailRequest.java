package com.gbcom.gwifi.functions.redbag.msg;

@UserMsgAndExecAnnotation(msgType = MsgType.LUCKY_REDBAG_DETAIL)
public class LuckyRedbagDetailRequest extends RequestMsgBase {
    private int accountId;
    private int luckyRedbagId;

    public LuckyRedbagDetailRequest() {
        super(MsgType.LUCKY_REDBAG_DETAIL);
        this.activityId = 12;
    }

    public int getAccountId() {
        return this.accountId;
    }

    public void setAccountId(int i) {
        this.accountId = i;
    }

    public int getLuckyRedbagId() {
        return this.luckyRedbagId;
    }

    public void setLuckyRedbagId(int i) {
        this.luckyRedbagId = i;
    }
}
