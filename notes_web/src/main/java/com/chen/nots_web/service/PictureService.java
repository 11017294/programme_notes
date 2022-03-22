package com.chen.nots_web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chen.nots_web.entity.Picture;
import com.chen.nots_web.global.service.SuperService;
import com.chen.nots_web.vo.PictureVO;

/**
 * <p>
 * 图片表 服务类
 * </p>
 *
 * @author MaybeBin
 * @since 2022-03-13
 */
public interface PictureService extends SuperService<Picture> {

    IPage getPictureList(PictureVO pictureVO);
}
