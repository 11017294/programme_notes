package com.chen.nots_web.controller;

import com.chen.nots_web.entity.User;
import com.chen.nots_web.global.SysConf;
import com.chen.nots_web.service.FileUploadService;
import com.chen.nots_web.service.UserService;
import com.chen.nots_web.vo.ResultBase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 文件上传接口
 * </p>
 *
 * @author: MaybeBin
 * @createTime: 2022-03-12
 */
@Api(value = "文件上传接口", tags = {"文件上传接口"})
@RestController
@RequestMapping("/fileUpload")
public class FileController {

    @Autowired
    private FileUploadService fileUploadService;
    @Autowired
    private UserService userService;

    @ApiOperation(value = "上传头像", notes = "上传头像")
    @PostMapping("/uploadAvatar")
    public ResultBase uploadAvatar(HttpServletRequest request, @RequestParam("file") MultipartFile file){
        if(file.isEmpty()){
            return ResultBase.error("未检测到文件");
        }
        String fileUrl = fileUploadService.uploadImage(file, SysConf.FILE_PATH_USER);
        String userUid = request.getAttribute(SysConf.USER_UID).toString();
        User user = userService.getById(userUid);
        user.setAvatar(fileUrl);
        user.updateById();
        return ResultBase.ok().data("fileUrl", fileUrl);
    }

}
