package com.chen.nots_web.vo;

import com.chen.nots_web.global.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 字典数据表
 * </p>
 *
 * @author MaybeBin
 * @since 2022-03-07
 */
@Data
public class SysDictDataVO extends BaseVO<SysDictDataVO> {

    @ApiModelProperty("字典类型UID")
    private String dictTypeUid;

    @ApiModelProperty("字典标签")
    private String dictLabel;

    @ApiModelProperty("字典键值")
    private String dictValue;

    @ApiModelProperty("样式属性（其他样式扩展）")
    private String cssClass;

    @ApiModelProperty("表格回显样式")
    private String listClass;

    @ApiModelProperty("是否默认（1是 0否）,默认为0")
    private Boolean isDefault;

    @ApiModelProperty("创建人UID")
    private String createByUid;

    @ApiModelProperty("最后更新人UID")
    private String updateByUid;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("是否发布(1:是，0:否)")
    private String isPublish;

    @ApiModelProperty("排序字段")
    private Integer sort;

}
