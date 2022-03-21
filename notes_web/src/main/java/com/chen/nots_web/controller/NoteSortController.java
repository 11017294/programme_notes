package com.chen.nots_web.controller;


import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.chen.nots_web.entity.NoteSort;
import com.chen.nots_web.global.SQLConf;
import com.chen.nots_web.service.NoteSortService;
import com.chen.nots_web.vo.NoteSortVO;
import com.chen.nots_web.vo.ResultBase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResultBase getList(NoteSortVO noteSortVO) {
        return ResultBase.ok().data("list", noteSortService.getAllSort(noteSortVO));
    }

    @ApiOperation(value = "获取笔记分类列表", notes = "获取笔记分类列表", response = String.class)
    @GetMapping("/getSortList")
    public ResultBase getSortList(NoteSortVO noteSortVO) {
        return ResultBase.ok().data("list", noteSortService.getPageList(noteSortVO));
    }

    @ApiOperation(value = "删除分类", notes = "删除分类")
    @PostMapping("/delete")
    public ResultBase delete(@ApiParam(name = "uid", value = "分类UID") String uid) {
        if(StringUtils.isBlank(uid)){
            return ResultBase.error("删除失败，没有传入uid");
        }
        boolean noteSort = noteSortService.removeById(uid);
        if(noteSort){
            return ResultBase.ok().data("id",uid);
        }
        return ResultBase.error("删除失败，没有ID:" + uid + "的分类");
    }


    @ApiOperation(value = "增加笔记分类", notes = "增加笔记分类", response = String.class)
    @PostMapping("/add")
    public ResultBase add(NoteSortVO noteSortVO) {
        log.info("增加笔记分类");
        QueryWrapper<NoteSort> wrapper = new QueryWrapper<>();
        wrapper.eq(SQLConf.SORT_NAME, noteSortVO.getSortName());
        NoteSort noteSort = noteSortService.getOne(wrapper);
        if(ObjectUtil.isNotEmpty(noteSort)){
            return ResultBase.error("已存在《" + noteSortVO.getSortName() + "》分类");
        }
        return ResultBase.ok().data("id", noteSortService.addNoteSort(noteSortVO));
    }

    @ApiOperation(value = "编辑笔记分类", notes = "编辑笔记分类", response = String.class)
    @PostMapping("/edit")
    public ResultBase edit(NoteSortVO noteSortVO) {
        log.info("编辑笔记分类");
        QueryWrapper<NoteSort> wrapper = new QueryWrapper<>();
        wrapper.eq(SQLConf.SORT_NAME, noteSortVO.getSortName());
        NoteSort noteSort = noteSortService.getOne(wrapper);
        if(ObjectUtil.isNotEmpty(noteSort) && !noteSort.getUid().equals(noteSortVO.getUid())){
            return ResultBase.error("已存在《" + noteSortVO.getSortName() + "》分类");
        }
        return ResultBase.ok().data("id", noteSortService.editNoteSort(noteSortVO));
    }
}
