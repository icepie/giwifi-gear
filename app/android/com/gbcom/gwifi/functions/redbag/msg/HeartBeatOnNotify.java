package com.gbcom.gwifi.functions.redbag.msg;

@UserMsgAndExecAnnotation(msgType = MsgType.COMMU_REPORT_HEARTBEAT_ON)
public class HeartBeatOnNotify extends AbstractMsg {
    private HeartBeatOnNotifyBody notify;
    private NotifyHeader notifyHeader = new NotifyHeader();

    public class HeartBeatOnNotifyBody {
        private int heartBeatPeriod;

        public HeartBeatOnNotifyBody() {
        }

        public int getHeartBeatPeriod() {
            return this.heartBeatPeriod;
        }

        public void setHeartBeatPeriod(int i) {
            this.heartBeatPeriod = i;
        }
    }

    public HeartBeatOnNotify() {
        this.notifyHeader.setCommand(MsgType.COMMU_REPORT_HEARTBEAT_ON);
        this.notify = new HeartBeatOnNotifyBody();
    }

    @Override // com.gbcom.gwifi.functions.redbag.msg.AbstractMsg
    public short getType() {
        return this.notifyHeader.getCommand();
    }

    public NotifyHeader getNotifyHeader() {
        return this.notifyHeader;
    }

    public void setNotifyHeader(NotifyHeader notifyHeader2) {
        this.notifyHeader = notifyHeader2;
    }

    public HeartBeatOnNotifyBody getNotify() {
        return this.notify;
    }

    public void setNotify(HeartBeatOnNotifyBody heartBeatOnNotifyBody) {
        this.notify = heartBeatOnNotifyBody;
    }
}
