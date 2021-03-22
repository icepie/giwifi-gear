package com.gbcom.gwifi.functions.redbag.msg;

import com.gbcom.gwifi.functions.redbag.domain.Redbag;
import java.util.ArrayList;

@UserMsgAndExecAnnotation(msgType = -4001)
public class RedbagListResponse extends AbstractMsg {
    protected RedbagListResponseBody response;
    protected ResponseHeader responseHeader = new ResponseHeader();

    public class RedbagListResponseBody {
        private int heartbeatPeriod;
        private int heartbeatSwitch;
        private ArrayList<Redbag> redList;
        private int refreshTime;
        private int totalCount;

        public int getTotalCount() {
            return this.totalCount;
        }

        public void setTotalCount(int i) {
            this.totalCount = i;
        }

        public RedbagListResponseBody() {
        }

        public int getRefreshTime() {
            return this.refreshTime;
        }

        public void setRefreshTime(int i) {
            this.refreshTime = i;
        }

        public int getHeartbeatSwitch() {
            return this.heartbeatSwitch;
        }

        public void setHeartbeatSwitch(int i) {
            this.heartbeatSwitch = i;
        }

        public int getHeartbeatPeriod() {
            return this.heartbeatPeriod;
        }

        public void setHeartbeatPeriod(int i) {
            this.heartbeatPeriod = i;
        }

        public ArrayList<Redbag> getRedList() {
            return this.redList;
        }

        public void setRedList(ArrayList<Redbag> arrayList) {
            this.redList = arrayList;
        }
    }

    public RedbagListResponse() {
        this.responseHeader.setCommand(-4001);
        this.response = new RedbagListResponseBody();
    }

    @Override // com.gbcom.gwifi.functions.redbag.msg.AbstractMsg
    public short getType() {
        return this.responseHeader.getCommand();
    }

    public ResponseHeader getResponseHeader() {
        return this.responseHeader;
    }

    public void setResponseHeader(ResponseHeader responseHeader2) {
        this.responseHeader = responseHeader2;
    }

    public RedbagListResponseBody getResponse() {
        return this.response;
    }

    public void setResponse(RedbagListResponseBody redbagListResponseBody) {
        this.response = redbagListResponseBody;
    }
}
