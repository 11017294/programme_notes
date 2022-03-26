package com.chen.nots_web.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.chen.nots_web.global.entity.SuperEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * Web访问记录表
 * </p>
 *
 * @author MaybeBin
 * @since 2022-03-19
 */
@Data
@TableName("t_web_visit")
@ApiModel(value = "WebVisit对象", description = "Web访问记录表")
public class WebVisit extends SuperEntity<WebVisit> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户uid")
    @TableField("user_uid")
    private String userUid;

    @ApiModelProperty("访问ip地址")
    @TableField("ip")
    private String ip;

    @ApiModelProperty("用户行为")
    @TableField("behavior")
    private String behavior;

    @ApiModelProperty("模块uid（文章uid，标签uid，分类uid）")
    @TableField("module_uid")
    private String moduleUid;

    @ApiModelProperty("附加数据(比如搜索内容)")
    @TableField("other_data")
    private String otherData;

    @ApiModelProperty("操作系统")
    @TableField("os")
    private String os;

    @ApiModelProperty("浏览器")
    @TableField("browser")
    private String browser;

    @ApiModelProperty("ip来源")
    @TableField("ip_source")
    private String ipSource;

    // 以下字段不存入数据库

    /**
     * 内容(点击的笔记名，点击的标签名，搜索的内容，点击的作者)
     */
    @TableField(exist = false)
    private String content;

    /**
     * 行为名称
     */
    @TableField(exist = false)
    private String behaviorContent;

}
