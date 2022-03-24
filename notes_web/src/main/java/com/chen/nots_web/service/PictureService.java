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

    /**
     * 获取图片列表
     * @param pictureVO
     * @return
     */
    IPage getPictureList(PictureVO pictureVO);

    /**
     * 删除图片
     * @param pictureVO
     * @return
     */
    String deleteImage(PictureVO pictureVO);
}
