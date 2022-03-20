package com.chen.nots_web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chen.nots_web.entity.Tag;
import com.chen.nots_web.global.service.SuperService;
import com.chen.nots_web.vo.TagVO;

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

    List<Tag> getAll();

    List<Tag> getTagContentList(String[] tagUids);


    IPage<Tag> getTagList(TagVO tagVO);
}
