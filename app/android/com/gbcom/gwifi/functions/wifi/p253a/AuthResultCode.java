package com.gbcom.gwifi.functions.wifi.p253a;

import java.util.HashMap;
import java.util.Map;

/* renamed from: com.gbcom.gwifi.functions.wifi.a.a */
public enum AuthResultCode {
    WO_ERRCODE_OK(0),
    WO_ERRCODE_INVALID_PARAMETER(1),
    WO_ERRCODE_INVALID_GW_ID(2),
    WO_ERRCODE_DB_OPERATION_ERROR(3),
    WO_ERRCODE_PHONE_BLACKLIST(4),
    WO_ERRCODE_FORMALMODE_TEMP_REGISTER_FORBIDDEN(5),
    WO_ERRCODE_REPEAT_REGISTER_FORBIDDEN(6),
    WO_ERRCODE_PHONE_MAC_BINDING(7),
    WO_ERRCODE_PHONE_LOCKED(8),
    WO_ERRCODE_FORMALMODE_TEMP_USE_FORBIDDEN(9),
    WO_ERRCODE_PHONE_ONLY_PHONE(10),
    WO_ERRCODE_SERVICEPLAN_PERIOD_FULL(11),
    WO_ERRCODE_SERVICEPLAN_CURR_HOURS_FULL(12),
    WO_ERRCODE_SERVICEPLAN_HOURS_FULL(13),
    WO_ERRCODE_SMS_SEND_TIMES_FULL(14),
    WO_ERRCODE_SMS_SEND_FAIL(15),
    WO_ERRCODE_USER_NOT_EXIST(16),
    WO_ERRCODE_PHONE_CODE_WRONG(17),
    WO_ERRCODE_PHONE_CODE_EXPIRED(18),
    WO_ERRCODE_INVALID_OWNER_ID(19),
    WO_ERRCODE_INVALID_TOKEN(20),
    WO_ERRCODE_USER_STATUS_ERROR(21),
    WO_ERRCODE_INVALID_SERVICEPLAN(22),
    WO_ERRCODE_APP_SESSION_NOT_EXIST(23),
    WO_ERRCODE_MUTI_USER_LOGIN(24),
    WO_ERRCODE_MUTI_USER_TRIAL(25),
    WO_ERRCODE_USER_HAS_EXIST(26),
    WO_ERRCODE_STATIC_PASSWORD_MATCH(27),
    WO_ERRCODE_USER_ONLINE(28),
    WO_ERRCODE_MAC_SMS_SEND_TIMES_FULL(29),
    WO_ERRCODE_PHONE_HAS_BEEN_REGISTER(30),
    WO_ERRCODE_ONLIEN_TEST_NUM_TO_MORE(31),
    WO_ERRCODE_PHONE_PASSWORD_EMPTY(32),
    WO_ERRCODE_ACCOUNT_MUST_BE_ACTIVE(33),
    WO_ERRCODE_SERVICEPLAN_NOT_EFFECT(34),
    WO_ERRCODE_APP_OUTDATED_VERSION(35),
    WO_ERRCODE_ACCOUNT_MUST_COMPLETE_DATA(40),
    WO_ERRCODE_ACCOUNT_MUST_BIND_PHONE(41),
    WO_ERRCODE_NAME_USER_NOT_EXIST(42),
    WO_ERRCODE_PHONE_NEED_MAC_BINDING(43),
    WO_ERRCODE_PHONE_NEED_WECHART_AUTH(44),
    WO_ERRCODE_NOT_LOCATION_AUTH(45),
    WO_ERRCODE_NETWORK_TYPE_ERROR(46),
    WO_ERRCODE_TRY_TOO_OFTEN(47),
    WO_ERRCODE_ACCOUNT_HAS_BIND_PHONE(51),
    WO_ERRCODE_ACCOUNT_PWD_NOTMATCH_POLICY(52),
    WO_ERRCODE_ACCOUNT_ERROR(53),
    WO_ERRCODE_ACCOUNT_NEED_SWITCH(54),
    WO_ERRCODE_ANTI_PROXY_ERROR(55),
    WO_ERRCODE_AAA_AUTH_FAIL(60),
    WO_ERRCODE_AAA_AUTH_ACCOUNT_NOTFOUND(61),
    WO_ERRCODE_AAA_AUTH_PWD_NULL(62),
    WO_ERRCODE_AAA_AUTH_PWD_WRONG(63),
    WO_ERRCODE_AAA_AUTH_ACCOUNT_WRONG(64),
    WO_ERRCODE_AAA_AUTH_ACCOUNT_LOCK(65),
    WO_ERRCODE_AAA_AUTH_LIMIT_EXCEED(66),
    WO_ERRCODE_AAA_AUTH_SERVICE_NOT_AVAILABLE(67),
    WO_ERRCODE_AAA_AUTH_SERVICE_TYPE_ERROR(68),
    WO_ERRCODE_NEED_RELOGIN(69),
    WO_ERRCODE_NEED_IDENTITY_RELOGIN(70),
    WO_ERRCODE_NAME_USER_OR_PASSWORD_IS_WRONG(71),
    WO_ERRCODE_TIME_AUTH_RULE_FORBID(72),
    WO_ERRCODE_HOTSPOT_CHANNEL_OFF(73),
    WO_ERRCODE_USER_SERVICEPLAN_IS_TRIAL(74),
    WO_ERRCODE_USER_HAVE_NOT_CHANNEL_SERVICEPLAN(75),
    WO_ERRCODE_USER_HAVE_NOT_CHANNEL_ADDRESS(76),
    WO_ERRCODE_USER_ADDRESS_IS_ERROR(77),
    WO_ERRCODE_USER_HAVE_NOT_THIE_SERVICE_PLAN(78),
    WO_ERRCODE_PPPOE_FAIL(81),
    WO_ERRCODE_PPPOE_TIMEOUT(82),
    WO_ERRCODE_PORTAL_FAIL(91),
    WO_ERRCODE_PORTAL_TIMEOUT(92);
    

