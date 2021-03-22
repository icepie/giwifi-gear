package com.gbcom.gwifi.functions.loading.domain;

import java.util.List;

public class WindowConfigResponse {
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
        private List<WicketConfigListBean> wicket_config_list;

        public List<WicketConfigListBean> getWicket_config_list() {
            return this.wicket_config_list;
        }

        public void setWicket_config_list(List<WicketConfigListBean> list) {
            this.wicket_config_list = list;
        }

        public static class WicketConfigListBean {
            private String img_url;
            private int source_type;
            private String title;
            private String wap_url;

            public String getImg_url() {
                return this.img_url;
            }

            public void setImg_url(String str) {
                this.img_url = str;
            }

            public int getSource_type() {
                return this.source_type;
            }

            public void setSource_type(int i) {
                this.source_type = i;
            }

            public String getTitle() {
                return this.title;
            }

            public void setTitle(String str) {
                this.title = str;
            }

            public String getWap_url() {
                return this.wap_url;
            }

            public void setWap_url(String str) {
                this.wap_url = str;
            }
        }
    }
}
