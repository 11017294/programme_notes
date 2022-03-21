package com.chen.nots_web.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chen.nots_web.entity.Tag;
import com.chen.nots_web.global.SQLConf;
import com.chen.nots_web.global.service.serviceImpl.SuperServiceImpl;
import com.chen.nots_web.mapper.TagMapper;
import com.chen.nots_web.service.TagService;
import com.chen.nots_web.vo.TagVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
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
    public List<Tag> getTagContentList(String[] tagUids) {
        if(tagUids.length < 1){
            return null;
        }
        return tagMapper.getTagContentList(tagUids);
    }

    @Override
    public IPage<Tag> getTagList(TagVO tagVO) {
        QueryWrapper<Tag> wrapper = new QueryWrapper<>();
        Page<Tag> page = new Page<>();
        if(StrUtil.isNotBlank(tagVO.getKeyword())){
            wrapper.like(SQLConf.CONTENT, tagVO.getKeyword());
        }
        page.setSize(tagVO.getPageSize());
        page.setCurrent(tagVO.getCurrentPage());
        return tagMapper.selectPage(page, wrapper);
    }

    @Override
    public String addTag(TagVO tagVO) {
        Tag tag = new Tag();
        tag.setContent(tagVO.getContent());
        tag.setClickCount(0);
        tag.setSort(tagVO.getSort());
        tag.insert();
        return tag.getUid();
    }

    @Override
    public String editTag(TagVO tagVO) {
        Tag tag = this.getById(tagVO.getUid());
        tag.setContent(tagVO.getContent());
        tag.setSort(tagVO.getSort());
        tag.setUpdateTime(new Date());
        tag.updateById();
        return tag.getUid();
    }
}
