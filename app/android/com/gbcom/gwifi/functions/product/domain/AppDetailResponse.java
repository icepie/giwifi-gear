package com.gbcom.gwifi.functions.product.domain;

import java.util.List;

public class AppDetailResponse {
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
        private String apkMd5;
        private int apkPublishTime;
        private String apkUrl;
        private long appDownCount;
        private String appName;
        private String authorName;
        private CategoryInfoBean categoryInfo;
        private String description;
        private String fileSize;
        private int hasFinished;
        private String iconUrl;
        private int minSdkVersion;
        private String newFeature;
        private String pkgName;
        private int pointsReward;
        private int productId;
        private RatingInfoBean ratingInfo;
        private String reportClickUrl;
        private String reportDownloadUrl;
        private String reportExposureUrl;
        private String reportInstallUrl;
        private List<ScreenshotsBean> screenshots;
        private int targetSdkVersion;
        private int versionCode;
        private String versionName;

        public RatingInfoBean getRatingInfo() {
            return this.ratingInfo;
        }

        public void setRatingInfo(RatingInfoBean ratingInfoBean) {
            this.ratingInfo = ratingInfoBean;
        }

        public CategoryInfoBean getCategoryInfo() {
            return this.categoryInfo;
        }

        public void setCategoryInfo(CategoryInfoBean categoryInfoBean) {
            this.categoryInfo = categoryInfoBean;
        }

        public String getPkgName() {
            return this.pkgName;
        }

        public void setPkgName(String str) {
            this.pkgName = str;
        }

        public String getReportDownloadUrl() {
            return this.reportDownloadUrl;
        }

        public void setReportDownloadUrl(String str) {
            this.reportDownloadUrl = str;
        }

        public String getIconUrl() {
            return this.iconUrl;
        }

        public void setIconUrl(String str) {
            this.iconUrl = str;
        }

        public int getApkPublishTime() {
            return this.apkPublishTime;
        }

        public void setApkPublishTime(int i) {
            this.apkPublishTime = i;
        }

        public String getNewFeature() {
            return this.newFeature;
        }

        public void setNewFeature(String str) {
            this.newFeature = str;
        }

        public int getPointsReward() {
            return this.pointsReward;
        }

        public void setPointsReward(int i) {
            this.pointsReward = i;
        }

        public String getApkUrl() {
            return this.apkUrl;
        }

        public void setApkUrl(String str) {
            this.apkUrl = str;
        }

        public int getVersionCode() {
            return this.versionCode;
        }

        public void setVersionCode(int i) {
            this.versionCode = i;
        }

        public String getReportClickUrl() {
            return this.reportClickUrl;
        }

        public void setReportClickUrl(String str) {
            this.reportClickUrl = str;
        }

        public String getVersionName() {
            return this.versionName;
        }

        public void setVersionName(String str) {
            this.versionName = str;
        }

        public int getProductId() {
            return this.productId;
        }

        public void setProductId(int i) {
            this.productId = i;
        }

        public String getAppName() {
            return this.appName;
        }

        public void setAppName(String str) {
            this.appName = str;
        }

        public String getFileSize() {
            return this.fileSize;
        }

        public void setFileSize(String str) {
            this.fileSize = str;
        }

        public int getMinSdkVersion() {
            return this.minSdkVersion;
        }

        public void setMinSdkVersion(int i) {
            this.minSdkVersion = i;
        }

        public String getReportInstallUrl() {
            return this.reportInstallUrl;
        }

        public void setReportInstallUrl(String str) {
            this.reportInstallUrl = str;
        }

        public int getTargetSdkVersion() {
            return this.targetSdkVersion;
        }

        public void setTargetSdkVersion(int i) {
            this.targetSdkVersion = i;
        }

        public String getDescription() {
            return this.description;
        }

        public void setDescription(String str) {
            this.description = str;
        }

        public String getAuthorName() {
            return this.authorName;
        }

        public void setAuthorName(String str) {
            this.authorName = str;
        }

        public String getReportExposureUrl() {
            return this.reportExposureUrl;
        }

        public void setReportExposureUrl(String str) {
            this.reportExposureUrl = str;
        }

        public String getApkMd5() {
            return this.apkMd5;
        }

        public void setApkMd5(String str) {
            this.apkMd5 = str;
        }

        public int getHasFinished() {
            return this.hasFinished;
        }

        public void setHasFinished(int i) {
            this.hasFinished = i;
        }

        public long getAppDownCount() {
            return this.appDownCount;
        }

        public void setAppDownCount(long j) {
            this.appDownCount = j;
        }

        public List<ScreenshotsBean> getScreenshots() {
            return this.screenshots;
        }

        public void setScreenshots(List<ScreenshotsBean> list) {
            this.screenshots = list;
        }

        public static class RatingInfoBean {
            private double averageRating;
            private int ratingCount;

            public double getAverageRating() {
                return this.averageRating;
            }

            public void setAverageRating(double d) {
                this.averageRating = d;
            }

            public int getRatingCount() {
                return this.ratingCount;
            }

            public void setRatingCount(int i) {
                this.ratingCount = i;
            }
        }

        public static class CategoryInfoBean {
            private String categoryId;
            private String categoryName;

            public String getCategoryName() {
                return this.categoryName;
            }

            public void setCategoryName(String str) {
                this.categoryName = str;
            }

            public String getCategoryId() {
                return this.categoryId;
            }

            public void setCategoryId(String str) {
                this.categoryId = str;
            }
        }

        public static class ScreenshotsBean {
            private String originalUrl;

            public String getOriginalUrl() {
                return this.originalUrl;
            }

            public void setOriginalUrl(String str) {
                this.originalUrl = str;
            }
        }
    }
}
