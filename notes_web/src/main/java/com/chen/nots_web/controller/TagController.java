package com.chen.nots_web.controller;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.chen.nots_web.service.TagService;
import com.chen.nots_web.vo.ResultBase;
import com.chen.nots_web.vo.TagVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @ApiOperation(value = "获取全部标签列表", notes = "获取全部标签列表")
    @GetMapping("/getList")
    public ResultBase getList(){
        return ResultBase.ok().data("list", tagService.getAll());
    }

    @ApiOperation(value = "获取标签分类列表", notes = "获取标签分类列表")
    @GetMapping("/getTagList")
    public ResultBase getTagList(TagVO tagVO){
        return ResultBase.ok().data("list", tagService.getTagList(tagVO));
    }

    @ApiOperation(value = "删除标签", notes = "删除标签")
    @PostMapping("/delete")
    public ResultBase delete(@ApiParam(name = "uid", value = "标签UID") String uid) {
        if(StringUtils.isBlank(uid)){
            return ResultBase.error("删除失败，没有传入uid");
        }
        boolean tag = tagService.removeById(uid);
        if(tag){
            return ResultBase.ok().data("id",uid);
        }
        return ResultBase.error("删除失败，没有ID:" + uid + "的标签");
    }
}
