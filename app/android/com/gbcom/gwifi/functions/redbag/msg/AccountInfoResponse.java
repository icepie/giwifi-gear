package com.gbcom.gwifi.functions.redbag.msg;

import com.gbcom.gwifi.functions.redbag.domain.AccountInfo;

@UserMsgAndExecAnnotation(msgType = -1001)
public class AccountInfoResponse extends AbstractMsg {
    protected AccountInfoResponseBody response;
    protected ResponseHeader responseHeader = new ResponseHeader();

    public class AccountInfoResponseBody {
        private AccountInfo info;

        public AccountInfoResponseBody() {
        }

        public AccountInfo getInfo() {
            return this.info;
        }

        public void setInfo(AccountInfo accountInfo) {
            this.info = accountInfo;
        }
    }

    public AccountInfoResponse() {
        this.responseHeader.setCommand(-1001);
        this.response = new AccountInfoResponseBody();
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

    public AccountInfoResponseBody getResponse() {
        return this.response;
    }

    public void setResponse(AccountInfoResponseBody accountInfoResponseBody) {
        this.response = accountInfoResponseBody;
    }
}
