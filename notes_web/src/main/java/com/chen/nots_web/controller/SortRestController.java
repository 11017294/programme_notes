package com.chen.nots_web.controller;

import com.chen.nots_web.entity.Note;
import com.chen.nots_web.service.NoteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author: MaybeBin
 * @createTime: 2022-03-26
 */
@RestController
@RequestMapping("/sort")
@Api(value = "笔记归档相关接口", tags = {"笔记归档相关接口"})
@Slf4j
public class SortRestController {

    @Autowired
    private NoteService noteService;

    @ApiOperation(value = "归档", notes = "归档")
    @GetMapping("/getSortList")
    public List<String> getSortList(){
        return noteService.getNoteTimeSortList();
    }

    @ApiOperation(value = "通过月份获取文章", notes = "通过月份获取文章")
    @GetMapping("/getArticleByMonth")
    public List<Note> getArticleByMonth(@ApiParam(name = "monthDate", value = "归档的日期", required = true)
                                            @RequestParam(name = "monthDate", required = false) String monthDate){
        return noteService.getArticleByMonth(monthDate);
    }
}
