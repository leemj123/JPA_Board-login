package com.example.LeeGamja.dto.boardDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardResponseDto {
        private String title;
        private String username;
        private String text;
}
