package com.chen.nots_web.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chen.nots_web.entity.Collect;
import com.chen.nots_web.entity.Note;
import com.chen.nots_web.entity.NoteSort;
import com.chen.nots_web.entity.Tag;
import com.chen.nots_web.global.RedisConf;
import com.chen.nots_web.global.SQLConf;
import com.chen.nots_web.global.SysConf;
import com.chen.nots_web.global.service.serviceImpl.SuperServiceImpl;
import com.chen.nots_web.mapper.NoteMapper;
import com.chen.nots_web.service.*;
import com.chen.nots_web.utils.DateUtils;
import com.chen.nots_web.utils.JsonUtils;
import com.chen.nots_web.utils.RedisUtil;
import com.chen.nots_web.vo.CollectVO;
import com.chen.nots_web.vo.NoteVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author：MaybeBin
 * @date: 2022-01-25 10:38
 */
@Service
public class NoteServiceImpl extends SuperServiceImpl<NoteMapper, Note> implements NoteService {

    @Resource
    private NoteMapper noteMapper;
    @Autowired
    private TagService tagService;
    @Autowired
    private NoteSortService noteSortService;
    @Autowired
    private UserService userService;
    @Autowired
    private CollectService collectService;
    @Autowired
    private RedisUtil redisUtil;

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
        }
        if(StrUtil.isNotBlank(noteVO.getNoteSortUid())){
            wrapper.eq(SQLConf.NOTE_SORT_UID, noteVO.getNoteSortUid());
        }
        if(StrUtil.isNotBlank(noteVO.getTagUid())){
            wrapper.like(SQLConf.TAG_UID, noteVO.getTagUid());
        }
        if(StrUtil.isNotBlank(noteVO.getIsPublish())){
            wrapper.eq(SQLConf.IS_PUBLISH, noteVO.getIsPublish());
        }
        if(StrUtil.isNotBlank(noteVO.getIsOriginal())){
            wrapper.eq(SQLConf.IS_ORIGINAL, noteVO.getIsOriginal());
        }
        wrapper.select(Note.class, i -> !i.getProperty().equals(SQLConf.CONTENT));
        Page page = new Page(noteVO.getCurrentPage(), noteVO.getPageSize());
        IPage<Note> noteList = noteMapper.selectPage(page, wrapper);
        for (Note note : noteList.getRecords()) {
            this.setSortAndTagByNote(note);
        }
        return noteList;
    }

    @Override
    public IPage getNoteByUserId(NoteVO noteVO) {
        QueryWrapper<Note> wrapper = new QueryWrapper<>();
        if(StrUtil.isNotBlank(noteVO.getUserUid())){
            wrapper.eq(SQLConf.USER_UID, noteVO.getUserUid());
        }
        Page<Note> page = new Page<>(noteVO.getCurrentPage(), noteVO.getPageSize());
        IPage<Note> noteList = noteMapper.selectPage(page, wrapper);
        for (Note note : noteList.getRecords()) {
            this.setSortAndTagByNote(note);
        }
        return noteList;
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
        BeanUtil.copyProperties(noteVO, note);
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

    @Override
    public Note setSortAndTagByNote(Note note) {
        this.setSortByNote(note);
        this.setTagByNote(note);
        this.setUserAvatar(note);
        return note;
    }

    @Override
    public Note setSortByNote(Note note) {
        NoteSort noteSort = noteSortService.getById(note.getNoteSortUid());
        if(ObjectUtil.isNotEmpty(noteSort)){
            note.setNoteSortName(noteSort.getSortName());
        }
        return note;
    }

    @Override
    public Note setTagByNote(Note note) {
        if(StrUtil.isNotBlank(note.getTagUid())){
            String[] tagUids = note.getTagUid().split(",");
            List<Tag> tagList = tagService.getTagContentList(tagUids);
            note.setTagList(tagList);
        }
        return note;
    }

    @Override
    public Note setUserAvatar(Note note) {
        if(StrUtil.isNotBlank(note.getUserUid())){
            String avatar = userService.getAvatar(note.getUserUid());
            note.setUserAvatar(avatar);
        }
        return note;
    }

    @Override
    public IPage getCollectNoteByUserId(CollectVO collectVO) {
        List<Collect> collectList = collectService.getCollectListByUserUid(collectVO.getUserUid());
        List<String> noteUidList = new ArrayList<>();
        collectList.forEach(item -> {
            noteUidList.add(item.getNoteUid());
        });
        if(noteUidList.size() <= 0) {
            return new Page();
        }
        QueryWrapper<Note> wrapper = new QueryWrapper<>();
        wrapper.in(SQLConf.UID, noteUidList);
        Page page = new Page<>();
        page.setCurrent(collectVO.getCurrentPage());
        page.setSize(collectVO.getPageSize());
        return noteMapper.selectPage(page, wrapper);
    }

    @Override
    public IPage getNoteList(NoteVO noteVO) {
        QueryWrapper<Note> wrapper = new QueryWrapper<>();
        if(StrUtil.isNotBlank(noteVO.getTagUid())) {
            wrapper.like(SQLConf.TAG_UID, noteVO.getTagUid());
        }
        if(StrUtil.isNotBlank(noteVO.getNoteSortUid())) {
            wrapper.eq(SQLConf.NOTE_SORT_UID, noteVO.getNoteSortUid());
        }
        wrapper.select(Note.class, i -> !i.getProperty().equals(SQLConf.CONTENT));
        Page page = new Page(noteVO.getCurrentPage(), noteVO.getPageSize());
        IPage<Note> noteList = noteMapper.selectPage(page, wrapper);
        for (Note note : noteList.getRecords()) {
            this.setSortAndTagByNote(note);
        }
        return noteList;
    }

    @Override
    public Map<String, Object> getNoteContributeCount() {
        // 从Redis中获取博客分类下包含的博客数量
        String jsonMap = redisUtil.get(RedisConf.DASHBOARD + RedisConf.SYMBOL_COLON + RedisConf.NOTE_CONTRIBUTE_COUNT);
        if (StrUtil.isNotBlank(jsonMap)) {
            Map<String, Object> resultMap = JsonUtils.jsonToMap(jsonMap);
            return resultMap;
        }

        // 获取今天结束时间
        String endTime = DateUtils.getNowTime();
        // 获取365天前的日期
        Date temp = DateUtils.getDate(endTime, -365);
        String startTime = DateUtils.dateTimeToStr(temp);
        List<Map<String, Object>> blogContributeMap = noteMapper.getNoteContributeCount(startTime, endTime);
        List<String> dateList = DateUtils.getDayBetweenDates(startTime, endTime);
        Map<String, Object> dateMap = new HashMap<>();
        for (Map<String, Object> itemMap : blogContributeMap) {
            dateMap.put(itemMap.get("DATE").toString(), itemMap.get("COUNT"));
        }

        List<List<Object>> resultList = new ArrayList<>();
        for (String item : dateList) {
            Integer count = 0;
            if (dateMap.get(item) != null) {
                count = Integer.valueOf(dateMap.get(item).toString());
            }
            List<Object> objectList = new ArrayList<>();
            objectList.add(item);
            objectList.add(count);
            resultList.add(objectList);
        }

        Map<String, Object> resultMap = new HashMap<>(2);
        List<String> contributeDateList = new ArrayList<>();
        contributeDateList.add(startTime);
        contributeDateList.add(endTime);
        resultMap.put(SysConf.CONTRIBUTE_DATE, contributeDateList);
        resultMap.put(SysConf.NOTE_CONTRIBUTE_COUNT, resultList);
        // 将 全年博客贡献度 存入到Redis【过期时间2小时】
        redisUtil.setEx(RedisConf.DASHBOARD + RedisConf.SYMBOL_COLON + RedisConf.NOTE_CONTRIBUTE_COUNT, JsonUtils.objectToJson(resultMap), 2, TimeUnit.HOURS);
        return resultMap;
    }

}
