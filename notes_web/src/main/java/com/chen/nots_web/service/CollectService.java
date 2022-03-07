package com.chen.nots_web.service;

import com.chen.nots_web.entity.Collect;
import com.chen.nots_web.entity.Note;
import com.chen.nots_web.global.service.SuperService;
import com.chen.nots_web.vo.UserVO;

import java.util.List;

/**
 * <p>
 * 收藏表 服务类
 * </p>
 *
 * @author MaybeBin
 * @since 2022-02-14
 */
public interface CollectService extends SuperService<Collect> {

    /**
     * 根据用户uid获取收藏数
     * @param userUid
     * @return
     */
    int getCollectCount(String userUid);

    /**
     * 根据用户uid获取收藏的笔记uid
     * 根据笔记uid查询所收藏的笔记
     * @param userVO
     * @return
     */
    List<Note> getCollectByUserId(UserVO userVO);
}
