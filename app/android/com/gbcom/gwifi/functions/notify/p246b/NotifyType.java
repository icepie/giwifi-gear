package com.gbcom.gwifi.functions.notify.p246b;

/* renamed from: com.gbcom.gwifi.functions.notify.b.a */
public enum NotifyType {
    text(1),
    wap(2),
    dialup(3),
    versionUpdate(4),
    productRecommend(5),
    charge(6),
    contentUpdate(7),
    authOnline(8),
    pointChange(9),
    userOffline(10);
    

    /* renamed from: k */
    private int f9945k = 0;

    private NotifyType(int i) {
        this.f9945k = i;
    }

    /* renamed from: a */
    public static NotifyType m11499a(int i) {
        switch (i) {
            case 1:
                return text;
            case 2:
                return wap;
            case 3:
                return dialup;
            case 4:
                return versionUpdate;
            case 5:
                return productRecommend;
            case 6:
                return charge;
            case 7:
                return contentUpdate;
            case 8:
                return authOnline;
            case 9:
                return pointChange;
            case 10:
                return userOffline;
            default:
                return null;
        }
    }

    /* renamed from: a */
    public int mo25232a() {
        return this.f9945k;
    }
}
