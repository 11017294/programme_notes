package com.chen.nots_web.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.chen.nots_web.global.entity.SuperEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 留言表
 * </p>
 *
 * @author MaybeBin
 * @since 2022-03-15
 */
@Data
@TableName("t_message")
@ApiModel(value = "Message对象", description = "留言表")
public class Message extends SuperEntity<Message> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("内容")
    @TableField("content")
    private String content;

    @ApiModelProperty("留言人邮箱")
    @TableField("email")
    private String email;

    @ApiModelProperty("留言人uid")
    @TableField("user_uid")
    private String userUid;

    @ApiModelProperty("状态，1表示已处理")
    @TableField("status")
    private int status;
}
