package com.chen.notes_web.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.chen.notes_web.global.entity.SuperEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 收藏表
 * </p>
 *
 * @author MaybeBin
 * @since 2022-03-13
 */
@Data
@TableName("t_collect")
@ApiModel(value = "Collect对象", description = "收藏表")
public class Collect extends SuperEntity<Collect> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户的uid")
    @TableField("user_uid")
    private String userUid;

    @ApiModelProperty("笔记的uid")
    @TableField("note_uid")
    private String noteUid;

}
