package com.example.LeeGamja.dto.boardDto;

import com.example.LeeGamja.entity.BoardListEntity;
import com.example.LeeGamja.entity.CommentEntity;
import com.example.LeeGamja.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardResponseDto {
        private String title;
        private String username;
        private String text;
        private UserEntity userEntity;
        private List<CommentEntity> commentEntity;
        public BoardResponseDto(BoardListEntity boardListEntity){
                this.title = boardListEntity.getTitle();
                this.username = boardListEntity.getUsername();
                this.text = boardListEntity.getText();
                this.userEntity = boardListEntity.getUserEntity();
                this.commentEntity = boardListEntity.getCommentEntity();
        }
}
