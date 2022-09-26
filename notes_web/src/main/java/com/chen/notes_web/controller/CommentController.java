package com.chen.notes_web.controller;


import com.chen.notes_web.annotion.log.BussinessLog;
import com.chen.notes_web.entity.Comment;
import com.chen.notes_web.global.enums.EBehavior;
import com.chen.notes_web.service.CommentService;
import com.chen.notes_web.vo.CommentRespVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 评论信息 前端控制器
 * </p>
 *
 * @author MaybeBin
 * @since 2022-09-22
 */

@Api(value = "评论相关接口", tags = {"评论相关接口"})
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @BussinessLog(value = "发表评论", behavior = EBehavior.PUBLISH_COMMENT)
    @ApiOperation(value = "发表评论", notes = "发表评论")
    @PostMapping("/add")
    public Comment add(Comment comment) {
        return commentService.addComment(comment);
    }

    @ApiOperation(value = "根据文章uid查询评论信息", notes = "根据文章uid查询评论信息")
    @GetMapping("/getByArticleUid/{articleUid}")
    public CommentRespVo getByArticleId(@PathVariable("articleUid") String articleUid) {
        return commentService.commentInfo(articleUid);
    }

}
