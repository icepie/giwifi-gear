package com.gbcom.gwifi.functions.redbag.msg;

@UserMsgAndExecAnnotation(msgType = -4401)
public class NewComerRedbagBettingResponse extends AbstractMsg {
    protected NewComerRedbagBettingResponseBody response;
    protected ResponseHeader responseHeader = new ResponseHeader();

    public class NewComerRedbagBettingResponseBody {
        private int balance;
        private int hitBeans;

        public NewComerRedbagBettingResponseBody() {
        }

        public int getHitBeans() {
            return this.hitBeans;
        }

        public void setHitBeans(int i) {
            this.hitBeans = i;
        }

        public int getBalance() {
            return this.balance;
        }

        public void setBalance(int i) {
            this.balance = i;
        }
    }

    public NewComerRedbagBettingResponse() {
        this.responseHeader.setCommand(-4401);
        this.response = new NewComerRedbagBettingResponseBody();
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

    public NewComerRedbagBettingResponseBody getResponse() {
        return this.response;
    }

    public void setResponse(NewComerRedbagBettingResponseBody newComerRedbagBettingResponseBody) {
        this.response = newComerRedbagBettingResponseBody;
    }
}
