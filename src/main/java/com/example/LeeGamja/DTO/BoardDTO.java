package com.example.LeeGamja.DTO;

import lombok.*;


public class BoardDTO {
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class BoardRequestDto{
        private String title;
        private String username;
        private String text;

    }
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BoardResponseDto{
        private String title;
        private String username;
        private String text;

    }
}

