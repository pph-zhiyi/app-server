package com.pph.demo.utils.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.lang.StringUtils;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Author: pph
 * @date 2019/9/19 18:59
 * @Description:
 */
public final class JwtUtil {
    private JwtUtil() {
    }

    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";

    private static final String SECRET = "jwtdemo";
    private static final String ISS = "echisan";

    /**
     * 过期时间：1 HOURS
     */
    private static final long EXPIRATION = TimeUnit.MILLISECONDS.convert(1, TimeUnit.HOURS);

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
    public static String decode(String token) throws UnauthorizedException {
        if (StringUtils.isEmpty(token) || StringUtils.equalsIgnoreCase("undefined", token)) {
            throw new UnauthorizedException("token异常:" + token);
        }
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        return decodedJWT.getId();
    }
}
