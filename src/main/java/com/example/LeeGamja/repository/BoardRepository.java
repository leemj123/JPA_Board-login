package com.example.LeeGamja.repository;

import com.example.LeeGamja.entity.BoardListEntity;
import com.example.LeeGamja.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<BoardListEntity, Long> {

    List<BoardListEntity> findByUserEntity(UserEntity userEntity);

    @Query("SELECT b FROM BoardListEntity b JOIN FETCH b.userEntity")
    public List<BoardListEntity> findAllWithBoardListEntityUsingJoin();

    @Query("SELECT b FROM BoardListEntity b JOIN FETCH b.userEntity WHERE b.userEntity.userName = :userName ")
    public List<BoardListEntity> findAllByUserEntity(@Param("userName")String userName);

    @Query("SELECT b FROM BoardListEntity b JOIN FETCH b.userEntity WHERE b.id =:id")
    public BoardListEntity findByBoardId(@Param("id")Long id);
}
