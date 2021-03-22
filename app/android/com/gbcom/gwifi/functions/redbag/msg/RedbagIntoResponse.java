package com.gbcom.gwifi.functions.redbag.msg;

import com.gbcom.gwifi.functions.redbag.domain.LastRound;
import com.gbcom.gwifi.functions.redbag.domain.ThisRound;

@UserMsgAndExecAnnotation(msgType = -4002)
public class RedbagIntoResponse extends AbstractMsg {
    protected RedbagIntoResponseBody response;
    protected ResponseHeader responseHeader = new ResponseHeader();

    public class RedbagIntoResponseBody {
        private LastRound lastRound;
        private int prizePoolBeans;
        private ThisRound thisRound;
        private int totalBeans;

        public RedbagIntoResponseBody() {
        }

        public int getTotalBeans() {
            return this.totalBeans;
        }

        public void setTotalBeans(int i) {
            this.totalBeans = i;
        }

        public int getPrizePoolBeans() {
            return this.prizePoolBeans;
        }

        public void setPrizePoolBeans(int i) {
            this.prizePoolBeans = i;
        }

        public ThisRound getThisRound() {
            return this.thisRound;
        }

        public void setThisRound(ThisRound thisRound2) {
            this.thisRound = thisRound2;
        }

        public LastRound getLastRound() {
            return this.lastRound;
        }

        public void setLastRound(LastRound lastRound2) {
            this.lastRound = lastRound2;
        }
    }

    public RedbagIntoResponse() {
        this.responseHeader.setCommand(-4002);
        this.response = new RedbagIntoResponseBody();
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

    public RedbagIntoResponseBody getResponse() {
        return this.response;
    }

    public void setResponse(RedbagIntoResponseBody redbagIntoResponseBody) {
        this.response = redbagIntoResponseBody;
    }
}
