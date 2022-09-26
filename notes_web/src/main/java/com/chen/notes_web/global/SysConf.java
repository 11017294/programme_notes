package com.chen.notes_web.global;

/**
 *
 * @author：MaybeBin
 * @date: 2022-02-16 12:46
 */
public class SysConf {

    public final static String UID = "uid";
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
    public final static String OS = "OS";
    public final static String BROWSER = "BROWSER";
    public final static String CONTRIBUTE_DATE = "contributeDate";
    public final static String NOTE_CONTRIBUTE_COUNT = "noteContributeCount";

    // 上传七牛云相关
    public final static String FILE_PATH_USER = "user/";
    public final static String FILE_PATH_NOTES = "notes/";

    /**
     * t_web_visit表
     */
    public final static String IP = "ip";
    public final static String BEHAVIOR = "behavior";

    /**
     * 文件分割符
     */
    public final static String FILE_SEGMENTATION = ",";

    /**
     * AOP相关
     */
    public static final String MODULE_UID = "moduleUid";
    public static final String OTHER_DATA = "otherData";


}
