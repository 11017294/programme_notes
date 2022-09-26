package com.chen.notes_web.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chen.notes_web.global.SysConf;
import com.chen.notes_web.service.MessageService;
import com.chen.notes_web.vo.BaseResponse;
import com.chen.notes_web.vo.MessageVO;
import com.chen.notes_web.vo.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public BaseResponse<IPage> getMessageList(MessageVO messageVO) {
        IPage messageList = messageService.getMessageList(messageVO);
        return ResultUtils.success(messageList);
    }

    @ApiOperation(value = "添加留言", notes = "添加留言")
    @PostMapping("/addMessage")
    public String addMessage(HttpServletRequest request,  MessageVO messageVO) {
        String userUid = request.getAttribute(SysConf.USER_UID).toString();
        messageVO.setUserUid(userUid);
        return messageService.addMessage(messageVO);
    }

    @ApiOperation(value = "修改留言", notes = "修改留言")
    @PostMapping("/editMessage")
    public String editMessage(HttpServletRequest request,  MessageVO messageVO) {
        return messageService.editMessage(messageVO);
    }

    @ApiOperation(value = "完成处理", notes = "将状态修改成已处理")
    @PostMapping("/completion/{id}")
    public String completion(@PathVariable("id") String id) {
        return messageService.completion(id);
    }

}
