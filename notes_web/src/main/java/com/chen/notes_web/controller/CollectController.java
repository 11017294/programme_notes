package com.chen.notes_web.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chen.notes_web.entity.Collect;
import com.chen.notes_web.global.SQLConf;
import com.chen.notes_web.global.SysConf;
import com.chen.notes_web.service.CollectService;
import com.chen.notes_web.utils.RedisUtil;
import com.chen.notes_web.vo.BaseResponse;
import com.chen.notes_web.vo.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 收藏表 前端控制器
 * </p>
 *
 * @author MaybeBin
 * @since 2022-03-13
 */
@Api(value = "收藏相关接口", tags = {"收藏相关接口"})
@RestController
@RequestMapping("/collect")
public class CollectController {

    @Autowired
    private CollectService collectService;
    @Autowired
    private RedisUtil redisUtil;

    @ApiOperation(value = "获取用户是否收藏该笔记", notes = "获取用户是否收藏该笔记", response = String.class)
    @GetMapping("/getUserCollectNote")
    public BaseResponse<Collect> getUserCollectNote(HttpServletRequest request, String noteUid){
        String userUid = request.getAttribute(SysConf.USER_UID).toString();
        QueryWrapper<Collect> wrapper = new QueryWrapper<>();
        wrapper.eq(SQLConf.USER_UID, userUid);
        wrapper.eq(SQLConf.NOTE_UID, noteUid);
        wrapper.last(SysConf.LIMIT_ONE);
        Collect collect = collectService.getOne(wrapper);
        return ResultUtils.success(collect);
    }

    @ApiOperation(value = "用户收藏笔记", notes = "用户收藏笔记", response = String.class)
    @GetMapping("/userCollectNote")
    public BaseResponse<Integer> userCollectNote(HttpServletRequest request, String noteUid){
        String userUid = request.getAttribute(SysConf.USER_UID).toString();
        int count = collectService.userCollectNote(userUid, noteUid);
        return ResultUtils.success(count);
    }
}
