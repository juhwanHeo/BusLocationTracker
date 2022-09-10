package com.bustracker.service;

import com.bustracker.config.auth.UserDetailsImpl;
import com.bustracker.entity.User;
import com.bustracker.repository.AuthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final AuthUserRepository authUserRepository;

    @Autowired
    public UserService(AuthUserRepository authUserRepository) {
        this.authUserRepository = authUserRepository;
    }

    @Override
    public UserDetailsImpl loadUserByUsername(String loginId) throws UsernameNotFoundException {
        User user = authUserRepository.findByLoginId(loginId)
                .orElseThrow(() -> new UsernameNotFoundException("Not Found User by loginId: " + loginId));

        return user.createUserDetails();
    }
}
