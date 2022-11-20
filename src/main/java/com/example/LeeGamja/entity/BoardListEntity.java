package com.example.LeeGamja.entity;
import com.example.LeeGamja.dto.boardDto.BoardRequestDto;
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

    @ManyToOne(fetch = FetchType.LAZY)// 지연로딩 LAZY;
    @JoinColumn(name ="user")
    private UserEntity userEntity;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String username;
    @Column(length=30)
    private String text;


    public void transfer(BoardRequestDto boardRequestDto) {
        this.userEntity = boardRequestDto.getUserEntity();
        this.title = boardRequestDto.getTitle();
        this.username = boardRequestDto.getUserName();
        this.text = boardRequestDto.getText();
    }
}
