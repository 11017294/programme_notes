package com.chen.notes_web.mapper;

import com.chen.notes_web.entity.Message;
import com.chen.notes_web.global.mapper.SuperMapper;
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

    @Update(" update t_message set status = 2 where uid = #{id} ")
    int completion(@Param("id") String id);
}
