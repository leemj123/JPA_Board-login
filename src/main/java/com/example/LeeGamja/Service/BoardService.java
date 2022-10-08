package com.example.LeeGamja.Service;

import com.example.LeeGamja.DTO.BoardDTO;
import com.example.LeeGamja.Entity.BoardListEntity;
import com.example.LeeGamja.Repository.ContentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final ContentsRepository contentsRepository;

    @Transactional
    public Page<BoardListEntity> readall(Pageable pageable) { // 전체 불러오기
        System.out.println(contentsRepository.findAll());
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
    public BoardListEntity Createboard (BoardListEntity boardListEntity) { //추가
        BoardListEntity tempboardE = BoardListEntity.builder().title(boardListEntity.getTitle())
                .username(boardListEntity.getUsername())//데이터 받아서 객체 생성할때 id가 만들어짐
                .text(boardListEntity.getText())
                .build();
        return contentsRepository.save(tempboardE);
    }
    @Transactional
    public ResponseEntity Boardupdate(Long id, BoardListEntity boardListEntity){ // 수정
        // pk타입으로 꺼내서 넣을때는 예외처리안하면 오류발생!
      BoardListEntity boardListE = contentsRepository.findById(id).orElseThrow(
              () -> { throw new RuntimeException("해당 정보가 없습니다.");});
        //받은 entity를 dto로 변환
        BoardDTO.Response boardResponse = new BoardDTO.Response();
        //빌더로 집어넣기
        boardResponse.builder().title(boardListEntity.getTitle())
                .username(boardListEntity.getUsername())
                .text(boardListEntity.getText());
        //dto를 entity 전환
        boardListE = boardResponse.toEntity();
     /* boardListE.setTitle(targetEntity.getTitle());
      boardListE.setUsername(targetEntity.getUsername());
      boardListE.setText(targetEntity.getText());*/
      contentsRepository.save(boardListE);
      System.out.println(contentsRepository.findById(id));
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
