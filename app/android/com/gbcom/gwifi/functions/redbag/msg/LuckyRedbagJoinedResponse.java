package com.gbcom.gwifi.functions.redbag.msg;

import com.gbcom.gwifi.functions.redbag.domain.LuckyRedbagJoined;
import java.p456io.Serializable;
import java.util.List;

@UserMsgAndExecAnnotation(msgType = -4454)
public class LuckyRedbagJoinedResponse extends AbstractMsg implements Serializable {
    protected LuckyRedbagJoinedResponseBody response;
    protected ResponseHeader responseHeader = new ResponseHeader();

    public class LuckyRedbagJoinedResponseBody implements Serializable {
        private List<LuckyRedbagJoined> joined;

        public LuckyRedbagJoinedResponseBody() {
        }

        public List<LuckyRedbagJoined> getJoined() {
            return this.joined;
        }

        public void setJoined(List<LuckyRedbagJoined> list) {
            this.joined = list;
        }
    }

    public LuckyRedbagJoinedResponse() {
        this.responseHeader.setCommand(-4454);
        this.response = new LuckyRedbagJoinedResponseBody();
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

    public LuckyRedbagJoinedResponseBody getResponse() {
        return this.response;
    }

    public void setResponse(LuckyRedbagJoinedResponseBody luckyRedbagJoinedResponseBody) {
        this.response = luckyRedbagJoinedResponseBody;
    }
}
