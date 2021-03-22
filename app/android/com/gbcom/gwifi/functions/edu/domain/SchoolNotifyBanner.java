package com.gbcom.gwifi.functions.edu.domain;

import java.p456io.Serializable;

public class SchoolNotifyBanner implements Serializable {
    private String imgUrl;
    private String object;
    private int source_type;
    private String title;
    private String wapUrl;

    public int getSource_type() {
        return this.source_type;
    }

    public void setSource_type(int i) {
        this.source_type = i;
    }

    public String getObject() {
        return this.object;
    }

    public void setObject(String str) {
        this.object = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
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
