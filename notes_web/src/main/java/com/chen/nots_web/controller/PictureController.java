package com.chen.nots_web.controller;


import com.chen.nots_web.service.PictureService;
import com.chen.nots_web.utils.QiniuUtil;
import com.chen.nots_web.vo.PictureVO;
import com.chen.nots_web.vo.ResultBase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 图片表 前端控制器
 * </p>
 *
 * @author MaybeBin
 * @since 2022-03-13
 */
@Api(value = "图片相关接口", tags = {"图片相关接口"})
@RestController
@RequestMapping("/picture")
@Slf4j
public class PictureController {

    @Autowired
    private PictureService pictureService;
    @Autowired
    private QiniuUtil qiniuUtil;

    @ApiOperation(value = "获取图片列表", notes = "获取图片列表")
    @GetMapping("/getPictureList")
    public ResultBase getPictureList(PictureVO pictureVO) {
        return ResultBase.ok().data("list", pictureService.getPictureList(pictureVO));
    }

    @ApiOperation(value = "删除图片", notes = "删除图片")
    @PostMapping("/deleteImage")
    public ResultBase deleteImage(PictureVO pictureVO) {
        int delete = qiniuUtil.deleteFileFromQiniu(pictureVO.getPicName());
        if(delete != 200){
            return ResultBase.error("删除失败");
        }
        return ResultBase.ok().data("id", pictureService.deleteImage(pictureVO));
    }

}
