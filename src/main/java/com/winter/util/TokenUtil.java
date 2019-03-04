package com.winter.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.winter.common.Const;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * token工具类
 */
public class TokenUtil {

    //token有效时间
    private static final long EXPIRE_TIME = 15 * 60 * 1000;
    //私匙
    private static final String TOKEN_SECRET = "thefirsttoken777";

    /**
     * 颁发签名
     * @return
     */
    public static String sign(Integer id, String phone) {
        try {
            // 设置过期时间
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            // 私钥和加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            // 设置头部信息
            Map<String, Object> header = new HashMap<>(2);
            header.put("Type", "Jwt");
            header.put("alg", "HS256");
            // 返回token字符串
            return JWT.create()
                    .withHeader(header)
                    .withClaim("id",id.toString())
                    .withClaim("phone",phone)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 检验token是否正确
     * @param token
     * @return
     */
    public static boolean verify(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception e){
            return false;
        }
    }


    /**
     * 从token中获取phone信息
     * @param token
     * @return
     */
    public static String getInfo(String token ,String type){
        try {
            DecodedJWT jwt = JWT.decode(token);
            if (type.equals("phone")) {
                return jwt.getClaim("phone").asString();
            }
            if (type.equals("id")) {
                return jwt.getClaim("id").asString();
            }

        } catch (JWTDecodeException e){
            e.printStackTrace();
            return null;
        }
        return null;
    }


}
