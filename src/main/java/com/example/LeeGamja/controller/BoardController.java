package com.example.LeeGamja.controller;


import com.example.LeeGamja.dto.BoardRequestDto;
import com.example.LeeGamja.entity.BoardListEntity;
import com.example.LeeGamja.service.BoardService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/read") // 모두 읽어오기
    public Page<BoardListEntity> Mainboard(Pageable pageable){
        return boardService.readall(pageable);
    }
    @GetMapping("/oneread/{id}")// id 게시글 읽어오기
    public ResponseEntity<BoardListEntity> onereadboard(@CookieValue(value = "userName",required = true)
                                                            @PathVariable Long id){return boardService.oneread(id);
    }
    @PostMapping("/create")
    public String createboard(@RequestBody BoardListEntity boardListEntity){

        return boardService.Createboard(boardListEntity);
    }

    @PutMapping("/updata/{id}")
    public ResponseEntity updateboard(@PathVariable Long id, @RequestBody BoardRequestDto boardRequestDto){
        return boardService.Boardupdate(id,boardRequestDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteboard(@PathVariable Long id){
        return boardService.Deleteboard(id);
    }

}
