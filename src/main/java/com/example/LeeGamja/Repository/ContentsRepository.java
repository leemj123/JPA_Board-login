package com.example.LeeGamja.Repository;

import com.example.LeeGamja.Entity.BoardListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentsRepository extends JpaRepository<BoardListEntity, Long> {
     //page에 대해 공부
}
