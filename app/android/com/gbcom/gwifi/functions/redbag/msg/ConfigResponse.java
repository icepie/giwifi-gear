package com.gbcom.gwifi.functions.redbag.msg;

import com.gbcom.gwifi.functions.redbag.domain.Config;

@UserMsgAndExecAnnotation(msgType = -1000)
public class ConfigResponse extends AbstractMsg {
    protected ConfigResponseBody response;
    protected ResponseHeader responseHeader = new ResponseHeader();

    public class ConfigResponseBody {
        private Config config;

        public ConfigResponseBody() {
        }

        public Config getConfig() {
            return this.config;
        }

        public void setConfig(Config config2) {
            this.config = config2;
        }
    }

    public ConfigResponse() {
        this.responseHeader.setCommand(-1000);
        this.response = new ConfigResponseBody();
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

    public ConfigResponseBody getResponse() {
        return this.response;
    }

    public void setResponse(ConfigResponseBody configResponseBody) {
        this.response = configResponseBody;
    }
}
