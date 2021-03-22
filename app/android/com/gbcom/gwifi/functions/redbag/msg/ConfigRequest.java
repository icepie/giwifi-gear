package com.gbcom.gwifi.functions.redbag.msg;

@UserMsgAndExecAnnotation(msgType = MsgType.CONFIG)
public class ConfigRequest extends RequestMsgBase {
    private int accountId;

    public ConfigRequest() {
        super(MsgType.CONFIG);
    }

    public int getAccountId() {
        return this.accountId;
    }

    public void setAccountId(int i) {
        this.accountId = i;
    }
}
