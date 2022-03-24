package com.chen.nots_web.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chen.nots_web.entity.Message;
import com.chen.nots_web.global.SQLConf;
import com.chen.nots_web.global.service.serviceImpl.SuperServiceImpl;
import com.chen.nots_web.mapper.MessageMapper;
import com.chen.nots_web.service.MessageService;
import com.chen.nots_web.vo.MessageVO;
import com.github.xiaoymin.knife4j.core.util.StrUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 留言表 服务实现类
 * </p>
 *
 * @author MaybeBin
 * @since 2022-03-15
 */
@Service
public class MessageServiceImpl extends SuperServiceImpl<MessageMapper, Message> implements MessageService {

    @Resource
    private MessageMapper messageMapper;

    @Override
    public String addMessage(MessageVO messageVO) {
        Message message = new Message();
        BeanUtil.copyProperties(messageVO, message);
        message.insert();
        return message.getUid();
    }

    @Override
    public IPage getMessageList(MessageVO messageVO) {
        QueryWrapper<Message> wrapper = new QueryWrapper<>();
        if(StrUtil.isNotBlank(messageVO.getKeyword())){
            wrapper.like(SQLConf.CONTENT, messageVO.getKeyword());
        }
        if(messageVO.getStatus() != 0){
            wrapper.eq(SQLConf.STATUS, messageVO.getStatus());
        }
        Page<Message> page = new Page<>();
        page.setCurrent(messageVO.getCurrentPage());
        page.setSize(messageVO.getPageSize());
        return messageMapper.selectPage(page, wrapper);
    }

    @Override
    public String editMessage(MessageVO messageVO) {
        Message message = new Message();
        BeanUtil.copyProperties(messageVO, message);
        message.updateById();
        return message.getUid();
    }

    @Override
    public String completion(String id) {
        return String.valueOf(messageMapper.completion(id));
    }
}
