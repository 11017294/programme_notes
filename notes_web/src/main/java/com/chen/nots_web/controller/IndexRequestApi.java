package com.chen.nots_web.controller;

import com.chen.nots_web.service.NoteService;
import com.chen.nots_web.service.UserService;
import com.chen.nots_web.vo.NoteVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 *
 * </p>
 *
 * @author：MaybeBin
 * @Date: 2022-02-24 10-21
 */
@Controller
public class IndexRequestApi {

    @Autowired
    private NoteService noteService;
    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public String index(ModelMap map){
        NoteVO noteVO = new NoteVO();
        noteVO.setPageSize(10L);
        noteVO.setCurrentPage(0L);
        map.addAttribute("noteList", noteService.search(noteVO));
        return "index";
    }

    @GetMapping("/content")
    public String content(ModelMap map, String uid){
        map.addAttribute("note", noteService.getById(uid));
        return "content";
    }

    @GetMapping("/userCenter")
    public String userCenter(ModelMap map, String uid){
        map.addAttribute("note", userService.getUserById(uid));
        return "userCenter";
    }

    @GetMapping("/notes")
    public String notes(){
        return "notes";
    }

    @GetMapping("/makeNotes")
    public String makeNotes(){
        return "makeNotes";
    }

    @GetMapping("/test")
    public String test(){
        return "test";
    }

}
