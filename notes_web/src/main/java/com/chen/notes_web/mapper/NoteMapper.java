package com.chen.notes_web.mapper;

import com.chen.notes_web.entity.Note;
import com.chen.notes_web.global.mapper.SuperMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 *
 * @author：MaybeBin
 * @date: 2022-01-25 10:34
 */
public interface NoteMapper extends SuperMapper<Note> {


    /**
     * 获取一年内的文章贡献数
     *
     * @param startTime
     * @param endTime
     * @return
     */
    @Select("SELECT DISTINCT DATE_FORMAT(create_time, '%Y-%m-%d') DATE, COUNT(uid) COUNT " +
            "FROM t_note WHERE 1=1 && is_delete = 0 && create_time >= #{startTime} && " +
            "create_time < #{endTime} GROUP BY DATE_FORMAT(create_time, '%Y-%m-%d')")
    List<Map<String, Object>> getNoteContributeCount(@Param("startTime") String startTime, @Param("endTime") String endTime);

}
