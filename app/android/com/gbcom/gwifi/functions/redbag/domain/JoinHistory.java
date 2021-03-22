package com.gbcom.gwifi.functions.redbag.domain;

import java.p456io.Serializable;

public class JoinHistory implements Serializable {
    private int accountId;
    private String createAt;
    private String nickName;

    public JoinHistory() {
    }

    public JoinHistory(int i, String str, String str2) {
        this.accountId = i;
        this.nickName = str;
        this.createAt = str2;
    }

    public int getAccountId() {
        return this.accountId;
    }

    public void setAccountId(int i) {
        this.accountId = i;
    }

    public String getNickName() {
        return this.nickName;
    }

    public void setNickName(String str) {
        this.nickName = str;
    }

    public String getCreateAt() {
        return this.createAt;
    }

    public void setCreateAt(String str) {
        this.createAt = str;
    }
}
