package com.gbcom.gwifi.functions.revenue.domain;

import java.util.List;

public class SingleRedbagConfig {
    private DataBean data;
    private int resultCode;
    private String resultMsg;

    public String getResultMsg() {
        return this.resultMsg;
    }

    public void setResultMsg(String str) {
        this.resultMsg = str;
    }

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

    public static class DataBean {
        private int beans;
        private String beansChargeLink;
        private List<RedListBean> redList;

        public int getBeans() {
            return this.beans;
        }

        public void setBeans(int i) {
            this.beans = i;
        }

        public String getBeansChargeLink() {
            return this.beansChargeLink;
        }

        public void setBeansChargeLink(String str) {
            this.beansChargeLink = str;
        }

        public List<RedListBean> getRedList() {
            return this.redList;
        }

        public void setRedList(List<RedListBean> list) {
            this.redList = list;
        }

        public static class RedListBean {
            private int maxGainBeans;
            private int minGainBeans;
            private int redId;
            private String redName;
            private int totalBeans;

            public int getMaxGainBeans() {
                return this.maxGainBeans;
            }

            public void setMaxGainBeans(int i) {
                this.maxGainBeans = i;
            }

            public int getMinGainBeans() {
                return this.minGainBeans;
            }

            public void setMinGainBeans(int i) {
                this.minGainBeans = i;
            }

            public int getRedId() {
                return this.redId;
            }

            public void setRedId(int i) {
                this.redId = i;
            }

            public String getRedName() {
                return this.redName;
            }

            public void setRedName(String str) {
                this.redName = str;
            }

            public int getTotalBeans() {
                return this.totalBeans;
            }

            public void setTotalBeans(int i) {
                this.totalBeans = i;
            }
        }
    }
}
