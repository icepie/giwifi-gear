package com.gbcom.gwifi.functions.product.domain;

import com.gbcom.gwifi.domain.BaseDomain;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class PlayHistory extends BaseDomain {
    private static final long serialVersionUID = 1;
    @DatabaseField
    private String TempFileUrls;
    @DatabaseField
    private Long createTime;
    @DatabaseField
    private Integer currentNo;
    @DatabaseField
    private Long lastPlayTime;
    @DatabaseField
    private Integer lastPosition;
    @DatabaseField
    private Integer productId;
    @DatabaseField(dataType = DataType.BYTE_ARRAY)
    private byte[] sourceIconIds;
    @DatabaseField
    private String sourceTitles;
    @DatabaseField
    private String sourceUrls;
    @DatabaseField
    private String title;
    @DatabaseField
    private String totalNos;
    @DatabaseField
    private Integer type;
    @DatabaseField
    private String videoType;

    public String getTempFileUrls() {
        return this.TempFileUrls;
    }

    public void setTempFileUrls(String str) {
        this.TempFileUrls = str;
    }

    public String getTotalNos() {
        return this.totalNos;
    }

    public void setTotalNos(String str) {
        this.totalNos = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public byte[] getSourceIconIds() {
        return this.sourceIconIds;
    }

    public void setSourceIconIds(byte[] bArr) {
        this.sourceIconIds = bArr;
    }

    public Integer getCurrentNo() {
        return this.currentNo;
    }

    public void setCurrentNo(Integer num) {
        this.currentNo = num;
    }

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer num) {
        this.type = num;
    }

    public String getSourceUrls() {
        return this.sourceUrls;
    }

    public void setSourceUrls(String str) {
        this.sourceUrls = str;
    }

    public String getSourceTitles() {
        return this.sourceTitles;
    }

    public void setSourceTitles(String str) {
        this.sourceTitles = str;
    }

    public Integer getLastPosition() {
        return this.lastPosition;
    }

    public void setLastPosition(Integer num) {
        this.lastPosition = num;
    }

    public String getVideoType() {
        return this.videoType;
    }

    public void setVideoType(String str) {
        this.videoType = str;
    }

    public Long getLastPlayTime() {
        return this.lastPlayTime;
    }

    public void setLastPlayTime(Long l) {
        this.lastPlayTime = l;
    }

    public Long getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Long l) {
        this.createTime = l;
    }

    public Integer getProductId() {
        return this.productId;
    }

    public void setProductId(Integer num) {
        this.productId = num;
    }
}
