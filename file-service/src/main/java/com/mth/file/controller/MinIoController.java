package com.mth.file.controller;

import com.mth.file.common.ResponseResult;
import com.mth.file.util.MinIoTemplate;
import io.minio.ObjectWriteResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description:
 * @author: mth
 * @date: 2022/4/14
 **/
@RestController
@RequestMapping(value = "/files")
public class MinIoController {
    @Resource
    private MinIoTemplate minIoTemplate;

    @PostMapping(value = "/create-bucket")
    public ResponseResult createBucket(String bucketName) throws Exception {
        minIoTemplate.makeBucket(bucketName);
        return ResponseResult.success("创建存储桶" + bucketName + "成功！");
    }

    @PostMapping(value = "/upload")
    public ResponseResult putObject(String bucketName, String objectName, String filepath) throws Exception {
        ObjectWriteResponse response = minIoTemplate.putObject(bucketName,
                objectName,
                filepath);
        return ResponseResult.success("上传文件" + objectName + "成功！");
    }

    @PostMapping(value = "/delete")
    public ResponseResult deleteObject(String bucketName, String objectName) throws Exception {
        minIoTemplate.removeObject(bucketName,
                objectName);
        return ResponseResult.success("删除文件" + objectName + "成功！");
    }
}
