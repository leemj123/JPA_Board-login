package com.example.LeeGamja.repository;

import com.example.LeeGamja.entity.BoardListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentsRepository extends JpaRepository<BoardListEntity, Long> {
     //page에 대해 공부
}
