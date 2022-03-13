package com.chen.nots_web.mapper;

import com.chen.nots_web.entity.Collect;
import com.chen.nots_web.global.mapper.SuperMapper;
import org.apache.ibatis.annotations.Delete;

/**
 * <p>
 * 收藏表 Mapper 接口
 * </p>
 *
 * @author MaybeBin
 * @since 2022-03-13
 */
public interface CollectMapper extends SuperMapper<Collect> {

    @Delete("delete from t_collect where uid = #{uid}")
    int deleteCollectByUid(String uid);

}
