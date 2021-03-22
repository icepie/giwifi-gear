package com.gbcom.gwifi.functions.redbag.msg;

import java.util.List;

public class RedbagMessageResponse {
    private ResponseBean response;
    private ResponseHeaderBean responseHeader;

    public ResponseBean getResponse() {
        return this.response;
    }

    public void setResponse(ResponseBean responseBean) {
        this.response = responseBean;
    }

    public ResponseHeaderBean getResponseHeader() {
        return this.responseHeader;
    }

    public void setResponseHeader(ResponseHeaderBean responseHeaderBean) {
        this.responseHeader = responseHeaderBean;
    }

    public static class ResponseBean {
        private List<PrizePoolBonusesBean> prizePoolBonuses;
        private RedbagContentBean redbagContent;

        public RedbagContentBean getRedbagContent() {
            return this.redbagContent;
        }

        public void setRedbagContent(RedbagContentBean redbagContentBean) {
            this.redbagContent = redbagContentBean;
        }

        public List<PrizePoolBonusesBean> getPrizePoolBonuses() {
            return this.prizePoolBonuses;
        }

        public void setPrizePoolBonuses(List<PrizePoolBonusesBean> list) {
            this.prizePoolBonuses = list;
        }

        public static class RedbagContentBean {
            private String content;
            private int endTime;

            public String getContent() {
                return this.content;
            }

            public void setContent(String str) {
                this.content = str;
            }

            public int getEndTime() {
                return this.endTime;
            }

            public void setEndTime(int i) {
                this.endTime = i;
            }
        }

        public static class PrizePoolBonusesBean {
            private int accountId;
            private String createAt;
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

    public static class ResponseHeaderBean {
        private int command;
        private String errorMessage;
        private int sequenceId;
        private int status;

        public int getCommand() {
            return this.command;
        }

        public void setCommand(int i) {
            this.command = i;
        }

        public String getErrorMessage() {
            return this.errorMessage;
        }

        public void setErrorMessage(String str) {
            this.errorMessage = str;
        }

        public int getSequenceId() {
            return this.sequenceId;
        }

        public void setSequenceId(int i) {
            this.sequenceId = i;
        }

        public int getStatus() {
            return this.status;
        }

        public void setStatus(int i) {
            this.status = i;
        }
    }
}
