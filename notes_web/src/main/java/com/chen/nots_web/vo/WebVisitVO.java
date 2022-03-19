package com.chen.nots_web.vo;

import com.chen.nots_web.global.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author: MaybeBin
 * @createTime: 2022-03-19
 */
@Data
public class WebVisitVO extends BaseVO<WebVisitVO> {

    @ApiModelProperty("用户uid")
    private String userUid;

    @ApiModelProperty("访问ip地址")
    private String ip;

    @ApiModelProperty("用户行为")
    private String behavior;

    @ApiModelProperty("模块uid（文章uid，标签uid，分类uid）")
    private String moduleUid;

    @ApiModelProperty("附加数据(比如搜索内容)")
    private String otherData;

    @ApiModelProperty("操作系统")
    private String os;

    @ApiModelProperty("浏览器")
    private String browser;

    @ApiModelProperty("ip来源")
    private String ipSource;

    /**
     * 日志时间段
     */
    private String startTime;
}
