package com.chen.nots_web.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.chen.nots_web.global.entity.SuperEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author MaybeBin
 * @since 2022-02-15
 */
@Data
@TableName("t_user")
@ApiModel(value = "User对象", description = "用户表")
public class User extends SuperEntity<User> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户名")
    @TableField("user_name")
    private String userName;

    @ApiModelProperty("密码")
    @TableField("pass_word")
    private String passWord;

    @ApiModelProperty("性别(1:男0:女)")
    @TableField("sex")
    private Integer sex;

    @ApiModelProperty("个人头像")
    @TableField("avatar")
    private String avatar;

    @ApiModelProperty("邮箱")
    @TableField("email")
    private String email;

    @ApiModelProperty("出生年月日")
    @TableField("birthday")
    private Date birthday;

    @ApiModelProperty("手机")
    @TableField("mobile")
    private String mobile;

    @ApiModelProperty("自我简介最多150字")
    @TableField("summary")
    private String summary;

    @ApiModelProperty("登录次数")
    @TableField("login_count")
    private Integer loginCount;

    @ApiModelProperty("最后登录时间")
    @TableField("last_login_time")
    private Date lastLoginTime;

    @ApiModelProperty("最后登录IP")
    @TableField("last_login_ip")
    private String lastLoginIp;

    @ApiModelProperty("昵称")
    @TableField("nick_name")
    private String nickName;

    @ApiModelProperty("平台uuid")
    @TableField("uuid")
    private String uuid;

    @ApiModelProperty("微信号")
    @TableField("we_chat")
    private String weChat;

    @ApiModelProperty("评论状态 1:正常 0:禁言")
    @TableField("comment_status")
    private Integer commentStatus;

    @ApiModelProperty("ip来源")
    @TableField("ip_source")
    private String ipSource;

    @ApiModelProperty("浏览器")
    @TableField("browser")
    private String browser;

    @ApiModelProperty("操作系统")
    @TableField("os")
    private String os;

    @ApiModelProperty("是否开启邮件通知 1:开启 0:关闭")
    @TableField("start_email_notification")
    private Integer startEmailNotification;

    @ApiModelProperty("用户标签：0：普通用户，1：管理员，2：博主 等")
    @TableField("user_tag")
    private Integer userTag;

    @ApiModelProperty("是否通过加载校验【0 未通过，1 已通过】")
    @TableField("loading_valid")
    private Integer loadingValid;

    @ApiModelProperty("状态，2表示被拉黑")
    @TableField("status")
    private int status;

    // 不存入数据库
    /**
     * 验证码
     */
    @TableField(exist = false)
    private String validCode;

    /**
     * 令牌UID【主要用于换取token令牌，防止token直接暴露到在线用户管理中】
     */
    @TableField(exist = false)
    private String tokenUid;

    /**
     * 用户头像
     */
    @TableField(exist = false)
    private List<String> photoList;

}
