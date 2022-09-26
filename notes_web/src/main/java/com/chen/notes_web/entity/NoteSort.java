package com.chen.notes_web.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.chen.notes_web.global.entity.SuperEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 笔记分类表
 * </p>
 *
 * @author MaybeBin
 * @since 2022-03-07
 */
@Data
@TableName("t_note_sort")
@ApiModel(value = "NoteSort对象", description = "笔记分类表")
public class NoteSort extends SuperEntity<NoteSort> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("分类内容")
    @TableField("sort_name")
    private String sortName;

    @ApiModelProperty("分类简介")
    @TableField("content")
    private String content;

    @ApiModelProperty("排序字段，越大越靠前")
    @TableField("sort")
    private Integer sort;

    @ApiModelProperty("点击数")
    @TableField("click_count")
    private Integer clickCount;


}
