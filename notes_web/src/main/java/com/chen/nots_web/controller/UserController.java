package com.chen.nots_web.controller;


import cn.hutool.core.util.StrUtil;
import com.chen.nots_web.global.RedisConf;
import com.chen.nots_web.service.UserService;
import com.chen.nots_web.utils.JsonUtils;
import com.chen.nots_web.utils.RedisUtil;
import com.chen.nots_web.vo.ResultBase;
import com.chen.nots_web.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author MaybeBin
 * @since 2022-02-15
 */
@Api(value = "用户相关接口", tags = {"用户相关接口"})
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RedisUtil redisUtil;

    @ApiOperation(value = "获取用户列表", notes = "获取用户列表")
    @PostMapping("/getList")
    public ResultBase getList(@RequestBody UserVO userVO) {
        log.info("获取用户列表: {}", userVO);
        return ResultBase.ok().data("list", userService.getPageList(userVO));
    }

    @ApiOperation(value = "通过id获取用户信息", notes = "通过id获取用户信息")
    @GetMapping("/getUserById/{id}")
    public ResultBase getUserById(@PathVariable("id") String id) {
        if (StrUtil.isBlank(id)) {
            return ResultBase.error("没有传入参数id");
        } else {
            return ResultBase.ok().data("user", userService.getUserById(id));
        }
    }

    @ApiOperation(value = "新增用户", notes = "新增用户")
    @PostMapping("/add")
    public ResultBase add(@RequestBody UserVO userVO) {
        log.info("新增用户: {}", userVO);
        return ResultBase.ok().data("id", userService.addUser(userVO));
    }

    @ApiOperation(value = "编辑用户", notes = "编辑用户")
    @PostMapping("/edit")
    public ResultBase edit(@RequestBody UserVO userVO) {
        log.info("编辑用户: {}", userVO);
        return ResultBase.ok().data("id", userService.editUser(userVO));
    }

    @ApiOperation(value = "删除用户", notes = "删除用户")
    @PostMapping("/delete")
    public ResultBase delete(@RequestBody UserVO userVO) {
        log.info("删除用户: {}", userVO);
        return ResultBase.ok().data("id", userService.removeById(userVO.getUid()));
    }

    @ApiOperation(value = "重置用户密码", notes = "重置用户密码")
    @PostMapping("/resetUserPassword")
    public ResultBase resetUserPassword(@RequestBody UserVO userVO) {
        log.info("重置用户密码: {}", userVO);
        return ResultBase.ok().data("id", userService.resetUserPassword(userVO));
    }

    @ApiOperation(value = "通过token获取用户信息", notes = "通过token获取用户信息")
    @GetMapping("/verify/{accessToken}")
    public ResultBase verifyUser(@PathVariable("accessToken") String accessToken) {
        String userInfo = redisUtil.get(RedisConf.LOGIN_TOKEN_KEY + RedisConf.SEGMENTATION + accessToken);
        if (StrUtil.isBlank(userInfo)) {
            return ResultBase.error("令牌无效");
        } else {
            Map<String, Object> map = JsonUtils.jsonToMap(userInfo);
            return ResultBase.ok().data("info", map);
        }
    }

}
