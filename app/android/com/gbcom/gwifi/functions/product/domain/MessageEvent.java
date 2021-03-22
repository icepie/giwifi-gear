package com.gbcom.gwifi.functions.product.domain;

import com.gbcom.gwifi.functions.product.domain.RecommendAppListResponse;

public class MessageEvent {
    private RecommendAppListResponse.DataBean.AppListBean appListBean;

    public MessageEvent(RecommendAppListResponse.DataBean.AppListBean appListBean2) {
        this.appListBean = appListBean2;
    }

    public RecommendAppListResponse.DataBean.AppListBean getAppListBean() {
        return this.appListBean;
    }

    public void setAppListBean(RecommendAppListResponse.DataBean.AppListBean appListBean2) {
        this.appListBean = appListBean2;
    }
}
