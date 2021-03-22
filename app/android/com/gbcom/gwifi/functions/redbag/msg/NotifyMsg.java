package com.gbcom.gwifi.functions.redbag.msg;

import java.p456io.Serializable;

public class NotifyMsg implements Serializable {
    private NotifyBean notify;
    private NotifyHeaderBean notifyHeader;

    public NotifyHeaderBean getNotifyHeader() {
        return this.notifyHeader;
    }

    public void setNotifyHeader(NotifyHeaderBean notifyHeaderBean) {
        this.notifyHeader = notifyHeaderBean;
    }

    public NotifyBean getNotify() {
        return this.notify;
    }

    public void setNotify(NotifyBean notifyBean) {
        this.notify = notifyBean;
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

    public static class NotifyBean implements Serializable {
        private int accountId;
        private String createAt;
        private String faceUrl;
        private String nickName;
        private int redId;
        private String roundNo;

        public String getFaceUrl() {
            return this.faceUrl;
        }

        public void setFaceUrl(String str) {
            this.faceUrl = str;
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

        public int getAccountId() {
            return this.accountId;
        }

        public void setAccountId(int i) {
            this.accountId = i;
        }

        public String getNickName() {
            return this.nickName;
        }

        public void setNickName(String str) {
            this.nickName = str;
        }

        public String getCreateAt() {
            return this.createAt;
        }

        public void setCreateAt(String str) {
            this.createAt = str;
        }
    }
}
