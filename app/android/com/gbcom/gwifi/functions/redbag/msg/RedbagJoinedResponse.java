package com.gbcom.gwifi.functions.redbag.msg;

import com.gbcom.gwifi.functions.redbag.domain.RedbagJoined;
import java.p456io.Serializable;
import java.util.List;

@UserMsgAndExecAnnotation(msgType = -4005)
public class RedbagJoinedResponse extends AbstractMsg implements Serializable {
    protected RedbagJoinedResponseBody response;
    protected ResponseHeader responseHeader = new ResponseHeader();

    public class RedbagJoinedResponseBody implements Serializable {
        private List<RedbagJoined> joined;

        public RedbagJoinedResponseBody() {
        }

        public List<RedbagJoined> getJoined() {
            return this.joined;
        }

        public void setJoined(List<RedbagJoined> list) {
            this.joined = list;
        }
    }

    public RedbagJoinedResponse() {
        this.responseHeader.setCommand(-4005);
        this.response = new RedbagJoinedResponseBody();
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

    public RedbagJoinedResponseBody getResponse() {
        return this.response;
    }

    public void setResponse(RedbagJoinedResponseBody redbagJoinedResponseBody) {
        this.response = redbagJoinedResponseBody;
    }
}
