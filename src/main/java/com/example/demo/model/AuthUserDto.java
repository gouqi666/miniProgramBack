package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class AuthUserDto {

    /**
     * 授权类型：0--WEB端 1--微信端
     */
    private Integer authType;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    @JsonIgnore
    private String password;

    /**
     * 传入参数：临时登录凭证
     */
    private String code;

    /**
     * 用户登录id
     */
    private String uuid = "";

    //**********************************
    //以下为微信类传输字段

    /**
     * 微信openId
     */
    private String openId;

    /**
     * 传入参数: 用户非敏感信息
     */
    private String rawData;

    /**
     * 传入参数: 签名
     */
    private String signature;

    /**
     * 传入参数: 用户敏感信息
     */
    private String encryptedData;

    /**
     * 传入参数: 解密算法的向量
     */
    private String iv;

    /**
     * 会话密钥
     */
    @JsonIgnore
    private String sessionKey;

    /**
     * 用户在开放平台的唯一标识符
     */
    @JsonIgnore
    private String unionId;

    //以上为微信类传输字段
    //**********************************

    /**
     * 返回:服务器jwt token
     */
    private String token;

    /**
     * 返回：userName或openId对应的用户
     */
    private User userInfo;

    @Override
    public String toString() {
        return "AuthUser{" +
                "userName='" + userName + '\'' +
                ", password='" + "*********" + '\'' +
                ", code='" + code + '\'' +
                ", uuid='" + uuid + '\'' +
                ", openId='" + openId + '\'' +
                ", token='" + token + '\'' +
                ", userInfo=" + userInfo +
                '}';
    }
}
