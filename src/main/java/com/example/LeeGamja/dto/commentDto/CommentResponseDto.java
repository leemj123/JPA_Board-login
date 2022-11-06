package com.example.LeeGamja.dto.commentDto;

import com.example.LeeGamja.entity.BoardListEntity;
import com.example.LeeGamja.entity.CommentEntity;

public class CommentResponseDto {
    private Long id;
    private BoardListEntity boardListEntity;
    private String userName;
    private String text;

    public CommentResponseDto(CommentEntity commentEntity){
        this.id = commentEntity.getId();
        this.boardListEntity = commentEntity.getBoardListEntity();
        this.userName = commentEntity.getUserName();
        this.text = commentEntity.getText();
    }
}
