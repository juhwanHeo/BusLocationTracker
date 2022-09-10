package com.bustracker.controller;

import com.bustracker.dto.UserDTO;
import com.bustracker.enums.UserRole;
import com.bustracker.exception.ResourceNotFoundException;
import com.bustracker.service.AuthService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class LoginController {

    private final AuthService authService;

    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    @Secured({UserRole.ROLES.ADMIN, UserRole.ROLES.MANAGER, UserRole.ROLES.DRIVER})
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO) {
        log.info("login {}", userDTO);
        try {
            return ResponseEntity.ok().body(authService.login(userDTO));
        } catch (ResourceNotFoundException | JsonProcessingException e) {
            throw new BadCredentialsException("Please enter your user name or password.");
        }
    }

}
