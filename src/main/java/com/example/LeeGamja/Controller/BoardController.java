package com.example.LeeGamja.Controller;


import com.example.LeeGamja.Entity.BoardListEntity;
import com.example.LeeGamja.Service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

public class BoardController {
    @Autowired
    private BoardService boardService;

    @GetMapping("/read") // 모두 읽어오기
    public Page<BoardListEntity> Mainboard(Pageable pageable){
        return boardService.readall(pageable);
    }
    @GetMapping("/oneread/{id}")// id 게시글 읽어오기
    public ResponseEntity<BoardListEntity> onereadboard(@PathVariable Long id){
        return boardService.oneread(id);
    }
    @PostMapping("/create")
    public BoardListEntity createboard(@RequestBody BoardListEntity boardListEntity){

        return boardService.Createboard(boardListEntity);
    }

    @PostMapping("/updata/{id}")
    public ResponseEntity<BoardListEntity> updateboard(@PathVariable Long id, @RequestBody BoardListEntity boardListEntity){

        return boardService.Boardupdate(id, boardListEntity);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BoardListEntity> deleteboard(@PathVariable Long id, @RequestBody BoardListEntity boardListEntity){
        return boardService.Deleteboard(id);
    }

}