    /* renamed from: av */
    private static final Map<Integer, String> f12896av = new HashMap<Integer, String>() {
        /* class com.gbcom.gwifi.functions.wifi.p253a.AuthResultCode.C33911 */

        {
            put(Integer.valueOf(AuthResultCode.WO_ERRCODE_OK.mo27459a()), "成功");
            put(Integer.valueOf(AuthResultCode.WO_ERRCODE_INVALID_PARAMETER.mo27459a()), "参数非法");
            put(Integer.valueOf(AuthResultCode.WO_ERRCODE_INVALID_GW_ID.mo27459a()), "热点不存在");
            put(Integer.valueOf(AuthResultCode.WO_ERRCODE_DB_OPERATION_ERROR.mo27459a()), "数据库操作错误");
            put(Integer.valueOf(AuthResultCode.WO_ERRCODE_PHONE_BLACKLIST.mo27459a()), "该手机号被禁用服务");
            put(Integer.valueOf(AuthResultCode.WO_ERRCODE_FORMALMODE_TEMP_REGISTER_FORBIDDEN.mo27459a()), "正式模式不允许注册试用用户");
            put(Integer.valueOf(AuthResultCode.WO_ERRCODE_REPEAT_REGISTER_FORBIDDEN.mo27459a()), "不允许重复注册试用");
            put(Integer.valueOf(AuthResultCode.WO_ERRCODE_PHONE_MAC_BINDING.mo27459a()), "该手机号已绑定其他终端");
            put(Integer.valueOf(AuthResultCode.WO_ERRCODE_PHONE_LOCKED.mo27459a()), "该账号已停用");
            put(Integer.valueOf(AuthResultCode.WO_ERRCODE_FORMALMODE_TEMP_USE_FORBIDDEN.mo27459a()), "正式模式不允许试用用户使用");
            put(Integer.valueOf(AuthResultCode.WO_ERRCODE_PHONE_ONLY_PHONE.mo27459a()), "该账号仅允许手机使用");
            put(Integer.valueOf(AuthResultCode.WO_ERRCODE_SERVICEPLAN_PERIOD_FULL.mo27459a()), "业务试用期满，时效到");
            put(Integer.valueOf(AuthResultCode.WO_ERRCODE_SERVICEPLAN_CURR_HOURS_FULL.mo27459a()), "业务试用周期内时长满，请下个周期再试用");
            put(Integer.valueOf(AuthResultCode.WO_ERRCODE_SERVICEPLAN_HOURS_FULL.mo27459a()), "业务试用期满，最近周期时长满");
            put(Integer.valueOf(AuthResultCode.WO_ERRCODE_SMS_SEND_TIMES_FULL.mo27459a()), "今天该号码获取短信验证码次数已满");
            put(Integer.valueOf(AuthResultCode.WO_ERRCODE_SMS_SEND_FAIL.mo27459a()), "短信验证码发送失败");
            put(Integer.valueOf(AuthResultCode.WO_ERRCODE_USER_NOT_EXIST.mo27459a()), "账户不存在");
            put(Integer.valueOf(AuthResultCode.WO_ERRCODE_PHONE_CODE_WRONG.mo27459a()), "短信校验码错误，验证失败，请重新获取");
            put(Integer.valueOf(AuthResultCode.WO_ERRCODE_PHONE_CODE_EXPIRED.mo27459a()), "短信验证码已过期");
            put(Integer.valueOf(AuthResultCode.WO_ERRCODE_INVALID_OWNER_ID.mo27459a()), "管理员不存在");
            put(Integer.valueOf(AuthResultCode.WO_ERRCODE_INVALID_TOKEN.mo27459a()), "TOKEN不存在");
            put(Integer.valueOf(AuthResultCode.WO_ERRCODE_USER_STATUS_ERROR.mo27459a()), "用户状态错误，稍后尝试重新认证");
            put(Integer.valueOf(AuthResultCode.WO_ERRCODE_INVALID_SERVICEPLAN.mo27459a()), "业务套餐不存在");
            put(Integer.valueOf(AuthResultCode.WO_ERRCODE_APP_SESSION_NOT_EXIST.mo27459a()), "会话不存在");
            put(Integer.valueOf(AuthResultCode.WO_ERRCODE_MUTI_USER_LOGIN.mo27459a()), "不允许多设备登陆");
            put(Integer.valueOf(AuthResultCode.WO_ERRCODE_MUTI_USER_TRIAL.mo27459a()), "注册用户数量已满");
            put(Integer.valueOf(AuthResultCode.WO_ERRCODE_USER_HAS_EXIST.mo27459a()), "用户已存在");
            put(Integer.valueOf(AuthResultCode.WO_ERRCODE_STATIC_PASSWORD_MATCH.mo27459a()), "手机号或密码不正确");
            put(Integer.valueOf(AuthResultCode.WO_ERRCODE_USER_ONLINE.mo27459a()), "用户已在线");
            put(Integer.valueOf(AuthResultCode.WO_ERRCODE_MAC_SMS_SEND_TIMES_FULL.mo27459a()), "今天该终端获取短信验证码次数已满");
            put(Integer.valueOf(AuthResultCode.WO_ERRCODE_PHONE_HAS_BEEN_REGISTER.mo27459a()), "该账户已经被注册");
            put(Integer.valueOf(AuthResultCode.WO_ERRCODE_ONLIEN_TEST_NUM_TO_MORE.mo27459a()), "在线的试用用户过多");
            put(Integer.valueOf(AuthResultCode.WO_ERRCODE_PHONE_PASSWORD_EMPTY.mo27459a()), "静态密码为空");
            put(Integer.valueOf(AuthResultCode.WO_ERRCODE_ACCOUNT_MUST_BE_ACTIVE.mo27459a()), "用户需要发送短信激活账号");
            put(Integer.valueOf(AuthResultCode.WO_ERRCODE_SERVICEPLAN_NOT_EFFECT.mo27459a()), "用户需要发送短信激活账号");
            put(Integer.valueOf(AuthResultCode.WO_ERRCODE_APP_OUTDATED_VERSION.mo27459a()), "app版本已过时");
            put(Integer.valueOf(AuthResultCode.WO_ERRCODE_ACCOUNT_MUST_COMPLETE_DATA.mo27459a()), "需要填写注册信息");
            put(Integer.valueOf(AuthResultCode.WO_ERRCODE_ACCOUNT_MUST_BIND_PHONE.mo27459a()), "账号需要绑定手机");
            put(Integer.valueOf(AuthResultCode.WO_ERRCODE_NAME_USER_NOT_EXIST.mo27459a()), "账号需要绑定手机");
            put(Integer.valueOf(AuthResultCode.WO_ERRCODE_PHONE_NEED_MAC_BINDING.mo27459a()), "是否确认绑定当前设备");
            put(Integer.valueOf(AuthResultCode.WO_ERRCODE_PHONE_NEED_WECHART_AUTH.mo27459a()), "需要微信认证");
            put(Integer.valueOf(AuthResultCode.WO_ERRCODE_NOT_LOCATION_AUTH.mo27459a()), "异地登陆");
            put(Integer.valueOf(AuthResultCode.WO_ERRCODE_NETWORK_TYPE_ERROR.mo27459a()), "使用网络的类型错误");
            put(Integer.valueOf(AuthResultCode.WO_ERRCODE_TRY_TOO_OFTEN.mo27459a()), "尝试过于频繁");
            put(Integer.valueOf(AuthResultCode.WO_ERRCODE_ACCOUNT_HAS_BIND_PHONE.mo27459a()), "当前账号已经绑定手机号");
            put(Integer.valueOf(AuthResultCode.WO_ERRCODE_ACCOUNT_PWD_NOTMATCH_POLICY.mo27459a()), "您的密码不符合密码规范");
            put(Integer.valueOf(AuthResultCode.WO_ERRCODE_ACCOUNT_ERROR.mo27459a()), "身份类型不对或账号不存在或密码错误");
            put(Integer.valueOf(AuthResultCode.WO_ERRCODE_ACCOUNT_NEED_SWITCH.mo27459a()), "当前合作模式下，需要切换到GiWiFi账号充值认证");
            put(Integer.valueOf(AuthResultCode.WO_ERRCODE_ANTI_PROXY_ERROR.mo27459a()), "防代理检查异常");
            put(Integer.valueOf(AuthResultCode.WO_ERRCODE_AAA_AUTH_FAIL.mo27459a()), "AAA认证失败");
            put(Integer.valueOf(AuthResultCode.WO_ERRCODE_AAA_AUTH_ACCOUNT_NOTFOUND.mo27459a()), "AAA账户不存在");
            put(Integer.valueOf(AuthResultCode.WO_ERRCODE_AAA_AUTH_PWD_NULL.mo27459a()), "AAA密码为空");
            put(Integer.valueOf(AuthResultCode.WO_ERRCODE_AAA_AUTH_PWD_WRONG.mo27459a()), "AAA密码错误");
            put(Integer.valueOf(AuthResultCode.WO_ERRCODE_AAA_AUTH_ACCOUNT_WRONG.mo27459a()), "您输入的AAA账号开户地有误，请重新输入");
            put(Integer.valueOf(AuthResultCode.WO_ERRCODE_AAA_AUTH_ACCOUNT_LOCK.mo27459a()), "AAA用户已欠费停机，请检查及时充值");
            put(Integer.valueOf(AuthResultCode.WO_ERRCODE_AAA_AUTH_LIMIT_EXCEED.mo27459a()), "AAA认证终端数超上限");
            put(Integer.valueOf(AuthResultCode.WO_ERRCODE_AAA_AUTH_SERVICE_NOT_AVAILABLE.mo27459a()), "AAA账号已无可用时长,请重新办理业务");
            put(Integer.valueOf(AuthResultCode.WO_ERRCODE_AAA_AUTH_SERVICE_TYPE_ERROR.mo27459a()), "AAA服务类型选择错误");
            put(Integer.valueOf(AuthResultCode.WO_ERRCODE_NEED_RELOGIN.mo27459a()), "需要重新认证");
            put(Integer.valueOf(AuthResultCode.WO_ERRCODE_NEED_IDENTITY_RELOGIN.mo27459a()), "需要学工号认证");
            put(Integer.valueOf(AuthResultCode.WO_ERRCODE_NAME_USER_OR_PASSWORD_IS_WRONG.mo27459a()), "账号或密码错误");
            put(Integer.valueOf(AuthResultCode.WO_ERRCODE_TIME_AUTH_RULE_FORBID.mo27459a()), "认证时间段限制");
            put(Integer.valueOf(AuthResultCode.WO_ERRCODE_HOTSPOT_CHANNEL_OFF.mo27459a()), "热点通道未开启");
            put(Integer.valueOf(AuthResultCode.WO_ERRCODE_USER_SERVICEPLAN_IS_TRIAL.mo27459a()), "未使用正式套餐");
            put(Integer.valueOf(AuthResultCode.WO_ERRCODE_USER_HAVE_NOT_CHANNEL_SERVICEPLAN.mo27459a()), "用户未购买增值通道套餐");
            put(Integer.valueOf(AuthResultCode.WO_ERRCODE_USER_HAVE_NOT_CHANNEL_ADDRESS.mo27459a()), "热点未配置通道地址段");
            put(Integer.valueOf(AuthResultCode.WO_ERRCODE_USER_ADDRESS_IS_ERROR.mo27459a()), "热点配置通道地址段和当前终端不符");
            put(Integer.valueOf(AuthResultCode.WO_ERRCODE_USER_HAVE_NOT_THIE_SERVICE_PLAN.mo27459a()), "用户没有当前服务套餐");
            put(Integer.valueOf(AuthResultCode.WO_ERRCODE_PPPOE_FAIL.mo27459a()), "PPPoE拨号失败");
            put(Integer.valueOf(AuthResultCode.WO_ERRCODE_PPPOE_TIMEOUT.mo27459a()), "PPPoE拨号超时");
            put(Integer.valueOf(AuthResultCode.WO_ERRCODE_PORTAL_FAIL.mo27459a()), "Portal代拨失败");
            put(Integer.valueOf(AuthResultCode.WO_ERRCODE_PORTAL_TIMEOUT.mo27459a()), "Portal代拨超时");
        }
    };

