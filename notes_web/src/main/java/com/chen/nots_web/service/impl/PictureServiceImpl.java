package com.chen.nots_web.service.impl;

import com.chen.nots_web.entity.Picture;
import com.chen.nots_web.global.service.serviceImpl.SuperServiceImpl;
import com.chen.nots_web.mapper.PictureMapper;
import com.chen.nots_web.service.PictureService;
import org.springframework.stereotype.Service;

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

}
