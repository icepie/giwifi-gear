package com.gbcom.gwifi.functions.redbag.msg;

@UserMsgAndExecAnnotation(msgType = MsgType.REDBAG_BETTING)
public class RedbagBettingRequest extends RequestMsgBase {
    private int accountId;
    private int redId;
    private String roundNo;

    public RedbagBettingRequest() {
        super(MsgType.REDBAG_BETTING);
    }

    public int getAccountId() {
        return this.accountId;
    }

    public void setAccountId(int i) {
        this.accountId = i;
    }

    public int getRedId() {
        return this.redId;
    }

    public void setRedId(int i) {
        this.redId = i;
    }

    public String getRoundNo() {
        return this.roundNo;
    }

    public void setRoundNo(String str) {
        this.roundNo = str;
    }
}
