package com.example.demo.constant;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "aliyun.oss.file")
public class ConstantProperties {

    @Value("${wx.xcx.appID}")
    private String appID;

    @Value("${wx.xcx.appSecret}")
    private String appSecret;


    private String endpoint;

    private String keyid;

    private String keysecret;

    private String bucketname;


}
