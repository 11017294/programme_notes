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
 * @createTime: 2022-03-15
 */
@Data
public class MessageVO extends BaseVO<MessageVO> {

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("留言人邮箱")
    private String email;

    @ApiModelProperty("留言人uid")
    private String userUid;

    @ApiModelProperty("状态(默认1)，2表示已处理")
    private int status;
}
