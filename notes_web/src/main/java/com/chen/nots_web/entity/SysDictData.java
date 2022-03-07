package com.chen.nots_web.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.chen.nots_web.global.entity.SuperEntity;
import io.swagger.annotations.ApiModel;
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
@TableName("t_sys_dict_data")
@ApiModel(value = "SysDictData对象", description = "字典数据表")
public class SysDictData extends SuperEntity<SysDictData> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("字典类型UID")
    @TableField("dict_type_uid")
    private String dictTypeUid;

    @ApiModelProperty("字典标签")
    @TableField("dict_label")
    private String dictLabel;

    @ApiModelProperty("字典键值")
    @TableField("dict_value")
    private String dictValue;

    @ApiModelProperty("样式属性（其他样式扩展）")
    @TableField("css_class")
    private String cssClass;

    @ApiModelProperty("表格回显样式")
    @TableField("list_class")
    private String listClass;

    @ApiModelProperty("是否默认（1是 0否）,默认为0")
    @TableField("is_default")
    private Boolean isDefault;

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
