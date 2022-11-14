package com.example.LeeGamja.dto.boardDto;

import com.example.LeeGamja.entity.BoardListEntity;
import com.example.LeeGamja.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardListResponseDto {
    private String title;
    private String username;
    private String text;
    private UserEntity userEntity;
    public BoardListResponseDto(BoardListEntity boardListEntity){
        this.title = boardListEntity.getTitle();
        this.username = boardListEntity.getUsername();
        this.text = boardListEntity.getText();
        this.userEntity = boardListEntity.getUserEntity();
    }
}