package com.chen.nots_web.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *
 * </p>
 *
 * @author: MaybeBin
 * @createTime: 2022-03-12
 */
public interface FileUploadService {

    /**
     * 上传头像
     * @param file
     * @return
     */
    String uploadImage(MultipartFile file, String filePath);
}
