package com.gbcom.gwifi.functions.product.domain;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
@Deprecated
public class TextNews {
    private static final long serialVersionUID = 1;
    @DatabaseField
    private Integer createTime;
    @DatabaseField(mo32986id = true)

    /* renamed from: id */
    private Integer f10804id;
    @DatabaseField
    private String imageUrl;
    @DatabaseField
    private String publishTime;
    @DatabaseField
    private Boolean read;
    @DatabaseField
    private Integer receiveTime;
    @DatabaseField
    private String subTitle;
    @DatabaseField
    private String title;
    @DatabaseField
    private Integer updateTime;
    @DatabaseField
    private String url;

    public TextNews() {
    }

    public TextNews(String str, String str2, String str3, String str4, Integer num, Integer num2, Boolean bool, Integer num3, String str5) {
        this.title = str;
        this.subTitle = str2;
        this.url = str3;
        this.imageUrl = str4;
        this.f10804id = num;
        this.updateTime = num2;
        this.read = bool;
        this.createTime = num3;
        this.publishTime = str5;
    }

    public String getSubTitle() {
        return this.subTitle;
    }

    public void setSubTitle(String str) {
        this.subTitle = str;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String str) {
        this.imageUrl = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public Integer getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Integer num) {
        this.updateTime = num;
    }

    public Boolean getRead() {
        return this.read;
    }

    public void setRead(Boolean bool) {
        this.read = bool;
    }

    public Integer getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Integer num) {
        this.createTime = num;
    }

    public Integer getReceiveTime() {
        return this.receiveTime;
    }

    public void setReceiveTime(Integer num) {
        this.receiveTime = num;
    }

    public Integer getId() {
        return this.f10804id;
    }

    public void setId(Integer num) {
        this.f10804id = num;
    }

    public String getPublishTime() {
        return this.publishTime;
    }

    public void setPublishTime(String str) {
        this.publishTime = str;
    }
}
