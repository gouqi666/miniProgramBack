package com.example.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.constant.ConstantProperties;
import com.example.demo.mapper.SliderPicMapper;
import com.example.demo.model.*;
import com.example.demo.utils.JwtTokenUtils;
import com.example.demo.utils.WeChatUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@Slf4j
public class AuthService {

    @Autowired
    ConstantProperties constantProperties;


    @Autowired
    JwtTokenUtils jwtTokenUtils;

    @Autowired
    WxMiniApi wxMiniApi;

    @Autowired
    UserService userService;

    @Autowired
    SliderPicMapper sliderPicMapper;

    public Result<AuthUserDto> login(AuthUserDto authUserDto, HttpServletRequest request) throws Exception {
        Result<AuthUserDto> result = new Result<>();

        //authType=1代表是微信登录
        if (authUserDto.getAuthType() != null  && authUserDto.getAuthType() == 1) {
            LoginResponse loginResponse = wxMiniApi.authCode2Session(constantProperties.getAppID(),
                    constantProperties.getAppSecret(), authUserDto.getCode());
            if (loginResponse == null) {
                throw new RuntimeException("调用微信端授权认证接口错误");
            }
            String openId = loginResponse.getOpenid();
            String session_key = loginResponse.getSession_key();
            String unionId = loginResponse.getUnionid();
            if (openId == null || openId.length() == 0) {
                return result.error(loginResponse.getErrcode(), loginResponse.getErrmsg());
            }
            authUserDto.setOpenId(openId);

            //判断用户表中是否存在该用户，不存在则进行解密得到用户信息，并进行新增用户

            Result<User> resultUser = userService.findByOpenId(openId);
            if (resultUser.getModule() == null) {
                String userInfo = WeChatUtil.decryptData(authUserDto.getEncryptedData(), session_key, authUserDto.getIv());
                if (userInfo == null || userInfo.length() == 0) {
                    throw new RuntimeException("解密用户信息错误");
                }
                User user = JSONObject.parseObject(userInfo, User.class);
                if (user == null) {
                    throw new RuntimeException("填充用户对象错误");
                }
                user.setUnionId(unionId);
                userService.create(user);
                authUserDto.setUserInfo(user);
            } else {
                authUserDto.setUserInfo(resultUser.getModule());
            }
            //创建token
            String token = jwtTokenUtils.createToken(openId, session_key);
            if (token == null || token.length() == 0) {
                throw new RuntimeException("生成token错误");
            }
            authUserDto.setToken(token);
        }
        return result.ok(authUserDto);
    }
    public Long savePic(String url) {
        return sliderPicMapper.save(url);
    }

    public List<SliderPic> getAllSliderPic(){
        List<SliderPic> sp = sliderPicMapper.getAllSliderPic();
        return sp;
    }

}
