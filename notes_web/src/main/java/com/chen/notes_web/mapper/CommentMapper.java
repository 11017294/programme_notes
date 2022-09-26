package com.chen.notes_web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chen.notes_web.entity.Comment;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 评论信息 Mapper 接口
 * </p>
 *
 * @author MaybeBin
 * @since 2022-09-22
 */
public interface CommentMapper extends BaseMapper<Comment> {

    @Select(" select * from t_comment where article_uid = #{articleUid} and is_delete = 0 order by create_Time desc")
    List<Comment> selectByArticleUid(String articleUid);

}
