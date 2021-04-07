package com.example.demo.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 用于JWT Token的生成与解码验证
 */
@Component
public class JwtTokenUtils {

    // 加密用的私钥
    private static String TOKEN_KEY = "keys";

    /**
     * 生成返回给客户端的token
     * @param openId, session_key
     * @return token
     * @throws Exception
     */
    public static String createToken(String openId, String session_key) throws Exception {
        // Token产生时间点
        Date startDate = new Date();

        // 设置过期时间
        Calendar now = Calendar.getInstance();
        now.add(Calendar.HOUR, 24);
        Date expireDate = now.getTime() ;

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        String token = JWT.create()
                .withHeader(map)
                .withClaim("open_id", openId)
                .withClaim("session_key",session_key)
                .withExpiresAt(expireDate)      // 过期时间
                .withIssuedAt(startDate)        // 签发时间
                .sign(Algorithm.HMAC256(TOKEN_KEY));        // 加密签名算法
        return token;
    }

    /**
     * 验证客户端token合法性
     * @param token
     * @return 解码token，获得的claim对
     * @throws Exception
     */
    public static Map<String, Claim> vertifyToken(String token) throws Exception {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_KEY)).build();
        DecodedJWT jwt = null;
        try {
            jwt = verifier.verify(token);
        } catch (Exception e) {
            throw new RuntimeException("token无效");
        }
        return jwt.getClaims();
    }
}