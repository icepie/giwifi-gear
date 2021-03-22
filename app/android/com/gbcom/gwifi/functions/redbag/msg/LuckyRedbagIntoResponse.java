package com.gbcom.gwifi.functions.redbag.msg;

import com.gbcom.gwifi.functions.redbag.domain.LuckyRedbagSimple;
import java.util.ArrayList;

@UserMsgAndExecAnnotation(msgType = -4451)
public class LuckyRedbagIntoResponse extends AbstractMsg {
    protected LuckyRedbagIntoResponseBody response;
    protected ResponseHeader responseHeader = new ResponseHeader();

    public class LuckyRedbagIntoResponseBody {
        private ArrayList<LuckyRedbagSimple> luckyList;

        public LuckyRedbagIntoResponseBody() {
        }

        public ArrayList<LuckyRedbagSimple> getLuckyList() {
            return this.luckyList;
        }

        public void setLuckyList(ArrayList<LuckyRedbagSimple> arrayList) {
            this.luckyList = arrayList;
        }
    }

    public LuckyRedbagIntoResponse() {
        this.responseHeader.setCommand(-4451);
        this.response = new LuckyRedbagIntoResponseBody();
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

    public LuckyRedbagIntoResponseBody getResponse() {
        return this.response;
    }

    public void setResponse(LuckyRedbagIntoResponseBody luckyRedbagIntoResponseBody) {
        this.response = luckyRedbagIntoResponseBody;
    }
}
