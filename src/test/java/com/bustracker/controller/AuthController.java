package com.bustracker.controller;

import com.bustracker.dto.UserDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping(value = "/login")
    public String login(@RequestBody UserDTO userDTO) {

        return "login";
    }

}
