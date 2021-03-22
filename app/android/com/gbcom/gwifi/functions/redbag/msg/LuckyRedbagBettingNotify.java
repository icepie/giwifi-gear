package com.gbcom.gwifi.functions.redbag.msg;

import java.p456io.Serializable;

@UserMsgAndExecAnnotation(msgType = MsgType.LUCKY_REDBAG_REPORT_BETTING)
public class LuckyRedbagBettingNotify extends AbstractMsg implements Serializable {
    private LuckyRedbagBettingNotifyBody notify;
    private NotifyHeader notifyHeader = new NotifyHeader();

    public class LuckyRedbagBettingNotifyBody implements Serializable {
        private int accountId;
        private int bestLuckyIndex;
        private int count;
        private String createAt;
        private String faceUrl;
        private int hasHitBeans;
        private int hasSale;
        private int hitBeans;
        private int luckyRedbagId;
        private String nickName;
        private String tenantName;
        private int totalBeans;

        public LuckyRedbagBettingNotifyBody() {
        }

        public int getLuckyRedbagId() {
            return this.luckyRedbagId;
        }

        public void setLuckyRedbagId(int i) {
            this.luckyRedbagId = i;
        }

        public int getAccountId() {
            return this.accountId;
        }

        public void setAccountId(int i) {
            this.accountId = i;
        }

        public String getFaceUrl() {
            return this.faceUrl;
        }

        public void setFaceUrl(String str) {
            this.faceUrl = str;
        }

        public String getNickName() {
            return this.nickName;
        }

        public void setNickName(String str) {
            this.nickName = str;
        }

        public String getTenantName() {
            return this.tenantName;
        }

        public void setTenantName(String str) {
            this.tenantName = str;
        }

        public String getCreateAt() {
            return this.createAt;
        }

        public void setCreateAt(String str) {
            this.createAt = str;
        }

        public int getHitBeans() {
            return this.hitBeans;
        }

        public void setHitBeans(int i) {
            this.hitBeans = i;
        }

        public int getHasSale() {
            return this.hasSale;
        }

        public void setHasSale(int i) {
            this.hasSale = i;
        }

        public int getCount() {
            return this.count;
        }

        public void setCount(int i) {
            this.count = i;
        }

        public int getHasHitBeans() {
            return this.hasHitBeans;
        }

        public void setHasHitBeans(int i) {
            this.hasHitBeans = i;
        }

        public int getTotalBeans() {
            return this.totalBeans;
        }

        public void setTotalBeans(int i) {
            this.totalBeans = i;
        }

        public int getBestLuckyIndex() {
            return this.bestLuckyIndex;
        }

        public void setBestLuckyIndex(int i) {
            this.bestLuckyIndex = i;
        }
    }

    public LuckyRedbagBettingNotify() {
        this.notifyHeader.setCommand(MsgType.LUCKY_REDBAG_REPORT_BETTING);
        this.notify = new LuckyRedbagBettingNotifyBody();
    }

    @Override // com.gbcom.gwifi.functions.redbag.msg.AbstractMsg
    public short getType() {
        return this.notifyHeader.getCommand();
    }

    public NotifyHeader getNotifyHeader() {
        return this.notifyHeader;
    }

    public void setNotifyHeader(NotifyHeader notifyHeader2) {
        this.notifyHeader = notifyHeader2;
    }

    public LuckyRedbagBettingNotifyBody getNotify() {
        return this.notify;
    }

    public void setNotify(LuckyRedbagBettingNotifyBody luckyRedbagBettingNotifyBody) {
        this.notify = luckyRedbagBettingNotifyBody;
    }
}
