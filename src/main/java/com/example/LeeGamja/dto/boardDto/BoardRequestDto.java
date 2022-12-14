package com.example.LeeGamja.dto.boardDto;

import com.example.LeeGamja.entity.UserEntity;
import lombok.*;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardRequestDto {
        private Long id;
        private String title;
        private String userName;
        private String text;
        private UserEntity userEntity;
        //private List<CommentEntity> commentEntity;
}

