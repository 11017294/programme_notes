package com.chen.notes_web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chen.notes_web.entity.NoteSort;
import com.chen.notes_web.global.service.SuperService;
import com.chen.notes_web.vo.NoteSortVO;

import java.util.List;

/**
 * <p>
 * 笔记分类表 服务类
 * </p>
 *
 * @author MaybeBin
 * @since 2022-03-07
 */
public interface NoteSortService extends SuperService<NoteSort> {

    List<NoteSort> getAllSort(NoteSortVO noteSortVO);

    IPage<NoteSort> getPageList(NoteSortVO noteSortVO);

    String addNoteSort(NoteSortVO noteSortVO);

    String editNoteSort(NoteSortVO noteSortVO);
}
