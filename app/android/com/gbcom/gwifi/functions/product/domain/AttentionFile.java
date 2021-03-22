package com.gbcom.gwifi.functions.product.domain;

import com.gbcom.gwifi.domain.BaseDomain;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class AttentionFile extends BaseDomain {
    private static final long serialVersionUID = 1;
    @DatabaseField
    private Integer appPointsReward;
    @DatabaseField(dataType = DataType.BYTE_ARRAY)
    private byte[] cacheIcon;
    @DatabaseField
    private Integer downloadCount;
    @DatabaseField
    private Long fileTotalSize;
    @DatabaseField
    private String productType;
    @DatabaseField
    private Integer sid;
    @DatabaseField
    private String tags;
    @DatabaseField
    private String title;

    public Integer getAppPointsReward() {
        return this.appPointsReward;
    }

    public void setAppPointsReward(Integer num) {
        this.appPointsReward = num;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getProductType() {
        return this.productType;
    }

    public void setProductType(String str) {
        this.productType = str;
    }

    public byte[] getCacheIcon() {
        return this.cacheIcon;
    }

    public void setCacheIcon(byte[] bArr) {
        this.cacheIcon = bArr;
    }

    public Integer getDownloadCount() {
        return this.downloadCount;
    }

    public void setDownloadCount(Integer num) {
        this.downloadCount = num;
    }

    public Integer getSid() {
        return this.sid;
    }

    public void setSid(Integer num) {
        this.sid = num;
    }

    public Long getFileTotalSize() {
        return this.fileTotalSize;
    }

    public void setFileTotalSize(Long l) {
        this.fileTotalSize = l;
    }

    public String getTags() {
        return this.tags;
    }

    public void setTags(String str) {
        this.tags = str;
    }
}
