package com.chen.notes_web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chen.notes_web.entity.Comment;
import com.chen.notes_web.vo.CommentRespVo;

/**
 * <p>
 * 评论信息 服务类
 * </p>
 *
 * @author MaybeBin
 * @since 2022-09-22
 */
public interface CommentService extends IService<Comment> {

    Comment addComment(Comment comment);

    CommentRespVo commentInfo(String articleUid);
}
