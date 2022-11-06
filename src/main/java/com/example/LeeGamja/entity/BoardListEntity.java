package com.example.LeeGamja.entity;
import com.example.LeeGamja.dto.boardDto.BoardRequestDto;
import com.example.LeeGamja.dto.commentDto.CommentRequestDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Table(name = "Board")
@Builder
public class BoardListEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // 즉시로딩 eager = 두개를 즉시 불러온다는데 더 공부 필요 // 지연로딩 LAZY;
    @JoinColumn(name ="user")
    private UserEntity userEntity;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String username;
    @Column(length=30)
    private String text;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name ="comment")
    private List<CommentEntity> commentEntity;


    public void transfer(BoardRequestDto boardRequestDto) {
        this.userEntity = boardRequestDto.getUserEntity();
        this.title = boardRequestDto.getTitle();
        this.username = boardRequestDto.getUserName();
        this.text = boardRequestDto.getText();
    }
    public void addComment(CommentRequestDto commentRequestDto){
        CommentEntity commentEntity1 = new CommentEntity().transfer(commentRequestDto);
    }
}
