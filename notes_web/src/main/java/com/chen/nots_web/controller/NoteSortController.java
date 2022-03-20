package com.chen.nots_web.controller;


import com.chen.nots_web.service.NoteSortService;
import com.chen.nots_web.vo.NoteSortVO;
import com.chen.nots_web.vo.ResultBase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
}
