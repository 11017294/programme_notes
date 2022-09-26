package com.chen.notes_web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chen.notes_web.entity.Note;
import com.chen.notes_web.global.service.SuperService;
import com.chen.notes_web.vo.CollectVO;
import com.chen.notes_web.vo.NoteVO;

import java.util.List;
import java.util.Map;

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

    /**
     * 对分类和标签进行处理
     * @return
     */
    Note setSortAndTagByNote(Note note);

    /**
     * 对分类进行处理
     * @return
     */
    Note setSortByNote(Note note);

    /**
     * 对标签进行处理
     * @return
     */
    Note setTagByNote(Note note);

    /**
     * 获取用户头像
     * @param note
     * @return
     */
    Note setUserAvatar(Note note);

    /**
     * 通过用户uid获取笔记列表
     * @param collectVO
     * @return
     */
    IPage getCollectNoteByUserId(CollectVO collectVO);

    /**
     * 根据条件获取笔记列表
     * @param noteVO
     * @return
     */
    IPage getNoteList(NoteVO noteVO);

    /**
     * 获取系统一年内文章贡献数
     * @return
     */
    Map<String, Object> getNoteContributeCount();

    /**
     * 获取归档日期
     * @return
     */
    List<String> getNoteTimeSortList();

    /**
     * 按月份获取笔记
     * @param monthDate
     * @return
     */
    List<Note> getArticleByMonth(String monthDate);

    /**
     * 根据数据库中所有笔记进行归档
     * @param noteList
     */
    void noteTimeSort(List<Note> noteList);
}
