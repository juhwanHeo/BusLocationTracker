package com.bustracker.entity;

import com.bustracker.config.auth.UserDetailsImpl;
import com.bustracker.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Slf4j
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "c_user")
public class User {

    @Id
    private String id;
    private String loginId;
    private String password;
    private String name;
    private String email;
    private List<UserRole> roles;
    private String facilityId;

    public UserDetailsImpl createUserDetails() {
        if (roles == null || roles.isEmpty()) roles = Collections.singletonList(UserRole.USER);
        List<SimpleGrantedAuthority> authList = roles.stream()
                .map(UserRole::toString)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        authList.forEach(o-> log.debug("authList -> {}",o.getAuthority()));

        return UserDetailsImpl.builder()
                .loginId(loginId)
                .password(password)
                .name(name)
                .email(email)
                .roles(roles)
                .facilityId(facilityId)
                .build();
    }
}
