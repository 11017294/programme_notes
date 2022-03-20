package com.chen.nots_web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chen.nots_web.entity.Tag;
import com.chen.nots_web.global.service.serviceImpl.SuperServiceImpl;
import com.chen.nots_web.mapper.TagMapper;
import com.chen.nots_web.service.TagService;
import com.chen.nots_web.vo.TagVO;
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
    public List<Tag> getAll() {
        return tagMapper.getAll();
    }

    @Override
    public List<String> getTagContentList(String[] tagUids) {
        if(tagUids.length < 1){
            return null;
        }
        return tagMapper.getTagContentList(tagUids);
    }

    @Override
    public IPage<Tag> getTagList(TagVO tagVO) {
        QueryWrapper<Tag> wrapper = new QueryWrapper<>();
        Page<Tag> page = new Page<>();
        page.setSize(tagVO.getPageSize());
        page.setCurrent(tagVO.getCurrentPage());
        return tagMapper.selectPage(page, wrapper);
    }
}
