package com.house.demo.common;

/**
 * @author xjj
 * 权限相关常量
 */

public class AuthConstant {

    /**
     * cookie中存储的token字段名
     */
    public final static String COOKIE_TOKEN_NAME = "Authorization";

    /**
     * token有效时间 时*分*秒*1000L
     */
    public final static Long EXPIRE_TIME = 60*60*1000L;

    //登录认证结果,返回给前端
    public final static String UNKNOWN_ACCOUNT = "登录失败, 用户不存在。";

    public final static String WRONG_PASSWORD = "登录失败，密码错误。";

    public final static String TOKEN_BLANK = "验证失败，token为空，请登录。";

    public final static String TOKEN_INVALID = "验证失败，token错误。";

    public final static String TOKEN_EXPIRE = "验证失败，token过期，请重新登录。";

    public final static String AUTHENTICATE_FAIL = "无访问权限";

}
