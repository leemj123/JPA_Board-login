package com.example.LeeGamja.Repository;

import com.example.LeeGamja.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUserId(String userId);
    boolean existsByUserId(String userId);
}
