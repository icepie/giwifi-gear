package com.gbcom.gwifi.functions.redbag.domain;

public class PrizePoolSetting {
    private int chance;
    private int number;
    private int odds;
    private String reward;

    public PrizePoolSetting() {
    }

    public PrizePoolSetting(int i, int i2, int i3, String str) {
        this.number = i;
        this.odds = i2;
        this.chance = i3;
        this.reward = str;
    }

    public int getNumber() {
        return this.number;
    }

    public void setNumber(int i) {
        this.number = i;
    }

    public int getOdds() {
        return this.odds;
    }

    public void setOdds(int i) {
        this.odds = i;
    }

    public int getChance() {
        return this.chance;
    }

    public void setChance(int i) {
        this.chance = i;
    }

    public String getReward() {
        return this.reward;
    }

    public void setReward(String str) {
        this.reward = str;
    }
}
