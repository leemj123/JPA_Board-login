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
    @Column
    private long id;
    @Column(nullable = false)
    private String title;
    @Column
    private String username;
    @Column(nullable = false)
    private String text;

    public BoardListEntity(String title, String username, String text) {
        this.title = title;
        this.username = username;
        this.text = text;
    }
}
