package com.chen.notes_web.vo;

import com.chen.notes_web.global.vo.BaseVO;
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
@ApiModel(value = "Tag对象", description = "标签表")
public class TagVO extends BaseVO<TagVO> {

    @ApiModelProperty("标签内容")
    private String content;

    @ApiModelProperty("标签简介")
    private Integer clickCount;

    @ApiModelProperty("排序字段，越大越靠前")
    private Integer sort;

}
