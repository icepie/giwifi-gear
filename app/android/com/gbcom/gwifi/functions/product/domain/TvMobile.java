package com.gbcom.gwifi.functions.product.domain;

import com.gbcom.gwifi.domain.BaseDomain;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class TvMobile extends BaseDomain {
    private static final long serialVersionUID = 1;
    @DatabaseField
    private String liveSourceUrls;
    @DatabaseField
    private String liveUrls;
    @DatabaseField
    private String logoUrl;
    @DatabaseField
    private String name;
    @DatabaseField
    private Integer needMember;
    @DatabaseField
    private Integer sid;

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public Integer getNeedMember() {
        return this.needMember;
    }

    public void setNeedMember(Integer num) {
        this.needMember = num;
    }

    public String getLogoUrl() {
        return this.logoUrl;
    }

    public void setLogoUrl(String str) {
        this.logoUrl = str;
    }

    public String getLiveUrls() {
        return this.liveUrls;
    }

    public void setLiveUrls(String str) {
        this.liveUrls = str;
    }

    public String getLiveSourceUrls() {
        return this.liveSourceUrls;
    }

    public void setLiveSourceUrls(String str) {
        this.liveSourceUrls = str;
    }

    public Integer getSid() {
        return this.sid;
    }

    public void setSid(Integer num) {
        this.sid = num;
    }
}
