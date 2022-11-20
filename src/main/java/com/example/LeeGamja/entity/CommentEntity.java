//package com.example.LeeGamja.entity;
//
//import com.example.LeeGamja.dto.commentDto.CommentRequestDto;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//
//@Entity
//@Getter
//@NoArgsConstructor
//@AllArgsConstructor
//@Table(name ="comment")
//public class CommentEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column
//    private Long id;
//    @Column
//    private String userName;
//    @Column
//    private String text;
//
//    public void transfer(CommentRequestDto commentRequestDto){
//        this.userName = commentRequestDto.getUserName();
//        this.text = commentRequestDto.getText();
//    }
//}
