package com.chen.nots_web.controller;

import com.chen.nots_web.service.NoteService;
import com.chen.nots_web.service.TagService;
import com.chen.nots_web.service.UserService;
import com.chen.nots_web.service.WebVisitService;
import com.chen.nots_web.vo.ResultBase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 * 编程笔记系统相关接口
 * </p>
 *
 * @author: MaybeBin
 * @createTime: 2022-03-18
 */
@Api(value = "编程笔记系统相关接口", tags = {"编程笔记系统相关接口"})
@RestController
@RequestMapping("/notesSys")
@Slf4j
public class NotesSysController {

    @Autowired
    private NoteService noteService;
    @Autowired
    private UserService userService;
    @Autowired
    private TagService tagService;
    @Autowired
    private WebVisitService webVisitService;

    @ApiOperation(value = "注册用户数", notes = "注册用户数")
    @GetMapping("/getUserCount")
    public ResultBase getUserCount() {
        return ResultBase.ok().data("count", userService.count());
    }

    @ApiOperation(value = "文章数", notes = "文章数")
    @GetMapping("/getNotesCount")
    public ResultBase getNotesCount() {
        return ResultBase.ok().data("count", noteService.count());
    }

    @ApiOperation(value = "标签数", notes = "标签数")
    @GetMapping("/getTagCount")
    public ResultBase getTagCount() {
        return ResultBase.ok().data("count", tagService.count());
    }

    @ApiOperation(value = "浏览数", notes = "浏览数")
    @GetMapping("/getViewingCount")
    public ResultBase getViewingCount() {
        return ResultBase.ok().data("count", webVisitService.getWebVisitCount());
    }

    @ApiOperation(value = "获取一年内的文章贡献数", notes = "获取一年内的文章贡献度")
    @GetMapping(value = "/getNoteContributeCount")
    public ResultBase getNoteContributeCount() {
        Map<String, Object> resultMap = noteService.getNoteContributeCount();
        return ResultBase.ok().data("res", resultMap);
    }

    @ApiOperation(value = "获取最近一周用户独立IP数和访问量", notes = "获取最近一周用户独立IP数和访问量")
    @RequestMapping(value = "/getVisitByWeek", method = RequestMethod.GET)
    public ResultBase getVisitByWeek() {
        Map<String, Object> visitByWeek = webVisitService.getVisitByWeek();
        return ResultBase.ok().data("res" ,visitByWeek);
    }
}
