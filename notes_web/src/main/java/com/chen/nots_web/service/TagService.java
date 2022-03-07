package com.chen.nots_web.service;

import com.chen.nots_web.entity.Tag;
import com.chen.nots_web.global.service.SuperService;

import java.util.List;

/**
 * <p>
 * 标签表 服务类
 * </p>
 *
 * @author MaybeBin
 * @since 2022-03-07
 */
public interface TagService extends SuperService<Tag> {

    List<Tag> getPageList();
}
