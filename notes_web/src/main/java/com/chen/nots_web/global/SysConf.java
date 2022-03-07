package com.chen.nots_web.global;

/**
 *
 * @author：MaybeBin
 * @date: 2022-02-16 12:46
 */
public class SysConf {

    // 获取第一条
    public final static String LIMIT_ONE = "limit 1";
    // 创建时间
    public final static String CREATE_TIME = "CREATE_TIME";
    // 作为token的密钥
    public final static byte[] JWT_KEY = "1234567890".getBytes();
    // 过期时间：30 天
    public final static long EXPIRED_TIME = 30 * 24 * 60 * 60;
    // 编码类型
    public final static String UTF_8 = "utf-8";
    // 用户名
    public final static String USER_NAME = "user_name";
    // 用户uid
    public final static String USER_UID = "user_uid";

    public final static String TOKEN = "token";
    public final static String SYMBOL_COLON = ":";
    public final static String EQUAL_TO = "=";
    public final static String IP = "ip";
    public final static String OS = "OS";
    public final static String BROWSER = "BROWSER";




}
