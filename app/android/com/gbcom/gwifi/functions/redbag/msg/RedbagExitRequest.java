package com.gbcom.gwifi.functions.redbag.msg;

@UserMsgAndExecAnnotation(msgType = MsgType.REDBAG_EXIT)
public class RedbagExitRequest extends RequestMsgBase {
    private int accountId;
    private int currRedId;
    private int redId;

    public RedbagExitRequest() {
        super(MsgType.REDBAG_EXIT);
    }

    public int getAccountId() {
        return this.accountId;
    }

    public void setAccountId(int i) {
        this.accountId = i;
    }

    public int getCurrRedId() {
        return this.currRedId;
    }

    public void setCurrRedId(int i) {
        this.currRedId = i;
    }

    public int getRedId() {
        return this.redId;
    }

    public void setRedId(int i) {
        this.redId = i;
    }
}
