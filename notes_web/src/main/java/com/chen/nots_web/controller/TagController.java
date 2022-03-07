package com.chen.nots_web.controller;


import com.chen.nots_web.service.TagService;
import com.chen.nots_web.vo.ResultBase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 标签表 前端控制器
 * </p>
 *
 * @author MaybeBin
 * @since 2022-03-07
 */
@Api(value = "标签相关接口", tags = {"标签相关接口"})
@RestController
@RequestMapping("/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @ApiOperation(value = "获取标签分类列表", notes = "获取标签分类列表")
    @GetMapping("/getList")
    public ResultBase getList(){
        return ResultBase.ok().data("list", tagService.getPageList());
    }

}
