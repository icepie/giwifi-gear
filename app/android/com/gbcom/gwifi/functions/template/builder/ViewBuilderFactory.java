package com.gbcom.gwifi.functions.template.builder;

import java.util.HashMap;

/* renamed from: com.gbcom.gwifi.functions.template.builder.ah */
public class ViewBuilderFactory {

    /* renamed from: A */
    public static final String f11712A = "2026";

    /* renamed from: B */
    public static final String f11713B = "2027";

    /* renamed from: C */
    public static final String f11714C = "2028";

    /* renamed from: D */
    public static final String f11715D = "2029";

    /* renamed from: E */
    public static final String f11716E = "2030";

    /* renamed from: F */
    public static final String f11717F = "2031";

    /* renamed from: G */
    private static int f11718G = 0;

    /* renamed from: H */
    private static HashMap<String, ViewBuilder> f11719H = new HashMap<>();

    /* renamed from: a */
    public static final String f11720a = "1001";

    /* renamed from: b */
    public static final String f11721b = "1002";

    /* renamed from: c */
    public static final String f11722c = "2001";

    /* renamed from: d */
    public static final String f11723d = "2003";

    /* renamed from: e */
    public static final String f11724e = "2004";

    /* renamed from: f */
    public static final String f11725f = "2005";

    /* renamed from: g */
    public static final String f11726g = "2006";

    /* renamed from: h */
    public static final String f11727h = "2007";

    /* renamed from: i */
    public static final String f11728i = "2008";

    /* renamed from: j */
    public static final String f11729j = "2009";

    /* renamed from: k */
    public static final String f11730k = "2010";

    /* renamed from: l */
    public static final String f11731l = "2011";

    /* renamed from: m */
    public static final String f11732m = "2012";

    /* renamed from: n */
    public static final String f11733n = "2013";

    /* renamed from: o */
    public static final String f11734o = "2014";

    /* renamed from: p */
    public static final String f11735p = "2015";

    /* renamed from: q */
    public static final String f11736q = "2016";

    /* renamed from: r */
    public static final String f11737r = "2017";

    /* renamed from: s */
    public static final String f11738s = "2018";

    /* renamed from: t */
    public static final String f11739t = "2019";

    /* renamed from: u */
    public static final String f11740u = "2020";

    /* renamed from: v */
    public static final String f11741v = "2021";

    /* renamed from: w */
    public static final String f11742w = "2022";

    /* renamed from: x */
    public static final String f11743x = "2023";

    /* renamed from: y */
    public static final String f11744y = "2024";

    /* renamed from: z */
    public static final String f11745z = "2025";

    /* renamed from: a */
    public static ViewBuilder m12847a(String str, int i) {
        f11718G = i;
        return m12846a(str);
    }

    /* renamed from: a */
    public static ViewBuilder m12846a(String str) {
        ViewBuilder agVar;
        if (f11718G == 0) {
            agVar = f11719H.get(str);
        } else {
            agVar = f11719H.get(str + f11718G);
        }
        if (agVar == null) {
            if (str.equals(f11720a)) {
                agVar = new VerticalLinearLayoutBuilder();
            } else if (str.equals(f11721b)) {
                agVar = new X5WebViewBuilder();
            } else if (str.equals(f11722c)) {
                agVar = new ImageBuilder();
            } else if (!str.equals("2002")) {
                if (str.equals(f11723d)) {
                    agVar = new HScrollBuilder();
                } else if (str.equals(f11724e)) {
                    agVar = new LeftOneRightTowBuilder();
                } else if (str.equals(f11725f)) {
                    agVar = new TwoColumnGiBuilder();
                } else if (str.equals(f11726g)) {
                    agVar = new ThreeColumnWithBorderBuilder();
                } else if (str.equals(f11727h)) {
                    agVar = new FourColumnGiBuilder();
                } else if (str.equals(f11728i)) {
                    agVar = new FourColumnNoBorderBuilder();
                } else if (str.equals(f11729j)) {
                    agVar = new ImageRotationWithTitleBuilder();
                } else if (str.equals(f11730k)) {
                    agVar = new ImageRotationNoTitleBuilder();
                } else if (str.equals(f11731l)) {
                    agVar = new VideoBuilder();
                } else if (str.equals(f11732m)) {
                    agVar = new AppBuilder();
                } else if (str.equals(f11733n)) {
                    agVar = new AppBuilder();
                } else if (str.equals(f11734o)) {
                    agVar = new ScrollTextBuilder();
                } else if (str.equals(f11735p)) {
                    agVar = new LeftOneRightTowCommendBuilder();
                } else if (str.equals(f11736q)) {
                    agVar = new BannerBuilder(f11718G);
                } else if (str.equals(f11737r)) {
                    agVar = new TopOneBottomScrollBuilder();
                } else if (str.equals(f11738s)) {
                    agVar = new LeftOneRightTowVideoBuilder();
                } else if (str.equals(f11739t)) {
                    agVar = new NewHScrollBuilder();
                } else if (str.equals(f11740u)) {
                    agVar = new TwoColumnWithBorderVideoBuilder();
                } else if (!str.equals(f11741v) && !str.equals(f11742w)) {
                    if (str.equals(f11743x)) {
                        agVar = new MessageBuilder();
                    } else if (str.equals(f11744y)) {
                        agVar = new ZhongGuangCommendBuilder();
                    } else if (str.equals(f11745z)) {
                        agVar = new SchoolNotifyBuilder();
                    } else if (str.equals(f11712A)) {
                        agVar = new EduModuleBuilder();
                    } else if (str.equals(f11713B)) {
                        agVar = new SchoolNewsBuilder();
                    } else if (str.equals(f11714C)) {
                        agVar = new AdLayoutGiBuilder();
                    } else if (str.equals(f11715D)) {
                        agVar = new ThreeColumnGibuilder();
                    } else if (!str.equals("3002") && !str.equals("5002") && !str.equals(f11716E) && str.equals(f11717F)) {
                        agVar = new WeatherBuilder();
                    }
                }
            }
            if (agVar != null) {
                if (f11718G == 0) {
                    f11719H.put(str, agVar);
                } else {
                    f11719H.put(str + f11718G, agVar);
                }
            }
        }
        return agVar;
    }
}
