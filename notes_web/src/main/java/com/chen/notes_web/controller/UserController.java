package com.chen.notes_web.controller;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.chen.notes_web.entity.User;
import com.chen.notes_web.exception.BusinessException;
import com.chen.notes_web.exception.ErrorCode;
import com.chen.notes_web.global.RedisConf;
import com.chen.notes_web.global.SysConf;
import com.chen.notes_web.service.UserService;
import com.chen.notes_web.utils.JsonUtils;
import com.chen.notes_web.utils.RedisUtil;
import com.chen.notes_web.vo.BaseResponse;
import com.chen.notes_web.vo.ResultUtils;
import com.chen.notes_web.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    @GetMapping("/getList")
    public BaseResponse<IPage> getList(UserVO userVO) {
        log.info("获取用户列表: {}", userVO);
        IPage userPage = userService.getPageList(userVO);
        return ResultUtils.success(userPage);
    }

    @ApiOperation(value = "通过id获取用户信息", notes = "通过id获取用户信息")
    @GetMapping("/getUserById/{id}")
    public User getUserById(@PathVariable("id") String id) {
        if (StrUtil.isBlank(id)) {
            throw new BusinessException(ErrorCode.REQUEST_PARAMS_ERROR);
        }
        return userService.getUserById(id);
    }

    @ApiOperation(value = "新增用户", notes = "新增用户")
    @PostMapping("/add")
    public String add(@RequestBody UserVO userVO) {
        log.info("新增用户: {}", userVO);
        return userService.addUser(userVO);
    }

    @ApiOperation(value = "新增用户", notes = "新增用户")
    @PostMapping("/addition")
    public String addition(UserVO userVO) {
        log.info("新增用户: {}", userVO);
        return userService.addUser(userVO);
    }

    @ApiOperation(value = "编辑用户", notes = "编辑用户")
    @PostMapping("/edit")
    public String edit(@RequestBody UserVO userVO) {
        log.info("编辑用户: {}", userVO);
        return userService.editUser(userVO);
    }

    @ApiOperation(value = "编辑用户", notes = "编辑用户")
    @PostMapping("/update")
    public String update(UserVO userVO) {
        log.info("编辑用户: {}", userVO);
        return userService.editUser(userVO);
    }

    @ApiOperation(value = "删除用户", notes = "删除用户")
    @PostMapping("/delete")
    public String delete(@ApiParam(name = "uid", value = "用户UID") String uid) {
        log.info("删除用户: {}", uid);
        if(StringUtils.isBlank(uid)){
            throw new BusinessException(ErrorCode.REQUEST_PARAMS_ERROR);
        }
        boolean user = userService.removeById(uid);
        if(!user){
            throw new BusinessException(ErrorCode.NOT_FOUND);
        }
        return uid;
    }

    @ApiOperation(value = "重置用户密码", notes = "重置用户密码")
    @PostMapping("/resetUserPassword")
    public String resetUserPassword(@RequestBody UserVO userVO) {
        log.info("重置用户密码: {}", userVO);
        return userService.resetUserPassword(userVO);
    }

    @ApiOperation(value = "修改用户密码", notes = "修改用户密码")
    @PostMapping("/updateUserPwd")
    public BaseResponse<String> updateUserPwd(HttpServletRequest request, String oldPwd, String newPwd) {
        String userUid = request.getAttribute(SysConf.USER_UID).toString();
        User user = userService.getById(userUid);
        if(ObjectUtil.isNull(user) || !SecureUtil.md5(oldPwd).equals(user.getPassWord())){
            return ResultUtils.error("旧密码错误不正确");
        }
        user.setPassWord(SecureUtil.md5(newPwd));
        user.updateById();
        return ResultUtils.success(user.getUid());
    }

    @ApiOperation(value = "通过token获取用户信息", notes = "通过token获取用户信息")
    @GetMapping("/verify/{accessToken}")
    public BaseResponse<Map<String, Object>> verifyUser(@PathVariable("accessToken") String accessToken) {
        String userInfo = redisUtil.get(RedisConf.LOGIN_TOKEN_KEY + RedisConf.SEGMENTATION + accessToken);
        if (StrUtil.isBlank(userInfo)) {
            return ResultUtils.error("令牌无效");
        }
        Map<String, Object> map = JsonUtils.jsonToMap(userInfo);
        return ResultUtils.success(map);
    }

    @ApiOperation(value = "添加黑名单", notes = "添加黑名单")
    @PostMapping("/addBlacklist")
    public BaseResponse<String> addBlacklist(@ApiParam(name = "uid", value = "用户UID") String uid) {
        log.info("添加黑名单用户: {}", uid);
        if(StringUtils.isBlank(uid)){
            throw new BusinessException(ErrorCode.REQUEST_PARAMS_ERROR);
        }
        User user = userService.getById(uid);
        if(ObjectUtil.isNotEmpty(user)){
            if(user.getStatus() != 2) {
                user.setStatus(2);
                user.updateById();
            }
            return ResultUtils.success(user.getUid());
        }
        return ResultUtils.error("拉黑失败，没有ID:" + uid + "的用户");
    }

    @ApiOperation(value = "移除黑名单", notes = "添加黑名单")
    @PostMapping("/deleteBlacklist")
    public BaseResponse<String> deleteBlacklist(@ApiParam(name = "uid", value = "用户UID") String uid) {
        log.info("移除黑名单用户: {}", uid);
        if(StringUtils.isBlank(uid)){
            throw new BusinessException(ErrorCode.REQUEST_PARAMS_ERROR);
        }
        User user = userService.getById(uid);
        if(ObjectUtil.isNotEmpty(user)){
            if(user.getStatus() == 2) {
                user.setStatus(1);
                user.updateById();
            }
            return ResultUtils.success(user.getUid());
        }
        return ResultUtils.error("移除失败，没有ID:" + uid + "的用户");
    }

}
