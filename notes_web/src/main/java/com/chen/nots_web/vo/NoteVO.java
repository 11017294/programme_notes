package com.chen.nots_web.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.chen.nots_web.global.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author：MaybeBin
 * @date: 2022-02-11 17:26
 */
@Data
public class NoteVO extends BaseVO<NoteVO> {

    @ApiModelProperty("笔记标题")
    @TableField("title")
    private String title;

    @ApiModelProperty("笔记简介")
    @TableField("summary")
    private String summary;

    @ApiModelProperty("笔记内容")
    @TableField("content")
    private String content;

    @ApiModelProperty("标签uid")
    @TableField("tag_uid")
    private String tagUid;

    @ApiModelProperty("笔记点击数")
    @TableField("click_count")
    private Integer clickCount;

    @ApiModelProperty("笔记收藏数")
    @TableField("collect_count")
    private Integer collectCount;

    @ApiModelProperty("标题图片uid")
    @TableField("file_uid")
    private String fileUid;

    @ApiModelProperty("是否原创（0:不是 1：是）")
    @TableField("is_original")
    private String isOriginal;

    @ApiModelProperty("作者")
    @TableField("author")
    private String author;

    @ApiModelProperty("文章出处")
    @TableField("articles_part")
    private String articlesPart;

    @ApiModelProperty("笔记分类UID")
    @TableField("note_sort_uid")
    private String noteSortUid;

    @ApiModelProperty("是否发布：0：否，1：是")
    @TableField("is_publish")
    private String isPublish;

    @ApiModelProperty("是否开启评论(0:否 1:是)")
    @TableField("open_comment")
    private String openComment;

    @ApiModelProperty("投稿用户UID")
    @TableField("user_uid")
    private String userUid;

    @ApiModelProperty("文章来源【0 后台添加，1 用户投稿】")
    @TableField("article_source")
    private String articleSource;

    @ApiModelProperty("文章来源【0 后台添加，1 用户投稿】")
    @TableField("create_time")
    private Date createTime;

    // 以下字段不存入数据库，封装为了方便使用
    @ApiModelProperty("标题图")
    private String photoUrl;

    @ApiModelProperty("笔记分类")
    private String noteSortStr;

    @ApiModelProperty("点赞数")
    private Integer praiseCount;

    @ApiModelProperty("版权申明")
    private String copyright;

    @ApiModelProperty("使用Sort字段进行排序 （0：不使用， 1：使用），默认为0")
    private Integer useSort;

    @ApiModelProperty("排序字段，数值越大，越靠前")
    private Integer sort;

    @ApiModelProperty("OrderBy排序字段（desc: 降序）")
    private String orderByDescColumn;

    @ApiModelProperty("OrderBy排序字段（asc: 升序）")
    private String orderByAscColumn;
}
