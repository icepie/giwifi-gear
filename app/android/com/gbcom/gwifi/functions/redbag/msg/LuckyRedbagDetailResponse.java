package com.gbcom.gwifi.functions.redbag.msg;

import com.gbcom.gwifi.functions.redbag.domain.LuckyHitRecord;
import java.p456io.Serializable;
import java.util.ArrayList;

@UserMsgAndExecAnnotation(msgType = -4453)
public class LuckyRedbagDetailResponse extends AbstractMsg implements Serializable {
    protected LuckyRedbagDetailResponseBody response;
    protected ResponseHeader responseHeader = new ResponseHeader();

    public class LuckyRedbagDetailResponseBody implements Serializable {
        private int hasHitBeans;
        private int hasSale;
        private int hitBeans;
        private ArrayList<LuckyHitRecord> hitRecord = new ArrayList<>();
        private int hitRecordSize;
        private int isJoin;
        private int luckyCostTime;
        private int luckyCount;
        private int luckyRedbagId;
        private String luckyRemark;
        private int luckyTotalBeans;
        private int luckyType;
        private int status;

        public LuckyRedbagDetailResponseBody() {
        }

        public int getLuckyRedbagId() {
            return this.luckyRedbagId;
        }

        public void setLuckyRedbagId(int i) {
            this.luckyRedbagId = i;
        }

        public String getLuckyRemark() {
            return this.luckyRemark;
        }

        public void setLuckyRemark(String str) {
            this.luckyRemark = str;
        }

        public int getLuckyType() {
            return this.luckyType;
        }

        public void setLuckyType(int i) {
            this.luckyType = i;
        }

        public int getLuckyTotalBeans() {
            return this.luckyTotalBeans;
        }

        public void setLuckyTotalBeans(int i) {
            this.luckyTotalBeans = i;
        }

        public int getLuckyCount() {
            return this.luckyCount;
        }

        public void setLuckyCount(int i) {
            this.luckyCount = i;
        }

        public int getHasSale() {
            return this.hasSale;
        }

        public void setHasSale(int i) {
            this.hasSale = i;
        }

        public int getHasHitBeans() {
            return this.hasHitBeans;
        }

        public void setHasHitBeans(int i) {
            this.hasHitBeans = i;
        }

        public int getStatus() {
            return this.status;
        }

        public void setStatus(int i) {
            this.status = i;
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

        public int getLuckyCostTime() {
            return this.luckyCostTime;
        }

        public void setLuckyCostTime(int i) {
            this.luckyCostTime = i;
        }

        public int getHitRecordSize() {
            return this.hitRecordSize;
        }

        public void setHitRecordSize(int i) {
            this.hitRecordSize = i;
        }

        public ArrayList<LuckyHitRecord> getHitRecord() {
            return this.hitRecord;
        }

        public void setHitRecord(ArrayList<LuckyHitRecord> arrayList) {
            this.hitRecord = arrayList;
        }
    }

    public LuckyRedbagDetailResponse() {
        this.responseHeader.setCommand(-4453);
        this.response = new LuckyRedbagDetailResponseBody();
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

    public LuckyRedbagDetailResponseBody getResponse() {
        return this.response;
    }

    public void setResponse(LuckyRedbagDetailResponseBody luckyRedbagDetailResponseBody) {
        this.response = luckyRedbagDetailResponseBody;
    }
}
