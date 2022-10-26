package com.example.LeeGamja.entity;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Table(name = "Board")
@Builder
public class BoardListEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;

   /* @ManyToOne(fetch = FetchType.EAGER) // 즉시로딩 eager = 두개를 즉시 불러온다는데 더 공부 필요 // 지연로딩 LAZY;
    @JoinColumn(name ="userName")
    private UserEntity userEntity;*/
    private String username;
    @Column(length=30)
    private String text;

    public void update(String title, String username, String text) {
        this.title = title; this.username = username; this.text = text;
    }
}
