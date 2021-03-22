package com.gbcom.gwifi.functions.revenue.domain;

public class ShakeResult {
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
        private String name;
        private int permitGain;
        private String remark;

        public int getHitBeans() {
            return this.hitBeans;
        }

        public void setHitBeans(int i) {
            this.hitBeans = i;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }

        public int getPermitGain() {
            return this.permitGain;
        }

        public void setPermitGain(int i) {
            this.permitGain = i;
        }

        public String getRemark() {
            return this.remark;
        }

        public void setRemark(String str) {
            this.remark = str;
        }
    }
}
