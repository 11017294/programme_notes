package com.chen.nots_web.vo;

import com.chen.nots_web.entity.Comment;
import com.chen.nots_web.global.vo.BaseVO;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author：MaybeBin
 * @Date: 2022-09-22 13-52
 */
@Data
public class CommentVO extends BaseVO<Comment> {

    @ApiModelProperty("文章的uid")
    private String articleUid;

    @ApiModelProperty("父评论uid")
    private String parentUid;

    @ApiModelProperty("评论用户的用户uid")
    private String commentUserUid;

    @ApiModelProperty("被评论的用户的uid")
    private String targetUserUid;

    @ApiModelProperty("评论内容")
    private String content;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("评论用户的用户名称")
    private String commentUserNickName;

    @ApiModelProperty("评论用户的用户头像")
    private String commentUserAvatar;

    @ApiModelProperty("被评论用户的用户名称")
    private String targetUserNickName;

    @ApiModelProperty("被评论用户的用户头像")
    private String targetUserAvatar;

    @ApiModelProperty("子评论")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<CommentVO> childrenList;

}
