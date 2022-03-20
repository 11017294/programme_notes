package com.chen.nots_web.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chen.nots_web.entity.NoteSort;
import com.chen.nots_web.global.SQLConf;
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
    public List<NoteSort> getAllSort(NoteSortVO noteSortVO) {
        return noteSortMapper.getAll();
    }

    @Override
    public IPage<NoteSort> getPageList(NoteSortVO noteSortVO) {
        QueryWrapper<NoteSort> wrapper = new QueryWrapper<>();
        Page<NoteSort> page = new Page<>();
        if(StrUtil.isNotBlank(noteSortVO.getKeyword())){
            wrapper.like(SQLConf.SORT_NAME, noteSortVO.getKeyword());
        }
        page.setSize(noteSortVO.getPageSize());
        page.setCurrent(noteSortVO.getCurrentPage());
        return noteSortMapper.selectPage(page, wrapper);
    }
}
