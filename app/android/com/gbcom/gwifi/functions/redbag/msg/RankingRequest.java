package com.gbcom.gwifi.functions.redbag.msg;

@UserMsgAndExecAnnotation(msgType = MsgType.EVERY_MONTH_RANKING)
public class RankingRequest extends RequestMsgBase {
    private int accountId;

    public RankingRequest() {
        super(MsgType.EVERY_MONTH_RANKING);
    }

    public int getAccountId() {
        return this.accountId;
    }

    public void setAccountId(int i) {
        this.accountId = i;
    }
}
