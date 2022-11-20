package com.example.LeeGamja.dto.boardDto;

import com.example.LeeGamja.entity.BoardListEntity;
import com.example.LeeGamja.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Calendar;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardResponseDto {
        private Long id;
        private String title;
        private String username;
        private String text;
        private UserEntity userEntity;
        public BoardResponseDto(BoardListEntity boardListEntity){
                this.id = boardListEntity.getId();
                this.title = boardListEntity.getTitle();
                this.username = boardListEntity.getUsername();
                this.text = boardListEntity.getText();
                this.userEntity = boardListEntity.getUserEntity();
        }
        /*public List<BoardResponseDto> listResponse(List<BoardListEntity> boardListEntitie){
                for(int i =0 ; i<boardListEntitie.size();i++){
                        listResponse().add(i, boardListEntitie.get(i));
                }
        }*/

}
