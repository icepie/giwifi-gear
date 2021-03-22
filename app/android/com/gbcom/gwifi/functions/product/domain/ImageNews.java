package com.gbcom.gwifi.functions.product.domain;

import com.gbcom.gwifi.domain.BaseDomain;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
@Deprecated
public class ImageNews extends BaseDomain {
    private static final long serialVersionUID = 1;
    @DatabaseField(dataType = DataType.BYTE_ARRAY)
    private byte[] cacheIcon;
    @DatabaseField
    private Integer createTime;
    @DatabaseField
    private String imageUrl;
    @DatabaseField
    private Integer nId;
    @DatabaseField
    private Boolean read;
    @DatabaseField
    private Long receiveTime;
    @DatabaseField
    private String title;
    @DatabaseField
    private Integer updateTime;
    @DatabaseField
    private String url;

    public ImageNews() {
    }

    public ImageNews(Boolean bool, String str, String str2, String str3, Integer num) {
        this.read = bool;
        this.imageUrl = str;
        this.title = str2;
        this.url = str3;
        this.nId = num;
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

    public Integer getnId() {
        return this.nId;
    }

    public void setnId(Integer num) {
        this.nId = num;
    }

    public Boolean getRead() {
        return this.read;
    }

    public void setRead(Boolean bool) {
        this.read = bool;
    }

    public Long getReceiveTime() {
        return this.receiveTime;
    }

    public void setReceiveTime(Long l) {
        this.receiveTime = l;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String str) {
        this.imageUrl = str;
    }

    public Integer getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Integer num) {
        this.createTime = num;
    }

    public Integer getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Integer num) {
        this.updateTime = num;
    }

    public byte[] getCacheIcon() {
        return this.cacheIcon;
    }

    public void setCacheIcon(byte[] bArr) {
        this.cacheIcon = bArr;
    }
}
