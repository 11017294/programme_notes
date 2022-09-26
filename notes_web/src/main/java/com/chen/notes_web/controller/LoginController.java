package com.chen.notes_web.controller;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.json.JSONUtil;
import cn.hutool.jwt.JWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chen.notes_web.entity.OnlineUser;
import com.chen.notes_web.entity.User;
import com.chen.notes_web.global.RedisConf;
import com.chen.notes_web.global.SQLConf;
import com.chen.notes_web.global.SysConf;
import com.chen.notes_web.global.holder.RequestHolder;
import com.chen.notes_web.service.UserService;
import com.chen.notes_web.utils.IpUtils;
import com.chen.notes_web.utils.RedisUtil;
import com.chen.notes_web.vo.BaseResponse;
import com.chen.notes_web.vo.ResultUtils;
import com.chen.notes_web.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author：MaybeBin
 * @date: 2022-02-15 15:08
 */
@Api(value = "登录相关接口", tags = {"登录相关接口"})
@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户登录", notes = "用户登录")
    @PostMapping("/login")
    public BaseResponse<String> login(HttpServletRequest request,
                              @ApiParam(name = "userName", value = "用户名或邮箱或手机号") @RequestParam(name = "userName", required = false) String userName,
                              @ApiParam(name = "passWord", value = "密码") @RequestParam(name = "passWord", required = false) String passWord,
                              @ApiParam(name = "isRememberMe", value = "是否记住账号密码") @RequestParam(name = "isRememberMe", required = false, defaultValue = "false") Boolean isRememberMe){

        if (StrUtil.isBlank(userName)  || StrUtil.isBlank(passWord)) {
            return ResultUtils.error( "账号或密码不能为空");
        }
        String ip = ServletUtil.getClientIP(request);
        String limitCount = redisUtil.get(RedisConf.LOGIN_LIMIT + RedisConf.SEGMENTATION + ip);
        if(StrUtil.isNotBlank(limitCount)){
            Integer limitCountInt = Integer.valueOf(limitCount);
            if(limitCountInt >= 5){
                return ResultUtils.error("输入密码错误过多，已对您进行锁定30分钟");
            }
        }
        boolean isEmail = Validator.isEmail(userName);
        boolean isMobile = Validator.isMobile(userName);
        QueryWrapper wrapper = new QueryWrapper();

        if(isEmail){
            wrapper.eq(SQLConf.EMAIL, userName);
        } else if(isMobile){
            wrapper.eq(SQLConf.MOBILE, userName);
        } else{
            wrapper.eq(SQLConf.USER_NAME, userName);
        }
        // sql最后添加以下字符串
        wrapper.last(SysConf.LIMIT_ONE);
        User user = userService.getOne(wrapper);

        if(ObjectUtil.isNotNull(user) && user.getStatus() == 2){
            return ResultUtils.error("您的账号已被拉黑，如有疑问请联系站长");
        }

        String inPassword = SecureUtil.md5(passWord);
        if(ObjectUtil.isNull(user) || !inPassword.equals(user.getPassWord())){
            setLoginCommit(request);
            return ResultUtils.error("账号或密码错误");
        }

        String token = JWT.create()
                .setPayload(SysConf.USER_NAME, user.getUserName())
                .setPayload(SysConf.USER_UID, user.getUid())
                .setPayload("admin", false)
                .setKey(SysConf.JWT_KEY)
                .sign();

        //进行登录相关操作
        Integer count = user.getLoginCount() + 1;
        user.setLoginCount(count);
        user.setLastLoginIp(ip);
        user.setLastLoginTime(new Date());
        user.updateById();
        // 设置token到validCode，用于记录登录用户
        user.setValidCode(token);
        // 设置tokenUid，【主要用于换取token令牌，防止token直接暴露到在线用户管理中】
        user.setTokenUid(IdUtil.simpleUUID());
        // 添加在线用户到Redis中【设置过期时间】
        userService.addOnlineUser(user, SysConf.EXPIRED_TIME);
        return ResultUtils.success(token);
    }

    @ApiOperation(value = "退出登录", notes = "退出登录")
    @PostMapping(value = "/logout")
    public BaseResponse<String> logout() {
        ServletRequestAttributes attribute = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attribute.getRequest();
        String token = request.getHeader("Authorization");
        if (StringUtils.isEmpty(token)) {
            return ResultUtils.error("退出失败");
        } else {
            // 获取在线用户信息
            String adminJson = redisUtil.get(RedisConf.LOGIN_TOKEN_KEY + RedisConf.SEGMENTATION + token);
            if (StrUtil.isNotBlank(adminJson)) {
                OnlineUser onlineAdmin = JSONUtil.toBean(adminJson, OnlineUser.class);
                String tokenUid = onlineAdmin.getTokenId();
                // 移除Redis中的TokenUid
                redisUtil.delete(RedisConf.LOGIN_UUID_KEY + RedisConf.SEGMENTATION + tokenUid);
            }
            // 移除Redis中的用户
            redisUtil.delete(RedisConf.LOGIN_TOKEN_KEY + RedisConf.SEGMENTATION + token);
            //SecurityContextHolder.clearContext();
            return ResultUtils.success("成功");
        }
    }

    @ApiOperation(value = "用户注册", notes = "用户注册")
    @PostMapping("/register")
    public BaseResponse<String> register(UserVO userVO) {
        HttpServletRequest request = RequestHolder.getRequest();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.and(wrapper -> wrapper.eq(SQLConf.USER_NAME, userVO.getUserName()).or().eq(SQLConf.EMAIL, userVO.getEmail()));
        List<String> deleteList = new ArrayList<String>(2){{
            add("0");
            add("1");
        }};
        if (!Validator.isEmail(userVO.getEmail())) {
            return ResultUtils.error("邮箱不合法");
        }
        queryWrapper.in(SQLConf.isDelete, deleteList);
        queryWrapper.last(SysConf.LIMIT_ONE);
        User user = userService.getOne(queryWrapper);
        if (user != null) {
            if(user.getUserName().equals(userVO.getUserName())){
                return ResultUtils.error("用户名：" + user.getUserName() + "已被注册");
            }
            return ResultUtils.error("邮箱：" + user.getEmail() + "已注册过");
        }
        user = new User();
        user.setUserName(userVO.getUserName());
        user.setNickName(userVO.getUserName());
        String s = SecureUtil.md5(userVO.getPassWord());
        System.out.println(userVO.getPassWord());
        user.setPassWord(s);
        user.setEmail(userVO.getEmail());
        String ip = null;
        try {
            ip = IpUtils.getIpAddr(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, String> map = IpUtils.getOsAndBrowserInfo(request);
        user.setLastLoginIp(ip);
        user.setBrowser(map.get(SysConf.BROWSER));
        user.setOs(map.get(SysConf.OS));
        user.setAvatar("user/Mikasa");
        user.insert();
        return ResultUtils.success("成功");
    }


    /**
     * 设置登录限制，返回剩余次数
     * 密码错误五次，将会锁定30分钟
     *
     * @param request
     */
    private Integer setLoginCommit(HttpServletRequest request) {
        String ip = IpUtils.getIpAddr(request);
        String count = redisUtil.get(RedisConf.LOGIN_LIMIT + RedisConf.SEGMENTATION + ip);
        Integer surplusCount = 5;
        if (StrUtil.isNotBlank(count)) {
            Integer countTemp = Integer.valueOf(count) + 1;
            surplusCount = surplusCount - countTemp;
            redisUtil.setEx(RedisConf.LOGIN_LIMIT + RedisConf.SEGMENTATION + ip, String.valueOf(countTemp), 30, TimeUnit.MINUTES);
        } else {
            surplusCount = surplusCount - 1;
            redisUtil.setEx(RedisConf.LOGIN_LIMIT + RedisConf.SEGMENTATION + ip, "1", 30, TimeUnit.MINUTES);
        }
        return surplusCount;
    }

}
