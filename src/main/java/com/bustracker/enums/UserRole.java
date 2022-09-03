package com.bustracker.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@Getter
@AllArgsConstructor
public enum UserRole implements GrantedAuthority {

    ADMIN(ROLES.ADMIN, "어드민"),
    DRIVER(UserRole.ROLES.DRIVER, "운전기사"),
    MANAGER(UserRole.ROLES.MANAGER, "관리자"),
    USER(UserRole.ROLES.USER, "유저");

    public static class ROLES {
        public static final String ADMIN = "ROLE_ADMIN";
        public static final String DRIVER = "ROLE_DRIVER";
        public static final String MANAGER = "ROLE_MANAGER";
        public static final String USER = "ROLE_USER";
    }

    private final String authority;
    private final String description;

    @Override
    public String getAuthority() {
        return authority;
    }

    @Override
    public String toString() {
        return authority;
    }
}
