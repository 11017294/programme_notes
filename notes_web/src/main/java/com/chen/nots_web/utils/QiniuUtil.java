package com.chen.nots_web.utils;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author: MaybeBin
 * @createTime: 2022-03-12
 */
@Slf4j
@Component
public class QiniuUtil {

    @Value("${notes.qiNiuYun.accessKey}")
    private String accessKey;
    @Value("${notes.qiNiuYun.secretKey}")
    private String secretKey;
    @Value("${notes.qiNiuYun.bucket}")
    private String bucket;

    //上传文件
    public String uploadQiniu(String filePath,String fileName) throws QiniuException{
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Region.huanan());
        UploadManager uploadManager = new UploadManager(cfg);
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        Response response = uploadManager.put(filePath, fileName, upToken);
        //解析上传成功的结果
        DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
        return putRet.key;
    }

    //上传文件
    public String uploadQiniu(byte[] bytes, String fileName) throws QiniuException{
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Region.huanan());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = fileName;
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        Response response = uploadManager.put(bytes, key, upToken);
        //解析上传成功的结果
        DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
        return putRet.key;
    }

    //删除文件
    public int deleteFileFromQiniu(String fileName){
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Region.huadong());
        String key = fileName;
        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            Response delete = bucketManager.delete(bucket, key);
            return delete.statusCode;
        } catch (QiniuException ex) {
            //如果遇到异常，说明删除失败
            System.err.println(ex.code());
            System.err.println(ex.response.toString());
        }
        return -1;
    }
}
