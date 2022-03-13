package com.chen.nots_web.service;

import com.chen.nots_web.entity.Collect;
import com.chen.nots_web.global.service.SuperService;

import java.util.List;

/**
 * <p>
 * 收藏表 服务类
 * </p>
 *
 * @author MaybeBin
 * @since 2022-03-13
 */
public interface CollectService extends SuperService<Collect> {

    /**
     * 收藏笔记
     * @param userUid
     * @param noteUid
     * @return
     */
    int userCollectNote(String userUid, String noteUid);

    /**
     * 根据用户uid查询收藏记录
     * @param UserUid
     * @return
     */
    List<Collect> getCollectListByUserUid(String UserUid);
}
