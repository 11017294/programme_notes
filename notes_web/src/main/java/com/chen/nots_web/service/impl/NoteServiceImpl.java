package com.chen.nots_web.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chen.nots_web.entity.Note;
import com.chen.nots_web.global.SQLConf;
import com.chen.nots_web.global.service.serviceImpl.SuperServiceImpl;
import com.chen.nots_web.mapper.NoteMapper;
import com.chen.nots_web.service.NoteService;
import com.chen.nots_web.vo.NoteVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 *
 * @author：MaybeBin
 * @date: 2022-01-25 10:38
 */
@Service
public class NoteServiceImpl extends SuperServiceImpl<NoteMapper, Note> implements NoteService {

    @Resource
    NoteMapper noteMapper;

    @Override
    public IPage search(NoteVO noteVO) {
        QueryWrapper<Note> wrapper = new QueryWrapper<>();
        String keyword = noteVO.getKeyword();
        if(StrUtil.isNotBlank(keyword)){
            wrapper.like(SQLConf.TITLE, keyword)
                    .or().like(SQLConf.SUMMARY, keyword)
                    .or().like(SQLConf.CONTENT, keyword)
                    .or().like(SQLConf.TAG_UID, keyword)
                    .or().like(SQLConf.AUTHOR, keyword);
        } else{
            wrapper.select(Note.class, i -> !i.getProperty().equals(SQLConf.CONTENT));
        }
        Page page = new Page(noteVO.getCurrentPage(), noteVO.getPageSize());
        IPage<Note> noteList = noteMapper.selectPage(page, wrapper);
        return noteList;
    }

    @Override
    public IPage getNoteByUserId(NoteVO noteVO) {
        QueryWrapper<Note> wrapper = new QueryWrapper<>();
        if(StrUtil.isNotBlank(noteVO.getUserUid())){
            wrapper.eq(SQLConf.USER_UID, noteVO.getUserUid());
        }
        Page<Note> page = new Page<>(noteVO.getCurrentPage(), noteVO.getPageSize());
        return noteMapper.selectPage(page, wrapper);
    }

    @Override
    public List<Note> getNoteInUid(List<String> uidList) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.in(SQLConf.UID, uidList);
        return noteMapper.selectList(wrapper);
    }

    @Override
    public String addNote(NoteVO noteVO) {
        Note note = new Note();
        note.setTitle(noteVO.getTitle());
        note.setSummary(noteVO.getSummary());
        note.setContent(noteVO.getContent());
        note.setTagUid(noteVO.getTagUid());
        note.setFileUid(noteVO.getFileUid());
        note.setIsOriginal(noteVO.getIsOriginal());
        note.setAuthor(noteVO.getAuthor());
        note.setArticlesPart(noteVO.getArticlesPart());
        note.setNoteSortUid(noteVO.getNoteSortUid());
        note.setIsPublish(note.getIsPublish());
        note.setOpenComment(noteVO.getOpenComment());
        note.setUserUid(noteVO.getUserUid());
        note.setArticleSource(noteVO.getArticleSource());
        note.insert();
        return note.getUid();
    }

    @Override
    public String editNote(NoteVO noteVO) {
        Note note = noteMapper.selectById(noteVO.getUid());
        note.setTitle(noteVO.getTitle());
        note.setSummary(noteVO.getSummary());
        note.setContent(noteVO.getContent());
        note.setTagUid(noteVO.getTagUid());
        note.setFileUid(noteVO.getFileUid());
        note.setIsOriginal(noteVO.getIsOriginal());
        note.setAuthor(noteVO.getAuthor());
        note.setArticlesPart(noteVO.getArticlesPart());
        note.setNoteSortUid(noteVO.getNoteSortUid());
        note.setIsPublish(note.getIsPublish());
        note.setOpenComment(noteVO.getOpenComment());
        note.setUserUid(noteVO.getUserUid());
        note.setArticleSource(noteVO.getArticleSource());
        note.setUpdateTime(new Date());
        note.updateById();
        return note.getUid();
    }
}
