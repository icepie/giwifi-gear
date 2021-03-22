package com.gbcom.gwifi.functions.redbag.msg;

@UserMsgAndExecAnnotation(msgType = 5)
public class HeartBeatMsg extends AbstractMsg {
    protected short command = 5;

    @Override // com.gbcom.gwifi.functions.redbag.msg.AbstractMsg
    public short getType() {
        return this.command;
    }
}
