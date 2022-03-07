package com.chen.nots_web.mapper;

import com.chen.nots_web.entity.NoteSort;
import com.chen.nots_web.global.mapper.SuperMapper;

import java.util.List;

/**
 * <p>
 * 笔记分类表 Mapper 接口
 * </p>
 *
 * @author MaybeBin
 * @since 2022-03-07
 */
public interface NoteSortMapper extends SuperMapper<NoteSort> {

    List<NoteSort> getAll();
}
