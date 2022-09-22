package com.chen.nots_web.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author：MaybeBin
 * @Date: 2022-09-22 13-51
 */
@Data
@Accessors(chain = true)
public class CommentRespVo {

    List<CommentVO> comments;
    private Integer count;

    public static CommentRespVo returnResult(List<CommentVO> comments, Integer count){
        CommentRespVo commentRespVo = new CommentRespVo();

        commentRespVo.setComments(comments).setCount(count);

        return commentRespVo;
    }

}
