package com.example.LeeGamja.dto.boardDto;

import com.example.LeeGamja.entity.BoardListEntity;
import com.example.LeeGamja.entity.UserEntity;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardRequestDto {
        private String title;
        private String userName;
        private String text;
        private UserEntity userEntity;

}

