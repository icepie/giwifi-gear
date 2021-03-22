package com.gbcom.gwifi.functions.product.p249c;

/* renamed from: com.gbcom.gwifi.functions.product.c.a */
public enum ProductType {
    video(1),
    music(2),
    app(3),
    book(4),
    game(5);
    

    /* renamed from: f */
    private int f10803f = 0;

    private ProductType(int i) {
        this.f10803f = i;
    }

    /* renamed from: a */
    public static ProductType m12209a(int i) {
        switch (i) {
            case 1:
                return video;
            case 2:
                return music;
            case 3:
                return app;
            case 4:
                return book;
            case 5:
                return game;
            default:
                return null;
        }
    }

    /* renamed from: a */
    public int mo25572a() {
        return this.f10803f;
    }
}