    /* renamed from: au */
    private int f12923au = 0;

    private AuthResultCode(int i) {
        this.f12923au = i;
    }

    /* renamed from: a */
    public int mo27459a() {
        return this.f12923au;
    }

    /* renamed from: a */
    public static String m13884a(int i) {
        String str = f12896av.get(Integer.valueOf(i));
        if (str == null || str.equals("")) {
            return "很抱歉，操作失败";
        }
        return str;
    }

    /* renamed from: b */
    public static boolean m13885b(int i) {
        if (i == WO_ERRCODE_USER_NOT_EXIST.mo27459a()) {
            return true;
        }
        return false;
    }

    /* renamed from: c */
    public static boolean m13886c(int i) {
        if (i == WO_ERRCODE_PHONE_PASSWORD_EMPTY.mo27459a()) {
            return true;
        }
        return false;
    }

    /* renamed from: d */
    public static boolean m13887d(int i) {
        if (i == WO_ERRCODE_PHONE_BLACKLIST.mo27459a() || i == WO_ERRCODE_FORMALMODE_TEMP_REGISTER_FORBIDDEN.mo27459a() || i == WO_ERRCODE_REPEAT_REGISTER_FORBIDDEN.mo27459a() || i == WO_ERRCODE_SMS_SEND_TIMES_FULL.mo27459a() || i == WO_ERRCODE_MUTI_USER_TRIAL.mo27459a() || i == WO_ERRCODE_MAC_SMS_SEND_TIMES_FULL.mo27459a()) {
            return true;
        }
        return false;
    }

