package com.chen.nots_web.service.impl;

import com.chen.nots_web.entity.Tag;
import com.chen.nots_web.global.service.serviceImpl.SuperServiceImpl;
import com.chen.nots_web.mapper.TagMapper;
import com.chen.nots_web.service.TagService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 标签表 服务实现类
 * </p>
 *
 * @author MaybeBin
 * @since 2022-03-07
 */
@Service
public class TagServiceImpl extends SuperServiceImpl<TagMapper, Tag> implements TagService {

    @Resource
    private TagMapper tagMapper;

    @Override
    public List<Tag> getPageList() {
        return tagMapper.getAll();
    }
}
