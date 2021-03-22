package com.gbcom.gwifi.functions.product.domain;

import com.gbcom.gwifi.domain.BaseDomain;
import com.gbcom.gwifi.functions.download.IDownloadFile;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class DownloadFile extends BaseDomain implements IDownloadFile {
    private static final long serialVersionUID = 1;
    @DatabaseField
    private Integer appPointsReward;
    @DatabaseField
    private Long createTime;
    @DatabaseField
    private String currentNo;
    @DatabaseField
    private Long downsize = 0L;
    @DatabaseField
    private Long fileTotalSize;
    @DatabaseField
    private Long finishTime;
    @DatabaseField
    private String imageUrl;
    @DatabaseField
    private Integer isRead = 0;
    @DatabaseField
    private Boolean isTV = false;
    @DatabaseField
    private String localFile;
    @DatabaseField
    private String name;
    @DatabaseField
    private String packageName;
    @DatabaseField
    private Long parentId = -1L;
    @DatabaseField
    private Integer productId;
    @DatabaseField
    private String productType;
    @DatabaseField
    private Integer stateId;
    @DatabaseField
    private String tags;
    @DatabaseField
    private String tempFile;
    @DatabaseField
    private Integer totalEpisode = 0;
    @DatabaseField
    private String url;

    @Override // com.gbcom.gwifi.functions.download.IDownloadFile
    public String getName() {
        return this.name;
    }

    public Integer getIsRead() {
        return this.isRead;
    }

    public void setIsRead(Integer num) {
        this.isRead = num;
    }

    public Integer getTotalEpisode() {
        return this.totalEpisode;
    }

    public void setTotalEpisode(Integer num) {
        this.totalEpisode = num;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String str) {
        this.imageUrl = str;
    }

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

    public void setName(String str) {
        this.name = str;
    }

    public String getTags() {
        return this.tags;
    }

    public void setTags(String str) {
        this.tags = str;
    }

    @Override // com.gbcom.gwifi.functions.download.IDownloadFile
    public Long getFileTotalSize() {
        return this.fileTotalSize;
    }

    public void setFileTotalSize(Long l) {
        this.fileTotalSize = l;
    }

    public String getCurrentNo() {
        return this.currentNo;
    }

    public void setCurrentNo(String str) {
        this.currentNo = str;
    }

    @Override // com.gbcom.gwifi.functions.download.IDownloadFile
    public String getLocalFile() {
        return this.localFile;
    }

    public void setLocalFile(String str) {
        this.localFile = str;
    }

    public Long getParentId() {
        return this.parentId;
    }

    public void setParentId(Long l) {
        this.parentId = l;
    }

    public Boolean getIsTV() {
        return this.isTV;
    }

    public void setIsTV(Boolean bool) {
        this.isTV = bool;
    }

    public Integer getProductId() {
        return this.productId;
    }

    public void setProductId(Integer num) {
        this.productId = num;
    }

    public String getProductType() {
        return this.productType;
    }

    public void setProductType(String str) {
        this.productType = str;
    }

    @Override // com.gbcom.gwifi.functions.download.IDownloadFile
    public String getUrl() {
        return this.url;
    }

    @Override // com.gbcom.gwifi.functions.download.IDownloadFile
    public Integer getStateId() {
        return this.stateId;
    }

    public void setStateId(Integer num) {
        this.stateId = num;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public Long getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Long l) {
        this.createTime = l;
    }

    public Long getFinishTime() {
        return this.finishTime;
    }

    public void setFinishTime(Long l) {
        this.finishTime = l;
    }

    @Override // com.gbcom.gwifi.functions.download.IDownloadFile
    public Long getDownsize() {
        return this.downsize;
    }

    public void setDownsize(Long l) {
        this.downsize = l;
    }

    @Override // com.gbcom.gwifi.functions.download.IDownloadFile
    public String getTempFile() {
        return this.tempFile;
    }

    public void setTempFile(String str) {
        this.tempFile = str;
    }
}
