package com.example.LeeGamja.repository;

import com.example.LeeGamja.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUserName(String userName);
    UserEntity findByUserId(String userId);

    boolean existsByUserId(String userId);
}
