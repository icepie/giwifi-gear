package com.gbcom.gwifi.functions.product.domain;

import com.gbcom.gwifi.domain.BaseDomain;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Video extends BaseDomain {
    private static final long serialVersionUID = 1;
    @DatabaseField
    private Integer availableEpisode = 0;
    @DatabaseField(dataType = DataType.BYTE_ARRAY)
    private byte[] cacheIcon;
    @DatabaseField
    private Integer downloadCount;
    @DatabaseField
    private Long fileTotalSize;
    @DatabaseField
    private String imageUrl;
    @DatabaseField
    private Boolean isTV;
    @DatabaseField
    private Integer sid;
    @DatabaseField
    private Integer speed = 0;
    @DatabaseField
    private String subTitle;
    @DatabaseField
    private String tags;
    @DatabaseField
    private String title;
    @DatabaseField
    private Integer totalEpisode = 0;
    @DatabaseField
    private String wapUrl;

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
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

    public String getWapUrl() {
        return this.wapUrl;
    }

    public void setWapUrl(String str) {
        this.wapUrl = str;
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

    public Boolean getTV() {
        return this.isTV;
    }

    public void setTV(Boolean bool) {
        this.isTV = bool;
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

    public Integer getSpeed() {
        return this.speed;
    }

    public void setSpeed(Integer num) {
        this.speed = num;
    }

    public Integer getAvailableEpisode() {
        return this.availableEpisode;
    }

    public void setAvailableEpisode(Integer num) {
        this.availableEpisode = num;
    }

    public Integer getTotalEpisode() {
        return this.totalEpisode;
    }

    public void setTotalEpisode(Integer num) {
        this.totalEpisode = num;
    }
}
