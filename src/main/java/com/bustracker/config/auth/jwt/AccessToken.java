package com.bustracker.config.auth.jwt;

import com.bustracker.enums.UserRole;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AccessToken {
    private String id;
    private String email;
    private String name;
    private List<UserRole> roles;
    private String jwt;
    private long jwtExpTime;

    public AccessToken(String jwt, long jwtExpTime, String id, String name, String email) {
        this(jwt, jwtExpTime, id, name, email, new ArrayList<>());
    }

    public AccessToken(String jwt, long jwtExpTime, String id, String name, String email, List<UserRole> roles) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.roles = roles;
        this.jwt = jwt;
        this.jwtExpTime = jwtExpTime;
    }

    public String getJwtExpTimeString() {
        return String.valueOf(jwtExpTime);
    }
}
