package com.example.LeeGamja.entity;

import com.example.LeeGamja.enumcustom.UserRole;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Table(name ="Users")
public class UserEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30,unique = true)
    private String userId;

    @Column(nullable = false, length = 30)
    private String userPw;

    @Column(nullable = false, length = 10,unique = true)
    private String userName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole userRole;

}
