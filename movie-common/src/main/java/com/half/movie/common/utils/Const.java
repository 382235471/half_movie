package com.half.movie.common.utils;

/**
 * @author lmm
 * @创建时间 2019-10-12
 * @描述
 **/
public class Const {
    //true false
    public static final String TRUE = "Y";
    public static final String FALSE = "N";
    //逻辑删除
    public static final Long DEL = 1L;
    public static final Long NOT_DEL = 0L;
    //jwt,shiro
    public static final String PREFIX_SHIRO_REDIS = "shiro:jwt:";
    //shiro cache
    public static final String PREFIX_SHIRO_CACHE = "shiro:cache:";
    //jwt claim
    public static final String TOKEN_TIME = "tokenTime:";
    public static final String USERNAME = "username:";
    public static final String USERID = "userid:";
    public static final String CHECK_PASSWORD = "checkpassword:";

    //微信用户唯一id
    public static final String OPEN_ID = "OpenId:";
    //微信用户SessionKey
    public static final String SESSION_KEY = "SessionKey:";
    //jwt-id
    public static final String JWT_ID = "jwtId:";

}
