package com.gbcom.gwifi.functions.revenue.domain;

import java.util.List;

public class SingleRedbagResult {
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

    public static class DataBean {
        private List<Integer> candidateList;
        private int deductBeans;
        private int finalBeans;
        private int hitBeans;

        public int getDeductBeans() {
            return this.deductBeans;
        }

        public void setDeductBeans(int i) {
            this.deductBeans = i;
        }

        public int getFinalBeans() {
            return this.finalBeans;
        }

        public void setFinalBeans(int i) {
            this.finalBeans = i;
        }

        public int getHitBeans() {
            return this.hitBeans;
        }

        public void setHitBeans(int i) {
            this.hitBeans = i;
        }

        public List<Integer> getCandidateList() {
            return this.candidateList;
        }

        public void setCandidateList(List<Integer> list) {
            this.candidateList = list;
        }
    }
}
