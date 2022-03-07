package com.chen.nots_web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chen.nots_web.entity.Collect;
import com.chen.nots_web.entity.Note;
import com.chen.nots_web.global.SQLConf;
import com.chen.nots_web.global.service.serviceImpl.SuperServiceImpl;
import com.chen.nots_web.mapper.CollectMapper;
import com.chen.nots_web.service.CollectService;
import com.chen.nots_web.service.NoteService;
import com.chen.nots_web.vo.UserVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 收藏表 服务实现类
 * </p>
 *
 * @author MaybeBin
 * @since 2022-02-14
 */
@Service
public class CollectServiceImpl extends SuperServiceImpl<CollectMapper, Collect> implements CollectService {

    @Resource
    CollectMapper collectMapper;

    @Resource
    NoteService noteService;

    @Override
    public int getCollectCount(String userUid) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq(SQLConf.USER_UID, userUid);
        return collectMapper.selectCount(wrapper);
    }

    @Override
    public List<Note> getCollectByUserId(UserVO userVO) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.select(SQLConf.NOTE_UID);
        wrapper.eq(SQLConf.USER_UID, userVO.getUid());

        Page page = new Page();
        page.setCurrent(userVO.getCurrentPage());
        page.setSize(userVO.getPageSize());
        List<Collect> collectList = collectMapper.selectPage(page, wrapper).getRecords();
        List<String> noteUidList = new ArrayList<>();
        collectList.forEach(item -> {
            noteUidList.add(item.getNoteUid());
        });

        // 调用getNoteInUid方法获取所收藏的笔记
        return noteService.getNoteInUid(noteUidList);
    }
}
