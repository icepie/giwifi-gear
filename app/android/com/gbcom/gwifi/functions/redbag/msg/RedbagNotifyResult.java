package com.gbcom.gwifi.functions.redbag.msg;

import com.gbcom.gwifi.functions.redbag.domain.HitRecord;
import java.p456io.Serializable;
import java.util.List;

public class RedbagNotifyResult implements Serializable {
    private NotifyBean notify;
    private NotifyHeaderBean notifyHeader;

    public NotifyBean getNotify() {
        return this.notify;
    }

    public void setNotify(NotifyBean notifyBean) {
        this.notify = notifyBean;
    }

    public NotifyHeaderBean getNotifyHeader() {
        return this.notifyHeader;
    }

    public void setNotifyHeader(NotifyHeaderBean notifyHeaderBean) {
        this.notifyHeader = notifyHeaderBean;
    }

    public static class NotifyBean implements Serializable {
        private List<HitRecord> hitRecord;
        private String nextRoundNo;
        private int nextTotalCount;
        private int prizePoolBeans;
        private int redId;
        private String roundNo;
        private int totalBeans;

        public String getNextRoundNo() {
            return this.nextRoundNo;
        }

        public void setNextRoundNo(String str) {
            this.nextRoundNo = str;
        }

        public int getNextTotalCount() {
            return this.nextTotalCount;
        }

        public void setNextTotalCount(int i) {
            this.nextTotalCount = i;
        }

        public int getPrizePoolBeans() {
            return this.prizePoolBeans;
        }

        public void setPrizePoolBeans(int i) {
            this.prizePoolBeans = i;
        }

        public int getRedId() {
            return this.redId;
        }

        public void setRedId(int i) {
            this.redId = i;
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

        public List<HitRecord> getHitRecord() {
            return this.hitRecord;
        }

        public void setHitRecord(List<HitRecord> list) {
            this.hitRecord = list;
        }
    }

    public static class NotifyHeaderBean implements Serializable {
        private int command;

        public int getCommand() {
            return this.command;
        }

        public void setCommand(int i) {
            this.command = i;
        }
    }
}
