package com.gbcom.gwifi.functions.revenue.domain;

import java.util.List;

public class SingleRedbagRanking {
    private DataBean data;
    private int resultCode;
    private String resultMsg;

    public DataBean getData() {
        return this.data;
    }

    public void setData(DataBean dataBean) {
        this.data = dataBean;
    }

    public int getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(int i) {
        this.resultCode = i;
    }

    public String getResultMsg() {
        return this.resultMsg;
    }

    public void setResultMsg(String str) {
        this.resultMsg = str;
    }

    public static class DataBean {
        private List<RankListBean> rankList;

        public List<RankListBean> getRankList() {
            return this.rankList;
        }

        public void setRankList(List<RankListBean> list) {
            this.rankList = list;
        }

        public static class RankListBean {
            private String faceUrl;
            private String nickName;
            private int rank;
            private String totalGainBeans;

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

            public int getRank() {
                return this.rank;
            }

            public void setRank(int i) {
                this.rank = i;
            }

            public String getTotalGainBeans() {
                return this.totalGainBeans;
            }

            public void setTotalGainBeans(String str) {
                this.totalGainBeans = str;
            }
        }
    }
}
