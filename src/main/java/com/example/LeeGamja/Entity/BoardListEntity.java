package com.example.LeeGamja.Entity;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter @Setter
@Table(name = "Board")
@Builder
public class BoardListEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String username;
    @Column(length=30)
    private String text;

    public BoardListEntity(String title, String username, String text) {
        this.title = title;
        this.username = username;
        this.text = text;
    }
}
