package com.gbcom.gwifi.functions.redbag.msg;

import com.gbcom.gwifi.functions.redbag.domain.HitRecord;
import java.p456io.Serializable;
import java.util.ArrayList;

@UserMsgAndExecAnnotation(msgType = -4004)
public class RedbagDetailResponse extends AbstractMsg implements Serializable {
    protected RedbagDetailResponseBody response;
    protected ResponseHeader responseHeader = new ResponseHeader();

    public class RedbagDetailResponseBody implements Serializable {
        private int hitBeans;
        private ArrayList<HitRecord> hitRecord = new ArrayList<>();
        private int hitRecordSize;
        private int isJoin;
        private int luckyNumberBonus;
        private String roundNo;
        private int status;
        private int totalBeans;

        public RedbagDetailResponseBody() {
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

        public int getIsJoin() {
            return this.isJoin;
        }

        public void setIsJoin(int i) {
            this.isJoin = i;
        }

        public int getHitBeans() {
            return this.hitBeans;
        }

        public void setHitBeans(int i) {
            this.hitBeans = i;
        }

        public int getStatus() {
            return this.status;
        }

        public void setStatus(int i) {
            this.status = i;
        }

        public int getLuckyNumberBonus() {
            return this.luckyNumberBonus;
        }

        public void setLuckyNumberBonus(int i) {
            this.luckyNumberBonus = i;
        }

        public int getHitRecordSize() {
            return this.hitRecordSize;
        }

        public void setHitRecordSize(int i) {
            this.hitRecordSize = i;
        }

        public ArrayList<HitRecord> getHitRecord() {
            return this.hitRecord;
        }

        public void setHitRecord(ArrayList<HitRecord> arrayList) {
            this.hitRecord = arrayList;
        }
    }

    public RedbagDetailResponse() {
        this.responseHeader.setCommand(-4004);
        this.response = new RedbagDetailResponseBody();
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

    public RedbagDetailResponseBody getResponse() {
        return this.response;
    }

    public void setResponse(RedbagDetailResponseBody redbagDetailResponseBody) {
        this.response = redbagDetailResponseBody;
    }
}
