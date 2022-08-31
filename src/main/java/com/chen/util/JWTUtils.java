package com.chen.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.chen.beans.User;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chen
 * @crete 2022-08-30-22:25
 */
public class JWTUtils {
    // 签名密钥
    private static final String SECRET = "!DAR$";

    /**
     * 生成token
     * @param payload token携带的信息
     * @return token字符串
     */
    public static String getToken(Map<String,String> payload){
        // 指定token过期时间为7天
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);

        JWTCreator.Builder builder = JWT.create();
        // 构建payload
        payload.forEach(builder::withClaim);
        // 指定过期时间和签名算法
        return builder.withExpiresAt(calendar.getTime()).sign(Algorithm.HMAC256(SECRET));
    }


    /**
     * 解析token
     * @param token token字符串
     * @return 解析后的token
     */
    public static DecodedJWT decode(String token){
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        return jwtVerifier.verify(token);
    }


    public static String getTokenByUser(User user){
        Map<String,String> payload =new HashMap<String,String>(){
            {
                put("username", user.getUsername());
                put("phone", user.getPhone());
                put("email", user.getEmail());
                put("account", user.getAccount());
            }
        };
        return getToken(payload);
    }

    public static Map<String, String> getTokenBody(Map<String, Claim> map) {
        Map<String, String> payload = new HashMap<>();
        map.forEach((u1, u2) -> payload.put(u1, u2.asString()));
        return payload;
    }

    public static void main(String[] args) {
        Map<String, String> payload = new HashMap<>();
        payload.put("name", "cehn");
        payload.put("phone", "c10086");
        String token = JWTUtils.getToken(payload);
        System.out.println("token = " + token);
        DecodedJWT decode = JWTUtils.decode(token);
        System.out.println("decode.getPayload() = " + decode.getPayload());
        System.out.println("decode.getHeader() = " + decode.getHeader());
        System.out.println("decode.getSignature() = " + decode.getSignature());
        //decode.getClaims().forEach((u1,u2)->{
        //    System.out.println(u1+u2.asString());
        //});
        System.out.println("decode.getClaims() = " + JWTUtils.getTokenBody(decode.getClaims()));
    }

}
