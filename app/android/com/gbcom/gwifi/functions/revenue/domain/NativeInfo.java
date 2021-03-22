package com.gbcom.gwifi.functions.revenue.domain;

import com.gbcom.gwifi.domain.BaseDomain;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class NativeInfo extends BaseDomain {
    private static final long serialVersionUID = 1;
    @DatabaseField
    private String iconUrl;
    @DatabaseField
    private boolean isRead;
    @DatabaseField
    private String subTitle;
    @DatabaseField
    private String title;
    @DatabaseField
    private int type;
    @DatabaseField
    private Long updateTime;
    @DatabaseField
    private String webUrl;

    public boolean isRead() {
        return this.isRead;
    }

    public void setRead(boolean z) {
        this.isRead = z;
    }

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

    public String getIconUrl() {
        return this.iconUrl;
    }

    public void setIconUrl(String str) {
        this.iconUrl = str;
    }

    public String getWebUrl() {
        return this.webUrl;
    }

    public void setWebUrl(String str) {
        this.webUrl = str;
    }

    public Long getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Long l) {
        this.updateTime = l;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }
}
