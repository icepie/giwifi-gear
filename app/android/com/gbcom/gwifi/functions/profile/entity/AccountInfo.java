package com.gbcom.gwifi.functions.profile.entity;

import java.util.HashMap;

public class AccountInfo {
    private String avatarUrl;
    private String balance;
    private int collegeId;
    private String email;
    private String genderName;
    private int hotspotGroupId;
    private String identityName;
    private int identityType;
    private String lanRemainTime;
    private String nickName;
    private String remainTime;
    private HashMap<String, Object> subData;
    private String totalScore;
    private int userLevel;
    private String userLevelName;
    private int userType;

    public int getHotspotGroupId() {
        return this.hotspotGroupId;
    }

    public void setHotspotGroupId(int i) {
        this.hotspotGroupId = i;
    }

    public int getCollegeId() {
        return this.collegeId;
    }

    public void setCollegeId(int i) {
        this.collegeId = i;
    }

    public int getIdentityType() {
        return this.identityType;
    }

    public void setIdentityType(int i) {
        this.identityType = i;
    }

    public int getUserType() {
        return this.userType;
    }

    public void setUserType(int i) {
        this.userType = i;
    }

    public int getUserLevel() {
        return this.userLevel;
    }

    public void setUserLevel(int i) {
        this.userLevel = i;
    }

    public String getUserLevelName() {
        return this.userLevelName;
    }

    public void setUserLevelName(String str) {
        this.userLevelName = str;
    }

    public String getAvatarUrl() {
        return this.avatarUrl;
    }

    public void setAvatarUrl(String str) {
        this.avatarUrl = str;
    }

    public String getNickName() {
        return this.nickName;
    }

    public void setNickName(String str) {
        this.nickName = str;
    }

    public String getIdentityName() {
        return this.identityName;
    }

    public void setIdentityName(String str) {
        this.identityName = str;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String str) {
        this.email = str;
    }

    public String getGenderName() {
        return this.genderName;
    }

    public void setGenderName(String str) {
        this.genderName = str;
    }

    public String getBalance() {
        return this.balance;
    }

    public void setBalance(String str) {
        this.balance = str;
    }

    public String getRemainTime() {
        return this.remainTime;
    }

    public void setRemainTime(String str) {
        this.remainTime = str;
    }

    public String getLanRemainTime() {
        return this.lanRemainTime;
    }

    public void setLanRemainTime(String str) {
        this.lanRemainTime = str;
    }

    public String getTotalScore() {
        return this.totalScore;
    }

    public void setTotalScore(String str) {
        this.totalScore = str;
    }

    public HashMap<String, Object> getSubData() {
        return this.subData;
    }

    public void setSubData(HashMap<String, Object> hashMap) {
        this.subData = hashMap;
    }
}
