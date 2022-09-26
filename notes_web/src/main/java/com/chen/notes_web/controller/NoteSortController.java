package com.chen.notes_web.controller;


import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.chen.notes_web.entity.NoteSort;
import com.chen.notes_web.exception.BusinessException;
import com.chen.notes_web.exception.ErrorCode;
import com.chen.notes_web.global.SQLConf;
import com.chen.notes_web.service.NoteSortService;
import com.chen.notes_web.vo.BaseResponse;
import com.chen.notes_web.vo.NoteSortVO;
import com.chen.notes_web.vo.ResultUtils;
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
 * 笔记分类表 前端控制器
 * </p>
 *
 * @author MaybeBin
 * @since 2022-03-07
 */
@Api(value = "笔记分类相关接口", tags = {"笔记分类相关接口"})
@RestController
@RequestMapping("/noteSort")
@Slf4j
public class NoteSortController {

    @Autowired
    private NoteSortService noteSortService;

    @ApiOperation(value = "获取全部笔记分类列表", notes = "获取全部笔记分类列表", response = String.class)
    @GetMapping("/getList")
    public List<NoteSort> getList(NoteSortVO noteSortVO) {
        return noteSortService.getAllSort(noteSortVO);
    }

    @ApiOperation(value = "获取笔记分类列表", notes = "获取笔记分类列表", response = String.class)
    @GetMapping("/getSortList")
    public BaseResponse<IPage> getSortList(NoteSortVO noteSortVO) {
        IPage<NoteSort> pageList = noteSortService.getPageList(noteSortVO);
        return ResultUtils.success(pageList);
    }

    @ApiOperation(value = "删除分类", notes = "删除分类")
    @PostMapping("/delete")
    public String delete(@ApiParam(name = "uid", value = "分类UID") String uid) {
        if(StringUtils.isBlank(uid)){
            throw new BusinessException(ErrorCode.REQUEST_PARAMS_ERROR);
        }
        boolean noteSort = noteSortService.removeById(uid);
        if(!noteSort){
            throw new BusinessException(ErrorCode.NOT_FOUND);
        }
        return uid;
    }


    @ApiOperation(value = "增加笔记分类", notes = "增加笔记分类", response = String.class)
    @PostMapping("/add")
    public String add(NoteSortVO noteSortVO) {
        log.info("增加笔记分类");
        QueryWrapper<NoteSort> wrapper = new QueryWrapper<>();
        wrapper.eq(SQLConf.SORT_NAME, noteSortVO.getSortName());
        NoteSort noteSort = noteSortService.getOne(wrapper);
        if(ObjectUtil.isNotEmpty(noteSort)){
            throw new BusinessException(ErrorCode.CONFLICT);
        }
        return noteSortService.addNoteSort(noteSortVO);
    }

    @ApiOperation(value = "编辑笔记分类", notes = "编辑笔记分类", response = String.class)
    @PostMapping("/edit")
    public String edit(NoteSortVO noteSortVO) {
        log.info("编辑笔记分类");
        QueryWrapper<NoteSort> wrapper = new QueryWrapper<>();
        wrapper.eq(SQLConf.SORT_NAME, noteSortVO.getSortName());
        NoteSort noteSort = noteSortService.getOne(wrapper);
        if(ObjectUtil.isNotEmpty(noteSort) && !noteSort.getUid().equals(noteSortVO.getUid())){
            throw new BusinessException(ErrorCode.CONFLICT);
        }
        return noteSortService.editNoteSort(noteSortVO);
    }
}
