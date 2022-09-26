package com.chen.notes_web.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chen.notes_web.entity.Collect;
import com.chen.notes_web.entity.Note;
import com.chen.notes_web.global.SQLConf;
import com.chen.notes_web.global.SysConf;
import com.chen.notes_web.global.service.serviceImpl.SuperServiceImpl;
import com.chen.notes_web.mapper.CollectMapper;
import com.chen.notes_web.service.CollectService;
import com.chen.notes_web.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 收藏表 服务实现类
 * </p>
 *
 * @author MaybeBin
 * @since 2022-03-13
 */
@Service
public class CollectServiceImpl extends SuperServiceImpl<CollectMapper, Collect> implements CollectService {

    @Resource
    private CollectMapper collectMapper;
    @Autowired
    private NoteService noteService;

    @Override
    public int userCollectNote(String userUid, String noteUid) {
        QueryWrapper<Collect> wrapper = new QueryWrapper<>();
        wrapper.eq(SQLConf.USER_UID, userUid);
        wrapper.eq(SQLConf.NOTE_UID, noteUid);
        wrapper.last(SysConf.LIMIT_ONE);
        Collect isCollect = collectMapper.selectOne(wrapper);
        if(ObjectUtil.isNotEmpty(isCollect)){
            // 修改收藏数
            Note note = noteService.getById(noteUid);
            note.setCollectCount(note.getCollectCount() - 1);
            note.setUpdateTime(new Date());
            note.updateById();
            // 删除收藏记录
            collectMapper.deleteCollectByUid(isCollect.getUid());
            return note.getCollectCount();
        }
        // 修改收藏数
        Note note = noteService.getById(noteUid);
        note.setCollectCount(note.getCollectCount() + 1);
        note.setUpdateTime(new Date());
        note.updateById();
        // 添加收藏
        Collect collect = new Collect();
        collect.setNoteUid(noteUid);
        collect.setUserUid(userUid);
        collect.insert();
        return note.getCollectCount();
    }

    @Override
    public List<Collect> getCollectListByUserUid(String UserUid) {
        QueryWrapper<Collect> wrapper = new QueryWrapper<>();
        wrapper.eq(SQLConf.USER_UID, UserUid);
        return collectMapper.selectList(wrapper);
    }
}
