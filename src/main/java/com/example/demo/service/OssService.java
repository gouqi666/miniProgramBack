package com.example.demo.service;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.example.demo.constant.ConstantProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
@Slf4j
public class OssService{

    @Resource
    private ConstantProperties constantProperties;

    public String uploadFileAvatar(MultipartFile file) {
        //获取oss上传配置文件中的参数
        String bucketName = constantProperties.getBucketname();
        String endpoint = constantProperties.getEndpoint();
        String keyId = constantProperties.getKeyid();
        String keySecret = constantProperties.getKeysecret();

        OSS ossClient;
        InputStream inputStream;
        try {
            // 创建OSSClient实例。
            ossClient  = new OSSClientBuilder().build(endpoint, keyId, keySecret);
            // 上传文件流
            inputStream = file.getInputStream();

            //为了使得文件可以重复上传，每次上传的时候需要将文件名进行修改
            String fileName = file.getOriginalFilename();
            log.info("图片上传的名字为：{}",fileName);
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            String newFileName = uuid + fileName;

            //获取当前日期,然后以日期和新的文件名组成全路径，使得oss中的文件按照日期进行分类存储
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            String date = dateFormat.format(new Date());
            String fullFileName = date + "/" + newFileName;
            log.info("图片保存在oss的全路径为：{}",fullFileName);

            //第一个参数Bucket名称 第二个参数 上传到oss文件路径和文件名称
            ossClient.putObject(bucketName, fullFileName, inputStream);

            // 关闭OSSClient。
            ossClient.shutdown();
            return "https://"+bucketName+"."+ endpoint+"/"+fullFileName;
        } catch (Exception e) {
            log.error("文件上传失败",e);
            throw new RuntimeException("文件上传错误");
        }
    }
}
