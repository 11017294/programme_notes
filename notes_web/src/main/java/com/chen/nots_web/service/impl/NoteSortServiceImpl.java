package com.chen.nots_web.service.impl;

import com.chen.nots_web.entity.NoteSort;
import com.chen.nots_web.global.service.serviceImpl.SuperServiceImpl;
import com.chen.nots_web.mapper.NoteSortMapper;
import com.chen.nots_web.service.NoteSortService;
import com.chen.nots_web.vo.NoteSortVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 笔记分类表 服务实现类
 * </p>
 *
 * @author MaybeBin
 * @since 2022-03-07
 */
@Service
public class NoteSortServiceImpl extends SuperServiceImpl<NoteSortMapper, NoteSort> implements NoteSortService {

    @Resource
    private NoteSortMapper noteSortMapper;

    @Override
    public List<NoteSort> getPageList(NoteSortVO noteSortVO) {
        return noteSortMapper.getAll();
    }
}
