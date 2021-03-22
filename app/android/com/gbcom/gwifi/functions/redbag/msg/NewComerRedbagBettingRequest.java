package com.gbcom.gwifi.functions.redbag.msg;

@UserMsgAndExecAnnotation(msgType = MsgType.NEWCOMER_REDBAG_BETTING)
public class NewComerRedbagBettingRequest extends RequestMsgBase {
    private int accountId;

    public NewComerRedbagBettingRequest() {
        super(MsgType.NEWCOMER_REDBAG_BETTING);
        this.activityId = 11;
    }

    public int getAccountId() {
        return this.accountId;
    }

    public void setAccountId(int i) {
        this.accountId = i;
    }
}
