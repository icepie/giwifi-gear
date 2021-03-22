package com.gbcom.gwifi.functions.redbag.domain;

import android.icu.impl.PatternTokenizer;
import java.p456io.Serializable;
import java.util.ArrayList;

public class LastRound implements Serializable {
    private ArrayList<HitRecord> hitRecord;
    private int isJoin;
    private ArrayList<JoinHistory> joinHistory;
    private String roundNo;

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

    public String toString() {
        return "LastRound{roundNo='" + this.roundNo + PatternTokenizer.SINGLE_QUOTE + ", isJoin=" + this.isJoin + ", joinHistory=" + ((Object) this.joinHistory) + ", hitRecord=" + ((Object) this.hitRecord) + '}';
    }
}
