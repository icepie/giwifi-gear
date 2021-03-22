package com.gbcom.gwifi.functions.edu.domain;

import java.util.List;

public class TopNews {
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
        private List<NewsListBean> news_list;

        public List<NewsListBean> getNews_list() {
            return this.news_list;
        }

        public void setNews_list(List<NewsListBean> list) {
            this.news_list = list;
        }

        public static class NewsListBean {
            private int comment_count;
            private String create_time;
            private List<String> img_urls;
            private String local_wap_url;
            private int point_count;
            private String publish_user;
            private int score_wall_type;
            private String short_content;
            private int source_type;
            private String title;
            private String wap_url;

            public int getComment_count() {
                return this.comment_count;
            }

            public void setComment_count(int i) {
                this.comment_count = i;
            }

            public String getWap_url() {
                return this.wap_url;
            }

            public void setWap_url(String str) {
                this.wap_url = str;
            }

            public String getPublish_user() {
                return this.publish_user;
            }

            public void setPublish_user(String str) {
                this.publish_user = str;
            }

            public String getShort_content() {
                return this.short_content;
            }

            public void setShort_content(String str) {
                this.short_content = str;
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

            public int getPoint_count() {
                return this.point_count;
            }

            public void setPoint_count(int i) {
                this.point_count = i;
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

            public List<String> getImg_urls() {
                return this.img_urls;
            }

            public void setImg_urls(List<String> list) {
                this.img_urls = list;
            }
        }
    }
}
