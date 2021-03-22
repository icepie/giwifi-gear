package com.gbcom.gwifi.functions.redbag.msg;

@UserMsgAndExecAnnotation(msgType = -4003)
public class RedbagBettingResponse extends AbstractMsg {
    protected RedbagBettingResponseBody response;
    protected ResponseHeader responseHeader = new ResponseHeader();

    public class RedbagBettingResponseBody {
        private int balance;
        private String roundNo;
        private int totalBeans;

        public RedbagBettingResponseBody() {
        }

        public String getRoundNo() {
            return this.roundNo;
        }

        public void setRoundNo(String str) {
            this.roundNo = str;
        }

        public int getTotalBeans() {
            return this.totalBeans;
        }

        public void setTotalBeans(int i) {
            this.totalBeans = i;
        }

        public int getBalance() {
            return this.balance;
        }

        public void setBalance(int i) {
            this.balance = i;
        }
    }

    public RedbagBettingResponse() {
        this.responseHeader.setCommand(-4003);
        this.response = new RedbagBettingResponseBody();
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

    public RedbagBettingResponseBody getResponse() {
        return this.response;
    }

    public void setResponse(RedbagBettingResponseBody redbagBettingResponseBody) {
        this.response = redbagBettingResponseBody;
    }
}
