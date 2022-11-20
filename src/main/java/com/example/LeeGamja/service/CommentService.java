//package com.example.LeeGamja.service;
//
//import com.example.LeeGamja.dto.boardDto.BoardRequestDto;
//import com.example.LeeGamja.dto.boardDto.BoardResponseDto;
//import com.example.LeeGamja.dto.commentDto.CommentRequestDto;
//import com.example.LeeGamja.entity.BoardListEntity;
//import com.example.LeeGamja.entity.CommentEntity;
//import com.example.LeeGamja.repository.BoardRepository;
//import com.example.LeeGamja.repository.CommentRepository;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//@Slf4j
//public class CommentService {
//    private final CommentRepository commentRepository;
//    private final BoardRepository boardRepository;
//
//    public String add(Long id,String user, CommentRequestDto commentRequestDto){
//        BoardListEntity boardListEntity = boardRepository.findById(id).orElseThrow(
//                ()->{throw new RuntimeException("해당 정보가 없습니다");});
//        log.info(user);
//        commentRequestDto.setUserName(user);
//        log.info(commentRequestDto.getText());
//        CommentEntity commentEntity = new CommentEntity();
//        commentEntity.transfer(commentRequestDto);
//        log.info(commentEntity.getText());
//        commentRepository.save(commentEntity);
//
//        //boardListEntity.addComment(commentEntity);
//        return "완료";
//    }
//}
