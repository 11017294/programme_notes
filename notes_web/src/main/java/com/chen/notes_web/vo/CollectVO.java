package com.chen.notes_web.vo;

import com.chen.notes_web.global.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author: MaybeBin
 * @createTime: 2022-03-13
 */
@Data
public class CollectVO extends BaseVO<CollectVO> {

    @ApiModelProperty("用户的uid")
    private String userUid;

    @ApiModelProperty("笔记的uid")
    private String noteUid;
}
