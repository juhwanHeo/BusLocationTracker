package com.bustracker.repository;

import com.bustracker.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AuthUserRepository extends MongoRepository<User, String> {
    Optional<User> findByLoginId(String loginId);
}
