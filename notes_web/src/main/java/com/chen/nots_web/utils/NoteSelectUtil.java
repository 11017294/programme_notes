package com.chen.nots_web.utils;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chen.nots_web.global.SQLConf;
import com.chen.nots_web.vo.NoteVO;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author：MaybeBin
 * @Date: 2022-03-14 14-47
 */
@Data
@Component
public class NoteSelectUtil<T> {

    private QueryWrapper<T> wrapper;

    private NoteVO note;

    public NoteSelectUtil(NoteVO note) {
        this.note = note;
        wrapper = new QueryWrapper<T>();
    }

    /**
     * 笔记uid
     * @return
     */
    public NoteSelectUtil<T> uidEq() {
        if(StrUtil.isNotBlank(note.getUserUid())){
            this.wrapper.eq(SQLConf.UID, note.getUserUid());
        }
        return this;
    }

    /**
     * 标签
     * @return
     */
    public NoteSelectUtil<T> tagUidLike() {
        if(StrUtil.isNotBlank(note.getTagUid())) {
            this.wrapper.like(SQLConf.TAG_UID, note.getTagUid());
        }
        return this;
    }

    /**
     * 是否原创
     * @return
     */
    public NoteSelectUtil<T> isOriginalEq() {
        if(StrUtil.isNotBlank(note.getIsOriginal())) {
            this.wrapper.eq(SQLConf.IS_ORIGINAL, note.getIsOriginal());
        }
        return this;
    }

    /**
     * 分类
     * @return
     */
    public NoteSelectUtil<T> noteSortUidEq() {
        if(StrUtil.isNotBlank(note.getNoteSortUid())) {
            this.wrapper.eq(SQLConf.NOTE_SORT_UID, note.getNoteSortUid());
        }
        return this;
    }

    /**
     * 是否发布
     * @return
     */
    public NoteSelectUtil<T> isPublishEq() {
        if(StrUtil.isNotBlank(note.getIsPublish())) {
            this.wrapper.eq(SQLConf.IS_PUBLISH, note.getIsPublish());
        }
        return this;
    }

    /**
     * 投稿人uid
     * @return
     */
    public NoteSelectUtil<T> userUidEq() {
        if(StrUtil.isNotBlank(note.getUserUid())) {
            this.wrapper.eq(SQLConf.USER_UID, note.getUserUid());
        }
        return this;
    }

    public NoteSelectUtil<T> noSelectContent(Class myClass) {
        this.wrapper.select(myClass, i -> !i.getProperty().equals(SQLConf.CONTENT));
        return this;
    }

}
