package com.gbcom.gwifi.functions.revenue.domain;

public class ShakeAdResponse {
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
        private int adType;
        private String imgUrl;
        private String wapUrl;

        public int getAdType() {
            return this.adType;
        }

        public void setAdType(int i) {
            this.adType = i;
        }

        public String getImgUrl() {
            return this.imgUrl;
        }

        public void setImgUrl(String str) {
            this.imgUrl = str;
        }

        public String getWapUrl() {
            return this.wapUrl;
        }

        public void setWapUrl(String str) {
            this.wapUrl = str;
        }
    }
}
