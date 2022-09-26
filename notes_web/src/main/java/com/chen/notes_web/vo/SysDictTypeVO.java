package com.chen.notes_web.vo;

import com.chen.notes_web.global.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 字典类型表
 * </p>
 *
 * @author MaybeBin
 * @since 2022-03-07
 */
@Data
public class SysDictTypeVO extends BaseVO<SysDictTypeVO> {

    @ApiModelProperty("字典名称")
    private String dictName;

    @ApiModelProperty("字典类型")
    private String dictType;

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
