package com.bustracker.entity;

import com.bustracker.enums.UserRole;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
public class User {

    @Id
    private String id;
    private String loginId;
    private String password;
    private String name;
    private String email;
    private List<UserRole> roles;
    private String facilityId;
}
