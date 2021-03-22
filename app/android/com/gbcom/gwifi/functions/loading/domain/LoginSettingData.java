package com.gbcom.gwifi.functions.loading.domain;

import java.p456io.Serializable;

public class LoginSettingData implements Serializable {
    private DataBean data;
    private int resultCode;

    public DataBean getData() {
        return this.data;
    }

    public void setData(DataBean dataBean) {
        this.data = dataBean;
    }

    public int getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(int i) {
        this.resultCode = i;
    }

    public static class DataBean implements Serializable {
        private int app_login_default_mode;
        private int app_login_phone_state;
        private int app_login_sn_state;
        private int cloud_mode;

        public int getApp_login_phone_state() {
            return this.app_login_phone_state;
        }

        public void setApp_login_phone_state(int i) {
            this.app_login_phone_state = i;
        }

        public int getApp_login_sn_state() {
            return this.app_login_sn_state;
        }

        public void setApp_login_sn_state(int i) {
            this.app_login_sn_state = i;
        }

        public int getApp_login_default_mode() {
            return this.app_login_default_mode;
        }

        public void setApp_login_default_mode(int i) {
            this.app_login_default_mode = i;
        }

        public int getCloud_mode() {
            return this.cloud_mode;
        }

        public void setCloud_mode(int i) {
            this.cloud_mode = i;
        }
    }
}
