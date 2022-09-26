package com.chen.notes_web.mapper;

import com.chen.notes_web.entity.Tag;
import com.chen.notes_web.global.mapper.SuperMapper;

import java.util.List;

/**
 * <p>
 * 标签表 Mapper 接口
 * </p>
 *
 * @author MaybeBin
 * @since 2022-03-07
 */
public interface TagMapper extends SuperMapper<Tag> {

    List<Tag> getAll();

    List<Tag> getTagContentList(String[] tagUids);
}
