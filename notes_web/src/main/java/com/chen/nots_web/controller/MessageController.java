package com.chen.nots_web.controller;


import com.chen.nots_web.global.SysConf;
import com.chen.nots_web.service.MessageService;
import com.chen.nots_web.vo.MessageVO;
import com.chen.nots_web.vo.ResultBase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 留言表 前端控制器
 * </p>
 *
 * @author MaybeBin
 * @since 2022-03-15
 */
@Api(value = "留言相关接口", tags = {"留言相关接口"})
@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @ApiOperation(value = "获取留言列表", notes = "获取留言列表")
    @GetMapping("/getMessageList")
    public ResultBase getMessageList(MessageVO messageVO) {
        return ResultBase.ok().data("list", messageService.getMessageList(messageVO));
    }

    @ApiOperation(value = "添加留言", notes = "添加留言")
    @GetMapping("/addMessage")
    public ResultBase addMessage(HttpServletRequest request,  MessageVO messageVO) {
        String userUid = request.getAttribute(SysConf.USER_UID).toString();
        messageVO.setUserUid(userUid);
        return ResultBase.ok().data("id", messageService.addMessage(messageVO));
    }

}