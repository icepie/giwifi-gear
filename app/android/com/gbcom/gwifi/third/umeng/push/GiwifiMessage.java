package com.gbcom.gwifi.third.umeng.push;

public class GiwifiMessage {
    public String custom;
    public String text;
    public String title;

    public GiwifiMessage() {
    }

    public GiwifiMessage(String str, String str2, String str3) {
        this.title = str;
        this.text = str2;
        this.custom = str3;
    }
}
