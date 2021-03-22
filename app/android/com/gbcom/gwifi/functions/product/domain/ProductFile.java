package com.gbcom.gwifi.functions.product.domain;

import com.gbcom.gwifi.domain.BaseDomain;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class ProductFile extends BaseDomain {
    private static final long serialVersionUID = 1;
    @DatabaseField
    private Long fileTotalSize;
    @DatabaseField
    private Integer productId;
    @DatabaseField
    private String tags;
    @DatabaseField
    private String title;
    @DatabaseField
    private String url;

    public Long getFileTotalSize() {
        return this.fileTotalSize;
    }

    public void setFileTotalSize(Long l) {
        this.fileTotalSize = l;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public Integer getProductId() {
        return this.productId;
    }

    public void setProductId(Integer num) {
        this.productId = num;
    }

    public String getTags() {
        return this.tags;
    }

    public void setTags(String str) {
        this.tags = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }
}
