package com.gbcom.gwifi.functions.notify.domain;

import com.gbcom.gwifi.domain.BaseDomain;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Notify extends BaseDomain {
    public static final int ALARM_TYPE = 1;
    public static final int TASK_TYPE = 2;
    private static final long serialVersionUID = 1;
    @DatabaseField
    private String content;
    @DatabaseField
    private Long createTime;
    @DatabaseField
    private String imageUrl;
    @DatabaseField
    private String messageId;
    @DatabaseField
    private String phone;
    @DatabaseField
    private Long productId;
    @DatabaseField
    private String productType;
    @DatabaseField
    private Boolean read;
    @DatabaseField
    private Long receiveTime;
    @DatabaseField
    private Integer showType;
    @DatabaseField
    private String title;
    @DatabaseField
    private Integer type;
    @DatabaseField
    private String userPhone;
    @DatabaseField
    private String wapUrl;

    public String getUserPhone() {
        return this.userPhone;
    }

    public void setUserPhone(String str) {
        this.userPhone = str;
    }

    public Integer getShowType() {
        return this.showType;
    }

    public void setShowType(Integer num) {
        this.showType = num;
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

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer num) {
        this.type = num;
    }

    public String getMessageId() {
        return this.messageId;
    }

    public void setMessageId(String str) {
        this.messageId = str;
    }

    public Boolean getRead() {
        return this.read;
    }

    public void setRead(Boolean bool) {
        this.read = bool;
    }

    public Long getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Long l) {
        this.createTime = l;
    }

    public Long getReceiveTime() {
        return this.receiveTime;
    }

    public void setReceiveTime(Long l) {
        this.receiveTime = l;
    }

    public String getWapUrl() {
        return this.wapUrl;
    }

    public void setWapUrl(String str) {
        this.wapUrl = str;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String str) {
        this.phone = str;
    }

    public Long getProductId() {
        return this.productId;
    }

    public void setProductId(Long l) {
        this.productId = l;
    }

    public String getProductType() {
        return this.productType;
    }

    public void setProductType(String str) {
        this.productType = str;
    }
}
