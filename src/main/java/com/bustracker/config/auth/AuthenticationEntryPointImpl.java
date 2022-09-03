package com.bustracker.config.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        log.error("Unauthorized error: [path: {}, message: {}]", ServletUriComponentsBuilder.fromRequest(httpServletRequest).toUriString(), e.getMessage());
        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
        throw new BadCredentialsException("Unauthorized");

    }
}
