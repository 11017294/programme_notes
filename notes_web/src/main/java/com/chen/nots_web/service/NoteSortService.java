package com.chen.nots_web.service;

import com.chen.nots_web.entity.NoteSort;
import com.chen.nots_web.global.service.SuperService;
import com.chen.nots_web.vo.NoteSortVO;

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

    List<NoteSort> getPageList(NoteSortVO noteSortVO);
}