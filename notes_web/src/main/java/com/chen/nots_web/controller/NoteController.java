package com.chen.nots_web.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.chen.nots_web.annotion.log.BussinessLog;
import com.chen.nots_web.global.enums.EBehavior;
import com.chen.nots_web.service.NoteService;
import com.chen.nots_web.vo.CollectVO;
import com.chen.nots_web.vo.NoteVO;
import com.chen.nots_web.vo.ResultBase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author：MaybeBin
 * @date: 2022-01-25 12:27
 */
@Api(value = "笔记相关接口", tags = {"笔记相关接口"})
@RestController
@RequestMapping("/note")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @BussinessLog(value = "搜索笔记", behavior = EBehavior.NOTE_SEARCH)
    @ApiOperation(value = "模糊查询/获取笔记列表", notes = "模糊查询/获取笔记列表")
    @GetMapping("/search")
    public ResultBase search(NoteVO noteVO) {
        return ResultBase.ok().data("list", noteService.search(noteVO));
    }

    @BussinessLog(value = "点击归档", behavior = EBehavior.VISIT_SORT)
    @ApiOperation(value = "根据条件获取笔记列表", notes = "根据条件获取笔记列表")
    @GetMapping("/getNoteList")
    public ResultBase getNoteList(NoteVO noteVO) {
        return ResultBase.ok().data("list", noteService.getNoteList(noteVO));
    }

    @ApiOperation(value = "根据用户id获取笔记", notes = "根据用户id获取笔记")
    @GetMapping("/getNoteByUserId")
    public ResultBase getNoteByUserId(NoteVO noteVO){
        return ResultBase.ok().data("list", noteService.getNoteByUserId(noteVO));
    }

    @ApiOperation(value = "根据用户id获取收藏的笔记", notes = "根据用户id获取收藏的笔记")
    @GetMapping("/getCollectNoteByUserId")
    public ResultBase getCollectNoteByUserId(CollectVO collectVO){
        return ResultBase.ok().data("list", noteService.getCollectNoteByUserId(collectVO));
    }

    @BussinessLog(value = "增加笔记", behavior = EBehavior.PUBLISH_NOTE)
    @ApiOperation(value = "增加笔记", notes = "增加笔记")
    @PostMapping("/add")
    public ResultBase add(NoteVO noteVO) {
        return ResultBase.ok().data("id", noteService.addNote(noteVO));
    }

    @BussinessLog(value = "删除笔记", behavior = EBehavior.DELETE_NOTE)
    @ApiOperation(value = "删除笔记", notes = "删除笔记")
    @PostMapping("/delete")
    public ResultBase delete(@ApiParam(name = "uid", value = "笔记UID") String uid) {
        if(StringUtils.isBlank(uid)){
            return ResultBase.error("删除失败，没有传入uid");
        }
        boolean note = noteService.removeById(uid);
        if(note){
            return ResultBase.ok().data("id",uid);
        }
        return ResultBase.error("删除失败，没有ID:" + uid + "的笔记");
    }

    @BussinessLog(value = "修改笔记", behavior = EBehavior.EDIT_NOTE)
    @ApiOperation(value = "修改笔记", notes = "修改笔记")
    @PostMapping("/update")
    public ResultBase update(NoteVO noteVO) {
        if(StringUtils.isNotBlank(noteVO.getUid())){
            return ResultBase.ok().data("id",noteService.editNote(noteVO));
        }
        return ResultBase.error("没有传入uid参数");
    }
}
