package com.chen.nots_web.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.chen.nots_web.global.entity.SuperEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 图片表
 * </p>
 *
 * @author MaybeBin
 * @since 2022-03-13
 */
@Data
@TableName("t_picture")
@ApiModel(value = "Picture对象", description = "图片表")
public class Picture extends SuperEntity<Picture> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("图片uid")
    @TableField("file_uid")
    private String fileUid;

    @ApiModelProperty("图片名")
    @TableField("pic_name")
    private String picName;

    @ApiModelProperty("分类uid")
    @TableField("picture_sort_uid")
    private String pictureSortUid;

    @TableField(exist = false)
    private String isUse;

}
