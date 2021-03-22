package com.gbcom.gwifi.functions.redbag.msg;

import com.gbcom.gwifi.functions.redbag.domain.AccountPrizePoolBonus;
import com.gbcom.gwifi.functions.redbag.domain.PrizePoolSetting;
import java.util.List;

@UserMsgAndExecAnnotation(msgType = -4200)
public class PrizePoolResponse extends AbstractMsg {
    protected PrizePoolResponseBody response;
    protected ResponseHeader responseHeader = new ResponseHeader();

    public class PrizePoolResponseBody {
        private List<AccountPrizePoolBonus> luckyAccounts;
        private List<PrizePoolSetting> luckyNumbers;
        private int totalBeans;

        public PrizePoolResponseBody() {
        }

        public int getTotalBeans() {
            return this.totalBeans;
        }

        public void setTotalBeans(int i) {
            this.totalBeans = i;
        }

        public List<PrizePoolSetting> getLuckyNumbers() {
            return this.luckyNumbers;
        }

        public void setLuckyNumbers(List<PrizePoolSetting> list) {
            this.luckyNumbers = list;
        }

        public List<AccountPrizePoolBonus> getLuckyAccounts() {
            return this.luckyAccounts;
        }

        public void setLuckyAccounts(List<AccountPrizePoolBonus> list) {
            this.luckyAccounts = list;
        }
    }

    public PrizePoolResponse() {
        this.responseHeader.setCommand(-4200);
        this.response = new PrizePoolResponseBody();
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

    public PrizePoolResponseBody getResponse() {
        return this.response;
    }

    public void setResponse(PrizePoolResponseBody prizePoolResponseBody) {
        this.response = prizePoolResponseBody;
    }
}
