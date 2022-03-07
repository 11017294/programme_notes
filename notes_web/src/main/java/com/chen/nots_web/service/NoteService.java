package com.chen.nots_web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chen.nots_web.entity.Note;
import com.chen.nots_web.global.service.SuperService;
import com.chen.nots_web.vo.NoteVO;

import java.util.List;

/**
 *
 * @author：MaybeBin
 * @date: 2022-01-25 10:34
 */
public interface NoteService extends SuperService<Note> {

    /**
     * 查询笔记列表，可模糊查询
     * @param noteVO
     * @return
     */
    IPage search(NoteVO noteVO);

    /**
     * 通过用户uid获取笔记列表
     * @param noteVO
     * @return
     */
    IPage getNoteByUserId(NoteVO noteVO);

    /**
     * 通过inUid list集合获取笔记
     * @param uidList
     */
    List<Note> getNoteInUid(List<String> uidList);

    /**
     * 添加笔记
     * @param noteVO
     * @return
     */
    String addNote(NoteVO noteVO);

    /**
     * 编辑笔记
     * @param noteVO
     * @return
     */
    String editNote(NoteVO noteVO);
}
