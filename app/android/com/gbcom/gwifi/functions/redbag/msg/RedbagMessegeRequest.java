package com.gbcom.gwifi.functions.redbag.msg;

@UserMsgAndExecAnnotation(msgType = MsgType.POLLING_NOTIFY)
public class RedbagMessegeRequest extends RequestMsgBase {
    public RedbagMessegeRequest() {
        super(MsgType.POLLING_NOTIFY);
    }
}
