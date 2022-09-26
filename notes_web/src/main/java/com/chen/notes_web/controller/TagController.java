package com.chen.notes_web.controller;


import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.chen.notes_web.entity.Tag;
import com.chen.notes_web.exception.BusinessException;
import com.chen.notes_web.exception.ErrorCode;
import com.chen.notes_web.global.SQLConf;
import com.chen.notes_web.service.TagService;
import com.chen.notes_web.vo.TagVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public List<Tag> getList(){
        return tagService.getAll();
    }

    @ApiOperation(value = "获取标签分类列表", notes = "获取标签分类列表")
    @GetMapping("/getTagList")
    public IPage<Tag> getTagList(TagVO tagVO){
        return tagService.getTagList(tagVO);
    }

    @ApiOperation(value = "删除标签", notes = "删除标签")
    @PostMapping("/delete")
    public String delete(@ApiParam(name = "uid", value = "标签UID") String uid) {
        if(StringUtils.isBlank(uid)){
            throw new BusinessException(ErrorCode.REQUEST_PARAMS_ERROR);
        }
        boolean tag = tagService.removeById(uid);
        if(!tag){
            throw new BusinessException(ErrorCode.NOT_FOUND);
        }
        return uid;
    }

    @ApiOperation(value = "增加标签", notes = "增加标签", response = String.class)
    @PostMapping("/add")
    public String add(TagVO tagVO) {
        log.info("增加标签");
        QueryWrapper<Tag> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(SQLConf.CONTENT, tagVO.getContent());
        Tag tempTag = tagService.getOne(queryWrapper);
        if(ObjectUtil.isNotEmpty(tempTag)){
            throw new BusinessException(ErrorCode.CONFLICT);
        }
        return tagService.addTag(tagVO);
    }

    @ApiOperation(value = "编辑标签", notes = "编辑标签", response = String.class)
    @PostMapping("/edit")
    public String edit(TagVO tagVO) {
        log.info("编辑标签");
        QueryWrapper<Tag> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(SQLConf.CONTENT, tagVO.getContent());
        Tag tag = tagService.getOne(queryWrapper);
        if(ObjectUtil.isNotEmpty(tag) && !tag.getUid().equals(tagVO.getUid())){
            throw new BusinessException(ErrorCode.CONFLICT);
        }
        return tagService.editTag(tagVO);
    }
}
