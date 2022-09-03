package com.bustracker.config.auth.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bustracker.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/*
 * https://kdg-is.tistory.com/228
 *
 * [Spring Boot] 스프링 부트에서 JWT 사용하기
 * https://devlog-wjdrbs96.tistory.com/204
 *
 * Spring Boot | Spring Security JWT 인증 처리 과정
 * [OncePerRequestFilter]
 * https://gaemi606.tistory.com/entry/Spring-Boot-Spring-Security-JWT-%EC%9D%B8%EC%A6%9D-%EC%B2%98%EB%A6%AC-%EA%B3%BC%EC%A0%95
 * */
public class JwtTokenProvider {

    @Value("${jwt.issuer}")
    private static String ISSUER;

    @Value("${jwt.secret}")
    private static String SECRET;

    public static final String BEARER_TYPE = "bearer";
    private static final String SECRET_KEY = "key";
    private static final long ACCESS_EXPIRE_TIME = 60 * 60 * 24;
    private static final Algorithm ALGORITHM = Algorithm.HMAC256(SECRET);

    public String create(User user) throws JsonProcessingException {
        Map<String, Object> header = new HashMap<>();
        header.put("type", "JWT");

        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("id", user.getId());
        userInfo.put("email", user.getEmail());
        userInfo.put("roles", user.getRoles());
        ObjectMapper mapper = new ObjectMapper();
        String userString = mapper.writeValueAsString(userInfo);

        long now = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        Date expiresTime = new Date(now + ACCESS_EXPIRE_TIME);
        return JWT.create()
                .withHeader(header)
                .withClaim("user", userString)
                .withExpiresAt(expiresTime)
                .withIssuer(ISSUER)
                .sign(ALGORITHM);
    }

    public static DecodedJWT verifyJwt(String jwt) {
        return JWT.require(ALGORITHM)
                .withIssuer(ISSUER)
                .build()
                .verify(jwt);
    }

    public static boolean isVerify(String jwt) {
        return verifyJwt(jwt) != null;
    }
}
