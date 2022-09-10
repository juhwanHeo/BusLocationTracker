package com.bustracker.service;

import com.bustracker.config.auth.UserDetailsImpl;
import com.bustracker.config.auth.jwt.JwtService;
import com.bustracker.config.auth.jwt.JwtTokenProvider;
import com.bustracker.dto.LoginResult;
import com.bustracker.dto.UserDTO;
import com.bustracker.entity.User;
import com.bustracker.exception.ResourceNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserService userService, JwtTokenProvider jwtTokenProvider, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginResult login(UserDTO userDTO) throws ResourceNotFoundException, JsonProcessingException {
        final UserDetailsImpl user = userService.loadUserByUsername(userDTO.getLoginId());

        if (user == null) throw new ResourceNotFoundException("Not found User");

        if (passwordEncoder.matches(userDTO.getPassword(), user.getPassword())) {
            final String jwt = jwtTokenProvider.create(user);
            return LoginResult.builder()
                    .jwt(jwt)
                    .build();
        }

        return null;
    }

    @Data
    static class Result {
        HttpStatus status;

    }
}
