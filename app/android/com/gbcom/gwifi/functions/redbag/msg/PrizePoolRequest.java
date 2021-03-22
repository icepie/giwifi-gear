package com.gbcom.gwifi.functions.redbag.msg;

@UserMsgAndExecAnnotation(msgType = MsgType.PRIZE_POOL)
public class PrizePoolRequest extends RequestMsgBase {
    private int accountId;

    public PrizePoolRequest() {
        super(MsgType.PRIZE_POOL);
    }

    public int getAccountId() {
        return this.accountId;
    }

    public void setAccountId(int i) {
        this.accountId = i;
    }
}
