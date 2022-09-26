package com.chen.notes_web.entity;

import lombok.Data;

/**
 *
 * @author：MaybeBin
 * @date: 2022-02-16 17:56
 */
@Data
public class OnlineUser {
    /**
     * 会话编号
     */
    private String tokenId;

    /**
     * 用户Token
     */
    private String token;

    /**
     * 用户的UID
     */
    private String userUid;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 登录IP地址
     */
    private String ipaddr;

    /**
     * 登录地址
     */
    private String loginLocation;

    /**
     * 浏览器类型
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 登录时间
     */
    private String loginTime;

    /**
     * 过期时间
     */
    private String expireTime;
}
