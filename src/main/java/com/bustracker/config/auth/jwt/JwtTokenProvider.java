package com.bustracker.config.auth.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bustracker.config.auth.UserDetailsImpl;
import com.bustracker.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

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
 *
 * Spirng Security + Jwt 로그인 적용하기
 * https://velog.io/@jkijki12/Spirng-Security-Jwt-%EB%A1%9C%EA%B7%B8%EC%9D%B8-%EC%A0%81%EC%9A%A9%ED%95%98%EA%B8%B0
 * */
@Slf4j
@Component
public class JwtTokenProvider {

    @Value("${jwt.issuer:test}")
    private String ISSUER;

    @Value("${jwt.secret:localSecret}")
    private String SECRET;

    private static final long ACCESS_EXPIRE_TIME = 60 * 60 * 24; // 1 일

    public String create(UserDetailsImpl user) throws JsonProcessingException {
        Algorithm ALGORITHM = Algorithm.HMAC256(SECRET);
        Map<String, Object> header = new HashMap<>();
        header.put("type", "JWT");

        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("loginId", user.getLoginId());
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

    public DecodedJWT verifyJwt(String jwt) {
        Algorithm ALGORITHM = Algorithm.HMAC256(SECRET);

        return JWT.require(ALGORITHM)
                .withIssuer(ISSUER)
                .build()
                .verify(jwt);
    }

    public boolean isVerify(String jwt) {
        boolean result = verifyJwt(jwt) != null;
        log.info("isVerify: {}", result);
        return result;
    }

    public AccessToken getAccessToken(String jwt) {
        if (!StringUtils.hasText(jwt)) return null;

        try {
            DecodedJWT decodedJWT = verifyJwt(jwt);
            User user = convertUser(decodedJWT);
            log.info("decoded user: {}", user);
            if (user == null) throw new BadCredentialsException("Please enter your user name or password.");
            return AccessToken.builder()
                    .id(user.getLoginId())
                    .name(user.getName())
                    .email(user.getEmail())
                    .roles(user.getRoles())
                    .jwt(jwt)
                    .jwtExpTime(decodedJWT.getExpiresAt().getTime())
                    .build();
        } catch (Exception e) {
            return null;
        }
    }

    private User convertUser(DecodedJWT decodedJWT) {
        try {
            String claim = decodedJWT.getClaim("user").asString();
            return new ObjectMapper().readValue(claim, User.class);
        } catch (JsonProcessingException e) {
            log.error("convertUser error: {}", e.getMessage());
            return null;
        }
    }
}
