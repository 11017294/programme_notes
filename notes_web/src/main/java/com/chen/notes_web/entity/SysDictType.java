package com.chen.notes_web.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.chen.notes_web.global.entity.SuperEntity;
import io.swagger.annotations.ApiModel;
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
@TableName("t_sys_dict_type")
@ApiModel(value = "SysDictType对象", description = "字典类型表")
public class SysDictType extends SuperEntity<SysDictType> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("字典名称")
    @TableField("dict_name")
    private String dictName;

    @ApiModelProperty("字典类型")
    @TableField("dict_type")
    private String dictType;

    @ApiModelProperty("创建人UID")
    @TableField("create_by_uid")
    private String createByUid;

    @ApiModelProperty("最后更新人UID")
    @TableField("update_by_uid")
    private String updateByUid;

    @ApiModelProperty("备注")
    @TableField("remark")
    private String remark;

    @ApiModelProperty("是否发布(1:是，0:否)")
    @TableField("is_publish")
    private String isPublish;

    @ApiModelProperty("排序字段")
    @TableField("sort")
    private Integer sort;

}
