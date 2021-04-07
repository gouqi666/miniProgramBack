package com.example.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.constant.ConstantProperties;
import com.example.demo.converter.WXMappingJackson2HttpMessageConverter;
import com.example.demo.model.LoginResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class WxMiniApi {

    public LoginResponse authCode2Session(String appID, String appSecret, String jsCode) {

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new WXMappingJackson2HttpMessageConverter());
        LoginResponse loginResponse = restTemplate.getForObject("https://api.weixin.qq.com/sns/jscode2session?appid={1}" +
                        "&secret={2}&js_code={3}&grant_type=authorization_code",
                LoginResponse.class, appID, appSecret, jsCode);
        return loginResponse;
    }
}
