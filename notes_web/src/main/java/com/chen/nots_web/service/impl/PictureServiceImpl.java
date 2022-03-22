package com.chen.nots_web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chen.nots_web.entity.Picture;
import com.chen.nots_web.global.service.serviceImpl.SuperServiceImpl;
import com.chen.nots_web.mapper.PictureMapper;
import com.chen.nots_web.service.PictureService;
import com.chen.nots_web.vo.PictureVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 图片表 服务实现类
 * </p>
 *
 * @author MaybeBin
 * @since 2022-03-13
 */
@Service
public class PictureServiceImpl extends SuperServiceImpl<PictureMapper, Picture> implements PictureService {

    @Resource
    private PictureMapper pictureMapper;

    @Override
    public IPage getPictureList(PictureVO pictureVO) {
        QueryWrapper<Picture> wrapper = new QueryWrapper<>();
        Page<Picture> page = new Page<>();
        page.setSize(pictureVO.getPageSize());
        page.setCurrent(pictureVO.getCurrentPage());
        return pictureMapper.selectPage(page, wrapper);
    }

    @Override
    public String deleteImage(PictureVO pictureVO) {
        pictureMapper.deleteById(pictureVO.getUid());
        return pictureVO.getUid();
    }
}
