package com.chen.nots_web.controller;


import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.chen.nots_web.entity.Tag;
import com.chen.nots_web.global.SQLConf;
import com.chen.nots_web.service.TagService;
import com.chen.nots_web.vo.ResultBase;
import com.chen.nots_web.vo.TagVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@Slf4j
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

    @ApiOperation(value = "增加标签", notes = "增加标签", response = String.class)
    @PostMapping("/add")
    public ResultBase add(TagVO tagVO) {
        log.info("增加标签");
        QueryWrapper<Tag> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(SQLConf.CONTENT, tagVO.getContent());
        Tag tempTag = tagService.getOne(queryWrapper);
        if(ObjectUtil.isNotEmpty(tempTag)){
            return ResultBase.error("已存在《" + tagVO.getContent() + "》标签");
        }
        return ResultBase.ok().data("id", tagService.addTag(tagVO));
    }

    @ApiOperation(value = "编辑标签", notes = "编辑标签", response = String.class)
    @PostMapping("/edit")
    public ResultBase edit(TagVO tagVO) {
        log.info("编辑标签");
        QueryWrapper<Tag> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(SQLConf.CONTENT, tagVO.getContent());
        Tag tag = tagService.getOne(queryWrapper);
        if(ObjectUtil.isNotEmpty(tag) && !tag.getUid().equals(tagVO.getUid())){
            return ResultBase.error("已存在《" + tagVO.getContent() + "》标签");
        }
        return ResultBase.ok().data("id", tagService.editTag(tagVO));
    }
}
