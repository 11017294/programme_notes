package com.chen.nots_web.task;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chen.nots_web.entity.Note;
import com.chen.nots_web.global.SQLConf;
import com.chen.nots_web.mapper.NoteMapper;
import com.chen.nots_web.service.NoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 笔记定时任务
 * </p>
 *
 * @author: MaybeBin
 * @createTime: 2022-03-27
 */
@Component
@Slf4j
public class NoteTask {

    @Resource
    private NoteMapper noteMapper;
    @Autowired
    private NoteService noteService;

    /**
     * 笔记归档定时任务
     * 每小时进行一次归档
     */
    @Scheduled(cron = "0 0 */1 * * ?")
    public void sortRestTask() {
        log.info("笔记归档定时任务开始");
        QueryWrapper<Note> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc(SQLConf.CREATE_TIME);
        wrapper.select(Note.class, i -> !i.getProperty().equals(SQLConf.CONTENT));
        List<Note> noteList = noteMapper.selectList(wrapper);
        // 调用方法进行归档
        noteService.noteTimeSort(noteList);
    }

}
