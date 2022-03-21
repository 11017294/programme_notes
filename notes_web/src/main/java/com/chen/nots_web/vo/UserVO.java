package com.chen.nots_web.vo;

import com.chen.nots_web.global.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author：MaybeBin
 * @date: 2022-02-15 09:10
 */
@Data
public class UserVO extends BaseVO<UserVO> {

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("密码")
    private String passWord;

    @ApiModelProperty("性别(1:男0:女)")
    private Integer sex;

    @ApiModelProperty("个人头像")
    private String avatar;

    @ApiModelProperty("邮箱")
    private String email;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("出生年月日")
    private Date birthday;

    @ApiModelProperty("手机")
    private String mobile;

    @ApiModelProperty("自我简介最多150字")
    private String summary;

    @ApiModelProperty("昵称")
    private String nickName;

    @ApiModelProperty("平台uuid")
    private String uuid;

    @ApiModelProperty("微信号")
    private String weChat;

    @ApiModelProperty("评论状态 1:正常 0:禁言")
    private Integer commentStatus;

    @ApiModelProperty("是否开启邮件通知 1:开启 0:关闭")
    private Integer startEmailNotification;

    @ApiModelProperty("用户标签：0：普通用户，1：管理员，2：博主 等")
    private Integer userTag;

    @ApiModelProperty("状态，2表示被拉黑")
    private int status;

    /**
     * OrderBy排序字段（desc: 降序）
     */
    private String orderByDescColumn;

    /**
     * OrderBy排序字段（asc: 升序）
     */
    private String orderByAscColumn;

}
