package com.chen.nots_web.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
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

import java.util.ArrayList;
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
            comment.setCommentUserNickName(commentUser.getNickName());
        }

        // 查询被评论用户 信息
        User targetUser = userService.getById(comment.getTargetUserUid());
        if(Objects.nonNull(targetUser)){
            comment.setTargetUserAvatar(targetUser.getAvatar());
            comment.setTargetUserNickName(targetUser.getNickName());
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

        // 转换实体类
        List<CommentVO> commentVOList = commentList.stream().map(comment -> {
                    CommentVO commentVO = new CommentVO();
                    BeanUtils.copyProperties(comment, commentVO);
                    return commentVO;
                })
                .collect(Collectors.toList());

        // 获取父评论
        List<CommentVO> parentList = commentVOList.stream()
                .filter(t -> StringUtils.isBlank(t.getParentUid()))
                .collect(Collectors.toList());

        for (CommentVO commentVO : parentList) {
            childrenList(commentVO, commentVOList);
        }

        return CommentRespVo.returnResult(parentList, commentList.size());
    }

    // 获取子评论
    private void childrenList(CommentVO parentComment, List<CommentVO> commentVOList){
        List<CommentVO> childrenList = new ArrayList<>();

        for (CommentVO commentVO : commentVOList) {
            if(Objects.equals(parentComment.getUid(), commentVO.getParentUid())){
                childrenList.add(commentVO);
            }
        }

        parentComment.setChildrenList(childrenList);
    }

}
