package com.gbcom.gwifi.functions.revenue.domain;

import java.util.List;

public class DigConfigResponse {
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
        private List<AdCfgListBean> adCfgList;

        public List<AdCfgListBean> getAdCfgList() {
            return this.adCfgList;
        }

        public void setAdCfgList(List<AdCfgListBean> list) {
            this.adCfgList = list;
        }

        public static class AdCfgListBean {
            private int adType;

            /* renamed from: id */
            private int f11641id;
            private String imgUrl;
            private String wapUrl;

            public int getAdType() {
                return this.adType;
            }

            public void setAdType(int i) {
                this.adType = i;
            }

            public int getId() {
                return this.f11641id;
            }

            public void setId(int i) {
                this.f11641id = i;
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
}
