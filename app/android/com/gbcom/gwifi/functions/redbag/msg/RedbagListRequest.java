package com.gbcom.gwifi.functions.redbag.msg;

@UserMsgAndExecAnnotation(msgType = MsgType.REDBAG_LIST)
public class RedbagListRequest extends RequestMsgBase {
    private int accountId;

    public RedbagListRequest() {
        super(MsgType.REDBAG_LIST);
    }

    public int getAccountId() {
        return this.accountId;
    }

    public void setAccountId(int i) {
        this.accountId = i;
    }
}
