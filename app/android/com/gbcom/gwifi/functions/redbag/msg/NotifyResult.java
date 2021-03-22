package com.gbcom.gwifi.functions.redbag.msg;

import java.p456io.Serializable;
import java.util.List;

public class NotifyResult implements Serializable {
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
        private List<HitRecordBean> hitRecord;
        private int prizePoolBeans;
        private int redId;
        private String roundNo;

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

        public List<HitRecordBean> getHitRecord() {
            return this.hitRecord;
        }

        public void setHitRecord(List<HitRecordBean> list) {
            this.hitRecord = list;
        }

        public static class HitRecordBean implements Serializable {
            private int accountId;
            private String createAt;
            private int deductBeans;
            private String faceUrl;
            private int gender;
            private int hitBeans;
            private int luckyNumberBonus;
            private String nickName;
            private String tenantName;

            public int getAccountId() {
                return this.accountId;
            }

            public void setAccountId(int i) {
                this.accountId = i;
            }

            public String getCreateAt() {
                return this.createAt;
            }

            public void setCreateAt(String str) {
                this.createAt = str;
            }

            public int getDeductBeans() {
                return this.deductBeans;
            }

            public void setDeductBeans(int i) {
                this.deductBeans = i;
            }

            public String getFaceUrl() {
                return this.faceUrl;
            }

            public void setFaceUrl(String str) {
                this.faceUrl = str;
            }

            public int getGender() {
                return this.gender;
            }

            public void setGender(int i) {
                this.gender = i;
            }

            public int getHitBeans() {
                return this.hitBeans;
            }

            public void setHitBeans(int i) {
                this.hitBeans = i;
            }

            public int getLuckyNumberBonus() {
                return this.luckyNumberBonus;
            }

            public void setLuckyNumberBonus(int i) {
                this.luckyNumberBonus = i;
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
