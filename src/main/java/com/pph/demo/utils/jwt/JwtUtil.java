package com.pph.demo.utils.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

/**
 * @Author: pph
 * @Date: 2019/9/19 18:59
 * @Description:
 */
public class JwtUtil {

    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";

    private static final String SECRET = "jwtdemo";
    private static final String ISS = "echisan";

    /**
     * 过期时间
     */
    private static final long EXPIRATION = 360000L;

    /**
     * 加密 jwt token
     *
     * @param user
     * @return
     */
    public static String encode(String user) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        return JWT.create()
                //设置过期时间为一个小时
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION))
                //设置负载
                .withJWTId(user)
                .sign(algorithm);
    }

    /**
     * 解密 jwt toke
     *
     * @param token
     * @return
     */
    public static String decode(String token) {
        if (token == null || token.length() == 0) {
            throw new IllegalArgumentException("token为空:" + token);
        }
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        return decodedJWT.getId();
    }

}
