package com.gbcom.gwifi.functions.redbag.msg;

@UserMsgAndExecAnnotation(msgType = MsgType.ACCOUNT_INFO)
public class AccountInfoRequest extends RequestMsgBase {
    private int accountId;

    public AccountInfoRequest() {
        super(MsgType.ACCOUNT_INFO);
    }

    public int getAccountId() {
        return this.accountId;
    }

    public void setAccountId(int i) {
        this.accountId = i;
    }
}
