package com.chen.nots_web.service.impl;

import cn.hutool.core.util.StrUtil;
import com.chen.nots_web.service.FileUploadService;
import com.chen.nots_web.utils.QiniuUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


/**
 * <p>
 *
 * </p>
 *
 * @author: MaybeBin
 * @createTime: 2022-03-12
 */
@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Autowired
    private QiniuUtil qiniuUtil;

    @Override
    public String uploadImage(MultipartFile file, String filePath) {
        String fileName = filePath + StrUtil.uuid();
        try {
            return qiniuUtil.uploadQiniu(file.getBytes(), fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传失败";
    }
}
