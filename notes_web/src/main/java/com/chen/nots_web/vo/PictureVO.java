package com.chen.nots_web.vo;

import com.chen.nots_web.global.vo.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author：MaybeBin
 * @Date: 2022-03-22 10-04
 */
@Data
@ApiModel(value = "图片表", description = "图片表")
public class PictureVO extends BaseVO<PictureVO> {

    @ApiModelProperty("图片uid")
    private String fileUid;

    @ApiModelProperty("图片名")
    private String picName;

    @ApiModelProperty("分类uid")
    private String pictureSortUid;
}
