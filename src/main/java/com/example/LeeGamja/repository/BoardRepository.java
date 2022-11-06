package com.example.LeeGamja.repository;

import com.example.LeeGamja.entity.BoardListEntity;
import com.example.LeeGamja.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<BoardListEntity, Long> {
    List<BoardListEntity> findByUserEntity(UserEntity userEntity);
}
