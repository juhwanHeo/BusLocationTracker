package com.bustracker.config.auth.filter;

import com.bustracker.config.auth.UserDetailsImpl;
import com.bustracker.config.auth.jwt.AccessToken;
import com.bustracker.config.auth.jwt.JwtTokenProvider;
import com.bustracker.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
* 역할별 API 제한 설정 필요
* */
@Slf4j
@Configuration
public class AuthFilter extends OncePerRequestFilter {

    private static final String TOKEN_PREFIX = "bearer";

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (isAsyncDispatch(request)) {
            filterChain.doFilter(request, response);
        }
        else if (request.getServletPath().equals("/api/user/login")) filterChain.doFilter(request, response);
        else {
            String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
            if (StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith(TOKEN_PREFIX)) {
                final String jwt = authorizationHeader.substring(TOKEN_PREFIX.length()).trim();
                if (jwtTokenProvider.isVerify(jwt)) {
                    AccessToken accessToken = jwtTokenProvider.getAccessToken(jwt);
                    UserDetailsImpl userDetails = userService.loadUserByUsername(accessToken.getId());
                    userDetails.setJwtToken(jwt);

                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    filterChain.doFilter(request, response);
                }
            }
        }
//        filterChain.doFilter(request, response);
    }

}
