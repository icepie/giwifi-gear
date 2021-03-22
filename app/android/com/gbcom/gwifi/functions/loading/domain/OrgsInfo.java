package com.gbcom.gwifi.functions.loading.domain;

import java.p456io.Serializable;
import java.util.List;

public class OrgsInfo implements Serializable {
    private List<DataBean> data;
    private String message;
    private int response;

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public int getResponse() {
        return this.response;
    }

    public void setResponse(int i) {
        this.response = i;
    }

    public List<DataBean> getData() {
        return this.data;
    }

    public void setData(List<DataBean> list) {
        this.data = list;
    }

    public class DataBean implements Serializable {

        /* renamed from: id */
        private int f9881id;
        private String name;
        private int orgType;

        public DataBean() {
        }

        public int getId() {
            return this.f9881id;
        }

        public void setId(int i) {
            this.f9881id = i;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }

        public int getOrgType() {
            return this.orgType;
        }

        public void setOrgType(int i) {
            this.orgType = i;
        }
    }
}
