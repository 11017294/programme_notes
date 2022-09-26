package com.chen.notes_web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chen.notes_web.entity.User;
import com.chen.notes_web.global.service.SuperService;
import com.chen.notes_web.vo.UserVO;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author MaybeBin
 * @since 2022-02-15
 */
public interface UserService extends SuperService<User> {

    /**
     * 添加在线用户
     *
     * @param user             用户
     * @param expirationSecond 过期时间【秒】
     */
    void addOnlineUser(User user, Long expirationSecond);

    /**
     * 获取用户列表
     *
     * @param userVO
     * @return
     */
    IPage getPageList(UserVO userVO);


    User getUserById(String uid);

    /**
     * 新增用户
     *
     * @param userVO
     */
    String addUser(UserVO userVO);

    /**
     * 编辑用户
     *
     * @param userVO
     */
    String editUser(UserVO userVO);

    /**
     * 重置用户密码
     *
     * @param userVO
     * @return
     */
    String resetUserPassword(UserVO userVO);

    /**
     * 获取用户头像
     * @param userUid
     */
    String getAvatar(String userUid);

    /**
     * 查询是否存在头像为avatar的数据
     * @param avatar
     * @return
     */
    public String getUserByAvatar(String avatar);
}
