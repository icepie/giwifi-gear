package com.gbcom.gwifi.functions.product.domain;

import com.gbcom.gwifi.domain.BaseDomain;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Game extends BaseDomain {
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
    private String fileUrl;
    @DatabaseField
    private String imageUrl;
    @DatabaseField
    private Integer isWap = 0;
    @DatabaseField
    private String packageName;
    @DatabaseField
    private Integer sid;
    @DatabaseField
    private Integer speed = 0;
    @DatabaseField
    private String tags;
    @DatabaseField
    private String title;
    private String wapUrl;

    public Integer getAppPointsReward() {
        return this.appPointsReward;
    }

    public void setAppPointsReward(Integer num) {
        this.appPointsReward = num;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public void setPackageName(String str) {
        this.packageName = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String str) {
        this.imageUrl = str;
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

    public String getFileUrl() {
        return this.fileUrl;
    }

    public void setFileUrl(String str) {
        this.fileUrl = str;
    }

    public Integer getSpeed() {
        return this.speed;
    }

    public void setSpeed(Integer num) {
        this.speed = num;
    }

    public Integer getIsWap() {
        return this.isWap;
    }

    public void setIsWap(Integer num) {
        this.isWap = num;
    }

    public String getWapUrl() {
        return this.wapUrl;
    }

    public void setWapUrl(String str) {
        this.wapUrl = str;
    }
}
