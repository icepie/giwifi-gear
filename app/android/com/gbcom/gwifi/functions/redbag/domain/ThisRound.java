package com.gbcom.gwifi.functions.redbag.domain;

import java.util.ArrayList;

public class ThisRound {
    private int balance;
    private int hasSale;
    private ArrayList<HitRecord> hitRecord;
    private int isJoin;
    private ArrayList<JoinHistory> joinHistory;
    private String roundNo;
    private int status;
    private int totalCount;

    public String getRoundNo() {
        return this.roundNo;
    }

    public void setRoundNo(String str) {
        this.roundNo = str;
    }

    public int getIsJoin() {
        return this.isJoin;
    }

    public void setIsJoin(int i) {
        this.isJoin = i;
    }

    public int getHasSale() {
        return this.hasSale;
    }

    public void setHasSale(int i) {
        this.hasSale = i;
    }

    public int getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(int i) {
        this.totalCount = i;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public int getBalance() {
        return this.balance;
    }

    public void setBalance(int i) {
        this.balance = i;
    }

    public ArrayList<JoinHistory> getJoinHistory() {
        return this.joinHistory;
    }

    public void setJoinHistory(ArrayList<JoinHistory> arrayList) {
        this.joinHistory = arrayList;
    }

    public ArrayList<HitRecord> getHitRecord() {
        return this.hitRecord;
    }

    public void setHitRecord(ArrayList<HitRecord> arrayList) {
        this.hitRecord = arrayList;
    }
}
