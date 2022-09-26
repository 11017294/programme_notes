package com.chen.notes_web.controller;

import com.chen.notes_web.entity.Picture;
import com.chen.notes_web.global.SysConf;
import com.chen.notes_web.service.FileUploadService;
import com.chen.notes_web.vo.BaseResponse;
import com.chen.notes_web.vo.ResultUtils;
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

    @ApiOperation(value = "上传头像", notes = "上传头像")
    @PostMapping("/uploadAvatar")
    public BaseResponse<String> uploadAvatar(HttpServletRequest request, @RequestParam("file") MultipartFile file){
        if(file.isEmpty()){
            return ResultUtils.error("未检测到文件");
        }
        String fileUrl = fileUploadService.uploadImage(file, SysConf.FILE_PATH_USER);
        // 保存图片信息
        Picture picture = new Picture();
        picture.setPicName(fileUrl);
        picture.insert();
        return ResultUtils.success(fileUrl);
    }

}
