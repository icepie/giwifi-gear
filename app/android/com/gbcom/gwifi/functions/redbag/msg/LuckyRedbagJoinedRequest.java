package com.gbcom.gwifi.functions.redbag.msg;

@UserMsgAndExecAnnotation(msgType = MsgType.LUCKY_REDBAG_JOINED)
public class LuckyRedbagJoinedRequest extends RequestMsgBase {
    private int accountId;
    private int size;
    private int start;

    public LuckyRedbagJoinedRequest() {
        super(MsgType.LUCKY_REDBAG_JOINED);
        this.activityId = 12;
    }

    public int getAccountId() {
        return this.accountId;
    }

    public void setAccountId(int i) {
        this.accountId = i;
    }

    public int getStart() {
        return this.start;
    }

    public void setStart(int i) {
        this.start = i;
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int i) {
        this.size = i;
    }
}
