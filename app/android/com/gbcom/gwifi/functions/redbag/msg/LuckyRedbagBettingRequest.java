package com.gbcom.gwifi.functions.redbag.msg;

@UserMsgAndExecAnnotation(msgType = MsgType.LUCKY_REDBAG_BETTING)
public class LuckyRedbagBettingRequest extends RequestMsgBase {
    private int accountId;
    private int luckyRedbagId;

    public LuckyRedbagBettingRequest() {
        super(MsgType.LUCKY_REDBAG_BETTING);
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
