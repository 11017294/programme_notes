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

    /**
     * 获取全部标签
     * @return
     */
    List<Tag> getAll();

    /**
     * 根据标签uid数组查询标签
     * @param tagUids
     * @return
     */
    List<Tag> getTagContentList(String[] tagUids);

    /**
     * 获取标签列表（分页）
     * @param tagVO
     * @return
     */
    IPage<Tag> getTagList(TagVO tagVO);

    /**
     * 添加标签
     * @param tagVO
     * @return
     */
    String addTag(TagVO tagVO);

    /**
     * 修改标签
     * @param tagVO
     * @return
     */
    String editTag(TagVO tagVO);
}
