package com.chen.nots_web.vo;

import com.chen.nots_web.global.vo.BaseVO;
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
@ApiModel(value = "NoteSort对象", description = "笔记分类表")
public class NoteSortVO extends BaseVO<NoteSortVO> {

    @ApiModelProperty("分类内容")
    private String sortName;

    @ApiModelProperty("分类简介")
    private String content;

    @ApiModelProperty("排序字段，越大越靠前")
    private Integer sort;

    @ApiModelProperty("点击数")
    private Integer clickCount;

}
