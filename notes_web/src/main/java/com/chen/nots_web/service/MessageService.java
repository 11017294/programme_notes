package com.chen.nots_web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chen.nots_web.entity.Message;
import com.chen.nots_web.global.service.SuperService;
import com.chen.nots_web.vo.MessageVO;

/**
 * <p>
 * 留言表 服务类
 * </p>
 *
 * @author MaybeBin
 * @since 2022-03-15
 */
public interface MessageService extends SuperService<Message> {

    /**
     * 添加留言
     * @param messageVO
     * @return
     */
    String addMessage(MessageVO messageVO);

    /**
     * 获取留言
     * @param messageVO
     * @return
     */
    IPage getMessageList(MessageVO messageVO);

    /**
     * 修改留言
     * @param messageVO
     * @return
     */
    String editMessage(MessageVO messageVO);
}
