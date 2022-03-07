package com.chen.nots_web.global.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.util.Date;

/**
 *
 * @author：MaybeBin
 * @date: 2022-01-25 10:54
 */
@Data
public class SuperEntity<T extends Model> extends Model {

    private static final long serialVersionUID = -4851055162892178225L;

    /**
     * 唯一UID
     */
    @TableId(value = "uid", type = IdType.ASSIGN_UUID)
    private String uid;

    /**
     * 是否删除，0:未删除 1:已删除
     */
    @TableField("is_delete")
    private int isDelete;

    /**
     * @TableField 配置需要填充的字段
     * 创建时间
     */
/*    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.UPDATE)*/
    private Date createTime;

    /**
     * 更新时间
     */
/*    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT_UPDATE)*/
    private Date updateTime;

/*    public SuperEntity() {
        this.isDelete = 0;
        this.createTime = new Date();
        this.updateTime = new Date();
    }*/
}
