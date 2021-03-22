package com.gbcom.gwifi.functions.product.domain;

import java.p456io.Serializable;
import java.util.List;

public class RecommendAppListResponse implements Serializable {
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
        private List<AppListBean> appList;

        public List<AppListBean> getAppList() {
            return this.appList;
        }

        public void setAppList(List<AppListBean> list) {
            this.appList = list;
        }

        public static class AppListBean implements Serializable {
            private String absolutePath;
            private String apkMd5;
            private String apkUrl;
            private long appDownCount;
            private String appName;
            private CategoryInfoBean categoryInfo;
            private String filePath;
            private String fileSize;
            private int hasFinished;
            private String iconUrl;
            private String pkgName;
            private int pointsReward;
            private int productId;
            private long prograss;
            private String reportClickUrl;
            private String reportDownloadUrl;
            private String reportExposureUrl;
            private String reportInstallUrl;
            private int state = 1;

            public String getAbsolutePath() {
                return this.absolutePath;
            }

            public void setAbsolutePath(String str) {
                this.absolutePath = str;
            }

            public long getPrograss() {
                return this.prograss;
            }

            public void setPrograss(long j) {
                this.prograss = j;
            }

            public String getFilePath() {
                return this.filePath;
            }

            public void setFilePath(String str) {
                this.filePath = str;
            }

            public int getState() {
                return this.state;
            }

            public void setState(int i) {
                this.state = i;
            }

            public String getApkMd5() {
                return this.apkMd5;
            }

            public void setApkMd5(String str) {
                this.apkMd5 = str;
            }

            public String getApkUrl() {
                return this.apkUrl;
            }

            public void setApkUrl(String str) {
                this.apkUrl = str;
            }

            public long getAppDownCount() {
                return this.appDownCount;
            }

            public void setAppDownCount(long j) {
                this.appDownCount = j;
            }

            public String getAppName() {
                return this.appName;
            }

            public void setAppName(String str) {
                this.appName = str;
            }

            public CategoryInfoBean getCategoryInfo() {
                return this.categoryInfo;
            }

            public void setCategoryInfo(CategoryInfoBean categoryInfoBean) {
                this.categoryInfo = categoryInfoBean;
            }

            public String getFileSize() {
                return this.fileSize;
            }

            public void setFileSize(String str) {
                this.fileSize = str;
            }

            public int getHasFinished() {
                return this.hasFinished;
            }

            public void setHasFinished(int i) {
                this.hasFinished = i;
            }

            public String getIconUrl() {
                return this.iconUrl;
            }

            public void setIconUrl(String str) {
                this.iconUrl = str;
            }

            public String getPkgName() {
                return this.pkgName;
            }

            public void setPkgName(String str) {
                this.pkgName = str;
            }

            public int getPointsReward() {
                return this.pointsReward;
            }

            public void setPointsReward(int i) {
                this.pointsReward = i;
            }

            public int getProductId() {
                return this.productId;
            }

            public void setProductId(int i) {
                this.productId = i;
            }

            public String getReportClickUrl() {
                return this.reportClickUrl;
            }

            public void setReportClickUrl(String str) {
                this.reportClickUrl = str;
            }

            public String getReportDownloadUrl() {
                return this.reportDownloadUrl;
            }

            public void setReportDownloadUrl(String str) {
                this.reportDownloadUrl = str;
            }

            public String getReportExposureUrl() {
                return this.reportExposureUrl;
            }

            public void setReportExposureUrl(String str) {
                this.reportExposureUrl = str;
            }

            public String getReportInstallUrl() {
                return this.reportInstallUrl;
            }

            public void setReportInstallUrl(String str) {
                this.reportInstallUrl = str;
            }

            public static class CategoryInfoBean implements Serializable {
                private String categoryId;
                private String categoryName;

                public String getCategoryId() {
                    return this.categoryId;
                }

                public void setCategoryId(String str) {
                    this.categoryId = str;
                }

                public String getCategoryName() {
                    return this.categoryName;
                }

                public void setCategoryName(String str) {
                    this.categoryName = str;
                }
            }
        }
    }
}
