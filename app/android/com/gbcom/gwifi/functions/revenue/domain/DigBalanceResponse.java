package com.gbcom.gwifi.functions.revenue.domain;

public class DigBalanceResponse {
    private DataBean data;
    private int resultCode;

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
        private int hitBeans;
        private int maxHitBeans;
        private int permitGain;

        public int getHitBeans() {
            return this.hitBeans;
        }

        public void setHitBeans(int i) {
            this.hitBeans = i;
        }

        public int getMaxHitBeans() {
            return this.maxHitBeans;
        }

        public void setMaxHitBeans(int i) {
            this.maxHitBeans = i;
        }

        public int getPermitGain() {
            return this.permitGain;
        }

        public void setPermitGain(int i) {
            this.permitGain = i;
        }
    }
}
