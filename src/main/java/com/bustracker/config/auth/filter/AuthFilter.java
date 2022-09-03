package com.bustracker.config.auth.filter;

import com.bustracker.config.auth.jwt.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class AuthFilter extends OncePerRequestFilter {

    private static final String TOKEN_PREFIX = "Bearer";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (isAsyncDispatch(request)) {
            filterChain.doFilter(request, response);
        }
        else if (request.getServletPath().equals("/login")) filterChain.doFilter(request, response);
        else {
            String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
            if (StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith(TOKEN_PREFIX)) {
                final String jwt = request.getHeader(JwtTokenProvider.BEARER_TYPE);
                if (JwtTokenProvider.isVerify(jwt)) filterChain.doFilter(request, response);
            }
        }
        filterChain.doFilter(request, response);
    }

}
