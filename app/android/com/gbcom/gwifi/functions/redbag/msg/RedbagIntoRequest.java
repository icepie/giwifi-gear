package com.gbcom.gwifi.functions.redbag.msg;

@UserMsgAndExecAnnotation(msgType = MsgType.REDBAG_INTO)
public class RedbagIntoRequest extends RequestMsgBase {
    private int accountId;
    private int redId;

    public RedbagIntoRequest() {
        super(MsgType.REDBAG_INTO);
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
}
