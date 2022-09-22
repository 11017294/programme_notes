package com.chen.nots_web.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.nots_web.entity.Comment;
import com.chen.nots_web.entity.User;
import com.chen.nots_web.mapper.CommentMapper;
import com.chen.nots_web.service.CommentService;
import com.chen.nots_web.service.UserService;
import com.chen.nots_web.vo.CommentRespVo;
import com.chen.nots_web.vo.CommentVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 评论信息 服务实现类
 * </p>
 *
 * @author MaybeBin
 * @since 2022-09-22
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    private UserService userService;

    @Override
    public Comment addComment(Comment comment) {
        // 查询评论用户 信息
        User commentUser = userService.getById(comment.getCommentUserUid());
        if(Objects.nonNull(commentUser)){
            comment.setCommentUserAvatar(commentUser.getAvatar());
            comment.setCommentUserName(commentUser.getUserName());
        }

        // 查询被评论用户 信息
        User targetUser = userService.getById(comment.getTargetUserUid());
        if(Objects.nonNull(targetUser)){
            comment.setTargetUserAvatar(targetUser.getAvatar());
            comment.setTargetUserName(targetUser.getUserName());
        }

        comment.insert();
        return comment;
    }

    @Override
    public CommentRespVo commentInfo(String articleUid) {
        List<Comment> commentList = baseMapper.selectByArticleUid(articleUid);
        if(CollectionUtils.isEmpty(commentList)){
            return null;
        }

        List<CommentVO> commentVOList = commentList.stream().map(comment -> {
                    CommentVO commentVO = new CommentVO();
                    BeanUtils.copyProperties(comment, commentVO);
                    return commentVO;
                })
                .collect(Collectors.toList());

        return CommentRespVo.returnResult(commentVOList, commentList.size());
    }
}
