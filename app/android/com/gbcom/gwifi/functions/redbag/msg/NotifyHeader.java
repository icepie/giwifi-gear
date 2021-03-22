package com.gbcom.gwifi.functions.redbag.msg;

import java.p456io.Serializable;

public class NotifyHeader implements Serializable {
    protected short command;

    public short getCommand() {
        return this.command;
    }

    public void setCommand(short s) {
        this.command = s;
    }
}
