package com.chen.nots_web.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chen.nots_web.entity.OnlineUser;
import com.chen.nots_web.entity.User;
import com.chen.nots_web.global.RedisConf;
import com.chen.nots_web.global.SQLConf;
import com.chen.nots_web.global.SysConf;
import com.chen.nots_web.global.holder.RequestHolder;
import com.chen.nots_web.global.service.serviceImpl.SuperServiceImpl;
import com.chen.nots_web.mapper.UserMapper;
import com.chen.nots_web.service.UserService;
import com.chen.nots_web.utils.IpUtils;
import com.chen.nots_web.utils.NotesSysDateUtils;
import com.chen.nots_web.utils.RedisUtil;
import com.chen.nots_web.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author MaybeBin
 * @since 2022-02-15
 */
@Service
public class UserServiceImpl extends SuperServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private RedisUtil redisUtil;
    @Resource
    private UserMapper userMapper;

    @Override
    public void addOnlineUser(User user, Long expirationSecond) {
        HttpServletRequest request = RequestHolder.getRequest();
        Map<String, String> map = IpUtils.getOsAndBrowserInfo(request);
        String os = map.get(SysConf.OS);
        String browser = map.get(SysConf.BROWSER);
        String ip = ServletUtil.getBody(request);
        OnlineUser onlineUser = new OnlineUser();
        onlineUser.setUserUid(user.getUid());
        onlineUser.setTokenId(user.getTokenUid());
        onlineUser.setToken(user.getValidCode());
        onlineUser.setOs(os);
        onlineUser.setBrowser(browser);
        onlineUser.setIpaddr(ip);
        onlineUser.setLoginTime(DateUtil.formatDateTime(new Date()));
        onlineUser.setUserName(user.getUserName());
        onlineUser.setExpireTime(NotesSysDateUtils.getDateStr(new Date(), expirationSecond));
        //从Redis中获取IP来源
        String jsonResult = redisUtil.get(RedisConf.IP_SOURCE + SysConf.SYMBOL_COLON + ip);
        if (StrUtil.isBlank(jsonResult)) {
            String addresses = IpUtils.getAddresses(SysConf.IP + SysConf.EQUAL_TO + ip, SysConf.UTF_8);
            if (StrUtil.isNotBlank(addresses)) {
                onlineUser.setLoginLocation(addresses);
                redisUtil.setEx(RedisConf.IP_SOURCE + SysConf.SYMBOL_COLON + ip, addresses, 24, TimeUnit.HOURS);
            }
        } else {
            onlineUser.setLoginLocation(jsonResult);
        }
        // 将登录的用户存储到在线用户表
        redisUtil.setEx(RedisConf.LOGIN_TOKEN_KEY + RedisConf.SEGMENTATION + user.getValidCode(), JSONUtil.toJsonStr(onlineUser), expirationSecond, TimeUnit.SECONDS);
        // 在维护一张表，用于 uuid - token 互相转换
        redisUtil.setEx(RedisConf.LOGIN_UUID_KEY + RedisConf.SEGMENTATION + user.getTokenUid(), user.getValidCode(), expirationSecond, TimeUnit.SECONDS);
    }

    @Override
    public IPage getPageList(UserVO userVO) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // 查询用户名
        if (StrUtil.isNotBlank(userVO.getKeyword()) && StrUtil.isNotBlank(userVO.getKeyword().trim())) {
            wrapper.like(SQLConf.USER_NAME, userVO.getKeyword().trim()).or().like(SQLConf.NICK_NAME, userVO.getKeyword().trim());
        }
        if (userVO.getCommentStatus() != null) {
            wrapper.eq(SQLConf.COMMENT_STATUS, userVO.getCommentStatus());
        }

        if(StrUtil.isNotBlank(userVO.getOrderByAscColumn())) {
            // 将驼峰转换成下划线
            String column = StrUtil.toUnderlineCase(new StringBuffer(userVO.getOrderByAscColumn()));
            wrapper.orderByAsc(column);
        } else if(StrUtil.isNotBlank(userVO.getOrderByDescColumn())) {
            // 将驼峰转换成下划线
            String column = StrUtil.toUnderlineCase(new StringBuffer(userVO.getOrderByDescColumn()));
            wrapper.orderByDesc(column);
        } else {
            wrapper.orderByDesc(SQLConf.CREATE_TIME);
        }

        wrapper.select(User.class, i -> !i.getProperty().equals(SQLConf.PASS_WORD));
        Page<User> page = new Page<>();
        page.setCurrent(userVO.getCurrentPage());
        page.setSize(userVO.getPageSize());
        return userMapper.selectPage(page, wrapper);
    }

    @Override
    public User getUserById(String uid) {
        if(StrUtil.isBlank(uid)){
            return new User();
        }
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq(SQLConf.UID, uid);
        wrapper.select(User.class, i -> !i.getColumn().equals(SQLConf.PASS_WORD));
        return userMapper.selectOne(wrapper);
    }

    @Override
    public String addUser(UserVO userVO) {
        User user = new User();
        BeanUtils.copyProperties(userVO, user);
        user.insert();
        return user.getUid();
    }

    @Override
    public String editUser(UserVO userVO) {
        User user = userMapper.selectById(userVO.getUid());
        user.setUserName(userVO.getUserName());
        user.setSex(userVO.getSex());
        user.setEmail(userVO.getEmail());
        user.setBirthday(userVO.getBirthday());
        user.setSummary(userVO.getSummary());
        user.setStartEmailNotification(userVO.getStartEmailNotification());
        user.setAvatar(userVO.getAvatar());
        user.setNickName(userVO.getNickName());
        user.setUserTag(userVO.getUserTag());
        user.setCommentStatus(userVO.getCommentStatus());
        user.setUpdateTime(new Date());
        user.updateById();
        return user.getUid();
    }

    @Override
    public String resetUserPassword(UserVO userVO) {
        User user = userMapper.selectById(userVO.getUid());
        String passWord = SecureUtil.md5(userVO.getPassWord());
        user.setPassWord(passWord);
        user.setCreateTime(new Date());
        user.updateById();
        return user.getUid();
    }

    @Override
    public String getAvatar(String userUid) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select(SQLConf.AVATAR);
        wrapper.eq(SQLConf.UID, userUid);
        wrapper.last(SysConf.LIMIT_ONE);
        return userMapper.selectOne(wrapper).getAvatar();
    }
}
