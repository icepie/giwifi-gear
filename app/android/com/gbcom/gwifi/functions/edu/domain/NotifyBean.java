package com.gbcom.gwifi.functions.edu.domain;

import java.util.List;

public class NotifyBean {
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
        private List<NotifyListBean> notify_list;

        public List<NotifyListBean> getNotify_list() {
            return this.notify_list;
        }

        public void setNotify_list(List<NotifyListBean> list) {
            this.notify_list = list;
        }

        public static class NotifyListBean {
            private String create_time;
            private String digest;
            private int limit_num;
            private String limit_time;
            private String local_wap_url;
            private String publish_department;
            private String publish_department_avatar;
            private int score_wall_type;
            private int source_type;
            private String title;
            private String wap_url;

            public String getWap_url() {
                return this.wap_url;
            }

            public void setWap_url(String str) {
                this.wap_url = str;
            }

            public String getTitle() {
                return this.title;
            }

            public void setTitle(String str) {
                this.title = str;
            }

            public int getScore_wall_type() {
                return this.score_wall_type;
            }

            public void setScore_wall_type(int i) {
                this.score_wall_type = i;
            }

            public String getLocal_wap_url() {
                return this.local_wap_url;
            }

            public void setLocal_wap_url(String str) {
                this.local_wap_url = str;
            }

            public String getCreate_time() {
                return this.create_time;
            }

            public void setCreate_time(String str) {
                this.create_time = str;
            }

            public int getSource_type() {
                return this.source_type;
            }

            public void setSource_type(int i) {
                this.source_type = i;
            }

            public String getDigest() {
                return this.digest;
            }

            public void setDigest(String str) {
                this.digest = str;
            }

            public int getLimit_num() {
                return this.limit_num;
            }

            public void setLimit_num(int i) {
                this.limit_num = i;
            }

            public String getPublish_department_avatar() {
                return this.publish_department_avatar;
            }

            public void setPublish_department_avatar(String str) {
                this.publish_department_avatar = str;
            }

            public String getPublish_department() {
                return this.publish_department;
            }

            public void setPublish_department(String str) {
                this.publish_department = str;
            }

            public String getLimit_time() {
                return this.limit_time;
            }

            public void setLimit_time(String str) {
                this.limit_time = str;
            }
        }
    }
}
