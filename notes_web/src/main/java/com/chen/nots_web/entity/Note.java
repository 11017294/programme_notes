package com.chen.nots_web.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.chen.nots_web.global.entity.SuperEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 笔记表(TNote)表实体类
 *
 * @author makejava
 * @since 2022-01-25 11:00:42
 */
@Data
@TableName("t_note")
@ApiModel(value = "Note对象", description = "笔记表")
public class Note extends SuperEntity<Note> {

    private static final long serialVersionUID = 1L;

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

    @ApiModelProperty("管理员uid")
    @TableField("admin_uid")
    private String adminUid;

    @ApiModelProperty("笔记点击数")
    @TableField("click_count")
    private Integer clickCount;

    @ApiModelProperty("笔记收藏数")
    @TableField("collect_count")
    private Integer collectCount;

    @ApiModelProperty("排序字段")
    @TableField("sort")
    private Integer sort;


    // 以下字段不存入数据库，封装为了方便使用
    /**
     * 标签,一篇笔记对应多个标签
     */
    @TableField(exist = false)
    private List<Tag> tagList;

    /**
     * 标签
     */
    @TableField(exist = false)
    private String tagsName;

    /**
     * 分类名称
     */
    @TableField(exist = false)
    private String noteSortName;

    /**
     * 用户头像url
     */
    @TableField(exist = false)
    private String userAvatar;

}

