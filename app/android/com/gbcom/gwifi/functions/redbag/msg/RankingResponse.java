package com.gbcom.gwifi.functions.redbag.msg;

import java.util.List;

public class RankingResponse {
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
        private List<ListBean> list;

        public List<ListBean> getList() {
            return this.list;
        }

        public void setList(List<ListBean> list2) {
            this.list = list2;
        }

        public static class ListBean {
            private int accountId;
            private int buyChipTimes;
            private String createAt;
            private String faceUrl;
            private int isMyself;
            private String nickName;
            private int rank;
            private int totalHitBeans;

            public String getFaceUrl() {
                return this.faceUrl;
            }

            public void setFaceUrl(String str) {
                this.faceUrl = str;
            }

            public int getAccountId() {
                return this.accountId;
            }

            public void setAccountId(int i) {
                this.accountId = i;
            }

            public int getBuyChipTimes() {
                return this.buyChipTimes;
            }

            public void setBuyChipTimes(int i) {
                this.buyChipTimes = i;
            }

            public String getCreateAt() {
                return this.createAt;
            }

            public void setCreateAt(String str) {
                this.createAt = str;
            }

            public int getIsMyself() {
                return this.isMyself;
            }

            public void setIsMyself(int i) {
                this.isMyself = i;
            }

            public String getNickName() {
                return this.nickName;
            }

            public void setNickName(String str) {
                this.nickName = str;
            }

            public int getRank() {
                return this.rank;
            }

            public void setRank(int i) {
                this.rank = i;
            }

            public int getTotalHitBeans() {
                return this.totalHitBeans;
            }

            public void setTotalHitBeans(int i) {
                this.totalHitBeans = i;
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
