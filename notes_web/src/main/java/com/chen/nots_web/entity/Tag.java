package com.chen.nots_web.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.chen.nots_web.global.entity.SuperEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 标签表
 * </p>
 *
 * @author MaybeBin
 * @since 2022-03-07
 */
@Data
@TableName("t_tag")
@ApiModel(value = "Tag对象", description = "标签表")
public class Tag extends SuperEntity<Tag> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("标签内容")
    @TableField("content")
    private String content;

    @ApiModelProperty("标签简介")
    @TableField("click_count")
    private Integer clickCount;

    @ApiModelProperty("排序字段，越大越靠前")
    @TableField("sort")
    private Integer sort;

}
