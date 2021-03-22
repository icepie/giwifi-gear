package com.gbcom.gwifi.functions.redbag.msg;

@UserMsgAndExecAnnotation(msgType = MsgType.REDBAG_JOINED)
public class RedbagJoinedRequest extends RequestMsgBase {
    private int accountId;

    public RedbagJoinedRequest() {
        super(MsgType.REDBAG_JOINED);
    }

    public int getAccountId() {
        return this.accountId;
    }

    public void setAccountId(int i) {
        this.accountId = i;
    }
}
