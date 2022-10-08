package com.example.LeeGamja.DTO;

import com.example.LeeGamja.Entity.BoardListEntity;
import lombok.*;


public class BoardDTO {
    @Getter @Setter
    public static class Request{
        private String title;
        private String username;
        private String text;
    }
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response{
        private String title;
        private String username;
        private String text;

        public BoardListEntity toEntity(){

            return new BoardListEntity(this.title,this.username,this.text);
        }
    }
}

