package com.chen.notes_web.controller;

import cn.hutool.core.util.StrUtil;
import com.chen.notes_web.annotion.log.BussinessLog;
import com.chen.notes_web.entity.Note;
import com.chen.notes_web.exception.BusinessException;
import com.chen.notes_web.exception.ErrorCode;
import com.chen.notes_web.global.RedisConf;
import com.chen.notes_web.global.enums.EBehavior;
import com.chen.notes_web.global.holder.RequestHolder;
import com.chen.notes_web.service.NoteService;
import com.chen.notes_web.utils.IpUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author：MaybeBin
 * @date: 2022-02-14 14:44
 */
@Api(value = "笔记详情相关接口", tags = {"笔记详情相关接口"})
@RestController
@RequestMapping("/content")
public class NoteContentController {

    @Autowired
    private NoteService noteService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @BussinessLog(value = "点击笔记", behavior = EBehavior.NOTE_CONTENT)
    @ApiOperation(value = "通过uid获取笔记", notes = "通过uid获取笔记")
    @GetMapping("/getNoteById")
    public Note getNoteById(@ApiParam(name = "uid", value = "笔记UID")
                                  @RequestParam(name = "uid", required = false)
                                           String uid){
        if(StrUtil.isBlank(uid)){
            throw new BusinessException(ErrorCode.REQUEST_PARAMS_ERROR);
        }
        Note note = noteService.getById(uid);
        noteService.setSortAndTagByNote(note);

        HttpServletRequest request = RequestHolder.getRequest();
        String ip = IpUtils.getIpAddr(request);
        // 从Redis取出数据，判断该用户是否点击过
        String jsonResult = stringRedisTemplate.opsForValue().get(
                RedisConf.NOTE_CLICK + RedisConf.SYMBOL_COLON + ip + RedisConf.WELL_NUMBER + note.getUid());
        if (StrUtil.isBlank(jsonResult)) {
            // 给笔记点击数增加
            Integer clickCount = note.getClickCount() + 1;
            note.setClickCount(clickCount);
            note.updateById();

            //将该用户点击记录存储到redis中, 24小时后过期
            stringRedisTemplate.opsForValue().set(
                    RedisConf.NOTE_CLICK + RedisConf.SYMBOL_COLON + ip + RedisConf.WELL_NUMBER + note.getUid(),
                    note.getClickCount().toString(), 24, TimeUnit.HOURS);
        }
        return note;
    }

}
