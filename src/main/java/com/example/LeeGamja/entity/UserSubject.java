package com.example.LeeGamja.entity;

import javax.persistence.*;

@Entity
@Table(name ="usersubject")
public class UserSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

}
