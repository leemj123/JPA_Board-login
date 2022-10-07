package DTO;

import Entity.BoardListEntity;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class BoardResponseDTO {
    private String title;
    private String username;
    private String text;

    public BoardListEntity toEntity(){

        return new BoardListEntity(title,username,text);
    }
}

