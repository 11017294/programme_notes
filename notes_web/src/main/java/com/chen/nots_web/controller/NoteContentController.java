package com.chen.nots_web.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.chen.nots_web.vo.ResultBase;
import com.chen.nots_web.service.NoteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author：MaybeBin
 * @date: 2022-02-14 14:44
 */
@Api(value = "笔记详情相关接口", tags = {"笔记详情相关接口"})
@RestController
@RequestMapping("/content")
public class NoteContentController {

    @Autowired
    NoteService noteService;

    @ApiOperation(value = "通过uid获取笔记", notes = "通过uid获取笔记")
    @GetMapping("/getNoteById")
    public ResultBase getNoteById(@ApiParam(name = "uid", value = "笔记UID")
                                  @RequestParam(name = "uid", required = false)
                                           String uid){
        if(StringUtils.isNotBlank(uid)){
            return ResultBase.ok().data("note", noteService.getById(uid));
        }
        return ResultBase.error("没有传入uid");
    }

}
