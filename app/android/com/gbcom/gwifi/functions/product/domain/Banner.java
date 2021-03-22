package com.gbcom.gwifi.functions.product.domain;

import com.gbcom.gwifi.domain.BaseDomain;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Banner extends BaseDomain {
    private static final long serialVersionUID = 1;
    @DatabaseField(dataType = DataType.BYTE_ARRAY)
    private byte[] cacheIcon;
    @DatabaseField
    private Integer catId;
    @DatabaseField
    private String catType;
    @DatabaseField
    private String imageUrl;
    private Integer productId;
    @DatabaseField
    private String title;
    @DatabaseField
    private Integer type;
    @DatabaseField
    private Long updateTime;
    @DatabaseField
    private String wapUrl;

    public Integer getProductId() {
        return this.productId;
    }

    public void setProductId(Integer num) {
        this.productId = num;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public Integer getCatId() {
        return this.catId;
    }

    public void setCatId(Integer num) {
        this.catId = num;
    }

    public String getCatType() {
        return this.catType;
    }

    public void setCatType(String str) {
        this.catType = str;
    }

    public String getWapUrl() {
        return this.wapUrl;
    }

    public void setWapUrl(String str) {
        this.wapUrl = str;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String str) {
        this.imageUrl = str;
    }

    public Long getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Long l) {
        this.updateTime = l;
    }

    public byte[] getCacheIcon() {
        return this.cacheIcon;
    }

    public void setCacheIcon(byte[] bArr) {
        this.cacheIcon = bArr;
    }

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer num) {
        this.type = num;
    }
}
