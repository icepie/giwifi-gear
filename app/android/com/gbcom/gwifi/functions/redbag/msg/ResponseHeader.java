package com.gbcom.gwifi.functions.redbag.msg;

import java.p456io.Serializable;

public class ResponseHeader implements Serializable {
    protected short command;
    protected String errorMessage;
    protected int sequenceId = 0;
    protected int status = 0;

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public int getSequenceId() {
        return this.sequenceId;
    }

    public void setSequenceId(int i) {
        this.sequenceId = i;
    }

    public short getCommand() {
        return this.command;
    }

    public void setCommand(short s) {
        this.command = s;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void setErrorMessage(String str) {
        this.errorMessage = str;
    }
}
