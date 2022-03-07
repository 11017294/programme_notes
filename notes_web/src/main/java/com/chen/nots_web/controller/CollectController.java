package com.chen.nots_web.controller;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.chen.nots_web.vo.ResultBase;
import com.chen.nots_web.service.CollectService;
import com.chen.nots_web.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 收藏表 前端控制器
 * </p>
 *
 * @author MaybeBin
 * @since 2022-02-14
 */
@Api(value = "收藏相关接口", tags = {"收藏相关接口"})
@RestController
@RequestMapping("/collect")
public class CollectController {

    @Autowired
    CollectService collectService;

    @ApiOperation(value = "获取收藏数", notes = "根据用户uid获取收藏数")
    @GetMapping("/getCollectCount")
    public ResultBase getCollectCount(String userUid){
        if(StringUtils.isNotBlank(userUid)){
            return ResultBase.ok().data("collectCount", collectService.getCollectCount(userUid));
        }
        return ResultBase.error("没有传入用户uid");
    }

    @ApiOperation(value = "获取收藏笔记", notes = "根据用户uid获取收藏笔记")
    @GetMapping("/getCollectByUserId")
    public ResultBase getCollectByUserId(UserVO userVO){
        return ResultBase.ok().data("list", collectService.getCollectByUserId(userVO));
    }

}
