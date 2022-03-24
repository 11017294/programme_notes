package com.chen.nots_web.mapper;

import com.chen.nots_web.entity.Message;
import com.chen.nots_web.global.mapper.SuperMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * 留言表 Mapper 接口
 * </p>
 *
 * @author MaybeBin
 * @since 2022-03-15
 */
public interface MessageMapper extends SuperMapper<Message> {

    @Update(" update t_message set status = 1 where uid = #{id} ")
    int completion(@Param("id") String id);
}
