package com.example.LeeGamja.Service;

import com.example.LeeGamja.Entity.BoardListEntity;
import com.example.LeeGamja.Repository.ContentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoardService {
    @Autowired
    private ContentsRepository contentsRepository;

    @Transactional
    public Page<BoardListEntity> readall(Pageable pageable) { // 전체 불러오기
        System.out.println(contentsRepository.findAll());
        return contentsRepository.findAll(pageable);
    }
    @Transactional
    public ResponseEntity oneread(Long id){
        BoardListEntity boardfind = contentsRepository.findById(id).orElseThrow(
                ()->{throw new RuntimeException("해당 정보가 없습니다");});
        return ResponseEntity.ok(boardfind);
    }

    @Transactional
    public BoardListEntity Createboard (BoardListEntity boardListEntity) { //추가
        return contentsRepository.save(boardListEntity);
    }
    @Transactional
    public ResponseEntity Boardupdate(Long id, BoardListEntity targetEntity){ // 수정
      BoardListEntity boardListE = contentsRepository.findById(id).orElseThrow( //pk타입으로 꺼내서 넣을때는 예외처리안하면 오류발생!
              () -> { throw new RuntimeException("해당 정보가 없습니다.");});
      boardListE.setTitle(targetEntity.getTitle());
      boardListE.setUsername(targetEntity.getUsername());
      boardListE.setText(targetEntity.getText());
      BoardListEntity yes = contentsRepository.save(boardListE);
      return ResponseEntity.ok(boardListE); // 공부거리
    }

    @Transactional
    public ResponseEntity Deleteboard(Long id){// 삭제
        BoardListEntity boardListE = contentsRepository.findById(id).orElseThrow(()->
        {throw new RuntimeException("삭제 할 정보가 없습니다.");});
        contentsRepository.deleteById(id);
        return ResponseEntity.ok(boardListE);
    }
}