    /* renamed from: e */
    public static boolean m13888e(int i) {
        if (i == WO_ERRCODE_NEED_RELOGIN.mo27459a()) {
            return true;
        }
        return false;
    }

    /* renamed from: f */
    public static boolean m13889f(int i) {
        if (i == WO_ERRCODE_NEED_IDENTITY_RELOGIN.mo27459a()) {
            return true;
        }
        return false;
    }

    /* renamed from: g */
    public static boolean m13890g(int i) {
        if (i == WO_ERRCODE_PHONE_HAS_BEEN_REGISTER.mo27459a()) {
            return true;
        }
        return false;
    }

    /* renamed from: h */
    public static boolean m13891h(int i) {
        if (i == WO_ERRCODE_ACCOUNT_MUST_BIND_PHONE.mo27459a()) {
            return true;
        }
        return false;
    }

    /* renamed from: i */
    public static boolean m13892i(int i) {
        if (i == WO_ERRCODE_ACCOUNT_MUST_BE_ACTIVE.mo27459a()) {
            return true;
        }
        return false;
    }

    /* renamed from: j */
    public static boolean m13893j(int i) {
        if (i == WO_ERRCODE_STATIC_PASSWORD_MATCH.mo27459a()) {
            return true;
        }
        return false;
    }

    /* renamed from: k */
    public static boolean m13894k(int i) {
        if (i == WO_ERRCODE_FORMALMODE_TEMP_REGISTER_FORBIDDEN.mo27459a() || i == WO_ERRCODE_REPEAT_REGISTER_FORBIDDEN.mo27459a() || i == WO_ERRCODE_SERVICEPLAN_CURR_HOURS_FULL.mo27459a() || i == WO_ERRCODE_SERVICEPLAN_HOURS_FULL.mo27459a() || i == WO_ERRCODE_INVALID_SERVICEPLAN.mo27459a()) {
            return true;
        }
        return false;
    }

    /* renamed from: l */
    public static boolean m13895l(int i) {
        if (i == WO_ERRCODE_SERVICEPLAN_CURR_HOURS_FULL.mo27459a()) {
            return true;
        }
        return false;
    }

    /* renamed from: m */
    public static boolean m13896m(int i) {
        if (i == WO_ERRCODE_PHONE_NEED_MAC_BINDING.mo27459a()) {
            return true;
        }
        return false;
    }

    /* renamed from: n */
    public static boolean m13897n(int i) {
        if (i == WO_ERRCODE_PHONE_NEED_WECHART_AUTH.mo27459a()) {
            return true;
        }
        return false;
    }

    /* renamed from: o */
    public static boolean m13898o(int i) {
        if (i == WO_ERRCODE_ACCOUNT_MUST_COMPLETE_DATA.mo27459a()) {
            return true;
        }
        return false;
    }

    /* renamed from: p */
    public static boolean m13899p(int i) {
        if (i == WO_ERRCODE_USER_HAVE_NOT_THIE_SERVICE_PLAN.mo27459a()) {
            return true;
        }
        return false;
    }
}
