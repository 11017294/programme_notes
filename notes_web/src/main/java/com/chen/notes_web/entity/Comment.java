package com.chen.notes_web.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.chen.notes_web.global.entity.SuperEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 评论信息
 * </p>
 *
 * @author MaybeBin
 * @since 2022-09-22
 */
@Data
@TableName("t_comment")
@ApiModel(value = "Comment对象", description = "评论信息")
public class Comment extends SuperEntity<Comment> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("文章的uid")
    @TableField("article_uid")
    private String articleUid;

    @ApiModelProperty("父评论uid")
    @TableField("parent_uid")
    private String parentUid;

    @ApiModelProperty("评论用户的用户uid")
    @TableField("comment_user_uid")
    private String commentUserUid;

    @ApiModelProperty("被评论的用户的uid")
    @TableField("target_user_uid")
    private String targetUserUid;

    @ApiModelProperty("评论内容")
    @TableField("content")
    private String content;

    @ApiModelProperty("评论用户的用户名称")
    @TableField("comment_user_nick_name")
    private String commentUserNickName;

    @ApiModelProperty("评论用户的用户头像")
    @TableField("comment_user_avatar")
    private String commentUserAvatar;

    @ApiModelProperty("被评论用户的用户名称")
    @TableField("target_user_nick_name")
    private String targetUserNickName;

    @ApiModelProperty("被评论用户的用户头像")
    @TableField("target_user_avatar")
    private String targetUserAvatar;

}
