package com.gbcom.gwifi.functions.loading.domain;

public class NotifyWhenAppExit {
    private String content;
    private boolean hasDialog;
    private String phone;
    private Long productId;
    private String productType;
    private String reminder;
    private Integer sort;
    private String title;
    private Integer type;
    private String wapUrl;

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

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer num) {
        this.type = num;
    }

    public String getReminder() {
        return this.reminder;
    }

    public void setReminder(String str) {
        this.reminder = str;
    }

    public boolean isHasDialog() {
        return this.hasDialog;
    }

    public void setHasDialog(boolean z) {
        this.hasDialog = z;
    }

    public Integer getSort() {
        return this.sort;
    }

    public void setSort(Integer num) {
        this.sort = num;
    }
}
