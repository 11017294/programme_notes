package com.chen.notes_web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.chen.notes_web.annotion.log.BussinessLog;
import com.chen.notes_web.exception.BusinessException;
import com.chen.notes_web.exception.ErrorCode;
import com.chen.notes_web.global.enums.EBehavior;
import com.chen.notes_web.service.NoteService;
import com.chen.notes_web.vo.*;
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
    public BaseResponse<IPage> search(NoteVO noteVO) {
        IPage notePage = noteService.search(noteVO);
        return ResultUtils.success(notePage);
    }

    @BussinessLog(value = "点击归档", behavior = EBehavior.VISIT_SORT)
    @ApiOperation(value = "根据条件获取笔记列表", notes = "根据条件获取笔记列表")
    @GetMapping("/getNoteList")
    public BaseResponse<IPage> getNoteList(NoteVO noteVO) {
        IPage notePage = noteService.getNoteList(noteVO);
        return ResultUtils.success(notePage);
    }

    @ApiOperation(value = "根据用户id获取笔记", notes = "根据用户id获取笔记")
    @GetMapping("/getNoteByUserId")
    public BaseResponse<IPage> getNoteByUserId(NoteVO noteVO){
        IPage notePage = noteService.getNoteByUserId(noteVO);
        return ResultUtils.success(notePage);
    }

    @ApiOperation(value = "根据用户id获取收藏的笔记", notes = "根据用户id获取收藏的笔记")
    @GetMapping("/getCollectNoteByUserId")
    public BaseResponse<IPage> getCollectNoteByUserId(CollectVO collectVO){
        IPage notePage = noteService.getCollectNoteByUserId(collectVO);
        return ResultUtils.success(notePage);
    }

    @BussinessLog(value = "增加笔记", behavior = EBehavior.PUBLISH_NOTE)
    @ApiOperation(value = "增加笔记", notes = "增加笔记")
    @PostMapping("/add")
    public String add(NoteVO noteVO) {
        return noteService.addNote(noteVO);
    }

    @BussinessLog(value = "删除笔记", behavior = EBehavior.DELETE_NOTE)
    @ApiOperation(value = "删除笔记", notes = "删除笔记")
    @PostMapping("/delete")
    public String delete(@ApiParam(name = "uid", value = "笔记UID") String uid) {
        if(StringUtils.isBlank(uid)){
            throw new BusinessException(ErrorCode.REQUEST_PARAMS_ERROR);
        }
        boolean note = noteService.removeById(uid);
        if(!note){
            throw new BusinessException(ErrorCode.NOT_FOUND);
        }
        return uid;
    }

    @BussinessLog(value = "修改笔记", behavior = EBehavior.EDIT_NOTE)
    @ApiOperation(value = "修改笔记", notes = "修改笔记")
    @PostMapping("/update")
    public String update(NoteVO noteVO) {
        if(StringUtils.isBlank(noteVO.getUid())){
            throw new BusinessException(ErrorCode.REQUEST_PARAMS_ERROR);
        }
        return noteService.editNote(noteVO);
    }
}
