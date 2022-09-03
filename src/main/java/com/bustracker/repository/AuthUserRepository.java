package com.bustracker.repository;

import com.bustracker.config.auth.UserDetailsImpl;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AuthUserRepository extends MongoRepository<UserDetailsImpl, String> {
    Optional<UserDetailsImpl> findByLoginId(String loginId);
}
