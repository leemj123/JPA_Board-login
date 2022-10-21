package com.example.LeeGamja.Service;

import com.example.LeeGamja.DTO.BoardRequestDto;
import com.example.LeeGamja.Entity.BoardListEntity;
import com.example.LeeGamja.Repository.ContentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CookieValue;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final ContentsRepository contentsRepository;

    @Transactional
    public Page<BoardListEntity> readall(Pageable pageable) { // 전체 불러오기
        return contentsRepository.findAll(pageable);
    }
    @Transactional
    public ResponseEntity oneread(Long id){
        BoardListEntity boardfind = contentsRepository.findById(id).orElseThrow(
                ()->{throw new RuntimeException("해당 정보가 없습니다");});
        System.out.println(contentsRepository.findById(id));
        return ResponseEntity.ok(boardfind);
    }

    @Transactional
    public String Createboard (BoardListEntity boardListEntity) { //추가
        BoardListEntity targetboard = BoardListEntity.builder().title(boardListEntity.getTitle())
        .username(boardListEntity.getUsername())//데이터 받아서 객체 생성할때 id가 만들어짐
        .text(boardListEntity.getText())
        .build();
        contentsRepository.save(targetboard);
        return "작성완료";
    }
    @Transactional
    public ResponseEntity Boardupdate(Long id, BoardRequestDto boardRequestDto){ // 수정
        // pk타입으로 꺼내서 넣을때는 예외처리안하면 오류발생!
       BoardListEntity boardListEntity = contentsRepository.findById(id).orElseThrow(() ->
        {throw new RuntimeException("해당 정보가 없습니다");});
       boardListEntity.update(boardRequestDto.getTitle()
               ,boardRequestDto.getUsername()
               ,boardRequestDto.getText());
       return ResponseEntity.ok(contentsRepository.findById(id));
    }

    @Transactional
    public ResponseEntity Deleteboard(Long id){// 삭제
        BoardListEntity Temp= contentsRepository.findById(id).orElseThrow(()->
        {throw new RuntimeException("삭제 할 정보가 없습니다.");});
        contentsRepository.deleteById(id);
        return ResponseEntity.ok("삭제완료");
    }
}